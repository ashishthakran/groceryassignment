package com.cgi.api.web.controller;

import com.cgi.api.core.model.GroceryTrendReport;
import com.cgi.api.service.IGroceryReportService;
import com.cgi.api.service.IGroceryService;
import com.cgi.api.web.annotation.ValidFileType;
import com.cgi.api.web.exceptions.ApiException;
import com.cgi.api.web.mapper.GroceryPriceTrendMapper;
import com.cgi.api.web.model.ApiResponse;
import com.cgi.api.web.model.GroceryModel;
import com.cgi.api.web.model.GroceryPriceTrendModel;
import com.cgi.api.web.model.MaxPriceReportModel;
import com.cgi.api.web.processors.GroceryFileProcessor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Aashish Thakran
 * @version 1.0
 */
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/groceries")
public class GroceriesApiController {

    private static final String ERR_GROCERY_FILE_UPLOAD = "Error in processing file.";
    private static final String MSG_GROCERY_FILE_UPLOAD = "File uploaded successfully.";

    private final GroceryFileProcessor groceryFileProcessor;
    private final IGroceryReportService groceryReportService;
    private final IGroceryService groceryService;

    /**
     * This method handles file upload functionality. It allows only CSV file formats.
     * @param file
     * @return
     */
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ApiResponse> handleFileUpload(@RequestParam("file") @ValidFileType MultipartFile file) {
        try {
            groceryFileProcessor.process(file.getInputStream());
        } catch (IOException ex) {
            log.error("Error in uploading file {} => {}", file.getOriginalFilename(), ex);
            throw new ApiException(ERR_GROCERY_FILE_UPLOAD);
        }

        return ResponseEntity.ok().body(ApiResponse.builder().message(MSG_GROCERY_FILE_UPLOAD).build());
    }

    /**
     * This method gives list of vegetable/fruit sorted by name alphabetically.
     * @return
     */
    @GetMapping(value = "/names", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<List<String>>> getGroceryItemNames() {
        List<String> nameList = groceryService.getDistinctNames();
        return ResponseEntity.ok().body(ApiResponse.<List<String>>builder().data(nameList).build());
    }

    /**
     * This method generates list of vegetable/fruit sorted by name, their maximum price ,date on which price was max.
     * @return
     */
    @GetMapping(value = "/reports/max-price", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<MaxPriceReportModel>> getMaxPriceReport(
            @RequestParam(name = "pageNo", defaultValue = "0") Integer pageNo,
            @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        Long totalCount = groceryReportService.countAll();
        List<GroceryTrendReport> groceryTrendReportList = groceryReportService.getTrendReport(pageNo, pageSize);
        List<GroceryModel> groceryModelList = groceryTrendReportList.stream()
                .map(GroceryPriceTrendMapper::mapToGroceryModel)
                .collect(Collectors.toList());
        MaxPriceReportModel maxPriceReportModel = MaxPriceReportModel.builder()
                .groceries(groceryModelList)
                .totalCount(totalCount)
                .build();
        return ResponseEntity.ok().body(ApiResponse.<MaxPriceReportModel>builder().data(maxPriceReportModel).build());
    }

    /**
     * This method provides price trend for the selected grocery item.
     * @return
     */
    @GetMapping(value = "/reports/price-trend/{itemName:.+}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<GroceryPriceTrendModel>> getPriceTrendReport(@PathVariable String itemName) {
        GroceryTrendReport groceryTrendReport = groceryReportService.getTrendReport(itemName);
        GroceryPriceTrendModel groceryPriceTrendModel = GroceryPriceTrendMapper.mapToPriceTrendReport(groceryTrendReport);
        return ResponseEntity.ok().body(ApiResponse.<GroceryPriceTrendModel>builder().data(groceryPriceTrendModel).build());
    }
}
