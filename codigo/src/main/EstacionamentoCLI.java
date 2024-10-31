package main;

import models.*;
import utils.ArquivoUtil;
import utils.ClienteUtil;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EstacionamentoCLI {

    private static Estacionamento estacionamento = new Estacionamento();
    private static List<Cliente> clientes;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            File arquivoVagas = new File("codigo/data/vagas.txt");
            if (!arquivoVagas.exists()) {
                System.out.println("Arquivo de vagas não encontrado. Criando um novo arquivo.");
                arquivoVagas.createNewFile();
            }

            File arquivoClientes = new File("codigo/data/clientes.txt");
            if (!arquivoClientes.exists()) {
                System.out.println("Arquivo de clientes não encontrado. Criando um novo arquivo.");
                arquivoClientes.createNewFile();
            }

            List<Vaga> vagas = ArquivoUtil.lerVagasDeArquivo("codigo/data/vagas.txt");
            clientes = lerClientesDoArquivo("codigo/data/clientes.txt");

            for (Vaga vaga : vagas) {
                estacionamento.adicionarVaga(vaga);
            }

            while (true) {
                exibirMenu();
                int opcao = scanner.nextInt();
                scanner.nextLine();

                switch (opcao) {
                    case 1:
                    exibirVagasDisponiveis();
                    break;
                case 2:
                    ocuparVaga();
                    break;
                case 3:
                    gerarFatura();
                    break;
                case 4:
                    cadastrarClienteVeiculo();
                    break;
                case 5:
                    editarVeiculoDeCliente();
                    break;
                case 6:
                    visualizarClientes();
                    break;
                case 7:
                    exibirValorTotalArrecadado();
                    break;
                case 8:
                    System.out.print("Digite o mês (1-12): ");
                    int mes = scanner.nextInt();
                    System.out.print("Digite o ano: ");
                    int ano = scanner.nextInt();
                    estacionamentoController.exibirValorArrecadadoMes(mes, ano);
                    break;
                case 9:
                    System.out.print("Digite o mês (1-12): ");
                    int mesRanking = scanner.nextInt();
                    System.out.print("Digite o ano: ");
                    int anoRanking = scanner.nextInt();
                    estacionamentoController.exibirRankingClientesMes(mesRanking, anoRanking);
                    break;
                case 10:
                    System.out.println("Saindo do sistema...");
                    break;
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler ou criar arquivos de vagas ou clientes: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro inesperado: " + e.getMessage());
        }
    }

    private static void exibirMenu() {
        System.out.println("Gestão de Estacionamento");
        System.out.println("1. Exibir vagas disponíveis");
        System.out.println("2. Ocupar vaga");
        System.out.println("3. Gerar fatura");
        System.out.println("4. Cadastrar cliente/veículo");
        System.out.println("5. Editar veículo de cliente");
        System.out.println("6. Visualizar clientes");
        System.out.println("7. Exibir valor total arrecadado");
        System.out.println("8. Exibir valor arrecadado em um mês específico");
        System.out.println("9. Exibir ranking de clientes por arrecadação em um mês");
        System.out.println("10. Sair");
        System.out.print("Escolha uma opção: ");
    }

    private static void cadastrarClienteVeiculo() {
        System.out.print("Nome do cliente: ");
        String nome = scanner.nextLine();
        System.out.print("Identificador do cliente: ");
        String identificador = scanner.nextLine();

        Cliente cliente = new Cliente(nome, identificador);

        System.out.print("Placa do veículo: ");
        String placa = scanner.nextLine();

        Veiculo veiculo = new Veiculo(placa, cliente);
        cliente.adicionarVeiculo(veiculo);
        clientes.add(cliente);

        System.out.println("Cliente e veículo cadastrados com sucesso!");
        salvarClientes();
    }

    private static void exibirVagasDisponiveis() {
        System.out.println("Escolha o tipo de vaga: ");
        System.out.println("1. Regular");
        System.out.println("2. Idoso");
        System.out.println("3. VIP");
        System.out.println("4. PCD");
        int tipoVaga = scanner.nextInt();
        scanner.nextLine();

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

    private static void ocuparVaga() {
        System.out.print("Placa do veículo: ");
        String placa = scanner.nextLine();

        System.out.println("Escolha o tipo de vaga: ");
        System.out.println("1. Regular");
        System.out.println("2. Idoso");
        System.out.println("3. VIP");
        System.out.println("4. PCD");
        int tipoVaga = scanner.nextInt();
        scanner.nextLine();

        Vaga vaga = null;
        switch (tipoVaga) {
            case 1:
                vaga = estacionamento.buscarVagasLivresPorTipo(VagaRegular.class).stream().findFirst().orElse(null);
                break;
            case 2:
                vaga = estacionamento.buscarVagasLivresPorTipo(VagaIdoso.class).stream().findFirst().orElse(null);
                break;
            case 3:
                vaga = estacionamento.buscarVagasLivresPorTipo(VagaVIP.class).stream().findFirst().orElse(null);
                break;
            case 4:
                vaga = estacionamento.buscarVagasLivresPorTipo(VagaPCD.class).stream().findFirst().orElse(null);
                break;
            default:
                System.out.println("Opção inválida.");
                return;
        }

        if (vaga == null) {
            System.out.println("Não há vagas disponíveis desse tipo.");
            return;
        }

        LocalDateTime entrada = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String entradaFormatada = entrada.format(formatter);

        Cliente cliente = buscarClientePorPlaca(placa);
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

        System.out.println(
                "Vaga " + vaga.getId() + " ocupada pelo veículo de placa " + placa + " às " + entradaFormatada);

        String registroEntrada = "ID Cliente: " + cliente.getIdentificador() +
                ", Placa: " + placa +
                ", Entrada: " + entradaFormatada +
                ", Saída: Pendente, Valor: Pendente";
        salvarRegistroNoArquivo(registroEntrada);
    }

    private static void gerarFatura() {
        try {
            System.out.print("Identificador da vaga: ");
            String idVaga = scanner.nextLine();

            Vaga vaga = estacionamento.buscarVagaPorId(idVaga);
            if (vaga == null || !vaga.isOcupada()) {
                System.out.println("A vaga não está ocupada ou não existe.");
                return;
            }

            System.out.print("Tempo de ocupação (minutos): ");
            int minutos = scanner.nextInt();
            scanner.nextLine();

            Fatura fatura = new Fatura(vaga, minutos);
            double valor = fatura.calcularValor();
            System.out.println("Valor da fatura: R$ " + valor);

            LocalDateTime saida = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String saidaFormatada = saida.format(formatter);

            String registroSaida = "ID Cliente: "
                    + buscarClientePorPlaca(vaga.getVeiculo().getPlaca()).getIdentificador() +
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

    private static Cliente buscarClientePorPlaca(String placa) {
        for (Cliente cliente : clientes) {
            for (Veiculo veiculo : cliente.getVeiculos()) {
                if (veiculo.getPlaca().equals(placa)) {
                    return cliente;
                }
            }
        }
        return null;
    }

    private static void visualizarClientes() {
        System.out.println("Clientes cadastrados:");
        for (Cliente cliente : clientes) {
            System.out.println("Cliente: " + cliente.getNome() + " (" + cliente.getIdentificador() + ")");
            System.out.println("Veículos: ");
            for (Veiculo veiculo : cliente.getVeiculos()) {
                System.out.println("- " + veiculo.getPlaca());
            }
        }
    }

    private static void editarVeiculoDeCliente() {
        System.out.print("Identificador do cliente: ");
        String identificador = scanner.nextLine();

        Cliente cliente = buscarClientePorIdentificador(identificador);
        if (cliente != null) {
            System.out.println("Escolha uma opção: ");
            System.out.println("1. Adicionar veículo");
            System.out.println("2. Remover veículo");
            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:

                    System.out.print("Placa do novo veículo: ");
                    String novaPlaca = scanner.nextLine();

                    Veiculo novoVeiculo = new Veiculo(novaPlaca, cliente);
                    cliente.adicionarVeiculo(novoVeiculo);

                    System.out.println("Veículo adicionado com sucesso!");
                    break;

                case 2:

                    System.out.print("Placa do veículo a ser removido: ");
                    String placaRemover = scanner.nextLine();

                    boolean removido = cliente.removerVeiculo(placaRemover);
                    if (removido) {
                        System.out.println("Veículo removido com sucesso!");
                    } else {
                        System.out.println("Veículo não encontrado.");
                    }
                    break;

                default:
                    System.out.println("Opção inválida.");
                    return;
            }

            salvarClientes();
        } else {
            System.out.println("Cliente não encontrado.");
        }
    }

    private static Cliente buscarClientePorIdentificador(String identificador) {
        for (Cliente cliente : clientes) {
            if (cliente.getIdentificador().equals(identificador)) {
                return cliente;
            }
        }
        return null;
    }

    private static void visualizarHistoricoReservas() {
        System.out.print("Identificador do cliente: ");
        String identificador = scanner.nextLine().trim();

        try {
            File arquivoRegistros = new File("codigo/data/registros.txt");
            if (!arquivoRegistros.exists()) {
                System.out.println("Arquivo de registros não encontrado.");
                return;
            }

            BufferedReader reader = new BufferedReader(new FileReader(arquivoRegistros));
            String linha;
            boolean encontrouRegistros = false;

            System.out.println("Histórico de reservas do cliente " + identificador + ":");
            while ((linha = reader.readLine()) != null) {

                String[] dados = linha.split(",");

                String idCliente = dados[0].split(": ")[1].trim();

                if (idCliente.equals(identificador)) {
                    String placa = dados[1].split(": ")[1].trim();
                    String entrada = dados[2].split(": ")[1].trim();
                    String saida = dados[3].split(": ")[1].trim();
                    String valorPago = dados[4].split(": ")[1].trim();

                    System.out.println("Placa: " + placa + ", Entrada: " + entrada + ", Saída: " + saida
                            + ", Valor pago: " + valorPago);
                    encontrouRegistros = true;
                }
            }

            if (!encontrouRegistros) {
                System.out.println("Nenhuma reserva encontrada para este cliente.");
            }

            reader.close();
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo de registros: " + e.getMessage());
        }
    }

    private static List<Cliente> lerClientesDoArquivo(String filePath) {
        try {
            return ClienteUtil.lerClientesDeArquivo(filePath);
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo de clientes: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    private static void salvarClientes() {
        try {
            ClienteUtil.salvarClientesEmArquivo("codigo/data/clientes.txt", clientes);
        } catch (IOException e) {
            System.out.println("Erro ao salvar clientes no arquivo: " + e.getMessage());
        }
    }

    private static void salvarRegistroNoArquivo(String registro) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("codigo/data/registros.txt", true))) {
            writer.write(registro);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Erro ao salvar o registro no arquivo: " + e.getMessage());
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