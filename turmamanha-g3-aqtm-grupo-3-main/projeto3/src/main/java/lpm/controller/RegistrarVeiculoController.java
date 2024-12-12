package lpm.controller;

import lpm.model.Cliente;
import lpm.model.Veiculo;
import lpm.model.dao.VeiculoDAO;
import lpm.view.RegistrarVeiculos;
import lpm.view.Estacionamento;

import java.util.Iterator;

public class RegistrarVeiculoController {
    RegistrarVeiculos view;
    lpm.model.Estacionamento estacionamentoAtual;

    public RegistrarVeiculoController(RegistrarVeiculos view, lpm.model.Estacionamento estacionamentoAtual) {
        this.view = view; this.estacionamentoAtual = estacionamentoAtual;
    }

    public void registrarVeiculo(String placa, String cpfCliente) {
        Veiculo veiculo = new Veiculo(placa);
        Iterator<Cliente> iteratorClientes = estacionamentoAtual.getClientes().iterator();
        Cliente auxCliente;

        while(iteratorClientes.hasNext()) {
            auxCliente = iteratorClientes.next();

            if(auxCliente.getId().equalsIgnoreCase(cpfCliente)) {
                auxCliente.addVeiculo(veiculo);
                new VeiculoDAO().cadastrarVeiculo(veiculo, cpfCliente);
                view.dispose(); new Estacionamento(estacionamentoAtual).setVisible(true);
            }
        }

    }
}
