package com.cgi.api.web.mapper;

import com.cgi.api.core.model.DatePrice;
import com.cgi.api.core.model.GroceryTrendReport;
import com.cgi.api.web.model.DatePriceModel;
import com.cgi.api.web.model.GroceryModel;
import com.cgi.api.web.model.GroceryPriceTrendModel;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Aashish Thakran
 * @version 1.0
 */
public class GroceryPriceTrendMapper {

    public static GroceryModel mapToGroceryModel(GroceryTrendReport groceryTrendReport) {
        return GroceryModel.builder()
                .name(groceryTrendReport.getName())
                .price(groceryTrendReport.getMaxPrice())
                .priceDate(groceryTrendReport.getPriceDate())
                .build();
    }

    public static GroceryPriceTrendModel mapToPriceTrendReport(GroceryTrendReport groceryTrendReport) {
        return GroceryPriceTrendModel.builder()
                .name(groceryTrendReport.getName())
                .priceTrendList(mapToDatePriceModel(groceryTrendReport.getPriceTrendList()))
                .build();
    }

    private static List<DatePriceModel> mapToDatePriceModel(List<DatePrice> datePriceList) {
        return datePriceList.stream()
                .map(GroceryPriceTrendMapper::buildDatePriceModel)
                .collect(Collectors.toList());
    }

    private static DatePriceModel buildDatePriceModel(DatePrice datePrice) {
        return DatePriceModel.builder()
                .priceDate(datePrice.getPriceDate())
                .price(datePrice.getPrice())
                .build();
    }
}
