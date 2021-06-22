package com.cgi.api.core.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author Aashish Thakran
 * @version 1.0
 */
@Getter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@SuperBuilder(toBuilder = true)
public class Grocery extends AbstractBaseDto {

    @EqualsAndHashCode.Include
    @NotBlank(message = "Grocery name can't be blank.")
    @Size(min = 1, max = 200, message = "Grocery name can't be more than 200 characters")
    private String name;

    @EqualsAndHashCode.Include
    @NotNull(message = "Grocery price can't be blank.")
    private BigDecimal price;

    @NotNull(message = "Price date can't be blank.")
    private LocalDate priceDate;
}
