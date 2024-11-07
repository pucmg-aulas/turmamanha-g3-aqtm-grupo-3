package view;

import dao.Parkings;
import model.Customer;
import model.Parking;

import javax.swing.*;
import java.io.IOException;

public class AddVehicleView extends JFrame {
    private JPanel vehiclePanel;
    public JTextField textField1;
    public JButton cadastrarVeículoButton;
    public JComboBox comboBox1;

    public AddVehicleView(Parking selectedItem) {
        super("Cadastrar Veículo");

        comboBox1.addItem("Selecione um cliente");
        for(Customer customer : selectedItem.getCustomers()) {
            comboBox1.addItem(customer);
        }

        comboBox1.addActionListener(e -> {
            if(comboBox1.getSelectedIndex() != 0) {
//                Parking selectedItem = (Parking) comboBox1.getSelectedItem();
//                setTable(selectedItem);
            }
        });

        setContentPane(vehiclePanel);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        pack();
        setVisible(true);
    }
}
