package br.com.ntconsult.cooperativa.exception;

public class BadRequestAlertException extends RuntimeException {

    public BadRequestAlertException(String message) {
        super(message);
    }
}
