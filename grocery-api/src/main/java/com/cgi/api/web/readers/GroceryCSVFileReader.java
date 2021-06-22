package com.cgi.api.web.readers;

import com.cgi.api.core.exceptions.FileDataProcessingException;
import com.cgi.api.web.enums.FileType;
import com.cgi.api.web.enums.GroceryFileHeader;
import com.cgi.api.web.model.GroceryModel;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.validator.GenericValidator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.StreamSupport;

import static com.cgi.api.web.enums.GroceryFileHeader.PRICE_DATE;

@Slf4j
public class GroceryCSVFileReader {

    private static final String ERR_FILE_PROCESSING = "Error parsing CSV file";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(PRICE_DATE.getDateFormat());

    public static List<GroceryModel> read(InputStream inputStream) {
        List<GroceryModel> groceryModelList = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
            int index = 0;
            bufferedReader.lines().skip(1).forEachOrdered(line -> {
                Iterable<CSVRecord> records = null;
                try {
                    records = CSVFormat
                            .newFormat(FileType.CSV.getColumnSeparator())
                            .withHeader(GroceryFileHeader.getHeaderNames())
                            .parse(new StringReader(line));
                    Optional<GroceryModel> groceryModel = StreamSupport.stream(records.spliterator(), false)
                            .map(GroceryCSVFileReader::mapToModel)
                            .filter(Objects::nonNull)
                            .findAny();
                    groceryModel.ifPresent(g -> groceryModelList.add(g));
                } catch (IOException ex) {
                    log.error("Error parsing CSV file => {}", ex.getMessage());
                    throw new FileDataProcessingException(ERR_FILE_PROCESSING);
                }
            });
        } catch (IOException ex) {
            log.error("Error parsing CSV file => {}", ex.getMessage());
            throw new FileDataProcessingException(ERR_FILE_PROCESSING);
        }
        return groceryModelList;
    }

    private static GroceryModel mapToModel(CSVRecord csvRecord) {
        boolean isValidPriceDate = GenericValidator.isDate(csvRecord.get(0), PRICE_DATE.getDateFormat(), true);
        String name = StringUtils.stripToEmpty(csvRecord.get(1));
        BigDecimal price = NumberUtils.isParsable(csvRecord.get(3)) ? NumberUtils.createBigDecimal(csvRecord.get(3)) : BigDecimal.ZERO;
        if(!isValidPriceDate || name.isEmpty())
            return null;

        return GroceryModel.builder()
                .priceDate(LocalDate.parse(csvRecord.get(0), FORMATTER))
                .name(name)
                .price(price)
                .build();
    }
}
