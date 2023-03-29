package com.andrewchatch.mathapi.statisticsapi.methods.summary_statistics;

import java.util.HashMap;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SummaryStatistics {
    private double[] numbers;

    @JsonCreator
    public SummaryStatistics(@JsonProperty("numbers") double[] numbers) {
        this.numbers = numbers;
    }

    public SummaryStatistics(SummaryStatistics s) {
        this.numbers = s.numbers;
    }

    public void setNumbers(double[] numbers) {
        this.numbers = numbers;
    }

    public double[] getNumbers() {
        return this.numbers;
    }

    private double calculateMean() {
        double sum = getSum();

        return sum / this.numbers.length;
    }

    private double findMin() {
        double min = this.numbers[0];
        for (int i = 0; i < this.numbers.length; i++) {
            if (this.numbers[i] < min) {
                min = this.numbers[i];
            }
        }

        return min;
    }

    private double findMax() {
        double max = this.numbers[0];
        for (int i = 0; i < this.numbers.length; i++) {
            if (this.numbers[i] > max) {
                max = this.numbers[i];
            }
        }

        return max;
    }

    private double getSum() {
        double sum = 0;
        for (int i = 0; i < this.numbers.length; i++) {
            sum += this.numbers[i];
        }

        return sum;
    }

    private double calculateMedian() {
        if (this.numbers.length % 2 == 0) {
            int index1 = (this.numbers.length / 2) - 1;
            int index2 = ((this.numbers.length + 1) / 2);

            double median = (double) (this.numbers[index1] + this.numbers[index2]) / 2.0;
            return median;
        }
        else {
            int index = ((this.numbers.length + 1) / 2) - 1;
    
            return this.numbers[index];
        }
        
    }

    private double calculateMode() {
        int maxCount = 0;
        double element = 0;
        for (int i = 0; i < this.numbers.length; i++) {
            int count = 0;
            for (int j = 0; j < this.numbers.length; j++) {
                if (this.numbers[i] == this.numbers[j]) {
                    count++;
                }
            }

            if (count > maxCount) {
                maxCount = count;
                element = this.numbers[i];
            }
        }

        return element;
    }

    private double calculateVariance() {
        double mean = calculateMean();
        double[] xSq = new double[this.numbers.length];

        for (int i = 0; i < this.numbers.length; i++) {
            xSq[i] = Math.pow(this.numbers[i] - mean, 2);
        }

        double sum = 0;
        for (int j = 0; j < xSq.length; j++) {
            sum += xSq[j];
        }

        return sum / (this.numbers.length - 1);
    }

    public HashMap<String,Double> getSummaryStatistics() {
        double min = findMin();
        double max = findMax();
        double range = max - min;
        double count = this.numbers.length;
        double sum = getSum();
        double mean = calculateMean();
        double median = calculateMedian();
        double mode = calculateMode();
        double variance = calculateVariance();
        double stDev = Math.sqrt(variance);

        HashMap<String,Double> map = new HashMap<String,Double>();
        map.put("Minimum", min);
        map.put("Maximum", max);
        map.put("Range", range);
        map.put("Count", count);
        map.put("Sum", sum);
        map.put("Mean", mean);
        map.put("Median", median);
        map.put("Mode", mode);
        map.put("Variance", variance);
        map.put("Standard Deviation", stDev);

        return map;
    }
}
