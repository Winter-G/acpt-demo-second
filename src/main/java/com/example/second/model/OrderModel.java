package com.example.second.model;

import com.example.second.db.DBConnection;
import com.example.second.dto.OrderDetailDto;
import com.example.second.dto.OrderDto;

import java.sql.*;

public class OrderModel {

    public static boolean placeOrder(OrderDto orderDto) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getDBConnection().getConnection();

        //Disable auto commit feature
        connection.setAutoCommit(false);

        //Insert data to order table
        PreparedStatement stm1 = connection.prepareStatement("insert into orders(orderDate, amount) values(?, ?)", Statement.RETURN_GENERATED_KEYS);
        stm1.setObject(1, orderDto.getOrderDate());
        stm1.setObject(2, orderDto.getSubTotal());

        int orderSave = stm1.executeUpdate();

        if (orderSave > 0) {
            //Get order id from generated keys
            ResultSet generatedKeys = stm1.getGeneratedKeys();

            if(generatedKeys.next()) {
                int orderId = generatedKeys.getInt(1);

                //Save in order details table
                for(OrderDetailDto dto : orderDto.getOrderDetailDtos()){
                    PreparedStatement stm2 = connection.prepareStatement("insert into order_details(orderId, carId, qty, price) values(?, ?, ?, ?)");
                    stm2.setObject(1, orderId);
                    stm2.setObject(2, dto.getCarId());
                    stm2.setObject(3, dto.getQty());
                    stm2.setObject(4, dto.getPrice());

                    int orderDetailSave = stm2.executeUpdate();

                    if(orderDetailSave > 0) {
                       PreparedStatement stm3 =  connection.prepareStatement("update car set qty = qty - ? where id = ?");
                       stm3.setObject(1, dto.getQty());
                       stm3.setObject(2, dto.getCarId());

                       int carQtyUpdated = stm3.executeUpdate();

                       //If no update then
                       if(carQtyUpdated <= 0) {
                           connection.rollback();
                           connection.setAutoCommit(true);
                           return false;
                       }

                    }else{
                        connection.rollback();
                        connection.setAutoCommit(true);
                        return false;
                    }
                }

            }
            //Saves the data into the db at once
            connection.commit();
            connection.setAutoCommit(true);
            return true;

        }else {
            //If not saved an order, rollback - cache remove
            connection.rollback();
            connection.setAutoCommit(true);
            return false;
        }
    }
}
