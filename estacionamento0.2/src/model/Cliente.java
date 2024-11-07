package model;

import java.util.ArrayList;
import java.util.List;

public class Cliente {
    private String id;
    private String nome;
    private List<String> veiculos;

    public Cliente(String id, String nome) {
        this.id = id;
        this.nome = nome;
        this.veiculos = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public List<String> getVeiculos() {
        return veiculos;
    }

    public void adicionarVeiculo(String placa) {
        veiculos.add(placa);
    }

    @Override
    public String toString() {
        return "ID: " + id + " Nome: " + nome + " Veículos: " + String.join(", ", veiculos);
    }
}
