package com.andrewchatch.mathapi.statisticsapi.controllers;

import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.andrewchatch.mathapi.statisticsapi.methods.distributions.Binomial;
import com.andrewchatch.mathapi.statisticsapi.methods.distributions.Exponential;
import com.andrewchatch.mathapi.statisticsapi.methods.distributions.Geometric;
import com.andrewchatch.mathapi.statisticsapi.methods.distributions.Normal;
import com.andrewchatch.mathapi.statisticsapi.methods.distributions.Poisson;

@RestController
@RequestMapping("/distributions")
public class DistributionsController {
    /**
     * Submit request in JSON format as follows:
     * {
     *  "n": int,
     *  "x": int,
     *  "p": double
     * }
     * @param binomial
     * @return
     */
    @PostMapping("/binomial")
    public Map<String,Double> getBinomialProbability(@RequestBody Binomial binom) {
        Binomial binomial = new Binomial(binom);
        Map<String,Double> probsMap = binomial.getProbability(binomial.getX(), binomial.getN(), binomial.getP());
        return probsMap;
    }

    /**
     * Submit request in JSON format as follows:
     * {
     *  "x": double,
     *  "mean": double,
     *  "stdev": double
     * }
     * @param norm
     * @return
     */
    @PostMapping("/normal")
    public Map<String,Double> getNormalProbability(@RequestBody Normal norm) {
        Normal normal = new Normal(norm);
        Map<String,Double> probsMap = normal.getProbability(normal.getX(), normal.getMean(), normal.getStDev());
        return probsMap;
    }

    /**
     * Submit request in JSON format as follows:
     * {
     *  "x": int,
     *  "lambda": double
     * }
     * @param p
     * @return
     */
    @PostMapping("/poisson")
    public Map<String,Double> getPoissonProbability(@RequestBody Poisson p) {
        Poisson poisson = new Poisson(p);
        Map<String,Double> probsMap = poisson.getProbability(poisson.getX(), poisson.getLambda());
        return probsMap;
    }

    /**
     * Submit request in JSON format as follows:
     * {
     *  "x": double,
     *  "lambda": double
     * }
     * @param exp
     * @return
     */
    @PostMapping("/exponential")
    public Map<String,Double> getExponentialProbability(@RequestBody Exponential exp) {
        Exponential exponential = new Exponential(exp);
        Map<String,Double> probsMap = exponential.getProbability(exponential.getX(), exponential.getLambda());
        return probsMap;
    }

    /**
     * Submit request in JSON format as follows:
     * {
     *  "n": int,
     *  "p": double,
     *  "includesSuccess": boolean
     * }
     * @param geo
     * @return
     */
    @PostMapping("/geometric")
    public Map<String,Double> getGeometricProbability(@RequestBody Geometric geo) {
        Geometric geometric = new Geometric(geo);
        Map<String,Double> probsMap = geometric.getProbability(geometric.getX(), geometric.getP(), geometric.getIncludesSuccess());
        return probsMap;
    }
}
