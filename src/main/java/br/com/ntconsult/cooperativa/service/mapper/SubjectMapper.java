package br.com.ntconsult.cooperativa.service.mapper;

import br.com.ntconsult.cooperativa.domain.Subject;
import br.com.ntconsult.cooperativa.service.dto.SubjectCreateRequestDTO;
import br.com.ntconsult.cooperativa.service.dto.SubjectDTO;
import br.com.ntconsult.cooperativa.service.dto.SubjectResultDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {SessionMapper.class})
public interface SubjectMapper extends EntityMapper<SubjectDTO, Subject> {

    @Mapping(source = "session.id", target = "sessionId")
    @Mapping(source = "session.startDateTime", target = "sessionStartDateTime")
    @Mapping(source = "session.endDateTime", target = "sessionEndDateTime")
    SubjectDTO toDto(Subject subject);

    @Mapping(source = "session.id", target = "sessionId")
    @Mapping(source = "session.startDateTime", target = "sessionStartDateTime")
    @Mapping(source = "session.endDateTime", target = "sessionEndDateTime")
    SubjectResultDTO toResultDto(Subject subject);

    Subject toEntity(SubjectCreateRequestDTO subjectCreateRequestDTO);

    @Mapping(source = "sessionId", target = "session.id")
    Subject toEntity(SubjectDTO subjectDTO);
}
