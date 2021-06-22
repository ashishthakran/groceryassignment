package com.cgi.api.core.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * @version 1.0
 * @author Aashish Thakran
 */
@Getter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@SuperBuilder(toBuilder = true)
public class GroceryTrendReport extends AbstractBaseDto {
    private String name;
    private BigDecimal maxPrice;
    private LocalDate priceDate;
    private List<DatePrice> priceTrendList;
}
