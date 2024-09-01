package case_study.controller;

import case_study.model.cart_manage.cart.Cart;
import case_study.model.product_manage.Laptop;
import case_study.view.CartView;

public class CartController {
    private final Cart cart;
    private final CartView cartView;

    public CartController(Cart cart, CartView cartView) {
        this.cart = cart;
        this.cartView = cartView;
    }

    public void addItemToCart(Laptop laptop, int quantity) {
        cart.addItem(laptop, quantity);
        cartView.showMessage("Đã thêm sản phẩm vào giỏ hàng.");
    }

    public void removeItemFromCart(int productId) {
        cart.removeItem(productId);
        cartView.showMessage("Đã xóa sản phẩm khỏi giỏ hàng.");
    }

    public void updateItemQuantity(int productId, int quantity) {
        cart.updateQuantity(productId, quantity);
        cartView.showMessage("Đã cập nhật số lượng sản phẩm trong giỏ hàng.");
    }

    public void viewCart() {
        cartView.displayCart(cart);
    }

    public double getTotalValue() {
        return cart.getTotalValue();
    }

    public void clearCart() {
        cart.clearCart();
        cartView.showMessage("Giỏ hàng đã được làm sạch.");
    }
}
