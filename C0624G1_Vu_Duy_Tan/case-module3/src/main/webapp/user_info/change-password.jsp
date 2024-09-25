<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Đổi mật khẩu</title>
    <link rel="stylesheet" href="../bootstrap-5.2.3-dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="../css/user.css">
</head>

<body>

<div class="sidebar">
    <nav class="nav flex-column">
        <a class="nav-link nav-logo" href="/users?action=logOut">
            <img class="logo" src="../image/4gear.png" alt="">
        </a>
        <a class="nav-link" href="../index.jsp"> Trang chủ </a>
        <a class="nav-link" href="/users?action=into-user"> Thông tin tài khoản </a>
        <a class="nav-link" href="/users?action=change-password"> Đổi mật khẩu </a>
        <a class="nav-link" href="/users?action=into-card"> Giỏ hàng </a>
        <a class="nav-link" href="/users?action=order-history"> Lịch sử đặt hàng </a>
        <a class="nav-link" href="/users?action=logOut"> Đăng xuất </a>
    </nav>
</div>
</body>

<body>
<div class="container-fluid">
    <div class="row">
        <nav class="col-md-3 col-lg-2 d-md-block bg-dark sidebar">
            <div class="position-sticky">
                <a class="nav-link nav-logo" href="/users?action=logOut">
                    <img class="logo img-fluid" src="../image/4gear.png" alt="Logo"/>
                </a>
                <ul class="nav flex-column">
                    <li class="nav-item">
                        <a class="nav-link" href="../index.jsp">Trang chủ</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/users?action=into-user">Thông tin tài khoản</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link active" href="/users?action=change-password">Đổi mật khẩu</a>
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
            <div class="container bg-white mt-5 w-50 shadow-sm rounded p-4">
                <h2 class="mb-4 fw-bold">Đổi mật khẩu</h2>

                <!-- Hiển thị thông báo nếu có -->
                <c:if test="${not empty message}">
                    <div class="alert alert-info">
                            ${message}
                    </div>
                </c:if>

                <form action="users" method="post">
                    <input type="hidden" name="action" value="changePass">

                    <div class="mb-3">
                        <label for="currentPassword" class="form-label">Mật khẩu hiện tại</label>
                        <input type="password" class="form-control" id="currentPassword" name="oldPassword" required>
                    </div>
                    <div class="mb-3">
                        <label for="newPassword" class="form-label">Mật khẩu mới</label>
                        <input type="password" class="form-control" id="newPassword" name="newPassword" required>
                    </div>
                    <div class="mb-3">
                        <label for="confirmPassword" class="form-label">Xác nhận mật khẩu mới</label>
                        <input type="password" class="form-control" id="confirmPassword" name="confirmPassword" required>
                    </div>
                    <div class="text-end">
                        <button type="submit" class="btn btn-primary btn-sm">Cập nhật mật khẩu</button>
                    </div>
                </form>
            </div>
        </main>
    </div> <!-- End of row -->
</div> <!-- End of container-fluid -->
</body>


<script src="../bootstrap-5.2.3-dist/js/bootstrap.js"></script>

</html>
