package dao;

import model.Parking;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class Parkings extends AbstractDAO {
    public ArrayList<Parking> parkings;
    Parkings parkingsInstance;

    public Parkings() {
        parkings = new ArrayList<>();
    }

    public Parkings getInstance() throws IOException {
        if(parkingsInstance == null) {
            parkingsInstance = new Parkings();
            ArrayList<String> fileResponse = super.readFile("parkings.txt");
            for(String line : fileResponse) {
                String[] data = line.split(";");
                parkingsInstance.parkings.add(new Parking(data[1], Integer.parseInt(data[0]), Integer.parseInt(data[2]), Integer.parseInt(data[3])));
            }

        }
        return parkingsInstance;
    }

    public void addParking(Parking parking) throws IOException {
        parkings.add(parking);
        super.writeObjectInFile("parkings.txt", parking.toStringToFile());
    }

    public void removeParking(Parking parking) {
        parkings.remove(parking);
    }

    public Parking getParking(String id) {
        for(Parking parking : parkings) {
            if(Objects.equals(parking.getId(), id)) {
                return parking;
            }
        }
        return null;
    }

    public int getNextId() {
        if(parkings.isEmpty()){
            return 1;
        }
        return parkings.getLast().getId() + 1;
    }

}
