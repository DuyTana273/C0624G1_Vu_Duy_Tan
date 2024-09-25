package com.example.demo.demo.service;

import com.example.demo.demo.model.Order;
import com.example.demo.demo.model.OrderDetail;
import com.example.demo.demo.repository.user.IOrderRepository;
import com.example.demo.demo.repository.user.OrderRepository;

import java.sql.SQLException;
import java.util.List;

public class OrderService implements IOrderService {
    IOrderRepository orderRepository = new OrderRepository();


    @Override
    public List<Order> selectAllOrders() {
        return orderRepository.selectAllOrders();
    }

    @Override
    public Order selectOrderById(int orderId) throws SQLException {
        return orderRepository.selectOrderById(orderId);
    }

    @Override
    public void updateOrderStatus(int orderId, int userId, String status) {
        orderRepository.updateOrderStatus(orderId, userId, status);
    }

    @Override
    public Order selectOrderByStatus(int userId) throws SQLException {
        return orderRepository.selectOrderByStatus(userId);
    }

    @Override
    public Order createOrder(Order order) throws SQLException {
        return orderRepository.createOrder(order);
    }

    @Override
    public List<Order> getOrdersByDate(String date) {
        return orderRepository.getOrdersByDate(date);
    }

    @Override
    public void addProductToOrder(int orderId, int productId, int quantity) throws SQLException {
        orderRepository.addProductToOrder(orderId, productId, quantity);
    }

    @Override
    public List<OrderDetail> getOrderDetails(int orderId) throws SQLException {
        return orderRepository.getOrderDetails(orderId);
    }

    @Override
    public double getTotalOrderPrice(int orderId) throws SQLException {
        return orderRepository.getTotalOrderPrice(orderId);
    }

    @Override
    public void removeProductFromOrder(int orderId, int productId) throws SQLException {
        orderRepository.removeProductFromOrder(orderId, productId);
    }

    @Override
    public int getAvailableQuantity(int productId) throws SQLException {
        return orderRepository.getAvailableQuantity(productId);
    }

    @Override
    public void removeAllOrderDetails(int orderId) throws SQLException {
        orderRepository.removeAllOrderDetails(orderId);
    }

    @Override
    public void deleteOrder(int orderId) throws SQLException {
        orderRepository.deleteOrder(orderId);
    }

    @Override
    public Order createOrderBuyNow(Order order) throws SQLException {
        return orderRepository.createOrderBuyNow(order);
    }
}
