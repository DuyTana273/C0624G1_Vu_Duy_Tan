package case_study.view;

import case_study.model.product_manage.Laptop;
import case_study.service.ProductService;
import case_study.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProductView {

    //===== ĐỊNH NGHĨA THUỘC TÍNH =====
    private final Scanner scanner = new Scanner(System.in);
    UserView userView;
    ProductService productService;

    //===== HIỂN THỊ MENU =====
    public void showMenu(String title, List<String> options) {
        System.out.println("=================== " + title + " ===================");
        for (int i = 0; i < options.size(); i++) {
            System.out.println((i + 1) + ". " + options.get(i));
        }
    }

    //============================ MENU PRODUCT ============================

    //===== PHÂN LOẠI LAPTOP =====
    public void showCategory() {
        List<String> options = new ArrayList<>();
        options.add("WINDOW");
        options.add("MACBOOK");
        options.add("TẤT CẢ SẢN PHẨM");
        options.add("Quay lại Menu trước đó");
        showMenu("DANH MỤC SẢN PHẨM", options);
    }

    //================= WINDOW ==================
    //===== HIỂN THỊ MENU LAPTOP WINDOW =====
    public void showCategoryLaptopsWindow() {
        List<String> options = new ArrayList<>();
        options.add("MẪU LAPTOP GAMING");
        options.add("MẪU LAPTOP VĂN PHÒNG");
        options.add("Quay lại Menu trước đó");
        showMenu("DANH MỤC SẢN PHẨM", options);
    }

    //===== HIỂN THỊ MENU LAPTOP WINDOW GAMING =====
    public void showCategoryLaptopsWindowGaming() {
        List<String> options = new ArrayList<>();
        options.add("ASUS");
        options.add("ACER");
        options.add("DELL");
        options.add("HP");
        options.add("MICROSOFT");
        options.add("LENOVO");
        options.add("RAZER");
        options.add("Quay lại Menu trước đó");
        showMenu("DANH MỤC LAPTOP GAMING", options);
    }

    //===== HIỂN THỊ MENU LAPTOP WINDOW VĂN PHÒNG =====
    public void showCategoryLaptopsWindowOffice() {
        List<String> options = new ArrayList<>();
        options.add("ASUS");
        options.add("ACER");
        options.add("DELL");
        options.add("HP");
        options.add("MICROSOFT");
        options.add("LENOVO");
        options.add("RAZER");
        options.add("Quay lại Menu trước đó");
        showMenu("DANH MỤC LAPTOP VĂN PHÒNG", options);
    }

    //===== HIỂN THỊ THÔNG TIN LAPTOP THEO THƯƠNG HIỆU =====
    public void displayLaptopsByBrand(String brand) {
        List<Laptop> filteredLaptops = ProductService.getLaptopsByBrand(brand);

        if (filteredLaptops.isEmpty()) {
            userView.showMessage("Không có sản phẩm nào thuộc thương hiệu: " + brand);
            return;
        }

        System.out.println("======> LAPTOP THƯƠNG HIỆU: " + brand.toUpperCase());
        for (int i = 0; i < filteredLaptops.size(); i++) {
            System.out.println((i + 1) + ". " + filteredLaptops.get(i));
        }

        while (true) {
            String input = getInput("Nhập số sản phẩm để thêm vào giỏ hàng (0 để quay lại): ");

            try {
                int index = Integer.parseInt(input) - 1; // Chuyển đổi sang chỉ số (0-based)
                if (index == -1) {
                    return;
                }

                if (index >= 0 && index < filteredLaptops.size()) {
                    Laptop selectedLaptop = filteredLaptops.get(index);
                    productService.addToCart(selectedLaptop);
                    userView.showMessage("Đã thêm " + selectedLaptop.getName() + " vào giỏ hàng!");
                    break;
                } else {
                    userView.showMessage("Lựa chọn không hợp lệ. Vui lòng thử lại!");
                }
            } catch (NumberFormatException e) {
                userView.showMessage("Lựa chọn không hợp lệ. Vui lòng nhập một số!");
            }
        }
    }

    //===== NHẬN ĐẦU VÀO =====
    public String getInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }
}
