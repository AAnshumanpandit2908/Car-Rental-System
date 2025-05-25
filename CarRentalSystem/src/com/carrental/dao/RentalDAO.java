package com.carrental.dao;

import com.carrental.model.Rental;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object for Rental operations
 */
public class RentalDAO {
    private static final String INSERT_RENTAL = 
        "INSERT INTO rentals(user_id, car_id, rental_date, return_date, total_cost, is_returned) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String SELECT_ALL = 
        "SELECT * FROM rentals";
    private static final String SELECT_BY_USER = 
        "SELECT * FROM rentals WHERE user_id = ?";
    private static final String UPDATE_RETURN = 
        "UPDATE rentals SET is_returned = TRUE WHERE rental_id = ?";
    private static final String CALCULATE_COST = 
        "SELECT daily_rate FROM cars WHERE car_id = ?";

    public boolean addRental(Rental rental) {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(INSERT_RENTAL, Statement.RETURN_GENERATED_KEYS)) {
            
            stmt.setInt(1, rental.getUserId());
            stmt.setInt(2, rental.getCarId());
            stmt.setDate(3, rental.getRentalDate());
            stmt.setDate(4, rental.getReturnDate());
            stmt.setDouble(5, rental.getTotalCost());
            stmt.setBoolean(6, rental.isReturned());
            
            int affectedRows = stmt.executeUpdate();
            
            if (affectedRows > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        rental.setRentalId(rs.getInt(1));
                    }
                }
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public List<Rental> getAllRentals() {
        List<Rental> rentals = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(SELECT_ALL)) {
            
            while (rs.next()) {
                Rental rental = new Rental();
                rental.setRentalId(rs.getInt("rental_id"));
                rental.setUserId(rs.getInt("user_id"));
                rental.setCarId(rs.getInt("car_id"));
                rental.setRentalDate(rs.getDate("rental_date"));
                rental.setReturnDate(rs.getDate("return_date"));
                rental.setTotalCost(rs.getDouble("total_cost"));
                rental.setReturned(rs.getBoolean("is_returned"));
                rentals.add(rental);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return rentals;
    }

    public List<Rental> getRentalsByUser(int userId) {
        List<Rental> rentals = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SELECT_BY_USER)) {
            
            stmt.setInt(1, userId);
            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Rental rental = new Rental();
                    rental.setRentalId(rs.getInt("rental_id"));
                    rental.setUserId(rs.getInt("user_id"));
                    rental.setCarId(rs.getInt("car_id"));
                    rental.setRentalDate(rs.getDate("rental_date"));
                    rental.setReturnDate(rs.getDate("return_date"));
                    rental.setTotalCost(rs.getDouble("total_cost"));
                    rental.setReturned(rs.getBoolean("is_returned"));
                    rentals.add(rental);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return rentals;
    }

    public boolean returnCar(int rentalId) {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(UPDATE_RETURN)) {
            
            stmt.setInt(1, rentalId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public double calculateCost(int carId, java.sql.Date rentalDate, java.sql.Date returnDate) {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(CALCULATE_COST)) {
            
            stmt.setInt(1, carId);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    double dailyRate = rs.getDouble("daily_rate");
                    long diffInMillis = returnDate.getTime() - rentalDate.getTime();
                    long diffInDays = diffInMillis / (1000 * 60 * 60 * 24);
                    return dailyRate * diffInDays;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }
}