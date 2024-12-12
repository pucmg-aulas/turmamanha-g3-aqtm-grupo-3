package lpm.view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuInicial extends JFrame {
    private JPanel panel1;
    private JButton visaoGeralDoEstacionamentoButton;
    private JButton estatisticasButton;
    private JButton clientesButton;

    public MenuInicial(lpm.model.Estacionamento estacionamentoAtual) {
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        add(panel1);

        visaoGeralDoEstacionamentoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Estacionamento(estacionamentoAtual).setVisible(true);
            }
        });
        clientesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new ClienteMenu(estacionamentoAtual).setVisible(true);
            }
        });

        setVisible(true);
        estatisticasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); new Faturamento(estacionamentoAtual).setVisible(true);
            }
        });
    }
}
