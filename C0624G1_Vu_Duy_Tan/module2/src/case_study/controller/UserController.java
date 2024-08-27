package case_study.controller;

import case_study.model.Role;
import case_study.model.User;
import case_study.model.UserService;
import case_study.view.UserView;

public class UserController {

    //===== ĐỊNH NGHĨA THUỘC TÍNH =====
    private final UserView userView;
    private User loggedInUser;
    private final UserService userService;
    private boolean exitToMainMenu;

    //===== CONSTRUCTOR =====
    public UserController(UserView userView, UserService userService) {
        this.userView = userView;
        this.userService = userService;
    }

    //********************* BẮT ĐẦU CHƯƠNG TRÌNH *********************
    public void start() {
        while (true) {
            if (loggedInUser == null) {
                userView.showInitialMenu();
                String choice = userView.getInput("Lựa chọn của bạn: ");
                switch (choice) {
                    case "1" -> registerBuyer();
                    case "2" -> login();
                    case "3" -> {
                        userView.showMessage("Cảm ơn bạn đã sử dụng chương trình!");
                        return;
                    }
                    default -> userView.showMessage("Lựa chọn không hợp lệ. Vui lòng thử lại!");
                }
            } else {
                exitToMainMenu = false;
                handleRoleBasedMenu();
                if (exitToMainMenu) {
                    loggedInUser = null;
                }
            }
        }
    }

    //===== ĐĂNG KÝ BUYER =====
    private void registerBuyer() {
        String username = userView.getInput("Nhập tài khoản: ");
        String password = userView.getInput("Nhập mật khẩu: ");
        String name = userView.getInput("Nhập họ tên: ");
        String email = userView.getInput("Nhập email: ");
        String phoneNumber = userView.getInput("Nhập SĐT: ");

        try {
            userService.registerBuyer(username, password, phoneNumber, name, email);
            userView.showMessage("Đăng ký tài khoản thành công!");
        } catch (IllegalArgumentException e) {
            userView.showMessage(e.getMessage());
        }
    }

    //===== ĐĂNG NHẬP NGƯỜI DÙNG =====
    private void login() {
        String username = userView.getInput("Nhập tài khoản: ");
        String password = userView.getInput("Nhập mật khẩu: ");

        loggedInUser = userService.loginUser(username, password);
        if (loggedInUser != null) {
            userView.showMessage("Đăng nhập thành công. Vai trò: " + loggedInUser.getRoles());
        } else {
            userView.showMessage("Sai tài khoản hoặc mật khẩu. Vui lòng thử lại!");
        }
    }

    //===== XỬ LÝ MENU DỰA THEO VAI TRÒ =====
    private void handleRoleBasedMenu() {
        if (loggedInUser.hasRole(Role.ROLE_ADMIN)) {
            handleAdminMenu();
        } else if (loggedInUser.hasRole(Role.ROLE_MANAGER)) {
            handleManagerMenu();
        } else if (loggedInUser.hasRole(Role.ROLE_SELLER)) {
            handleSellerMenu();
        } else if (loggedInUser.hasRole(Role.ROLE_BUYER)) {
            handleBuyerMenu();
        }
    }

    //======= MENU ADMIN =======
    private void handleAdminMenu() {
        while (true) {
            userView.showAdminMenu();
            String choice = userView.getInput("Lựa chọn của bạn: ");

            switch (choice) {
                case "1":
                    manageUsers();
                    break;
                case "2":
                    viewReports();
                    break;
                case "3":
                    exitToMainMenu = true;
                    return;
                default:
                    userView.showMessage("Lựa chọn không hợp lệ. Vui lòng thử lại!");
            }
        }
    }

    //======= MENU MANAGER =======
    private void handleManagerMenu() {
        while (true) {
            userView.showManagerMenu();
            String choice = userView.getInput("Lựa chọn của bạn: ");

            switch (choice) {
                case "1":
                    manageUsers();
                    break;
                case "2":
                    approveSeller();
                    break;
                case "3":
                    exitToMainMenu = true;
                    return;
                default:
                    userView.showMessage("Lựa chọn không hợp lệ. Vui lòng thử lại!");
            }
        }
    }

    //======= MENU SELLER =======
    private void handleSellerMenu() {
        while (true) {
            userView.showSellerMenu();
            String choice = userView.getInput("Lựa chọn của bạn: ");

            switch (choice) {
                case "1":
                    manageProducts();
                    break;
                case "2":
                    viewOrders();
                    break;
                case "3":
                    exitToMainMenu = true;
                    return;
                default:
                    userView.showMessage("Lựa chọn không hợp lệ. Vui lòng thử lại!");
            }
        }
    }

    //======= MENU BUYER =======
    private void handleBuyerMenu() {
        while (true) {
            userView.showBuyerMenu();
            String choice = userView.getInput("Lựa chọn của bạn: ");

            switch (choice) {
                case "1":
                    viewProducts();
                    break;
                case "2":
                    placeOrder();
                    break;
                case "3":
                    exitToMainMenu = true;
                    return;
                default:
                    userView.showMessage("Lựa chọn không hợp lệ. Vui lòng thử lại!");
            }
        }
    }

    //============================ QUẢN LÝ NGƯỜI DÙNG ============================
    private void manageUsers() {
        while (true) {
            userView.showManageUsersMenu();
            String choice = userView.getInput("Lựa chọn của bạn: ");
            switch (choice) {
                case "1":
                    addUser();
                    break;
                case "2":
                    removeUser();
                    break;
                case "3":
                    editUser();
                    break;
                case "4":
                    return;
                default:
                    userView.showMessage("Lựa chọn không hợp lệ. Vui lòng thử lại!");
            }
        }
    }

    //===== THÊM NGƯỜI DÙNG =====
    private void addUser() {
        String username = userView.getInput("Nhập tài khoản: ");
        String password = userView.getInput("Nhập mật khẩu: ");
        String name = userView.getInput("Nhập họ tên: ");
        String email = userView.getInput("Nhập email: ");
        String phoneNumber = userView.getInput("Nhập SĐT: ");
        String roleInput = userView.getInput("Nhập vai trò (buyer/seller/manager): ");

        Role role;
        if (roleInput.equalsIgnoreCase("buyer")) {
            role = Role.ROLE_BUYER;
        } else if (roleInput.equalsIgnoreCase("seller")) {
            role = Role.ROLE_SELLER;
        } else if (roleInput.equalsIgnoreCase("manager")) {
            role = Role.ROLE_MANAGER;
        } else {
            userView.showMessage("Vai trò không hợp lệ. Chỉ chấp nhận buyer, seller hoặc manager.");
            return;
        }

        userService.registerBuyer(username, password, phoneNumber, name, email);
        userService.addRoleToUser(username, role);
        userView.showMessage("Thêm người dùng thành công!");
    }

    //===== XÓA NGƯỜI DÙNG =====
    private void removeUser() {
        String username = userView.getInput("Nhập tài khoản cần xóa: ");
        if (userService.removeUser(username)) {
            userView.showMessage("Đã xóa người dùng thành công.");
        } else {
            userView.showMessage("Không tìm thấy người dùng với tài khoản: " + username);
        }
    }

    //===== CHỈNH SỬA NGƯỜI DÙNG =====
    private void editUser() {
        String username = userView.getInput("Nhập tài khoản cần chỉnh sửa: ");
        User user = userService.getUser(username);

        if (user == null) {
            userView.showMessage("Không tìm thấy người dùng với tài khoản: " + username);
            return;
        }

        String newName = userView.getInput("Nhập họ tên mới (hiện tại: " + user.getFullName() + "): ");
        String newEmail = userView.getInput("Nhập email mới (hiện tại: " + user.getEmail() + "): ");
        String newPhoneNumber = userView.getInput("Nhập SĐT mới (hiện tại: " + user.getPhoneNumber() + "): ");

        user.setFullName(newName);
        user.setEmail(newEmail);
        user.setPhoneNumber(newPhoneNumber);
        userView.showMessage("Đã cập nhật thông tin người dùng thành công!");
    }

    //===== PHÊ DUYỆT SELLER =====
    private void approveSeller() {
        String username = userView.getInput("Nhập tài khoản Seller cần phê duyệt: ");
        try {
            userService.approveSeller(username);
            userView.showMessage("Phê duyệt Seller thành công!");
        } catch (IllegalArgumentException e) {
            userView.showMessage(e.getMessage());
        }
    }

    //===== QUẢN LÝ SẢN PHẨM (CHO SELLER) =====
    private void manageProducts() {}

    //===== XEM ĐƠN HÀNG (CHO SELLER) =====
    private void viewOrders() {}

    //===== XEM SẢN PHẨM (CHO BUYER) =====
    private void viewProducts() {}

    //===== ĐẶT HÀNG (CHO BUYER) =====
    private void placeOrder() {}

    //===== XEM BÁO CÁO (CHO ADMIN) =====
    private void viewReports() {}
}
