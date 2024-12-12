package lpm.controller;

import lpm.model.Estacionamento;
import lpm.model.UsoDeVaga;
import lpm.model.Veiculo;
import lpm.model.dao.VeiculoDAO;
import lpm.view.ListarVeiculos;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.Iterator;

public class ListaVeiculosController {
    private Estacionamento estacionamentoAtual;
    private ListarVeiculos view;
    public ListaVeiculosController(ListarVeiculos view, Estacionamento estacionamentoAtual) {
        this.view = view; this.estacionamentoAtual = estacionamentoAtual;
    }

    public void popularTabela(DefaultTableModel tableModel, String cpfCliente) {
        Veiculo auxVeiculo;
        ArrayList<Veiculo> auxVeiculos;
        ArrayList<UsoDeVaga> auxUsosVeiculo;
        UsoDeVaga auxUltimoUso;

        auxVeiculos = new VeiculoDAO().lerVeiculos(cpfCliente, estacionamentoAtual.getNome());

        Iterator<Veiculo> iteratorVeiculos = auxVeiculos.iterator();

        while (iteratorVeiculos.hasNext()) {
            auxVeiculo = iteratorVeiculos.next();
             auxUsosVeiculo = auxVeiculo.getUsos();
             if (!auxUsosVeiculo.isEmpty()) {
                 auxUltimoUso = auxUsosVeiculo.get(auxUsosVeiculo.size() - 1);
                 if (auxUltimoUso.getSaida() == null) {
                     tableModel.addRow(new Object[]{auxVeiculo.getPlaca(), auxUltimoUso.getVaga().getId() + ", " + estacionamentoAtual.getNome()});
                 } else {
                     tableModel.addRow(new Object[]{auxVeiculo.getPlaca(), "-"});
                 }
             } else {
                 tableModel.addRow(new Object[]{auxVeiculo.getPlaca(), "-"});
             }
        }
    }
}
