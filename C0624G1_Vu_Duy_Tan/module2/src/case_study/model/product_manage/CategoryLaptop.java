package case_study.model.product_manage;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class CategoryLaptop {

    public static final String WINDOWS_CATEGORY = "Windows";
    public static final String APPLE_CATEGORY = "Apple";

    private String categoryName;
    private List<Laptop> laptops;

    public CategoryLaptop(String categoryName) {
        if (!categoryName.equalsIgnoreCase(WINDOWS_CATEGORY) && !categoryName.equalsIgnoreCase(APPLE_CATEGORY)) {
            throw new IllegalArgumentException("Tên danh mục không hợp lệ. Chỉ hỗ trợ danh mục 'Windows' và 'Apple'.");
        }
        this.categoryName = categoryName;
        this.laptops = new ArrayList<>();
    }

    public static boolean isWindowsBrand(String brand) {
        return !brand.equalsIgnoreCase("Apple");
    }

    public static boolean isAppleBrand(String brand) {
        return brand.equalsIgnoreCase("Apple");
    }

    public void addLaptopToCategory(Laptop laptop) {
        if ((categoryName.equalsIgnoreCase(WINDOWS_CATEGORY) && isWindowsBrand(laptop.getBrand())) ||
                (categoryName.equalsIgnoreCase(APPLE_CATEGORY) && isAppleBrand(laptop.getBrand()))) {
            laptops.add(laptop);
        } else {
            System.out.println("Laptop không thuộc danh mục " + categoryName + ". Không thể thêm.");
        }
    }

    public void displayLaptopsInCategory() {
        System.out.println("===== Danh mục: " + categoryName + " =====");

        if (laptops.isEmpty()) {
            System.out.println("Không có laptop nào trong danh mục này.");
        } else {
            List<Laptop> sortedLaptops = laptops.stream()
                    .sorted(Comparator.comparingInt(Laptop::getProductId))
                    .toList();

            for (Laptop laptop : sortedLaptops) {
                System.out.println(laptop);
            }
        }
    }

    public List<Laptop> getLaptops() {
        return laptops.stream()
                .sorted(Comparator.comparingInt(Laptop::getProductId))
                .collect(Collectors.toList());
    }
}
