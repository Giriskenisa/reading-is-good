package com.getir.readingisgood.controller;

import com.getir.readingisgood.dto.Statistic;
import com.getir.readingisgood.service.StatisticsService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/statistics")
public class StatisticsController {

    @Autowired
    private StatisticsService service;


    @GetMapping
    @SneakyThrows
    public ResponseEntity<?> getCustomerStatistics(@RequestParam("customerId") final String customerId,
                                                   @RequestParam("year") final String year) {

        Map<String, Statistic> report = service.getStatistic(customerId, year);

        if(report.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(report);
    }

}
