package br.com.ntconsult.cooperativa.service;

import br.com.ntconsult.cooperativa.service.dto.VoteCreateRequestDTO;
import br.com.ntconsult.cooperativa.service.dto.VoteDTO;

import java.util.Optional;

public interface VoteService {
    /**
     * Save a vote.
     *
     * @param voteDTO the entity to save.
     * @return the persisted entity.
     */
    VoteDTO vote(VoteCreateRequestDTO voteDTO);


    /**
     * Get the "cpf" vote.
     *
     * @param cpf the cpf of the entity.
     * @return the entity.
     */
    Optional<VoteDTO> findOneByAffiliatedCpfAndSessionId(String cpf, Long sessionId);

    /**
     * Get the "cpf" vote.
     *
     * @param cpf the cpf of the entity.
     * @return if has or not.
     */
    boolean hasAffiliatedVoteInSessionByAffiliatedCpfAndSessionId(String cpf, Long sessionId);

}
