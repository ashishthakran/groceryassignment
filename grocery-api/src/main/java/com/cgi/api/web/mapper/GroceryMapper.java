package com.cgi.api.web.mapper;

import com.cgi.api.core.model.Grocery;
import com.cgi.api.web.model.GroceryModel;

/**
 * @author Aashish Thakran
 * @version 1.0
 */
public class GroceryMapper {

    public static Grocery mapToDto(GroceryModel grocery) {
        return Grocery.builder()
                .priceDate(grocery.getPriceDate())
                .price(grocery.getPrice())
                .name(grocery.getName())
                .build();
    }

    public static GroceryModel mapToModel(Grocery grocery) {
        return GroceryModel.builder()
                .priceDate(grocery.getPriceDate())
                .price(grocery.getPrice())
                .name(grocery.getName())
                .build();
    }
}
