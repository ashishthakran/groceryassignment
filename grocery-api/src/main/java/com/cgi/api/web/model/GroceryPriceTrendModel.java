package com.cgi.api.web.model;

import lombok.Builder;
import lombok.Value;

import java.util.List;

/**
 * @author Aashish Thakran
 * @version 1.0
 */
@Value
@Builder(toBuilder = true)
public class GroceryPriceTrendModel {

    private String name;
    private List<DatePriceModel> priceTrendList;
}
