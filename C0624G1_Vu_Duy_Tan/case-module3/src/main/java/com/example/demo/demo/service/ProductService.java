package com.example.demo.demo.service;

import com.example.demo.demo.model.Product;
import com.example.demo.demo.repository.user.IProductRepository;
import com.example.demo.demo.repository.user.ProductRepository;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class ProductService implements IProductService {
    IProductRepository productRepository = new ProductRepository();

    @Override
    public List<Product> findAll(String sql) {
        return productRepository.findAll(sql);
    }

    @Override
    public Product getProductByName(String name) {
        return productRepository.getProductByName(name);
    }

    @Override
    public Product getProductById(int productId) {
        return productRepository.getProductById(productId);
    }

    @Override
    public List<Product> searchProducts(String keyword) throws SQLException {
        return productRepository.searchProducts(keyword);
    }

    @Override
    public List<Product> selectAllProducts() {
        return productRepository.selectAllProducts();
    }

    @Override
    public void addProduct(Product product) {
        productRepository.addProduct(product);
    }

    @Override
    public void editProduct(Product product) throws IOException, SQLException {
        productRepository.editProduct(product);
    }

    @Override
    public void deleteProduct(int productId) throws SQLException {
        productRepository.deleteProduct(productId);
    }

    @Override
    public List<Product> findAllPaginated(String sql, int page, int productsPerPage) {
        return productRepository.findAllPaginated(sql, page, productsPerPage);
    }

    @Override
    public int getTotalProducts(String sql) {
        return productRepository.getTotalProducts(sql);
    }

    @Override
    public List<Product> selectProductsPaginated(int offset, int limit) throws SQLException {
        return productRepository.selectProductsPaginated(offset, limit);
    }

    @Override
    public int getTotalProducts() throws SQLException {
        return productRepository.getTotalProducts();
    }
}
