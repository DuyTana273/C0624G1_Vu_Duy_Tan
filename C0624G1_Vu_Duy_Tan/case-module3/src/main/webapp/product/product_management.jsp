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

    <div id="product-management">
        <h2>Quản lý sản phẩm</h2>
        <button class="btn btn-primary mb-3" data-toggle="modal" data-target="#addProductModal">Thêm sản phẩm mới</button>
        <table class="table table-bordered custom-table">
            <thead>
            <tr>
                <th>#</th>
                <th>Tên sản phẩm</th>
                <th>Mô tả</th>
                <th>Thương hiệu</th>
                <th>Giá</th>
                <th>Số lượng</th>
                <th>Thao tác</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="product" items="${listProduct}">
                <tr>
                    <td>${product.productId}</td>
                    <td>${product.name}</td>
                    <td>${product.description}</td>
                    <td>${product.brand}</td>
                    <td><fmt:formatNumber value="${product.price}"/> VND</td>
                    <td>${product.stock}</td>
                    <td>
                        <div class="btn-group">
                            <button class="btn btn-sm btn-warning" data-toggle="modal" data-target="#editProductModal" onclick="editProduct(${product.productId}, '${product.name}', '${product.description}', '${product.brand}', ${product.price}, ${product.stock}, '${product.image}')">Chỉnh sửa</button>
                            <form action="products1?action=delete" method="post" onsubmit="return confirm('Bạn có chắc chắn muốn xóa sản phẩm này?');">
                                <input type="hidden" name="productId" value="${product.productId}">
                                <button type="submit" class="btn btn-sm btn-danger">Xóa</button>
                            </form>
                        </div>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <nav aria-label="Product navigation">
            <ul class="pagination justify-content-center">
                <c:if test="${currentPage > 1}">
                    <li class="page-item">
                        <a class="page-link" href="products1?page=${currentPage - 1}" aria-label="Previous">
                            <span aria-hidden="true">&laquo;</span>
                            <span class="sr-only">Previous</span>
                        </a>
                    </li>
                </c:if>

                <c:forEach begin="1" end="${totalPages}" var="i">
                    <li class="page-item ${currentPage eq i ? 'active' : ''}">
                        <a class="page-link" href="products1?page=${i}">${i}</a>
                    </li>
                </c:forEach>

                <c:if test="${currentPage < totalPages}">
                    <li class="page-item">
                        <a class="page-link" href="products1?page=${currentPage + 1}" aria-label="Next">
                            <span aria-hidden="true">&raquo;</span>
                            <span class="sr-only">Next</span>
                        </a>
                    </li>
                </c:if>
            </ul>
        </nav>
    </div>

    <!-- Modal for adding new product -->
    <div class="modal fade" id="addProductModal" tabindex="-1" role="dialog" aria-labelledby="addProductModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="addProductModalLabel" >Thêm sản phẩm mới</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <form action="products1?action=create" method="post">
                        <div class="form-group">
                            <label for="productName">Tên sản phẩm</label>
                            <input type="text" class="form-control" id="productName" name="name" placeholder="Nhập tên sản phẩm" required>

                        </div>
                        <div class="form-group">
                            <label for="productDescription">Mô tả</label>
                            <textarea class="form-control" id="productDescription" name="description" placeholder="Nhập mô tả sản phẩm" required></textarea>
                        </div>
                        <div class="form-group">
                            <label for="productBrand">Thương hiệu</label>
                            <select class="form-control" id="productBrand" name="brand">
                                <option value="MSI">MSI</option>
                                <option value="HP">HP</option>
                                <option value="ASUS">ASUS</option>
                                <option value="LENOVO">LENOVO</option>
                                <option value="ACER">ACER</option>
                                <option value="GIGABYTE">GIGABYTE</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="productPrice">Giá</label>
                            <input type="number" class="form-control" id="productPrice" name="price" placeholder="Nhập giá" required>
                        </div>
                        <div class="form-group">
                            <label for="productStock">Số lượng</label>
                            <input type="number" class="form-control" id="productStock" name="stock" placeholder="Nhập số lượng" required>
                        </div>
                        <button type="submit" class="btn btn-primary">Lưu sản phẩm</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- Modal for editing product -->
<div class="modal fade" id="editProductModal" tabindex="-1" role="dialog" aria-labelledby="editProductModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="editProductModalLabel">Chỉnh sửa sản phẩm</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form action="products1?action=update" method="post">
                    <input type="hidden" id="editProductId" name="id" value="${product.productId}">
                    <div class="form-group">
                        <label for="editProductName">Tên sản phẩm</label>
                        <input type="text" class="form-control" id="editProductName" name="name" placeholder="Nhập tên sản phẩm">
                    </div>
                    <div class="form-group">
                        <label for="editProductDescription">Mô tả</label>
                        <textarea class="form-control" id="editProductDescription" name="description" placeholder="Nhập mô tả sản phẩm"></textarea>
                    </div>
                    <div class="form-group">
                        <label for="editProductBrand">Thương hiệu</label>
                        <input type="text" class="form-control" id="editProductBrand" name="brand" placeholder="Nhập thương hiệu">
                    </div>
                    <div class="form-group">
                        <label for="editProductPrice">Giá</label>
                        <input type="number" class="form-control" id="editProductPrice" name="price" placeholder="Nhập giá">
                    </div>
                    <div class="form-group">
                        <label for="editProductStock">Số lượng</label>
                        <input type="number" class="form-control" id="editProductStock" name="stock" placeholder="Nhập số lượng">
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
