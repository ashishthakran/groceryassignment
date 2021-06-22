package com.cgi.api.web.model;

import lombok.Value;
import lombok.experimental.SuperBuilder;

import java.util.List;

/**
 * @author Aashish Thakran
 * @version 1.0
 */
@Value
@SuperBuilder
public class ApiResponse<T> {

    T data;
    boolean status;
    String message;
    List<ErrorItem> errors;
}
