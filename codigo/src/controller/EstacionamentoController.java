package controller;

import models.*;
//import utils.ArquivoUtil;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class EstacionamentoController {
    private Estacionamento estacionamento;
    
    public EstacionamentoController(Estacionamento estacionamento) {
        this.estacionamento = estacionamento;
    }

    public void exibirVagasDisponiveis(int tipoVaga) {
        List<Vaga> vagasLivres = null;

        switch (tipoVaga) {
            case 1:
                vagasLivres = estacionamento.buscarVagasLivresPorTipo(VagaRegular.class);
                break;
            case 2:
                vagasLivres = estacionamento.buscarVagasLivresPorTipo(VagaIdoso.class);
                break;
            case 3:
                vagasLivres = estacionamento.buscarVagasLivresPorTipo(VagaVIP.class);
                break;
            case 4:
                vagasLivres = estacionamento.buscarVagasLivresPorTipo(VagaPCD.class);
                break;
            default:
                System.out.println("Opção inválida.");
                return;
        }

        if (vagasLivres != null && !vagasLivres.isEmpty()) {
            System.out.println("Vagas disponíveis:");
            for (Vaga vaga : vagasLivres) {
                System.out.println("- Vaga: " + vaga.getId());
            }
        } else {
            System.out.println("Não há vagas disponíveis desse tipo no momento.");
        }
    }

    public void ocuparVaga(String placa, int tipoVaga, List<Cliente> clientes) {
        Vaga vaga = buscarVagaDisponivel(tipoVaga);
        if (vaga == null) {
            System.out.println("Não há vagas disponíveis desse tipo.");
            return;
        }

        LocalDateTime entrada = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String entradaFormatada = entrada.format(formatter);

        Cliente cliente = buscarClientePorPlaca(clientes, placa);
        if (cliente == null) {
            System.out.println("Cliente não encontrado para o veículo de placa " + placa);
            return;
        }

        Veiculo veiculo = cliente.getVeiculos().stream()
                .filter(v -> v.getPlaca().equals(placa))
                .findFirst()
                .orElse(null);

        if (veiculo == null) {
            System.out.println("Veículo não encontrado para a placa " + placa);
            return;
        }

        vaga.ocupar(veiculo);
        System.out.println("Vaga " + vaga.getId() + " ocupada pelo veículo de placa " + placa + " às " + entradaFormatada);

        String registroEntrada = "ID Cliente: " + cliente.getIdentificador() +
                ", Placa: " + placa +
                ", Entrada: " + entradaFormatada +
                ", Saída: Pendente, Valor: Pendente";
        salvarRegistroNoArquivo(registroEntrada);
    }

    public void gerarFatura(String idVaga, int minutos, List<Cliente> clientes) {
        try {
            Vaga vaga = estacionamento.buscarVagaPorId(idVaga);
            if (vaga == null || !vaga.isOcupada()) {
                System.out.println("A vaga não está ocupada ou não existe.");
                return;
            }

            Fatura fatura = new Fatura(vaga, minutos);
            double valor = fatura.calcularValor();
            System.out.println("Valor da fatura: R$ " + valor);

            LocalDateTime saida = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String saidaFormatada = saida.format(formatter);

            String registroSaida = "ID Cliente: "
                    + buscarClientePorPlaca(clientes, vaga.getVeiculo().getPlaca()).getIdentificador() +
                    ", Placa: " + vaga.getVeiculo().getPlaca() +
                    ", Entrada: " + vaga.getHorarioEntrada() +
                    ", Saída: " + saidaFormatada +
                    ", Valor: R$ " + valor;
            salvarRegistroNoArquivo(registroSaida);

            vaga.desocupar();
        } catch (Exception e) {
            System.out.println("Erro ao gerar a fatura: " + e.getMessage());
        }
    }

    private Vaga buscarVagaDisponivel(int tipoVaga) {
        switch (tipoVaga) {
            case 1:
                return estacionamento.buscarVagasLivresPorTipo(VagaRegular.class).stream().findFirst().orElse(null);
            case 2:
                return estacionamento.buscarVagasLivresPorTipo(VagaIdoso.class).stream().findFirst().orElse(null);
            case 3:
                return estacionamento.buscarVagasLivresPorTipo(VagaVIP.class).stream().findFirst().orElse(null);
            case 4:
                return estacionamento.buscarVagasLivresPorTipo(VagaPCD.class).stream().findFirst().orElse(null);
            default:
                return null;
        }
    }

    private Cliente buscarClientePorPlaca(List<Cliente> clientes, String placa) {
        for (Cliente cliente : clientes) {
            for (Veiculo veiculo : cliente.getVeiculos()) {
                if (veiculo.getPlaca().equals(placa)) {
                    return cliente;
                }
            }
        }
        return null;
    }

    private void salvarRegistroNoArquivo(String registro) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("codigo/data/registros.txt", true))) {
            writer.write(registro);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Erro ao salvar o registro no arquivo: " + e.getMessage());
        }
    }
}
