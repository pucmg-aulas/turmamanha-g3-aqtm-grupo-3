package models;

import java.util.ArrayList;
import java.util.List;

public class Estacionamento {

    private List<Vaga> vagas;

    public Estacionamento() {
        this.vagas = new ArrayList<>();
    }

    // Método para adicionar uma vaga ao estacionamento
    public void adicionarVaga(Vaga vaga) {
        vagas.add(vaga);
    }

    // Método revisado para retornar todas as vagas livres de um determinado tipo
    public List<Vaga> buscarVagasLivresPorTipo(Class<? extends Vaga> tipo) {
        List<Vaga> vagasLivres = new ArrayList<>(); // Lista para armazenar vagas livres do tipo

        for (Vaga vaga : vagas) {
            // Verifica se a vaga é do tipo especificado e se está livre (não ocupada)
            if (tipo.isInstance(vaga) && !vaga.isOcupada()) {
                vagasLivres.add(vaga);
            }
        }

        return vagasLivres; // Retorna a lista de vagas livres
    }

    // Método para buscar uma vaga específica por ID
    public Vaga buscarVagaPorId(String id) {
        for (Vaga vaga : vagas) {
            if (vaga.getId().equals(id)) {
                return vaga;
            }
        }
        return null; // Retorna null se a vaga não for encontrada
    }
}
