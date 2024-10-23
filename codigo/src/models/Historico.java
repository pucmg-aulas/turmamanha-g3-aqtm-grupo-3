package models;

import java.time.LocalDateTime;

public class Historico {
    private LocalDateTime entrada;
    private LocalDateTime saida;
    private Vaga vaga;
    private Veiculo veiculo;

    public Historico(LocalDateTime entrada, LocalDateTime saida, Vaga vaga, Veiculo veiculo) {
        this.entrada = entrada;
        this.saida = saida;
        this.vaga = vaga;
        this.veiculo = veiculo;
    }

    public LocalDateTime getEntrada() {
        return entrada;
    }

    public LocalDateTime getSaida() {
        return saida;
    }

    public Vaga getVaga() {
        return vaga;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    @Override
    public String toString() {
        return "Historico{" + "entrada=" + entrada + ", saida=" + saida + ", vaga=" + vaga.getId() + ", veiculo=" + veiculo.getPlaca() + '}';
    }
}