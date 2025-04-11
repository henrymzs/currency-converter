package org.aplicacao;

import org.aplicacao.dto.CoinDto;
import org.aplicacao.dto.ConversionHistory;
import org.aplicacao.services.ApiService;
import org.aplicacao.services.ConversorService;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ApiService apiService = new ApiService();
        ConversorService conversor = new ConversorService();
        CoinDto coinDto;

        while (true) {
            System.out.println("\n=== Conversor de Moedas ===");
            System.out.println("1 - Converter moedas");
            System.out.println("2 - Mostrar tabela de cotações");
            System.out.println("3 - Ver histórico");
            System.out.println("0 - Sair do programa");
            System.out.print("Escolha: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // limpar o \n

            try {
                switch (choice) {
                    case 1:
                        System.out.print("Digite a moeda de origem (ex: USD): ");
                        String baseCurrency = scanner.nextLine().toUpperCase();

                        System.out.print("Digite a moeda de destino (ex: BRL): ");
                        String targetCurrency = scanner.nextLine().toUpperCase();

                        System.out.print("Digite o valor que deseja converter: ");
                        double amount = scanner.nextDouble();
                        scanner.nextLine(); // limpar buffer

                        double result = conversor.converter(baseCurrency, targetCurrency, amount);
                        System.out.printf("\n%.2f %s equivalem a %.2f %s\n", amount, baseCurrency, result, targetCurrency);
                        break;

                    case 2:
                        System.out.print("Digite a moeda base (ex: USD): ");
                        String coinBase = scanner.nextLine().toUpperCase();

                        coinDto = apiService.getCoinDto(coinBase);
                        Map<String, Double> rates = coinDto.getConversionRates();

                        System.out.println("\nCotações para" + coinBase + ":");
                        for (Map.Entry<String, Double> entry : rates.entrySet()) {
                            System.out.println("1 " + coinBase + " = " + entry.getValue() + " " + entry.getKey());
                        }
                        break;

                    case 3:
                        var history = conversor.getHistory();

                        if (history.isEmpty()) {
                            System.out.println("Nenhuma conversão realizada ainda.");
                        } else {
                            System.out.println("\nHistórico de conversões:");
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

                            for (ConversionHistory h : history) {
                                System.out.printf("%s: %.2f %s => %.2f %s (Taxa: %.4f)\n",
                                        h.getDateTime().format(formatter),
                                        h.getOriginalValue(),
                                        h.getOrigin(),
                                        h.getConvertedValue(),
                                        h.getDestination(),
                                        h.getRate());
                            }
                        }
                        break;

                    case 0:
                        System.out.println("Saindo do programa. Até mais!");
                        return;

                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                        break;
                }
            } catch (IOException | InterruptedException e) {
                System.out.println("Erro ao acessar a API: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Erro inesperado: " + e.getMessage());
            }
        }
    }
}