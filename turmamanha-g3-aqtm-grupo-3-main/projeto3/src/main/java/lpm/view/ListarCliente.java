package lpm.view;

import lpm.controller.ListClientController;
import lpm.model.Estacionamento;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ListarCliente extends JFrame {
    private JPanel panel1;
    private JTable tableClientes;
    private JLabel labelTituloJanela;
    private JButton btnVoltar;
    private JButton btnVeiculos;
    private final ListClientController controller;

    public ListarCliente(Estacionamento estacionamentoAtual) {
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.controller = new ListClientController(this, estacionamentoAtual);

        // Cria o DefaultTableModel
        DefaultTableModel tableModel = new DefaultTableModel();

        // Adiciona as colunas à tabela
        tableModel.addColumn("Nome");
        tableModel.addColumn("ID");

        // Adiciona os dados à tabela
        controller.popularTabela(tableModel);

        // Cria a JTable com o DefaultTableModel
        tableClientes = new JTable(tableModel);
        tableClientes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Define o botao de veiculos do cliente como nao ativado
        btnVeiculos.setEnabled(false);

        // Cria o listener das linhas da tabela
        tableClientes.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            int counter = 0; // counter auxiliar para ajudar a remover o buttonListener na hora correta
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(!e.getValueIsAdjusting()) {
                    int selectedRow = tableClientes.getSelectedRow();

                    if(counter >= 1 && btnVeiculos.getActionListeners().length != 0) { // testa se e' a segunda linha selecionada sem acoes e se existem listeners
                        btnVeiculos.removeActionListener(btnVeiculos.getActionListeners()[0]); // remove o ultimo action listener
                    }

                    if(selectedRow != -1) {
                        // Ativando o botao
                        btnVeiculos.setEnabled(true);
                        // Chamando a view ListVehicles para trazer os veiculos daquele cliente
                        btnVeiculos.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                String cpfCliente = (String) tableClientes.getValueAt(selectedRow, 1);
                                if(controller.clienteTemVeiculos(cpfCliente)) {
                                    dispose();
                                    new ListarVeiculos(estacionamentoAtual, cpfCliente).setVisible(true);
                                    btnVeiculos.removeActionListener(this);
                                } else {
                                    exibeMensagem("O cliente selecionado não possui veículos!");
                                    btnVeiculos.removeActionListener(this);
                                }
                            }
                        });
                    } else {
                        btnVeiculos.setEnabled(false);
                    }
                    counter++;
                }
            }
        });

        // Adiciona um JScrollPane para a tabela
        JScrollPane scrollPane = new JScrollPane(tableClientes);
        panel1.setLayout(new BorderLayout());
        panel1.add(btnVoltar, BorderLayout.BEFORE_FIRST_LINE);
        panel1.add(labelTituloJanela, BorderLayout.NORTH);
        panel1.add(scrollPane, BorderLayout.CENTER);
        panel1.add(btnVeiculos, BorderLayout.AFTER_LAST_LINE);

        add(panel1);
        btnVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); new ClienteMenu(estacionamentoAtual).setVisible(true);
            }
        });
    }

    public void exibeMensagem(String mensagem) {
        JOptionPane.showMessageDialog(null, mensagem);
    }
}

