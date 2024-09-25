<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Quản lý sản phẩm</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .btn-group {
            display: flex;
            gap: 5px; /
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

        .btn-outline-light :hover {
            color: #343a40;
            background-color: white;
        }

        body {
            background-color: #f8f9fa; /* Overall background color of the page */
        }

        .modal-header, modal-footer {
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
            <li class="nav-item"><a class="nav-link" href="/products1">Quản lý sản phẩm</a></li>
            <li class="nav-item"><a class="nav-link" href="/products1?action=product_sale_management">Quản lý đơn hàng</a></li>
            <li class="nav-item"><a class="nav-link" href="/users">Quản lý người dùng</a></li>
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

<div class="container mt-5">
    <h2>Danh sách người dùng</h2>
    <table class="table table-bordered">
        <thead>
        <tr style="background-color: #2c3034">
            <th style="color: white">#</th>
            <th style="color: white">Tài khoản</th>
            <th style="color: white">Email</th>
            <th style="color: white">Họ và tên</th>
            <th style="color: white">Số điện thoại</th>
            <th style="color: white">Địa chỉ</th>
            <th style="color: white">Thao tác</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="user" items="${userList}">
            <tr>
                <td>${user.id}</td>
                <td>${user.account}</td>
                <td>${user.email}</td>
                <td>${user.name}</td>
                <td>${user.phone}</td>
                <td>${user.address}</td>
                <td>
                    <div class="btn-group">
                        <button class="btn btn-sm btn-warning" data-toggle="modal" data-target="#editUserModal" onclick="editUser('${user.account}', '${user.email}', '${user.name}', '${user.phone}', '${user.address}', '${user.role}')">Chỉnh sửa</button>
                        <form action="users?action=delete" method="post" style="display:inline;">
                            <input type="hidden" name="account" value="${user.account}">
                            <button type="submit" class="btn btn-sm btn-danger" onclick="return confirm('Bạn có chắc chắn muốn xóa người dùng này?');">Xóa</button>
                        </form>
                    </div>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <!-- Pagination -->
    <nav aria-label="User navigation">
        <ul class="pagination justify-content-center">
            <c:if test="${currentPage > 1}">
                <li class="page-item">
                    <a class="page-link" href="users?action=viewUserManagement&page=${currentPage - 1}" aria-label="Previous">
                        <span aria-hidden="true">&laquo;</span>
                        <span class="sr-only">Previous</span>
                    </a>
                </li>
            </c:if>

            <c:forEach begin="1" end="${totalPages}" var="i">
                <li class="page-item ${currentPage eq i ? 'active' : ''}">
                    <a class="page-link" href="users?action=viewUserManagement&page=${i}">${i}</a>
                </li>
            </c:forEach>

            <c:if test="${currentPage < totalPages}">
                <li class="page-item">
                    <a class="page-link" href="users?action=viewUserManagement&page=${currentPage + 1}" aria-label="Next">
                        <span aria-hidden="true">&raquo;</span>
                        <span class="sr-only">Next</span>
                    </a>
                </li>
            </c:if>
        </ul>
    </nav>
</div>

    <div class="modal fade" id="addUserModal" tabindex="-1" role="dialog" aria-labelledby="addUserModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="addUserModalLabel">Thêm người dùng</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <form action="users?action=create" method="post">
                        <div class="form-group">
                            <label for="userName">Tên người dùng</label>
                            <input type="text" class="form-control" id="userName" name="name" placeholder="Nhập tên người dùng" required>
                        </div>
                        <div class="form-group">
                            <label for="userEmail">Email</label>
                            <input type="email" class="form-control" id="userEmail" name="email" placeholder="Nhập email" required>
                        </div>
                        <div class="form-group">
                            <label for="userPassword">Mật khẩu</label>
                            <input type="password" class="form-control" id="userPassword" name="password" placeholder="Nhập mật khẩu" required>
                        </div>
                        <div class="form-group">
                            <label for="userRole">Vai trò</label>
                            <select class="form-control" id="userRole" name="role">
                                <option value="customer">Khách hàng</option>
                                <option value="admin">Quản trị viên</option>
                                <option value="employee">Nhân viên</option>
                            </select>
                        </div>
                        <button type="submit" class="btn btn-primary">Lưu người dùng</button>
                    </form>
                </div>
            </div>
        </div>
    </div>

<div class="modal fade" id="editUserModal" tabindex="-1" role="dialog" aria-labelledby="editUserModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="editUserModalLabel">Chỉnh sửa người dùng</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form action="users?action=update1" method="post">
                    <input type="hidden" id="editUserId">
                    <div class="form-group">
                        <label for="editUserAccount">Tên đăng nhập</label>
                        <input type="text" class="form-control" id="editUserAccount" name="account3" placeholder="Nhập tên đăng nhập" required>
                    </div>
                    <div class="form-group">
                        <label for="editUserEmail">Email</label>
                        <input type="email" class="form-control" id="editUserEmail" name="email3" placeholder="Nhập email" required>
                    </div>
                    <div class="form-group">
                        <label for="editUserName">Họ và tên</label>
                        <input type="text" class="form-control" id="editUserName" name="name3" placeholder="Nhập họ và tên" required>
                    </div>
                    <div class="form-group">
                        <label for="editUserPhone">Số điện thoại</label>
                        <input type="text" class="form-control" id="editUserPhone" name="phone3" placeholder="Nhập số điện thoại">
                    </div>
                    <div class="form-group">
                        <label for="editUserAddress">Địa chỉ</label>
                        <input type="text" class="form-control" id="editUserAddress" name="address3" placeholder="Nhập địa chỉ">
                    </div>
                    <div class="form-group">
                        <label for="editUserRole">Vai trò</label>
                        <select class="form-control" id="editUserRole" name="role3">
                            <option value="customer">Khách hàng</option>
                            <option value="admin">Quản trị viên</option>
                            <option value="employee">Nhân viên</option>
                        </select>
                    </div>
                    <button type="submit" class="btn btn-primary">Lưu thay đổi</button>
                </form>
            </div>
        </div>
    </div>
</div>
<%
    String message = (String) session.getAttribute("message");
    String status = (String) session.getAttribute("status");
    if (message != null && status != null) {
%>
<div id="popup" class="popup <%= status %>">
    <span class="popup-message"><%= message %></span>
    <button id="close-popup" onclick="closePopup()">X</button>
</div>
<%
        session.removeAttribute("message");
        session.removeAttribute("status");
    }
%>


<script>

    function closePopup() {
        document.getElementById("popup").style.display = "none";
    }


    window.onload = function() {
        setTimeout(function() {
            document.getElementById("popup").style.display = "none";
        }, 3000);
    };

</script>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/javascript/edit.js"></script>
</body>
</html>
