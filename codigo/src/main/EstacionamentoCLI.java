package main;

import models.*;
import utils.ArquivoUtil;
import utils.ClienteUtil;

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
                        adicionarVeiculoParaCliente();
                        break;
                    case 7:
                        removerVeiculoDeCliente();
                        break;
                    case 8:
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
        System.out.println("6. Adicionar veículo a cliente");
        System.out.println("7. Remover veículo de cliente");
        System.out.println("8. Sair");
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
        salvarClientes(); // Salva os dados atualizados no arquivo
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

        vaga.ocupar();
        System.out.println("Vaga " + vaga.getId() + " ocupada pelo veículo de placa " + placa);
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

            vaga.desocupar();
        } catch (Exception e) {
            System.out.println("Erro ao gerar a fatura: " + e.getMessage());
        }
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

    private static void adicionarVeiculoParaCliente() {
        System.out.print("Identificador do cliente: ");
        String identificador = scanner.nextLine();

        Cliente cliente = buscarClientePorIdentificador(identificador);
        if (cliente != null) {
            System.out.print("Placa do novo veículo: ");
            String novaPlaca = scanner.nextLine();

            Veiculo novoVeiculo = new Veiculo(novaPlaca, cliente);
            cliente.adicionarVeiculo(novoVeiculo);

            System.out.println("Veículo adicionado com sucesso!");

            // Salvar as alterações no arquivo
            salvarClientes();
        } else {
            System.out.println("Cliente não encontrado.");
        }
    }

    private static void removerVeiculoDeCliente() {
        System.out.print("Identificador do cliente: ");
        String identificador = scanner.nextLine();

        Cliente cliente = buscarClientePorIdentificador(identificador);
        if (cliente != null) {
            System.out.print("Placa do veículo a ser removido: ");
            String placa = scanner.nextLine();

            boolean removido = cliente.removerVeiculo(placa);
            if (removido) {
                System.out.println("Veículo removido com sucesso!");

                // Salvar as alterações no arquivo
                salvarClientes();
            } else {
                System.out.println("Veículo não encontrado.");
            }
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

    private static List<Cliente> lerClientesDoArquivo(String filePath) {
        try {
            return ClienteUtil.lerClientesDeArquivo(filePath);
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo de clientes: " + e.getMessage());
            return new ArrayList<>(); // Retorna uma lista vazia em caso de erro
        }
    }

    private static void salvarClientes() {
        try {
            ClienteUtil.salvarClientesEmArquivo("codigo/data/clientes.txt", clientes);
        } catch (IOException e) {
            System.out.println("Erro ao salvar clientes no arquivo: " + e.getMessage());
        }
    }
}
