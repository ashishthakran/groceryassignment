package com.cgi.api.dao.mapper;

import com.cgi.api.core.model.Grocery;
import com.cgi.api.dao.entity.GroceryDocument;

/**
 * @author Aashish Thakran
 * @version 1.0
 */
public class GroceryMapper {

    public static GroceryDocument mapToDocument(Grocery grocery) {
        return GroceryDocument.builder()
                .id(grocery.getId())
                .priceDate(grocery.getPriceDate())
                .price(grocery.getPrice())
                .name(grocery.getName())
                .build();
    }

    public static Grocery mapToModel(GroceryDocument grocery) {
        return Grocery.builder()
                .id(grocery.getId())
                .priceDate(grocery.getPriceDate())
                .price(grocery.getPrice())
                .name(grocery.getName())
                .build();
    }
}
