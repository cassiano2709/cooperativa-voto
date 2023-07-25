package br.com.ntconsult.cooperativa.service.mapper;

import br.com.ntconsult.cooperativa.domain.Affiliated;
import br.com.ntconsult.cooperativa.service.dto.AffiliatedDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AffiliatedMapper extends EntityMapper<AffiliatedDTO, Affiliated> {
}
