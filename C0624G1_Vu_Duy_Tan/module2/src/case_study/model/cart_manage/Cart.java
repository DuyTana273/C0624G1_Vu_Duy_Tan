package case_study.model.cart_manage;

import case_study.model.product_manage.Laptop;

import java.util.HashMap;
import java.util.Map;

public class Cart {

    //===== ĐỊNH NGHĨA THUỘC TÍNH =====
    private Map<Laptop, Integer> items;  // Laptop và số lượng

    //===== CONSTRUCTOR =====
    public Cart() {
        this.items = new HashMap<>();
    }

    //===== GETTER/SETTER =====
    public Map<Laptop, Integer> getItems() {
        return items;
    }

    public void setItems(Map<Laptop, Integer> items) {
        this.items = items;
    }

    //===== TÍNH TỔNG GIÁ TRỊ GIỎ HÀNG =====
    public double getTotalCartValue() {
        return items.entrySet().stream()
                .mapToDouble(entry -> entry.getKey().getPrice() * entry.getValue())
                .sum();
    }

    //===== THÊM MỤC VÀO GIỎ HÀNG =====
    public void addItem(Laptop laptop, int quantity) {
        items.put(laptop, items.getOrDefault(laptop, 0) + quantity);
    }

    //===== XOÁ MỤC KHỎI GIỎ HÀNG =====
    public void removeItem(Laptop laptop) {
        items.remove(laptop);
    }

    @Override
    public String toString() {
        return "Cart{" +
                "Items=" + items +
                ", TotalCartValue=" + getTotalCartValue() +
                '}';
    }
}
