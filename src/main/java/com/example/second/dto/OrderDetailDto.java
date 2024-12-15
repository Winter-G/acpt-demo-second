package com.example.second.dto;

public class OrderDetailDto {

    private int carId;
    private int qty;
    private double price;

    public OrderDetailDto(int carId, int qty, double price) {
        this.carId = carId;
        this.qty = qty;
        this.price = price;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
