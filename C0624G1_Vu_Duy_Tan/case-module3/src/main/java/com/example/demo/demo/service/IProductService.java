package com.example.demo.demo.service;

import com.example.demo.demo.model.Product;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface IProductService {
    List<Product> findAll(String sql);
    Product getProductByName(String name);
    Product getProductById(int productId);
    List<Product> searchProducts(String keyword) throws SQLException;
    List<Product> selectAllProducts();
    void addProduct(Product product);
    void editProduct(Product product) throws IOException, SQLException;
    void deleteProduct(int productId) throws SQLException;
    List<Product> findAllPaginated(String sql, int page, int productsPerPage);
    int getTotalProducts(String sql);
    List<Product> selectProductsPaginated(int offset, int limit) throws SQLException;
    int getTotalProducts() throws SQLException;

}
