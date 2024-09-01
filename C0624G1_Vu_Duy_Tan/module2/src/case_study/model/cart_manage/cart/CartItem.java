package case_study.model.cart_manage.cart;

import case_study.model.product_manage.Laptop;

public class CartItem {
    private final Laptop laptop;
    private int quantity;

    public CartItem(Laptop laptop, int quantity) {
        this.laptop = laptop;
        this.quantity = quantity;
    }

    public Laptop getLaptop() {
        return laptop;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalPrice() {
        return laptop.getPrice() * quantity;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "laptop=" + laptop +
                ", quantity=" + quantity +
                ", totalPrice=" + getTotalPrice() +
                '}';
    }
}
