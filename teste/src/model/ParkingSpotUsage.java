package model;

import java.time.LocalDateTime;

public class ParkingSpotUsage {
    private static final double FRACTION_USE = 0.25;
    private static final double VALUE_FRACTION = 4.0;
    private static final double VALUE_MAXIMUM = 50.0;

    public String parkingSpotId;
    public LocalDateTime entry;
    public LocalDateTime exit;
    public double amountPaid;
    public int customerId;
    public int parkingId;
    public String plate;

    public ParkingSpotUsage(String parkingSpotId, LocalDateTime entry, int customerId, int parkingId, String plate) {
        this.parkingSpotId = parkingSpotId;
        this.entry = entry;
        this.customerId = customerId;
        this.parkingId = parkingId;
        this.plate = plate;
    }

    public ParkingSpotUsage(String parkingSpotId, LocalDateTime entry, LocalDateTime exit, int customerId, int parkingId, String plate) {
        this.parkingSpotId = parkingSpotId;
        this.entry = entry;
        this.exit = exit;
        this.customerId = customerId;
        this.parkingId = parkingId;
        this.plate = plate;
        this.amountPaid = calculateAmountPaid();
    }

    private double calculateAmountPaid() {
        double amountPaid = 0.0;
        double hours = entry.until(exit, java.time.temporal.ChronoUnit.HOURS);
        double minutes = entry.until(exit, java.time.temporal.ChronoUnit.MINUTES);
        double fraction = minutes % 60 / 60;

        if(hours < 1) {
            amountPaid = VALUE_FRACTION;
        } else {
            amountPaid = hours * VALUE_FRACTION;
            if(fraction > FRACTION_USE) {
                amountPaid += VALUE_FRACTION;
            }
        }

        if(amountPaid > VALUE_MAXIMUM) {
            amountPaid = VALUE_MAXIMUM;
        }

        return amountPaid;
    }

    public double exit() {
        return amountPaid;
    }

    public double getAmountPaid() {
        return amountPaid;
    }

    public String toStringToFile() {
        String date = entry.toString();
        return parkingSpotId + ";" + date + ";" + customerId + ";" + parkingId + ";" + plate + ";" + exit + ";" + amountPaid;
    }
}