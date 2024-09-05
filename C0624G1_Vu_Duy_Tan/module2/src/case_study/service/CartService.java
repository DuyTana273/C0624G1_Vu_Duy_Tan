package case_study.service;

import case_study.model.cart_manage.Cart;
import case_study.model.product_manage.Laptop;
import case_study.view.UserView;

import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class CartService {

    //===== ĐỊNH NGHĨA THUỘC TÍNH =====
    private UserView userView;
    private Cart cart;
    private ProductService productService;
    private Scanner scanner;

    //===== CONSTRUCTOR =====
    public CartService(UserView userView,ProductService productService) {
        this.cart = new Cart();
        this.userView = userView;
        this.productService = productService;
        this.scanner = new Scanner(System.in);
    }

    //===== HIỂN THỊ GIỎ HÀNG =====
    public void displayCart() {
        if (cart.getItems().isEmpty()) {
            System.out.println("Giỏ hàng đang trống.");
        } else {
            System.out.println("==== GIỎ HÀNG ====");
            for (Map.Entry<Laptop, Integer> entry : cart.getItems().entrySet()) {
                Laptop laptop = entry.getKey();
                int quantity = entry.getValue();
                System.out.println(laptop + " | Số lượng: " + quantity);
            }
            System.out.println("Tổng giá trị giỏ hàng: " + cart.getTotalCartValue());
        }
    }

    //===== THÊM SẢN PHẨM VÀO GIỎ HÀNG =====
    public void addProductToCart() {
        Map<String, Laptop> availableLaptops = productService.getAllLaptops();
        if (availableLaptops.isEmpty()) {
            System.out.println("Không có sản phẩm nào để thêm vào giỏ hàng.");
            return;
        }
        int productIndex;
        while (true) {
            String productIndexStr = userView.getInput("Nhập ID sản phẩm muốn thêm: ");
            try {
                productIndex = Integer.parseInt(productIndexStr);
                // Kiểm tra chỉ số sản phẩm hợp lệ
                if (productIndex < 0 || productIndex >= availableLaptops.size()) {
                    System.out.println("ID không hợp lệ. Vui lòng nhập lại.");
                } else {
                    break; // Thoát khỏi vòng lặp khi ID hợp lệ
                }
            } catch (NumberFormatException e) {
                System.out.println("Dữ liệu bạn nhập không phải là số nguyên. Vui lòng nhập lại.");
            }
        }
        Laptop selectedLaptop = (Laptop) availableLaptops.values().toArray()[productIndex];
        System.out.println("Bạn đã chọn laptop: " + selectedLaptop.getName());



        int quantity;
        while (true) {
            String quantityStr = userView.getInput("Nhập số lượng muốn thêm: ");
            try {
                quantity = Integer.parseInt(quantityStr);
                if (quantity < 0) {
                    System.out.println("Số lượng không thể là số âm. Vui lòng nhập lại.");
                } else if (quantity >= availableLaptops.size()) {
                    System.out.println("Số lượng vượt quá số sản phẩm có sẵn. Vui lòng nhập lại.");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Dữ liệu bạn nhập không phải là số nguyên. Vui lòng nhập lại.");
            }
        }

        cart.addItem(selectedLaptop, quantity);
        System.out.println("Đã thêm " + quantity + " " + selectedLaptop.getName() + " vào giỏ hàng.");
    }

    //===== XOÁ SẢN PHẨM KHỎI GIỎ HÀNG =====
    public void removeProductFromCart() {
        if (cart.getItems().isEmpty()) {
            System.out.println("Giỏ hàng đang trống.");
            return;
        }

        displayCart();
        System.out.print("Nhập tên sản phẩm muốn xóa: ");
        String productName = scanner.nextLine();

        Laptop laptopToRemove = null;
        for (Laptop laptop : cart.getItems().keySet()) {
            if (laptop.getName().equalsIgnoreCase(productName)) {
                laptopToRemove = laptop;
                break;
            }
        }

        if (laptopToRemove != null) {
            cart.removeItem(laptopToRemove);
            System.out.println("Đã xóa " + laptopToRemove.getName() + " khỏi giỏ hàng.");
        } else {
            System.out.println("Không tìm thấy sản phẩm trong giỏ hàng.");
        }
    }

    //===== CẬP NHẬT SỐ LƯỢNG SẢN PHẨM TRONG GIỎ HÀNG =====
    public void updateProductQuantityInCart() {
        if (cart.getItems().isEmpty()) {
            System.out.println("Giỏ hàng đang trống.");
            return;
        }

        displayCart();
        System.out.print("Nhập tên sản phẩm muốn cập nhật số lượng: ");
        String productName = scanner.nextLine();

        Laptop laptopToUpdate = null;
        for (Laptop laptop : cart.getItems().keySet()) {
            if (laptop.getName().equalsIgnoreCase(productName)) {
                laptopToUpdate = laptop;
                break;
            }
        }

        if (laptopToUpdate != null) {
            System.out.print("Nhập số lượng mới: ");
            int newQuantity = Integer.parseInt(scanner.nextLine());
            cart.addItem(laptopToUpdate, newQuantity); // Cập nhật số lượng
            System.out.println("Đã cập nhật số lượng của " + laptopToUpdate.getName() + " thành " + newQuantity);
        } else {
            System.out.println("Không tìm thấy sản phẩm trong giỏ hàng.");
        }
    }

    //===== XÓA GIỎ HÀNG =====
    public void clearCart() {
        cart.getItems().clear();
        System.out.println("Đã xóa toàn bộ sản phẩm trong giỏ hàng.");
    }

    //===== TÍNH TỔNG TIỀN GIỎ HÀNG =====
    public double getTotalCartValue() {
        return cart.getTotalCartValue();
    }

    //====== THÊM SẢN PHẨM VÀO GIỎ HÀNG =======
    public void addProductToCart(Map<String, Laptop> laptopMap) {
        if (laptopMap.isEmpty()) {
            System.out.println("Không có sản phẩm nào để thêm vào giỏ hàng.");
            return;
        }

        while (true) {
            String response = userView.getInput("Bạn có muốn thêm sản phẩm vào giỏ hàng không? (Có/Không): ");

            if (response.equalsIgnoreCase("Không")) {
                System.out.println("Đã thoát khỏi chức năng thêm sản phẩm vào giỏ hàng.");
                break;
            } else if (response.equalsIgnoreCase("Có")) {
                // Hiển thị danh sách sản phẩm với mã sản phẩm

                // Nhập mã sản phẩm
                String productKey = userView.getInput("Nhập mã sản phẩm muốn thêm: ");

                if (!laptopMap.containsKey(productKey)) {
                    userView.showMessage("Mã sản phẩm không hợp lệ. Vui lòng thử lại.");
                    continue;
                }

                // Lấy sản phẩm theo mã
                Laptop selectedLaptop = laptopMap.get(productKey);

                // Nhập số lượng muốn thêm
                int quantity = Integer.parseInt(userView.getInput("Nhập số lượng muốn thêm: "));
                if (quantity <= 0) {
                    userView.showMessage("Số lượng phải lớn hơn 0.");
                    continue;
                }

                cart.addItem(selectedLaptop, quantity);
                userView.showMessage("Đã thêm " + quantity + " " + selectedLaptop.getName() + " vào giỏ hàng.");
            } else {
                userView.showMessage("Vui lòng nhập 'Có' hoặc 'Không'.");
            }
        }
    }

}
