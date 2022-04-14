package com.oscarrtorres.kodecentral.spring.boot.models.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Getter
@Setter
public class CustomHttpResponse {
    // customizing timestamp serialization format
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private Date timestamp;
    private int code;
    private String status;
    private List<String> errors = new ArrayList<>();
    private List<String> causeStackTrace;
    private HashMap<String, Object> data;

    public CustomHttpResponse() {
        timestamp = new Date();
    }

    public CustomHttpResponse(HttpStatus httpStatus) {
        this();
        this.code = httpStatus.value();
        this.status = httpStatus.name();
    }

    public CustomHttpResponse(HttpStatus httpStatus, List<Exception> causeStackTrace) {
        this(httpStatus);
        // skip the first element 1->N to avoid putting the error message twice
        this.causeStackTrace = causeStackTrace.stream().map(Throwable::getMessage).skip(1).toList();
    }

    public CustomHttpResponse(HttpStatus httpStatus, String error, List<Exception> causeStackTrace) {
        this(httpStatus, causeStackTrace);
        this.errors.add(error);
    }

    public CustomHttpResponse(HttpStatus httpStatus, List<String> errors, List<Exception> causeStackTrace) {
        this(httpStatus, causeStackTrace);
        this.errors = errors;
    }

    public CustomHttpResponse addData(String key, Object value) {
        data.put(key, value);
        return this;
    }

    public void setData(HashMap<String, Object> data) {
        this.data = data;
    }
}