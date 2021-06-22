package com.cgi.api.service;

import com.cgi.api.core.exceptions.GroceryItemNotFoundException;
import com.cgi.api.core.model.GroceryTrendReport;

import java.util.List;

public interface IGroceryReportService {

    List<GroceryTrendReport> generateTrendReport();

    Long countAll();

    List<GroceryTrendReport> getTrendReport(Integer pageNo, Integer pageSize);

    GroceryTrendReport getTrendReport(String itemName) throws GroceryItemNotFoundException;
}
