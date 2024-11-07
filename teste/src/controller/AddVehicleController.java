package controller;

import model.Customer;
import model.Parking;
import model.Vehicle;
import view.AddVehicleView;

import java.io.IOException;

import static javax.swing.JOptionPane.showMessageDialog;

public class AddVehicleController {
    AddVehicleView addVehicleView;
    public AddVehicleController(Parking parking) {
        this.addVehicleView = new AddVehicleView(parking);

        this.addVehicleView.cadastrarVeículoButton.addActionListener(e -> {
            String plate = this.addVehicleView.textField1.getText();

            if (addVehicleView.comboBox1.getSelectedIndex() == 0) {
                throw new RuntimeException("Selecione um cliente");
            }

            Customer customer = (Customer) addVehicleView.comboBox1.getSelectedItem();
            Vehicle vehicle = null;
            try {
                vehicle = new Vehicle(plate, customer.getId(), parking.getId());
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

            try {
                customer.addVehicle(vehicle);
                showMessageDialog(null, "Veículo adicionado com sucesso!");
                addVehicleView.dispose();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        addVehicleView.setLocationRelativeTo(null);
        addVehicleView.setVisible(true);
    }
}
