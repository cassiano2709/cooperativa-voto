package br.com.ntconsult.cooperativa.service;

public interface MensageriaProducerService {

    void sendMessage(String topic, String message);
}
