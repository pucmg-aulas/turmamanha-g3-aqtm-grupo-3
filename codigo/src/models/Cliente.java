package models;

import java.util.ArrayList;
import java.util.List;

public class Cliente {
    private String nome;
    private String identificador;
    private List<Veiculo> veiculos;

    public Cliente(String nome, String identificador) {
        this.nome = nome;
        this.identificador = identificador;
        this.veiculos = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public String getIdentificador() {
        return identificador;
    }

    public List<Veiculo> getVeiculos() {
        return veiculos;
    }

    public void adicionarVeiculo(Veiculo veiculo) {
        veiculos.add(veiculo);
    }
}
