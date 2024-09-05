package case_study.view;

import case_study.model.product_manage.Laptop;
import case_study.service.ProductService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProductView {

    //===== ĐỊNH NGHĨA THUỘC TÍNH =====
    private final Scanner scanner = new Scanner(System.in);
    private UserView userView;
    private ProductService productService;

    //===== CONSTANTS =====
    private static final List<String> LAPTOP_BRANDS = List.of("ASUS", "ACER", "DELL", "HP", "MICROSOFT", "LENOVO", "RAZER");

    //===== CONSTRUCTOR =====
    public ProductView(ProductService productService, UserView userView) {
        this.productService = productService;
        this.userView = userView;
    }

    //===== MENU LAPTOP THEO THƯƠNG HIỆU =====
    public void showLaptopsCategoryMenu(String title) {
        List<String> options = new ArrayList<>(LAPTOP_BRANDS);
        options.add("Quay lại Menu trước đó");
        userView.showMenu(title, options);
    }

    //============================ MENU MANAGER PRODUCT ============================

    //===== PHÂN LOẠI LAPTOP =====
    public void showCategory() {
        List<String> options = new ArrayList<>();
        options.add("WINDOW");
        options.add("MACBOOK");
        options.add("TẤT CẢ SẢN PHẨM");
        options.add("Quay lại Menu trước đó");
        userView.showMenu("DANH MỤC SẢN PHẨM", options);
    }

    //===== HIỂN THỊ MENU LAPTOP WINDOW =====
    public void showCategoryLaptopsWindow() {
        List<String> options = new ArrayList<>();
        options.add("MẪU LAPTOP GAMING");
        options.add("MẪU LAPTOP VĂN PHÒNG");
        options.add("Quay lại Menu trước đó");
        userView.showMenu("DANH MỤC SẢN PHẨM ", options);
    }

    //===== MANAGER LAPTOP =====
    public void manageProduct() {
        List<String> options = new ArrayList<>();
        options.add("Thêm sản phẩm");
        options.add("Xóa sản phẩm");
        options.add("Sửa sản phẩm");
        options.add("TẤT CẢ SẢN PHẨM");
        options.add("Quay lại Menu trước đó");
        userView.showMenu("QUẢN LÝ SẢN PHẨM", options);
    }

}
