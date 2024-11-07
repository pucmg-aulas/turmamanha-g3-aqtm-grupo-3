package controller;

import model.Parking;
import model.ParkingSpot;
import view.CheckinView;

public class CheckinController {
    CheckinView checkinView;

    public CheckinController(Parking parking, ParkingSpot parkingSpot) {
        this.checkinView = new CheckinView(parking, parkingSpot);

        checkinView.setLocationRelativeTo(null);
        checkinView.setVisible(true);
    }

}
