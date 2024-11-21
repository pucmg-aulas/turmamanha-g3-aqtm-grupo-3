package lpm.view;

import javax.swing.*;

import lpm.controller.SelecionarEstacionamenrtoController;

public class SelecionarEstacionamento extends JFrame {
    private JPanel panel1;
    private JButton btnCarregar;
    private JTextField textField1;
    private JComboBox comboBoxEstacionamentos;
    private JLabel labelCarregarEstacionamento;
    private final SelecionarEstacionamenrtoController controller;

    public JComboBox getComboBoxEstacionamentos() {
        return comboBoxEstacionamentos;
    }

    public void setComboBoxEstacionamentos(JComboBox comboBoxEstacionamentos) {
        this.comboBoxEstacionamentos = comboBoxEstacionamentos;
    }

    public SelecionarEstacionamento() {
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        add(panel1);

        setVisible(true);

        controller = new SelecionarEstacionamenrtoController(this);

        controller.carregarComboBox();

        btnCarregar.addActionListener(e -> {
            dispose();
            new MenuInicial(controller.carregarEstacionamento());
        });
    }

    public JTextField getTextField1() {
        return textField1;
    }

    public void exibeMensagem(String mensagem) {
        JOptionPane.showMessageDialog(null, mensagem);
    }

}
