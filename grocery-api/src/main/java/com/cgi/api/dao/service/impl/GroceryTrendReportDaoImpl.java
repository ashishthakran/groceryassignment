package com.cgi.api.dao.service.impl;

import com.cgi.api.core.model.GroceryTrendReport;
import com.cgi.api.dao.entity.GroceryTrendReportDocument;
import com.cgi.api.dao.mapper.GroceryTrendReportMapper;
import com.cgi.api.dao.repository.GroceryTrendReportRepository;
import com.cgi.api.dao.service.IGroceryTrendReportDao;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Collation;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Collections;
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
public class GroceryTrendReportDaoImpl implements IGroceryTrendReportDao {

    private final GroceryTrendReportRepository groceryTrendReportRepository;

    @Override
    public void saveAll(List<GroceryTrendReport> groceryTrendReportList) {
        List<GroceryTrendReportDocument> groceryTrendReportDocumentList = groceryTrendReportList.stream()
                .map(GroceryTrendReportMapper::mapToDocument)
                .collect(Collectors.toList());
        groceryTrendReportRepository.saveAll(groceryTrendReportDocumentList);
    }

    @Override
    public List<GroceryTrendReport> findAll() {
        Optional<List<GroceryTrendReportDocument>> groceryTrendReportDocumentList = Optional.ofNullable(groceryTrendReportRepository.findAll());
        return !groceryTrendReportDocumentList.isPresent() ? Collections.emptyList() :
                groceryTrendReportDocumentList.get().stream()
                        .map(GroceryTrendReportMapper::mapToModel)
                .collect(Collectors.toList());
    }

    @Override
    public Long countAll() {
        return groceryTrendReportRepository.count();
    }

    @Override
    public List<GroceryTrendReport> findAll(int pageNo, int pageSize) {
        PageRequest request = PageRequest.of(pageNo, pageSize, Sort.by(Sort.Direction.ASC, "name"));
        Collation collation = Collation.of("en").strength(Collation.ComparisonLevel.secondary());
        Page<GroceryTrendReportDocument> groceryTrendReportDocumentPage = groceryTrendReportRepository.findAllByPagination(request, collation);
        return groceryTrendReportDocumentPage.stream()
                .map(GroceryTrendReportMapper::mapToModel)
                .collect(Collectors.toList());
    }

    @Override
    public GroceryTrendReport findByName(String name) {
        Optional<GroceryTrendReportDocument> groceryTrendReportDocument = Optional.ofNullable(groceryTrendReportRepository.findByName(name));
        return groceryTrendReportDocument.map(GroceryTrendReportMapper::mapToModel).orElse(null);
    }

    @Override
    public void purgeAll() {
        groceryTrendReportRepository.deleteAll();
    }
}
