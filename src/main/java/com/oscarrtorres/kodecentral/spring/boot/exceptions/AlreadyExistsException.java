package com.oscarrtorres.kodecentral.spring.boot.exceptions;

public class AlreadyExistsException extends RuntimeException {
    public AlreadyExistsException(String message) {
        super(message);
    }
}
