package com.andrewchatch.mathapi.statisticsapi.methods.distributions;

import java.util.HashMap;

import com.andrewchatch.mathapi.statisticsapi.exceptions.InvalidProbabilityException;

public class Binomial {
    private int x;
    private int n;
    private double p;

    public Binomial(int x, int n, double p) {
            if (x > n) {
                throw new IllegalArgumentException("Number of trials must be greater than number of successes.");
            }
            if (p < 0 || p > 1) {
                throw new InvalidProbabilityException("p must be a number between 0 and 1.");
            }
    
            this.x = x;
            this.n = n;
            this.p = p;
    }

    public Binomial(Binomial binom) {
        try {
            if (binom.x > binom.n) {
                System.out.println("error");
                throw new IllegalArgumentException("Number of trials must be greater than number of successes.");
            }
            if (binom.p < 0 || binom.p > 1) {
                throw new InvalidProbabilityException("p must be a number between 0 and 1.");
            }
            if (binom.x < 0 || binom.n < 0) {
                throw new IllegalArgumentException("x and n must be greater than 0");
            }
    
            this.x = binom.x;
            this.n = binom.n;
            this.p = binom.p;
        }
        catch (Exception e) {
            System.out.println(e);
        }
        
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setN(int n) {
        this.n = n;
    }

    public void setP(double p) {
        this.p = p;
    }

    public int getX() {
        return this.x;
    }

    public int getN() {
        return this.n;
    }

    public double getP() {
        return this.p;
    }

    private double calculateProbability(int x, int n, double p) {
        double nChooseX = (factorial(n) / (factorial(x) * factorial(n - x)));
        double pToX = Math.pow(p, x);
        double qToNSubtractX = Math.pow(1 - p, n - x);

        double binomProbability = nChooseX * pToX * qToNSubtractX;
        return binomProbability;
    }

    private double calculateCumulativeProbInclusive(int x, int n, double p) {
        double sum = 0;
        for (int i = 0; i <= this.x; i++) {
            sum += calculateProbability(i, n, p);
        }

        return sum;
    }

    private double calculateCumulativeProbExclusive(int x, int n, double p) {
        double sum = 0;
        for (int i = 0; i < this.x; i++) {
            sum += calculateProbability(i, n, p);
        }

        return sum;
    }

    public HashMap<String,Double> getProbability(int x, int n, double p) {
        double pEqX = calculateProbability(x, n, p);
        double cumulativePInclusive = calculateCumulativeProbInclusive(x, n, p);
        double cumulativeQInclusive = 1 - cumulativePInclusive;
        double cumulativePExclusive = calculateCumulativeProbExclusive(x, n, p);
        double cumulativeQExclusive = 1 - cumulativePExclusive;
    
        HashMap<String,Double> map = new HashMap<String,Double>();
    
        map.put("P(X = x)", pEqX);
        map.put("P(X \u2265 x)", cumulativePInclusive);
        map.put("P(X > x)", cumulativeQInclusive);
        map.put("P(X < x)", cumulativePExclusive);
        map.put("P(X \u2264 x)", cumulativeQExclusive);
        return map;
    }

    private int factorial(int n) {
        if (n == 0) {
            return 1;
        }
        return n * this.factorial(n - 1);
    }
}

