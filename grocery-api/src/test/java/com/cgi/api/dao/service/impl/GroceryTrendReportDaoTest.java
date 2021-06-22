package com.cgi.api.dao.service.impl;

import com.cgi.api.core.model.DatePrice;
import com.cgi.api.core.model.GroceryTrendReport;
import com.cgi.api.dao.entity.DatePriceDocument;
import com.cgi.api.dao.entity.GroceryTrendReportDocument;
import com.cgi.api.dao.mapper.GroceryTrendReportMapper;
import com.cgi.api.dao.repository.GroceryTrendReportRepository;
import com.cgi.api.dao.service.IGroceryTrendReportDao;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Aashish Thakran
 * @version 1.0
 */
class GroceryTrendReportDaoTest {

    private final GroceryTrendReportRepository groceryTrendReportRepository = mock(GroceryTrendReportRepository.class);
    private final IGroceryTrendReportDao groceryTrendReportDao = new GroceryTrendReportDaoImpl(groceryTrendReportRepository);

    @Test
    public void testSaveAll() {
        //GIVEN
        val id = UUID.randomUUID();
        val givenGroceryTrendReportList = Lists.newArrayList(buildGroceryTrendReport(null));
        val givenGroceryTrendReportDocumentList = Lists.newArrayList(buildGroceryTrendReportDocument(null));
        val savedGroceryTrendReportDocumentList = Lists.newArrayList(buildGroceryTrendReportDocument(id));

        //WHEN
        when(groceryTrendReportRepository.saveAll(givenGroceryTrendReportDocumentList)).thenReturn(savedGroceryTrendReportDocumentList);

        groceryTrendReportDao.saveAll(givenGroceryTrendReportList);

        //THEN
        verify(groceryTrendReportRepository, times(1)).saveAll(givenGroceryTrendReportDocumentList);
    }

    @Test
    public void testFindAll() {
        //GIVEN
        val id = UUID.randomUUID();
        val savedGroceryTrendReportDocumentList = Lists.newArrayList(buildGroceryTrendReportDocument(id));

        //WHEN
        when(groceryTrendReportRepository.findAll()).thenReturn(savedGroceryTrendReportDocumentList);

        val result = groceryTrendReportDao.findAll();

        //THEN
        assertThat(result).isNotNull().element(0)
                .hasFieldOrPropertyWithValue("name", "Pomegranate")
                .hasFieldOrPropertyWithValue("maxPrice", new BigDecimal(11))
                .hasFieldOrPropertyWithValue("priceDate", LocalDate.of(2021, 06, 19))
                .hasFieldOrPropertyWithValue("id", id);

        verify(groceryTrendReportRepository, times(1)).findAll();
    }

    private GroceryTrendReport buildGroceryTrendReport(UUID id) {
        return GroceryTrendReport.builder()
                .id(id)
                .name("Pomegranate")
                .maxPrice(new BigDecimal(11))
                .priceDate(LocalDate.of(2021, 06, 19))
                .priceTrendList(singletonList(buildDatePrice()))
                .build();
    }

    private DatePrice buildDatePrice() {
        return DatePrice.builder()
                .price(new BigDecimal(11))
                .priceDate(LocalDate.of(2021, 06, 19))
                .build();
    }

    private GroceryTrendReportDocument buildGroceryTrendReportDocument(UUID id) {
        return GroceryTrendReportDocument.builder()
                .id(id)
                .name("Pomegranate")
                .maxPrice(new BigDecimal(11))
                .priceDate(LocalDate.of(2021, 06, 19))
                .priceTrendList(singletonList(buildDatePriceDocument()))
                .build();
    }

    private DatePriceDocument buildDatePriceDocument() {
        return DatePriceDocument.builder()
                .price(new BigDecimal(11))
                .priceDate(LocalDate.of(2021, 06, 19))
                .build();
    }
}
