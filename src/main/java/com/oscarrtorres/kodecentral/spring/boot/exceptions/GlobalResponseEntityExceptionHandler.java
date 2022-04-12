package com.oscarrtorres.kodecentral.spring.boot.exceptions;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<String> messageList = e.getBindingResult().getFieldErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage).filter(Objects::nonNull).toList();

        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        ErrorResponse er = new ErrorResponse(status, messageList, stackTraceCauses(e));

        return new ResponseEntity<>(er, status);
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