package main;

import models.*;
import utils.ArquivoUtil;
import utils.ClienteUtil;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.Month;

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
                        cadastrarClienteVeiculo();
                        break;
                    case 2:
                        exibirVagasDisponiveis();
                        break;
                    case 3:
                        ocuparVaga();
                        break;
                    case 4:
                        gerarFatura();
                        break;
                    case 5:
                        visualizarClientes();
                        break;
                    case 6:
                        editarVeiculoDeCliente();
                        break;
                    case 7:
                        visualizarHistoricoReservas();
                        break;
                    case 8:
                        calcularReceitaTotal();
                        break;
                    case 9:
                        calcularReceitaPorMes();
                        break;
                    case 10:
                        calcularMediaReceitaPorUso();
                        break;
                    case 11:
                        salvarClientes();
                        System.out.println("Saindo...");
                        return;
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
        System.out.println("1. Cadastrar cliente e veículo");
        System.out.println("2. Visualizar vagas disponíveis");
        System.out.println("3. Ocupação de vaga por veículo");
        System.out.println("4. Gerar fatura de um veículo");
        System.out.println("5. Visualizar clientes cadastrados");
        System.out.println("6. Editar veículo de cliente");
        System.out.println("7. Ver histórico de reservas");
        System.out.println("8. Calcular receita total");
        System.out.println("9. Calcular receita de um mês específico");
        System.out.println("10. Calcular média da receita por uso");
        System.out.println("11. Sair");
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
            System.out.println("Erro ao gerar fatura: " + e.getMessage());
        }
    }

    private static void visualizarClientes() {
        System.out.println("Clientes cadastrados:");
        for (Cliente cliente : clientes) {
            System.out.println("- " + cliente.getNome() + " (ID: " + cliente.getIdentificador() + ")");
        }
    }

    private static void editarVeiculoDeCliente() {
        System.out.print("ID do cliente: ");
        String idCliente = scanner.nextLine();
        Cliente cliente = buscarClientePorId(idCliente);

        if (cliente == null) {
            System.out.println("Cliente não encontrado.");
            return;
        }

        System.out.print("Nova placa do veículo: ");
        String novaPlaca = scanner.nextLine();

        Veiculo veiculo = cliente.getVeiculos().stream().findFirst().orElse(null);
        if (veiculo != null) {
            veiculo.setPlaca(novaPlaca);
            System.out.println("Veículo atualizado com sucesso!");
        } else {
            System.out.println("Cliente não possui veículos cadastrados.");
        }

        salvarClientes();
    }

    private static void visualizarHistoricoReservas() {
        System.out.println("Histórico de reservas:");
        // Implemente a visualização do histórico aqui, caso exista
    }

    private static void calcularReceitaTotal() {
        double receitaTotal = 0;
        try (BufferedReader br = new BufferedReader(new FileReader("codigo/data/registros.txt"))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(", Valor: R$ ");
                if (partes.length > 1) {
                    receitaTotal += Double.parseDouble(partes[1].trim());
                }
            }
            System.out.println("Receita total: R$ " + receitaTotal);
        } catch (IOException e) {
            System.out.println("Erro ao calcular receita total: " + e.getMessage());
        }
    }

    private static void calcularReceitaPorMes() {
        System.out.print("Mês (1-12): ");
        int mes = scanner.nextInt();
        scanner.nextLine();

        double receitaMes = 0;
        try (BufferedReader br = new BufferedReader(new FileReader("codigo/data/registros.txt"))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(", ");
                String dataStr = partes[2].split(": ")[1]; // Pega a data da linha
                LocalDateTime data = LocalDateTime.parse(dataStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

                if (data.getMonthValue() == mes) {
                    receitaMes += Double.parseDouble(partes[4].split(": R$ ")[1].trim());
                }
            }
            System.out.println("Receita do mês " + Month.of(mes) + ": R$ " + receitaMes);
        } catch (IOException e) {
            System.out.println("Erro ao calcular receita do mês: " + e.getMessage());
        }
    }

    private static void calcularMediaReceitaPorUso() {
        int totalUso = 0;
        double receitaTotal = 0;
        try (BufferedReader br = new BufferedReader(new FileReader("codigo/data/registros.txt"))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(", Valor: R$ ");
                if (partes.length > 1) {
                    receitaTotal += Double.parseDouble(partes[1].trim());
                    totalUso++;
                }
            }
            double mediaReceita = totalUso > 0 ? receitaTotal / totalUso : 0;
            System.out.println("Média da receita por uso: R$ " + mediaReceita);
        } catch (IOException e) {
            System.out.println("Erro ao calcular média de receita por uso: " + e.getMessage());
        }
    }

    private static void salvarClientes() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("codigo/data/clientes.txt"))) {
            for (Cliente cliente : clientes) {
                bw.write(cliente.getNome() + "," + cliente.getIdentificador() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar clientes: " + e.getMessage());
        }
    }

    private static void salvarRegistroNoArquivo(String registro) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("codigo/data/registros.txt", true))) {
            bw.write(registro + "\n");
        } catch (IOException e) {
            System.out.println("Erro ao salvar registro: " + e.getMessage());
        }
    }

    private static List<Cliente> lerClientesDoArquivo(String caminho) {
        List<Cliente> clientes = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(caminho))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(",");
                Cliente cliente = new Cliente(partes[0], partes[1]);
                clientes.add(cliente);
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler clientes do arquivo: " + e.getMessage());
        }
        return clientes;
    }

    private static Cliente buscarClientePorId(String idCliente) {
        for (Cliente cliente : clientes) {
            if (cliente.getIdentificador().equals(idCliente)) {
                return cliente;
            }
        }
        return null;
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
}
