package case_study.controller;

import case_study.model.product_manage.Laptop;
import case_study.service.CartService;
import case_study.service.ProductService;
import case_study.view.ProductView;
import case_study.view.UserView;

import java.util.HashMap;
import java.util.Map;

public class ProductController {

    //===== ĐỊNH NGHĨA THUỘC TÍNH =====
    private Map<String, Laptop> laptops = new HashMap<>();
    private UserView userView;
    private ProductView productView;
    private ProductService productService;
    private CartService cartService;

    //===== CONSTRUCTOR =====
    public ProductController(UserView userView, ProductView productView, ProductService productService, CartService cartService) {
        this.userView = userView;
        this.productView = productView;
        this.productService = productService;
        this.cartService = cartService;
    }

    public void showProductManagementMenu() {
        while (true) {
            productView.manageProduct();
            String choice = userView.getInput("Lựa chọn của bạn: ");
            switch (choice) {
                case "1":
                    addNewLaptop();
                    break;
                case "2":
                    removeLaptop();
                    break;
                case "3":
                    updateLaptop();
                    break;
                case "4":
                    productService.displayAllProducts();
                    break;
                case "5":
                    return;
                default:
                    userView.showMessage("Lựa chọn không hợp lệ. Vui lòng thử lại!");
            }
        }
    }

    //========= MANAGE LAPTOP ===========
    private void addNewLaptop() {
        String addName = userView.getInput("Nhập tên sản phẩm: ");
        String brand = userView.getInput("Nhập thương hiệu: ");
        double price = Double.parseDouble(userView.getInput("Nhập giá sản phẩm: "));
        String specifications = userView.getInput("Nhập mô tả: ");
        Laptop newLaptop = new Laptop(addName, brand, price, specifications);
        productService.addLaptop(newLaptop);
    }

    private void removeLaptop() {
        int productId = Integer.parseInt(userView.getInput("Nhập ID sản phẩm: "));

        if (productService.getLaptopById(productId).isPresent()) {
            Laptop laptopToRemove = productService.getLaptopById(productId).get();
            productService.removeLaptop(laptopToRemove);
            System.out.println("Đã xóa sản phẩm với ID '" + productId + "' thành công.");
        } else {
            System.out.println("Sản phẩm với ID '" + productId + "' không tồn tại.");
        }
    }

    private void updateLaptop() {
        int productId = Integer.parseInt(userView.getInput("Nhập ID sản phẩm: "));

        if (productService.getLaptopById(productId).isPresent()) {
            String name = userView.getInput("Nhập tên sản phẩm mới (để trống nếu không thay đổi): ");
            String brand = userView.getInput("Nhập thương hiệu mới (để trống nếu không thay đổi): ");
            String priceInput = userView.getInput("Nhập giá mới (để trống nếu không thay đổi): ");
            double price = priceInput.isEmpty() ? -1 : Double.parseDouble(priceInput);
            String description = userView.getInput("Nhập mô tả mới (để trống nếu không thay đổi): ");

            productService.updateLaptop(productId, name, brand, price, description);
        } else {
            System.out.println("Sản phẩm với ID '" + productId + "' không tồn tại.");
        }
    }

    //===== SHOW MENU CATEGORY LAPTOPS =====
    public void showCategoryLaptops() {
        while (true) {
            productView.showCategory();
            String choice = userView.getInput("Lựa chọn của bạn: ");
            switch (choice) {
                case "1":
                    handleLaptopWindows();
                    break;
                case "2":
                    productService.displayLaptopsByBrand("Apple");
                    cartService.addProductToCart();
                    break;
                case "3":
                    productService.displayAllProducts();
                    cartService.addProductToCart();
                    break;
                case "4":
                    return;
                default:
                    userView.showMessage("Lựa chọn không hợp lệ. Vui lòng thử lại!");
            }
        }
    }

    //===== HANDLE LAPTOP WINDOWS =====
    private void handleLaptopWindows() {
        while (true) {
            productView.showCategoryLaptopsWindow();
            String choice = userView.getInput("Lựa chọn của bạn: ");
            switch (choice) {
                case "1":
                    handleLaptopBrands("DANH MỤC LAPTOP GAMING");
                    break;
                case "2":
                    handleLaptopBrands("DANH MỤC LAPTOP VĂN PHÒNG");
                    break;
                case "3":
                    return;
                default:
                    userView.showMessage("Lựa chọn không hợp lệ. Vui lòng thử lại!");
            }
        }
    }

    //===== HANDLE LAPTOP BRANDS =====
    private void handleLaptopBrands(String title) {
        while (true) {
            productView.showLaptopsCategoryMenu(title);
            String choice = userView.getInput("Lựa chọn của bạn: ");
            switch (choice) {
                case "1":
                    productService.displayLaptopsByBrand("ASUS");
                    break;
                case "2":
                    productService.displayLaptopsByBrand("ACER");
                    break;
                case "3":
                    productService.displayLaptopsByBrand("DELL");
                    break;
                case "4":
                    productService.displayLaptopsByBrand("HP");
                    break;
                case "5":
                    productService.displayLaptopsByBrand("MICROSOFT");
                    break;
                case "6":
                    productService.displayLaptopsByBrand("LENOVO");
                    break;
                case "7":
                    productService.displayLaptopsByBrand("RAZER");
                    break;
                case "8":
                    return;
                default:
                    userView.showMessage("Lựa chọn không hợp lệ. Vui lòng thử lại!");
            }
        }
    }
}
