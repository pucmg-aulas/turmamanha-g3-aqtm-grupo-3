package lpm.controller;

import lpm.model.Estacionamento;
import lpm.model.dao.EstacionamentoDAO;
import lpm.view.SelecionarEstacionamento;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Iterator;

public class SelecionarEstacionamenrtoController {
    private final SelecionarEstacionamento view;

    public SelecionarEstacionamenrtoController(SelecionarEstacionamento view) {
        this.view = view;
    }

    public Estacionamento carregarEstacionamento() {
        String nome = view.getComboBoxEstacionamentos().getSelectedItem().toString();

        if(nome != null) {
            Estacionamento estacionamentoAtual = new EstacionamentoDAO().lerEstacionamento(nome);

            view.exibeMensagem(nome + " lido com sucesso!\n");

            return estacionamentoAtual;
        } else {
            throw new Error("Erro: selecao invalida!");
        }
    }

    public void carregarComboBox() {
        ArrayList<String> nomesEstacionamentos;
        JComboBox auxComboBox = view.getComboBoxEstacionamentos();

        nomesEstacionamentos = new EstacionamentoDAO().lerNomesEstacionamentos();

        Iterator<String> iteratorNomes = nomesEstacionamentos.iterator();

        while(iteratorNomes.hasNext()) {
            auxComboBox.addItem(iteratorNomes.next());
        }
    }
}
