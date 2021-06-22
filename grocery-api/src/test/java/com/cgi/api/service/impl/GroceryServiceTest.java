package com.cgi.api.service.impl;

import com.cgi.api.core.model.Grocery;
import com.cgi.api.dao.service.IGroceryDao;
import com.cgi.api.dao.service.impl.GroceryDaoImpl;
import com.cgi.api.service.IGroceryService;
import lombok.val;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
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
public class GroceryServiceTest {

    private final IGroceryDao groceryDao = mock(GroceryDaoImpl.class);
    private final IGroceryService groceryService = new GroceryService(groceryDao);

    @Test
    public void testSaveAll() {
        //GIVEN
        val id = UUID.randomUUID();
        val givenGroceryList = Lists.newArrayList(buildGrocery(null));

        groceryService.saveAll(givenGroceryList);
        //THEN
        verify(groceryDao, times(1)).saveAll(givenGroceryList);
    }

    @Test
    public void testGetDistinctNames() {
        //GIVEN
        val givenNames = Lists.newArrayList("Pomegranate");

        //WHEN
        when(groceryDao.getDistinctNames()).thenReturn(givenNames);

        val result = groceryService.getDistinctNames();

        //THEN
        assertThat(result).isNotNull().isNotEmpty()
                .hasSize(1)
                .element(0)
                .isEqualTo("Pomegranate");
        verify(groceryDao, times(1)).getDistinctNames();
    }

    @Test
    public void testGetAllByName() {
        //GIVEN
        val name = "Pomegranate";
        val id = UUID.randomUUID();
        val groceryList = Lists.newArrayList(buildGrocery(id));

        //WHEN
        when(groceryDao.findAllByName(name)).thenReturn(groceryList);

        val result = groceryService.getAllByName(name);

        //THEN
        assertThat(result).isNotNull().isNotEmpty()
                .hasSize(1)
                .element(0)
                .hasFieldOrPropertyWithValue("name", name)
                .hasFieldOrPropertyWithValue("id", id);
        verify(groceryDao, times(1)).findAllByName(name);

    }

    private Grocery buildGrocery(UUID id) {
        return Grocery.builder()
                .id(id)
                .name("Pomegranate")
                .price(new BigDecimal(11))
                .priceDate(LocalDate.of(2021, 06, 19))
                .build();

    }
}
