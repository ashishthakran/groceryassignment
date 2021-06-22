package com.cgi.api.web.exceptions;

public class ApiException extends RuntimeException {

    public ApiException(String message) {
        super(message);
    }

    public ApiException(String message, Throwable ex) {
        super(message, ex);
    }
}
