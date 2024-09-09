package case_study.view;

import case_study.controller.CartController;
import case_study.controller.LaptopController;
import case_study.controller.UserController;
import case_study.service.CartService;
import case_study.service.LaptopService;
import case_study.service.UserService;

public class Main {
    public static void main(String[] args) {
        LaptopService laptopService;
        UserService userService;
        UserView userView;
        LaptopView laptopView;
        CartService cartService;
        CartView cartView;
        LaptopController laptopController;
        CartController cartController;
        UserController userController;

        userView = new UserView();
        userService = new UserService();
        cartService = new CartService();
        laptopService = new LaptopService(userView, cartService);


        laptopView = new LaptopView(userView, laptopService);
        cartView = new CartView(userView);

        // Khởi tạo các controller
        laptopController = new LaptopController(laptopView, laptopService, userView);
        cartController = new CartController(cartService, cartView, userView, laptopService);
        userController = new UserController(userView, userService, laptopController, cartService, cartController);

        userController.start();
    }
}
