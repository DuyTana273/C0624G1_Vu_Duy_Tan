package case_study.model.product_manage;

import java.util.HashMap;
import java.util.Map;

public class SubCategory {
    private final String name;
    private final Map<Integer, Laptop> laptops;

    public SubCategory(String name) {
        this.name = name;
        this.laptops = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public void addLaptop(Laptop laptop) {
        laptops.put(laptop.getProductId(), laptop);
    }

    public void removeLaptop(int productId) {
        laptops.remove(productId);
    }

    public Laptop getLaptop(int productId) {
        return laptops.get(productId);
    }

    public Map<Integer, Laptop> getAllLaptops() {
        return laptops;
    }

    @Override
    public String toString() {
        return "SubCategory{" +
                "name='" + name + '\'' +
                ", laptops=" + laptops +
                '}';
    }
}
