package controller;

import models.*;
//import utils.ArquivoUtil;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.nio.file.*;
import java.time.LocalDate;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

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

    // Função para obter o valor total arrecadado no estacionamento
    public void exibirValorTotalArrecadado() {
        double valorTotal = 0.0;
        try (BufferedReader reader = new BufferedReader(new FileReader("codigo/data/Registro.txt"))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                if (linha.contains("Valor: R$")) {
                    String[] partes = linha.split("Valor: R\\$ ");
                    valorTotal += Double.parseDouble(partes[1]);
                }
            }
            System.out.println("Valor total arrecadado no estacionamento: R$ " + valorTotal);
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo de registros: " + e.getMessage());
        }
    }

    // Função para exibir o valor arrecadado em um determinado mês e ano
    public void exibirValorArrecadadoMes(int mes, int ano) {
        double valorTotalMes = 0.0;
        try (BufferedReader reader = new BufferedReader(new FileReader("codigo/data/Registro.txt"))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                if (linha.contains("Valor: R$") && linha.contains("Entrada: " + ano + "-" + String.format("%02d", mes))) {
                    String[] partes = linha.split("Valor: R\\$ ");
                    valorTotalMes += Double.parseDouble(partes[1]);
                }
            }
            System.out.println("Valor arrecadado em " + Month.of(mes) + "/" + ano + ": R$ " + valorTotalMes);
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo de registros: " + e.getMessage());
        }
    }

    // Função para exibir o ranking dos clientes que mais geraram arrecadação em um mês específico
    public void exibirRankingClientesMes(int mes, int ano) {
        Map<String, Double> clienteArrecadacao = new HashMap<>();
        try (BufferedReader readerRegistros = new BufferedReader(new FileReader("codigo/data/Registro.txt"));
             BufferedReader readerClientes = new BufferedReader(new FileReader("codigo/data/Clientes.txt"))) {

            // Carregar ID e nome dos clientes em um mapa
            Map<String, String> mapaClientes = readerClientes.lines()
                    .map(linha -> linha.split(";"))
                    .collect(Collectors.toMap(
                        partes -> partes[1], 
                        partes -> partes[0])
                    );

            // Processar arrecadação por cliente para o mês e ano especificados
            String linha;
            while ((linha = readerRegistros.readLine()) != null) {
                if (linha.contains("Valor: R$") && linha.contains("Entrada: " + ano + "-" + String.format("%02d", mes))) {
                    String[] partes = linha.split(", ");
                    String idCliente = partes[0].split(": ")[1];
                    double valor = Double.parseDouble(partes[partes.length - 1].split("R\\$ ")[1]);
                    clienteArrecadacao.put(idCliente, clienteArrecadacao.getOrDefault(idCliente, 0.0) + valor);
                }
            }

            // Ordenar por valor arrecadado e exibir o ranking
            List<Map.Entry<String, Double>> ranking = clienteArrecadacao.entrySet()
                    .stream()
                    .sorted((a, b) -> Double.compare(b.getValue(), a.getValue()))
                    .collect(Collectors.toList());

            System.out.println("Ranking de arrecadação dos clientes em " + Month.of(mes) + "/" + ano + ":");
            for (Map.Entry<String, Double> entrada : ranking) {
                String nomeCliente = mapaClientes.getOrDefault(entrada.getKey(), "Cliente Desconhecido");
                System.out.printf("Cliente: %s (ID: %s), Total Arrecadado: R$ %.2f%n", nomeCliente, entrada.getKey(), entrada.getValue());
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo de registros ou clientes: " + e.getMessage());
        }
    }
}
