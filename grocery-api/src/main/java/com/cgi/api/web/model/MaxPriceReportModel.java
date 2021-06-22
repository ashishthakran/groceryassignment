package com.cgi.api.web.model;

import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * @author Aashish Thakran
 * @version 1.0
 */
@Value
@Builder(toBuilder = true)
public class MaxPriceReportModel {

    private Long totalCount;
    private List<GroceryModel> groceries;
}
