package com.cgi.api.dao.service.impl;

import com.cgi.api.core.model.Grocery;
import com.cgi.api.dao.entity.GroceryDocument;
import com.cgi.api.dao.mapper.GroceryMapper;
import com.cgi.api.dao.repository.GroceryRepository;
import com.cgi.api.dao.service.IGroceryDao;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Aashish Thakran
 * @version 1.0
 */
@Service
@Validated
@RequiredArgsConstructor
public class GroceryDaoImpl implements IGroceryDao {

    private final GroceryRepository groceryRepository;
    private final MongoTemplate mongoTemplate;

    @Override
    public void saveAll(List<Grocery> groceryList) {
        List<GroceryDocument> groceryEntityList = groceryList.stream()
                .map(GroceryMapper::mapToDocument)
                .collect(Collectors.toList());
        groceryRepository.saveAll(groceryEntityList);
    }

    @Override
    public List<String> getDistinctNames() {
        List<String> nameList = mongoTemplate.query(GroceryDocument.class)
                .distinct("name")
                .as(String.class)
                .all();
        return nameList.stream()
                .sorted(Comparator.comparing(String::valueOf, String.CASE_INSENSITIVE_ORDER))
                .collect(Collectors.toList());
    }

    @Override
    public List<Grocery> findAllByName(String name) {
        Optional<List<GroceryDocument>> groceryEntityList = Optional.ofNullable(groceryRepository.findAllByName(name));
        return !groceryEntityList.isPresent() ? Collections.emptyList() :
                groceryEntityList.get().stream()
                        .map(GroceryMapper::mapToModel)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Grocery> findByNameAndDate(String name, LocalDate priceDate) {
        Optional<GroceryDocument> groceryEntity = Optional.ofNullable(groceryRepository.findByNameAndPriceDate(name, priceDate));
        return groceryEntity
                .map(GroceryMapper::mapToModel);
    }
}
