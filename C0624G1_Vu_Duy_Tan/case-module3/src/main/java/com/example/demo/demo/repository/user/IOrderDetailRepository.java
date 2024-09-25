package com.example.demo.demo.repository.user;


import com.example.demo.demo.model.OrderDetail;

import java.util.List;

public interface IOrderDetailRepository {
    List<OrderDetail> getOrderDetailByOrderId(int orderId);
}
