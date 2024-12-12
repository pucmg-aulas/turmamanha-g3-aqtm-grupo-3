package lpm.controller;

import lpm.model.Estacionamento;
import lpm.model.dao.UsoDeVagaDAO;
import lpm.model.dao.VagaDAO;
import lpm.view.Saida;

import java.time.LocalDateTime;

public class VagaController {
    Saida view;
    Estacionamento estacionamentoAtual;

    public VagaController(Saida view, Estacionamento estacionamentoAtual) {
        this.view = view; this.estacionamentoAtual = estacionamentoAtual;
    }

    public void buscarDadosUsoAtual(String idVaga) {
        String[] dados = new UsoDeVagaDAO().consultarInfoUsoAtual(idVaga, estacionamentoAtual.getNome());

        view.getLabelVeiculo().setText(dados[0]);
        view.getLabelEntrada().setText(dados[1]);
    }

    public void registrarSaida(String idVaga) {
        double aPagar;
        aPagar = estacionamentoAtual.procurarVeiculo(view.getLabelVeiculo().getText()).sair();

        // update nas tabelas
        new VagaDAO().atualizarEstadoVaga(idVaga, estacionamentoAtual.getNome());
        new UsoDeVagaDAO().registrarSaida(view.getLabelVeiculo().getText(), view.getLabelEntrada().getText(), LocalDateTime.now().toString());

        view.exibeMensagem("Vaga liberada! Faça a cobrança de R$" + aPagar + " ao cliente");
    }
}
