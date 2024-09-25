package com.example.javawebcurrencyconverter;

import java.io.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "ConvertServlet", value = "/convert")
public class ConvertServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        float rate = Float.parseFloat(request.getParameter("rate"));
        float usd = Float.parseFloat(request.getParameter("usd"));

        float vnd = rate * usd;

        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<h2>Rate: " + rate + "</h2>");
        out.println("<h2>USD: " + usd + "</h2>");
        out.println("<h2>VND: " + vnd + "</h2>");
        out.println("</html>");
    }
}