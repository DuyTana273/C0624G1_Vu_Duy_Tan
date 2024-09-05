package case_study.service;

import case_study.model.cart_manage.cart.Cart;
import case_study.model.cart_manage.order.Order;
import case_study.model.product_manage.Laptop;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class ProductService {

    //===== ĐỊNH NGHĨA THUỘC TÍNH =====
    private Map<String, Laptop> laptops = new HashMap<>();
    private List<Order> orders = new ArrayList<>();
    //    private final List<Laptop> cart;
    private final Cart cart;
    // Link File csv
    private static final String PRODUCT_FILE_PATH = "src/case_study/store/products.csv";


    //===== CONSTRUCTOR =====
    public ProductService() {
        this.cart = new Cart();
        Map<String, Laptop> loadedLaptops = readProductsFromFile();
        if (!loadedLaptops.isEmpty()) {
            this.laptops = loadedLaptops;
        }

        // Tìm productId lớn nhất từ các sản phẩm hiện có và thiết lập lại idCounter
        int maxProductId = laptops.values().stream()
                .mapToInt(Laptop::getProductId)
                .max()
                .orElse(0);

        // Thiết lập lại giá trị cho idCounter
        Laptop.setIdCounter(new AtomicInteger(maxProductId));
    }

    //===== LẤY LAPTOP THEO ID =====
    public Optional<Laptop> getLaptopById(int productId) {
        return Optional.ofNullable(laptops.get(String.valueOf(productId)));
    }

    //===== LẤY LAPTOP THEO THƯƠNG HIỆU =====
    public List<Laptop> getLaptopsByBrand(String brand) {
        return laptops.values().stream()
                .filter(laptop -> laptop.getBrand().equalsIgnoreCase(brand))
                .collect(Collectors.toList());
    }

    //===== HIỂN THỊ TẤT CẢ LAPTOP =====
    public void displayAllProducts() {
        if (laptops.isEmpty()) {
            System.out.println("Danh sách sản phẩm trống.");
        } else {
            System.out.println("Danh sách tất cả các laptop:");
            laptops.values().forEach(System.out::println);
        }
    }

    //================= GIỎ HÀNG =================
    public void addToCart(Laptop laptop, int quantity) {
        cart.addItem(laptop, quantity);
        System.out.println("Đã thêm " + quantity + " sản phẩm " + laptop.getName() + " vào giỏ hàng.");
    }

    public void removeFromCart(int productId) {
        cart.removeItem(productId);
        System.out.println("Đã xóa sản phẩm có ID " + productId + " khỏi giỏ hàng.");
    }

    public void updateCartItemQuantity(int productId, int quantity) {
        cart.updateQuantity(productId, quantity);
        System.out.println("Đã cập nhật số lượng sản phẩm có ID " + productId + " trong giỏ hàng.");
    }

    public double getCartTotalValue() {
        return cart.getTotalValue();
    }

    public void clearCart() {
        cart.clearCart();
        System.out.println("Đã xóa tất cả sản phẩm trong giỏ hàng.");
    }

    public Cart getCart() {
        return cart;
    }

    //================= QUẢN LÝ SẢN PHẨM =================
    public void addLaptop(Laptop laptop) {
        if (laptop != null) {
            // Kiểm tra xem productId đã tồn tại chưa
            if (laptops.containsKey(String.valueOf(laptop.getProductId()))) {
                System.out.println("Sản phẩm với ID " + laptop.getProductId() + " đã tồn tại. Không thể thêm.");
            } else {
                laptops.put(String.valueOf(laptop.getProductId()), laptop);
                writeProductToFile(laptops); // Ghi sản phẩm vào file
                System.out.println("Đã thêm sản phẩm với ID " + laptop.getProductId() + " vào danh sách.");
            }
        } else {
            System.out.println("Laptop không hợp lệ. Không thể thêm vào danh sách.");
        }
    }

    public void removeLaptop(Laptop laptop) {
        if (laptop != null) {
            laptops.remove(String.valueOf(laptop.getProductId()));
            writeProductToFile(laptops);
            System.out.println("Sản phẩm với ID " + laptop.getProductId() + " đã được xóa.");
        } else {
            System.out.println("Laptop không tồn tại.");
        }
    }

    public void updateLaptop(int productId, String name, String brand, double price, String description) {
        Laptop laptop = laptops.get(String.valueOf(productId));

        if (laptop != null) {
            if (name != null && !name.isEmpty()) {
                laptop.setName(name);
            }
            if (brand != null && !brand.isEmpty()) {
                laptop.setBrand(brand);
            }
            if (price > 0) {
                laptop.setPrice(price);
            }
            if (description != null && !description.isEmpty()) {
                laptop.setSpecifications(description);
            }

            // Cập nhật lại laptop trong Map
            laptops.put(String.valueOf(productId), laptop);
            writeProductToFile(laptops);
            System.out.println("Đã cập nhật thông tin sản phẩm với ID " + productId + " thành công.");
        } else {
            System.out.println("Sản phẩm với ID " + productId + " không tồn tại.");
        }
    }

    /**********   Handle File   ***********/
    public void writeProductToFile(Map<String, Laptop> laptops) {
        try (FileWriter writer = new FileWriter(PRODUCT_FILE_PATH)) {
            writer.write("ProductId|Name|Brand|Price|Description\n"); // Thay dấu "," bằng "|"
            for (Laptop laptop : laptops.values()) {
                writer.write(String.format("%d|%s|%s|%.2f|%s%n",
                        laptop.getProductId(),
                        laptop.getName(),
                        laptop.getBrand(),
                        laptop.getPrice(),
                        laptop.getSpecifications()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Map<String, Laptop> readProductsFromFile() {
        Map<String, Laptop> laptops = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(PRODUCT_FILE_PATH))) {
            String line;
            reader.readLine(); // Đọc và bỏ qua dòng tiêu đề
            while ((line = reader.readLine()) != null) {
                String[] split = line.split("\\|");

                int productId = Integer.parseInt(split[0]);
                String name = split[1];
                String brand = split[2];
                double price = Double.parseDouble(split[3]);
                String description = split[4];

                Laptop laptop = new Laptop(productId, name, brand, price, description);
                laptops.put(String.valueOf(productId), laptop);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return laptops;
    }
}
