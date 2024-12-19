package com.example.second.controller;

import com.example.second.dto.OrderDetailDto;
import com.example.second.dto.OrderDto;
import com.example.second.model.OrderModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import com.example.second.tm.ItemTM;
import com.example.second.dto.VehicleDto;
import com.example.second.model.VehicleModel;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class OrderFormController implements Initializable {

    @FXML
    private TableView<ItemTM> tblOrder;

    @FXML
    private TextField txtBrand;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtModel;

    @FXML
    private TextField txtPrice;

    @FXML
    private TextField txtQtyOnHand;

    @FXML
    private TextField txtQty;

    @FXML
    private Label lblSubTotal;

    // Change List to ObservableList
//    private ObservableList<ItemTM> itemTMS;

//    private List<ItemTM> itemTMS;

    private List<ItemTM> itemTMS = FXCollections.observableArrayList();

    private ArrayList<OrderDetailDto> orderDetailDtos = new ArrayList<>();

    private double subTotal = 0.0;



    @FXML
    void addToCart(ActionEvent event) {
        try {
            // Get the input values
            String brand = txtBrand.getText();
            String model = txtModel.getText();
            int qty = Integer.parseInt(txtQty.getText());
            double unitPrice = Double.parseDouble(txtPrice.getText());
            double total = unitPrice * qty;

            // Calculate sub-total
            subTotal += total;

            // Set sub-total at the label
            lblSubTotal.setText(String.valueOf(subTotal));

            // Add the new item to the ObservableList
            itemTMS.add(new ItemTM(brand, model,qty,unitPrice,total));

            int id = Integer.parseInt(txtId.getText());

            // Add order details to the list
            orderDetailDtos.add(new OrderDetailDto(id, qty, total));


            // Update the TableView by setting the ObservableList
            tblOrder.setItems(FXCollections.observableArrayList(itemTMS)); // Wrap itemTMS again into an ObservableList



            // Create a new ItemTM object
//            ItemTM newItem = new ItemTM(brand, model, qty, unitPrice, total);

            // Add the new item to itemTMS
//            itemTMS.add(newItem);

            // Update TableView with the new item
//            tblOrder.setItems(itemTMS);

            // Clear qty input field
            txtQty.clear();

        } catch (NumberFormatException e) {
            System.out.println("Invalid input.");
        }
    }


    @FXML
    void placeOrder(ActionEvent event) throws SQLException, ClassNotFoundException {
//        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
//        Date date = new Date();
//        String format = dateFormat.format(date);

        DateFormat mysqlDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String formattedDate = mysqlDateFormat.format(date);

        // Create an OrderDto with the current date, total cost, and order details,
        // then pass it to the OrderModel to place the order.
        try {
            boolean orderPlaced = OrderModel.placeOrder(new OrderDto(formattedDate, subTotal, orderDetailDtos));
            if (orderPlaced) {
                System.out.println("Order Placed");

                // Generate and display the bill
                System.out.println("\n************* BILL *************");
                System.out.println("Date & Time: " + formattedDate);
                System.out.println("Order Details:");
                System.out.printf("%-10s %-10s %-10s %-12s %-10s\n", "Brand", "Model", "Qty", "Unit Price", "Total"); // Format and align output

                // Iterate through each item in the itemTMS list to process its details
                for (ItemTM item : itemTMS) {
                    // Process the current item
                    System.out.printf("%-10s %-10s %-10d %-12.2f %-10.2f\n",
                            item.getBrand(),
                            item.getModel(),
                            item.getQty(),
                            item.getUnitPrice(),
                            item.getTotal());
                }

                System.out.println("---------------------------------");
                System.out.printf("Sub-Total: %.2f\n", subTotal);
                System.out.println("*********************************");

            } else {
                System.out.println("Order Not Placed");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("An error occurred while placing the order.");
        }

    }

        @FXML
    void search(ActionEvent event) {
        int id = Integer.parseInt(txtId.getText());

        VehicleDto vehicle = VehicleModel.searchVehicle(id); // Example ID

        txtBrand.setText(vehicle.getBrand());
        txtModel.setText(vehicle.getModel());
        txtQtyOnHand.setText(String.valueOf(vehicle.getQty()));
        txtPrice.setText(String.valueOf(vehicle.getPrice()));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        tblOrder.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("brand"));
        tblOrder.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("model"));
        tblOrder.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("qty"));
        tblOrder.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        tblOrder.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("total"));

        // Set the ObservableList to TableView once during initialization
        // tblOrder.setItems((ObservableList<ItemTM>) itemTMS);

        itemTMS = new ArrayList<>();

        orderDetailDtos = new ArrayList<>();
    }
}
