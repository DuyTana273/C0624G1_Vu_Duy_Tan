package case_study.controller;

import case_study.model.product_manage.Laptop;
import case_study.service.ProductService;
import case_study.service.UserService;
import case_study.view.ProductView;
import case_study.view.UserView;

import java.util.List;

public class ProductController {

    //===== ĐỊNH NGHĨA THUỘC TÍNH =====
    private final UserView userView;
    private final ProductView productView;
    private final ProductService productService;
    String choice;

    //===== CONSTRUCTOR =====
    public ProductController(UserView userView, UserService userService, ProductView productView, ProductService productService) {
        this.userView = userView;
        this.productView = productView;
        this.productService = productService;
    }

    //===== SHOW MENU CATEGORY LAPTOPS =====
    public void showCategoryLaptops() {
        while (true) {
            productView.showCategory();
            choice = userView.getInput("Lựa chọn của bạn: ");
            switch (choice) {
                case "1":
                    while (true) {
                        productView.showCategoryLaptopsWindow();
                        String subChoice = userView.getInput("Lựa chọn của bạn: ");
                        switch (subChoice) {
                            case "1":
                                showCategoryLaptopsWindowGaming();
                                break;
                            case "2":
                                showCategoryLaptopsWindowOffice();
                                break;
                            case "3":
                                break;
                            default:
                                userView.showMessage("Lựa chọn không hợp lệ. Vui lòng thử lại!");
                        }
                        if (subChoice.equals("3")) {
                            break;
                        }
                    }
                    break;
                case "2":
                    showCategoryLaptopsMac();
                    break;
                case "3":
                    productService.displayAllProducts();
                    break;
                case "4":
                    break;
                default:
                    userView.showMessage("Lựa chọn không hợp lệ. Vui lòng thử lại!");
            }
        }
    }

    //===== SHOW MENU CATEGORY LAPTOPS WINDOW GAMING =====
    public void showCategoryLaptopsWindowGaming() {
        while (true) {
            productView.showCategoryLaptopsWindowGaming();
            choice = productView.getInput("Lựa chọn của bạn: ");
            switch (choice) {
                case "1":
                    productView.displayLaptopsByBrand("ASUS");
                    break;
                case "2":
                    productView.displayLaptopsByBrand("ACER");
                    break;
                case "3":
                    productView.displayLaptopsByBrand("DELL");
                    break;
                case "4":
                    productView.displayLaptopsByBrand("HP");
                    break;
                case "5":
                    productView.displayLaptopsByBrand("MICROSOFT");
                    break;
                case "6":
                    productView.displayLaptopsByBrand("LENOVO");
                    break;
                case "7":
                    productView.displayLaptopsByBrand("RAZER");
                    break;
                case "8":
                    return;
                default:
                    userView.showMessage("Lựa chọn không hợp lệ. Vui lòng thử lại!");
            }
        }
    }

    //===== SHOW MENU CATEGORY LAPTOPS WINDOW VĂN PHÒNG =====
    public void showCategoryLaptopsWindowOffice() {
        while (true) {
            productView.showCategoryLaptopsWindowOffice();
            choice = productView.getInput("Lựa chọn của bạn: ");
            switch (choice) {
                case "1":
                    productView.displayLaptopsByBrand("ASUS");
                    break;
                case "2":
                    productView.displayLaptopsByBrand("ACER");
                    break;
                case "3":
                    productView.displayLaptopsByBrand("DELL");
                    break;
                case "4":
                    productView.displayLaptopsByBrand("HP");
                    break;
                case "5":
                    productView.displayLaptopsByBrand("MICROSOFT");
                    break;
                case "6":
                    productView.displayLaptopsByBrand("LENOVO");
                    break;
                case "7":
                    productView.displayLaptopsByBrand("RAZER");
                    break;
                case "8":
                    return;
                default:
                    userView.showMessage("Lựa chọn không hợp lệ. Vui lòng thử lại!");
            }
        }
    }

    //===== SHOW MENU CATEGORY LAPTOPS MACBOOK =====
    public void showCategoryLaptopsMac() {
        productView.displayLaptopsByBrand("Apple");
    }
}