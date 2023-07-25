package br.com.ntconsult.cooperativa.service.mapper;

import br.com.ntconsult.cooperativa.domain.Session;
import br.com.ntconsult.cooperativa.domain.Subject;
import br.com.ntconsult.cooperativa.service.dto.SessionDTO;
import br.com.ntconsult.cooperativa.service.dto.SessionInitRequestDTO;
import br.com.ntconsult.cooperativa.service.dto.SessionVotingResultDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-07-25T16:55:24-0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.20 (Amazon.com Inc.)"
)
@Component
public class SessionMapperImpl implements SessionMapper {

    @Override
    public List<Session> toEntity(List<SessionDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Session> list = new ArrayList<Session>( dtoList.size() );
        for ( SessionDTO sessionDTO : dtoList ) {
            list.add( toEntity( sessionDTO ) );
        }

        return list;
    }

    @Override
    public List<SessionDTO> toDto(List<Session> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<SessionDTO> list = new ArrayList<SessionDTO>( entityList.size() );
        for ( Session session : entityList ) {
            list.add( toDto( session ) );
        }

        return list;
    }

    @Override
    public SessionDTO toDto(Session session) {
        if ( session == null ) {
            return null;
        }

        SessionDTO sessionDTO = new SessionDTO();

        sessionDTO.setSubjectSubject( sessionSubjectSubject( session ) );
        sessionDTO.setSubjectId( sessionSubjectId( session ) );
        sessionDTO.setId( session.getId() );
        sessionDTO.setStartDateTime( session.getStartDateTime() );
        sessionDTO.setEndDateTime( session.getEndDateTime() );

        return sessionDTO;
    }

    @Override
    public SessionVotingResultDTO toSessionVotingResultDto(Session session) {
        if ( session == null ) {
            return null;
        }

        SessionVotingResultDTO sessionVotingResultDTO = new SessionVotingResultDTO();

        sessionVotingResultDTO.setSubjectId( sessionSubjectId( session ) );
        sessionVotingResultDTO.setSubjectSubject( sessionSubjectSubject( session ) );
        sessionVotingResultDTO.setId( session.getId() );
        sessionVotingResultDTO.setStartDateTime( session.getStartDateTime() );
        sessionVotingResultDTO.setEndDateTime( session.getEndDateTime() );

        return sessionVotingResultDTO;
    }

    @Override
    public SessionDTO toDto(SessionInitRequestDTO initRequestDTO) {
        if ( initRequestDTO == null ) {
            return null;
        }

        SessionDTO sessionDTO = new SessionDTO();

        sessionDTO.setSubjectId( initRequestDTO.getSubjectId() );
        sessionDTO.setEndDateTime( initRequestDTO.getEndDateTime() );

        return sessionDTO;
    }

    @Override
    public Session toEntity(SessionDTO sessionDTO) {
        if ( sessionDTO == null ) {
            return null;
        }

        Session session = new Session();

        session.setSubject( sessionDTOToSubject( sessionDTO ) );
        session.setEndDateTime( sessionDTO.getEndDateTime() );
        session.setId( sessionDTO.getId() );
        session.setStartDateTime( sessionDTO.getStartDateTime() );

        return session;
    }

    private String sessionSubjectSubject(Session session) {
        if ( session == null ) {
            return null;
        }
        Subject subject = session.getSubject();
        if ( subject == null ) {
            return null;
        }
        String subject1 = subject.getSubject();
        if ( subject1 == null ) {
            return null;
        }
        return subject1;
    }

    private Long sessionSubjectId(Session session) {
        if ( session == null ) {
            return null;
        }
        Subject subject = session.getSubject();
        if ( subject == null ) {
            return null;
        }
        Long id = subject.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    protected Subject sessionDTOToSubject(SessionDTO sessionDTO) {
        if ( sessionDTO == null ) {
            return null;
        }

        Subject subject = new Subject();

        subject.setId( sessionDTO.getSubjectId() );

        return subject;
    }
}
