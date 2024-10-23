package controllers;

import models.Cliente;
import models.Veiculo;
import utils.ClienteUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ClienteController {
    private List<Cliente> clientes;

    public ClienteController() {
        clientes = lerClientesDoArquivo("codigo/data/clientes.txt");
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void cadastrarCliente(String nome, String identificador, String placaVeiculo) {
        Cliente cliente = new Cliente(nome, identificador);
        Veiculo veiculo = new Veiculo(placaVeiculo, cliente);
        cliente.adicionarVeiculo(veiculo);
        clientes.add(cliente);
        salvarClientes();
    }

    public Cliente buscarClientePorIdentificador(String identificador) {
        return clientes.stream()
                .filter(c -> c.getIdentificador().equals(identificador))
                .findFirst()
                .orElse(null);
    }

    public void salvarClientes() {
        try {
            ClienteUtil.salvarClientesEmArquivo("codigo/data/clientes.txt", clientes);
        } catch (IOException e) {
            System.out.println("Erro ao salvar clientes: " + e.getMessage());
        }
    }

    private List<Cliente> lerClientesDoArquivo(String filePath) {
        try {
            return ClienteUtil.lerClientesDeArquivo(filePath);
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo de clientes: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}
