<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Chi tiết đơn hàng</title>

    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .custom-navbar {
            background-color: #343a40;
        }
        .order-details-table th, .order-details-table td {
            border: 1px solid #343a40;
        }
        .order-details-table th {
            background-color: #343a40;
            color: white;
        }
        .navbar-brand, .nav-link, .dropdown-item {
            color: white !important;
        }
        body {
            background-color: #f8f9fa;
        }
        .btn-primary {
            background-color: #343a40;
            border-color: #343a40;
        }
        .btn-primary:hover {
            background-color: #23272b;
            border-color: #23272b;
        }
    </style>
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-dark custom-navbar">
    <a class="navbar-brand" href="order-management.html">
        <img src="/image/4gear.png" width="80" height="50" class="d-inline-block align-top" alt="Logo">
        Laptop 4Gear Nhân viên
    </a>
    <div class="collapse navbar-collapse">
        <ul class="navbar-nav ml-auto">
            <li class="nav-item"><a class="nav-link" href="/products1">Quản lý sản phẩm</a></li>
            <li class="nav-item"><a class="nav-link" href="/products1?action=product_sale_management">Quản lý đơn hàng</a></li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    Nhân viên
                </a>
                <div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdown">
                    <a class="dropdown-item bg-dark" href="#">Thông tin cá nhân</a>
                    <a class="dropdown-item bg-dark" href="products?action=">Đăng xuất</a>
                </div>
            </li>
        </ul>
    </div>
</nav>

<div class="container mt-5">
    <h2>Chi tiết đơn hàng</h2>

    <table class="table order-details-table">
        <thead>
        <tr>
            <th>Mã hóa đơn chi tiết</th>
            <th>Tên sản phẩm</th>
            <th>Số lượng</th>
            <th>Giá mỗi sản phẩm</th>
            <th>Tổng cộng</th>
        </tr>
        </thead>
        <tbody>

        <c:forEach var="orderDetail" items="${orderDetails}">
            <tr>
                <td>${orderDetail.orderDetailId}</td>
                <td>${orderDetail.productName}</td>
                <td>${orderDetail.productQuantity}</td>
                <td>${orderDetail.productPrice}</td>
                <td>${orderDetail.orderDetailSum}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <div class="mb-4">
        <p><strong>Mã đơn hàng:</strong> ${order.orderId}</p>
        <p><strong>Mã khách hàng:</strong> ${order.userId}</p>
        <p><strong>Tên khách hàng:</strong> ${order.customerName}</p>
        <p><strong>Ngày đặt hàng:</strong> ${order.date}</p>
        <p><strong>Tổng cộng:</strong> $${order.sum}</p>
        <p><strong>Trạng thái:</strong> ${order.status}</p>

    </div>
    <div>
        <a class="btn btn-primary" href="/products1?action=product_sale_management">Quay lại</a>
    </div>
</div>


<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
