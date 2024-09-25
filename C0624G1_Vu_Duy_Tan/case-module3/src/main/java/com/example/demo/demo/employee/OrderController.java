package com.example.demo.demo.employee;


import com.example.demo.demo.model.Order;
import com.example.demo.demo.model.OrderDetail;
import com.example.demo.demo.model.User;
import com.example.demo.demo.service.IOrderService;
import com.example.demo.demo.service.OrderService;
import com.google.gson.Gson;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "OrderController", urlPatterns = "/orders")
public class OrderController extends HttpServlet {
    private IOrderService orderService = new OrderService();


    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        try {
            switch (action) {
                case "updateStatus":
                    UpdateOrderStatus(request, response);
                    break;
                case "add-to-cart":
                    addToCart(request, response);
                    break;
                case "removeFromCart":
                    removeFromCart(request, response);
                    break;
                case "checkout":
                    checkout(request, response);
                    break;
                case"deleteOrder":
                    deleteOrder(request, response);
                    break;
                default:
                    response.sendRedirect("/home.jsp");
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            if (action == null) {
                action = "";
            }
            switch (action) {
                case "searchOrderId":
                    searchOrderById(request, response);
                    break;
                case "searchDate":
                    searchByDate(request, response);
                    break;
                case "into-cart":
                    displayCart(request, response);
                    break;

                default:
                    listOrders(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void displayCart(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            request.setAttribute("loginError", "Bạn chưa đăng nhập");
            request.getRequestDispatcher("/home.jsp").forward(request, response);
            return;
        }

        Order existingOrder = orderService.selectOrderByStatus(user.getId());
        if (existingOrder != null) {
            List<OrderDetail> orderDetails = orderService.getOrderDetails(existingOrder.getOrderId());
            double totalPrice = orderService.getTotalOrderPrice(existingOrder.getOrderId());
            int itemCount = orderDetails.size();
            request.setAttribute("orderDetails", orderDetails);
            request.setAttribute("totalPrice", totalPrice);
            request.setAttribute("itemCount", itemCount);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/user_info/cart.jsp");
            dispatcher.forward(request, response);
        } else {
            request.setAttribute("message1", "Giỏ hàng của bạn đang trống");
            request.setAttribute("itemCount", 0);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/user_info/cart.jsp");
            dispatcher.forward(request, response);
        }
    }

    private void addToCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            request.setAttribute("loginError", "Bạn chưa đăng nhập");
            request.getRequestDispatcher("/home.jsp").forward(request, response);
            return;
        }
        int productId = Integer.parseInt(request.getParameter("productId"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        Order existingOrder = orderService.selectOrderByStatus(user.getId());

        if (existingOrder == null) {
            Order newOrder = new Order();
            newOrder.setUserId(user.getId());
            newOrder = orderService.createOrder(newOrder);
            System.out.println(newOrder.toString());
            existingOrder = newOrder;
        }
        orderService.addProductToOrder(existingOrder.getOrderId(), productId, quantity);
        request.getSession().setAttribute("message", "Thêm sản phầm thành công");
        request.getSession().setAttribute("status", "error");
        response.sendRedirect("/products?action=");

    }

    private void listOrders(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Order> listOrder = orderService.selectAllOrders();
        request.setAttribute("listOrder", listOrder);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/product/order_management.jsp");
        dispatcher.forward(request, response);
    }

    private void UpdateOrderStatus(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int orderId = Integer.parseInt(request.getParameter("orderId"));
        int userId = Integer.parseInt(request.getParameter("userId"));
        String status = request.getParameter("status");

        orderService.updateOrderStatus(orderId, userId, status);
        response.sendRedirect("/orders");
    }

    private void searchByDate(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        String searchDate = request.getParameter("date");
        List<Order> orders;

        if (searchDate != null && !searchDate.isEmpty()) {
            orders = orderService.getOrdersByDate(searchDate);
        } else {
            orders = orderService.selectAllOrders();
        }

        Gson gson = new Gson();
        List<String> stringList = new ArrayList<>();
        for (int i = 0; i <orders.size() ; i++) {
            stringList.add(gson.toJson(orders.get(i)));
        }
        // Gửi dữ liệu JSON về client
        PrintWriter out = response.getWriter();
        out.print(stringList);
        out.flush();

    }

    private void searchOrderById(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        String orderIdStr = request.getParameter("orderId");
        List<Order> orders = new ArrayList<>();

        if (orderIdStr != null && !orderIdStr.isEmpty()) {
            int orderId = Integer.parseInt(orderIdStr);
            Order order = orderService.selectOrderByStatus(orderId);
            if (order != null) {
                orders.add(order);
            }
        }
        request.setAttribute("listOrder", orders);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/product/order_management.jsp");
        dispatcher.forward(request, response);
    }

    private void removeFromCart(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            request.setAttribute("loginError", "Bạn chưa đăng nhập");
            request.getRequestDispatcher("/home.jsp").forward(request, response);
            return;
        }

        int productId = Integer.parseInt(request.getParameter("productId"));
        Order existingOrder = orderService.selectOrderByStatus(user.getId());

        if (existingOrder != null) {
            orderService.removeProductFromOrder(existingOrder.getOrderId(), productId);
            response.sendRedirect("/orders?action=into-cart");
        } else {
            request.setAttribute("message", "Không tìm thấy giỏ hàng để xóa sản phẩm.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/user_info/cart.jsp");
            dispatcher.forward(request, response);
        }
    }

    private void checkout(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            request.setAttribute("loginError", "Bạn chưa đăng nhập");
            request.getRequestDispatcher("/home.jsp").forward(request, response);
            return;
        }

        Order existingOrder = orderService.selectOrderByStatus(user.getId());
        if (existingOrder != null) {
            int orderId = existingOrder.getOrderId();


            List<OrderDetail> orderDetails = orderService.getOrderDetails(orderId);
            boolean canProceed = true;

            for (OrderDetail detail : orderDetails) {
                int productId = detail.getProductId();
                int quantity = detail.getProductQuantity();


                int availableQuantity = orderService.getAvailableQuantity(productId);
                if (availableQuantity < quantity) {
                    canProceed = false;
                    request.setAttribute("message", "Sản phẩm '" + detail.getProductName() + "' không đủ để đặt hàng.");
                    break;
                }
            }

            if (canProceed) {
                orderService.updateOrderStatus(orderId, user.getId(), "processing");
                orderService.removeAllOrderDetails(orderId);
                request.setAttribute("message", "Đơn hàng đã được đặt và đang được xử lý.");
                response.sendRedirect("/orders?action=into-cart");
            } else {

                RequestDispatcher dispatcher = request.getRequestDispatcher("/user_info/cart.jsp");
                dispatcher.forward(request, response);
            }
        } else {
            request.setAttribute("message", "Không tìm thấy đơn hàng để thanh toán.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/user_info/cart.jsp");
            dispatcher.forward(request, response);
        }
    }
    private void deleteOrder(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            // Lấy orderId từ request
            int orderId = Integer.parseInt(request.getParameter("orderId"));

            // Gọi phương thức xóa từ repository
            orderService.deleteOrder(orderId);

            // Chuyển hướng về trang quản lý đơn hàng sau khi xóa thành công
            response.sendRedirect(request.getContextPath() + "/orders?action=list");

        } catch (NumberFormatException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid order ID");
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error deleting order");
        }
    }

}

