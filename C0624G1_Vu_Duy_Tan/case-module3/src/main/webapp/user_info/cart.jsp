<%@ page import="com.example.demo.demo.model.OrderDetail" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Giỏ hàng</title>
    <link rel="stylesheet" href="../bootstrap-5.2.3-dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="../css/user.css">
    <style>
        .popup {
            position: fixed;
            top: 20px;
            right: 20px;
            background-color: #86B817 !important; /* Success color */
            color: white;
            padding: 15px 20px;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            z-index: 1000;
            display: flex;
            align-items: center;
            justify-content: space-between;
        }
        .popup .error {
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
                        <a class="nav-link" href="/users?action=change-password">Đổi mật khẩu</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link active" href="/users?action=into-card">Giỏ hàng</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Lịch sử đặt hàng</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/users?action=logOut">Đăng xuất</a>
                    </li>
                </ul>
            </div>
        </nav>

        <main class="col-md-9 ms-sm-auto col-lg-10 px-4 main_content">
            <div class="container bg-white mt-5 w-75 shadow-sm rounded p-4">
                <h1 class="mb-4 fw-bold">Giỏ hàng của bạn</h1>

                <c:set var="orderDetails" value="${requestScope.orderDetails}"/>
                <c:set var="totalPrice" value="${requestScope.totalPrice}"/>

                <c:if test="${totalPrice == null}">
                    <c:set var="totalPrice" value="0.0"/>
                </c:if>

                <c:if test="${not empty message}">
                    <div class="alert alert-info" role="alert">
                            ${message}
                    </div>
                </c:if>

                <c:if test="${not empty orderDetails}">
                    <table class="table cart-table">
                        <thead>
                        <tr>
                            <th>Hình ảnh</th>
                            <th>Sản phẩm</th>
                            <th>Giá</th>
                            <th>Số lượng</th>
                            <th>Hành động</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="detail" items="${orderDetails}">
                            <tr>
                                <td class="product">
                                    <img src="${detail.image}" alt="${detail.productName}" class="img-thumbnail">
                                </td>
                                <td>${detail.productName}</td>
                                <td class="price">
                                    <fmt:formatNumber value="${detail.productPrice}"/> VND
                                </td>
                                <td class="quantity">${detail.productQuantity}</td>
                                <td class="actions">
                                    <form action="orders" method="post" class="d-inline">
                                        <input type="hidden" name="action" value="removeFromCart"/>
                                        <input type="hidden" name="productId" value="${detail.productId}"/>
                                        <button type="submit" class="btn btn-danger">Xóa</button>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>

                    <div class="cart-summary mt-4">
                        <h2>Tổng đơn hàng</h2>
                        <p>Tổng cộng: <span>
                            <fmt:formatNumber value="${totalPrice}"/>VND
                        </span></p>
                        <a href="#" data-bs-toggle="modal" data-bs-target="#checkoutModal">
                            <button id="checkoutButton" type="button" class="btn btn-primary">Đặt hàng</button>
                        </a>
                    </div>
                </c:if>

                <c:if test="${empty orderDetails}">
                    <c:if test="${not empty message1}">
                        <div class="alert alert-info mt-4">
                            <span>${message1}</span>
                        </div>
                    </c:if>
                </c:if>
            </div>
        </main>
    </div>
</div>

<div class="modal fade" id="checkoutModal" tabindex="-1" aria-labelledby="checkoutModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="checkoutModalLabel">Xác nhận thanh toán</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                Bạn có chắc chắn muốn thanh toán không? Nếu bạn xác nhận, đơn hàng của bạn sẽ được đặt và đang được xử lý.
            </div>
            <div class="modal-footer">
                <form action="orders" method="post">
                    <input type="hidden" name="action" value="checkout">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Hủy</button>
                    <button type="submit" id="confirmCheckout" class="btn btn-primary" name="checkout">Thanh toán</button>
                </form>
            </div>
        </div>
    </div>
</div>

<script src="../bootstrap-5.2.3-dist/js/bootstrap.bundle.min.js"></script>
<script>
    document.addEventListener('DOMContentLoaded', function () {
        var checkoutModal = new bootstrap.Modal(document.getElementById('checkoutModal'));

        document.getElementById('checkoutButton').addEventListener('click', function () {
            checkoutModal.show();
        });

        document.getElementById('confirmCheckout').addEventListener('click', function () {
            var form = document.createElement('form');
            form.method = 'post';
            form.action = '/orders';
        });
    });
</script>
<%
    String message = (String) session.getAttribute("message2");
    String status = (String) session.getAttribute("status");
    if (message != null && status != null) {
%>
<div id="popup" class="popup <%= status %>">
    <span class="popup-message"><%= message %></span>
    <button id="close-popup" onclick="closePopup()">X</button>
</div>
<%
        session.removeAttribute("message2");
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

</body>
</html>