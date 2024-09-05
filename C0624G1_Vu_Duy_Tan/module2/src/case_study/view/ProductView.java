package case_study.view;

import case_study.controller.CartController;
import case_study.model.cart_manage.cart.CartItem;
import case_study.model.product_manage.Laptop;
import case_study.service.ProductService;
import case_study.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ProductView {

    //===== ĐỊNH NGHĨA THUỘC TÍNH =====
    private final Scanner scanner = new Scanner(System.in);
    private UserView userView;
    private ProductService productService;
    private CartController cartController;

    //===== CONSTANTS =====
    private static final List<String> LAPTOP_BRANDS = List.of("ASUS", "ACER", "DELL", "HP", "MICROSOFT", "LENOVO", "RAZER");

    //===== CONSTRUCTOR =====
    public ProductView(CartController cartController, ProductService productService, UserView userView) {
        this.cartController = cartController;
        this.productService = productService;
        this.userView = userView;
    }

    //============================ HIỂN THỊ MENU ============================
    private void showMenu(String title, List<String> options) {
        System.out.println("=================== " + title + " ===================");
        for (int i = 0; i < options.size(); i++) {
            System.out.println((i + 1) + ". " + options.get(i));
        }
    }
    //===== HIỂN THỊ THÔNG TIN LAPTOP THEO THƯƠNG HIỆU =====
    public void showLaptopsCategoryMenu(String title) {
        List<String> options = new ArrayList<>(LAPTOP_BRANDS);
        options.add("Quay lại Menu trước đó");
        showMenu(title, options);
    }

    //===== HIỂN THỊ THÔNG TIN LAPTOP THEO THƯƠNG HIỆU =====
    public void displayLaptopsByBrand(String brand) {
        List<Laptop> filteredLaptops = productService.getLaptopsByBrand(brand);

        if (filteredLaptops.isEmpty()) {
            userView.showMessage("Không có sản phẩm nào thuộc thương hiệu: " + brand);
            return;
        }

        System.out.println("==== LAPTOP THƯƠNG HIỆU: " + brand.toUpperCase() + " ====");
        for (int i = 0; i < filteredLaptops.size(); i++) {
            System.out.println((i + 1) + ". " + filteredLaptops.get(i));
        }
    }

    public void showCartMenu(String title) {
        List<String> options = new ArrayList<>(LAPTOP_BRANDS);

    }


    //============================ MENU MANAGER PRODUCT ============================

    //===== PHÂN LOẠI LAPTOP =====
    public void showCategory() {
        List<String> options = new ArrayList<>();
        options.add("WINDOW");
        options.add("MACBOOK");
        options.add("TẤT CẢ SẢN PHẨM");
        options.add("Quay lại Menu trước đó");
        showMenu("DANH MỤC SẢN PHẨM", options);
    }

    //===== HIỂN THỊ MENU LAPTOP WINDOW =====
    public void showCategoryLaptopsWindow() {
        List<String> options = new ArrayList<>();
        options.add("MẪU LAPTOP GAMING");
        options.add("MẪU LAPTOP VĂN PHÒNG");
        options.add("Quay lại Menu trước đó");
        showMenu("DANH MỤC SẢN PHẨM ", options);
    }

    //===== MANAGER LAPTOP =====
    public void manageProduct() {
        List<String> options = new ArrayList<>();
        options.add("Thêm sản phẩm");
        options.add("Xóa sản phẩm");
        options.add("Sửa sản phẩm");
        options.add("TẤT CẢ SẢN PHẨM");
        options.add("Quay lại Menu trước đó");
        showMenu("QUẢN LÝ SẢN PHẨM", options);
    }

    //============================ MENU GIỎ HÀNG ============================
    public void showCart(ProductService productService) {
        if (productService.getCart().getItems().isEmpty()) {
            System.out.println("Giỏ hàng của bạn đang trống.");
        } else {
            System.out.println("Danh sách sản phẩm trong giỏ hàng:");
            for (Map.Entry<Integer, CartItem> entry : productService.getCart().getItems().entrySet()) {
                CartItem item = entry.getValue();
                System.out.println(item);
            }
            System.out.println("Tổng giá trị giỏ hàng: " + productService.getCartTotalValue() + " VND");
        }
    }

    public void manageCart(ProductService productService) {
        while (true) {
            showCart(productService);
            System.out.println("1. Thêm sản phẩm vào giỏ hàng");
            System.out.println("2. Xóa sản phẩm khỏi giỏ hàng");
            System.out.println("3. Cập nhật số lượng sản phẩm trong giỏ hàng");
            System.out.println("4. Xóa toàn bộ giỏ hàng");
            System.out.println("5. Quay lại");

            String choice = getInput("Lựa chọn của bạn: ");
            switch (choice) {
                case "1":
                    int productIdToAdd = Integer.parseInt(getInput("Nhập ID sản phẩm: "));
                    int quantityToAdd = Integer.parseInt(getInput("Nhập số lượng: "));
                    Laptop laptopToAdd = productService.getLaptopById(productIdToAdd).orElse(null);
                    if (laptopToAdd != null) {
                        productService.addToCart(laptopToAdd, quantityToAdd);
                    } else {
                        System.out.println("Sản phẩm không tồn tại.");
                    }
                    break;
                case "2":
                    int productIdToRemove = Integer.parseInt(getInput("Nhập ID sản phẩm cần xóa: "));
                    productService.removeFromCart(productIdToRemove);
                    break;
                case "3":
                    int productIdToUpdate = Integer.parseInt(getInput("Nhập ID sản phẩm: "));
                    int newQuantity = Integer.parseInt(getInput("Nhập số lượng mới: "));
                    productService.updateCartItemQuantity(productIdToUpdate, newQuantity);
                    break;
                case "4":
                    productService.clearCart();
                    break;
                case "5":
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ, vui lòng thử lại.");
            }
        }
    }

    //===== NHẬN ĐẦU VÀO =====
    public String getInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }
}
