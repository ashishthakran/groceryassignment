package com.cgi.api.web.controller;

import com.cgi.api.core.model.DatePrice;
import com.cgi.api.core.model.Grocery;
import com.cgi.api.core.model.GroceryTrendReport;
import com.cgi.api.service.IGroceryReportService;
import com.cgi.api.service.IGroceryService;
import com.cgi.api.service.impl.GroceryReportService;
import com.cgi.api.service.impl.GroceryService;
import com.cgi.api.web.model.ApiResponse;
import com.cgi.api.web.model.GroceryModel;
import com.cgi.api.web.model.MaxPriceReportModel;
import com.cgi.api.web.processors.GroceryFileProcessor;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Aashish Thakran
 * @version 1.0
 */
public class GroceriesApiControllerTest {

    private final GroceryFileProcessor groceryFileProcessor = mock(GroceryFileProcessor.class);
    private final IGroceryReportService groceryReportService = mock(GroceryReportService.class);
    private final IGroceryService groceryService = mock(GroceryService.class);
    private final GroceriesApiController groceriesApiController = new GroceriesApiController(groceryFileProcessor, groceryReportService, groceryService);

    @Test
    public void testGetMaxPriceReport() throws Exception {
        //GIVEN
        List<GroceryTrendReport> groceryTrendReportList = buildTrendData();
        int pageNo = 0;
        int pageSize = 10;
        long count = 2;

        //WHEN
        when(groceryReportService.getTrendReport(pageNo, pageSize)).thenReturn(groceryTrendReportList);
        when(groceryReportService.countAll()).thenReturn(count);

        ResponseEntity<ApiResponse<MaxPriceReportModel>> result = groceriesApiController.getMaxPriceReport(pageNo, pageSize);
        HttpStatus httpStatus = result.getStatusCode();
        MaxPriceReportModel maxPriceReportModel = result.getBody().getData();
        List<GroceryModel> groceryModelList = maxPriceReportModel.getGroceries();
        Long totalCount = maxPriceReportModel.getTotalCount();

        //THEN
        assertAll(
                () -> assertThat(httpStatus).isNotNull().isEqualTo(HttpStatus.OK),
                () -> assertThat(groceryModelList).hasSize(2),
                () -> assertThat(totalCount).isEqualTo(2),
                () -> assertThat(groceryModelList).element(0)
                        .hasFieldOrPropertyWithValue("name", "Kakadi")
                        .hasFieldOrPropertyWithValue("price", new BigDecimal(34.45))
                        .hasFieldOrPropertyWithValue("priceDate", LocalDate.of(2021, 6, 22)),
                () -> assertThat(groceryModelList).element(1)
                        .hasFieldOrPropertyWithValue("name", "Corriander Leave")
                        .hasFieldOrPropertyWithValue("price", new BigDecimal(123.45))
                        .hasFieldOrPropertyWithValue("priceDate", LocalDate.of(2021, 6, 21))
        );

        verify(groceryReportService, times(1)).getTrendReport(pageNo, pageSize);
    }

    private List<GroceryTrendReport> buildTrendData() {
        return Lists.newArrayList(groceryTrendReport1(), groceryTrendReport2());
    }

    private GroceryTrendReport groceryTrendReport1() {
        DatePrice datePrice1 = buildPriceTrendList(LocalDate.of(2021, 6, 20), new BigDecimal(13.45));
        DatePrice datePrice2 = buildPriceTrendList(LocalDate.of(2021, 6, 21), new BigDecimal(12));
        DatePrice datePrice3 = buildPriceTrendList(LocalDate.of(2021, 6, 22), new BigDecimal(34.45));
        DatePrice datePrice4 = buildPriceTrendList(LocalDate.of(2021, 6, 23), new BigDecimal(28.35));

        return GroceryTrendReport.builder()
                .name("Kakadi")
                .maxPrice(new BigDecimal(34.45))
                .priceDate(LocalDate.of(2021, 6, 22))
                .priceTrendList(Lists.newArrayList(datePrice1, datePrice2, datePrice3, datePrice4))
                .build();
    }

    private GroceryTrendReport groceryTrendReport2() {
        DatePrice datePrice1 = buildPriceTrendList(LocalDate.of(2021, 6, 20), new BigDecimal(16.34));
        DatePrice datePrice2 = buildPriceTrendList(LocalDate.of(2021, 6, 21), new BigDecimal(123.45));
        DatePrice datePrice3 = buildPriceTrendList(LocalDate.of(2021, 6, 22), new BigDecimal(65.34));
        DatePrice datePrice4 = buildPriceTrendList(LocalDate.of(2021, 6, 23), new BigDecimal(13.45));

        return GroceryTrendReport.builder()
                .name("Corriander Leave")
                .maxPrice(new BigDecimal(123.45))
                .priceDate(LocalDate.of(2021, 6, 21))
                .priceTrendList(Lists.newArrayList(datePrice1, datePrice2, datePrice3, datePrice4))
                .build();
    }

    private DatePrice buildPriceTrendList(LocalDate priceDate, BigDecimal price) {
        return DatePrice.builder()
                .price(price)
                .priceDate(priceDate)
                .build();
    }

    private Grocery buildGrocery(String name, LocalDate priceDate, BigDecimal price) {
        return Grocery.builder()
                .name(name)
                .priceDate(priceDate)
                .price(price)
                .build();
    }
}
