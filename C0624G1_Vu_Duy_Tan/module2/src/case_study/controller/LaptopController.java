package case_study.controller;

import case_study.model.product_manage.Laptop;
import case_study.service.LaptopService;
import case_study.view.LaptopView;
import case_study.view.UserView;

public class LaptopController {

    //===== ĐỊNH NGHĨA THUỘC TÍNH =====
    private LaptopView laptopView;
    private LaptopService laptopService;
    private UserView userView;

    //===== CONSTRUCTOR =====
    public LaptopController(LaptopView laptopView, LaptopService laptopService, UserView userView) {
        this.laptopView = laptopView;
        this.laptopService = laptopService;
        this.userView = userView;
    }

    //===== MENU QUẢN LÝ SẢN PHẨM =====
    public void showProductManagementMenu() {
        while (true) {
            laptopView.manageProduct();
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
                    laptopService.displayAllProducts();
                    break;
                case "5":
                    return;
                default:
                    userView.showMessage("Lựa chọn không hợp lệ. Vui lòng thử lại.");
            }
        }
    }

    //===== THÊM MỚI LAPTOP =====
    private void addNewLaptop() {
        // Lấy thông tin sản phẩm từ người dùng
        String name = laptopView.inputLaptopName();
        String brand = laptopView.inputLaptopBrand();
        double price = laptopView.inputLaptopPrice();
        int quantity = laptopView.inputLaptopQuantity();
        String description = laptopView.inputLaptopDescription();

        // Tạo đối tượng Laptop mới
        Laptop newLaptop = new Laptop(name, brand, price, quantity, description);

        String category = brand.equalsIgnoreCase("Apple") ? "Apple" : "Windows";

        laptopService.addLaptop(newLaptop, category);
    }

    //===== XÓA LAPTOP =====
    private void removeLaptop() {
        int productId = Integer.parseInt(userView.getInput("Nhập ID sản phẩm: "));
        laptopService.getLaptopById(productId).ifPresentOrElse(
                laptop -> {
                    laptopView.displayLaptopDetail(productId);
                    if (userView.confirmAction("Bạn có chắc chắn muốn xóa sản phẩm này không?")) {
                        laptopService.removeLaptop(laptop);
                    }
                },
                () -> userView.showMessage("Sản phẩm không tồn tại.")
        );
    }

    //===== SỬA LAPTOP =====
    private void updateLaptop() {
        int productId = Integer.parseInt(userView.getInput("Nhập ID sản phẩm: "));

        laptopService.getLaptopById(productId).ifPresentOrElse(
                laptop -> {
                    String name = laptopView.inputLaptopName();
                    String brand = laptopView.inputLaptopBrand();
                    double price = laptopView.inputLaptopPrice();
                    int quantity = laptopView.inputLaptopQuantity();
                    String description = laptopView.inputLaptopDescription();

                    laptopService.updateLaptop(productId, name, brand, price, quantity, description);
                    userView.showMessage("Sản phẩm đã được cập nhật thành công.");
                },
                () -> userView.showMessage("Sản phẩm không tồn tại.")
        );
    }

    //===== HIỂN THỊ SẢN PHẨM THEO DANH MỤC =====
    public void showCategoryLaptops() {
        while (true) {
            laptopView.showCategory();
            String choice = userView.getInput("Lựa chọn của bạn: ");

            switch (choice) {
                case "1":
                    laptopService.displayLaptopsByCategory("Windows");
                    break;
                case "2":
                    laptopService.displayLaptopsByCategory("Apple");
                    break;
                case "3":
                    laptopService.displayAllProducts();
                    break;
                case "4":
                    return;
                default:
                    userView.showMessage("Lựa chọn không hợp lệ. Vui lòng thử lại.");
            }
        }
    }
}
