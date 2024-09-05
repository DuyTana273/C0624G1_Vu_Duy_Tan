package case_study.model.product_manage;

import java.util.concurrent.atomic.AtomicInteger;

public class Laptop {

    //===== ĐỊNH NGHĨA THUỘC TÍNH =====
    private static AtomicInteger idCounter = new AtomicInteger();
    private int productId;
    private String name;
    private String brand;
    private double price;
    private String specifications;

    //===== CONSTRUCTOR thêm sản phẩm mới =====
    public Laptop(String name, String brand, double price, String specifications) {
        this.productId = idCounter.incrementAndGet();
        this.name = name;
        this.brand = brand;
        this.price = price;
        this.specifications = specifications;
    }

    //===== CONSTRUCTOR đọc từ file =====
    public Laptop(int productId, String name, String brand, double price, String specifications) {
        this.productId = productId;
        this.name = name;
        this.brand = brand;
        this.price = price;
        this.specifications = specifications;
    }

    public int getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public String getBrand() {
        return brand;
    }

    public double getPrice() {
        return price;
    }

    public String getSpecifications() {
        return specifications;
    }

    public static void setIdCounter(AtomicInteger idCounter) {
        Laptop.idCounter = idCounter;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setSpecifications(String specifications) {
        this.specifications = specifications;
    }

    @Override
    public String toString() {
        return "Laptop{" +
                "Mã số: " + productId +
                " | Tên sản phẩm:'" + name + '\'' +
                " | Thương hiệu:'" + brand + '\'' +
                " | Giá:" + price +
                " | Ghi chú:'" + specifications + '\'' +
                '}';
    }
}
