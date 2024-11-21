package lpm.controller;

import lpm.model.Cliente;
import lpm.model.Estacionamento;
import lpm.model.dao.VeiculoDAO;
import lpm.view.ListarCliente;

import javax.swing.table.DefaultTableModel;
import java.util.Iterator;

public class ListClientController {
    private ListarCliente view;
    private Estacionamento estacionamentoAtual;

    public ListClientController(ListarCliente view, Estacionamento estacionamentoAtual) {
        this.view = view; this.estacionamentoAtual = estacionamentoAtual;
    }

    public void popularTabela(DefaultTableModel tableModel) {
        Iterator<Cliente> iteratorClientes = estacionamentoAtual.getClientes().iterator();
        Cliente auxCliente;

        while(iteratorClientes.hasNext()) {
            auxCliente = iteratorClientes.next();

            tableModel.addRow(new Object[]{auxCliente.getNome(), auxCliente.getId()});
        }
    }

    public boolean clienteTemVeiculos(String cpfCliente) {
        return new VeiculoDAO().temVeiculos(cpfCliente);
    }
}
