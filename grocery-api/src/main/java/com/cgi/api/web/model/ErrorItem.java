package com.cgi.api.web.model;

import lombok.Value;
import lombok.experimental.SuperBuilder;

/**
 * @author Aashish Thakran
 * @version 1.0
 */
@Value
@SuperBuilder
public class ErrorItem {

    private String name;
    private String message;
}
