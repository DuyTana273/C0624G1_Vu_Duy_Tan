package case_study.model.cart_manage.cart;

import case_study.model.product_manage.Laptop;

import java.util.HashMap;
import java.util.Map;

public class Cart {
    private final Map<Integer, CartItem> items;

    public Cart() {
        this.items = new HashMap<>();
    }

    public void addItem(Laptop laptop, int quantity) {
        int productId = laptop.getProductId();
        if (items.containsKey(productId)) {
            CartItem item = items.get(productId);
            item.setQuantity(item.getQuantity() + quantity);
        } else {
            items.put(productId, new CartItem(laptop, quantity));
        }
    }

    public void removeItem(int productId) {
        items.remove(productId);
    }

    public void updateQuantity(int productId, int quantity) {
        if (items.containsKey(productId)) {
            CartItem item = items.get(productId);
            item.setQuantity(quantity);
        }
    }

    public double getTotalValue() {
        return items.values().stream()
                .mapToDouble(CartItem::getTotalPrice)
                .sum();
    }

    public Map<Integer, CartItem> getItems() {
        return items;
    }

    public void clearCart() {
        items.clear();
    }

    @Override
    public String toString() {
        return "Cart{" +
                "items=" + items +
                '}';
    }
}