package models;

import java.time.LocalDateTime;

public abstract class Vaga {
    private String id;
    private boolean ocupada;
    private LocalDateTime horarioEntrada;
    private LocalDateTime horarioSaida;
    private Veiculo veiculo; // Corrigido o tipo de Veiculo

    public Vaga(String id) {
        this.id = id;
        this.ocupada = false;
    }

    public String getId() {
        return id;
    }

    public boolean isOcupada() {
        return ocupada;
    }

    public void ocupar(Veiculo veiculo) { // Adicionado o parâmetro Veiculo
        this.ocupada = true;
        this.horarioEntrada = LocalDateTime.now();
        this.veiculo = veiculo; // Associando o veículo à vaga
    }

    public void desocupar() {
        this.ocupada = false;
        this.horarioSaida = LocalDateTime.now();
    }

    public LocalDateTime getHorarioEntrada() {
        return horarioEntrada;
    }

    public LocalDateTime getHorarioSaida() {
        return horarioSaida;
    }

    public abstract double calcularPreco(int minutos);

    public double calcularFatura() {
        if (horarioEntrada != null && horarioSaida != null) {
            long minutos = java.time.Duration.between(horarioEntrada, horarioSaida).toMinutes();
            return calcularPreco((int) minutos);
        }
        return 0;
    }

    public Veiculo getVeiculo() { // Corrigido o método para retornar Veiculo
        return veiculo;
    }
}
