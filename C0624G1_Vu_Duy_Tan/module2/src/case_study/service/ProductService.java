package case_study.service;

import case_study.controller.ProductController;
import case_study.model.cart_manage.order.Order;
import case_study.model.product_manage.Laptop;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ProductService {

    //===== ĐỊNH NGHĨA THUỘC TÍNH =====
    private static List<Laptop> laptops;
    private final List<Order> orders;
    private final List<Laptop> cart;

    //===== CONSTRUCTOR =====
    public ProductService() {
        laptops = new ArrayList<>();
        this.orders = new ArrayList<>();
        this.cart = new ArrayList<>();
        initializeDefaultProducts();
    }

    private void initializeDefaultProducts() {
        // LAPTOP DEFAULT
        laptops.add(new Laptop(1, "MacBook Air M1", "Apple", 25000000, "Mẫu laptop mỏng nhẹ, hiệu suất cao."));
        laptops.add(new Laptop(2, "MacBook Pro 14", "Apple", 45000000, "Mẫu laptop chuyên nghiệp cho công việc sáng tạo."));
        laptops.add(new Laptop(3, "Dell XPS 13", "Dell", 30000000, "Mẫu laptop nhỏ gọn với thiết kế sang trọng."));
        laptops.add(new Laptop(4, "HP Spectre x360", "HP", 40000000, "Mẫu laptop 2 trong 1 với khả năng gập linh hoạt."));
        laptops.add(new Laptop(5, "Asus ROG Zephyrus G14", "Asus", 35000000, "Mẫu laptop gaming mạnh mẽ với thiết kế nhỏ gọn."));
        laptops.add(new Laptop(6, "Lenovo ThinkPad X1 Carbon", "Lenovo", 42000000, "Mẫu laptop cho doanh nhân với hiệu suất ổn định."));
        laptops.add(new Laptop(7, "Acer Swift 3", "Acer", 20000000, "Mẫu laptop giá rẻ nhưng hiệu năng tốt cho sinh viên."));
        laptops.add(new Laptop(8, "Microsoft Surface Laptop 4", "Microsoft", 36000000, "Mẫu laptop thanh lịch với màn hình cảm ứng."));
        laptops.add(new Laptop(9, "Razer Blade Stealth", "Razer", 38000000, "Mẫu laptop gaming mỏng nhẹ với hiệu suất cao."));
    }

    //===== DISPLAY ALL LAPTOP =====
    public void displayAllProducts() {
        if (laptops.isEmpty()) {
            System.out.println("Danh sách sản phẩm trống.");
        } else {
            System.out.println("Danh sách tất cả các laptop:");
            for (Laptop laptop : laptops) {
                System.out.println(laptop);
            }
        }
    }

    //===== LẤY LAPTOP THEO ID =====
    public Optional<Laptop> getLaptopById(int id) {
        return laptops.stream()
                .filter(laptop -> laptop.getProductId() == id)
                .findFirst();
    }

    //===== LẤY LAPTOP THEO BRAND =====
    public static List<Laptop> getLaptopsByBrand(String brand) {
        return laptops.stream()
                .filter(laptop -> laptop.getBrand().equalsIgnoreCase(brand))
                .collect(Collectors.toList());
    }

    //===== THÊM LAPTOP VÀO GIỎ HÀNG =====
    public void addToCart(Laptop laptop) {
        if (laptop == null) {
            System.out.println("Sản phẩm không hợp lệ. Không thể thêm vào giỏ hàng.");
            return;
        }
        if (cart.contains(laptop)) {
            System.out.println("Sản phẩm " + laptop.getName() + " đã có trong giỏ hàng.");
        } else {
            cart.add(laptop);
            System.out.println("Đã thêm " + laptop.getName() + " vào giỏ hàng thành công!");
        }
    }

    public void addLaptop(Laptop laptop) {
        if (laptop != null) {
            laptops.add(laptop);
        }
    }

    public List<Laptop> getLaptops() {
        return new ArrayList<>(laptops);
    }

    public void addOrder(Order order) {
        if (order != null) {
            orders.add(order);
        }
    }

    public List<Order> getOrders() {
        return new ArrayList<>(orders);
    }
}
