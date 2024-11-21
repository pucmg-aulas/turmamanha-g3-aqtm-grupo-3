package lpm.controller;

import lpm.model.Vaga;
import lpm.view.Estacionamento;

import java.util.ArrayList;

public class EstacionamentoController {
    private Estacionamento view;
    private lpm.model.Estacionamento estacionamento;

    public EstacionamentoController(Estacionamento view, lpm.model.Estacionamento estacionamento) {this.view = view; this.estacionamento = estacionamento;}

    public ArrayList<Vaga> getVagas() {return estacionamento.getVagas();}

}
