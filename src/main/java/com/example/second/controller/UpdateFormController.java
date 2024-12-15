package com.example.second.controller;

import com.example.second.dto.VehicleDto;
import com.example.second.model.VehicleModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UpdateFormController {


    @FXML
    private TextField txtId;

    @FXML
    private TextField txtBrand;

    @FXML
    private TextField txtModel;

    @FXML
    private TextField txtQty;

    @FXML
    private TextField txtPrice;


    @FXML
    void update(ActionEvent event) {
        int id = Integer.parseInt(txtId.getText()); // Convert ID to an integer.
        String brand = txtBrand.getText(); // Get the brand as a string.
        String model = txtModel.getText(); // Get the model as a string.
        int qty = Integer.parseInt(txtQty.getText());
        double price = Double.parseDouble(txtPrice.getText()); // Convert price to double.



        boolean upd = VehicleModel.updateVehicle(new VehicleDto(id, brand, model, qty, price));


    }

    @FXML
    void delete(ActionEvent event) {
        int id = Integer.parseInt(txtId.getText()); // Get user input and convert it to an integer

        VehicleModel.deleteVehicle(id);
        txtId.clear(); // Clear the GUI field after deletion
        txtBrand.clear();
        txtModel.clear();
        txtQty.clear();
        txtPrice.clear();

    }

    @FXML
    void save(ActionEvent event) throws SQLException, ClassNotFoundException {

        // Retrieve and parse input values from the text fields.
        int id = Integer.parseInt(txtId.getText()); // Convert ID to an integer.
        String brand = txtBrand.getText(); // Get the brand as a string.
        String model = txtModel.getText(); // Get the model as a string.
        int qty = Integer.parseInt(txtQty.getText()); // Convert qty to an integer.
        double price = Double.parseDouble(txtPrice.getText()); // Convert price to double.



        // (id,brand,model,qty,price) filled data object
        boolean b = VehicleModel.saveVehicle(new VehicleDto(id, brand, model, qty, price));

        //alert
    }

    @FXML
    void cancel(ActionEvent event) {
        System.exit(0); // Terminates the program when cancel is clicked.
    }

    @FXML
    void load(ActionEvent event) {
        try {

            Stage stage = (Stage) txtBrand.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("load-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    void search(ActionEvent event) {
        int id = Integer.parseInt(txtId.getText());

        VehicleDto vehicle = VehicleModel.searchVehicle(id); // Example ID

        if (vehicle != null) {
            txtBrand.setText(vehicle.getBrand());
            txtModel.setText(vehicle.getModel());
            txtQty.setText(String.valueOf(vehicle.getQty()));
            txtPrice.setText(String.valueOf(vehicle.getPrice()));
        } else {
            System.out.println("Vehicle not found.");
        }


    }
}