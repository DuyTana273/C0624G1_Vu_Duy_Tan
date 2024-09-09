package case_study.controller;

import case_study.model.cart_manage.Cart;
import case_study.model.cart_manage.CartItem;
import case_study.model.product_manage.Laptop;
import case_study.model.user_manage.Role;
import case_study.model.user_manage.User;
import case_study.service.CartService;
import case_study.service.LaptopService;
import case_study.util.SessionManager;
import case_study.view.CartView;
import case_study.view.UserView;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class CartController {

    private CartService cartService;
    private CartView cartView;
    private UserView userView;
    private LaptopService laptopService;

    public CartController(CartService cartService, CartView cartView, UserView userView, LaptopService laptopService) {
        this.cartService = cartService;
        this.cartView = cartView;
        this.userView = userView;
        this.laptopService = laptopService;
    }

    //===== MENU CHÍNH GIỎ HÀNG =====
    public void cartMenu() {
        while (true) {
            cartView.showCartMenu();
            String choice = userView.getInput("Lựa chọn của bạn: ");
            switch (choice) {
                case "1":
                    displayCart();
                    break;
                case "2":
                    addItemToCart();
                    break;
                case "3":
                    removeItemFromCart();
                    break;
                case "4":
                    clearCart();
                    break;
                case "5":
                    System.out.println("Quay lại.");
                    return;
                default:
                    userView.showMessage("Tùy chọn không hợp lệ.");
            }
        }
    }

    //===== MANAGE CART =====
    public void addItemToCart() {
        String username = SessionManager.getCurrentUser().getUsername();

        laptopService.displayAllProducts();

        Laptop selectedProduct = null;
        int quantity = 0;

        while (true) {
            try {
                String productId = userView.getInput("Nhập Mã số sản phẩm: ");
                Optional<Laptop> selectedProductOpt = laptopService.getLaptopById(Integer.parseInt(productId));

                if (selectedProductOpt.isPresent()) {
                    selectedProduct = selectedProductOpt.get();

                    if (selectedProduct.getQuantity() == 0) {
                        System.out.println("Sản phẩm đã hết hàng.");
                        return;
                    }

                    System.out.println("Thông tin sản phẩm: " + selectedProduct);
                    break;
                } else {
                    System.out.println("ID sản phẩm không hợp lệ. Vui lòng nhập lại.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Định dạng Mã số sản phẩm không đúng. Vui lòng nhập số hợp lệ.");
            }
        }

        while (true) {
            try {
                quantity = Integer.parseInt(userView.getInput("Nhập số lượng: "));

                if (quantity <= 0) {
                    throw new IllegalArgumentException("Số lượng phải lớn hơn 0.");
                }

                if (quantity > selectedProduct.getQuantity()) {
                    System.out.println("Số lượng yêu cầu vượt quá số lượng tồn kho. Số lượng hiện có: " + selectedProduct.getQuantity());
                } else {
                    break;
                }

            } catch (NumberFormatException e) {
                System.out.println("Định dạng số lượng không đúng. Vui lòng nhập số hợp lệ.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        CartItem item = new CartItem(selectedProduct.getProductId(), selectedProduct.getName(), quantity, selectedProduct.getPrice());
        cartService.updateCart(username, item, true);

        laptopService.decreaseQuantity(selectedProduct.getProductId(), quantity);
    }

    public void removeItemFromCart() {
        String username = SessionManager.getCurrentUser().getUsername();
        Cart cart = cartService.getCart(username);

        if (cart != null && !cart.getItems().isEmpty()) {
            System.out.println("========== Các sản phẩm trong giỏ hàng của bạn ==========");
            List<CartItem> items = cart.getItems();
            for (int i = 0; i < items.size(); i++) {
                CartItem item = items.get(i);
                System.out.println((i + 1) + ". " + "Mã sản phẩm: " + item.getProductId() +
                        " | Tên: " + item.getProductName() +
                        " | Số lượng: " + item.getQuantity() +
                        " | Giá: " + item.getPrice());
            }

            boolean validInput = false;
            while (!validInput) {
                try {
                    String productIndexInput = userView.getInput("Nhập số thứ tự sản phẩm cần xóa: ");
                    int productIndex = Integer.parseInt(productIndexInput) - 1;

                    if (productIndex >= 0 && productIndex < items.size()) {
                        CartItem selectedItem = items.get(productIndex);

                        // Xoá sản phẩm khỏi giỏ hàng
                        cartService.updateCart(username, selectedItem, false);

                        // Trả lại số lượng sản phẩm vào kho dựa trên productId
                        Optional<Laptop> laptopOpt = laptopService.getLaptopById(selectedItem.getProductId());
                        if (laptopOpt.isPresent()) {
                            laptopService.increaseQuantity(laptopOpt.get().getProductId(), selectedItem.getQuantity());
                        }

                        System.out.println("Đã xóa sản phẩm và trả lại số lượng vào kho.");
                        validInput = true;
                    } else {
                        System.out.println("Mã số sản phẩm không hợp lệ. Vui lòng thử lại.");
                    }

                } catch (NumberFormatException e) {
                    System.out.println("Định dạng Mã số sản phẩm không đúng. Vui lòng nhập số hợp lệ.");
                }
            }
        } else {
            System.out.println("Giỏ hàng của bạn trống.");
        }
    }

    private void displayCart() {
            String username = SessionManager.getCurrentUser().getUsername();
            cartService.printCart(username);
    }

    public void clearCart() {
        String username = SessionManager.getCurrentUser().getUsername();
        Cart cart = cartService.getCart(username);

        if (cart != null && !cart.getItems().isEmpty()) {
            boolean validInput = false;
            while (!validInput) {
                String confirmation = userView.getInput("Bạn có chắc chắn muốn xóa toàn bộ giỏ hàng? (yes/no): ");

                if (confirmation.equalsIgnoreCase("yes")) {
                    // Trả lại số lượng tất cả các sản phẩm trong giỏ hàng vào kho dựa trên productId
                    for (CartItem item : cart.getItems()) {
                        Optional<Laptop> laptopOpt = laptopService.getLaptopById(item.getProductId());
                        if (laptopOpt.isPresent()) {
                            laptopService.increaseQuantity(laptopOpt.get().getProductId(), item.getQuantity());
                        }
                    }

                    // Xóa toàn bộ giỏ hàng
                    cartService.clearCart(username);
                    System.out.println("Toàn bộ giỏ hàng đã được xóa và số lượng sản phẩm đã được trả lại kho.");
                    validInput = true;
                } else if (confirmation.equalsIgnoreCase("no")) {
                    System.out.println("Hủy thao tác xóa giỏ hàng.");
                    validInput = true;
                } else {
                    System.out.println("Đầu vào không hợp lệ. Vui lòng nhập 'yes' hoặc 'no'.");
                }
            }
        } else {
            System.out.println("Giỏ hàng của bạn trống.");
        }
    }

    //===== THANH TOÁN =====
    public void placeOrder() {
        String username = SessionManager.getCurrentUser().getUsername();
        Cart cart = cartService.getCart(username);

        // Nếu giỏ hàng trống, hỏi người dùng có muốn mua hàng không
        if (cart == null || cart.getItems().isEmpty()) {
            System.out.println("Giỏ hàng của bạn trống.");
            String response = userView.getInput("Bạn có muốn xem tất cả sản phẩm để mua hàng không? (yes/no): ");
            if (response.equalsIgnoreCase("yes")) {
                // Chuyển hướng người dùng đến trang xem tất cả sản phẩm
                laptopService.displayAllProducts();
            } else {
                userView.showMessage("Bạn đã chọn không mua hàng. Hủy thao tác đặt hàng.");
            }
            return;
        }

        // Nếu giỏ hàng có sản phẩm, hỏi người dùng có muốn thanh toán không
        System.out.println("Bạn có các sản phẩm trong giỏ hàng sau:");
        cartService.printCart(username);

        String confirm = userView.getInput("Bạn có muốn thanh toán các sản phẩm này không? (yes/no): ");
        if (confirm.equalsIgnoreCase("yes")) {
            userView.showMessage("Thanh toán thành công! Cảm ơn bạn đã mua hàng.");

            cartService.clearCart(username);
        } else {
            userView.showMessage("Bạn đã hủy thanh toán.");
        }
    }

    // Xem giỏ hàng theo quyền của người dùng
    public void viewCartByRole(String username) {
        // Lấy thông tin người dùng từ SessionManager
        User currentUser = SessionManager.getCurrentUser();

        if (currentUser != null) {
            // Kiểm tra vai trò của người dùng
            if (currentUser.hasRole(Role.ROLE_ADMIN) || currentUser.hasRole(Role.ROLE_SELLER) || currentUser.hasRole(Role.ROLE_MANAGER)) {
                viewAllCarts();
            } else if (currentUser.hasRole(Role.ROLE_BUYER)) {
                cartService.printCart(username);
            } else {
                userView.showMessage("Bạn không có quyền truy cập giỏ hàng.");
            }
        } else {
            userView.showMessage("Người dùng không được xác thực.");
        }
    }

    // Admin/Seller xem tất cả giỏ hàng
    public void viewAllCarts() {
        Map<String, Cart> allCarts = cartService.getAllCartsForAdminOrSeller();

        if (allCarts.isEmpty()) {
            userView.showMessage("Không có giỏ hàng nào trong hệ thống.");
        } else {
            for (Map.Entry<String, Cart> entry : allCarts.entrySet()) {
                String username = entry.getKey();
                Cart cart = entry.getValue();

                userView.showMessage("Giỏ hàng của: " + username);
                cartService.printCart(username);
            }
        }
    }
}