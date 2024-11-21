package lpm.controller;

import lpm.model.Cliente;
import lpm.model.Estacionamento;
import lpm.model.Veiculo;
import lpm.view.RegistrarCliente;

import java.util.ArrayList;

public class RegistrarClienteController {
    private RegistrarCliente view;
    private Estacionamento estacionamentoAtual;

    public RegistrarClienteController(RegistrarCliente view, Estacionamento estacionamentoAtual) {
        this.view = view; this.estacionamentoAtual = estacionamentoAtual;
    }

    public void registrarCliente() {
        estacionamentoAtual.addCliente(new Cliente(view.getTextId().getText(), view.getTextName().getText(), new ArrayList<Veiculo>()));
    }
}
