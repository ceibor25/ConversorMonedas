package com.example.currencyconverter;

import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        com.example.currencyconverter.CurrencyConverter converter = new com.example.currencyconverter.CurrencyConverter();
        com.example.currencyconverter.ConversionHistory history = new com.example.currencyconverter.ConversionHistory(); // Historial de conversiones

        System.out.println("Bienvenido al Conversor de Monedas");

        while (true) {
            try {
                // Solicitar la moneda base
                System.out.print("Ingrese la moneda base (ej. USD): ");
                String baseCurrency = scanner.nextLine().toUpperCase();

                // Obtener las tasas de cambio
                Map<String, Double> rates = converter.getExchangeRates(baseCurrency);

                // Mostrar las monedas disponibles
                System.out.println("Monedas disponibles para convertir:");
                for (String currency : rates.keySet()) {
                    System.out.println("- " + currency);
                }

                // Solicitar la moneda objetivo
                System.out.print("Ingrese la moneda objetivo (ej. EUR): ");
                String targetCurrency = scanner.nextLine().toUpperCase();

                // Verificar si la moneda objetivo existe
                if (!rates.containsKey(targetCurrency)) {
                    System.out.println("Moneda no válida. Intente nuevamente.");
                    continue;
                }

                // Solicitar el monto a convertir
                System.out.print("Ingrese el monto a convertir: ");
                double amount = scanner.nextDouble();
                scanner.nextLine(); // Limpiar el buffer del scanner

                // Realizar la conversión
                double rate = rates.get(targetCurrency);
                double convertedAmount = amount * rate;

                System.out.printf("%.2f %s = %.2f %s%n", amount, baseCurrency, convertedAmount, targetCurrency);

                // Guardar la conversión en el historial
                history.addConversion(baseCurrency, targetCurrency, amount, convertedAmount);

            } catch (Exception e) {
                System.out.println("Ocurrió un error: " + e.getMessage());
            }

            // Preguntar si desea realizar otra conversión
            System.out.print("¿Desea realizar otra conversión? (s/n): ");
            String continuar = scanner.nextLine();
            if (!continuar.equalsIgnoreCase("s")) {
                break;
            }
        }

        // Mostrar el historial de conversiones
        history.showHistory();

        scanner.close();
    }
}