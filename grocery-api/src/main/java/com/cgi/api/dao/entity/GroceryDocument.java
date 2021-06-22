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

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @version 1.0
 * @author Aashish Thakran
 */
@Getter
@Setter
@Document(collection = "grocery")
@ToString
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@SuperBuilder(toBuilder = true)
public class GroceryDocument extends AbstractBaseDocument {

    @NotNull
    @Indexed
    @EqualsAndHashCode.Include
    @Field(name = "name")
    private String name;

    @NotNull
    @EqualsAndHashCode.Include
    @Field(name = "price")
    private BigDecimal price;

    @NotNull
    @Field(name = "price_date")
    private LocalDate priceDate;
}
