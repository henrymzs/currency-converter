package org.aplicacao.dto;


import com.google.gson.annotations.SerializedName;

import java.util.Map;

public class CoinDto {
    @SerializedName("time_last_update_utc")
    private String lastUpdated;

    @SerializedName("time_next_update_utc")
    private String nextUpdate;

    @SerializedName("base_code")
    private String baseCode;

    @SerializedName("conversion_rates")
    private Map<String, Double> conversionRates;

    public String getLastUpdated() {
        return lastUpdated;
    }

    public String getNextUpdate() {
        return nextUpdate;
    }

    public String getBaseCode() {
        return baseCode;
    }

    public Map<String, Double> getConversionRates() {
        return conversionRates;
    }
}

