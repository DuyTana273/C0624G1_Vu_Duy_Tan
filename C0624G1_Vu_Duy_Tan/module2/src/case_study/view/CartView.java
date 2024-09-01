package case_study.view;

import case_study.model.cart_manage.cart.Cart;

public class CartView {
    public void displayCart(Cart cart) {
        System.out.println("Giỏ hàng của bạn:");
        cart.getItems().forEach((key, item) -> {
            System.out.println(item);
        });
        System.out.println("Tổng giá trị: " + cart.getTotalValue() + " VND");
    }

    public void showMessage(String message) {
        System.out.println(message);
    }
}
