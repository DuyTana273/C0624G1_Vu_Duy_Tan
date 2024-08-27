package case_study.model;

public class Admin extends User {
    // ==== Giá trị mặc định ====
    private static final String DEFAULT_USERNAME = "admin";
    private static final String DEFAULT_PASSWORD = "admin";

    // ==== Constructor ====
    public Admin() {
        super(DEFAULT_USERNAME, DEFAULT_PASSWORD, "Administrator", "123456789", "admin@gmail.com", Role.ROLE_ADMIN);
    }

    // ==== Check Login ====
    public boolean checkLogin(String username, String password) {
        return this.getUsername().equalsIgnoreCase(username) && this.getPassword().equals(password);
    }

    // ==== Xét duyệt Seller ====
    public void approveSeller(String username, UserService userService) {
        User user = userService.getUserByUsername(username);
        if (user != null && user.getRole() == Role.ROLE_SELLER) {
            user.addRole(Role.ROLE_SELLER); // Cấp quyền cho Seller
            System.out.println("Seller " + username + " đã được phê duyệt.");
        } else {
            System.out.println("Người dùng không phải là seller hoặc không tồn tại.");
        }
    }

    // ==== Method Xem Báo Cáo ====
    public void viewReports() {
        // Logic xem báo cáo
        System.out.println("Xem báo cáo...");
    }

    @Override
    public String toString() {
        return "Admin{" +
                "username='" + getUsername() + '\'' +
                ", fullName='" + getFullName() + '\'' +
                ", phone='" + getPhoneNumber() + '\'' +
                ", email='" + getEmail() + '\'' +
                '}';
    }
}
