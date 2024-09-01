package case_study.model.cart_manage.order;

import case_study.model.cart_manage.cart.Cart;
import case_study.model.cart_manage.cart.CartItem;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Order {
    private static int orderCounter = 0;
    private final int orderId;
    private final Map<Integer, CartItem> items;
    private final double totalValue;
    private final Date orderDate;
    private OrderStatus status;

    public Order(Cart cart) {
        this.orderId = ++orderCounter;
        this.items = new HashMap<>(cart.getItems());
        this.totalValue = cart.getTotalValue();
        this.orderDate = new Date();
        this.status = OrderStatus.PENDING;
    }

    public int getOrderId() {
        return orderId;
    }

    public Map<Integer, CartItem> getItems() {
        return items;
    }

    public double getTotalValue() {
        return totalValue;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", items=" + items +
                ", totalValue=" + totalValue +
                ", orderDate=" + orderDate +
                ", status=" + status +
                '}';
    }
}