package com.example.productdiscountcalculator;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "TranslateServlet", urlPatterns = "/product-calculator")
public class ProductDiscountCalculator extends HttpServlet {
    @Override
    public void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Lấy dữ liệu từ form
        String description = request.getParameter("description");
        String listPriceStr = request.getParameter("listPrice");
        String discountPercentStr = request.getParameter("discountPercent");

        // Chuyển đổi giá trị thành kiểu số
        double listPrice = Double.parseDouble(listPriceStr);
        double discountPercent = Double.parseDouble(discountPercentStr);

        // Tính toán chiết khấu
        double discountAmount = listPrice * discountPercent * 0.01;
        double discountPrice = listPrice - discountAmount;

        // Thiết lập phản hồi cho trình duyệt
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head><title>Discount Calculator Result</title></head>");
        out.println("<body>");
        out.println("<h2>Discount Calculator Result</h2>");
        out.println("<p>Product Description: " + description + "</p>");
        out.println("<p>List Price: $" + String.format("%.2f", listPrice) + "</p>");
        out.println("<p>Discount Percent: " + String.format("%.2f", discountPercent) + "%</p>");
        out.println("<p>Discount Amount: $" + String.format("%.2f", discountAmount) + "</p>");
        out.println("<p>Discount Price: $" + String.format("%.2f", discountPrice) + "</p>");
        out.println("</body>");
        out.println("</html>");
    }
}