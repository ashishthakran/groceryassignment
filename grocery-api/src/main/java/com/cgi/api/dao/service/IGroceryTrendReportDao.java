package com.cgi.api.dao.service;

import com.cgi.api.core.model.GroceryTrendReport;

import java.util.List;

/**
 * @author Aashish Thakran
 * @version 1.0
 */
public interface IGroceryTrendReportDao {

    void saveAll(List<GroceryTrendReport> groceryTrendReportList);

    List<GroceryTrendReport> findAll();

    Long countAll();

    List<GroceryTrendReport> findAll(int pageNo, int pageSize);

    GroceryTrendReport findByName(String name);

    void purgeAll();
}
