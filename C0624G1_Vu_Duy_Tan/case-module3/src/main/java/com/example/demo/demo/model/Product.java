package com.example.demo.demo.model;

public class Product {
    private int productId;
    private String name;
    private String description;
    private String brand;
    private double price;
    private int stock;
    private String image;

    public Product(){}
    public Product(int productId, String name, String description, String brand, double price, int stock, String image) {
        this.productId = productId;
        this.name = name;
        this.description = description;
        this.brand = brand;
        this.price = price;
        this.stock = stock;
        this.image = image;
    }

    public Product(int productId, String name, String description, String brand, double price, int stock) {
        this.productId = productId;
        this.name = name;
        this.description = description;
        this.brand = brand;
        this.price = price;
        this.stock = stock;
    }

    public Product(String name, String description, String brand, double price, int stock) {
        this.name = name;
        this.description = description;
        this.brand = brand;
        this.price = price;
        this.stock = stock;
    }

    public Product(String name, String description, String brand, double price, int stock, String image) {
        this.name = name;
        this.description = description;
        this.brand = brand;
        this.price = price;
        this.stock = stock;
        this.image = image;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
