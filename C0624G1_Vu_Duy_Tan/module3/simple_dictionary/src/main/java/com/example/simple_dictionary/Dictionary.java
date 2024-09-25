package com.example.simple_dictionary;

import java.io.*;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "TranslateServlet", urlPatterns = "/TranslateServlet")
public class Dictionary extends HttpServlet {
    @Override
    public void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "search";
        }

        switch (action) {
            case "search":
                Map<String , String> dictionary = new HashMap<>();
                dictionary.put("hello", "Xin chào");
                dictionary.put("how", "Thế nào");
                dictionary.put("book", "Quyển vở");
                dictionary.put("computer", "Máy tính");

                String search = request.getParameter("search");
                PrintWriter out = response.getWriter();

                out.println("<html>");
                String result = dictionary.get(search);
                if(result != null) {
                    out.println("Word: " + search + "<br>");
                    out.println("Result: " + result);
                } else {
                    out.println("Not found");
                }
                out.println("</html>");
        }
    }
}