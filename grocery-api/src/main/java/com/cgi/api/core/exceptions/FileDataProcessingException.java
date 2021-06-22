package com.cgi.api.core.exceptions;

public class FileDataProcessingException extends RuntimeException {

    public FileDataProcessingException(String message) {
        super(message);
    }

    public FileDataProcessingException(String message, Throwable ex) {
        super(message, ex);
    }
}
