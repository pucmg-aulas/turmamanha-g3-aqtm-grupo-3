package controller;

import dao.Parkings;
import model.Parking;
import view.AddParkingView;

import java.io.IOException;

import static javax.swing.JOptionPane.showMessageDialog;

public class AddParkingController {
    AddParkingView addParkingView;
    Parkings parkings;

    public AddParkingController() throws IOException {
        this.addParkingView = new AddParkingView();
        this.parkings = new Parkings().getInstance();

        this.addParkingView.salvarButton.addActionListener(e -> {
            String name = this.addParkingView.name.getText();
            int numberOfRows = (int) this.addParkingView.numberOfRows.getValue();
            int spotsInRow = (int) this.addParkingView.spotsInRow.getValue();

            try {
                parkings.addParking(new Parking(name, parkings.getNextId(), numberOfRows, spotsInRow));
                showMessageDialog(null, "Estacionamento adicionado com sucesso!");
                addParkingView.dispose();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

        });

        addParkingView.setLocationRelativeTo(null);
        addParkingView.setVisible(true);
    }
}
