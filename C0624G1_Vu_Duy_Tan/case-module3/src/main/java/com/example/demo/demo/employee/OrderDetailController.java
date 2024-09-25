package com.example.demo.demo.employee;



import com.example.demo.demo.model.Order;
import com.example.demo.demo.model.OrderDetail;
import com.example.demo.demo.service.IOrderDetailService;
import com.example.demo.demo.service.IOrderService;
import com.example.demo.demo.service.OrderDetailService;
import com.example.demo.demo.service.OrderService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "OrderDetailController" , urlPatterns ="/orderdetails")
public class OrderDetailController extends HttpServlet {
    private IOrderDetailService orderDetailService = new OrderDetailService();
    private IOrderService orderService = new OrderService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        try {
            switch (action) {

                default:
                    listOrderDetails(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {

        }
    }
    private void listOrderDetails(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        int orderId = Integer.parseInt(request.getParameter("orderId"));
        List<OrderDetail> orderDetails = orderDetailService.getOrderDetailByOrderId(orderId);
        request.setAttribute("orderDetails", orderDetails);

        Order order = orderService.selectOrderById(orderId);
        request.setAttribute("order", order);
        RequestDispatcher dispatcher = request.getRequestDispatcher("product/order_detail.jsp");
        dispatcher.forward(request, response);


    }

}
