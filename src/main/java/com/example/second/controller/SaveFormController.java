package com.example.second.controller;

import com.example.second.dto.VehicleDto;
import com.example.second.model.VehicleModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SaveFormController {

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



    // Event handler for the "Cancel" button.
    @FXML
    void cancel(ActionEvent event) {
        System.exit(0); // Terminates the program when cancel is clicked.
    }

    @FXML
    public void save(ActionEvent event) throws SQLException, ClassNotFoundException {

//        int id = Integer.parseInt(txtId.getText());
        String brand = txtBrand.getText();
        String model = txtModel.getText();
        int qty = Integer.parseInt(txtQty.getText());
        double price = Double.valueOf(txtPrice.getText());


        boolean b = VehicleModel.saveVehicle(new VehicleDto(0, brand, model, qty, price));




        }
}







