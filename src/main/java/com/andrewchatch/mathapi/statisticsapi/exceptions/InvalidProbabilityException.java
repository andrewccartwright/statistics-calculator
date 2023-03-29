package com.andrewchatch.mathapi.statisticsapi.exceptions;

public class InvalidProbabilityException extends IllegalArgumentException {
    public InvalidProbabilityException() {
        super();
    }

    public InvalidProbabilityException(String exceptionMessage) {
        super(exceptionMessage);
    }
}
