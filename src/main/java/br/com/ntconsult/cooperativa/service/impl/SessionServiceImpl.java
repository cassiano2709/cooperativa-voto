package br.com.ntconsult.cooperativa.service.impl;

import br.com.ntconsult.cooperativa.domain.Session;
import br.com.ntconsult.cooperativa.domain.Vote;
import br.com.ntconsult.cooperativa.domain.enumeration.VoteType;
import br.com.ntconsult.cooperativa.exception.BadRequestAlertException;
import br.com.ntconsult.cooperativa.exception.InvalidParameterException;
import br.com.ntconsult.cooperativa.repositoty.SessionRepository;
import br.com.ntconsult.cooperativa.service.SessionService;
import br.com.ntconsult.cooperativa.service.SubjectService;
import br.com.ntconsult.cooperativa.service.dto.SessionDTO;
import br.com.ntconsult.cooperativa.service.dto.SessionInitRequestDTO;
import br.com.ntconsult.cooperativa.service.dto.SessionVotingResultDTO;
import br.com.ntconsult.cooperativa.service.mapper.SessionMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class SessionServiceImpl implements SessionService {

    private final SessionRepository sessionRepository;

    private final SessionMapper sessionMapper;

    private final SubjectService subjectService;

    private final KafkaProducerService kfkaProducerService;

    @Value("${mensageria.topic.name}")
    private String topicName;

    public SessionServiceImpl(SessionRepository sessionRepository, SessionMapper sessionMapper, SubjectService subjectService, KafkaProducerService kfkaProducerService) {
        this.sessionRepository = sessionRepository;
        this.sessionMapper = sessionMapper;
        this.subjectService = subjectService;
        this.kfkaProducerService = kfkaProducerService;
    }

    /**
     * Save a session.
     *
     * @param sessionInitRequestDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public SessionDTO init(SessionInitRequestDTO sessionInitRequestDTO) {
        log.debug("Request to save Session : {}", sessionInitRequestDTO);
        subjectService.findOne(sessionInitRequestDTO.getSubjectId()).orElseThrow(() -> {
            throw new EntityNotFoundException("The Subject does not exist!");
        });

        findOneBySubjectId(sessionInitRequestDTO.getSubjectId())
                .ifPresent((s) -> {
                    throw new BadRequestAlertException("The session has already been started");
                });

        SessionDTO sessionDTO = sessionMapper.toDto(sessionInitRequestDTO);
        sessionDTO.setStartDateTime(LocalDateTime.now());
        validateEndDateTime(sessionDTO);
        Session session = sessionMapper.toEntity(sessionDTO);

        session = sessionRepository.save(session);
        return sessionMapper.toDto(session);
    }

    /**
     * Get one session by id Subject.
     *
     * @param subjectId the cpf of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<SessionDTO> findOneBySubjectId(Long subjectId) {
        log.debug("Request to get session by id subject : {}", subjectId);
        return sessionRepository.findFirstBySubjectId(subjectId)
                .map(sessionMapper::toDto);
    }

    /**
     * Get all the sessions.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<SessionDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Sessions");
        return sessionRepository.findAll(pageable)
                .map(sessionMapper::toDto);
    }


    /**
     * Get one session by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<SessionDTO> findOne(Long id) {
        log.debug("Request to get Session : {}", id);
        return sessionRepository.findById(id)
                .map(sessionMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public void isOpen(Long id) {
        SessionDTO sessionDTO = findOne(id).get();
        if (!isOpen(sessionDTO)) {
            throw new BadRequestAlertException("This session is closed!");
        }
    }

    /**
     * This method returns a SessionVotingResultDTO with voting result.
     *
     * @param session the entity to get the votes.
     * @return the SessionVotingResultDTO with voting result.
     */
    private SessionVotingResultDTO setValuesSessionVotingResultDTO(Session session) {
        log.debug("Set values voting result of session {}", session.getId());
        SessionVotingResultDTO sessionVotingResultDTO = sessionMapper.toSessionVotingResultDto(session);
        List<Vote> votes = session.getVotes();
        sessionVotingResultDTO.setYesVotes(votes.stream().filter(vote -> vote.getVote().equals(VoteType.Sim)).count());
        sessionVotingResultDTO.setNoVotes(votes.size() - sessionVotingResultDTO.getYesVotes());
        return sessionVotingResultDTO;
    }

    /**
     * This method check closed Sessions and send messages to topic.
     */
    @Override
    @Transactional
    @Scheduled(fixedRate = 40000, zone = "America/Brasilia")
    public void checkClosedSessionsAndSendMessageToTopic() throws JsonProcessingException {
        log.debug("Call to check closed sessions and send message to topic");
        LocalDateTime n = LocalDateTime.now();
        List<Session> listClosedSessions = sessionRepository.findAllByInformedClosingAndEndDateTimeIsLessThanEqual(false, LocalDateTime.now());
        for (Session session : listClosedSessions) {
            log.debug("Send message inform closing of session {}", session.getId());

            session.setInformedClosing(true);

            SessionVotingResultDTO sessionVotingResultDTO = setValuesSessionVotingResultDTO(session);

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.findAndRegisterModules();
            String json = objectMapper.writeValueAsString(sessionVotingResultDTO);

            kfkaProducerService.sendMessage(topicName, json);

            sessionRepository.save(session);
        }
    }

    @Override
    public void setDefaultValueToEndDateTime(SessionDTO sessionDTO) {
        if (sessionDTO.getStartDateTime() == null) {
            sessionDTO.setStartDateTime(LocalDateTime.now());
        }
        sessionDTO.setEndDateTime(sessionDTO.getStartDateTime().plusMinutes(1));
    }

    @Override
    public void validateEndDateTime(SessionDTO sessionDTO) {
        if (sessionDTO.getEndDateTime() == null) {
            this.setDefaultValueToEndDateTime(sessionDTO);
        } else if (LocalDateTime.now().isAfter(sessionDTO.getEndDateTime())) {
            throw new InvalidParameterException("End date must be greater than current");
        }
    }

    @Override
    public boolean isOpen(SessionDTO sessionDTO) {
        LocalDateTime now = LocalDateTime.now();
        if (sessionDTO.getEndDateTime().isAfter(now)) {
            return true;
        } else return false;
    }
}

