package case_study.view;

import case_study.model.cart_manage.order.Order;

public class OrderView {
    public void displayOrder(Order order) {
        System.out.println("Thông tin đơn hàng:");
        System.out.println(order);
    }

    public void showMessage(String message) {
        System.out.println(message);
    }
}
