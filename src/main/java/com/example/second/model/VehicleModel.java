package com.example.second.model;

import com.example.second.db.DBConnection;
import com.example.second.dto.VehicleDto;

import java.sql.*;
import java.util.List;

public class VehicleModel {

    public static boolean saveVehicle(VehicleDto vehicleDto) throws SQLException, ClassNotFoundException {

        try {
//            //load Driver class to ram
//            Class.forName("com.mysql.cj.jdbc.Driver");
//
//            // Establish the database connection
//            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3308/vehicle", "root", "gnwg2001");

            Connection connection = DBConnection.getDBConnection().getConnection();
//            Connection connection2 = DBConnection.getDBConnection().getConnection();

            // write sql query
            String query = "INSERT INTO car (id, brand, model, qty, price) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setObject(1, vehicleDto.getId()); // Set id
            preparedStatement.setObject(2, vehicleDto.getBrand()); // Set brand
            preparedStatement.setObject(3, vehicleDto.getModel()); // Set model
            preparedStatement.setObject(4, vehicleDto.getQty()); // Set qty
            preparedStatement.setObject(5, vehicleDto.getPrice()); // Set price


            //execute query
            int i = preparedStatement.executeUpdate();

            if (i > 0) {
                System.out.println("Insert successful");
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public static boolean loadVehicle(){
        try {
            Connection connection = DBConnection.getDBConnection().getConnection();

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException("Error loading vehicles: " + e.getMessage(), e);
        }
        return false;

    }

    public static boolean deleteVehicle(int id) {
        try {
            Connection connection = DBConnection.getDBConnection().getConnection();

            String query = "DELETE FROM car WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setObject(1, id);

            int result = preparedStatement.executeUpdate();

            if (result > 0) {
                System.out.println("Delete successful");
            } else {
                System.out.println("No record found with the given ID.");
            }

            // Close the connection and statement
            preparedStatement.close();
            connection.close();

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e); // Handle exceptions
        }
        return false;
    }

        public static boolean updateVehicle(VehicleDto vehicleDto) {
            try {
                Connection connection = DBConnection.getDBConnection().getConnection();

                String query = "UPDATE car SET brand = ?, model = ?, qty = ?, price = ? WHERE id = ?";

                PreparedStatement preparedStatement = connection.prepareStatement(query);

                preparedStatement.setObject(1, vehicleDto.getBrand()); // Set brand.
                preparedStatement.setObject(2, vehicleDto.getModel()); // Set model.
                preparedStatement.setObject(3, vehicleDto.getQty()); // Set qty.
                preparedStatement.setObject(4, vehicleDto.getPrice()); // Set price.
                preparedStatement.setObject(5, vehicleDto.getId()); // Set id for WHERE clause.


                int result = preparedStatement.executeUpdate();

                if (result > 0) {
                    System.out.println("Update successful");
                    return true;
                } else {
                    System.out.println("Update unsuccessful");
                    return false;
                }
            }
            catch (SQLException | ClassNotFoundException e) {
                System.out.println("Error updating vehicle: " + e.getMessage());
                throw new RuntimeException(e);
            }

        }

    //collection framework use because out many data
    public static List<VehicleDto> getAllVehicle() {
        return null;
    }

    public static VehicleDto searchVehicle(int id) {
        try {

            Connection connection = DBConnection.getDBConnection().getConnection();

            //write sql query
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM car WHERE id=?");
            preparedStatement.setObject(1, id); //

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return new VehicleDto(
                        resultSet.getInt("id"),
                        resultSet.getString("brand"),
                        resultSet.getString("model"),
                        resultSet.getInt("qty"),
                        resultSet.getDouble("price")

                );
            } else {
                System.out.println("No vehicle found with the given ID.");
                return null;
            }


        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


//    public static VehicleDto searchItem(int id, Connection connection) {
//        try {
//            String query = "SELECT * FROM car WHERE id = ?";
//            PreparedStatement preparedStatement = connection.prepareStatement(query);
//            preparedStatement.setInt(1, id);
//
//            ResultSet resultSet = preparedStatement.executeQuery();
//
//            if (resultSet.next()) {
//                return new VehicleDto(
//                        resultSet.getInt("id"),
//                        resultSet.getString("brand"),
//                        resultSet.getString("model"),
//                        resultSet.getInt("qty"),
//                        resultSet.getDouble("price")
//
//                );
//            } else {
//                return null;
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }

}


