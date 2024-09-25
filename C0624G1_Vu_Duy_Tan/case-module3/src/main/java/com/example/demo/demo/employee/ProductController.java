package com.example.demo.demo.employee;


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

@WebServlet(name = "ProductController", urlPatterns = "/products1")
public class ProductController extends HttpServlet {
    private final IProductService productService = new ProductService();
    private static final int PRODUCTS_PER_PAGE = 10;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }

        try {
            switch (action) {

                case "product_sale_management":
                    productSaleManagementForm(request, response);
                    break;
                case "search":
                    searchProduct(request, response);
                    break;
                default:
                    listProduct(request, response);
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
        try {
            switch (action) {
                case "admin":
                    listProductAdmin(request, response);
                    break;
                case "create":
                    insertProduct(request, response);
                    break;
                case "update":
                    updateProduct(request, response);
                    break;
                case "delete":
                    deleteProduct(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }
    private void listProductAdmin(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        int page = 1;
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }

        int totalProducts = productService.getTotalProducts();
        int totalPages = (int) Math.ceil((double) totalProducts / PRODUCTS_PER_PAGE);

        List<Product> listProduct = productService.selectProductsPaginated((page - 1) * PRODUCTS_PER_PAGE, PRODUCTS_PER_PAGE);

        request.setAttribute("listProduct", listProduct);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);
        RequestDispatcher dispatcher = request.getRequestDispatcher("admin/product_management.jsp");
        dispatcher.forward(request, response);
    }

    private void searchProductAdmin(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        String keyword = request.getParameter("keyword");
        List<Product> products = productService.searchProducts(keyword);

        request.setAttribute("listProduct", products);
        request.setAttribute("keyword", keyword);
        RequestDispatcher dispatcher = request.getRequestDispatcher("admin/product_management.jsp");
        dispatcher.forward(request, response);
    }

    private void listProduct(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        int page = 1;
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }

        int totalProducts = productService.getTotalProducts();
        int totalPages = (int) Math.ceil((double) totalProducts / PRODUCTS_PER_PAGE);

        List<Product> listProduct = productService.selectProductsPaginated((page - 1) * PRODUCTS_PER_PAGE, PRODUCTS_PER_PAGE);

        request.setAttribute("listProduct", listProduct);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);
        RequestDispatcher dispatcher = request.getRequestDispatcher("product/product_management.jsp");
        dispatcher.forward(request, response);
    }

    private void insertProduct(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String brand = request.getParameter("brand");
        double price = Double.parseDouble(request.getParameter("price"));
        int stock = Integer.parseInt(request.getParameter("stock"));

        Product newProduct = new Product(name, description, brand, price, stock);
        productService.addProduct(newProduct);
        request.getSession().setAttribute("message", "Thêm sản phầm thành công");
        request.getSession().setAttribute("status", "error");
        response.sendRedirect("/products1");

    }

    private void searchProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        String keyword = req.getParameter("search");
        List<Product> products = productService.searchProducts(keyword);
        req.setAttribute("listProduct", products);
        RequestDispatcher dispatcher = req.getRequestDispatcher("product/product_management.jsp");
        dispatcher.forward(req, resp);

    }

    private void productSaleManagementForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/orders");
        dispatcher.forward(request, response);
    }

    private void updateProduct(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));

        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String brand = request.getParameter("brand");
        double price = Double.parseDouble(request.getParameter("price"));
        int stock = Integer.parseInt(request.getParameter("stock"));
        String image = request.getParameter("image");

        Product product = new Product(id, name, description, brand, price, stock);
        productService.editProduct(product);
        request.getSession().setAttribute("message", "Sữa sản phầm thành công");
        request.getSession().setAttribute("status", "error");
        response.sendRedirect("/products1");

    }

    private void deleteProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int productId = Integer.parseInt(request.getParameter("productId"));
        try {
            productService.deleteProduct(productId);
            request.getSession().setAttribute("message", "Xóa sản phầm thành công");
            request.getSession().setAttribute("status", "error");
            response.sendRedirect("/products1");

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to delete product.");
        }
    }

}
