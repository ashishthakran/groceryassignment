package com.cgi.api.dao.repository;

import com.cgi.api.dao.entity.GroceryTrendReportDocument;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.query.Collation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface GroceryTrendReportRepository extends MongoRepository<GroceryTrendReportDocument, UUID> {

    GroceryTrendReportDocument findByName(String name);

    @Query(collation = "en", value = "{}")
    Page<GroceryTrendReportDocument> findAllByPagination(PageRequest request, Collation collation);
}
