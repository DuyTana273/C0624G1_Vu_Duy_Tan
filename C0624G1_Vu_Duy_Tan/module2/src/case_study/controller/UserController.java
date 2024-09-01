package case_study.controller;

import case_study.model.user_manage.Role;
import case_study.model.user_manage.User;
import case_study.service.ProductService;
import case_study.service.UserService;
import case_study.view.ProductView;
import case_study.view.UserView;

public class UserController {

    //===== ĐỊNH NGHĨA THUỘC TÍNH =====
    private final UserView userView;
    private final UserService userService;
    private final ProductView productView;
    private final ProductService productService;
    private final ProductController productController;
    private User loggedInUser;

    //===== CONSTRUCTOR =====
    public UserController(UserView userView, UserService userService, ProductController productController, ProductView productView, ProductService productService) {
        this.userView = userView;
        this.userService = userService;
        this.productView = productView;
        this.productService = productService;
        this.productController = productController;
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
                handleRoleBasedMenu();
            }
        }
    }

    private void logout() {
        loggedInUser = null;
        userView.showMessage("Bạn đã đăng xuất thành công.");
    }

    private void registerBuyer() {
        String username = "";
        String password = "";
        String name = "";
        String email = "";
        String phoneNumber = "";

        // Nhập username
        while (true) {
            username = userView.getInput("Nhập tài khoản: ");
            try {
                userService.validateUsername(username);
                break;
            } catch (IllegalArgumentException e) {
                userView.showMessage(e.getMessage());
            }
        }

        // Nhập password
        while (true) {
            password = userView.getInput("Nhập mật khẩu: ");
            try {
                userService.validatePassword(password);
                break;
            } catch (IllegalArgumentException e) {
                userView.showMessage(e.getMessage());
            }
        }

        // Nhập họ tên
        while (true) {
            name = userView.getInput("Nhập họ tên: ");
            try {
                userService.validateFullName(name);
                break;
            } catch (IllegalArgumentException e) {
                userView.showMessage(e.getMessage());
            }
        }

        // Nhập email
        while (true) {
            email = userView.getInput("Nhập email: ");
            try {
                userService.validateEmail(email);
                break;
            } catch (IllegalArgumentException e) {
                userView.showMessage(e.getMessage());
            }
        }

        // Nhập số điện thoại
        while (true) {
            phoneNumber = userView.getInput("Nhập SĐT: ");
            try {
                userService.validatePhoneNumber(phoneNumber);
                break;
            } catch (IllegalArgumentException e) {
                userView.showMessage(e.getMessage());
            }
        }

        // Đăng ký tài khoản sau khi tất cả thông tin đã hợp lệ
        try {
            userService.registerBuyer(username, password, phoneNumber, name, email);
            userView.showMessage("Đăng ký tài khoản thành công!");
        } catch (IllegalArgumentException e) {
            userView.showMessage(e.getMessage());
        }
    }

    //===== LOGIN USER =====
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
                    manageUsersAdmin();
                    break;
                case "2":
                    viewReports();
                    break;
                case "3":
                    logout();
                    return;
                default:
                    userView.showMessage("Lựa chọn không hợp lệ. Vui lòng thử lại!");
            }
        }
    }

    //======= MENU QUẢN LÝ (ADMIN) =======
    private void manageUsersAdmin() {
        while (true) {
            userView.showManageUsersMenuAdmin();
            String choice = userView.getInput("Lựa chọn của bạn: ");
            switch (choice) {
                case "1":
                    addBuyer();
                    break;
                case "2":
                    addSeller();
                    break;
                case "3":
                    addManager();
                    break;
                case "4":
                    removeUser();
                    break;
                case "5":
                    editUser();
                    break;
                case "6":
                    showUsers();
                    break;
                case "7":
                    return;
                default:
                    userView.showMessage("Lựa chọn không hợp lệ. Vui lòng thử lại!");
            }
        }
    }

    //======= MENU MANAGER=======
    private void handleManagerMenu() {
        while (true) {
            userView.showManagerMenu();
            String choice = userView.getInput("Lựa chọn của bạn: ");

            switch (choice) {
                case "1":
                    manageUsers();
                    break;
                case "2":
                    changePassword();
                    break;
                case "3":
                    logout();
                    return;
                default:
                    userView.showMessage("Lựa chọn không hợp lệ. Vui lòng thử lại!");
            }
        }
    }

    //======= MENU QUẢN LÝ (MANAGER) =======
    private void manageUsers() {
        while (true) {
            userView.showManageUsersMenu();
            String choice = userView.getInput("Lựa chọn của bạn: ");
            switch (choice) {
                case "1":
                    addBuyer();
                    break;
                case "2":
                    addSeller();
                    break;
                case "3":
                    removeUser();
                    break;
                case "4":
                    editUser();
                    break;
                case "5":
                    showUsers();
                    break;
                case "6":
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
                    changePassword();
                    break;
                case "4":
                    logout();
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
                    productController.showCategoryLaptops();
                    break;
                case "2":
                    placeOrder();
                    break;
                case "3":
                    changePassword();
                    break;
                case "4":
                    removeAccount();
                    return;
                case "5":
                    logout();
                    return;
                default:
                    userView.showMessage("Lựa chọn không hợp lệ. Vui lòng thử lại!");
            }
        }
    }

    //============================ QUẢN LÝ NGƯỜI DÙNG ============================

    //===== XEM BÁO CÁO (CHO ADMIN) =====
    private void viewReports() {
    }

    //======= HIỂN THỊ THÔNG TIN USERS =======
    private void showUsers() {
        userView.displayUsers(userService.getAllUsers());
    }

    //===== THÊM NGƯỜI DÙNG =====
    private void addBuyer() {
        User newBuyer = getInputForRegister();
        userService.registerBuyer(newBuyer.getUsername(), newBuyer.getPassword(), newBuyer.getPhoneNumber(), newBuyer.getFullName(), newBuyer.getEmail());
        userView.showMessage("Đăng ký khách hàng mới thành công.");
    }

    private void addSeller() {
        User newSeller = getInputForRegister();
        userService.registerSeller(newSeller.getUsername(), newSeller.getPassword(), newSeller.getPhoneNumber(), newSeller.getFullName(), newSeller.getEmail());
        userView.showMessage("Đăng ký nhân viên mới thành công.");
    }

    private void addManager() {
        User newManager = getInputForRegister();
        userService.registerManager(newManager.getUsername(), newManager.getPassword(), newManager.getPhoneNumber(), newManager.getFullName(), newManager.getEmail());
        userView.showMessage("Đăng ký quản lý mới thành công.");
    }

    //===== XÓA NGƯỜI DÙNG =====
    private void removeUser() {
        String username = userView.getInput("Nhập tài khoản cần xóa: ");
        User userToRemove = userService.getUser(username);

        if (userToRemove == null) {
            userView.showMessage("Không tìm thấy người dùng với tài khoản: " + username);
            return;
        }

        if (userToRemove.hasRole(Role.ROLE_MANAGER) && !loggedInUser.hasRole(Role.ROLE_ADMIN)) {
            userView.showMessage("Chỉ admin mới có thể xóa quản lý. Bạn không có quyền xóa người dùng này.");
            return;
        }

        if (userService.removeUser(username)) {
            userView.showMessage("Đã xóa người dùng thành công.");
        } else {
            userView.showMessage("Không thể xóa người dùng với tài khoản: " + username);
        }
    }

    //===== XÓA TÀI KHOẢN BUYER =====
    private void removeAccount() {
        String username = loggedInUser.getUsername();
        if (userService.removeBuyer(username)) {
            loggedInUser = null;
            userView.showMessage("Tài khoản của bạn đã được xóa thành công.");
        } else {
            userView.showMessage("Không thể xóa tài khoản của bạn.");
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

    //===== THÔNG TIN ĐĂNG KÝ =====
    private User getInputForRegister() {
        String username;
        while (true) {
            username = userView.getInput("Nhập tài khoản: ");
            try {
                userService.validateUsername(username);
                break;
            } catch (IllegalArgumentException e) {
                userView.showMessage(e.getMessage());
            }
        }

        String password;
        while (true) {
            password = userView.getInput("Nhập mật khẩu: ");
            try {
                userService.validatePassword(password);
                break;
            } catch (IllegalArgumentException e) {
                userView.showMessage(e.getMessage());
            }
        }

        String name;
        while (true) {
            name = userView.getInput("Nhập họ tên: ");
            try {
                userService.validateFullName(name);
                break;
            } catch (IllegalArgumentException e) {
                userView.showMessage(e.getMessage());
            }
        }

        String email;
        while (true) {
            email = userView.getInput("Nhập email: ");
            try {
                userService.validateEmail(email);
                break;
            } catch (IllegalArgumentException e) {
                userView.showMessage(e.getMessage());
            }
        }

        String phoneNumber;
        while (true) {
            phoneNumber = userView.getInput("Nhập SĐT: ");
            try {
                userService.validatePhoneNumber(phoneNumber);
                break;
            } catch (IllegalArgumentException e) {
                userView.showMessage(e.getMessage());
            }
        }

        return new User(username, password, phoneNumber, name, email);
    }


    //===== CHANGE PASSWORD =====
    private void changePassword() {
        String oldPassword;
        String newPassword;
        boolean valid = false;

        while (!valid) {
            oldPassword = userView.getInput("Nhập mật khẩu cũ: ");

            if (userService.getUser(loggedInUser.getUsername()).getPassword().equals(oldPassword)) {
                newPassword = userView.getInput("Nhập mật khẩu mới: ");
                userService.changePasswordForUser(loggedInUser.getUsername(), oldPassword, newPassword);
                valid = true;
            } else {
                userView.showMessage("Mật khẩu cũ không đúng. Vui lòng thử lại.");
            }
        }
    }

    //===== QUẢN LÝ SẢN PHẨM (CHO SELLER) =====
    private void manageProducts() {
    }

    //===== XEM ĐƠN HÀNG (CHO SELLER) =====
    private void viewOrders() {
    }

    //===== ĐẶT HÀNG (CHO BUYER) =====
    private void placeOrder() {
    }
}
