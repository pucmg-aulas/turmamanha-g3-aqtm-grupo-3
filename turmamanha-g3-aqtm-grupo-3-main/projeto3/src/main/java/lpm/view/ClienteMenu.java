package lpm.view;


import lpm.model.Estacionamento;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClienteMenu extends JFrame {

    private JButton btnCadastrar;
    private JPanel panel1;
    private JButton btnListagem;
    private JLabel labelClientes;
    private JButton btnVoltar;

    public ClienteMenu(Estacionamento estacionamentoAtual) {
        labelClientes.setText( estacionamentoAtual.getNome());
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        add(panel1);

        setVisible(true);

        btnCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new RegistrarCliente(estacionamentoAtual).setVisible(true);
            }
        });

        btnListagem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); new ListarCliente(estacionamentoAtual).setVisible(true);
            }
        });

        btnVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); new MenuInicial(estacionamentoAtual).setVisible(true);
            }
        });
    }
}
