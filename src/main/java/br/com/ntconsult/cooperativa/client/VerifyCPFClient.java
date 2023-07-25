package br.com.ntconsult.cooperativa.client;


import br.com.ntconsult.cooperativa.service.dto.CpfExternalApiResultDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "cpfValidate", url = "${cooperativa.cpfExternalApi.url}")
public interface VerifyCPFClient {

    @GetMapping(value = "/{cpf}")
    CpfExternalApiResultDTO verifyCpfIsAbleToVote(@PathVariable("cpf") String cpf);
}