package br.com.ntconsult.cooperativa.service.impl;

import br.com.ntconsult.cooperativa.service.MensageriaProducerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaProducerService implements MensageriaProducerService {

    private final KafkaTemplate<String,String> kafkaTemplate;

    @Override
    public void sendMessage(String topic, String message){
        this.kafkaTemplate.send(topic, message);
    }


}
