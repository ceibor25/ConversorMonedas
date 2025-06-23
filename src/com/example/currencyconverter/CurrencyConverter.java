package com.example.currencyconverter;

import com.google.gson.Gson;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;

public class CurrencyConverter {
    private static final String API_KEY = "f3ae38b495cffb2e0daf4a3d"; // Reemplaza con tu clave de API
    private static final String BASE_URL = "https://v6.exchangerate-api.com/v6/"  + API_KEY + "/latest/";

    // Metodo para obtener las tasas de cambio
    public Map<String, Double> getExchangeRates(String baseCurrency) throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + baseCurrency))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // Verificar el código de estado HTTP
        if (response.statusCode() != 200) {
            throw new RuntimeException("Error al obtener las tasas de cambio: Código " + response.statusCode());
        }

        // Analizar la respuesta JSON
        Gson gson = new Gson();
        ApiResponse apiResponse = gson.fromJson(response.body(), ApiResponse.class);

        // Validar que la respuesta contenga datos válidos
        if (apiResponse == null || apiResponse.conversion_rates == null || apiResponse.conversion_rates.isEmpty()) {
            throw new RuntimeException("Respuesta de la API no contiene tasas de cambio válidas.");
        }

        return apiResponse.conversion_rates;
    }

    // Clase interna para mapear la respuesta JSON
    private static class ApiResponse {
        Map<String, Double> conversion_rates;
    }
}