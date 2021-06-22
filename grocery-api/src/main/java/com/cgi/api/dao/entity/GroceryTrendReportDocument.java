package com.cgi.api.dao.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * @version 1.0
 * @author Aashish Thakran
 */
@Getter
@Setter
@Document(collection = "grocery_trend_report")
@ToString
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@SuperBuilder(toBuilder = true)
public class GroceryTrendReportDocument extends AbstractBaseDocument {

    @Indexed
    @EqualsAndHashCode.Include
    @Field(name = "name")
    private String name;

    @Field(name = "max_price")
    private BigDecimal maxPrice;

    @Field(name = "price_date")
    private LocalDate priceDate;

    @Field(name = "price_trends")
    private List<DatePriceDocument> priceTrendList;
}
