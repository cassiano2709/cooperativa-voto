package br.com.ntconsult.cooperativa.config;


import br.com.ntconsult.cooperativa.exception.CpfNotFoundException;
import io.swagger.models.Response;
import feign.codec.ErrorDecoder;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeingConfiguration {

    @Bean
    public ResponseErrorDecoder clientErrorDecoder() {
        return new ResponseErrorDecoder();
    }

    public class ResponseErrorDecoder implements ErrorDecoder {
        @SneakyThrows
        public Exception decode(String key, Response response) {
            return new CpfNotFoundException("The CPF is invalid.");
        }

        @Override
        public Exception decode(String s, feign.Response response) {
            return null;
        }
    }
}
