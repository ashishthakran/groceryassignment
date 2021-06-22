package com.cgi.api.core.model;

import lombok.Builder;
import lombok.Value;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author Aashish Thakran
 * @version 1.0
 */
@Value
@Builder(toBuilder = true)
public class DatePrice implements Serializable {
    private BigDecimal price;
    private LocalDate priceDate;
}
