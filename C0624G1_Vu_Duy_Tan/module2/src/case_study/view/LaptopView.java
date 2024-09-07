package case_study.view;

import case_study.model.product_manage.CategoryLaptop;
import case_study.model.product_manage.Laptop;
import case_study.service.LaptopService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LaptopView {

    //===== ĐỊNH NGHĨA THUỘC TÍNH =====
    private UserView userView;
    private Scanner scanner = new Scanner(System.in);
    private LaptopService laptopService;

    //===== CONSTRUCTOR =====
    public LaptopView(UserView userView, LaptopService laptopService) {
        this.userView = userView;
        this.laptopService = laptopService;
    }

    //===== PHÂN LOẠI LAPTOP =====
    public void showCategory() {
        List<String> options = new ArrayList<>();
        options.add(CategoryLaptop.WINDOWS_CATEGORY);
        options.add(CategoryLaptop.APPLE_CATEGORY);
        options.add("TẤT CẢ SẢN PHẨM");
        options.add("Quay lại Menu trước đó");
        userView.showMenu("DANH MỤC SẢN PHẨM", options);
    }

    //===== HIỂN THỊ SẢN PHẨM THEO DANH MỤC =====
    public void displayLaptopsByCategory(String category) {
        System.out.println("=== HIỂN THỊ SẢN PHẨM THEO DANH MỤC: " + category + " ===");
        laptopService.displayLaptopsByCategory(category);
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

    //===== NHẬP THÔNG TIN SẢN PHẨM =====
    public String inputLaptopName() {
        String name;
        do {
            System.out.print("Nhập tên sản phẩm: ");
            name = scanner.nextLine();
            if (name.trim().isEmpty()) {
                System.out.println("Tên sản phẩm không được để trống.");
            }
        } while (name.trim().isEmpty());
        return name;
    }

    public String inputLaptopBrand() {
        String brand;
        do {
            System.out.print("Nhập thương hiệu sản phẩm: ");
            brand = scanner.nextLine();
            if (brand.trim().isEmpty()) {
                System.out.println("Thương hiệu không được để trống.");
            }
        } while (brand.trim().isEmpty());
        return brand;
    }

    public double inputLaptopPrice() {
        double price = 0;
        boolean valid = false;
        while (!valid) {
            try {
                System.out.print("Nhập giá sản phẩm: ");
                price = Double.parseDouble(scanner.nextLine());
                if (price <= 0) {
                    throw new IllegalArgumentException("Giá sản phẩm phải lớn hơn 0.");
                }
                valid = true;
            } catch (NumberFormatException e) {
                System.out.println("Giá sản phẩm phải là số hợp lệ.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        return price;
    }

    public int inputLaptopQuantity() {
        int quantity = 0;
        boolean valid = false;
        while (!valid) {
            try {
                System.out.print("Nhập số lượng sản phẩm: ");
                quantity = Integer.parseInt(scanner.nextLine());
                if (quantity < 0) {
                    throw new IllegalArgumentException("Số lượng phải không âm.");
                }
                valid = true;
            } catch (NumberFormatException e) {
                System.out.println("Số lượng phải là số nguyên.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        return quantity;
    }

    public String inputLaptopDescription() {
        System.out.print("Nhập mô tả sản phẩm: ");
        return scanner.nextLine();
    }

    //===== HIỂN THỊ CHI TIẾT SẢN PHẨM =====
    public void displayLaptopDetail(int productId) {
        laptopService.getLaptopById(productId).ifPresentOrElse(
                laptop -> System.out.println(laptop),
                () -> System.out.println("Sản phẩm không tồn tại.")
        );
    }

    //===== XÁC NHẬN HÀNH ĐỘNG =====
    public boolean confirmAction(String message) {
        System.out.print(message + " (yes/no): ");
        String response = scanner.nextLine().trim().toLowerCase();
        return response.equals("yes");
    }
}
