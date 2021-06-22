package com.cgi.api.dao.service.impl;

import com.cgi.api.core.model.Grocery;
import com.cgi.api.dao.entity.GroceryDocument;
import com.cgi.api.dao.repository.GroceryRepository;
import com.cgi.api.dao.service.IGroceryDao;
import lombok.val;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Aashish Thakran
 * @version 1.0
 */
class GroceryDaoTest {

    private final GroceryRepository groceryRepository = mock(GroceryRepository.class);
    private final MongoTemplate mongoTemplate = mock(MongoTemplate.class);

    private final IGroceryDao groceryDao = new GroceryDaoImpl(groceryRepository, mongoTemplate);

    @Test
    public void testSaveAll() {
        //GIVEN
        UUID id = UUID.randomUUID();
        List<Grocery> givenGroceryList = Lists.newArrayList(buildGrocery(null));
        List<GroceryDocument> givenGroceryDocumentList = Lists.newArrayList(buildGroceryDocument(null));
        List<GroceryDocument> savedGroceryDocumentList = Lists.newArrayList(buildGroceryDocument(id));

        //WHEN
        when(groceryRepository.saveAll(givenGroceryDocumentList)).thenReturn(savedGroceryDocumentList);

        groceryDao.saveAll(givenGroceryList);

        //THEN
        verify(groceryRepository, times(1)).saveAll(givenGroceryDocumentList);
    }

    @Test
    public void testFindAllByName() {
        //GIVEN
        val name = "Pomegranate";
        UUID id = UUID.randomUUID();
        List<GroceryDocument> savedGroceryDocumentList = Lists.newArrayList(buildGroceryDocument(id));

        //WHEN
        when(groceryRepository.findAllByName(name)).thenReturn(savedGroceryDocumentList);

        val result = groceryDao.findAllByName(name);

        //THEN
        assertThat(result).isNotNull().element(0)
                .hasFieldOrPropertyWithValue("name", name)
                .hasFieldOrPropertyWithValue("id", id);
    }

    private Grocery buildGrocery(UUID id) {
        return Grocery.builder()
                .id(id)
                .name("Pomegranate")
                .price(new BigDecimal(11))
                .priceDate(LocalDate.of(2021, 06, 19))
                .build();

    }

    private GroceryDocument buildGroceryDocument(UUID id) {
        return GroceryDocument.builder()
                .id(id)
                .name("Pomegranate")
                .price(new BigDecimal(11))
                .priceDate(LocalDate.of(2021, 06, 19))
                .build();

    }
}
