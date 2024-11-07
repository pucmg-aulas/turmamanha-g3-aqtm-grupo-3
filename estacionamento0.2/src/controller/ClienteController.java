package controller;

import model.Cliente;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class ClienteController {
    public void salvarCliente(Cliente cliente) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("Cliente.txt", true))) {
            writer.println(cliente.toString());
            System.out.println("Cliente salvo com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao salvar cliente: " + e.getMessage());
        }
    }
}
