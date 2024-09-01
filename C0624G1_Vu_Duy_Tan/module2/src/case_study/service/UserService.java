package case_study.service;

import case_study.model.user_manage.*;
import case_study.view.UserView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserService {
    private UserView userView;

    //===== ĐỊNH NGHĨA THUỘC TÍNH =====
    private final Map<String, User> users = new HashMap<>();
    private final Map<String, Seller> sellers = new HashMap<>();
    private final Map<String, Buyer> buyers = new HashMap<>();
    private final Map<String, Manager> managers = new HashMap<>();
    private final Map<String, List<Role>> pendingSellers = new HashMap<>();

    //===== ADMIN LÀ MẶC ĐỊNH =====
    public UserService() {
        users.put("admin", new Admin());
        users.put("manager", new Manager("manager", "manager", "012345", "manager", "manager@gmail.com"));
        users.put("seller", new Seller("seller", "seller", "0123456", "seller", "seller@gmail.com"));
        addRoleToUser("admin", Role.ROLE_ADMIN);
        addRoleToUser("manager", Role.ROLE_MANAGER);
        addRoleToUser("seller", Role.ROLE_SELLER);
    }

    //===== CHECK USERNAME =====
    public boolean isUsername(String username) {
        return users.containsKey(username);
    }

    //===== ĐĂNG KÝ NGƯỜI DÙNG MỚI =====
    public void registerBuyer(String username, String password, String phoneNumber, String fullName, String email) {
        if (isUsername(username)) {
            userView.showMessage("Tên tài khoản đã tồn tại. Vui lòng chọn tên tài khoản khác.");
        } else {
            User newUser = new User(username, password, phoneNumber, fullName, email);
            Buyer newBuyer = new Buyer(username, password, phoneNumber, fullName, email);

            users.put(username, newUser);
            buyers.put(username, newBuyer);
            addRoleToUser(username, Role.ROLE_BUYER);
        }
    }

    //===== ĐĂNG KÝ SELLER MỚI =====
    public void registerSeller(String username, String password, String phoneNumber, String fullName, String email) {
        if (isUsername(username)) {
            userView.showMessage("Tên tài khoản đã tồn tại. Vui lòng chọn tên tài khoản khác.");
        } else {
            User newUser = new User(username, password, phoneNumber, fullName, email);
            Seller newSeller = new Seller(username, password, phoneNumber, fullName, email);
            users.put(username, newUser);
            sellers.put(username, newSeller);
            addRoleToUser(username, Role.ROLE_SELLER);
        }
    }

    //===== ĐĂNG KÝ MANAGER MỚI =====
    public void registerManager(String username, String password, String phoneNumber, String fullName, String email) {
        if (isUsername(username)) {
            userView.showMessage("Tên tài khoản đã tồn tại. Vui lòng chọn tên tài khoản khác.");
        } else {
            User newUser = new User(username, password, phoneNumber, fullName, email);
            Manager newManager = new Manager(username, password, phoneNumber, fullName, email);

            users.put(username, newUser);
            managers.put(username, newManager);
            addRoleToUser(username, Role.ROLE_MANAGER);
        }
    }

    //===== ĐĂNG NHẬP NGƯỜI DÙNG =====
    public User loginUser(String username, String password) {
        User user = users.get(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    //===== THÊM VAI TRÒ CHO NGƯỜI DÙNG =====
    public void addRoleToUser(String username, Role role) {
        User user = users.get(username);
        if (user == null) {
            throw new IllegalArgumentException("Không tìm thấy người dùng với tài khoản: " + username);
        } else {
            user.addRole(role);
        }
    }

    //===== XÓA NGƯỜI DÙNG =====
    public boolean removeUser(String username) {
        return users.remove(username) != null;
    }

    //===== XÓA TÀI KHOẢN BUYER =====
    public boolean removeBuyer(String username) {
        User userToRemove = getUser(username);
        if (userToRemove != null && userToRemove.hasRole(Role.ROLE_BUYER)) {
            users.remove(username);
            return true;
        }
        return false;
    }

    //===== CHANGE PASSWORD =====
    public void changePasswordForUser(String username, String oldPassword, String newPassword) {
        User user = getUser(username);
        if (user != null && user.getPassword().equals(oldPassword)) {
            user.changePassword(newPassword);
            System.out.println("Đổi mật khẩu thành công!");
        } else {
            throw new IllegalArgumentException("Mật khẩu cũ không đúng hoặc tài khoản không tồn tại.");
        }
    }

    //===== LẤY THÔNG TIN NGƯỜI DÙNG =====
    public User getUser(String username) {
        return users.get(username);
    }

    //===== HIỆN THỊ THÔNG TIN USERS =====
    public Map<String, User> getAllUsers() {
        return users;
    }

    //===== PHƯƠNG THỨC VALIDATE =====
    public void validateUsername(String username) {
        if (username == null || username.isEmpty()) {
            throw new IllegalArgumentException("Username không được để trống!");
        } else if (!username.matches("^[a-zA-Z0-9]+$")) {
            throw new IllegalArgumentException("Username chỉ được chứa chữ cái và số, không chứa ký tự đặc biệt!");
        } else if (isUsername(username)) {
            throw new IllegalArgumentException("Tên tài khoản đã tồn tại!");
        }
    }

    public void validatePassword(String password) {
        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Mật khẩu không được để trống!");
        }
    }

    public void validatePhoneNumber(String phoneNumber) {
        if (phoneNumber == null || !phoneNumber.matches("0.*")) {
            throw new IllegalArgumentException("Số điện thoại phải bắt đầu bằng số 0");
        }
    }

    public void validateFullName(String fullName) {
        if (fullName == null || fullName.trim().isEmpty()) {
            throw new IllegalArgumentException("Họ tên không được để trống!");
        } else if (!fullName.matches("^[a-zA-Z\\s]*$")) {
            throw new IllegalArgumentException("Họ tên chỉ được chứa chữ cái và khoảng trắng!");
        }
    }

    public void validateEmail(String email) {
        if (email == null || !email.matches("^[\\w-.]+@[\\w-]+\\.[a-zA-Z]{2,}$")) {
            throw new IllegalArgumentException("Địa chỉ email không hợp lệ! Ví dụ: a@a.com");
        }
    }
}


