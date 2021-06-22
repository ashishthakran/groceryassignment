package com.cgi.api.dao.service;

import com.cgi.api.core.model.Grocery;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * @author Aashish Thakran
 * @version 1.0
 */
public interface IGroceryDao {

    void saveAll(List<Grocery> groceryList);

    List<String> getDistinctNames();

    List<Grocery> findAllByName(String name);

    Optional<Grocery> findByNameAndDate(final String name, final LocalDate priceDate);
}
