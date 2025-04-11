package org.aplicacao.services;

import org.aplicacao.dto.CoinDto;
import org.aplicacao.dto.ConversionHistory;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ConversorService {
    private final ApiService apiService = new ApiService();
    private final List<ConversionHistory> history = new ArrayList<>();

    public double converter(String origin, String target, double amount) throws Exception {
        CoinDto coinDto = apiService.getCoinDto(origin);
        Map<String, Double> rates = coinDto.getConversionRates();

        Double rate = rates.get(target);

        if (rate == null) {
            throw new Exception("Target currency not found");
        }

        double convertedAmount = amount * rate;

        ConversionHistory record = new ConversionHistory(
                origin, target, amount, convertedAmount, rate
        );

        history.add(record);
        return convertedAmount;
    }

    public List<ConversionHistory> getHistory() {
        return history;
    }
}
