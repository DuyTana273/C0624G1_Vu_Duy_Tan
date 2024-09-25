package com.example.demo.demo.controller;

import com.example.demo.demo.model.Product;
import com.example.demo.demo.service.IProductService;
import com.example.demo.demo.service.ProductService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "ProductServlet", value = "/products")
public class ProductServlet extends HttpServlet {
    IProductService productService = new ProductService();
    private static final String SELECT_PRODUCT = "SELECT * FROM Products";
    private static final String SELECT_MSI = "SELECT * FROM Products WHERE NAME LIKE 'MSI%'";
    private static final String SELECT_ASUS = "SELECT * FROM Products WHERE NAME LIKE 'ASUS%'";
    private static final String SELECT_HP = "SELECT * FROM Products WHERE NAME LIKE 'HP%'";
    private static final String SELECT_LENOVO = "SELECT * FROM Products WHERE NAME LIKE 'LENOVO%'";
    private static final String SELECT_ACER = "SELECT * FROM Products WHERE NAME LIKE 'ACER%'";
    private static final String SELECT_GIGABYTE = "SELECT * FROM Products WHERE NAME LIKE 'GIGABYTE%'";
    private static final String SELECT_BIG_SALE = "SELECT * FROM Products WHERE DESCRIPTION LIKE '%BS'";
    private static final String SELECT_BEST_SELLER = "SELECT * FROM Products WHERE DESCRIPTION LIKE '%BSL'";
    private static final IProductService PRODUCT_SERVICE = new ProductService();
    private static final int PRODUCTS_PER_PAGE = 10;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        String sortBy = req.getParameter("sort");
        if (sortBy == null) {
            sortBy = "default";
        }
        switch (action) {
            case "get-msi":
                paginatedProductList(SELECT_MSI, req, resp,sortBy);
                break;
            case "get-asus":
                paginatedProductList(SELECT_ASUS, req, resp,sortBy);
                break;
            case "get-hp":
                paginatedProductList(SELECT_HP, req, resp,sortBy);
                break;
            case "get-lenovo":
                paginatedProductList(SELECT_LENOVO, req, resp,sortBy);
                break;
            case "get-acer":
                paginatedProductList(SELECT_ACER, req, resp,sortBy);
                break;
            case "get-gigabyte":
                paginatedProductList(SELECT_GIGABYTE, req, resp,sortBy);
                break;
            case "get-big-sale":
                paginatedProductList(SELECT_BIG_SALE, req, resp,sortBy);
                break;
            case "get-best-seller":
                paginatedProductList(SELECT_BEST_SELLER, req, resp,sortBy);
                break;
            case "view-product":
                viewProduct(req, resp);
                break;
            case "search":
                try {
                    searchProduct(req, resp);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            default:
                paginatedProductList(SELECT_PRODUCT, req, resp,sortBy);
        }
    }

    private void viewProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        Product product = PRODUCT_SERVICE.getProductByName(name);
        req.setAttribute("product", product);
        req.getRequestDispatcher("/product-detail.jsp").forward(req, resp);
    }

    private void productList(String sql, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Product> products = PRODUCT_SERVICE.findAll(sql);
        req.setAttribute("products", products);
        System.out.println("ok");
        req.getRequestDispatcher("/home.jsp").forward(req, resp);
    }

    private void searchProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        String keyword = req.getParameter("search");
        List<Product> products = productService.searchProducts(keyword);
        req.setAttribute("products", products);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/home.jsp");
        dispatcher.forward(req, resp);
    }

    private void paginatedProductList(String sql, HttpServletRequest req, HttpServletResponse resp, String sortBy) throws ServletException, IOException {
        int page = 1;
        if (req.getParameter("page") != null) {
            page = Integer.parseInt(req.getParameter("page"));
        }

        // Modify the SQL based on the sorting option
        String sortedSql = getSortedSql(sql, sortBy);

        int totalProducts = PRODUCT_SERVICE.getTotalProducts(sortedSql);
        int totalPages = (int) Math.ceil((double) totalProducts / PRODUCTS_PER_PAGE);

        List<Product> products = PRODUCT_SERVICE.findAllPaginated(sortedSql, page, PRODUCTS_PER_PAGE);

        req.setAttribute("products", products);
        req.setAttribute("currentPage", page);
        req.setAttribute("totalPages", totalPages);
        req.setAttribute("currentSort", sortBy);
        req.getRequestDispatcher("/home.jsp").forward(req, resp);
    }
    private String getSortedSql(String baseSql, String sortBy) {
        String orderByClause;
        switch (sortBy) {
            case "price-asc":
                orderByClause = " ORDER BY price ASC";
                break;
            case "price-desc":
                orderByClause = " ORDER BY price DESC";
                break;
            case "name-asc":
                orderByClause = " ORDER BY name ASC";
                break;
            case "name-desc":
                orderByClause = " ORDER BY name DESC";
                break;
            default:
                return baseSql; // No sorting
        }

        // Check if the base SQL already has an ORDER BY clause
        if (baseSql.toUpperCase().contains("ORDER BY")) {
            // Replace the existing ORDER BY clause
            return baseSql.replaceAll("(?i)ORDER BY.*", orderByClause);
        } else {
            // Append the new ORDER BY clause
            return baseSql + orderByClause;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
