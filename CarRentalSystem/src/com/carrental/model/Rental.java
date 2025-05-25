package com.carrental.model;

import java.sql.Date;

/**
 * Represents a rental transaction
 */
public class Rental {
    private int rentalId;
    private int userId;
    private int carId;
    private Date rentalDate;
    private Date returnDate;
    private double totalCost;
    private boolean isReturned;

    // Constructors
    public Rental() {}

    public Rental(int userId, int carId, Date rentalDate, Date returnDate, double totalCost, boolean isReturned) {
        this.userId = userId;
        this.carId = carId;
        this.rentalDate = rentalDate;
        this.returnDate = returnDate;
        this.totalCost = totalCost;
        this.isReturned = isReturned;
    }

    // Getters and setters
    public int getRentalId() { return rentalId; }
    public void setRentalId(int rentalId) { this.rentalId = rentalId; }
    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }
    public int getCarId() { return carId; }
    public void setCarId(int carId) { this.carId = carId; }
    public Date getRentalDate() { return rentalDate; }
    public void setRentalDate(Date rentalDate) { this.rentalDate = rentalDate; }
    public Date getReturnDate() { return returnDate; }
    public void setReturnDate(Date returnDate) { this.returnDate = returnDate; }
    public double getTotalCost() { return totalCost; }
    public void setTotalCost(double totalCost) { this.totalCost = totalCost; }
    public boolean isReturned() { return isReturned; }
    public void setReturned(boolean returned) { isReturned = returned; }
}