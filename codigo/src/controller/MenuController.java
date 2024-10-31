package controller;

// import models.*;
// import java.util.List;
import java.util.Scanner;

public class MenuController {

    private EstacionamentoController estacionamentoController;
    private ClienteController clienteController;
    private Scanner scanner;

    public MenuController(EstacionamentoController estacionamentoController, ClienteController clienteController, Scanner scanner) {
        this.estacionamentoController = estacionamentoController;
        this.clienteController = clienteController;
        this.scanner = scanner;
    }

    public void exibirMenu() {
        int opcao;
        do {
            System.out.println("\nMenu:");
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
            opcao = scanner.nextInt();
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
                    clienteController.cadastrarClienteVeiculo();
                    break;
                case 5:
                    clienteController.editarVeiculoDeCliente();
                    break;
                case 6:
                    clienteController.visualizarClientes();
                    break;
                case 7:
                    estacionamentoController.exibirValorTotalArrecadado();
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
                    System.out.println("Opção inválida.");
            }
        } while (opcao != 10);
    }

    private void exibirVagasDisponiveis() {
        System.out.println("1. Regular");
        System.out.println("2. Idoso");
        System.out.println("3. VIP");
        System.out.println("4. PCD");
        System.out.print("Escolha o tipo de vaga: ");
        int tipoVaga = scanner.nextInt();
        scanner.nextLine();
        estacionamentoController.exibirVagasDisponiveis(tipoVaga);
    }

    private void ocuparVaga() {
        System.out.print("Placa do veículo: ");
        String placa = scanner.nextLine();

        System.out.println("1. Regular");
        System.out.println("2. Idoso");
        System.out.println("3. VIP");
        System.out.println("4. PCD");
        System.out.print("Escolha o tipo de vaga: ");
        int tipoVaga = scanner.nextInt();
        scanner.nextLine();

        estacionamentoController.ocuparVaga(placa, tipoVaga, clienteController.getClientes());
    }

    private void gerarFatura() {
        System.out.print("ID da vaga: ");
        String idVaga = scanner.nextLine();
        System.out.print("Minutos de permanência: ");
        int minutos = scanner.nextInt();
        scanner.nextLine();

        estacionamentoController.gerarFatura(idVaga, minutos, clienteController.getClientes());
    }
}
