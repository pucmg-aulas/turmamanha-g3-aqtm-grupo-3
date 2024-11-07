package controller;

import model.Customer;
import model.Parking;
import view.AddCustomerView;

import java.io.IOException;

import static javax.swing.JOptionPane.showMessageDialog;

public class AddCustomerController {
    AddCustomerView addCustomerView;

    public AddCustomerController(Parking selectedItem) {
        this.addCustomerView = new AddCustomerView();

        this.addCustomerView.cadastrarClienteButton.addActionListener(e -> {
            String name = this.addCustomerView.textField1.getText();

            try {
                Customer customer = new Customer(name, selectedItem.getNextCustomerId(), selectedItem.getId());
                selectedItem.addCustomer(customer);

                showMessageDialog(null, "Cliente adicionado com sucesso!");
                addCustomerView.dispose();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        addCustomerView.setLocationRelativeTo(null);
        addCustomerView.setVisible(true);
    }
}
