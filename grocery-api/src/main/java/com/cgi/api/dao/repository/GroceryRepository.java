package com.cgi.api.dao.repository;

import com.cgi.api.dao.entity.GroceryDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface GroceryRepository extends MongoRepository<GroceryDocument, UUID> {

    List<GroceryDocument> findAllByName(final String name);

    GroceryDocument findByNameAndPriceDate(final String name, final LocalDate priceDate);
}
