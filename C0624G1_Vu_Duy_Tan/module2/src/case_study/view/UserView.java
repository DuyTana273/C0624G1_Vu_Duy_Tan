package case_study.view;

import case_study.model.product_manage.Laptop;
import case_study.model.user_manage.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class UserView {
    private final Scanner scanner = new Scanner(System.in);

    //===== NHẬN ĐẦU VÀO =====
    public String getInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    //===== HIỂN THỊ MENU =====
    public void showMenu(String title, List<String> options) {
        System.out.println("=================== " + title + " ===================");
        for (int i = 0; i < options.size(); i++) {
            System.out.println((i + 1) + ". " + options.get(i));
        }
    }

    //============================ MENU USER ============================

    //===== HIỂN THỊ MENU BAN ĐẦU =====
    public void showInitialMenu() {
        List<String> options = new ArrayList<>();
        options.add("Đăng ký tài khoản");
        options.add("Đăng nhập");
        options.add("Thoát");
        showMenu("WELCOME TO MY SHOP", options);
    }

    //===== MENU ADMIN =====
    public void showAdminMenu() {
        List<String> options = new ArrayList<>();
        options.add("Quản lý người dùng");
        options.add("Xem báo cáo");
        options.add("Đăng xuất");
        showMenu("ADMIN MENU", options);
    }

    //===== MENU QUẢN LÝ ADMIN =====
    public void showManageUsersMenuAdmin() {
        List<String> options = new ArrayList<>();
        options.add("Thêm khách hàng");
        options.add("Thêm nhân viên");
        options.add("Thêm quản lý");
        options.add("Xóa người dùng");
        options.add("Chỉnh sửa thông tin người dùng");
        options.add("Hiển thị danh sách người dùng");
        options.add("Quay lại Menu trước đó");
        showMenu("QUẢN LÝ NGƯỜI DÙNG (ADMIN)", options);
    }

    //===== MENU MANAGER =====
    public void showManagerMenu() {
        List<String> options = new ArrayList<>();
        options.add("Quản lý người dùng");
        options.add("Đổi mật khẩu");
        options.add("Đăng xuất");
        showMenu("MENU MANAGER", options);
    }

    //===== MENU QUẢN LÝ MANAGER =====
    public void showManageUsersMenu() {
        List<String> options = new ArrayList<>();
        options.add("Thêm khách hàng");
        options.add("Thêm nhân viên");
        options.add("Xóa người dùng");
        options.add("Chỉnh sửa thông tin người dùng");
        options.add("Hiển thị danh sách người dùng");
        options.add("Quay lại Menu trước đó");
        showMenu("QUẢN LÝ NGƯỜI DÙNG (MANAGER)", options);
    }

    //===== MENU SELLER =====
    public void showSellerMenu() {
        List<String> options = new ArrayList<>();
        options.add("Quản lý sản phẩm");
        options.add("Xem đơn hàng");
        options.add("Đổi mật khẩu");
        options.add("Log Out");
        showMenu("SELLER MENU", options);
    }

    //===== MENU BUYER =====
    public void showBuyerMenu() {
        List<String> options = new ArrayList<>();
        options.add("Xem sản phẩm");
        options.add("Đặt hàng");
        options.add("Đổi mật khẩu");
        options.add("Xóa tài khoản");
        options.add("Log Out");
        showMenu("BUYER MENU", options);
    }

    //===== THÔNG BÁO =====
    public void showMessage(String message) {
        System.out.println(message);
    }

    //===== HIỆN THỊ THÔNG TIN NGƯỜI DÙNG =====
    public void displayUsers(Map<String, User> users) {
        if (users.isEmpty()) {
            System.out.println("Không tìm thấy!!!");
            return;
        }
        System.out.println("Danh sách người dùng:");
        for (User user : users.values()) {
            System.out.println(user);

        }
    }

    //========================= QUẢN LÝ LAPTOP =========================
}
