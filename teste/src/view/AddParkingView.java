package view;

import javax.swing.*;
import java.awt.*;

public class AddParkingView extends JFrame{
    public JPanel addParkingPanel;
    public JTextField name;
    public JSpinner numberOfRows;
    public JSpinner spotsInRow;
    public JButton salvarButton;

    public AddParkingView() {
        super("Cadastrar Estacionamento");
        setContentPane(addParkingPanel);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        pack();
        setVisible(true);
    }
}
