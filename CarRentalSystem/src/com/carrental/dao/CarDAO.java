package com.carrental.dao;

import com.carrental.model.Car;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object for Car operations
 */
public class CarDAO {
    private static final String INSERT_CAR = 
        "INSERT INTO cars(make, model, year, color, license_plate, daily_rate, available) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String SELECT_ALL = 
        "SELECT * FROM cars";
    private static final String SELECT_AVAILABLE = 
        "SELECT * FROM cars WHERE available = TRUE";
    private static final String SELECT_BY_ID = 
        "SELECT * FROM cars WHERE car_id = ?";
    private static final String UPDATE_CAR = 
        "UPDATE cars SET make=?, model=?, year=?, color=?, license_plate=?, daily_rate=?, available=? WHERE car_id=?";
    private static final String DELETE_CAR = 
        "DELETE FROM cars WHERE car_id=?";

    public boolean addCar(Car car) {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(INSERT_CAR, Statement.RETURN_GENERATED_KEYS)) {
            
            stmt.setString(1, car.getMake());
            stmt.setString(2, car.getModel());
            stmt.setInt(3, car.getYear());
            stmt.setString(4, car.getColor());
            stmt.setString(5, car.getLicensePlate());
            stmt.setDouble(6, car.getDailyRate());
            stmt.setBoolean(7, car.isAvailable());
            
            int affectedRows = stmt.executeUpdate();
            
            if (affectedRows > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        car.setCarId(rs.getInt(1));
                    }
                }
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public List<Car> getAllCars() {
        List<Car> cars = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(SELECT_ALL)) {
            
            while (rs.next()) {
                Car car = new Car();
                car.setCarId(rs.getInt("car_id"));
                car.setMake(rs.getString("make"));
                car.setModel(rs.getString("model"));
                car.setYear(rs.getInt("year"));
                car.setColor(rs.getString("color"));
                car.setLicensePlate(rs.getString("license_plate"));
                car.setDailyRate(rs.getDouble("daily_rate"));
                car.setAvailable(rs.getBoolean("available"));
                cars.add(car);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return cars;
    }

    public List<Car> getAvailableCars() {
        List<Car> cars = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(SELECT_AVAILABLE)) {
            
            while (rs.next()) {
                Car car = new Car();
                car.setCarId(rs.getInt("car_id"));
                car.setMake(rs.getString("make"));
                car.setModel(rs.getString("model"));
                car.setYear(rs.getInt("year"));
                car.setColor(rs.getString("color"));
                car.setLicensePlate(rs.getString("license_plate"));
                car.setDailyRate(rs.getDouble("daily_rate"));
                car.setAvailable(rs.getBoolean("available"));
                cars.add(car);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return cars;
    }

    public Car getCarById(int carId) {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SELECT_BY_ID)) {
            
            stmt.setInt(1, carId);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Car car = new Car();
                    car.setCarId(rs.getInt("car_id"));
                    car.setMake(rs.getString("make"));
                    car.setModel(rs.getString("model"));
                    car.setYear(rs.getInt("year"));
                    car.setColor(rs.getString("color"));
                    car.setLicensePlate(rs.getString("license_plate"));
                    car.setDailyRate(rs.getDouble("daily_rate"));
                    car.setAvailable(rs.getBoolean("available"));
                    return car;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public boolean updateCar(Car car) {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(UPDATE_CAR)) {
            
            stmt.setString(1, car.getMake());
            stmt.setString(2, car.getModel());
            stmt.setInt(3, car.getYear());
            stmt.setString(4, car.getColor());
            stmt.setString(5, car.getLicensePlate());
            stmt.setDouble(6, car.getDailyRate());
            stmt.setBoolean(7, car.isAvailable());
            stmt.setInt(8, car.getCarId());
            
            return stmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean deleteCar(int carId) {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(DELETE_CAR)) {
            
            stmt.setInt(1, carId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}