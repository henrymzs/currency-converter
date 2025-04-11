package org.aplicacao.dto;

import java.time.LocalDateTime;

public class ConversionHistory {
    private final String origin;
    private final String destination;
    private final double originalValue;
    private final double convertedValue;
    private final double rate;
    private final LocalDateTime dateTime;

    public ConversionHistory(String origin, String destination, double originalValue, double convertedValue, double rate) {
        this.origin = origin;
        this.destination = destination;
        this.originalValue = originalValue;
        this.convertedValue = convertedValue;
        this.rate = rate;
        this.dateTime = LocalDateTime.now();
    }

    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }

    public double getOriginalValue() {
        return originalValue;
    }

    public double getConvertedValue() {
        return convertedValue;
    }

    public double getRate() {
        return rate;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    @Override
    public String toString() {
        return String.format("[%s] %.2f %s → %.2f %s (Taxa: %.4f)",
                dateTime.toString(), originalValue, origin, convertedValue, destination, rate);
    }
}


