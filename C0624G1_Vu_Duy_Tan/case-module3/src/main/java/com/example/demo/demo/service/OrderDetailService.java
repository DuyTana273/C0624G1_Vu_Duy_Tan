package com.example.demo.demo.service;



import com.example.demo.demo.model.OrderDetail;
import com.example.demo.demo.repository.user.IOrderDetailRepository;
import com.example.demo.demo.repository.user.OrderDetailRepository;

import java.util.List;


public class OrderDetailService implements IOrderDetailService {
    IOrderDetailRepository orderDetailRepository = new OrderDetailRepository();


    @Override
    public List<OrderDetail> getOrderDetailByOrderId(int orderId) {
        return orderDetailRepository.getOrderDetailByOrderId(orderId);
    }
}

