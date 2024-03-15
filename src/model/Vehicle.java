package model;

public abstract class Vehicle {
    private final String licensePlate;
    private final Double dailyRentCost;
    private final String carModel;

    public Vehicle(String licensePlate, Double dailyRentCost, String carModel) {
        this.licensePlate = licensePlate;
        this.dailyRentCost = dailyRentCost;
        this.carModel = carModel;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public Double getDailyRentCost(){
        return dailyRentCost;
    }

    public String getCarModel() {
        return carModel;
    }
    
}
