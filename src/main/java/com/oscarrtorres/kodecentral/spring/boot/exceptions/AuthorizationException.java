package com.oscarrtorres.kodecentral.spring.boot.exceptions;


public class AuthorizationException extends RuntimeException {
    public AuthorizationException() {
        super("Unauthorized data access");
    }
}
