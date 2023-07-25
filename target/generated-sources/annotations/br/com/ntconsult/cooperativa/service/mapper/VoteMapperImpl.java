package br.com.ntconsult.cooperativa.service.mapper;

import br.com.ntconsult.cooperativa.domain.Affiliated;
import br.com.ntconsult.cooperativa.domain.Session;
import br.com.ntconsult.cooperativa.domain.Vote;
import br.com.ntconsult.cooperativa.service.dto.VoteCreateRequestDTO;
import br.com.ntconsult.cooperativa.service.dto.VoteDTO;
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
public class VoteMapperImpl implements VoteMapper {

    @Override
    public List<Vote> toEntity(List<VoteDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Vote> list = new ArrayList<Vote>( dtoList.size() );
        for ( VoteDTO voteDTO : dtoList ) {
            list.add( toEntity( voteDTO ) );
        }

        return list;
    }

    @Override
    public List<VoteDTO> toDto(List<Vote> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<VoteDTO> list = new ArrayList<VoteDTO>( entityList.size() );
        for ( Vote vote : entityList ) {
            list.add( toDto( vote ) );
        }

        return list;
    }

    @Override
    public VoteDTO toDto(Vote vote) {
        if ( vote == null ) {
            return null;
        }

        VoteDTO voteDTO = new VoteDTO();

        voteDTO.setSessionId( voteSessionId( vote ) );
        voteDTO.setSessionStartDateTime( voteSessionStartDateTime( vote ) );
        voteDTO.setSessionEndDateTime( voteSessionEndDateTime( vote ) );
        voteDTO.setAffiliatedId( voteAffiliatedId( vote ) );
        voteDTO.setAffiliatedCpf( voteAffiliatedCpf( vote ) );
        voteDTO.setId( vote.getId() );
        voteDTO.setVoteDateTime( vote.getVoteDateTime() );
        voteDTO.setVote( vote.getVote() );

        return voteDTO;
    }

    @Override
    public VoteDTO toDto(VoteCreateRequestDTO voteDTO) {
        if ( voteDTO == null ) {
            return null;
        }

        VoteDTO voteDTO1 = new VoteDTO();

        voteDTO1.setSessionId( voteDTO.getSessionId() );
        voteDTO1.setAffiliatedCpf( voteDTO.getAffiliatedCpf() );
        voteDTO1.setVote( voteDTO.getVote() );

        return voteDTO1;
    }

    @Override
    public Vote toEntity(VoteDTO voteDTO) {
        if ( voteDTO == null ) {
            return null;
        }

        Vote vote = new Vote();

        vote.setSession( voteDTOToSession( voteDTO ) );
        vote.setAffiliated( voteDTOToAffiliated( voteDTO ) );
        vote.setId( voteDTO.getId() );
        vote.setVoteDateTime( voteDTO.getVoteDateTime() );
        vote.setVote( voteDTO.getVote() );

        return vote;
    }

    @Override
    public Vote toEntity(VoteCreateRequestDTO voteDTO) {
        if ( voteDTO == null ) {
            return null;
        }

        Vote vote = new Vote();

        vote.setVote( voteDTO.getVote() );

        return vote;
    }

    private Long voteSessionId(Vote vote) {
        if ( vote == null ) {
            return null;
        }
        Session session = vote.getSession();
        if ( session == null ) {
            return null;
        }
        Long id = session.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private LocalDateTime voteSessionStartDateTime(Vote vote) {
        if ( vote == null ) {
            return null;
        }
        Session session = vote.getSession();
        if ( session == null ) {
            return null;
        }
        LocalDateTime startDateTime = session.getStartDateTime();
        if ( startDateTime == null ) {
            return null;
        }
        return startDateTime;
    }

    private LocalDateTime voteSessionEndDateTime(Vote vote) {
        if ( vote == null ) {
            return null;
        }
        Session session = vote.getSession();
        if ( session == null ) {
            return null;
        }
        LocalDateTime endDateTime = session.getEndDateTime();
        if ( endDateTime == null ) {
            return null;
        }
        return endDateTime;
    }

    private Long voteAffiliatedId(Vote vote) {
        if ( vote == null ) {
            return null;
        }
        Affiliated affiliated = vote.getAffiliated();
        if ( affiliated == null ) {
            return null;
        }
        Long id = affiliated.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String voteAffiliatedCpf(Vote vote) {
        if ( vote == null ) {
            return null;
        }
        Affiliated affiliated = vote.getAffiliated();
        if ( affiliated == null ) {
            return null;
        }
        String cpf = affiliated.getCpf();
        if ( cpf == null ) {
            return null;
        }
        return cpf;
    }

    protected Session voteDTOToSession(VoteDTO voteDTO) {
        if ( voteDTO == null ) {
            return null;
        }

        Session session = new Session();

        session.setId( voteDTO.getSessionId() );

        return session;
    }

    protected Affiliated voteDTOToAffiliated(VoteDTO voteDTO) {
        if ( voteDTO == null ) {
            return null;
        }

        Affiliated affiliated = new Affiliated();

        affiliated.setId( voteDTO.getAffiliatedId() );
        affiliated.setCpf( voteDTO.getAffiliatedCpf() );

        return affiliated;
    }
}
