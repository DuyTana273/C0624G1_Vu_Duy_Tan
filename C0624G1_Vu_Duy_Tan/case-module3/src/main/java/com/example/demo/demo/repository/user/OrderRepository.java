package com.example.demo.demo.repository.user;


import com.example.demo.demo.model.Order;
import com.example.demo.demo.model.OrderDetail;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static java.sql.DriverManager.getConnection;

public class OrderRepository implements IOrderRepository {
    private BaseRepository baseRepository = new BaseRepository();
    public static final String SELECT_ALL_ORDER =
            "SELECT " +
                    "o.order_id AS orderId, " +
                    "u.user_id AS userId, " +
                    "u.account AS customerName, " +
                    "o.order_date AS date, " +
                    "o.status AS status, " +
                    "SUM(od.quantity * p.price) AS sum " +
                    "FROM Orders o " +
                    "JOIN Users u ON o.user_id = u.user_id " +
                    "JOIN OrderDetails od ON o.order_id = od.order_id " +
                    "JOIN Products p ON od.product_id = p.product_id " +
                    "WHERE o.status IN ('shipped', 'processing') " +
                    "GROUP BY o.order_id, u.user_id, u.account, o.order_date, o.status";
    private static final String SELECT_ORDER_BY_ID =
            "SELECT " +
                    "u.user_id AS userId," +
                    "u.account AS customerName, " +
                    "o.order_date AS date, " +
                    "o.status AS status, " +
                    "SUM(od.quantity * p.price) AS total " +
                    "FROM Orders o " +
                    "JOIN Users u ON o.user_id = u.user_id " +
                    "JOIN OrderDetails od ON o.order_id = od.order_id " +
                    "JOIN Products p ON od.product_id = p.product_id " +
                    "where o.order_id = ? " +
                    "GROUP BY o.order_id, u.name, o.order_date, o.status ";

    private static final String UPDATE_ORDER_STATUS = "UPDATE Orders SET status = ? WHERE order_id = ? AND user_id = ?";
    private static final String GET_ORDER_BY_DAY =
            "SELECT o.order_id, o.user_id, u.name AS customer_name, o.order_date, o.status, " +
                    "       COALESCE(SUM(od.quantity * p.price), 0) AS total_sum " +
                    "FROM Orders o " +
                    "JOIN Users u ON o.user_id = u.user_id " +
                    "LEFT JOIN OrderDetails od ON o.order_id = od.order_id " +
                    "LEFT JOIN Products p ON od.product_id = p.product_id " +
                    "WHERE DATE(o.order_date) = ? " +
                    "GROUP BY o.order_id, o.user_id, u.name, o.order_date, o.status";
    private Order order = null;

    public List<Order> selectAllOrders() {
        List<Order> orders = new ArrayList<>();

        try (Connection connection = baseRepository.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_ORDER)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int orderId = resultSet.getInt("orderId");
                int userId = resultSet.getInt("userId");
                String customerName = resultSet.getString("customerName");
                String date = resultSet.getString("date");
                String status = resultSet.getString("status");
                double sum = resultSet.getDouble("sum");

                orders.add(new Order(orderId, userId, customerName, date, status, sum));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error Code: " + e.getErrorCode());
            System.out.println("SQL State: " + e.getSQLState());
            System.out.println("Message: " + e.getMessage());
        }
        return orders;
    }

    public Order selectOrderById(int orderId) throws SQLException {

        try (Connection connection = baseRepository.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ORDER_BY_ID)) {
            preparedStatement.setInt(1, orderId);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                // Lấy dữ liệu từ bảng
                int userId = rs.getInt("userId");
                String customerName = rs.getString("customerName");
                String orderDate = String.valueOf(rs.getDate("date"));
                double sum = rs.getDouble("total");
                String status = rs.getString("status");
                order = new Order(orderId, userId, customerName, orderDate, status, sum);
            }
        }
        return order;
    }

    public void updateOrderStatus(int orderId, int userId, String status) {
        try (Connection connection = baseRepository.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ORDER_STATUS)) {
            preparedStatement.setString(1, status);
            preparedStatement.setInt(2, orderId);
            preparedStatement.setInt(3, userId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Order> getOrdersByDate(String date) {
        List<Order> orders = new ArrayList<>();

        try (Connection connection = baseRepository.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ORDER_BY_DAY)) {
            preparedStatement.setString(1, date);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Order order = new Order();
                order.setOrderId(rs.getInt("order_id"));
                order.setUserId(rs.getInt("user_id"));
                order.setCustomerName(rs.getString("customer_name"));
                order.setDate(String.valueOf(rs.getTimestamp("order_date")));
                order.setStatus(rs.getString("status"));
                order.setSum(rs.getDouble("total_sum")); // Đặt tổng đơn hàng ở đây
                orders.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orders;
    }
    public void deleteOrder(int orderId) {
        String deleteOrderDetailsQuery = "DELETE FROM OrderDetails WHERE order_id = ?";
        String deleteOrderQuery = "DELETE FROM Orders WHERE order_id = ?";

        try (Connection connection = baseRepository.getConnection()) {
            // Bắt đầu transaction
            connection.setAutoCommit(false);

            // Xóa các chi tiết đơn hàng trước
            try (PreparedStatement deleteOrderDetailsStmt = connection.prepareStatement(deleteOrderDetailsQuery)) {
                deleteOrderDetailsStmt.setInt(1, orderId);
                deleteOrderDetailsStmt.executeUpdate();
            }

            // Xóa đơn hàng
            try (PreparedStatement deleteOrderStmt = connection.prepareStatement(deleteOrderQuery)) {
                deleteOrderStmt.setInt(1, orderId);
                deleteOrderStmt.executeUpdate();
            }

            // Commit transaction nếu không có lỗi
            connection.commit();

        } catch (SQLException e) {
            e.printStackTrace();

            }
        }


    private static final String SELECT_ORDER_BY_STATUS = " SELECT * FROM Orders WHERE user_id = ? AND status = 'pending'";
    private static final String INSERT_INTO_ORDER = "INSERT INTO Orders (user_id) VALUES (?)";
    private static final String INSERT_INTO_ORDER_BUY_NOW = "INSERT INTO Orders (user_id, status) VALUES (?, 'processing')";
    @Override
    public Order selectOrderByStatus(int userId) throws SQLException {
        try (Connection connection = baseRepository.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ORDER_BY_STATUS)) {
            preparedStatement.setInt(1, userId);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                int orderId = rs.getInt("order_id");
                Timestamp orderDate = rs.getTimestamp("order_date");
                String status = rs.getString("status");

                return new Order(orderId, String.valueOf(orderDate), status, userId);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public Order createOrder(Order order) throws SQLException {
        try (Connection connection = baseRepository.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO_ORDER, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, order.getUserId());
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet rs = preparedStatement.getGeneratedKeys()) {
                    if (rs.next()) {
                        int orderId = rs.getInt(1);
                        order.setOrderId(orderId);
                    }
                }
            }

            return order;
        }
    }
    public Order createOrderBuyNow(Order order) throws SQLException {
        try (Connection connection = baseRepository.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO_ORDER, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, order.getUserId());
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet rs = preparedStatement.getGeneratedKeys()) {
                    if (rs.next()) {
                        int orderId = rs.getInt(1);
                        order.setOrderId(orderId);
                    }
                }
            }
            return order;
        }
    }

    private static final String SELECT_ORDER_DETAIL = "SELECT quantity FROM OrderDetails WHERE order_id = ? AND product_id = ?";
    private static final String INSERT_ORDER_DETAIL = "INSERT INTO OrderDetails (order_id, product_id, quantity) VALUES (?, ?, ?)";
    private static final String UPDATE_ORDER_DETAIL = "UPDATE OrderDetails SET quantity = quantity + ? WHERE order_id = ? AND product_id = ?";

    @Override
    public void addProductToOrder(int orderId, int productId, int quantity) throws SQLException {
        try (Connection connection = baseRepository.getConnection()) {

            boolean exists;
            try (PreparedStatement selectStmt = connection.prepareStatement(SELECT_ORDER_DETAIL)) {
                selectStmt.setInt(1, orderId);
                selectStmt.setInt(2, productId);

                try (ResultSet rs = selectStmt.executeQuery()) {
                    exists = rs.next();
                }
            }

            if (exists) {
                try (PreparedStatement updateStmt = connection.prepareStatement(UPDATE_ORDER_DETAIL)) {
                    updateStmt.setInt(1, quantity);
                    updateStmt.setInt(2, orderId);
                    updateStmt.setInt(3, productId);
                    updateStmt.executeUpdate();
                }
            } else {

                try (PreparedStatement insertStmt = connection.prepareStatement(INSERT_ORDER_DETAIL)) {
                    insertStmt.setInt(1, orderId);
                    insertStmt.setInt(2, productId);
                    insertStmt.setInt(3, quantity);
                    insertStmt.executeUpdate();
                }
            }
        } catch (SQLException e) {

            throw new RuntimeException(e);
        }
    }

    @Override
    public List<OrderDetail> getOrderDetails(int orderId) throws SQLException {
        String query = "SELECT od.product_id, p.name, p.image, od.quantity, p.price " +
                "FROM OrderDetails od " +
                "JOIN Products p ON od.product_id = p.product_id " +
                "JOIN Orders o ON od.order_id = o.order_id " +
                "WHERE od.order_id = ? AND o.status = 'pending'";
        List<OrderDetail> orderDetails = new ArrayList<>();

        try (Connection connection = baseRepository.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, orderId);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int productId = rs.getInt("product_id");
                String productName = rs.getString("name");
                String productImage = rs.getString("image");
                int quantity = rs.getInt("quantity");
                double price = rs.getDouble("price");

                OrderDetail detail = new OrderDetail(productId, productName, productImage, quantity, price);
                orderDetails.add(detail);
            }
        }
        return orderDetails;
    }


    public double getTotalOrderPrice(int orderId) throws SQLException {
        String query = "SELECT SUM(od.quantity * p.price) AS total " +
                "FROM OrderDetails od " +
                "JOIN Products p ON od.product_id = p.product_id " +
                "JOIN Orders o ON od.order_id = o.order_id " +
                "WHERE od.order_id = ? AND o.status = 'pending'";
        double total = 0.0;

        try (Connection connection = baseRepository.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, orderId);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                total = rs.getDouble("total");
            }
        }
        return total;
    }

    @Override
    public void removeProductFromOrder(int orderId, int productId) throws SQLException {
        String sql = "DELETE FROM OrderDetails WHERE order_id = ? AND product_id = ?";
        try (Connection connection = baseRepository.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, orderId);
            statement.setInt(2, productId);
            statement.executeUpdate();
        }
    }

    @Override
    public int getAvailableQuantity(int productId) throws SQLException {
        String sql = "SELECT stock FROM products WHERE product_id = ?";
        try (Connection connection = baseRepository.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, productId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("stock");
            }
        }
        return 0;
    }

    @Override
    public void removeAllOrderDetails(int orderId) throws SQLException {
        String sql = "DELETE FROM OrderDetails WHERE order_id = ?";
        try (Connection connection = baseRepository.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, orderId);
            statement.executeUpdate();
        }

    }

}

