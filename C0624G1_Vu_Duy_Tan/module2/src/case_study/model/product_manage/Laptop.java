package case_study.model.product_manage;

import java.util.concurrent.atomic.AtomicInteger;

public class Laptop {

    //===== ĐỊNH NGHĨA THUỘC TÍNH =====
    private static final AtomicInteger idCounter = new AtomicInteger();
    private final int productId;
    private final String name;
    private final String brand;
    private final double price;
    private final String specifications;

    //===== CONSTRUCTOR =====
    public Laptop(int productId, String name, String brand, double price, String specifications) {
        this.productId = idCounter.incrementAndGet();
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

    @Override
    public String toString() {
        return "Laptop{" +
                "productId=" + productId +
                ", name='" + name + '\'' +
                ", brand='" + brand + '\'' +
                ", price=" + price +
                ", specifications='" + specifications + '\'' +
                '}';
    }
}
