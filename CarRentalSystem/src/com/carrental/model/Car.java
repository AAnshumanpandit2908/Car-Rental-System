package com.carrental.model;

/**
 * Represents a car in the rental system
 */
public class Car {
    private int carId;
    private String make;
    private String model;
    private int year;
    private String color;
    private String licensePlate;
    private double dailyRate;
    private boolean available;

    // Constructors
    public Car() {}

    public Car(String make, String model, int year, String color, String licensePlate, double dailyRate, boolean available) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.color = color;
        this.licensePlate = licensePlate;
        this.dailyRate = dailyRate;
        this.available = available;
    }

    // Getters and setters
    public int getCarId() { return carId; }
    public void setCarId(int carId) { this.carId = carId; }
    public String getMake() { return make; }
    public void setMake(String make) { this.make = make; }
    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }
    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }
    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }
    public String getLicensePlate() { return licensePlate; }
    public void setLicensePlate(String licensePlate) { this.licensePlate = licensePlate; }
    public double getDailyRate() { return dailyRate; }
    public void setDailyRate(double dailyRate) { this.dailyRate = dailyRate; }
    public boolean isAvailable() { return available; }
    public void setAvailable(boolean available) { this.available = available; }
}