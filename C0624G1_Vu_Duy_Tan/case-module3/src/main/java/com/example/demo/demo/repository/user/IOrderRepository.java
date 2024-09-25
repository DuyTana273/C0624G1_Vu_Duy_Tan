package com.example.demo.demo.repository.user;



import com.example.demo.demo.model.Order;
import com.example.demo.demo.model.OrderDetail;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public interface IOrderRepository {
    List<Order> selectAllOrders();
    Order selectOrderById(int orderId) throws SQLException;
    void updateOrderStatus(int orderId, int userId, String status);
    List<Order> getOrdersByDate(String date);
    public Order selectOrderByStatus(int userId) throws SQLException;
    public Order createOrder(Order order) throws SQLException;
    void addProductToOrder(int orderId, int productId, int quantity) throws SQLException;
    public double getTotalOrderPrice(int orderId) throws SQLException;
    public List<OrderDetail> getOrderDetails(int orderId) throws SQLException;
    public void removeProductFromOrder(int orderId, int productId) throws SQLException;
    public void removeAllOrderDetails(int orderId) throws SQLException;
    public int getAvailableQuantity(int productId) throws SQLException;
    public void deleteOrder(int orderId) throws SQLException;
    public Order createOrderBuyNow(Order order) throws SQLException;
}
