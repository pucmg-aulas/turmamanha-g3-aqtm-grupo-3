package model;

import dao.AbstractDAO;

public class ParkingSpot extends AbstractDAO {
    private int parkingId;
    private String id;
    private boolean available;

    public ParkingSpot(int row, int number, int parkingId, boolean available) {
        this.parkingId = parkingId;
        this.id = getRow(row) + number;
        this.available = available;

        try {
            super.writeObjectInFile("parkingSpots.txt", this.toStringToFile());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public ParkingSpot(String id, int parkingId, boolean available) {
        this.parkingId = parkingId;
        this.id = id;
        this.available = available;
    }

    private String getRow(int row) {
        return String.valueOf((char) (65 + row));
    }

    public boolean Park() {
        if (available) {
            available = false;
            return true;
        } else {
            return false;
        }
    }

    public boolean Leave() {
        if (!available) {
            available = true;
            return true;
        } else {
            return false;
        }
    }

    public boolean isAvailable() {
        return available;
    }

    public String getId() {
        return id;
    }

    public String toStringToFile() {
        return parkingId + ";" + id + ";" + available;
    }
}
