package case_study.view;

import case_study.controller.CartController;
import case_study.controller.ProductController;
import case_study.controller.UserController;
import case_study.service.CartService;
import case_study.service.ProductService;
import case_study.service.UserService;

public class Main {
    public static void main(String[] args) {
        ProductService productService;
        UserService userService;
        UserView userView;
        ProductView productView;
        CartService cartService;
        CartView cartView;
        ProductController productController;
        CartController cartController;
        UserController userController;

        userView = new UserView();
        userService = new UserService();
        productService = new ProductService(userView);
        cartService = new CartService(userView,productService);


        productView = new ProductView(productService, userView);
        cartView = new CartView(userView);

        // Khởi tạo các controller
        productController = new ProductController(userView, productView, productService, cartService);
        cartController = new CartController(productService,cartService,cartView,userView);
        userController = new UserController(userView, userService,
                productView, productService, productController,
                cartView, cartService, cartController);

        userController.start();
    }
}
