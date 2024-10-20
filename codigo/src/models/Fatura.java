package models;

public class Fatura {
    private Vaga vaga;
    private int minutos;

    public Fatura(Vaga vaga, int minutos) {
        this.vaga = vaga;
        this.minutos = minutos;
    }

    public double calcularValor() {
        return vaga.calcularPreco(minutos);
    }
}
