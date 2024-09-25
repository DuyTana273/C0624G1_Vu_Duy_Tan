<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="content-type" content="text/html">
    <title>Quản lý đơn hàng</title>
    <!-- Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <script src="../javascript/search.js"></script>

    <style>
        .action-buttons {
            display: flex;
            gap: 5px; /* Adjust the space between buttons */
            justify-content: center; /* Center align the buttons */
            align-items: center; /* Align items vertically in the middle */
        }

        .action-buttons form {
            flex: 1; /* Ensure that each form takes equal space */
        }

        .btn-sm {
            width: 100%; /* Make buttons expand to fill the container */
            white-space: nowrap; /* Prevent text wrapping inside buttons */
        }
        .custom-navbar {
            background-color: #343a40; /* Dark gray background for navbar */
        }

        .custom-table thead {
            background-color: #343a40; /* Dark gray background for table headers */
            color: white; /* White text color for table headers */
        }

        .navbar-brand, .nav-link, .dropdown-item, .btn-outline-light {
            color: white !important; /* White text color for navbar, dropdown, and outline button */
        }

        .btn-outline-light:hover {
            color: #343a40; /* Dark gray text color on hover */
            background-color: white; /* White background color on hover */
        }

        body {
            background-color: #f8f9fa; /* Overall background color of the page */
        }

        .modal-header, .modal-footer {
            background-color: #343a40; /* Dark gray background for modal header and footer */
            color: white; /* White text color for modal */
        }

        .modal-content {
            border-color: #343a40; /* Dark gray border color for modal */
        }
    </style>
</head>
<body>
<!-- Navigation bar -->
<nav class="navbar navbar-expand-lg navbar-dark custom-navbar">
    <a class="navbar-brand" href="#">
        <img src="/image/4gear.png" width="80" height="50" class="d-inline-block align-top" alt="Logo">
        Laptop 4Gear Nhân viên
    </a>
    <div class="collapse navbar-collapse">
        <!-- Order search form -->
        <form class="form-inline my-2 my-lg-0 ml-auto" onsubmit="searchByOrderId(); return false;">
            <input class="form-control mr-sm-2" type="text" id="searchOrderId" placeholder="Tìm kiếm đơn hàng"
                   aria-label="Search">
            <button class="btn btn-outline-light my-2 my-sm-0" type="button" onclick="searchByOrderId()">Tìm kiếm
            </button>
        </form>
        <ul class="navbar-nav ml-auto">
            <li class="nav-item"><a class="nav-link" href="/products1">Quản lý sản phẩm</a></li>
            <li class="nav-item"><a class="nav-link" href="/products1?action=product_sale_management">Quản lý đơn
                hàng</a></li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown"
                   aria-haspopup="true" aria-expanded="false">
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
    <!-- Daily total revenue -->
    <div class="mb-4">
        <h4>Tổng doanh thu trong ngày: <span id="total-revenue"><fmt:formatNumber value="${totalRevenue}"/>0</span></h4>
    </div>

    <!-- Date search form -->
    <div class="mb-4">
        <h5>Tìm kiếm đơn hàng theo ngày:</h5>
        <div class="form-inline">
            <input type="date" id="searchDate" class="form-control mr-2">
            <button class="btn btn-primary" onclick="searchByDate()">Tìm kiếm</button>
        </div>
    </div>

    <!-- Order management -->
    <div id="order-management" class="container-fluid">
        <h2>Quản lý đơn hàng</h2>
        <table class="table table-bordered custom-table">
            <thead>
            <tr>
                <th>Mã đơn hàng</th>
                <th>Mã khách hàng</th>
                <th>Tên khách hàng</th>
                <th>Ngày đặt hàng</th>
                <th>Tổng cộng</th>
                <th>Trạng thái</th>
                <th>Thao tác</th>
            </tr>
            </thead>
            <tbody id="content">
            <c:forEach var="order" items="${listOrder}">
                <tr>
                    <td>${order.orderId}</td>
                    <td>${order.userId}</td>
                    <td>${order.customerName}</td>
                    <td>${order.date}</td>
                    <td><fmt:formatNumber value="${order.sum}"/></td>
                    <td>${order.status}</td>
                    <td>
                        <div class="action-buttons">
                            <form action="/orderdetails" method="get">
                                <input type="hidden" name="orderId" value="${order.orderId}">
                                <button type="submit" class="btn btn-sm btn-primary">Xem</button>
                            </form>

                            <!-- Form for updating order status to shipped -->
                            <form action="orders?action=updateStatus" method="post" onsubmit="return confirm('Bạn có chắc chắn muốn giao đơn hàng này?');">
                                <input type="hidden" name="orderId" value="${order.orderId}">
                                <input type="hidden" name="userId" value="${order.userId}">
                                <input type="hidden" name="status" value="Shipped">
                                <button type="submit" class="btn btn-sm btn-success">Giao hàng</button>
                            </form>

                            <!-- Form for deleting an order -->
                            <form action="/orders" method="post" onsubmit="return confirm('Bạn có chắc chắn muốn xóa đơn hàng này?');">
                                <input type="hidden" name="action" value="deleteOrder">
                                <input type="hidden" name="orderId" value="${order.orderId}">
                                <button type="submit" class="btn btn-sm btn-danger">Xóa</button>
                            </form>
                        </div>
                    </td>
                </tr>
            </c:forEach>
            <!-- More rows can be added here -->
            </tbody>
        </table>
    </div>
</div>


<!-- Bootstrap JS and jQuery -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/javascript/deliver_confirm.js"></script>
<script type="text/javascript" src="/javascript/search_by_day.js" charset="utf-8"></script>
<script type="text/javascript" src="/javascript/search_by_id.js"></script>
<!-- JavaScript for date search -->

</body>
</html>
