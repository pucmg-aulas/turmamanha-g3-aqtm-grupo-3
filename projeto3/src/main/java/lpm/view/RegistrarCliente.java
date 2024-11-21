package lpm.view;

import lpm.controller.RegistrarClienteController;
import lpm.model.Estacionamento;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegistrarCliente extends JFrame {

    private JPanel panel1;
    private JTextField textId;
    private JButton salvarButton;
    private JTextField textName;
    private JButton btnCancelar;
    private RegistrarClienteController controller;

    public RegistrarCliente(Estacionamento estacionamentoAtual) {
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

        add(panel1);

        controller = new RegistrarClienteController(this, estacionamentoAtual);
        salvarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.registrarCliente();
                estacionamentoAtual.gerar();
            }
        });
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); new ClienteMenu(estacionamentoAtual).setVisible(true);
            }
        });
    }

    public JTextField getTextId() {
        return textId;
    }

    public JTextField getTextName() {
        return textName;
    }
}
