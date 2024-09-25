
package com.example.demo.demo.controller;


import com.example.demo.demo.model.User;
import com.example.demo.demo.service.IUserService;
import com.example.demo.demo.service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.regex.Pattern;

@WebServlet(name = "UserServlet", value = "/users")
public class UserServlet extends HttpServlet {
    private IUserService userService = new UserService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");

        if (action == null) {
            action = "";
        }
        try {
            switch (action) {
                case "create":
                    insertUser(request, response);
                    break;
                case "login":
                    loginUser(request, response);
                    break;
                case "update":
                    updateUser(request, response);
                    break;
                case "update1":
                    updateUser1(request, response);
                    break;
                case "delete":
                    deleteUser(request, response);
                    break;
                case "changePass":
                    changePassForm(request, response);
                    break;
                default:
                    response.sendRedirect("/home");
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "logOut":
                logout(request, response);
                break;
            case "into-user":
                showUserInfo(request, response);
                break;
            case "change-password":
                showPassForm(request, response);
                break;
            case "into-card":
                showCartForm(request, response);
                break;
            case "order-history":
                showHistoryForm(request, response);
                break;
            default:
                try {
                    viewUserManagement(request, response);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
        }
    }

    private void viewUserManagement(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {

            List<User> userList = userService.getAllUsers();
            request.setAttribute("listUser", userList);

            int pageSize = 10;
            int totalUsers = userList.size();
            int totalPages = (int) Math.ceil(totalUsers / (double) pageSize);
            int currentPage = Integer.parseInt(request.getParameter("page") == null ? "1" : request.getParameter("page"));
            int start = (currentPage - 1) * pageSize;
            int end = Math.min(start + pageSize, totalUsers);

            request.setAttribute("currentPage", currentPage);
            request.setAttribute("totalPages", totalPages);
            request.setAttribute("userList", userList.subList(start, end));

            request.getRequestDispatcher("user_info/user_management.jsp").forward(request, response);

    }

    private void insertUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        String account = request.getParameter("account");
        String password = request.getParameter("password1");
        String confirmPassword = request.getParameter("password2");
        String email = request.getParameter("email");
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");

        boolean isValidAccount = Pattern.compile("^[^\\s!@#$%^&*()_+={}\\[\\]:;\"'<>,.?/|\\\\~]{3,20}$").matcher(account).matches();
        boolean isValidPassword = Pattern.compile("^(?!.*\\s)[\\S]{3,20}$").matcher(password).matches();
        boolean isValidEmail = Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$").matcher(email).matches();
        boolean isValidName = Pattern.compile("^[A-Za-z ]{5,30}$").matcher(name).matches() && !name.trim().isEmpty();
        boolean isValidPhone = Pattern.compile("^\\d{9}$").matcher(phone).matches();
        boolean isValidAddress = Pattern.compile("^.{5,200}$").matcher(address).matches();

        boolean hasError = false;


        if (!isValidAccount) {
            request.setAttribute("accountError", "Tối thiểu 3 ký tự, tối đa 20 ký tự, không dùng khoảng trắng.");
            hasError = true;
        }

        if (!isValidPassword) {
            request.setAttribute("pass1Error", "Tối thiểu 3 ký tự, tối đa 20 ký tự, không dùng khoảng trắng.");
            hasError = true;
        }

        if (!password.equals(confirmPassword)) {
            request.setAttribute("passError", "Mật khẩu không khớp!");
            hasError = true;
        }

        if (!isValidEmail) {
            request.setAttribute("emailError", "Email không hợp lệ");
            hasError = true;
        }

        if (!isValidName) {
            request.setAttribute("nameError", "Tên không hợp lệ");
            hasError = true;
        }

        if (!isValidPhone) {
            request.setAttribute("phoneError", "Số điện thoại gồm 9 chữ số");
            hasError = true;
        }

        if (!isValidAddress) {
            request.setAttribute("addressError", "Địa chỉ tối thiểu 5 ký tự,tối đa 200 ký tự");
            hasError = true;
        }

        if (hasError) {

            request.setAttribute("account", account);
            request.setAttribute("password", password);
            request.setAttribute("email", email);
            request.setAttribute("name", name);
            request.setAttribute("phone", phone);
            request.setAttribute("address", address);

            request.getRequestDispatcher("/home.jsp").forward(request, response);
            return;
        }

        List<User> usersList = userService.getAllUsers();

        for (User user : usersList) {
            if (user.getAccount().equals(account)) {
                request.setAttribute("accountError", "Tên tài khoản đã tồn tại!");
                request.setAttribute("email", email);
                request.setAttribute("name", name);
                request.setAttribute("phone", phone);
                request.setAttribute("address", address);
                request.getRequestDispatcher("/home.jsp").forward(request, response);
                return;
            }
            if (user.getEmail().equals(email)) {
                request.setAttribute("emailError", "Email đã tồn tại!");
                request.setAttribute("account", account);
                request.setAttribute("name", name);
                request.setAttribute("phone", phone);
                request.setAttribute("address", address);
                request.getRequestDispatcher("/home.jsp").forward(request, response);
                return;
            }
        }

        User user1 = new User(account, password, email, name, phone, address, "customer");
        userService.insertUser(user1);
        response.sendRedirect("/login_successfully.jsp");
    }



    private void loginUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        String account = request.getParameter("account");
        String password = request.getParameter("password");
        User user = userService.authenticateUser(account, password);

        if (user != null) {
            HttpSession session = request.getSession(true);
            session.setAttribute("user", user);
            session.setAttribute("loginSuccess", "Chào mừng " + account + " đến với 4 Gear Store!!!" );

            String role = user.getRole();
            if ("admin".equals(role)) {
                response.sendRedirect("/admin");
            } else if ("customer".equals(role)) {
                response.sendRedirect("/products");
            } else {
                response.sendRedirect("/products1");
            }
        } else {
            request.setAttribute("loginError", "Tên tài khoản hoặc mật khẩu không đúng!");
            request.getRequestDispatcher("/home.jsp").forward(request, response);
        }
    }


    private void changePassForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        System.out.println(user.getPassword());
        String oldPassword = request.getParameter("oldPassword");
        String newPassword = request.getParameter("newPassword");
        String confirmPassword = request.getParameter("confirmPassword");
        String message = "";

        System.out.println(oldPassword);
        System.out.println(newPassword);
        System.out.println(confirmPassword);
        if (!oldPassword.equals(user.getPassword())) {
            message = "Mật khẩu hiện tại không chính xác.";
        }
        else if (!newPassword.equals(confirmPassword)) {
            message = "Mật khẩu mới và xác nhận mật khẩu không khớp.";
        }
        else {
            boolean success = userService.changePassword(newPassword, user);
            if (success) {
                message = "Mật khẩu đã được cập nhật thành công.";
                user.setPassword(newPassword);
                session.setAttribute("user", user);
            } else {
                message = "Đã xảy ra lỗi khi cập nhật mật khẩu. Vui lòng thử lại.";
            }
        }

        request.setAttribute("message", message);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/user_info/change-password.jsp");
        dispatcher.forward(request, response);

    }

    private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        String userIdStr = request.getParameter("user_id");
        try {
            int userId = Integer.parseInt(userIdStr);
            boolean isDeleted = userService.deleteUserById(userId);
            if (isDeleted) {
                response.sendRedirect("users?action=viewUserManagement");
                request.getSession().setAttribute("message", "Xóa người dùng thành công");
                request.getSession().setAttribute("status", "success");
            } else {
                request.setAttribute("errorMessage", "Không thể xóa người dùng.");
                viewUserManagement(request, response);
            }
        } catch (NumberFormatException e) {
            request.setAttribute("errorMessage", "ID người dùng không hợp lệ.");
            viewUserManagement(request, response);
        }
    }

    private void updateUser1(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        String account = request.getParameter("account3");
        String email = request.getParameter("email3");
        String name = request.getParameter("name3");
        String phone = request.getParameter("phone3");
        String address = request.getParameter("address3");
        String role = request.getParameter("role3");
        User user = new User(account, email, name, phone, address, role);
        boolean updated = userService.updateUser1(user);
        if (updated) {
            request.getSession().setAttribute("message", "Cập nhật thông tin thành công");
            request.getSession().setAttribute("status", "success");
        } else {
            request.getSession().setAttribute("message", "Cập nhật thông tin thất bại");
            request.getSession().setAttribute("status", "error");
        }
    }

    private void updateUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {

        String account = request.getParameter("account1");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        user.setAccount(account);
        user.setName(name);
        user.setEmail(email);
        user.setPhone(phone);
        user.setAddress(address);
        boolean isSuccessful = userService.updateUser(user);

        System.out.println(isSuccessful);
        if (isSuccessful) {
            request.setAttribute("successMessage", "Cập nhật thông tin thành công.");
            session.setAttribute("user", user);
            System.out.println("đã cn");
        } else {
            request.setAttribute("errorMessage", "Cập nhật thông tin không thành công.");
            System.out.println("chưa cn");

        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("/user_info/user_infor.jsp");
        dispatcher.forward(request, response);

    }

    private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        response.sendRedirect("/products");
    }

    private void changePassWord(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
    }

    private void showUserInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("user_info/user_infor.jsp");
        dispatcher.forward(request, response);
    }

    private void showPassForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("user_info/change-password.jsp");
        dispatcher.forward(request, response);
    }


    private void showCartForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("user_info/cart.jsp");
        dispatcher.forward(request, response);
    }

    private void showHistoryForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("user_info/order-history.jsp");
        dispatcher.forward(request, response);
    }

}
