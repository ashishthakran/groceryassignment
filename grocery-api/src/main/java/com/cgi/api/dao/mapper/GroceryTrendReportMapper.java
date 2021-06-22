package com.cgi.api.dao.mapper;

import com.cgi.api.core.model.DatePrice;
import com.cgi.api.core.model.GroceryTrendReport;
import com.cgi.api.dao.entity.DatePriceDocument;
import com.cgi.api.dao.entity.GroceryTrendReportDocument;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Aashish Thakran
 * @version 1.0
 */
public class GroceryTrendReportMapper {

    public static GroceryTrendReportDocument mapToDocument(GroceryTrendReport groceryTrendReport) {
        return GroceryTrendReportDocument.builder()
                .id(groceryTrendReport.getId())
                .name(groceryTrendReport.getName())
                .priceDate(groceryTrendReport.getPriceDate())
                .maxPrice(groceryTrendReport.getMaxPrice())
                .priceTrendList(mapToDatePriceDocument(groceryTrendReport.getPriceTrendList()))
                .build();
    }

    public static GroceryTrendReport mapToModel(GroceryTrendReportDocument groceryTrendReport) {
        return GroceryTrendReport.builder()
                .id(groceryTrendReport.getId())
                .name(groceryTrendReport.getName())
                .priceDate(groceryTrendReport.getPriceDate())
                .maxPrice(groceryTrendReport.getMaxPrice())
                .priceTrendList(mapToDatePriceModel(groceryTrendReport.getPriceTrendList()))
                .build();
    }

    private static List<DatePriceDocument> mapToDatePriceDocument(List<DatePrice> priceTrendList) {
        if(null == priceTrendList)
            return Collections.emptyList();

        return priceTrendList.stream()
                .map(GroceryTrendReportMapper::buildDatePriceDocument)
                .collect(Collectors.toList());
    }

    private static List<DatePrice> mapToDatePriceModel(List<DatePriceDocument> priceTrendList) {
        if(null == priceTrendList)
            return Collections.emptyList();

        return priceTrendList.stream()
                .map(GroceryTrendReportMapper::buildDatePriceModel)
                .collect(Collectors.toList());
    }

    private static DatePriceDocument buildDatePriceDocument(DatePrice datePrice) {
        return DatePriceDocument.builder()
                .price(datePrice.getPrice())
                .priceDate(datePrice.getPriceDate())
                .build();
    }

    private static DatePrice buildDatePriceModel(DatePriceDocument datePrice) {
        return DatePrice.builder()
                .price(datePrice.getPrice())
                .priceDate(datePrice.getPriceDate())
                .build();
    }
}
