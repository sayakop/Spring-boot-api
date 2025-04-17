package com.think.rest_demo.exception.VendorException;

import org.springframework.http.HttpStatus;

public class VendorException {

    private final HttpStatus httpStatus;
    private final String message;
    private final String error;

    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public String getError() {
        return error;
    }

    public VendorException(String message, Throwable throwable, HttpStatus httpStatus) {
        this.message = message;
        this.error = throwable != null?throwable.getLocalizedMessage() : null;
        this.httpStatus = httpStatus;
    }
}
