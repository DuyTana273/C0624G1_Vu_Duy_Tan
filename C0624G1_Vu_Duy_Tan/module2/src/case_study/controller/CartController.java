package case_study.controller;

import case_study.model.product_manage.Laptop;
import case_study.service.ProductService;

public class CartController {
    private final ProductService productService;

    // Constructor: Nhận ProductService để quản lý giỏ hàng
    public CartController(ProductService productService) {
        this.productService = productService;
    }

    // Thêm sản phẩm vào giỏ hàng
    public void addItemToCart(Laptop laptop, int quantity) {
        productService.addToCart(laptop, quantity);
    }

    // Cập nhật số lượng sản phẩm trong giỏ hàng
    public void updateCartItemQuantity(int productId, int quantity) {
        productService.updateCartItemQuantity(productId, quantity);
    }

    // Xóa sản phẩm khỏi giỏ hàng
    public void removeItemFromCart(int productId) {
        productService.removeFromCart(productId);
    }

    // Hiển thị giỏ hàng
    public void showCartItems() {
        productService.getCart().getItems().forEach((productId, cartItem) -> {
            System.out.println("Product ID: " + productId);
            System.out.println(cartItem);
            System.out.println("---------------------------");
        });
    }

    // Hiển thị tổng giá trị giỏ hàng
    public void showTotalValue() {
        System.out.println("Tổng giá trị giỏ hàng: " + productService.getCartTotalValue());
    }

    // Xóa toàn bộ giỏ hàng
    public void clearCart() {
        productService.clearCart();
        System.out.println("Đã xóa toàn bộ sản phẩm trong giỏ hàng.");
    }
}
