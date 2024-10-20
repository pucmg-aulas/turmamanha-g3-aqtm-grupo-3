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
            // Verifica e cria os arquivos de vagas e clientes, se não existirem
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

            // Ler vagas e clientes dos arquivos
            List<Vaga> vagas = ArquivoUtil.lerVagasDeArquivo("codigo/data/vagas.txt");
            clientes = lerClientesDoArquivo("codigo/data/clientes.txt");

            // Adicionar as vagas lidas ao estacionamento
            for (Vaga vaga : vagas) {
                estacionamento.adicionarVaga(vaga);
            }

            // Loop principal de interação
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
                        adicionarVeiculoClienteExistente();
                        break;
                    case 7:
                        removerVeiculoCliente();
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

    // Método para exibir o menu
    private static void exibirMenu() {
        System.out.println("Gestão de Estacionamento");
        System.out.println("1. Cadastrar cliente e veículo");
        System.out.println("2. Visualizar vagas disponíveis");
        System.out.println("3. Ocupação de vaga por veículo");
        System.out.println("4. Gerar fatura de um veículo");
        System.out.println("5. Visualizar clientes cadastrados");
        System.out.println("6. Adicionar veículo a cliente existente");
        System.out.println("7. Remover veículo de cliente existente");
        System.out.println("8. Sair");
        System.out.print("Escolha uma opção: ");
    }

    // Método para cadastrar cliente e veículo
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
    }

    // Exibir todas as vagas disponíveis de um tipo específico
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

        // Exibe todas as vagas disponíveis desse tipo
        if (vagasLivres != null && !vagasLivres.isEmpty()) {
            System.out.println("Vagas disponíveis:");
            for (Vaga vaga : vagasLivres) {
                System.out.println("- Vaga: " + vaga.getId());
            }
        } else {
            System.out.println("Não há vagas disponíveis desse tipo no momento.");
        }
    }

    // Método para ocupar uma vaga
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

    // Método para gerar fatura
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

    // Método para visualizar os clientes cadastrados
    private static void visualizarClientes() {
        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado.");
        } else {
            for (Cliente cliente : clientes) {
                System.out.println("Nome: " + cliente.getNome());
                System.out.println("Identificador: " + cliente.getIdentificador());
                System.out.println("Veículos: ");
                for (Veiculo veiculo : cliente.getVeiculos()) {
                    System.out.println(" - Placa: " + veiculo.getPlaca());
                }
                System.out.println();
            }
        }
    }

    // Método para adicionar um veículo a um cliente existente
    private static void adicionarVeiculoClienteExistente() {
        System.out.print("Digite o identificador do cliente: ");
        String identificador = scanner.nextLine();

        Cliente cliente = buscarClientePorIdentificador(identificador);
        if (cliente != null) {
            System.out.print("Placa do novo veículo: ");
            String novaPlaca = scanner.nextLine();

            Veiculo novoVeiculo = new Veiculo(novaPlaca, cliente);
            cliente.adicionarVeiculo(novoVeiculo);
            System.out.println("Veículo adicionado com sucesso!");
        } else {
            System.out.println("Cliente não encontrado.");
        }
    }

    // Método para remover um veículo de um cliente existente
    private static void removerVeiculoCliente() {
        System.out.print("Digite o identificador do cliente: ");
        String identificador = scanner.nextLine();

        Cliente cliente = buscarClientePorIdentificador(identificador);
        if (cliente != null) {
            System.out.print("Placa do veículo a ser removido: ");
            String placa = scanner.nextLine();

            boolean removido = cliente.removerVeiculo(placa);
            if (removido) {
                System.out.println("Veículo removido com sucesso!");
            } else {
                System.out.println("Veículo não encontrado.");
            }
        } else {
            System.out.println("Cliente não encontrado.");
        }
    }

    // Método para buscar um cliente pelo identificador
    private static Cliente buscarClientePorIdentificador(String identificador) {
        for (Cliente cliente : clientes) {
            if (cliente.getIdentificador().equals(identificador)) {
                return cliente;
            }
        }
        return null;
    }

    // Método para ler os clientes do arquivo
    private static List<Cliente> lerClientesDoArquivo(String caminhoArquivo) throws IOException {
        List<Cliente> clientes = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(caminhoArquivo));
        String linha;

        while ((linha = reader.readLine()) != null) {
            String[] dadosCliente = linha.split(";");
            String nome = dadosCliente[0];
            String identificador = dadosCliente[1];

            Cliente cliente = new Cliente(nome, identificador);
            for (int i = 2; i < dadosCliente.length; i++) {
                Veiculo veiculo = new Veiculo(dadosCliente[i], cliente);
                cliente.adicionarVeiculo(veiculo);
            }

            clientes.add(cliente);
        }

        reader.close();
        return clientes;
    }

    // Método para salvar os clientes no arquivo
    private static void salvarClientes() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("codigo/data/clientes.txt"))) {
            for (Cliente cliente : clientes) {
                writer.write(cliente.getNome() + ";" + cliente.getIdentificador());
                for (Veiculo veiculo : cliente.getVeiculos()) {
                    writer.write(";" + veiculo.getPlaca());
                }
                writer.newLine();
            }
            System.out.println("Dados dos clientes salvos com sucesso.");
        } catch (IOException e) {
            System.out.println("Erro ao salvar os dados dos clientes.");
        }
    }
}
