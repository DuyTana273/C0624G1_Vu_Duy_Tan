package case_study.controller;

import case_study.service.CartService;
import case_study.service.ProductService;
import case_study.view.CartView;
import case_study.view.UserView;

public class CartController {

    //===== ĐỊNH NGHĨA THUỘC TÍNH =====
    private UserView userView;
    private ProductService productService;
    private CartService cartService;
    private CartView cartView;

    // Constructor: Nhận ProductService để quản lý giỏ hàng
    public CartController(ProductService productService, CartService cartService, CartView cartView, UserView userView) {
        this.productService = productService;
        this.cartService = cartService;
        this.cartView = cartView;
        this.userView = userView;
    }

    //===== MENU GIỎ HÀNG =====
    public void menuCart() {
        while (true) {
            cartView.showCartMenu();
            String choice = userView.getInput("Lựa chọn của bạn: ");
            switch (choice) {
                case "1":
                    cartService.displayCart();
                    break;
                case "2":
                    cartService.addProductToCart();
                    break;
                case "3":
                    cartService.updateProductQuantityInCart();
                    break;
                case "4":
                    cartService.removeProductFromCart();
                    break;
                case "5":
                    double total = cartService.getTotalCartValue();
                    System.out.println("Tổng giá trị giỏ hàng: " + total);
                    break;
                case "6":
                    cartService.clearCart();
                    break;
                case "7":
                    return;
                default:
                    userView.showMessage("Lựa chọn không hợp lệ. Vui lòng thử lại!");
            }
        }
    }
}
