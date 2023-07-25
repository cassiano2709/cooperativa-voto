package br.com.ntconsult.cooperativa.service.mapper;

import br.com.ntconsult.cooperativa.domain.Session;
import br.com.ntconsult.cooperativa.service.dto.SessionDTO;
import br.com.ntconsult.cooperativa.service.dto.SessionInitRequestDTO;
import br.com.ntconsult.cooperativa.service.dto.SessionVotingResultDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {SubjectMapper.class})
public interface SessionMapper extends EntityMapper<SessionDTO, Session> {

    @Mapping(source = "subject.subject", target = "subjectSubject")
    @Mapping(source = "subject.id", target = "subjectId")
    SessionDTO toDto(Session session);

    @Mapping(source = "subject.id", target = "subjectId")
    @Mapping(source = "subject.subject", target = "subjectSubject")
    SessionVotingResultDTO toSessionVotingResultDto(Session session);

    @Mapping(source = "subjectId", target = "subjectId")
    @Mapping(source = "endDateTime", target = "endDateTime")
    SessionDTO toDto(SessionInitRequestDTO initRequestDTO);

    @Mapping(source = "subjectId", target = "subject.id")
    Session toEntity(SessionDTO sessionDTO);

}
