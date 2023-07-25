package br.com.ntconsult.cooperativa.exception;

public class CpfNotFoundException extends RuntimeException {
    public CpfNotFoundException(String message) {
        super(message);
    }
}
