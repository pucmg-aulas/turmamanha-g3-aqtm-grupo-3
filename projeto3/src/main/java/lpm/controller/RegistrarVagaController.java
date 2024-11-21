package lpm.controller;

import lpm.model.Estacionamento;
import lpm.model.dao.UsoDeVagaDAO;
import lpm.model.dao.VagaDAO;
import lpm.model.dao.VeiculoDAO;
import lpm.view.RegistrarVaga;
import lpm.view.RegistrarVeiculos;

import javax.swing.*;

public class RegistrarVagaController {
    private RegistrarVaga view;
    private Estacionamento estacionamentoAtual;

    public RegistrarVagaController(RegistrarVaga view, Estacionamento estacionamentoAtual) {
        this.view = view;
        this.estacionamentoAtual = estacionamentoAtual;
    }

    public boolean registrar(String placa, String idVaga) {
        if(new VeiculoDAO().veiculoRegistrado(placa)) {
            if (!new UsoDeVagaDAO().estaEstacionado(placa)) {
                new UsoDeVagaDAO().cadastrarUsoDeVaga(estacionamentoAtual.estacionar(placa, idVaga), estacionamentoAtual.getNome(), placa);
                new VagaDAO().atualizarEstadoVaga(idVaga, estacionamentoAtual.getNome());
                return true;
            } else {
                view.exibeMensagem("Erro: Veículo já se encontra estacionado. Tente novamente!");
                view.getTextFieldPlaca().setText("");
                return false;
            }
        } else {
            if(view.exibeDialogo("Placa não encontrada na base de dados. Deseja registrar um novo veículo?") == JOptionPane.YES_OPTION) {
                new RegistrarVeiculos(estacionamentoAtual, placa).setVisible(true);
            }
            return false;
        }
    }
}
