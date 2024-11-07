package model;

import dao.AbstractDAO;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Vehicle extends AbstractDAO {
    private String placa;

    private int customerId;

    private int parkingId;

    private ArrayList<ParkingSpotUsage> parkingSpotUsages;

    public Vehicle(String placa, int customerId, int parkingId) throws IOException {
        this.placa = placa;
        this.parkingId = parkingId;
        this.customerId = customerId;
        this.parkingSpotUsages = createParkingSpotUsages();
    }

    private ArrayList<ParkingSpotUsage> createParkingSpotUsages() throws IOException {
        ArrayList<ParkingSpotUsage> parkingSpotUsages = new ArrayList<>();
        ArrayList<String> fileResponse = super.readFile("parkingSpotUsages.txt");

        for (String line : fileResponse) {
            String[] data = line.split(";");
            if (data[3].equals(String.valueOf(this.parkingId)) && data[2].equals(String.valueOf(this.customerId)) && data[4].equals(this.placa)) {
                parkingSpotUsages.add(new ParkingSpotUsage(data[0], LocalDateTime.parse(data[1]), Integer.parseInt(data[2]), Integer.parseInt(data[3]), data[4]));
            }
        }

        return parkingSpotUsages;
    }

    public String toStringToFile(){
        return placa + ";" + customerId + ";" + parkingId;
    }

    public String toString(){
        return placa;
    }

    public void setCheckin(ParkingSpot parkingSpot) throws IOException {
        LocalDateTime dateTime = LocalDateTime.now();
        ParkingSpotUsage parkingSpotUsage = new ParkingSpotUsage(parkingSpot.getId(), dateTime, customerId, parkingId, placa);
        parkingSpotUsages.add(parkingSpotUsage);
        try {
            super.writeObjectInFile("parkingSpotUsages.txt", parkingSpotUsage.toStringToFile());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        ArrayList<String> fileResponse = super.readFile("parkingSpots.txt");
        super.clearFile("parkingSpots.txt");
        for (String line : fileResponse) {
            String[] data = line.split(";");
            if (data[1].equals(parkingSpot.getId())) {
                String newLine = data[0] + ";" + data[1] + ";" + false;
                super.writeObjectInFile("parkingSpots.txt", newLine);
            } else {
                super.writeObjectInFile("parkingSpots.txt", line);
            }
        }
    }

    public void setCheckout(ParkingSpot parkingSpot, ParkingSpotUsage parkingSpotUsage) throws IOException {
        LocalDateTime dateTime = LocalDateTime.now();
        ParkingSpotUsage newParkingSpotUsage = new ParkingSpotUsage(parkingSpotUsage.parkingSpotId, parkingSpotUsage.entry, dateTime, parkingSpotUsage.customerId, parkingSpotUsage.parkingId, parkingSpotUsage.plate);
        parkingSpotUsages.add(parkingSpotUsage);

        ArrayList<String> fileResponse = super.readFile("parkingSpotUsages.txt");
        super.clearFile("parkingSpotUsages.txt");
        for (String line : fileResponse) {
            String[] data = line.split(";");
            if (data[0].equals(parkingSpotUsage.parkingSpotId) && data[1].equals(parkingSpotUsage.entry.toString()) && data[2].equals(String.valueOf(parkingSpotUsage.customerId)) && data[3].equals(String.valueOf(parkingSpotUsage.parkingId)) && data[4].equals(parkingSpotUsage.plate)) {
                super.writeObjectInFile("parkingSpotUsages.txt", newParkingSpotUsage.toStringToFile());
            } else {
                super.writeObjectInFile("parkingSpotUsages.txt", line);
            }
        }

        fileResponse = super.readFile("parkingSpots.txt");
        super.clearFile("parkingSpots.txt");
        for (String line : fileResponse) {
            String[] data = line.split(";");
            if (data[1].equals(parkingSpot.getId())) {
                String newLine = data[0] + ";" + data[1] + ";" + false;
                super.writeObjectInFile("parkingSpots.txt", newLine);
            } else {
                super.writeObjectInFile("parkingSpots.txt", line);
            }
        }
    }
//    public void estacionar(Vacancy vaga) {
//        parkingSpotUsages.add(new ParkingSpotUsage(vaga));
//    }
//
//    public double sair() {
//        ParkingSpotUsage parkingSpotUsage = parkingSpotUsages.remove(parkingSpotUsages.size() - 1);
//        double valor = parkingSpotUsage.getValor();
//        parkingSpotUsage.getVaga().desocupar();
//        return valor;
//    }
//
//    public double totalArrecadado() {
//        double valor = 0;
//        for (ParkingSpotUsage parkingSpotUsage : parkingSpotUsages) {
//            valor += parkingSpotUsage.getValor();
//        }
//        return valor;
//    }
//
//    public double arrecadadoNoMes(int mes) {
//        double valor = 0;
//        for (ParkingSpotUsage parkingSpotUsage : parkingSpotUsages) {
//            if (parkingSpotUsage.getDataEntrada().getMonth() == mes) {
//                valor += parkingSpotUsage.getValor();
//            }
//        }
//        return valor;
//    }
//
//    public int totalDeUsos() {
//        return parkingSpotUsages.size();
//    }
//
//    public String getPlaca() {
//        return placa;
//    }
//
//    public void setPlaca(String placa) {
//        this.placa = placa;
//    }
}
