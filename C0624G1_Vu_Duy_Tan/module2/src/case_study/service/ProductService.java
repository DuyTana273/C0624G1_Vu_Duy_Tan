package case_study.service;

import case_study.controller.ProductController;
import case_study.model.cart_manage.Cart;
import case_study.model.product_manage.Laptop;
import case_study.view.ProductView;
import case_study.view.UserView;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class ProductService {

    //===== ĐỊNH NGHĨA THUỘC TÍNH =====
    private UserView userView;
    private Map<String, Laptop> laptops = new HashMap<>();
    private final Cart cart;

    // Link File csv
    private static final String PRODUCT_FILE_PATH = "src/case_study/store/products.csv";

    //===== CONSTRUCTOR =====
    public ProductService(UserView userView) {
        this.userView = userView;
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

    //===== HIỂN THỊ THÔNG TIN LAPTOP THEO THƯƠNG HIỆU =====
    public void displayLaptopsByBrand(String brand) {
        List<Laptop> filteredLaptops = getLaptopsByBrand(brand);

        if (filteredLaptops.isEmpty()) {
            userView.showMessage("Không có sản phẩm nào thuộc thương hiệu: " + brand);
            return;
        }

        System.out.println("==== LAPTOP THƯƠNG HIỆU: " + brand.toUpperCase() + " ====");
        for (int i = 0; i < filteredLaptops.size(); i++) {
            System.out.println((i + 1) + ". " + filteredLaptops.get(i));
        }
    }

    //===== HIỂN THỊ TẤT CẢ LAPTOP =====
    public void displayAllProducts() {
        if (laptops.isEmpty()) {
            System.out.println("Danh sách sản phẩm trống.");
        } else {
            List<String> ids = new ArrayList<>(laptops.keySet());
            Collections.sort(ids, (s1, s2) -> Integer.compare(Integer.parseInt(s1), Integer.parseInt(s2)));
            System.out.println("Danh sách tất cả các laptop:");
            for (String id : ids) {
                System.out.println(laptops.get(id));
            }
        }
    }

    public Map<String, Laptop> getAllLaptops() {
        return laptops;
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
            writer.write("ProductId|Name|Brand|Price|Description\n");
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
            reader.readLine();
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
