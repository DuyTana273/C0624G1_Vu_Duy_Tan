package case_study.view;

import java.util.Scanner;

public class UserView {
    private final Scanner scanner = new Scanner(System.in);

    //===== NHẬN ĐẦU VÀO =====
    public String getInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    //===== HIỆN THỊ MENU BAN ĐẦU =====
    public void showInitialMenu() {
        System.out.println("=================== WELCOME TO MY SHOP ===================");
        System.out.println("1. Đăng ký tài khoản");
        System.out.println("2. Đăng nhập");
        System.out.println("3. Thoát");
        System.out.print("Lựa chọn của bạn: ");
    }

    //===== MENU ADMIN =====
    public void showAdminMenu() {
        System.out.println("=================== ADMIN MENU ===================");
        System.out.println("1. Quản lý người dùng");
        System.out.println("2. Xem báo cáo");
        System.out.println("3. Quay lại Menu chính");
        System.out.println("4. Log Out");
        System.out.print("Lựa chọn của bạn: ");
    }

    //===== MENU MANAGER =====
    public void showManagerMenu() {
        System.out.println("=================== MANAGER MENU ===================");
        System.out.println("1. Quản lý người dùng");
        System.out.println("2. Phê duyệt seller");
        System.out.println("3. Quay lại Menu chính");
        System.out.println("4. Log Out");
        System.out.print("Lựa chọn của bạn: ");
    }

    //===== MENU SELLER =====
    public void showSellerMenu() {
        System.out.println("=================== SELLER MENU ===================");
        System.out.println("1. Quản lý sản phẩm");
        System.out.println("2. Xem đơn hàng");
        System.out.println("3. Quay lại Menu chính");
        System.out.println("4. Log Out");
        System.out.print("Lựa chọn của bạn: ");
    }

    //===== MENU BUYER =====
    public void showBuyerMenu() {
        System.out.println("=================== BUYER MENU ===================");
        System.out.println("1. Xem sản phẩm");
        System.out.println("2. Đặt hàng");
        System.out.println("3. Quay lại Menu chính");
        System.out.println("4. Log Out");
        System.out.print("Lựa chọn của bạn: ");
    }

    //===== MENU QUẢN LÝ NGƯỜI DÙNG =====
    public void showManageUsersMenu() {
        System.out.println("=================== MANAGE USERS MENU ===================");
        System.out.println("1. Thêm người dùng");
        System.out.println("2. Xóa người dùng");
        System.out.println("3. Chỉnh sửa thông tin người dùng");
        System.out.println("4. Quay lại Menu trước đó");
        System.out.print("Lựa chọn của bạn: ");
    }

    //===== THÔNG BÁO =====
    public void showMessage(String message) {
        System.out.println(message);
    }
}