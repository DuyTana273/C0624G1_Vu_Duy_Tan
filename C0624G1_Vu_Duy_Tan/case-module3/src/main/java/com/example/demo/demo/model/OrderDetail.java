package com.example.demo.demo.model;

public class OrderDetail {
    private int orderDetailId;
    private String productName;
    private double productPrice;
    private int productQuantity;
    private String image;
    private double orderDetailSum;
    private int productId;



    public OrderDetail(int orderDetailId, String productName, double productPrice, int productQuantity, double orderDetailSum) {
        this.orderDetailId = orderDetailId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productQuantity = productQuantity;
        this.orderDetailSum = orderDetailSum;
    }

    public OrderDetail(int productId, String productName, String image, int productQuantity,double productPrice) {
      this.productId = productId;
      this.productName = productName;
      this.image = image;
      this.productQuantity = productQuantity;
      this.productPrice = productPrice;

    }

    public int getProductId() {
        return productId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }
    public int getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(int orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    public double getOrderDetailSum() {
        return orderDetailSum;
    }

    public void setOrderDetailSum(double orderDetailSum) {
        this.orderDetailSum = orderDetailSum;
    }

    @Override
    public String toString() {
        return "OrderDetail{" +
                "orderDetailId=" + orderDetailId +
                ", productName='" + productName + '\'' +
                ", productPrice='" + productPrice + '\'' +
                ", productQuantity='" + productQuantity + '\'' +
                ", orderDetailSum=" + orderDetailSum +
                '}';
    }
}
