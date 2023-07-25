package br.com.ntconsult.cooperativa.service.mapper;

import br.com.ntconsult.cooperativa.domain.Session;
import br.com.ntconsult.cooperativa.domain.Subject;
import br.com.ntconsult.cooperativa.service.dto.SubjectCreateRequestDTO;
import br.com.ntconsult.cooperativa.service.dto.SubjectDTO;
import br.com.ntconsult.cooperativa.service.dto.SubjectResultDTO;
import java.time.LocalDateTime;
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
public class SubjectMapperImpl implements SubjectMapper {

    @Override
    public List<Subject> toEntity(List<SubjectDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Subject> list = new ArrayList<Subject>( dtoList.size() );
        for ( SubjectDTO subjectDTO : dtoList ) {
            list.add( toEntity( subjectDTO ) );
        }

        return list;
    }

    @Override
    public List<SubjectDTO> toDto(List<Subject> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<SubjectDTO> list = new ArrayList<SubjectDTO>( entityList.size() );
        for ( Subject subject : entityList ) {
            list.add( toDto( subject ) );
        }

        return list;
    }

    @Override
    public SubjectDTO toDto(Subject subject) {
        if ( subject == null ) {
            return null;
        }

        SubjectDTO subjectDTO = new SubjectDTO();

        subjectDTO.setSessionId( subjectSessionId( subject ) );
        subjectDTO.setSessionStartDateTime( subjectSessionStartDateTime( subject ) );
        subjectDTO.setSessionEndDateTime( subjectSessionEndDateTime( subject ) );
        subjectDTO.setId( subject.getId() );
        subjectDTO.setSubject( subject.getSubject() );
        subjectDTO.setDateTime( subject.getDateTime() );

        return subjectDTO;
    }

    @Override
    public SubjectResultDTO toResultDto(Subject subject) {
        if ( subject == null ) {
            return null;
        }

        SubjectResultDTO subjectResultDTO = new SubjectResultDTO();

        subjectResultDTO.setSessionId( subjectSessionId( subject ) );
        subjectResultDTO.setSessionStartDateTime( subjectSessionStartDateTime( subject ) );
        subjectResultDTO.setSessionEndDateTime( subjectSessionEndDateTime( subject ) );
        subjectResultDTO.setId( subject.getId() );
        subjectResultDTO.setSubject( subject.getSubject() );
        subjectResultDTO.setDateTime( subject.getDateTime() );

        return subjectResultDTO;
    }

    @Override
    public Subject toEntity(SubjectCreateRequestDTO subjectCreateRequestDTO) {
        if ( subjectCreateRequestDTO == null ) {
            return null;
        }

        Subject subject = new Subject();

        subject.setSubject( subjectCreateRequestDTO.getSubject() );

        return subject;
    }

    @Override
    public Subject toEntity(SubjectDTO subjectDTO) {
        if ( subjectDTO == null ) {
            return null;
        }

        Subject subject = new Subject();

        subject.setSession( subjectDTOToSession( subjectDTO ) );
        subject.setId( subjectDTO.getId() );
        subject.setSubject( subjectDTO.getSubject() );
        subject.setDateTime( subjectDTO.getDateTime() );

        return subject;
    }

    private Long subjectSessionId(Subject subject) {
        if ( subject == null ) {
            return null;
        }
        Session session = subject.getSession();
        if ( session == null ) {
            return null;
        }
        Long id = session.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private LocalDateTime subjectSessionStartDateTime(Subject subject) {
        if ( subject == null ) {
            return null;
        }
        Session session = subject.getSession();
        if ( session == null ) {
            return null;
        }
        LocalDateTime startDateTime = session.getStartDateTime();
        if ( startDateTime == null ) {
            return null;
        }
        return startDateTime;
    }

    private LocalDateTime subjectSessionEndDateTime(Subject subject) {
        if ( subject == null ) {
            return null;
        }
        Session session = subject.getSession();
        if ( session == null ) {
            return null;
        }
        LocalDateTime endDateTime = session.getEndDateTime();
        if ( endDateTime == null ) {
            return null;
        }
        return endDateTime;
    }

    protected Session subjectDTOToSession(SubjectDTO subjectDTO) {
        if ( subjectDTO == null ) {
            return null;
        }

        Session session = new Session();

        session.setId( subjectDTO.getSessionId() );

        return session;
    }
}
