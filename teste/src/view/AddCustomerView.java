package view;

import javax.swing.*;

public class AddCustomerView extends JFrame {
    public JTextField textField1;
    public JButton cadastrarClienteButton;
    private JPanel customerPanel;

    public AddCustomerView() {
        super("Cadastrar Cliente");
        setContentPane(customerPanel);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        pack();
        setVisible(true);
    }
}
