package com.example.demo.demo.repository.user;

import com.example.demo.demo.model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProductRepository implements IProductRepository {
    private static final String SELECT_PRODUCT = "SELECT * FROM Products WHERE name LIKE ?";
    private static final String SELECT_PRODUCT_BY_NAME = "SELECT * FROM Products WHERE name = ?";
    public static final String SELECT_ALL_PRODUCTS = "SELECT * FROM PRODUCTS ORDER BY product_id DESC ;";
    public static final String ADD_PRODUCT_SQL = "INSERT INTO products (name, description, brand, price, stock) VALUES (?, ?, ?, ?, ?);";
    private static final String UPDATE_PRODUCT_SQL ="UPDATE products SET name = ?, description = ?, brand = ?, price = ?, stock = ? WHERE product_id = ?;";
    private static final String DELETE_PRODUCT_SQL = "DELETE FROM products WHERE product_id = ?;";
    private static final String SELECT_ALL_PRODUCTS_SORTED = "SELECT * FROM Products ORDER BY ? ?";
    BaseRepository baseRepository = new BaseRepository();

    @Override
    public List<Product> findAll(String sql) {
        List<Product> products = new ArrayList<>();
        try (Connection conn = baseRepository.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                products.add(extractProductFromResultSet(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return products;
    }
    @Override
    public List<Product> findAllPaginated(String sql, int page, int productsPerPage) {
        List<Product> products = new ArrayList<>();
        int start = (page - 1) * productsPerPage;
        String paginatedSql = sql + " LIMIT ?, ?";
        try (Connection conn = baseRepository.getConnection();
             PreparedStatement ps = conn.prepareStatement(paginatedSql)) {
            ps.setInt(1, start);
            ps.setInt(2, productsPerPage);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                products.add(extractProductFromResultSet(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return products;
    }
    private Product extractProductFromResultSet(ResultSet rs) throws SQLException {
        String name = rs.getString("name");
        String description = rs.getString("description");
        String brand = rs.getString("brand");
        double price = rs.getDouble("price");
        int stock = rs.getInt("stock");
        String image = rs.getString("image");
        return new Product(name, description, brand, price, stock, image);
    }

    @Override
    public int getTotalProducts(String sql) {
        String countSql = "SELECT COUNT(*) FROM (" + sql + ") AS count_table";
        try (Connection conn = baseRepository.getConnection();
             PreparedStatement ps = conn.prepareStatement(countSql)) {
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

    @Override
    public Product getProductById(int productId) {
        Product product = null;
        try (Connection conn = baseRepository.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM Products WHERE product_id = ?")) {
            ps.setInt(1, productId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String name = rs.getString("name");
                String description = rs.getString("description");
                String brand = rs.getString("brand");
                double price = rs.getDouble("price");
                int stock = rs.getInt("stock");
                String image = rs.getString("image");
                product = new Product(productId, name, description, brand, price, stock, image);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }

    @Override
    public Product getProductByName(String name) {
        Product product = null;
        try (Connection conn = baseRepository.getConnection();
             PreparedStatement ps = conn.prepareStatement(SELECT_PRODUCT_BY_NAME)) {
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("product_id");
                String productName = rs.getString("name");
                String description = rs.getString("description");
                String brand = rs.getString("brand");
                double price = rs.getDouble("price");
                int stock = rs.getInt("stock");
                String image = rs.getString("image");
                product = new Product(id,productName, description, brand, price, stock, image);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }

    @Override
    public List<Product> searchProducts(String keyword) throws SQLException {
        List<Product> products = new ArrayList<>();
        try (Connection conn = baseRepository.getConnection();
             PreparedStatement ps = conn.prepareStatement(SELECT_PRODUCT)) {
           ps.setString(1,  keyword + "%");;
            ResultSet rs = ps.executeQuery();
            System.out.println(rs);
                while (rs.next()) {
                    int id = rs.getInt("product_id");
                    String productName = rs.getString("name");
                    String description = rs.getString("description");
                    String brand = rs.getString("brand");
                    double price = rs.getDouble("price");
                    int stock = rs.getInt("stock");
                    String image = rs.getString("image");
                    Product product = new Product(id,productName, description, brand, price, stock, image);
                    products.add(product);
                }
            }

        return products;
    }
    public List<Product> selectAllProducts() {
        List<Product> products = new ArrayList<>();

        try (Connection connection = baseRepository.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PRODUCTS)) {
            System.out.println(preparedStatement);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                String brand = resultSet.getString("brand");
                double price = resultSet.getDouble("price");
                int stock = resultSet.getInt("stock");
                int productId = resultSet.getInt("product_id");

                Product product = new Product(productId, name, description, brand, price, stock);
                products.add(product);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return products;

    }

    public void addProduct(Product product) {
        try {
            PreparedStatement preparedStatement = null;
            Connection connection = baseRepository.getConnection();
            preparedStatement = connection.prepareStatement(ADD_PRODUCT_SQL);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getDescription());
            preparedStatement.setString(3, product.getBrand());
            preparedStatement.setDouble(4, product.getPrice());
            preparedStatement.setInt(5, product.getStock());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public void editProduct(Product product) throws  SQLException {

        try (Connection connection = baseRepository.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PRODUCT_SQL)) {

            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getDescription());
            preparedStatement.setString(3, product.getBrand());
            preparedStatement.setDouble(4, product.getPrice());
            preparedStatement.setInt(5, product.getStock());
            preparedStatement.setInt(6, product.getProductId());

            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Product was updated successfully!");
            }
        } catch (SQLException e) {
            System.err.println("Error updating product: " + e.getMessage());
            throw e;
        }
    }
    public void deleteProduct(int productId) throws SQLException {

        try (Connection connection = baseRepository.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PRODUCT_SQL)) {
            preparedStatement.setInt(1, productId);

            int rowsDeleted = preparedStatement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Product was deleted successfully!");
            } else {
                System.out.println("No product found with the given ID.");
            }
        } catch (SQLException e) {
            System.err.println("Error deleting product: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public List<Product> selectProductsPaginated(int offset, int limit) throws SQLException {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM products LIMIT ? OFFSET ?";

        try (Connection conn = baseRepository.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, limit);
            pstmt.setInt(2, offset);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Product product = new Product();
                    product.setProductId(rs.getInt("product_id"));
                    product.setName(rs.getString("name"));
                    product.setDescription(rs.getString("description"));
                    product.setBrand(rs.getString("brand"));
                    product.setPrice(rs.getDouble("price"));
                    product.setStock(rs.getInt("stock"));
                    product.setImage(rs.getString("image"));
                    products.add(product);
                }
            }
        }

        return products;
    }

    @Override
    public int getTotalProducts() throws SQLException {
        String sql = "SELECT COUNT(*) FROM products";

        try (Connection conn = baseRepository.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            if (rs.next()) {
                return rs.getInt(1);
            }
        }

        return 0;
    }

}

