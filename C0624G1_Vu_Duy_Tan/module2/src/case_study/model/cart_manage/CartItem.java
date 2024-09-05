package case_study.model.cart_manage;

import case_study.model.product_manage.Laptop;

public class CartItem {

    //===== ĐỊNH NGHĨA THUỘC TÍNH =====
    private Laptop laptop;
    private int quantity;

    //===== CONSTRUCTOR =====
    public CartItem(Laptop laptop, int quantity) {
        this.laptop = laptop;
        this.quantity = quantity;
    }

    //===== TÍNH TỔNG GIÁ CỦA CART ITEM =====
    public double getTotalPrice() {
        return laptop.getPrice() * quantity;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "Laptop=" + laptop.getName() +
                ", Số lượng=" + quantity +
                ", Tổng giá=" + getTotalPrice() +
                '}';
    }
}
