package com.cgi.api.service.impl;

import com.cgi.api.core.model.Grocery;
import com.cgi.api.dao.service.IGroceryTrendReportDao;
import com.cgi.api.dao.service.impl.GroceryTrendReportDaoImpl;
import com.cgi.api.service.IGroceryReportService;
import com.cgi.api.service.IGroceryService;
import lombok.val;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class GroceryReportServiceTest {

    private final IGroceryTrendReportDao groceryTrendReportDao = mock(GroceryTrendReportDaoImpl.class);
    private final IGroceryService groceryService = mock(GroceryService.class);
    private final IGroceryReportService groceryReportService = new GroceryReportService(groceryTrendReportDao, groceryService);

    @Test
    public void testGenerateTrendReport() {
        //GIVEN
        val name1 = "Kakadi";
        val name2 = "Corriander Leave";

        val grocery1 = buildGrocery("Kakadi", LocalDate.of(2021, 6, 20), new BigDecimal(13.45));
        val grocery2 = buildGrocery("Corriander Leave", LocalDate.of(2021, 6, 20), new BigDecimal(16.34));
        val grocery3 = buildGrocery("Kakadi", LocalDate.of(2021, 6, 21), new BigDecimal(12));
        val grocery4 = buildGrocery("Corriander Leave", LocalDate.of(2021, 6, 21), new BigDecimal(123.45));
        val grocery5 = buildGrocery("Kakadi", LocalDate.of(2021, 6, 22), new BigDecimal(34.45));
        val grocery6 = buildGrocery("Corriander Leave", LocalDate.of(2021, 6, 22), new BigDecimal(65.34));
        val grocery7 = buildGrocery("Kakadi", LocalDate.of(2021, 6, 23), new BigDecimal(28.35));
        val grocery8 = buildGrocery("Corriander Leave", LocalDate.of(2021, 6, 23), new BigDecimal(13.45));

        val kakadiGroceryList = Arrays.asList(grocery1, grocery3, grocery5, grocery7);
        val corrianderGroceryList = Arrays.asList(grocery2, grocery4, grocery6, grocery8);

        val givenNameList = Arrays.asList(name1, name2);


        //WHEN
        when(groceryService.getDistinctNames()).thenReturn(givenNameList);
        when(groceryService.getAllByName(name1)).thenReturn(kakadiGroceryList);
        when(groceryService.getAllByName(name2)).thenReturn(corrianderGroceryList);

        val result = groceryReportService.generateTrendReport();

        //THEN
        assertAll(
                () -> assertThat(result).isNotEmpty().hasSize(2),
                () -> assertThat(result).isNotNull().isNotEmpty()
                        .element(0)
                        .hasFieldOrPropertyWithValue("name", name1)
                        .hasFieldOrPropertyWithValue("maxPrice", new BigDecimal(34.45))
                        .hasFieldOrPropertyWithValue("priceDate", LocalDate.of(2021, 6, 22)),
                () -> assertThat(result).isNotNull().isNotEmpty()
                        .element(1)
                        .hasFieldOrPropertyWithValue("name", name2)
                        .hasFieldOrPropertyWithValue("maxPrice", new BigDecimal(123.45))
                        .hasFieldOrPropertyWithValue("priceDate", LocalDate.of(2021, 6, 21))
        );

        verify(groceryService, times(1)).getDistinctNames();
        verify(groceryService, times(2)).getAllByName(any());
    }

    private Grocery buildGrocery(String name, LocalDate priceDate, BigDecimal price) {
        return Grocery.builder()
                .name(name)
                .priceDate(priceDate)
                .price(price)
                .build();
    }
}
