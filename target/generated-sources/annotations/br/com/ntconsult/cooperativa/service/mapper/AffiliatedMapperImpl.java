package br.com.ntconsult.cooperativa.service.mapper;

import br.com.ntconsult.cooperativa.domain.Affiliated;
import br.com.ntconsult.cooperativa.service.dto.AffiliatedDTO;
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
public class AffiliatedMapperImpl implements AffiliatedMapper {

    @Override
    public Affiliated toEntity(AffiliatedDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Affiliated affiliated = new Affiliated();

        affiliated.setId( dto.getId() );
        affiliated.setNome( dto.getNome() );
        affiliated.setCpf( dto.getCpf() );

        return affiliated;
    }

    @Override
    public AffiliatedDTO toDto(Affiliated entity) {
        if ( entity == null ) {
            return null;
        }

        AffiliatedDTO affiliatedDTO = new AffiliatedDTO();

        affiliatedDTO.setId( entity.getId() );
        affiliatedDTO.setNome( entity.getNome() );
        affiliatedDTO.setCpf( entity.getCpf() );

        return affiliatedDTO;
    }

    @Override
    public List<Affiliated> toEntity(List<AffiliatedDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Affiliated> list = new ArrayList<Affiliated>( dtoList.size() );
        for ( AffiliatedDTO affiliatedDTO : dtoList ) {
            list.add( toEntity( affiliatedDTO ) );
        }

        return list;
    }

    @Override
    public List<AffiliatedDTO> toDto(List<Affiliated> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<AffiliatedDTO> list = new ArrayList<AffiliatedDTO>( entityList.size() );
        for ( Affiliated affiliated : entityList ) {
            list.add( toDto( affiliated ) );
        }

        return list;
    }
}
