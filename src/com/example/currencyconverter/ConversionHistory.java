package com.example.currencyconverter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ConversionHistory {
    private List<String> history = new ArrayList<>();

    // Metodo para añadir una nueva conversión al historial
    public void addConversion(String base, String target, double amount, double result) {
        String entry = String.format("%s - %.2f %s -> %.2f %s",
                LocalDateTime.now(), amount, base, result, target);
        history.add(entry);
    }

    // Metodo para mostrar el historial de conversiones
    public void showHistory() {
        if (history.isEmpty()) {
            System.out.println("No hay conversiones en el historial.");
            return;
        }

        System.out.println("Historial de conversiones:");
        for (String entry : history) {
            System.out.println(entry);
        }
    }
}