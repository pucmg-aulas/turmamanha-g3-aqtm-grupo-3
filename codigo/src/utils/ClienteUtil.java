package utils;

import models.Cliente;
import models.Veiculo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteUtil {

    // Método para ler os clientes e seus veículos de um arquivo de texto
    public static List<Cliente> lerClientesDeArquivo(String caminho) throws IOException {
        List<Cliente> clientes = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(caminho));
        String linha;

        while ((linha = reader.readLine()) != null) {
            // Divide a linha pelos pontos e vírgula
            String[] dados = linha.split(";");

            // Verifica se a linha tem pelo menos 2 elementos (nome e identificador)
            if (dados.length < 2) {
                System.out.println("Erro: linha mal formatada ou incompleta no arquivo de clientes.");
                continue; // Pula para a próxima linha
            }

            String nome = dados[0];
            String identificador = dados[1];
            Cliente cliente = new Cliente(nome, identificador);

            // Adiciona os veículos associados ao cliente (se houver)
            for (int i = 2; i < dados.length; i++) {
                cliente.adicionarVeiculo(new Veiculo(dados[i], cliente));
            }

            clientes.add(cliente);
        }
        reader.close();
        return clientes;
    }

    // Método para salvar os clientes e seus veículos em um arquivo de texto
    public static void salvarClientesEmArquivo(String caminho, List<Cliente> clientes) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(caminho));
        for (Cliente cliente : clientes) {
            StringBuilder linha = new StringBuilder();
            linha.append(cliente.getNome()).append(";").append(cliente.getIdentificador());
            for (Veiculo veiculo : cliente.getVeiculos()) {
                linha.append(";").append(veiculo.getPlaca());
            }
            writer.write(linha.toString() + "\n");
        }
        writer.close();
    }
}
