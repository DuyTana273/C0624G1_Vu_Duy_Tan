package case_study.view;

import case_study.controller.CartController;
import case_study.controller.ProductController;
import case_study.controller.UserController;
import case_study.service.ProductService;
import case_study.service.UserService;

public class Main {
    public static void main(String[] args) {
        // Khởi tạo các service
        UserService userService = new UserService();
        ProductService productService = new ProductService();

        // Khởi tạo các view
        UserView userView = new UserView();
        CartController cartController = new CartController(productService);
        CartView cartView = new CartView(cartController, productService, userView); // Truyền CartService và ProductService vào CartView
        ProductView productView = new ProductView(cartController, productService, userView); // Truyền CartService, ProductService và UserView vào ProductView

        // Khởi tạo các controller

        ProductController productController = new ProductController(userView, productView, productService); // Khởi tạo ProductController
        UserController userController = new UserController(userView, userService, productController, productView, productService); // Khởi tạo UserController

        // Bắt đầu ứng dụng
        userController.start();
    }
}
