package com.cgi.api.scheduler;

import com.cgi.api.service.IGroceryReportService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class GroceryScheduler {

    private final IGroceryReportService groceryReportService;

    @Scheduled(fixedDelay = 60000, initialDelay = 30000)
    public void generateMaxPriceReport() {
        try {
            groceryReportService.generateTrendReport();
        } catch(Exception ex) {
            log.error("Error in generating trend report.");
        }
    }
}
