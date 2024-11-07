package view;

import model.Customer;
import model.Parking;
import model.ParkingSpot;
import model.Vehicle;

import javax.swing.*;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class CheckinView extends JFrame {
    private JPanel checkinPanel;
    private JButton registrarEntradaButton;
    private JComboBox comboBox1;
    private JComboBox comboBox2;

    public CheckinView(Parking parking, ParkingSpot parkingSpot) {
        super("Registrar Entrada");

        comboBox1.addItem("Selecione um cliente");
        for(Customer customer : parking.getCustomers()) {
            comboBox1.addItem(customer);
        }

        if (comboBox1.getSelectedIndex() == 0) {
            comboBox2.setEnabled(false);
        }

        comboBox1.addActionListener(e -> {
            if(comboBox1.getSelectedIndex() != 0) {
                Customer selectedItem = (Customer) comboBox1.getSelectedItem();
                Customer customer = parking.getCustomerById(selectedItem.getId());
                comboBox2.removeAllItems();
                comboBox2.addItem("Selecione um veículo");
                for(Vehicle vehicle : customer.getVehicles()) {
                    comboBox2.addItem(vehicle);
                }

                comboBox2.setEnabled(true);
            }
        });

        registrarEntradaButton.addActionListener(e -> {
            if(comboBox2.getSelectedIndex() != 0) {
                Vehicle vehicle = (Vehicle) comboBox2.getSelectedItem();
                try {
                    vehicle.setCheckin(parkingSpot);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                dispose();
            }
        });

        setContentPane(checkinPanel);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        pack();
        setVisible(true);
    }
}
