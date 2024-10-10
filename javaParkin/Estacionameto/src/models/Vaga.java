package models;

public abstract class Vaga {
    private String id;
    private boolean ocupada;

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

    public void ocupar() {
        this.ocupada = true;
    }

    public void desocupar() {
        this.ocupada = false;
    }

    public abstract double calcularPreco(int minutos);
}
