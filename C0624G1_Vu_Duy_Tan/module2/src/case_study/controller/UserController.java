package case_study.controller;

import case_study.model.user_manage.Role;
import case_study.model.user_manage.User;
import case_study.service.CartService;
import case_study.service.LaptopService;
import case_study.service.UserService;
import case_study.util.SessionManager;
import case_study.view.CartView;
import case_study.view.LaptopView;
import case_study.view.UserView;

import java.util.HashMap;
import java.util.Map;

public class UserController {

    //===== ĐỊNH NGHĨA THUỘC TÍNH =====
    private Map<String, User> users = new HashMap<>();
    private UserView userView;
    private UserService userService;
    private LaptopController laptopController;
    private CartService cartService;
    private CartController cartController;
    private User loggedInUser;

    //===== CONSTRUCTOR =====
    public UserController(UserView userView,
                          UserService userService,
                          LaptopController laptopController,
                          CartService cartService,
                          CartController cartController) {
        this.userView = userView;
        this.userService = userService;
        this.laptopController = laptopController;
        this.cartService = cartService;
        this.cartController = cartController;
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

        while (true) {
            username = userView.getInput("Nhập tài khoản: ");
            try {
                userService.validateUsername(username);
                break;
            } catch (IllegalArgumentException e) {
                userView.showMessage(e.getMessage());
            }
        }
        while (true) {
            password = userView.getInput("Nhập mật khẩu: ");
            try {
                userService.validatePassword(password);
                break;
            } catch (IllegalArgumentException e) {
                userView.showMessage(e.getMessage());
            }
        }
        while (true) {
            name = userView.getInput("Nhập họ tên: ");
            try {
                userService.validateFullName(name);
                break;
            } catch (IllegalArgumentException e) {
                userView.showMessage(e.getMessage());
            }
        }
        while (true) {
            email = userView.getInput("Nhập email: ");
            try {
                userService.validateEmail(email);
                break;
            } catch (IllegalArgumentException e) {
                userView.showMessage(e.getMessage());
            }
        }
        while (true) {
            phoneNumber = userView.getInput("Nhập SĐT: ");
            try {
                userService.validatePhoneNumber(phoneNumber);
                break;
            } catch (IllegalArgumentException e) {
                userView.showMessage(e.getMessage());
            }
        }
        try {
            userService.registerBuyer(username, password, phoneNumber, name, email);
            userView.showMessage("Đăng ký tài khoản thành công!");
        } catch (IllegalArgumentException e) {
            userView.showMessage(e.getMessage());
        }
    }

    private void login() {
        String username = userView.getInput("Nhập tài khoản: ");
        String password = userView.getInput("Nhập mật khẩu: ");

        loggedInUser = userService.loginUser(username, password);
        if (loggedInUser != null) {
            SessionManager.login(loggedInUser);
            userView.showMessage("Đăng nhập thành công. Vai trò: " + loggedInUser.getRoles());

            if (loggedInUser.getRoles().contains(Role.ROLE_BUYER)) {
                cartService.getOrCreateCart(loggedInUser);
            }

        } else {
            userView.showMessage("Sai tài khoản hoặc mật khẩu. Vui lòng thử lại!");
        }
    }

    //*************************** MENU ***************************

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
                    laptopController.showProductManagementMenu();
                    break;
                case "3":
                    cartController.viewCartByRole(String.valueOf(users));
                    break;
                case "4":
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
                    laptopController.showProductManagementMenu();
                    break;
                case "3":
                    cartController.viewCartByRole(String.valueOf(users));
                    break;
                case "4":
                    changePassword();
                    break;
                case "5":
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
                    laptopController.showProductManagementMenu();
                    break;
                case "2":
                    cartController.viewCartByRole(String.valueOf(users));
                    break;
                case "3":
                    editUser();
                    break;
                case "4":
                    changePassword();
                    break;
                case "5":
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
                    laptopController.showCategoryLaptops();
                    break;
                case "2":
                    cartController.placeOrder();
                    break;
                case "3":
                    cartController.cartMenu();
                    break;
                case "4":
                    editUser();
                    break;
                case "5":
                    changePassword();
                    break;
                case "6":
                    removeAccount();
                    return;
                case "7":
                    logout();
                    return;
                default:
                    userView.showMessage("Lựa chọn không hợp lệ. Vui lòng thử lại!");
            }
        }
    }

    //============================ QUẢN LÝ NGƯỜI DÙNG ============================

    //======= HIỂN THỊ THÔNG TIN USERS =======
    private void showUsers() {
        userView.displayUsers(userService.getAllUsers());
    }

    //===== REGISTER USERNAME =====
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

    //======= MANAGE USERS =======
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

    private void removeUser() {
        showUsers();
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

        userView.confirmAction("Bạn có chắc chắn muốn xóa người dùng này không?");

        if (userService.removeUser(username)) {
            userView.showMessage("Đã xóa người dùng thành công.");
        } else {
            userView.showMessage("Không thể xóa người dùng với tài khoản: " + username);
        }
    }

    private void removeAccount() {
        boolean confirmed = userView.confirmAction("Bạn có chắc chắn muốn xóa tài khoản không?");

        if (confirmed) {
            String username = loggedInUser.getUsername();

            if (userService.removeBuyer(username)) {
                loggedInUser = null;
                userView.showMessage("Tài khoản của bạn đã được xóa thành công.");
            } else {
                userView.showMessage("Không thể xóa tài khoản của bạn.");
            }
        } else {
            userView.showMessage("Hành động xóa tài khoản đã bị hủy.");
        }
    }

    private void editUser() {
        if (loggedInUser.hasRole(Role.ROLE_ADMIN) || loggedInUser.hasRole(Role.ROLE_MANAGER) || loggedInUser.hasRole(Role.ROLE_SELLER)) {
            showUsers();

            String username = userView.getInput("Nhập tài khoản cần chỉnh sửa: ");
            User userToEdit = userService.getUser(username);

            if (userToEdit == null) {
                userView.showMessage("Không tìm thấy người dùng với tài khoản: " + username);
                return;
            }

            if (loggedInUser.hasRole(Role.ROLE_ADMIN)) {
                String newName = userView.getInput("Nhập họ tên mới (hiện tại: " + userToEdit.getFullName() + "): ");
                String newEmail = userView.getInput("Nhập email mới (hiện tại: " + userToEdit.getEmail() + "): ");
                String newPhoneNumber = userView.getInput("Nhập SĐT mới (hiện tại: " + userToEdit.getPhoneNumber() + "): ");
                userService.updateUserDetail(username, newPhoneNumber, newName, newEmail);
                userView.showMessage("Đã cập nhật thông tin người dùng thành công!");
            } else if (loggedInUser.hasRole(Role.ROLE_MANAGER)) {
                if (userToEdit.hasRole(Role.ROLE_SELLER) || userToEdit.hasRole(Role.ROLE_BUYER) || userToEdit.getUsername().equals(loggedInUser.getUsername())) {
                    String newName = userView.getInput("Nhập họ tên mới (hiện tại: " + userToEdit.getFullName() + "): ");
                    String newEmail = userView.getInput("Nhập email mới (hiện tại: " + userToEdit.getEmail() + "): ");
                    String newPhoneNumber = userView.getInput("Nhập SĐT mới (hiện tại: " + userToEdit.getPhoneNumber() + "): ");
                    userService.updateUserDetail(username, newPhoneNumber, newName, newEmail);
                    userView.showMessage("Đã cập nhật thông tin người dùng thành công!");
                } else {
                    userView.showMessage("Bạn không có quyền chỉnh sửa thông tin người dùng này.");
                }
            } else if (loggedInUser.hasRole(Role.ROLE_SELLER)) {
                if (userToEdit.hasRole(Role.ROLE_BUYER) || userToEdit.getUsername().equals(loggedInUser.getUsername())) {
                    String newName = userView.getInput("Nhập họ tên mới (hiện tại: " + userToEdit.getFullName() + "): ");
                    String newEmail = userView.getInput("Nhập email mới (hiện tại: " + userToEdit.getEmail() + "): ");
                    String newPhoneNumber = userView.getInput("Nhập SĐT mới (hiện tại: " + userToEdit.getPhoneNumber() + "): ");
                    userService.updateUserDetail(username, newPhoneNumber, newName, newEmail);
                    userView.showMessage("Đã cập nhật thông tin người dùng thành công!");
                } else {
                    userView.showMessage("Bạn không có quyền chỉnh sửa thông tin người dùng này.");
                }
            } else {
                userView.showMessage("Bạn không có quyền thực hiện chức năng này.");
            }
        } else if (loggedInUser.hasRole(Role.ROLE_BUYER)) {
            String username = loggedInUser.getUsername();
            User userToEdit = userService.getUser(username);

            if (userToEdit == null) {
                userView.showMessage("Không tìm thấy người dùng với tài khoản: " + username);
                return;
            }

            String newName = userView.getInput("Nhập họ tên mới (hiện tại: " + userToEdit.getFullName() + "): ");
            String newEmail = userView.getInput("Nhập email mới (hiện tại: " + userToEdit.getEmail() + "): ");
            String newPhoneNumber = userView.getInput("Nhập SĐT mới (hiện tại: " + userToEdit.getPhoneNumber() + "): ");

            userService.updateUserDetail(username, newPhoneNumber, newName, newEmail);
            userView.showMessage("Đã cập nhật thông tin của bạn thành công!");
        } else {
            userView.showMessage("Bạn không có quyền thực hiện chức năng này.");
        }
    }

    private void changePassword() {
        userView.confirmAction("Bạn có chắc chắn muốn thay đổi mật khẩu không?");
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
}
