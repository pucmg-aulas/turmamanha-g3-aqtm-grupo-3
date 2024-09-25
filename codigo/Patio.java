import java.util.ArrayList;
import java.util.List;

public class Patio {
    private String nome;
    private String endereco;
    private int numeroVagas;
    private List<Vaga> vagas;

    public Patio(String nome, String endereco, int numeroVagas) {
        this.nome = nome;
        this.endereco = endereco;
        this.numeroVagas = numeroVagas;
        this.vagas = new ArrayList<>();
    }

    public void entradaVaga() {

    }

    public void registrarEntrada() {

    }

}
