package controller;

import model.Vaga;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class HistoricoController {
    // Método para carregar o histórico de um cliente
public String carregarHistoricoCliente(String idCliente) {
    StringBuilder historico = new StringBuilder();
    boolean encontrouHistorico = false;

    try (BufferedReader reader = new BufferedReader(new FileReader("registro.txt"))) {
        String linha;
        while ((linha = reader.readLine()) != null) {
            String[] dados = linha.split(":");
            String idClienteArquivo = dados[0];
            String placa = dados[2];
            double valorPago = Double.parseDouble(dados[3]);
            String data = dados[4];

            if (idClienteArquivo.equals(idCliente)) {
                historico.append("ID Cliente: ").append(idClienteArquivo)
                        .append(", Placa: ").append(placa)
                        .append(", Data: ").append(data)
                        .append(", Valor Pago: R$ ").append(valorPago).append("\n");
                encontrouHistorico = true;
            }
        }

        if (!encontrouHistorico) {
            historico.append("Nenhuma utilização encontrada para este cliente.\n");
        }
    } catch (IOException e) {
        historico.append("Erro ao ler o arquivo de registros: ").append(e.getMessage()).append("\n");
    }

    return historico.toString();
}

}
