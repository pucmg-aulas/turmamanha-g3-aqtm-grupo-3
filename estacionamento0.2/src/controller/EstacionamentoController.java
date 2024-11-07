package controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class EstacionamentoController {

    // Função para calcular o total arrecadado
    // EstacionamentoController.java
public String calcularTotalArrecadado() {
    double totalArrecadado = 0.0;
    StringBuilder resultado = new StringBuilder();

    try (BufferedReader reader = new BufferedReader(new FileReader("registro.txt"))) {
        String linha;
        while ((linha = reader.readLine()) != null) {
            String[] partes = linha.split(":");

            if (partes.length == 5) {
                try {
                    double valor = Double.parseDouble(partes[3]);
                    totalArrecadado += valor;
                } catch (NumberFormatException e) {
                    resultado.append("Erro ao converter valor: ").append(partes[3]).append("\n");
                }
            }
        }
        resultado.append(String.format("Valor Arrecadado Total: R$ %.2f\n", totalArrecadado));
    } catch (IOException e) {
        resultado.append("Erro ao ler o arquivo de registros: ").append(e.getMessage()).append("\n");
    }

    return resultado.toString();
}

public String calcularArrecadadoPorTipo(String tipoVaga) {
    double totalArrecadado = 0.0;
    StringBuilder resultado = new StringBuilder();

    tipoVaga = tipoVaga.toLowerCase();

    try (BufferedReader reader = new BufferedReader(new FileReader("registro.txt"))) {
        String linha;
        while ((linha = reader.readLine()) != null) {
            String[] partes = linha.split(":");

            if (partes.length == 5) {
                String tipoVagaRegistro = partes[1].substring(0, 3).toLowerCase();

                if (tipoVagaRegistro.equals(tipoVaga)) {
                    try {
                        double valor = Double.parseDouble(partes[3]);
                        totalArrecadado += valor;
                    } catch (NumberFormatException e) {
                        resultado.append("Erro ao converter valor: ").append(partes[3]).append("\n");
                    }
                }
            }
        }

        if (totalArrecadado > 0) {
            resultado.append(String.format("Valor arrecadado para vagas do tipo %s: R$ %.2f\n", tipoVaga, totalArrecadado));
        } else {
            resultado.append("Nenhuma arrecadação encontrada para vagas do tipo ").append(tipoVaga).append(".\n");
        }
    } catch (IOException e) {
        resultado.append("Erro ao ler o arquivo de registros: ").append(e.getMessage()).append("\n");
    }

    return resultado.toString();
}

}
