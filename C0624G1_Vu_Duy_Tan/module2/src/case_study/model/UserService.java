package case_study.model;

import java.util.*;

public class UserService {

    //===== ĐỊNH NGHĨA THUỘC TÍNH =====
    private final Map<String, User> users = new HashMap<>();
    private final Map<String, List<Role>> pendingSellers = new HashMap<>();

    //===== ADMIN LÀ MẶC ĐỊNH =====
    public UserService() {
        users.put("admin", new Admin());
    }

    public User getUserByUsername(String username) {
        return users.get(username);
    }

    //===== ĐĂNG KÝ NGƯỜI DÙNG MỚI =====
    public void registerBuyer(String username, String password, String phoneNumber, String fullName, String email) {
        if (users.containsKey(username)) {
            throw new IllegalArgumentException("Tên tài khoản đã tồn tại. Vui lòng chọn tên tài khoản khác.");
        }

        User newUser = new User(username, password, phoneNumber, fullName, email, Role.ROLE_BUYER);
        users.put(username, newUser);
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
        }

        if (role == Role.ROLE_SELLER && !user.hasRole(Role.ROLE_SELLER)) {
            // Nếu là vai trò SELLER, cần phải chờ phê duyệt từ MANAGER
            pendingSellers.put(username, new ArrayList<>(Collections.singletonList(role)));
        } else {
            user.addRole(role);
        }
    }

    //===== XÓA NGƯỜI DÙNG =====
    public boolean removeUser(String username) {
        return users.remove(username) != null;
    }

    //===== LẤY THÔNG TIN NGƯỜI DÙNG =====
    public User getUser(String username) {
        return users.get(username);
    }

    //===== PHÊ DUYỆT SELLER =====
    public void approveSeller(String username) {
        if (!pendingSellers.containsKey(username)) {
            throw new IllegalArgumentException("Không có yêu cầu phê duyệt nào cho tài khoản: " + username);
        }

        User user = users.get(username);
        if (user != null) {
            List<Role> rolesToApprove = pendingSellers.remove(username);
            for (Role role : rolesToApprove) {
                user.addRole(role);
            }
        } else {
            throw new IllegalArgumentException("Không tìm thấy người dùng với tài khoản: " + username);
        }
    }
}
