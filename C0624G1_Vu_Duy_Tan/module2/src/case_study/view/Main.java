package case_study.view;

import case_study.controller.UserController;
import case_study.model.UserService;

public class Main {
    public static void main(String[] args) {
        UserView userView = new UserView();
        UserService userService = new UserService();
        UserController userController = new UserController(userView, userService);

        userController.start();
    }
}
