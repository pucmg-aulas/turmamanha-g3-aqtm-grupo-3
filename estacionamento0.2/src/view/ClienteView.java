package view;

import controller.ClienteController;
import controller.VagaController;
import controller.HistoricoController;
import controller.EstacionamentoController;
import controller.RankingController;
import model.Cliente;
import model.Vaga;

import java.util.List;
import java.util.Scanner;

public class ClienteView {
    private ClienteController clienteController;
    private VagaController vagaController;
    private HistoricoController historicoController;
    private EstacionamentoController estacionamentoController;  // Instanciando o controlador de estacionamento
    private RankingController rankingController;
    private Scanner scanner;

    public ClienteView() {
        clienteController = new ClienteController();
        vagaController = new VagaController();
        historicoController = new HistoricoController();
        estacionamentoController = new EstacionamentoController();  // Inicializando o controlador de estacionamento
        rankingController = new RankingController();
        scanner = new Scanner(System.in);
    }

    public void exibirMenu() {
        int opcao;
        do {
            System.out.println("\nMenu:");
            System.out.println("1. Cadastrar Cliente");
            System.out.println("2. Visualizar Vagas Disponíveis");
            System.out.println("3. Ocupar Vaga");
            System.out.println("4. Gerar Fatura");
            System.out.println("5. Visualizar Histórico");
            System.out.println("6. Visualizar Ranking de Clientes");
            System.out.println("7. Calcular Arrecadação por Tipo de Vaga");
            System.out.println("8. Calcular Total Arrecadado");
            System.out.println("9. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer

            switch (opcao) {
                case 1 -> cadastrarCliente();
                case 2 -> visualizarVagasDisponiveis();
                case 3 -> ocuparVaga();
                case 4 -> gerarFatura();
                case 5 -> visualizarHistorico();
                case 6 -> visualizarRanking();
                case 7 -> calcularArrecadacaoPorTipo();  // Chama o novo método para calcular a arrecadação por tipo de vaga
                case 8 -> calcularTotalArrecadado();  // Chama o método para calcular o total arrecadado
                case 9 -> System.out.println("Saindo...");
                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 9);
    }

    // Novo método para calcular o total arrecadado
    public void calcularTotalArrecadado() {
        estacionamentoController.calcularTotalArrecadado();  // Chama o controlador para calcular o total arrecadado
    }

    // Método para calcular a arrecadação por tipo de vaga
    public void calcularArrecadacaoPorTipo() {
        System.out.print("Digite o tipo de vaga (ex: comum, idoso, PCD, VIP): ");
        String tipoVaga = scanner.nextLine();
        estacionamentoController.calcularArrecadadoPorTipo(tipoVaga);  // Chama o controlador para calcular a arrecadação por tipo de vaga
    }

    public void cadastrarCliente() {
        System.out.print("Digite o ID do cliente: ");
        String id = scanner.nextLine();

        System.out.print("Digite o nome do cliente: ");
        String nome = scanner.nextLine();

        Cliente cliente = new Cliente(id, nome);

        String placa;
        do {
            System.out.print("Digite a placa do veículo (ou 'sair' para finalizar): ");
            placa = scanner.nextLine();
            if (!placa.equalsIgnoreCase("sair")) {
                cliente.adicionarVeiculo(placa);
            }
        } while (!placa.equalsIgnoreCase("sair"));

        clienteController.salvarCliente(cliente);
    }

    public void visualizarVagasDisponiveis() {
        List<Vaga> vagasDisponiveis = vagaController.listarVagasDisponiveis();
        System.out.println("\nVagas Disponíveis:");
        if (vagasDisponiveis.isEmpty()) {
            System.out.println("Nenhuma vaga disponível.");
        } else {
            for (Vaga vaga : vagasDisponiveis) {
                System.out.println(vaga);
            }
        }
    }

    public void ocuparVaga() {
        System.out.print("Digite o ID do Cliente: ");
        String idCliente = scanner.nextLine();

        System.out.print("Digite a placa do veículo: ");
        String placa = scanner.nextLine();

        System.out.print("Digite o ID da Vaga: ");
        String idVaga = scanner.nextLine();

        boolean ocupadaComSucesso = vagaController.ocuparVaga(idCliente, placa, idVaga);

        if (ocupadaComSucesso) {
            System.out.println("Vaga ocupada com sucesso!");
        } else {
            System.out.println("Falha ao ocupar a vaga.");
        }
    }

    public void gerarFatura() {
        System.out.print("Digite o ID do cliente: ");
        String idCliente = scanner.nextLine();
    
        System.out.print("Digite o ID da Vaga: ");
        String idVaga = scanner.nextLine();
    
        System.out.print("Digite a placa do veículo: ");
        String placa = scanner.nextLine();
    
        System.out.print("Digite a hora de entrada (ex: 14): ");
        int horaEntrada = scanner.nextInt();
    
        System.out.print("Digite os minutos de entrada (ex: 30): ");
        int minutoEntrada = scanner.nextInt();
    
        System.out.print("Digite a hora de saída (ex: 16): ");
        int horaSaida = scanner.nextInt();
    
        System.out.print("Digite os minutos de saída (ex: 45): ");
        int minutoSaida = scanner.nextInt();
    
        scanner.nextLine(); // Limpar o buffer
    
        System.out.print("Digite a data (ex: 2024-11-07): ");
        String data = scanner.nextLine();
    
        Vaga vaga = vagaController.encontrarVagaPorId(idVaga);
        if (vaga == null) {
            System.out.println("Vaga não encontrada!");
            return;
        }
    
        long horas = horaSaida - horaEntrada;
        long minutos = minutoSaida - minutoEntrada;
        if (minutos < 0) {
            minutos += 60;
            horas -= 1;
        }
    
        double valorPago = vagaController.calcularPreco(horas, minutos, vaga);
    
        vagaController.salvarFatura(idCliente, idVaga, placa, valorPago, data);
    
        System.out.println("Fatura gerada com sucesso! Valor a pagar: R$ " + valorPago);
    }

    public void visualizarHistorico() {
        System.out.print("Digite o ID do Cliente para ver o histórico: ");
        String idCliente = scanner.nextLine();

        historicoController.carregarHistoricoCliente(idCliente);
    }

    public void visualizarRanking() {
        rankingController.gerarRanking();
    }
}
