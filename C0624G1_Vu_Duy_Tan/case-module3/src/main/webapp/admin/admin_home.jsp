<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Quản lý sản phẩm</title>
    <!-- Include Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .btn-group {
            display: flex;
            gap: 5px; /* Khoảng cách giữa các nút */
        }

        .btn-group form {
            margin: 0; /* Loại bỏ margin của form */
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
        .popup {
            position: fixed;
            top: 20px;
            right: 20px;
            background-color: #28a745 !important;/* Success color */
            color: white;
            padding: 15px 20px;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            z-index: 1000;
            display: flex;
            align-items: center;
            justify-content: space-between;
        }
        .popup.error {
            background-color: #E74C3C;
        }
        .popup-message {
            font-size: 14px;
            margin-right: 10px;
        }
        #close-popup {
            background: none;
            border: none;
            color: white;
            font-size: 16px;
            cursor: pointer;
        }
        .popup {
            animation: fadeIn 0.5s ease-in-out;
        }
        @keyframes fadeIn {
            from {
                opacity: 0;
                transform: translateY(-10px);
            }
            to {
                opacity: 1;
                transform: translateY(0);
            }
        }
        /* Center text and add padding */
        .main-content {
            text-align: center; /* Center align text */
            padding: 40px; /* Add padding around the text */
        }
        .main-content h2 {
            font-size: 2rem; /* Increase font size for the header */
            margin-bottom: 20px; /* Add space below the header */
        }
        .main-content p {
            font-size: 1.25rem; /* Increase font size for the paragraph */
            color: #6c757d; /* Set color for the paragraph */
        }
    </style>
</head>
<body>
<!-- Navigation bar -->
<nav class="navbar navbar-expand-lg navbar-dark custom-navbar">
    <a class="navbar-brand" href="products?action=">
        <img src="/image/4gear.png" width="80" height="50" class="d-inline-block align-top" alt="Logo">
        Laptop 4Gear Nhân viên
    </a>
    <div class="collapse navbar-collapse">
        <!-- Product search form -->
        <form class="form-inline my-2 my-lg-0 ml-auto" action="/products1?action=searchName" method="post">
            <input class="form-control mr-sm-2" type="search" name="productName" placeholder="Tìm kiếm sản phẩm" aria-label="Search">
            <button class="btn btn-light my-2 my-sm-0" type="submit">Tìm kiếm</button>
        </form>
        <ul class="navbar-nav ml-auto">
            <li class="nav-item"><a class="nav-link" href="<c:url value='/products1?action=admin' />">Quản lý sản phẩm</a></li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    ${user.role}
                </a>
                <div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdown">
                    <a class="dropdown-item bg-dark" href="#">Thông tin cá nhân</a>
                    <a class="dropdown-item bg-dark" href="products?action=">Đăng xuất</a>
                </div>
            </li>
        </ul>
    </div>
</nav>
<main class="main-content">
    <h2>Welcome, <c:out value="${user.name}" />!</h2>
    <p>Tại đây bạn có thể quản lý toàn bộ ứng dụng.</p>
</main>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/javascript/edit.js"></script>
</body>
</html>
