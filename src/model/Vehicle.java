package model;

public abstract class Vehicle {
    private final String licensePlate;
    private final Double dailyRentCost;

    public Vehicle(String licensePlate, Double dailyRentCost) {
        this.licensePlate = licensePlate;
        this.dailyRentCost = dailyRentCost;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public Double getDailyRentCost(){
        return dailyRentCost;
    }
}
