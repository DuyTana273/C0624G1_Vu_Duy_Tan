package com.example.demo.demo.model;

public class Order {
    private int orderId;
    private String customerName;
    private String date;
    private String status;
    private double sum;
    private int userId;

    public Order() {}
    public Order(int orderId, int userId,String customerName, String date, String status, double sum) {
        this.orderId = orderId;
        this.userId = userId;
        this.customerName = customerName;
        this.date = date;
        this.status = status;
        this.sum = sum;
    }

    public Order(int orderId, String date, String status, int userId) {
        this.orderId = orderId;
        this.date = date;
        this.status = status;
        this.userId = userId;
    }

    public Order(double sum, String status, String date, String customerName) {
        this.sum = sum;
        this.status = status;
        this.date = date;
        this.customerName = customerName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                "userId=" + userId +
                ", customerName='" + customerName + '\'' +
                ", date='" + date + '\'' +
                ", sum=" + sum +
                '}';
    }
}

