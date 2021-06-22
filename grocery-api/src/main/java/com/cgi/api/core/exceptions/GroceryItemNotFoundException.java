package com.cgi.api.core.exceptions;

public class GroceryItemNotFoundException extends RuntimeException {

    public GroceryItemNotFoundException(String message) {
        super(message);
    }

    public GroceryItemNotFoundException(String message, Throwable ex) {
        super(message, ex);
    }
}
