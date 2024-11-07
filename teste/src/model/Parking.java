package model;

import dao.AbstractDAO;

import java.io.IOException;
import java.util.ArrayList;

public class Parking extends AbstractDAO {
    final String name;
    final int id;
    final ArrayList<Customer> customers;
    public final ArrayList<ParkingSpot> parkingSpots;
    final int numberOfRows;
    final int spotOfRow;

    public Parking(String name, int id, int numberOfRows, int spotOfRow) throws IOException {
        this.name = name;
        this.id = id;
        this.customers = createCustomers();
        this.parkingSpots = createParkingSpots(numberOfRows, spotOfRow);
        this.numberOfRows = numberOfRows;
        this.spotOfRow = spotOfRow;
    }

    private ArrayList<Customer> createCustomers() throws IOException {
        ArrayList<Customer> customers = new ArrayList<>();
        ArrayList<String> fileResponse = super.readFile("customers.txt");

        for (String line : fileResponse) {
            String[] data = line.split(";");
            if (Integer.parseInt(data[2]) == this.id) {
                customers.add(new Customer(data[1], Integer.parseInt(data[0]), Integer.parseInt(data[2])));
            }
        }

        return customers;
    }

    private ArrayList<ParkingSpot> createParkingSpots(int numberOfRows, int spotOfRow) throws IOException {
        ArrayList<ParkingSpot> parkingSpots = new ArrayList<>();
        ArrayList<String> fileResponse = super.readFile("parkingSpots.txt");

        for (String line : fileResponse) {
            String[] data = line.split(";");
            if (Integer.parseInt(data[0]) == this.id) {
                parkingSpots.add(new ParkingSpot(data[1], Integer.parseInt(data[0]), Boolean.parseBoolean(data[2])));
            }
        }

        if (parkingSpots.isEmpty()) {
            for (int i = 0; i < numberOfRows; i++) {
                for (int j = 0; j < spotOfRow; j++) {
                    ParkingSpot parkingSpot = new ParkingSpot(i, j, this.id, true);
                    parkingSpots.add(parkingSpot);
                }
            }
        }

        return parkingSpots;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public String toStringToFile() {
        return id + ";" + name + ";" + numberOfRows + ";" + spotOfRow;
    }

    public String toString() {
        return name;
    }

    public int getNumberOfRows() {
        return numberOfRows;
    }

    public int getSpotsInRow() {
        return spotOfRow;
    }

    public int getNextCustomerId() {
        if(customers.isEmpty()){
            return 1;
        }
        return customers.getLast().getId() + 1;
    }

    public void addCustomer(Customer customer) throws IOException {
        customers.add(customer);
        super.writeObjectInFile("customers.txt", customer.toStringToFile());
    }

    public ArrayList<Customer> getCustomers() {
        return customers;
    }

    public ParkingSpot getParkingSpot(String id) {
        for(ParkingSpot parkingSpot : parkingSpots) {
            if(parkingSpot.getId().equals(id)) {
                return parkingSpot;
            }
        }
        return null;
    }

    public Customer getCustomerById(int id) {
        for(Customer customer : customers) {
            if(customer.getId() == id) {
                return customer;
            }
        }
        return null;
    }
}