package com.cgi.api.web.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Locale;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;

@Getter
@RequiredArgsConstructor
public enum GroceryFileHeader {

    PRICE_DATE      (   "datesk",       null,           "yyyyMMdd"),
    ITEM_NAME       (   "Item Name",    null,           null),
    DATE            (   "Date",         null,            null),
    PRICE           (   "price",               Locale.GERMANY,   null);

    private final String headerName;
    private final Locale locale;
    private final String dateFormat;

    public static String[] getHeaderNames() {
        return asList(values()).stream()
                .map(String::valueOf)
                .collect(Collectors.toList()).toArray(new String[values().length]);
    }
}
