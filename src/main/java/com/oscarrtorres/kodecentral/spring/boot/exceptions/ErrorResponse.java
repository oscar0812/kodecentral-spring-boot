package com.oscarrtorres.kodecentral.spring.boot.exceptions;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class ErrorResponse {
    // customizing timestamp serialization format
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private Date timestamp;
    private int code;
    private String status;
    private List<String> errors = new ArrayList<>();
    private List<String> causeStackTrace;
    private Object data;

    public ErrorResponse() {
        timestamp = new Date();
    }

    public ErrorResponse(HttpStatus httpStatus) {
        this();
        this.code = httpStatus.value();
        this.status = httpStatus.name();
    }

    public ErrorResponse(HttpStatus httpStatus, List<Exception> causeStackTrace) {
        this(httpStatus);
        // skip the first element 1->N to avoid putting the error message twice
        this.causeStackTrace = causeStackTrace.stream().map(Throwable::getMessage).skip(1).toList();
    }

    public ErrorResponse(HttpStatus httpStatus, String error, List<Exception> causeStackTrace) {
        this(httpStatus, causeStackTrace);
        this.errors.add(error);
    }

    public ErrorResponse(HttpStatus httpStatus, List<String> errors, List<Exception> causeStackTrace) {
        this(httpStatus, causeStackTrace);
        this.errors = errors;

    }
}