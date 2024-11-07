package controller;

import model.Vaga;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class VagaController {
    private List<Vaga> vagas;

    public VagaController() {
        vagas = new ArrayList<>();
        carregarVagas();
    }

    private void carregarVagas() {
        try (BufferedReader reader = new BufferedReader(new FileReader("Vagas.txt"))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(":");
                String id = dados[0];
                String tipo = dados[1];
                boolean ocupada = Boolean.parseBoolean(dados[2]);
                vagas.add(new Vaga(id, tipo, ocupada));
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo de vagas: " + e.getMessage());
        }
    }

   // Método para ocupar a vaga
public boolean ocuparVaga(String idCliente, String placa, String idVaga) {
    for (Vaga vaga : vagas) {
        if (vaga.getId().equals(idVaga) && !vaga.isOcupada()) {
            // Atualizar a vaga para ocupada
            vaga.setOcupada(true);
            System.out.println("Vaga " + idVaga + " ocupada com sucesso por cliente " + idCliente + " e veículo " + placa);
            salvarVagas();  // Salvar as mudanças no arquivo
            // Salvar a fatura no momento da ocupação da vaga, passando o idCliente
            String data = "2024-11-07"; // Aqui você pode pegar a data do sistema ou da entrada
            double valorPago = calcularPreco(2, 30, vaga); // Exemplo de cálculo com horas e minutos
            salvarFatura(idCliente, idVaga, placa, valorPago, data);
            return true;
        }
    }
    System.out.println("Vaga não encontrada ou já ocupada.");
    return false;
}

    // Método para salvar as vagas no arquivo
    private void salvarVagas() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Vagas.txt"))) {
            for (Vaga vaga : vagas) {
                writer.write(vaga.getId() + ":" + vaga.getTipo() + ":" + vaga.isOcupada());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar vagas: " + e.getMessage());
        }
    }

    // Método para listar as vagas disponíveis
    public List<Vaga> listarVagasDisponiveis() {
        List<Vaga> vagasDisponiveis = new ArrayList<>();
        for (Vaga vaga : vagas) {
            if (!vaga.isOcupada()) {
                vagasDisponiveis.add(vaga);
            }
        }
        return vagasDisponiveis;
    }

    // Método para encontrar uma vaga pelo ID
    public Vaga encontrarVagaPorId(String idVaga) {
        for (Vaga vaga : vagas) {
            if (vaga.getId().equals(idVaga)) {
                return vaga;
            }
        }
        return null;  // Retorna null caso a vaga não seja encontrada
    }

    // Método para calcular o preço a ser pago
    public double calcularPreco(long horas, long minutos, Vaga vaga) {
        double precoPor15Minutos = 4.0;
        long totalMinutos = horas * 60 + minutos;
        long intervalos15minutos = (totalMinutos + 14) / 15;  // Round up
        double preco = intervalos15minutos * precoPor15Minutos;

        // Aplica regras de preços
        if (preco > 50) {
            preco = 50;  // Valor máximo de R$50
        }

        // Aplica descontos ou acréscimos conforme o tipo de vaga
        switch (vaga.getTipo().toLowerCase()) {
            case "idoso":
                preco *= 0.85;  // Desconto de 15% para idosos
                break;
            case "pcd":
                preco *= 0.87;  // Desconto de 13% para PCD
                break;
            case "vip":
                preco *= 1.20;  // Acréscimo de 20% para VIP
                break;
        }

        return preco;
    }

// Método para salvar a fatura no arquivo registro.txt
public void salvarFatura(String idCliente, String idVaga, String placa, double valorPago, String data) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter("registro.txt", true))) {
        // Escreve todos os dados da fatura, incluindo o preço calculado
        writer.write(idCliente + ":" + idVaga + ":" + placa + ":" + valorPago + ":" + data);
        writer.newLine();
    } catch (IOException e) {
        System.out.println("Erro ao salvar fatura: " + e.getMessage());
    }
}

}
