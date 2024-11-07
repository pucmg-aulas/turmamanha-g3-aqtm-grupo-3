package controller;

import model.ClienteRanking;

import java.io.*;
import java.util.*;

public class RankingController {
     // RankingController.java
public List<String> gerarRanking() {
    Map<String, Double> gastosClientes = new HashMap<>();
    List<String> ranking = new ArrayList<>();

    try (BufferedReader reader = new BufferedReader(new FileReader("registro.txt"))) {
        String linha;
        while ((linha = reader.readLine()) != null) {
            String[] dados = linha.split(":");
            String idCliente = dados[0];
            try {
                double valorPago = Double.parseDouble(dados[3]);

                // Atualiza o total gasto do cliente
                gastosClientes.put(idCliente, gastosClientes.getOrDefault(idCliente, 0.0) + valorPago);
            } catch (NumberFormatException e) {
                System.out.println("Erro ao processar valor pago para o cliente: " + dados[3]);
            }
        }
    } catch (IOException e) {
        ranking.add("Erro ao ler o arquivo de registros: " + e.getMessage());
    }

    // Adiciona as informações de clientes e seus gastos ao ranking
    for (Map.Entry<String, Double> entry : gastosClientes.entrySet()) {
        ranking.add("Cliente ID: " + entry.getKey() + " - Total Gasto: R$ " + String.format("%.2f", entry.getValue()));
    }

    // Ordena os clientes com base no total gasto (do maior para o menor)
    try {
        ranking.sort((c1, c2) -> {
            double gasto1 = Double.parseDouble(c1.split(" - Total Gasto: R$ ")[1].replace("R$", "").trim());
            double gasto2 = Double.parseDouble(c2.split(" - Total Gasto: R$ ")[1].replace("R$", "").trim());
            return Double.compare(gasto2, gasto1); // Ordem decrescente
        });
    } catch (Exception e) {
        System.out.println("Erro ao ordenar o ranking: " + e.getMessage());
    }

    return ranking;
}

}
