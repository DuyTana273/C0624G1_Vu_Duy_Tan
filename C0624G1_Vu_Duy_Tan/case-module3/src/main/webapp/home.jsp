<%@ page import="java.util.HashSet" %>
<%@ page import="java.text.NumberFormat" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page session="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>4Gear Store</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css"
          integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg=="
          crossorigin="anonymous" referrerpolicy="no-referrer"/>
    <link rel="stylesheet" href="bootstrap-5.2.3-dist/css/bootstrap.css">
    <link rel="stylesheet" href="css/style.css">
    <style>
        .btn-group .btn {
            margin-right: 10px;
            background-color: white;
            border: 1px solid #ccc;
            padding: 10px 20px;
            font-size: 20px;
            color: #000;
            transition: background-color 0.3s ease, color 0.3s ease;
        }

        .btn-group .btn:hover {
            background-color: #ff5c75;
            color: white;
        }


        .btn-group .btn:last-child {
            margin-right: 0;
        }


        .btn-check:checked + .btn {
            background-color: #ff5c75;
            color: white;
        }
    </style>
</head>
<body>


<section class="myheader">
    <div class="container header_one">
        <div class="row">
            <div class="col-md-3">
                <a href="products?action="> <img src="image/4gear.png" height="100" width="160" class="img-fluid logo"
                                                 alt="Logo"></a>

            </div>
            <div class="col-md-5 search-product">
                <div class="input-group input_text mb-3 py-3">
                    <form class="d-flex" id="searchForm" action="products" method="get">
                        <input type="hidden" name="action" value="search">
                        <input class="form-control me-2 custom-search-input" type="search"
                               placeholder="Bạn đang tìm gì....."
                               aria-label="Search" name="search" style="width : 400px">
                        <button class="btn btn-primary bg-danger" type="submit">Tìm kiếm</button>
                    </form>
                </div>
            </div>

            <div class="toast-container position-fixed top-0 end-0 p-3" style="z-index: 1050;">
                <div id="loginToast" class="toast align-items-center text-white bg-success border-0"
                     role="alert" aria-live="assertive" aria-atomic="true" style="display: none;">
                    <div class="d-flex">
                        <div class="toast-body">
                            <%
                                HttpSession sessions = request.getSession(false);
                                if (sessions != null && sessions.getAttribute("loginSuccess") != null) {
                            %>
                            <%= sessions.getAttribute("loginSuccess") %>
                            <%
                                    sessions.removeAttribute("loginSuccess");
                                }
                            %>
                        </div>
                        <button type="button" class="btn-close btn-close-white me-2 m-auto"
                                data-bs-dismiss="toast" aria-label="Close"></button>
                    </div>
                </div>
            </div>
            <div class="col-md-4 py-3">
                <div class="row">

                    <div class="col input_text call">
                        <div class="row">
                            <div class="col-3">
                                <div class="fs-3 text-danger "><i class="fa-solid fa-phone"></i></div>
                            </div>
                            <div class="col-9">Tư vấn hỗ trợ<br>
                                <strong class="text-danger">09050506</strong>
                            </div>
                        </div>
                    </div>

                    <div class="col input_text user">
                        <div class="row">
                            <div class="col-3">
                                <div class="fs-3 text-danger"><i class="fa-regular fa-user"></i></div>
                            </div>
                            <div class="col-9">
                                <c:choose>
                                    <c:when test="${not empty sessionScope.user}">
                                        <a href="/users?action=into-user"><p><c:out
                                                value="${sessionScope.user.account}"/></p></a>

                                        <a href="/users?action=logOut">Đăng xuất</a>
                                    </c:when>
                                    <c:otherwise>
                                        <a href="#" data-bs-toggle="modal" data-bs-target="#register">Đăng ký</a><br>
                                        <strong><a href="#" class="text-danger" data-bs-toggle="modal"
                                                   data-bs-target="#signup">Đăng nhập</a></strong>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </div>
                    </div>


                    <div class="col-md-2 py-3 cart  ">
                        <a href="orders?action=into-cart" class="position-relative">
                            <span class="fs-2"><i class="fa-solid fa-cart-shopping text-danger"></i></span>
                            <span class="position-absolute top-5 start-90 translate-middle badge rounded-pill bg-info">
                                0
                            </span>
                        </a>

                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

<section class="myMenu bg-danger">
    <nav class="navbar navbar-expand-lg bg-danger">
        <a class="navbar-brand " href="#"></a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link custom-text-white active" aria-current="page" onclick="forcus()"
                       href="/products?action=get-msi&scrollTo=no-products">
                        <img class="img_msi" src="image/msi.png" alt="">
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link custom-text-white" href="/products?action=get-asus&scrollTo=no-products" >
                        <img class="img_logo" src="image/asus.png" alt=""></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link custom-text-white" href="/products?action=get-lenovo&scrollTo=no-products">
                        <img class="img_lenovo" src="image/lenovo.png" alt="">
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link custom-text-white" href="/products?action=get-acer&scrollTo=no-products">
                        <img class="img_acer" src="image/acer.png" alt=""></a>
                </li>

                <li class="nav-item">
                    <a class="nav-link custom-text-white" href="/products?action=get-gygabyte&scrollTo=no-products">
                        <img class="img_giga" src="image/gygabyte.png" alt=""></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link custom-text-white" href="/products?action=get-hp&scrollTo=no-products">
                        <img class="img_dell" src="image/hp.png" alt=""></a>
                </li>

                <li class="nav-item">
                    <a class="nav-link custom-text-white" aria-disabled="true"></a>
                </li>
            </ul>
        </div>
    </nav>
</section>

<section class="main_content my-3">
    <div class="container">
        <div class="slider ">
            <div id="carouselExampleAutoplaying" class="carousel slide" data-bs-ride="carousel">
                <div class="carousel-inner">
                    <div class="carousel-item active">
                        <img src="image/slider3.png" class="d-block w-100" alt="...">
                    </div>
                    <div class="carousel-item">
                        <img src="image/slider2.png" class="d-block w-100" alt="...">
                    </div>
                    <div class="carousel-item">
                        <img src="image/slider4.png" class="d-block w-100" alt="...">
                    </div>
                </div>
                <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleAutoplaying"
                        data-bs-slide="prev">
                    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                    <span class="visually-hidden">Trước</span>
                </button>
                <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleAutoplaying"
                        data-bs-slide="next">
                    <span class="carousel-control-next-icon" aria-hidden="true"></span>
                    <span class="visually-hidden">Sau</span>
                </button>
            </div>
        </div>

        <nav class="navbar navbar-expand-lg bg-light ads ">
            <div class="container-fluid">

                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav"
                        aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarNav">
                    <ul class="navbar-nav">
                        <li class="nav-item bl">
                            <a class="nav-link" href="products?action=get-big-sale">
                                <img class="best_sell" src="image/bestseller.png" alt="">
                            </a>
                        </li>
                        <li class="nav-item bs">
                            <a class="nav-link" href="products?action=get-best-seller">
                                <img class="big_sale" src="image/bigsell2.png" alt="">
                            </a>
                        </li>
                        <li class="nav-item video ">
                            <video src="image/SPHV-%20Quảng%20cáo%20LAPTOP%20GAMING%20ASUS.mp4" autoplay loop
                                   muted></video>
                        </li>

                    </ul>
                </div>
            </div>
        </nav>

        <div class="product-list my-3" id="scroll-target">


            <c:if test="${empty products}">
                <div class="no-products-found bg-light  text-center p-5 my-3">
                    <p class="no-products-message  ">Không tìm thấy sản phẩm.</p>
                </div>
            </c:if>

            <c:if test="${not empty products}">
                <div class="product_title border-bottom">

                    <c:forEach items="${products}" var="product" varStatus="s">
                        <c:if test="${s.count == 1}">
                            <strong class="name_product ">${product.brand}</strong>
                        </c:if>
                    </c:forEach>
                </div>

                <div class="product-list-a bg-white">
                    <div class="row">
                        <div class="product-sort-list d-flex justify-content-center align-items-center my-3">
                            <label class="me-2"></label>
                            <div class="btn-group" role="group" aria-label="Sort options">
                                <input type="radio" class="btn-check" name="sort-options" id="price-asc" autocomplete="off" ${currentSort == 'price-asc' ? 'checked' : ''}>
                                <label class="btn btn-outline-primary" for="price-asc" onclick="sortProducts('price-asc')">Giá: Tăng dần</label>

                                <input type="radio" class="btn-check" name="sort-options" id="price-desc" autocomplete="off" ${currentSort == 'price-desc' ? 'checked' : ''}>
                                <label class="btn btn-outline-primary" for="price-desc" onclick="sortProducts('price-desc')">Giá: Giảm dần</label>

                                <input type="radio" class="btn-check" name="sort-options" id="name-asc" autocomplete="off" ${currentSort == 'name-asc' ? 'checked' : ''}>
                                <label class="btn btn-outline-primary" for="name-asc" onclick="sortProducts('name-asc')">A-Z</label>

                                <input type="radio" class="btn-check" name="sort-options" id="name-desc" autocomplete="off" ${currentSort == 'name-desc' ? 'checked' : ''}>
                                <label class="btn btn-outline-primary" for="name-desc" onclick="sortProducts('name-desc')">Z-A</label>
                            </div>
                        </div>
                        <c:forEach items="${products}" var="product">
                            <form  class="mb-3 col-md-6 col-lg-3 p-5">
                                <a href="/products?action=view-product&name=${product.name}">
                                    <img src="image/products-small/${product.image}" alt="${product.name}"
                                         class="img-fluid asus_product img_sell">
                                    <h3>${product.name}</h3>
                                    <h4>${product.description}</h4>
                                    <h1 class="price"><fmt:formatNumber value="${product.price}"/> VND</h1>
                                </a>
                            </form>
                        </c:forEach>
                    </div>
                </div>
            </c:if>
        </div>

        <div class="pagination justify-content-center">
            <ul class="pagination">

                <c:if test="${currentPage > 1}">
                    <li class="page-item">
                        <a class="page-link" href="products?page=${currentPage - 1}&action=${param.action}"
                           aria-label="Previous">
                            <span aria-hidden="true">&laquo;</span>
                            <span class="sr-only">Previous</span>
                        </a>
                    </li>
                </c:if>


                <c:forEach begin="1" end="${totalPages}" var="i">
                    <c:choose>
                        <c:when test="${currentPage eq i}">
                            <li class="page-item active" aria-current="page">
                                <span class="page-link">${i}</span>
                            </li>
                        </c:when>
                        <c:otherwise>
                            <li class="page-item">
                                <a class="page-link" href="products?page=${i}&action=${param.action}">${i}</a>
                            </li>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>


                <c:if test="${currentPage < totalPages}">
                    <li class="page-item">
                        <a class="page-link" href="products?page=${currentPage + 1}&action=${param.action}"
                           aria-label="Next">

                            <span aria-hidden="true">&raquo;</span>
                            <span class="sr-only">Next</span>
                        </a>
                    </li>
                </c:if>
            </ul>
        </div>
    </div>

</section>
<section class="myfooter bg-dark custom-text-white py-4">
    <div class="container">
        <div class="row">
            <div class="col-md-6 info ">
                Thiên đường LapTop <br>
                Công ty cổ phần thương mại 4Gear Sttore <br>
                Chứng nhận ĐKKD số 22222222 do sở Code Gym cấp <br>
                Địa chỉ Đà nẵng,Sài Gòn,Ha Nội <br>
                Điện thoại : 09050505 <br>
                Email chuyensupport@4gear.vn <br>
            </div>
            <div class="col-md-6">
                <h5>Nhận Tin khuyến mãi</h5>
                <div class="input-group mb-3">
                    <input type="text" class="form-control" placeholder="your email" aria-label="Recipient's username"
                           aria-describedby="basic-addon2">
                    <button class="input-group-text custom-text-white bg-danger" id="basic-addon3">Đăng ký</button>
                </div>
                <div>
                    <span class="box50  ">
                      <i class="fa-brands fa-facebook"></i>
                    </span>
                    <span class="box50 tiktok ">
                        <i class="fa-brands fa-tiktok"></i>
                    </span>
                    <span class="box50 youtube ">
                     <i class="fa-brands fa-youtube"></i>
                    </span>
                    <span class="box50 google ">
                  <i class="fa-brands fa-twitter"></i>
                    </span>
                </div>
            </div>
        </div>
    </div>

</section>

<div style="color: #2c3034" class="modal fade" id="signup" tabindex="-1" role="dialog"
     aria-labelledby="addProductModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">

            <div class="modal-header">
                <h5 class="modal-title" id="addProductModalLabel">
                    <i class="fa fa-sign-in-alt" style="margin-right: 10px;"></i> Đăng nhập
                </h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>

            <div class="modal-body">
                <form action="/users" method="post">
                    <input type="hidden" name="action" value="login">
                    <div class="mb-3">
                        <label for="exampleInputAccount" class="form-label">Tên tài khoản</label>
                        <input class="form-control" id="exampleInputAccount" name="account" required>
                        <div id="" class="form-text"><span></span></div>
                    </div>
                    <div class="mb-3">
                        <label for="exampleInputPassword1" class="form-label">Mật khẩu</label>
                        <input type="password" class="form-control" id="exampleInputPassword1" name="password" required>
                        <div>
                            <span id="loginError"
                                  data-error="<%= request.getAttribute("loginError") != null ? request.getAttribute("loginError") : "" %>"
                                  style="display: none; color: red;margin-top: 10px"></span>
                        </div>
                    </div>

                    <button type="submit" class="btn btn-primary" style="width:100%;height: 40px;margin-bottom:10px">
                        <span style="font-size: 16px">Đăng nhập</span>
                    </button>

                    <div> Nếu chưa có tài khoản? <a href="#" data-bs-toggle="modal" data-bs-target="#register" style="margin-left: 20px;color: #0b5ed7">Đăng ký</a>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="register" tabindex="-1" role="dialog" aria-labelledby="registerLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="registerLabel">
                    <i class="fa fa-user-plus" style="margin-right: 10px;"></i> Đăng ký
                </h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form action="users" method="post">
                    <input type="hidden" name="action" value="create">
                    <div class="form-group mt-2">
                        <label for="nameAccount">Tên tài khoản <span class="text-danger require">*</span></label>
                        <input type="text" class="form-control mt-1" id="nameAccount" name="account"
                               placeholder="Nhập tên tài khoản" value="${requestScope.account}" required>
                        <div>
            <span id="accountError"
                  data-error="${requestScope.accountError}"
                  style="display: ${not empty requestScope.accountError ? 'block' : 'none'}; color: red;margin-top: 10px">
                ${requestScope.accountError}
            </span>
                        </div>
                    </div>
                    <div class="form-group mt-2">
                        <label for="passWord">Mật khẩu <span class="text-danger require">*</span></label>
                        <input type="password" class="form-control mt-1" id="passWord" name="password1"
                               placeholder="Nhập mật khẩu" required>
                        <span id="pass1Error"
                              data-error="${requestScope.pass1Error}"
                              style="display: ${not empty requestScope.pass1Error ? 'block' : 'none'}; color: red;margin-top: 10px">
                            ${requestScope.passError}
                        </span>
                    </div>
                    <div class="form-group mt-2">
                        <label for="passWordAgain">Xác nhận mật khẩu <span class="text-danger">*</span></label>
                        <input type="password" class="form-control mt-1" id="passWordAgain" name="password2"
                               placeholder="Nhập lại mật khẩu" required>
                        <span id="passError"
                              data-error="${requestScope.passError}"
                              style="display: ${not empty requestScope.passError ? 'block' : 'none'}; color: red;margin-top: 10px">
                            ${requestScope.passError}
                        </span>
                    </div>
                    <div class="form-group mt-2">
                        <label for="email">Email <span class="text-danger require">*</span></label>
                        <input type="email" class="form-control mt-1" id="email" name="email"
                               placeholder="Nhập email" value="${requestScope.email}" required>
                        <span id="emailError"
                              data-error="${requestScope.emailError}"
                              style="display: ${not empty requestScope.emailError ? 'block' : 'none'}; color: red;margin-top: 10px">
                            ${requestScope.emailError}
                        </span>
                    </div>
                    <div class="form-group mt-2">
                        <label for="name">Họ và tên <span class="text-danger require">*</span></label>
                        <input type="text" class="form-control mt-1" id="name" name="name"
                               placeholder="Nhập họ và tên" value="${requestScope.name}" required>
                        <span id="nameError"
                              data-error="${requestScope.nameError}"
                              style="display: ${not empty requestScope.nameError ? 'block' : 'none'}; color: red;margin-top: 10px">
                            ${requestScope.nameError}
                        </span>
                    </div>
                    <div class="form-group mt-2">
                        <label for="telephone">Số điện thoại</label>
                        <input type="text" class="form-control mt-1" id="telephone" name="phone"
                               placeholder="Nhập số điện thoại" value="${requestScope.phone}" required>
                        <span id="phoneError"
                              data-error="${requestScope.phoneError}"
                              style="display: ${not empty requestScope.phoneError ? 'block' : 'none'}; color: red;margin-top: 10px">
                            ${requestScope.phoneError}
                        </span>
                    </div>
                    <div class="form-group mt-2">
                        <label for="address">Địa chỉ</label>
                        <textarea class="form-control mt-1" id="address" name="address"
                                  placeholder="Nhập địa chỉ" required>${requestScope.address}</textarea>
                        <span id="addressError"
                              data-error="${requestScope.addressError}"
                              style="display: ${not empty requestScope.addressError ? 'block' : 'none'}; color: red;margin-top: 10px">
                            ${requestScope.addressError}
                        </span>
                    </div>
                    <button type="submit" class="btn btn-primary mt-3 btn-lg">Đăng ký</button>
                </form>

            </div>
        </div>
    </div>
</div>


<script src="javascript/js.js"></script>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script src="bootstrap-5.2.3-dist/js/bootstrap.js"></script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="javascript/loginSuccess.js"></script>
<script>
    function sortProducts(sortOption) {
        // Get the current URL
        let url = new URL(window.location.href);
        let params = new URLSearchParams(url.search);

        // Update or add the 'sort' parameter
        params.set('sort', sortOption);

        // Update or add the 'page' parameter to 1 (reset to first page when sorting)
        params.set('page', '1');

        // Reconstruct the URL with the new parameters
        url.search = params.toString();

        // Redirect to the new URL
        window.location.href = url.toString();
    }
</script>


</body>
</html>

