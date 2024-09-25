<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Thông tin tài khoản</title>
    <link rel="stylesheet" href="../bootstrap-5.2.3-dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="../css/user.css">
</head>

<body>

<div class="sidebar">
    <nav class="nav flex-column">
        <a class="nav-link nav-logo" href="/users?action=">
            <img class="logo" src="../image/4gear.png" alt="">
        </a>
        <a class="nav-link" href="../users?action="> Trang chủ </a>
        <a class="nav-link" href="/users?action=into-user"> Thông tin tài khoản </a>
        <a class="nav-link" href="/users?action=change-password"> Đổi mật khẩu </a>
        <a class="nav-link" href="/users?action=into-card"> Giỏ hàng </a>
        <a class="nav-link" href="/users?action=order-history"> Lịch sử đặt hàng </a>
        <a class="nav-link" href="/users?action=logOut"> Đăng xuất </a>
    </nav>
</div>

<body>
<div class="container-fluid">
    <div class="row">
        <nav class="col-md-3 col-lg-2 d-md-block bg-dark sidebar">
            <div class="position-sticky">
                <a class="nav-link nav-logo" href="/users?action=logOut">
                    <img class="logo img-fluid" src="../image/4gear.png" alt="Logo" />
                </a>
                <ul class="nav flex-column">
                    <li class="nav-item">
                        <a class="nav-link" href="../index.jsp">Trang chủ</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link active" href="/users?action=into-user">Thông tin tài khoản</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/users?action=change-password">Đổi mật khẩu</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/orders?action=into-cart">Giỏ hàng</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/users?action=order-history">Lịch sử đặt hàng</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/users?action=logOut">Đăng xuất</a>
                    </li>
                </ul>
            </div>
        </nav>

        <!-- Main Content -->
        <main class="col-md-9 ms-sm-auto col-lg-10 px-4 main_content">
            <div class="container bg-white mt-5 shadow-sm rounded p-4">
                <h2 class="mb-4 fw-bold">Thông tin tài khoản</h2>

                <c:if test="${not empty requestScope.successMessage}">
                    <div class="alert alert-success mt-3">
                            ${requestScope.successMessage}
                    </div>
                </c:if>

                <form action="users" method="post">
                    <input type="hidden" name="action" value="update">
                    <div class="mb-3">
                        <label for="userAccount" class="form-label">Tên tài khoản</label>
                        <input type="text" class="form-control" id="userAccount" name="account1" value="${sessionScope.user.account}" readonly>
                    </div>
                    <div class="mb-3">
                        <label for="userName" class="form-label">Tên người dùng</label>
                        <input type="text" class="form-control" id="userName" name="name" value="${sessionScope.user.name}">
                    </div>
                    <div class="mb-3">
                        <label for="userEmail" class="form-label">Email</label>
                        <input type="email" class="form-control" id="userEmail" name="email" value="${sessionScope.user.email}" readonly>
                    </div>
                    <div class="mb-3">
                        <label for="userPhone" class="form-label">Số điện thoại</label>
                        <input type="tel" class="form-control" id="userPhone" name="phone" value="${sessionScope.user.phone}">
                    </div>
                    <div class="mb-3">
                        <label for="userAddress" class="form-label">Địa chỉ</label>
                        <textarea class="form-control" id="userAddress" name="address" rows="3">${sessionScope.user.address}</textarea>
                    </div>
                    <div class="text-end">
                        <button type="submit" class="btn btn-primary btn-sm">Cập nhật</button>
                    </div>
                </form>
            </div>
        </main>
    </div>
</div>
</body>
<script src="../bootstrap-5.2.3-dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
