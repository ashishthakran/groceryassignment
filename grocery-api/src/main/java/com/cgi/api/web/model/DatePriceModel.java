package com.cgi.api.web.model;

import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author Aashish Thakran
 * @version 1.0
 */
@Value
@Builder(toBuilder = true)
public class DatePriceModel {

    private BigDecimal price;
    private LocalDate priceDate;
}
