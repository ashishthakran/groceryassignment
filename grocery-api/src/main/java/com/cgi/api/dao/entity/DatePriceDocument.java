package com.cgi.api.dao.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author Aashish Thakran
 * @version 1.0
 */
@Data
@Builder(toBuilder = true)
public class DatePriceDocument {

    @Field(name = "price")
    private BigDecimal price;

    @Field(name = "price_date")
    private LocalDate priceDate;
}
