package com.example.second.dto;

import java.util.ArrayList;

public class OrderDto {

    private String orderDate;
    private double subTotal;

    // List of details for items in the order
    private ArrayList<OrderDetailDto> orderDetailDtos = new ArrayList<>();

    public OrderDto(String orderDate, double subTotal, ArrayList<OrderDetailDto> orderDetailDtos) {
        this.orderDate = orderDate;
        this.subTotal = subTotal;
        this.orderDetailDtos = orderDetailDtos;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public ArrayList<OrderDetailDto> getOrderDetailDtos() {
        return orderDetailDtos;
    }

    public void setOrderDetailDtos(ArrayList<OrderDetailDto> orderDetailDtos) {
        this.orderDetailDtos = orderDetailDtos;
    }
}


