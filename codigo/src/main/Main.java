package main;

import controllers.ClienteController;
import controllers.VagaController;
import models.Estacionamento;
import views.EstacionamentoView;

public class Main {
    public static void main(String[] args) {
        Estacionamento estacionamento = new Estacionamento();
        ClienteController clienteController = new ClienteController();
        VagaController vagaController = new VagaController(estacionamento);

        EstacionamentoView view = new EstacionamentoView(clienteController, vagaController);
        view.exibirMenu();
    }
}
