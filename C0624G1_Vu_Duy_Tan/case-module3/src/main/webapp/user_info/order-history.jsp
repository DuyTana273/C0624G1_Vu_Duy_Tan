<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 09/09/2024
  Time: 23:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" %>


<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="../bootstrap-5.2.3-dist/css/bootstrap.css">
    <link rel="stylesheet" href="../css/user.css">

</head>

<body>
<div class="sidebar">
    <nav class="nav flex-column">
        <a class="nav-link nav-logo" href="/users?action=order-history">
            <img class="logo" src="../image/4gear.png" alt="">
        </a>
        <a class="nav-link" href="../index.jsp">Trang chủ</a>
        <a class="nav-link" href="/users?action=into-user">Thông tin tài khoản</a>
        <a class="nav-link" href="/users?action=change-password">Đổi mật khẩu</a>
        <a class="nav-link" href="/users?action=into-card">Giỏ hàng</a>
        <a class="nav-link" href="/users?action=order-history">Lịch sử đặt hàng</a>
        <a class="nav-link" href="/users?action=logOut">Đăng xuất</a>
    </nav>
</div>

<main class="main_content">
    <div class="container mt-5 cart">
        <h2 class="mb-4">Lịch sử đặt hàng</h2>
        <div class="cart-container">
            <table class="cart-table">
                <thead>
                <tr>
                    <th>STT</th>
                    <th>Sản phẩm</th>
                    <th>Giá</th>
                    <th>Số lượng</th>
                    <th>Ngày đặt</th>
                    <th>Trạng thái</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>1</td>
                    <td>Chuột Logitech G502</td>
                    <td>1,500,000 VND</td>
                    <td>2</td>
                    <td>2024-09-10</td>
                    <td>Đang xử lý</td>
                </tr>
                <tr>
                    <td>2</td>
                    <td>Bàn phím Corsair K70</td>
                    <td>2,200,000 VND</td>
                    <td>1</td>
                    <td>2024-09-09</td>
                    <td>Đã giao</td>
                </tr>
                <tr>
                    <td>3</td>
                    <td>Màn hình Acer 24"</td>
                    <td>4,500,000 VND</td>
                    <td>1</td>
                    <td>2024-09-08</td>
                    <td>Chờ thanh toán</td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
</main>

<script src="../bootstrap-5.2.3-dist/js/bootstrap.js"></script>
</body>

</html>
