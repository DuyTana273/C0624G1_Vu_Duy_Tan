package case_study.view;

import case_study.controller.CartController;
import case_study.model.product_manage.Laptop;
import case_study.service.ProductService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CartView {
    private UserView userView;
    private final CartController cartController;
    private final ProductService productService;
    private final Scanner scanner = new Scanner(System.in);

    // Constructor: Nhận CartController và ProductService
    public CartView(CartController cartController, ProductService productService, UserView userView) {
        this.cartController = cartController;
        this.productService = productService;
        this.userView = userView;
    }

    //===== HIỂN THỊ MENU =====
    private void showMenu(String title, List<String> options) {
        System.out.println("=================== " + title + " ===================");
        for (int i = 0; i < options.size(); i++) {
            System.out.println((i + 1) + ". " + options.get(i));
        }
    }

    // Hiển thị menu giỏ hàng
    public void showCartMenu() {
        List<String> options = new ArrayList<>();
        options.add("Hiển thị giỏ hàng");
        options.add("Thêm sản phẩm vào giỏ hàng");
        options.add("Cập nhật số lượng sản phẩm");
        options.add("Xóa sản phẩm khỏi giỏ hàng");
        options.add("Xóa toàn bộ giỏ hàng");
        options.add("Quay lại");
        showMenu("QUẢN LÝ GIỎ HÀNG", options);
        while (true) {
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    cartController.showCartItems();
                    cartController.showTotalValue();
                    break;
                case "2":
                    addItemToCart();
                    break;
                case "3":
                    updateCartItemQuantity();
                    break;
                case "4":
                    removeItemFromCart();
                    break;
                case "5":
                    cartController.clearCart();
                    break;
                case "6":
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng thử lại.");
            }
        }
    }

    // Thêm sản phẩm vào giỏ hàng
    private void addItemToCart() {
        System.out.print("Nhập ID sản phẩm muốn thêm vào giỏ: ");
        int productId = Integer.parseInt(scanner.nextLine());

        productService.getLaptopById(productId).ifPresentOrElse(laptop -> {
            System.out.print("Nhập số lượng: ");
            int quantity = Integer.parseInt(scanner.nextLine());
            cartController.addItemToCart(laptop, quantity);
            System.out.println("Đã thêm sản phẩm vào giỏ hàng.");
        }, () -> {
            System.out.println("Sản phẩm không tồn tại.");
        });
    }

    // Cập nhật số lượng sản phẩm trong giỏ hàng
    private void updateCartItemQuantity() {
        System.out.print("Nhập ID sản phẩm muốn cập nhật: ");
        int productId = Integer.parseInt(scanner.nextLine());

        System.out.print("Nhập số lượng mới: ");
        int quantity = Integer.parseInt(scanner.nextLine());

        cartController.updateCartItemQuantity(productId, quantity);
        System.out.println("Đã cập nhật số lượng sản phẩm trong giỏ hàng.");
    }

    // Xóa sản phẩm khỏi giỏ hàng
    private void removeItemFromCart() {
        System.out.print("Nhập ID sản phẩm muốn xóa khỏi giỏ hàng: ");
        int productId = Integer.parseInt(scanner.nextLine());

        cartController.removeItemFromCart(productId);
        System.out.println("Đã xóa sản phẩm khỏi giỏ hàng.");
    }
}
