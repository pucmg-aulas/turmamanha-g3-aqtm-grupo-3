package model;

import dao.AbstractDAO;

import java.io.IOException;
import java.util.ArrayList;

public class Customer extends AbstractDAO {
    private String name;
    private int id;
    private ArrayList<Vehicle> vehicles;
    private int parkingId;

    public Customer(String name, int id, int parkingId) throws IOException {
        this.name = name;
        this.id = id;
        this.parkingId = parkingId;
        this.vehicles = createVehicles();
    }

    private ArrayList<Vehicle> createVehicles() throws IOException {
        ArrayList<Vehicle> vehicles = new ArrayList<>();
        ArrayList<String> fileResponse = super.readFile("vehicles.txt");

        for (String line : fileResponse) {
            String[] data = line.split(";");
            if (Integer.parseInt(data[1]) == this.id && Integer.parseInt(data[2]) == this.parkingId) {
                vehicles.add(new Vehicle(data[0], Integer.parseInt(data[1]), Integer.parseInt(data[2])));
            }
        }

        return vehicles;
    }

    public int getId() {
        return id;
    }

    public String toStringToFile(){
        return id + ";" + name + ";" + parkingId;
    }

    public String toString(){
        return name;
    }

    public void addVehicle(Vehicle vehicle) throws IOException {
        vehicles.add(vehicle);
        super.writeObjectInFile("vehicles.txt", vehicle.toStringToFile());
    }

    public ArrayList<Vehicle> getVehicles() {
        return vehicles;
    }

//    public void addVehicle(Vehicle vehicle){
//
//    }
//
//    public Vehicle haveVehicle(String plate){
//
//    }
//
//    public int totalUses(){
//
//    }
//
//    public double collectedPerVehicle(String plate){
//
//    }
//
//    public double totalCollected(){
//
//    }
//
//    public double collectedInMonth(Int month){
//
//    }

}