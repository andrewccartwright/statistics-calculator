package com.andrewchatch.mathapi.statisticsapi.controllers;

import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.andrewchatch.mathapi.statisticsapi.methods.summary_statistics.SummaryStatistics;

@RestController
@RequestMapping("/summary_statistics")
public class SummaryStatisticsController {
    @PostMapping("")
    public Map<String, Double> getSummaryStatistics(@RequestBody SummaryStatistics stats) {
        SummaryStatistics statistics = new SummaryStatistics(stats);
        Map<String,Double> map = statistics.getSummaryStatistics();
        return map;
    }
}
