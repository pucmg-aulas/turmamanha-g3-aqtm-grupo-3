package model;

public class Vaga {
    private String id;
    private String tipo;
    private boolean ocupada;

    public Vaga(String id, String tipo, boolean ocupada) {
        this.id = id;
        this.tipo = tipo;
        this.ocupada = ocupada;
    }

    public String getId() {
        return id;
    }

    public String getTipo() {
        return tipo;
    }

    public boolean isOcupada() {
        return ocupada;
    }

    // Adicionando o método setOcupada para atualizar o status de ocupação
    public void setOcupada(boolean ocupada) {
        this.ocupada = ocupada;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Tipo: " + tipo + ", Ocupada: " + ocupada;
    }
}
