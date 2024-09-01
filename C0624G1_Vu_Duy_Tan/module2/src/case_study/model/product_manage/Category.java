package case_study.model.product_manage;

import java.util.HashMap;
import java.util.Map;

public class Category {
    private final String name;
    private final Map<String, SubCategory> subCategories;

    public Category(String name) {
        this.name = name;
        this.subCategories = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public void addSubCategory(SubCategory subCategory) {
        subCategories.put(subCategory.getName(), subCategory);
    }

    public void removeSubCategory(String subCategoryName) {
        subCategories.remove(subCategoryName);
    }

    public SubCategory getSubCategory(String subCategoryName) {
        return subCategories.get(subCategoryName);
    }

    public Map<String, SubCategory> getAllSubCategories() {
        return subCategories;
    }

    @Override
    public String toString() {
        return "Category{" +
                "name='" + name + '\'' +
                ", subCategories=" + subCategories +
                '}';
    }
}
