<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%--<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>--%>
<%--<>--%>
<%--<html lang="en">--%>
<%--<head>--%>
<%--    <meta charset="UTF-8">--%>
<%--    <meta name="viewport" content="width=device-width, initial-scale=1.0">--%>
<%--    <title>Admin - Danh sách sản phẩm</title>--%>
<%--    <link rel="stylesheet" href="../bootstrap-5.2.3-dist/css/bootstrap.min.css">--%>
<%--    <link rel="stylesheet" href="../css/admin.css">--%>
<%--</head>--%>
<%--<body>--%>
<%--<div class="container-fluid">--%>
<%--    <div class="row">--%>
<%--        <!-- Sidebar -->--%>
<%--        <nav class="col-md-3 col-lg-2 d-md-block bg-dark sidebar">--%>
<%--            <div class="position-sticky">--%>
<%--                <a class="nav-link nav-logo" href="/admin">--%>
<%--                    <img class="logo img-fluid" src="../image/4gear.png" alt="Logo" />--%>
<%--                </a>--%>
<%--                <ul class="nav flex-column">--%>
<%--                    <li class="nav-item">--%>
<%--                        <a class="nav-link" href="#">Dashboard</a>--%>
<%--                    </li>--%>
<%--                    <li class="nav-item">--%>
<%--                        <a class="nav-link active" href="/products1">Danh sách sản phẩm</a>--%>
<%--                    </li>--%>
<%--                    <!-- Add more menu items as needed -->--%>
<%--                    <li class="nav-item">--%>
<%--                        <a class="nav-link" href="/logout">Đăng xuất</a>--%>
<%--                    </li>--%>
<%--                </ul>--%>
<%--            </div>--%>
<%--        </nav>--%>

<%--        <!-- Main content -->--%>
<%--        <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">--%>
<%--            <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">--%>
<%--                <h1 class="h2">Danh sách sản phẩm</h1>--%>
<%--                <div class="btn-toolbar mb-2 mb-md-0">--%>
<%--                    <a href="/products1?action=add" class="btn btn-sm btn-outline-secondary">--%>
<%--                        Thêm sản phẩm mới--%>
<%--                    </a>--%>
<%--                </div>--%>
<%--            </div>--%>

<%--            <!-- Search form -->--%>
<%--            <form action="/products1" method="get" class="mb-3">--%>
<%--                <input type="hidden" name="action" value="search">--%>
<%--                <div class="input-group">--%>
<%--                    <input type="text" class="form-control" placeholder="Tìm kiếm sản phẩm" name="keyword">--%>
<%--                    <button class="btn btn-outline-secondary" type="submit">Tìm kiếm</button>--%>
<%--                </div>--%>
<%--            </form>--%>

<%--            <!-- Product list table -->--%>
<%--            <div class="table-responsive">--%>
<%--                <table class="table table-striped table-sm">--%>
<%--                    <thead>--%>
<%--                    <tr>--%>
<%--                        <th>ID</th>--%>
<%--                        <th>Tên sản phẩm</th>--%>
<%--                        <th>Giá</th>--%>
<%--                        <th>Số lượng</th>--%>
<%--                        <th>Hành động</th>--%>
<%--                    </tr>--%>
<%--                    </thead>--%>
<%--                    <tbody>--%>
<%--                    <c:forEach var="product" items="${listProduct}">--%>
<%--                        <tr>--%>
<%--                            <td>${product.id}</td>--%>
<%--                            <td>${product.name}</td>--%>
<%--                            <td>${product.price}</td>--%>
<%--                            <td>${product.stock}</td>--%>
<%--                            <td>--%>
<%--                                <a href="/products1?action=edit&id=${product.id}" class="btn btn-sm btn-primary">Sửa</a>--%>
<%--                                <a href="/products1?action=delete&id=${product.id}" class="btn btn-sm btn-danger"--%>
<%--                                   onclick="return confirm('Bạn có chắc chắn muốn xóa sản phẩm này?')">Xóa</a>--%>
<%--                            </td>--%>
<%--                        </tr>--%>
<%--                    </c:forEach>--%>
<%--                    </tbody>--%>
<%--                </table>--%>

<%--                <!-- Phân trang -->--%>
<%--                <nav aria-label="Page navigation">--%>
<%--                    <ul class="pagination justify-content-center">--%>
<%--                        <c:if test="${currentPage > 1}">--%>
<%--                            <li class="page-item">--%>
<%--                                <a class="page-link" href="/products1?action=admin&page=${currentPage - 1}">Trước</a>--%>
<%--                            </li>--%>
<%--                        </c:if>--%>

<%--                        <c:forEach begin="1" end="${totalPages}" var="i">--%>
<%--                            <li class="page-item ${currentPage == i ? 'active' : ''}">--%>
<%--                                <a class="page-link" href="/products1?action=admin&page=${i}">${i}</a>--%>
<%--                            </li>--%>
<%--                        </c:forEach>--%>

<%--                        <c:if test="${currentPage < totalPages}">--%>
<%--                            <li class="page-item">--%>
<%--                                <a class="page-link" href="/products1?action=admin&page=${currentPage + 1}">Tiếp</a>--%>
<%--                            </li>--%>
<%--                        </c:if>--%>
<%--                    </ul>--%>
<%--                </nav>--%>
<%--            </div>--%>
<%--        </main>--%>
<%--    </div>--%>
<%--</div>--%>

<%--<script src="../bootstrap-5.2.3-dist/js/bootstrap.bundle.min.js"></script>--%>
<%--</body>--%>
<%--</html>--%>