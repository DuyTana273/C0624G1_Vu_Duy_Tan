package com.example.demo.demo.repository.user;

import com.example.demo.demo.model.OrderDetail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailRepository implements IOrderDetailRepository {
    private Connection connection;
    private BaseRepository baseRepository = new BaseRepository();
    private static final String SELECT_ALL_ORDERDETAIL =
            "SELECT od.order_detail_id, p.name, p.price, od.quantity, (p.price * od.quantity) AS orderDetailSum " +
                    "FROM orderdetails od " +
                    "JOIN products p ON od.product_id = p.product_id " +
                    "WHERE od.order_id = ?";
    @Override
    public List<OrderDetail> getOrderDetailByOrderId(int orderId) {
        List<OrderDetail> orderDetails = new ArrayList<>();
        try (Connection connection = baseRepository.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_ORDERDETAIL)){
            preparedStatement.setInt(1,orderId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int orderDetailId = resultSet.getInt("order_detail_id");
                String productName = resultSet.getString("name");
                double productPrice = resultSet.getDouble("price");
                int productQuantity = resultSet.getInt("quantity");
                double orderDetailSum = resultSet.getDouble("orderDetailSum");
                OrderDetail orderDetail = new OrderDetail(orderDetailId, productName, productPrice, productQuantity,orderDetailSum);
                orderDetails.add(orderDetail);
            }
        }
        catch (
                SQLException e) {
            throw new RuntimeException(e);
        }
        return orderDetails;
    }

}
