package com.cgi.api.service;

import com.cgi.api.core.model.Grocery;

import javax.validation.Valid;
import java.math.BigInteger;
import java.util.List;
import java.util.UUID;

public interface IGroceryService {

    void saveAll(@Valid List<Grocery> groceryList);

    List<String> getDistinctNames();

    List<Grocery> getAllByName(String name);
}
