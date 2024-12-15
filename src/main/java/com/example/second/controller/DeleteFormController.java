package com.example.second.controller;

import com.example.second.model.VehicleModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class DeleteFormController {

    @FXML
    private TextField enterID;

    public void delete(ActionEvent event) {
        try {
            int id = Integer.parseInt(enterID.getText());
            if (VehicleModel.deleteVehicle(id)) {
                System.out.println("Vehicle deleted successfully.");
            } else {
                System.out.println("Vehicle not found.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID format.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

