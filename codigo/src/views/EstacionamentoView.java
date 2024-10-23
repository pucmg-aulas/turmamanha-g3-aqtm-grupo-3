package views;

import controllers.ClienteController;
import controllers.VagaController;
import models.Estacionamento;
import models.Vaga;

import java.util.List;
import java.util.Scanner;

import models.VagaRegular;
import models.VagaIdoso;
import models.VagaVIP;
import models.VagaPCD;


public class EstacionamentoView {
    private Scanner scanner;
    private ClienteController clienteController;
    private VagaController vagaController;

    public EstacionamentoView(ClienteController clienteController, VagaController vagaController) {
        this.scanner = new Scanner(System.in);
        this.clienteController = clienteController;
        this.vagaController = vagaController;
    }

    public void exibirMenu() {
        while (true) {
            System.out.println("Gestão de Estacionamento");
            System.out.println("1. Cadastrar cliente e veículo");
            System.out.println("2. Visualizar vagas disponíveis");
            System.out.println("3. Ocupar vaga");
            System.out.println("4. Sair");
            System.out.print("Escolha uma opção: ");
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
                    clienteController.salvarClientes();
                    System.out.println("Saindo...");
                    return;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    private void cadastrarClienteVeiculo() {
        System.out.print("Nome do cliente: ");
        String nome = scanner.nextLine();
        System.out.print("Identificador do cliente: ");
        String identificador = scanner.nextLine();
        System.out.print("Placa do veículo: ");
        String placa = scanner.nextLine();
        clienteController.cadastrarCliente(nome, identificador, placa);
        System.out.println("Cliente e veículo cadastrados com sucesso!");
    }

    private void exibirVagasDisponiveis() {
        System.out.println("Escolha o tipo de vaga: ");
        System.out.println("1. Regular");
        System.out.println("2. Idoso");
        System.out.println("3. VIP");
        System.out.println("4. PCD");
        int tipoVaga = scanner.nextInt();
        scanner.nextLine();

        List<Vaga> vagasLivres = vagaController.buscarVagasDisponiveis(getTipoVaga(tipoVaga));

        if (!vagasLivres.isEmpty()) {
            System.out.println("Vagas disponíveis:");
            for (Vaga vaga : vagasLivres) {
                System.out.println("- Vaga: " + vaga.getId());
            }
        } else {
            System.out.println("Não há vagas disponíveis desse tipo no momento.");
        }
    }

    private void ocuparVaga() {
        System.out.print("ID da vaga: ");
        String idVaga = scanner.nextLine();
        vagaController.ocuparVaga(idVaga);
    }

    private Class<? extends Vaga> getTipoVaga(int tipoVaga) {
        switch (tipoVaga) {
            case 1: return VagaRegular.class;
            case 2: return VagaIdoso.class;
            case 3: return VagaVIP.class;
            case 4: return VagaPCD.class;
            default: throw new IllegalArgumentException("Tipo de vaga inválido.");
        }
    }
}
