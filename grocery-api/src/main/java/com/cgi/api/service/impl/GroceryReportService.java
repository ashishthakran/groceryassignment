package com.cgi.api.service.impl;

import com.cgi.api.core.exceptions.GroceryItemNotFoundException;
import com.cgi.api.core.model.DatePrice;
import com.cgi.api.core.model.Grocery;
import com.cgi.api.core.model.GroceryTrendReport;
import com.cgi.api.dao.service.IGroceryTrendReportDao;
import com.cgi.api.service.IGroceryReportService;
import com.cgi.api.service.IGroceryService;
import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;

@Slf4j
@Service
@Validated
@RequiredArgsConstructor
public class GroceryReportService implements IGroceryReportService {

    private static final String ERR_GROCERY_ITEM_NOT_FOUND = "Grocery item with %s not found";

    private final IGroceryTrendReportDao groceryTrendReportDao;
    private final IGroceryService groceryService;

    @Override
    public List<GroceryTrendReport> generateTrendReport() {
        List<GroceryTrendReport> groceryTrendReportList = Lists.newLinkedList();
        groceryTrendReportDao.purgeAll();

        Predicate<Grocery> nonZeroPricePredicate = grocery -> null != grocery.getPrice() && !grocery.getPrice().equals(BigDecimal.ZERO);

        List<String> nameList = groceryService.getDistinctNames();
        nameList.forEach(name -> {
            Optional<GroceryTrendReport> groceryTrendReport = Optional.ofNullable(getGroceryTrend(name, nonZeroPricePredicate));
            groceryTrendReport.ifPresent(g -> groceryTrendReportList.add(g));
        });

        if(!groceryTrendReportList.isEmpty())
            groceryTrendReportDao.saveAll(groceryTrendReportList);
        return groceryTrendReportList;
    }

    @Override
    public Long countAll() {
        return groceryTrendReportDao.countAll();
    }

    @Override
    public List<GroceryTrendReport> getTrendReport(Integer pageNo, Integer pageSize) {
        return groceryTrendReportDao.findAll(pageNo, pageSize);
    }

    @Override
    public GroceryTrendReport getTrendReport(String itemName) throws GroceryItemNotFoundException {
        return Optional.ofNullable(groceryTrendReportDao.findByName(itemName))
                .orElseThrow(() -> {throw new GroceryItemNotFoundException(String.format(ERR_GROCERY_ITEM_NOT_FOUND, itemName));});
    }

    private GroceryTrendReport getGroceryTrend(final String name, Predicate<Grocery> nonZeroPricePredicate) {
        List<Grocery> groceryList = groceryService.getAllByName(name);
        Optional<Grocery> groceryMaxPrice = groceryList.stream()
                .sorted(comparing(Grocery::getPrice).reversed())
                .findFirst();

        if(!groceryMaxPrice.isPresent())
            return null;

        List<Grocery> groceryPriceTrendList = groceryList.stream()
                .filter(nonZeroPricePredicate)
                .sorted(comparing(Grocery::getPriceDate))
                .collect(Collectors.toList());
        return GroceryTrendReport.builder()
                .name(name)
                .maxPrice(groceryMaxPrice.get().getPrice())
                .priceDate(groceryMaxPrice.get().getPriceDate())
                .priceTrendList(mapToDatePrice(groceryPriceTrendList))
                .build();
    }

    private List<DatePrice> mapToDatePrice(List<Grocery> groceryPriceTrendList) {
        return groceryPriceTrendList.stream()
                .map(this::buildDatePrice)
                .collect(Collectors.toList());
    }

    private DatePrice buildDatePrice(Grocery grocery) {
        return DatePrice.builder()
                .price(grocery.getPrice())
                .priceDate(grocery.getPriceDate())
                .build();
    }
}
