package case_study.controller;

import case_study.model.cart_manage.cart.Cart;
import case_study.model.cart_manage.order.Order;
import case_study.model.cart_manage.order.OrderStatus;
import case_study.view.OrderView;

public class OrderController {
    private Order order;
    private OrderView orderView;

    public OrderController(OrderView orderView) {
        this.orderView = orderView;
    }

    public void createOrder(Cart cart) {
        this.order = new Order(cart);
        orderView.displayOrder(order);
    }

    public void updateOrderStatus(OrderStatus status) {
        if (order != null) {
            order.setStatus(status);
            orderView.showMessage("Trạng thái đơn hàng đã được cập nhật: " + status);
        }
    }
}
