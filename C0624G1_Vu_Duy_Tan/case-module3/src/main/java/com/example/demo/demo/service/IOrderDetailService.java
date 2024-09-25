package com.example.demo.demo.service;



import com.example.demo.demo.model.OrderDetail;

import java.sql.SQLException;
import java.util.List;

public interface IOrderDetailService {
    List<OrderDetail> getOrderDetailByOrderId(int orderId);
}
