package case_study.service;

import case_study.model.user_manage.*;
import case_study.view.UserView;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class UserService {
    private UserView userView;

    // Link File csv
    private static final String USERS_FILE_PATH = "src/case_study/store/users.csv";

    //===== ĐỊNH NGHĨA THUỘC TÍNH =====
    private Map<String, User> users = new HashMap<>();
    private final Map<String, Seller> sellers = new HashMap<>();
    private final Map<String, Buyer> buyers = new HashMap<>();
    private final Map<String, Manager> managers = new HashMap<>();
    private final Map<String, List<Role>> pendingSellers = new HashMap<>();

    //===== ADMIN LÀ MẶC ĐỊNH =====
    public UserService() {
        users = readUsersFromFile();
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
            writeUsersToFile(users);
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
            writeUsersToFile(users);
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
            writeUsersToFile(users);
        }
    }

    //===== SỬA THÔNG TIN BUYER =====
    public void updateUserDetail(String username, String newPhoneNumber, String newFullName, String newEmail) {
        User user = users.get(username);
        if (user != null) {
            user.setFullName(newFullName);
            user.setEmail(newEmail);
            user.setPhoneNumber(newPhoneNumber);
            writeUsersToFile(users);
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
        boolean removed = users.remove(username) != null;
        if (removed) {
            writeUsersToFile(users);
        }
        return removed;
    }

    //===== XÓA TÀI KHOẢN BUYER =====
    public boolean removeBuyer(String username) {
        User userToRemove = getUser(username);
        if (userToRemove != null && userToRemove.hasRole(Role.ROLE_BUYER)) {
            users.remove(username);
            writeUsersToFile(users);
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
            writeUsersToFile(users);
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


    /**********   Handle File   ***********/
    // Ghi dữ liệu vào file
    public void writeUsersToFile(Map<String, User> users) {
        try (FileWriter writer = new FileWriter(USERS_FILE_PATH)) {
            writer.write("UserName,Password,PhoneNumber,Email,FullName,Role\n");
            for (User user : users.values()) {
                writer.write(String.format("%s,%s,%s,%s,%s,%s%n",
                        user.getUsername(),
                        user.getPassword(),
                        user.getPhoneNumber(),
                        user.getEmail(),
                        user.getFullName(),
                        String.join("-", user.getRoles().stream()
                                .map(Role::name)
                                .collect(Collectors.toSet()))));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Đọc dữ liệu từ file
    private Map<String, User> readUsersFromFile() {
        Map<String, User> users = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(USERS_FILE_PATH))) {
            String line;
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] split = line.split(",");
                String username = split[0];
                String password = split[1];
                String phoneNumber = split[2];
                String email = split[3];
                String fullName = split[4];
                String role = split[5];
                User user = new User(username, password, phoneNumber, fullName, email);
                Set<Role> roles = Arrays.stream(role.split("-"))
                        .map(Role::valueOf)
                        .collect(Collectors.toSet());
                user.setRoles(roles);
                users.put(username, user);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return users;
    }
}


