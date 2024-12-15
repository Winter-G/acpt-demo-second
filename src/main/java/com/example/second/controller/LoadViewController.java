package com.example.second.controller;

import com.example.second.tm.VehicleTM;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.*;
import java.util.ResourceBundle;

public class LoadViewController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3308/vehicle", "root", "gnwg2001");

            PreparedStatement preparedStatement = connection.prepareStatement("select * from car");

            ResultSet resultSet = preparedStatement.executeQuery();

            ArrayList<VehicleTM> tms = new ArrayList<>();

            while(resultSet.next()){
                VehicleTM tm = new VehicleTM(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getInt(4),
                        resultSet.getDouble(5));

                tms.add(tm);
            }


            //configure fx table
            tblVehicle.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
            tblVehicle.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("brand"));
            tblVehicle.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("model"));
            tblVehicle.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("qty"));
            tblVehicle.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("price"));

            tblVehicle.setItems(FXCollections.observableArrayList(tms));

        }catch (ClassNotFoundException | SQLException e){
            throw new RuntimeException(e);
        }

    }

    @FXML
    private TableView<VehicleTM> tblVehicle;

    @FXML
    void load(ActionEvent event) {
//        try{
//            Class.forName("com.mysql.cj.jdbc.Driver");
//
//            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3308/vehicle", "root", "gnwg2001");
//
//            PreparedStatement preparedStatement = connection.prepareStatement("select * from vehicle");
//
//            ResultSet resultSet = preparedStatement.executeQuery();
//
//            ArrayList<VehicleTM> tms = new ArrayList<>();
//
//            while(resultSet.next()){
//                VehicleTM tm = new VehicleTM(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
//                        resultSet.getDouble(4));
//
//                tms.add(tm);
//            }
//
//
//            //configure fx table
//            tblVehicle.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
//            tblVehicle.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("brand"));
//            tblVehicle.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("model"));
//            tblVehicle.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("price"));
//
//            tblVehicle.setItems(FXCollections.observableArrayList(tms));
//
//        }catch (ClassNotFoundException | SQLException e){
//            throw new RuntimeException(e);
//        }
    }
}
