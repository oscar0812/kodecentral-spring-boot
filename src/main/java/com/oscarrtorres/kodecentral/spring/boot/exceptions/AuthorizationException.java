package com.oscarrtorres.kodecentral.spring.boot.exceptions;


public class AuthorizationException extends RuntimeException {
    public AuthorizationException() {
        super("Unauthorized data access");
    }

    public AuthorizationException(String msg) {
        super(msg);
    }
}
