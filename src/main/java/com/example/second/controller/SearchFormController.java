package com.example.second.controller;

import com.example.second.db.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SearchFormController {

    @FXML
    private TextField txtId;
    @FXML
    private TextField textBrand;
    @FXML
    private TextField txtModel;

    @FXML
    private TextField txtQty;

    @FXML
    private TextField txtPrice;

    @FXML
    public void search(ActionEvent event) throws ClassNotFoundException {
        String idText = txtId.getText();
        if (idText.isEmpty()) {
            return; // If the ID field is empty, do nothing
        }

        int id = Integer.parseInt(idText); // Convert the ID to integer

        // Use try-with-resources to automatically close resources
        try (Connection connection = DBConnection.getDBConnection().getConnection() ;
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM car WHERE id=?")) {

            // Set the ID parameter for the query
            preparedStatement.setInt(1, id);

            // Execute the query and get the result
            try (ResultSet resultSet = preparedStatement.executeQuery()) {

                // If a result is found, update the fields
                if (resultSet.next()) {
                    textBrand.setText(resultSet.getString("brand"));
                    txtModel.setText(resultSet.getString("model"));
                    txtQty.setText(resultSet.getString("qty"));
                    txtPrice.setText(String.valueOf(resultSet.getDouble("price")));
                } else {
                    // Clear the fields if no result is found
                    textBrand.clear();
                    txtModel.clear();
                    txtPrice.clear();
                    System.out.println("Vehicle not found.");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


