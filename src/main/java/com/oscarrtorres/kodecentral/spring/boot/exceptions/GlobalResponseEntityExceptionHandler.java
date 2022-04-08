package com.oscarrtorres.kodecentral.spring.boot.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedList;
import java.util.List;

@ControllerAdvice
public class GlobalResponseEntityExceptionHandler {

    private List<Exception> stackTraceCauses(Exception e) {
        // converting the stack trace to String
        List<Exception> exceptions = new LinkedList<>();
        do {
            exceptions.add(e);
        } while((e = (Exception) e.getCause()) != null);

        return exceptions;
    }

    // fallback method
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleExceptions(HttpServletRequest request, Exception e) {
        e.printStackTrace();
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        ErrorResponse er = new ErrorResponse(status, e.getMessage(), stackTraceCauses(e));
        return new ResponseEntity<>(er, status);
    }
}