package org.aplicacao.services;

import com.google.gson.Gson;
import org.aplicacao.dto.CoinDto;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ApiService {

    public CoinDto getCoinDto(String baseCurrency) throws IOException, InterruptedException {
        CoinDto coinDto = new CoinDto();

        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://v6.exchangerate-api.com/v6/f44eb53358aed2a395f98e10/latest/" + baseCurrency))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            Gson gson = new Gson();
            coinDto = gson.fromJson(response.body(), CoinDto.class);
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }

        return coinDto;
    }
}
