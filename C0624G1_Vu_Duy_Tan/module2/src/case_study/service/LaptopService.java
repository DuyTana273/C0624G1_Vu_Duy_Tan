package case_study.service;

import case_study.model.cart_manage.CartItem;
import case_study.model.product_manage.Laptop;
import case_study.model.product_manage.CategoryLaptop;
import case_study.util.SessionManager;
import case_study.view.UserView;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class LaptopService {
    // Link File csv
    private static final String PRODUCT_FILE_PATH = "C0624G1_Vu_Duy_Tan/C0624G1_Vu_Duy_Tan/module2/src/case_study/store/products.csv";

    //===== ĐỊNH NGHĨA THUỘC TÍNH =====
    private UserView userView;
    private Map<String, Laptop> laptops = new HashMap<>();
    private CategoryLaptop windowsCategory;
    private CategoryLaptop appleCategory;
    private CartService cartService;

    //===== CONSTRUCTOR =====
    public LaptopService(UserView userView, CartService cartService) {
        this.userView = userView;
        this.cartService = cartService;
        this.windowsCategory = new CategoryLaptop(CategoryLaptop.WINDOWS_CATEGORY);
        this.appleCategory = new CategoryLaptop(CategoryLaptop.APPLE_CATEGORY);

        Map<String, Laptop> loadedLaptops = readProductsFromFile();
        if (!loadedLaptops.isEmpty()) {
            this.laptops = loadedLaptops;
            categorizeLaptops();
        }

        // Tìm productId lớn nhất từ các sản phẩm hiện có và thiết lập lại idCounter
        int maxProductId = laptops.values().stream()
                .mapToInt(Laptop::getProductId)
                .max()
                .orElse(0);

        // Thiết lập lại giá trị cho idCounter
        Laptop.setIdCounter(maxProductId);
    }

    //===== PHÂN LOẠI LAPTOP =====
    private void categorizeLaptops() {
        for (Laptop laptop : laptops.values()) {
            if (CategoryLaptop.isWindowsBrand(laptop.getBrand())) {
                windowsCategory.addLaptopToCategory(laptop);
            } else if (CategoryLaptop.isAppleBrand(laptop.getBrand())) {
                appleCategory.addLaptopToCategory(laptop);
            }
        }
    }

    //===== LẤY LAPTOP THEO ID =====
    public Optional<Laptop> getLaptopById(int productId) {
        Laptop laptop = laptops.get(String.valueOf(productId));
        return Optional.ofNullable(laptop);
    }

    //===== LẤY LAPTOP THEO DANH MỤC (Category) =====
    public void displayLaptopsByCategory(String category) {
        try {
            // Hiển thị danh sách laptop theo danh mục
            if (category.equalsIgnoreCase(CategoryLaptop.WINDOWS_CATEGORY)) {
                windowsCategory.displayLaptopsInCategory();
            } else if (category.equalsIgnoreCase(CategoryLaptop.APPLE_CATEGORY)) {
                appleCategory.displayLaptopsInCategory();
            } else {
                userView.showMessage("Danh mục không tồn tại.");
                return;
            }

            // Hỏi người dùng có muốn mua sản phẩm không
            String response = userView.getInput("Bạn có muốn mua sản phẩm nào từ danh mục này không? (yes/no): ");

            if (response.equalsIgnoreCase("yes")) {
                // Nhập Mã sản phẩm và kiểm tra xem sản phẩm có hợp lệ hay không
                String productId = userView.getInput("Nhập Mã sản phẩm bạn muốn mua: ");
                Optional<Laptop> selectedProductOpt = getLaptopById(Integer.parseInt(productId));

                if (selectedProductOpt.isPresent()) {
                    Laptop laptop = selectedProductOpt.get();

                    // Kiểm tra số lượng hàng tồn kho
                    if (laptop.getQuantity() <= 0) {
                        System.out.println("Sản phẩm này đã hết hàng.");
                        return;
                    }

                    // Nhập số lượng sản phẩm
                    int quantity;
                    while (true) {
                        try {
                            quantity = Integer.parseInt(userView.getInput("Nhập số lượng sản phẩm bạn muốn mua: "));
                            if (quantity > 0 && quantity <= laptop.getQuantity()) {
                                break;
                            } else {
                                System.out.println("Số lượng không hợp lệ. Số lượng tối đa có thể mua là: " + laptop.getQuantity());
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Vui lòng nhập một số hợp lệ.");
                        }
                    }

                    // Thêm sản phẩm vào giỏ hàng
                    CartItem item = new CartItem(laptop.getProductId(), laptop.getName(), quantity, laptop.getPrice());
                    cartService.updateCart(SessionManager.getCurrentUser().getUsername(), item, true);

                    // Giảm số lượng sản phẩm trong kho
                    decreaseQuantity(laptop.getProductId(), quantity);

                } else {
                    System.out.println("Mã sản phẩm không hợp lệ.");
                }
            } else {
                System.out.println("Bạn đã chọn không mua sản phẩm.");
            }

        } catch (NumberFormatException e) {
            System.out.println("Lỗi: Định dạng Mã sản phẩm hoặc số lượng không hợp lệ. Vui lòng nhập số hợp lệ.");
        } catch (Exception e) {
            System.out.println("Đã xảy ra lỗi: " + e.getMessage());
        }
    }

    //===== HIỂN THỊ TẤT CẢ LAPTOP =====
    public void displayAllProducts() {
        if (laptops.isEmpty()) {
            System.out.println("Danh sách sản phẩm trống.");
        } else {
            List<Laptop> sortedLaptops = laptops.values().stream()
                    .sorted(Comparator.comparingInt(Laptop::getProductId))
                    .toList();

            System.out.println("====== TẤT CẢ SẢN PHẨM ======");
            for (Laptop laptop : sortedLaptops) {
                System.out.println(laptop);
            }
        }
    }
    //================= QUẢN LÝ SẢN PHẨM =================
    public void addLaptop(Laptop laptop, String categoryName) {
        if (laptops.containsKey(String.valueOf(laptop.getProductId()))) {
            System.out.println("Sản phẩm với ID " + laptop.getProductId() + " đã tồn tại. Không thể thêm.");
        } else {
            if (categoryName.equals(CategoryLaptop.APPLE_CATEGORY) && CategoryLaptop.isAppleBrand(laptop.getBrand())) {
                appleCategory.addLaptopToCategory(laptop);
                System.out.println("Đã thêm laptop vào danh mục Apple.");
            } else {
                windowsCategory.addLaptopToCategory(laptop);
                System.out.println("Đã thêm laptop vào danh mục Windows.");
            }

            // Thêm vào danh sách sản phẩm
            laptops.put(String.valueOf(laptop.getProductId()), laptop);
            writeProductToFile(laptops);
            System.out.println("Đã thêm sản phẩm với ID " + laptop.getProductId() + " vào danh sách và lưu vào file.");
        }
    }

    public void removeLaptop(Laptop laptop) {
        if (laptop != null) {
            if (CategoryLaptop.isWindowsBrand(laptop.getBrand())) {
                windowsCategory.getLaptops().remove(laptop);
            } else if (CategoryLaptop.isAppleBrand(laptop.getBrand())) {
                appleCategory.getLaptops().remove(laptop);
            }

            laptops.remove(String.valueOf(laptop.getProductId()));
            writeProductToFile(laptops);
            System.out.println("Sản phẩm với ID " + laptop.getProductId() + " đã được xóa.");
        } else {
            System.out.println("Laptop không tồn tại.");
        }
    }

    public void updateLaptop(int productId, String name, String brand, double price, int quantity, String description) {
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
            if (quantity >= 0) {
                laptop.setQuantity(quantity);
            }
            if (description != null && !description.isEmpty()) {
                laptop.setSpecifications(description);
            }

            if (!laptop.getBrand().equalsIgnoreCase(brand)) {
                if (CategoryLaptop.isWindowsBrand(laptop.getBrand())) {
                    windowsCategory.getLaptops().remove(laptop);
                } else if (CategoryLaptop.isAppleBrand(laptop.getBrand())) {
                    appleCategory.getLaptops().remove(laptop);
                }

                if (CategoryLaptop.isWindowsBrand(brand)) {
                    System.out.println("Thêm vào danh mục Window thành công");
                    windowsCategory.addLaptopToCategory(laptop);
                } else if (CategoryLaptop.isAppleBrand(brand)) {
                    System.out.println("Thêm vào danh mục Apple thành công");
                    appleCategory.addLaptopToCategory(laptop);
                }
            }

            laptops.put(String.valueOf(productId), laptop);
            writeProductToFile(laptops);
            System.out.println("Đã cập nhật thông tin sản phẩm với ID " + productId + " thành công.");
        } else {
            System.out.println("Sản phẩm với ID " + productId + " không tồn tại.");
        }
    }

    public void increaseQuantity(int productId, int amount) {
        Laptop laptop = laptops.get(String.valueOf(productId));
        if (laptop != null) {
            laptop.setQuantity(laptop.getQuantity() + amount);
            writeProductToFile(laptops);
        } else {
            System.out.println("Sản phẩm không tồn tại.");
        }
    }

    public void decreaseQuantity(int productId, int amount) {
        Laptop laptop = laptops.get(String.valueOf(productId));
        if (laptop != null) {
            int newQuantity = laptop.getQuantity() - amount;
            if (newQuantity >= 0) {
                laptop.setQuantity(newQuantity);
                writeProductToFile(laptops);
            } else {
                System.out.println("Không thể giảm số lượng quá mức hiện có.");
            }
        } else {
            System.out.println("Sản phẩm không tồn tại.");
        }
    }

    /**********   Handle File   ***********/
    // Ghi dữ liệu sản phẩm vào file CSV
    public void writeProductToFile(Map<String, Laptop> laptops) {
        try (FileWriter writer = new FileWriter(PRODUCT_FILE_PATH)) {
            writer.write("ProductId|Name|Brand|Price|Quantity|Description\n");

            List<Laptop> sortedLaptops = laptops.values().stream()
                    .sorted(Comparator.comparingInt(Laptop::getProductId))
                    .toList();

            for (Laptop laptop : sortedLaptops) {
                writer.write(String.format("%d|%s|%s|%.2f|%d|%s%n",
                        laptop.getProductId(),
                        laptop.getName(),
                        laptop.getBrand(),
                        laptop.getPrice(),
                        laptop.getQuantity(),
                        laptop.getSpecifications()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Đọc dữ liệu sản phẩm từ file CSV
    public Map<String, Laptop> readProductsFromFile() {
        Map<String, Laptop> laptops = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(PRODUCT_FILE_PATH))) {
            String line;
            reader.readLine(); // Bỏ qua tiêu đề

            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] split = line.split("\\|");
                if (split.length != 6) {
                    continue;
                }

                try {
                    int productId = Integer.parseInt(split[0].trim());
                    String name = split[1].trim();
                    String brand = split[2].trim();
                    double price = Double.parseDouble(split[3].trim());
                    int quantity = Integer.parseInt(split[4].trim());
                    String description = split[5].trim();

                    Laptop laptop = new Laptop(productId, name, brand, price, quantity, description);
                    laptops.put(String.valueOf(productId), laptop);

                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return laptops;
    }
}
