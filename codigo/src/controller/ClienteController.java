package controller;

import models.Cliente;
import models.Veiculo;
import utils.ClienteUtil;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class ClienteController {

    private List<Cliente> clientes;
    private Scanner scanner;

    public ClienteController(List<Cliente> clientes, Scanner scanner) {
        this.clientes = clientes;
        this.scanner = scanner;
    }

    public void cadastrarClienteVeiculo() {
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

    public void visualizarClientes() {
        System.out.println("Clientes cadastrados:");
        for (Cliente cliente : clientes) {
            System.out.println("Cliente: " + cliente.getNome() + " (" + cliente.getIdentificador() + ")");
            System.out.println("Veículos: ");
            for (Veiculo veiculo : cliente.getVeiculos()) {
                System.out.println("- " + veiculo.getPlaca());
            }
        }
    }

    public void editarVeiculoDeCliente() {
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
                    adicionarVeiculo(cliente);
                    break;
                case 2:
                    removerVeiculo(cliente);
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

    private void adicionarVeiculo(Cliente cliente) {
        System.out.print("Placa do novo veículo: ");
        String novaPlaca = scanner.nextLine();
        Veiculo novoVeiculo = new Veiculo(novaPlaca, cliente);
        cliente.adicionarVeiculo(novoVeiculo);
        System.out.println("Veículo adicionado com sucesso!");
    }

    private void removerVeiculo(Cliente cliente) {
        System.out.print("Placa do veículo a ser removido: ");
        String placaRemover = scanner.nextLine();

        boolean removido = cliente.removerVeiculo(placaRemover);
        if (removido) {
            System.out.println("Veículo removido com sucesso!");
        } else {
            System.out.println("Veículo não encontrado.");
        }
    }

    private Cliente buscarClientePorIdentificador(String identificador) {
        for (Cliente cliente : clientes) {
            if (cliente.getIdentificador().equals(identificador)) {
                return cliente;
            }
        }
        return null;
    }

    private void salvarClientes() {
        try {
            ClienteUtil.salvarClientesNoArquivo(null, clientes);
        } catch (IOException e) {
            System.out.println("Erro ao salvar os clientes: " + e.getMessage());
        }
    }

    public List<Cliente> getClientes() {
        
        throw new UnsupportedOperationException("Unimplemented method 'getClientes'");
    }
}
