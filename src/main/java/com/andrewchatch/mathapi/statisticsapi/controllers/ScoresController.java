package com.andrewchatch.mathapi.statisticsapi.controllers;

import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.andrewchatch.mathapi.statisticsapi.methods.scores.TScore;
import com.andrewchatch.mathapi.statisticsapi.methods.scores.ZScore;

@RestController
@RequestMapping("/scores")
public class ScoresController {

    @PostMapping("/z_score")
    public Map<String,Double> getZScore(@RequestBody ZScore z) {
        ZScore zScore = new ZScore(z);
        Map<String,Double> map = zScore.calculateZScore();
        return map;
    }

    @PostMapping("/t_score")
    public Map<String,Double> getTScore(@RequestBody TScore t) {
        TScore tScore = new TScore(t);
        Map<String,Double> map = tScore.getProbability();
        return map;
    }
}
