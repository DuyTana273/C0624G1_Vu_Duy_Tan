package case_study.service;

import case_study.model.cart_manage.Cart;
import case_study.model.cart_manage.CartItem;
import case_study.model.user_manage.User;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class CartService {
    private Map<String, Cart> carts;

    private static final String FILE_NAME = "C0624G1_Vu_Duy_Tan/C0624G1_Vu_Duy_Tan/module2/src/case_study/store/carts.csv";

    public CartService() {
        carts = new HashMap<>();
        carts = readCartsFromFile();
    }

    // Tạo hoặc lấy giỏ hàng của buyer
    public Cart getOrCreateCart(User buyer) {
        String username = buyer.getUsername();
        if (!carts.containsKey(username)) {
            carts.put(username, new Cart(username));
        }
        return carts.get(username);
    }

    // Lấy giỏ hàng của một buyer
    public Cart getCart(String username) {
        return carts.get(username);
    }

    // Cập nhật giỏ hàng (thêm/xóa sản phẩm)
    public void updateCart(String username, CartItem item, boolean isAdding) {
        Cart cart = carts.get(username);

        if (cart != null) {
            if (isAdding) {
                cart.addItem(item);
                System.out.println("Sản phẩm đã được thêm vào giỏ hàng.");
            } else {
                cart.removeItemByProductId(item.getProductId());
                System.out.println("Sản phẩm đã được xóa khỏi giỏ hàng.");
            }
            // Ghi lại trạng thái giỏ hàng sau khi cập nhật vào file
            writeCartsToFile(new ArrayList<>(carts.values()));
        } else {
            System.out.println("Không tìm thấy giỏ hàng.");
        }
    }

    // In ra thông tin giỏ hàng
    public void printCart(String username) {
        Cart cart = carts.get(username);
        if (cart != null && !cart.getItems().isEmpty()) {
            // In ra thông tin các sản phẩm trong giỏ hàng
            System.out.println("====== GIỎ HÀNG CỦA ' " + username + " ' ======");
            for (CartItem item : cart.getItems()) {
                System.out.println("Mã sản phẩm: " + item.getProductId() + " - Tên sản phẩm: " + item.getProductName() +
                        " - Số lượng: " + item.getQuantity() + " - Giá: " + item.getPrice());
            }
            System.out.println("Tổng giá trị: " + cart.getTotalCartValue());
        } else {
            System.out.println("Giỏ hàng của bạn trống.");
        }
    }

    // Xoá toàn bộ giỏ hàng
    public void clearCart(String username) {
        Cart cart = carts.get(username);

        if (cart != null && !cart.getItems().isEmpty()) {
            cart.clearCart();
            System.out.println("Đã xoá toàn bộ sản phẩm trong giỏ hàng của bạn...");
        } else {
            System.out.println("Giỏ hàng trống hoặc không tìm thấy giỏ hàng.");
        }
        writeCartsToFile(new ArrayList<>(carts.values()));
    }

    // Lấy giỏ hàng của một buyer theo username
    public Cart getCartByUsername(String username) {
        return carts.get(username);
    }

    // Phương thức cho Admin/Seller xem giỏ hàng của tất cả người dùng
    public Map<String, Cart> getAllCartsForAdminOrSeller() {
        return new HashMap<>(carts);
    }


    /**********   Handle File   ***********/
    // Ghi dữ liệu giỏ hàng vào file CSV
    public void writeCartsToFile(List<Cart> carts) {
        try (FileWriter writer = new FileWriter(FILE_NAME)) {
            writer.write("Username|Mã sản phẩm|Tên sản phẩm|Số lượng|Giá\n");

            for (Cart cart : carts) {
                for (CartItem item : cart.getItems()) {
                    writer.write(String.format("%s|%d|%s|%d|%.2f%n",
                            cart.getUsername(),
                            item.getProductId(),
                            item.getProductName(),
                            item.getQuantity(),
                            item.getPrice()));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Đọc dữ liệu giỏ hàng từ file CSV
    public Map<String, Cart> readCartsFromFile() {
        Map<String, Cart> carts = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line = reader.readLine(); // Bỏ qua tiêu đề
            while ((line = reader.readLine()) != null) {
                String[] data = line.split("\\|");
                String username = data[0];
                int productId = Integer.parseInt(data[1]);
                String productName = data[2];
                int quantity = Integer.parseInt(data[3]);
                double price = Double.parseDouble(data[4]);

                CartItem item = new CartItem(productId, productName, quantity, price);
                carts.computeIfAbsent(username, k -> new Cart(username)).addItem(item);
            }
        } catch (FileNotFoundException e) {
            e.fillInStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return carts;
    }
}
