package com.cgi.api.web.processors;

import com.cgi.api.core.exceptions.FileDataProcessingException;
import com.cgi.api.core.model.Grocery;
import com.cgi.api.service.IGroceryService;
import com.cgi.api.web.mapper.GroceryMapper;
import com.cgi.api.web.model.GroceryModel;
import com.cgi.api.web.readers.GroceryCSVFileReader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class GroceryFileProcessor {

    private final IGroceryService groceryService;

    public void process(InputStream inputStream) throws FileDataProcessingException {
        List<GroceryModel> groceryModelList = GroceryCSVFileReader.read(inputStream);
        List<Grocery> groceryList = groceryModelList.stream()
                .map(GroceryMapper::mapToDto)
                .collect(Collectors.toList());
        groceryService.saveAll(groceryList);
    }
}
