package lpm.view;

import lpm.controller.VagaController;
import lpm.model.Vaga;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Saida extends JFrame{
    private JLabel labelVaga;
    private JLabel labelVeiculo;
    private JLabel labelEntrada;
    private JButton btnSair;
    private JButton btnCancelar;
    private JPanel panel1;
    private final VagaController controller;

    public JLabel getLabelVeiculo() {
        return labelVeiculo;
    }

    public JLabel getLabelEntrada() {
        return labelEntrada;
    }

    public JLabel getLabelVaga() {
        return labelVaga;
    }

    public Saida(lpm.model.Estacionamento estacionamentoAtual, Vaga vaga) {
        setTitle(estacionamentoAtual.getNome());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        add(panel1);

        controller = new VagaController(this, estacionamentoAtual);
        labelVaga.setText("Vaga: " + vaga.getId());
        controller.buscarDadosUsoAtual(vaga.getId());

        btnSair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.registrarSaida(vaga.getId()); dispose(); new MenuInicial(estacionamentoAtual).setVisible(true);
            }
        });

        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Estacionamento(estacionamentoAtual).setVisible(true);
            }
        });
    }

    public void exibeMensagem(String mensagem) {
        JOptionPane.showMessageDialog(null, mensagem);
    }
}
