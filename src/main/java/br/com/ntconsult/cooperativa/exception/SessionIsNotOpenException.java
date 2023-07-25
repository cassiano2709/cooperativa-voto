package br.com.ntconsult.cooperativa.exception;

public class SessionIsNotOpenException extends RuntimeException {

    public SessionIsNotOpenException(String message){
        super(message);
    }
}
