package controllers;

import models.Estacionamento;
import models.Vaga;
import java.util.List;

public class VagaController {
    private Estacionamento estacionamento;

    public VagaController(Estacionamento estacionamento) {
        this.estacionamento = estacionamento;
    }

    public List<Vaga> buscarVagasDisponiveis(Class<? extends Vaga> tipoVaga) {
        return estacionamento.buscarVagasLivresPorTipo(tipoVaga);
    }

    public void ocuparVaga(String idVaga) {
        Vaga vaga = estacionamento.buscarVagaPorId(idVaga);
        if (vaga != null && !vaga.isOcupada()) {
            vaga.ocupar();
        } else {
            System.out.println("Vaga não encontrada ou já ocupada.");
        }
    }

    public void desocuparVaga(String idVaga) {
        Vaga vaga = estacionamento.buscarVagaPorId(idVaga);
        if (vaga != null && vaga.isOcupada()) {
            vaga.desocupar();
        } else {
            System.out.println("Vaga não encontrada ou não está ocupada.");
        }
    }
}
