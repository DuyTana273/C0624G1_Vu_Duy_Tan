package case_study.view;

import case_study.controller.ProductController;
import case_study.controller.UserController;
import case_study.service.ProductService;
import case_study.service.UserService;

public class Main {
    public static void main(String[] args) {
        UserView userView = new UserView();
        UserService userService = new UserService();
        ProductService productService = new ProductService();
        CartView cartView = new CartView();
        ProductView productView = new ProductView();
        ProductController productController = new ProductController(userView, userService, productView, productService);
        UserController userController = new UserController(userView, userService, productController, productView, productService);

        userController.start();
    }
}
