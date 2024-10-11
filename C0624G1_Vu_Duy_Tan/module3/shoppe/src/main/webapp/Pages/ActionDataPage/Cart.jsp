<%@page import="model.BOs.ProductBO"%>
<%@page import="model.BOs.OwnerShopBO"%>
<%@page import="model.entities.Shop"%>
<%@page import="model.entities.Product"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.entities.Cart"%>
<%@page import="model.entities.Client"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link
	href="https://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700,800"
	rel="stylesheet">
<script
	src="//cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<style type="text/css">
.container {
	max-width: 980px !important;
	overflow: unset !important;
}

.content:not(:last-child) {
	margin-bottom: 0 !important;
}

.content h2:not(:first-child) {
	margin-top: 0 !important;
}
</style>
</head>
<body>

	<%@ include file="/Pages/MasterPage/Header.jsp" %>
	<link href="<%=request.getContextPath()%>/style/assets/css/notify.css"
		rel="stylesheet" />
	<link
		href="<%=request.getContextPath()%>/style/assets/css/stylePageCart.css"
		rel="stylesheet" />

	<header id="site-header">
		<div class="container">
			<h1>
				Shopping cart <span>[</span> <em><a href="" target="_blank">CodePen
						Challange</a></em> <span class="last-span is-open">]</span>
			</h1>
		</div>
	</header>

	<div class="container">
		<section id="cart">
			<%
			long subtotal = 0;
			if (itemsCartList != null)
				for (Cart cart : itemsCartList) {
					Product product = ProductBO.getProductByID(cart.getProductID());
					Shop shop = OwnerShopBO.getShopByID(product.getShopID());
					subtotal += cart.getQuantity() * (Long.parseLong(product.getSalePrice()));
			%>
			<article class="product">
				<header>
					<a
						href="GioHang?<%="cartID=" + cart.getCartID() + "&actionCart=remove"%>"
						class="remove"> <img src="<%=product.getUrl()%>" alt="">

						<h3>Remove product</h3>
					</a>
				</header>

				<div class="content">

					<h1><%=shop.getNameShop()%></h1>
					<%=product.getProduct()%>
					<div
						title="You have selected this product to be shipped in the color yellow."
						style="top: 0" class="color yellow"></div>
					<div style="top: 43px" class="type small"><%=cart.getFeature()%></div>
				</div>

				<footer class="content">
					<a
						href="GioHang?<%="cartID=" + cart.getCartID() + "&actionCart=minus&quantity=" + cart.getQuantity()%>"><span
						class="qt-minus">-</span></a> <span class="qt"><%=cart.getQuantity()%></span>
					<a
						href="GioHang?<%="cartID=" + cart.getCartID() + "&actionCart=plus&quantity=" + cart.getQuantity()%>"><span
						class="qt-plus">+</span></a>

					<h2 class="full-price">
						<%=Product.formMoney(Long.toString(cart.getQuantity() * (Long.parseLong(product.getSalePrice()))))%>đ
					</h2>

					<h2 class="price">
						<%=Product.formMoney(product.getSalePrice())%>đ
					</h2>
				</footer>
			</article>
			<%
			}
			%>

		</section>

	</div>

	<footer id="site-footer">
		<div class="container clearfix">
			<div class="left">
				<h2 class="subtotal">
					Subtotal: <span><%=Product.formMoney(Long.toString(subtotal))%></span>đ
				</h2>
				<h3 class="tax">
					Taxes (2%): <span> <%
 long tax = Math.round((subtotal * 0.02));
 out.print(Product.formMoney(Long.toString(tax)));
 %>
					</span>đ
				</h3>
				<h3 class="shipping">
					Shipping: <span>20.000</span>đ
				</h3>
			</div>

			<div class="right">
				<h1 class="total">
					Total: <span><%=subtotal != 0 ? Product.formMoney(Long.toString(tax + subtotal + 20000)) : 0%></span>đ
				</h1>
				<%
				if (subtotal != 0) {
					if (subtotal + tax + 20000 <= Long.parseLong(client.getMoney()))
						out.print("<button class=\"btn btn-success\" style=\"width: 100%\">Checkout</button>");
					else
						out.print("<button class=\"btn btn-eror\" style=\"width: 100%\">Checkout</button>");
				} else
					out.print("<button class=\"btn\" style=\"width: 100%\">Checkout</button>");
				%>
			</div>


		</div>
	</footer>
	<div id="notify" class="close">
		<div id="success-box" class="close">
			<span></span>
			<div class="dot"></div>
			<div class="dot two"></div>
			<div class="face">
				<div class="eye"></div>
				<div class="eye right"></div>
				<div class="mouth happy"></div>
			</div>
			<div class="shadow scale"></div>
			<div class="message">
				<h1 class="alert">Success!</h1>
				<p>Cảm ơn bạn đã sử dụng dịch vụ của chúng tôi!</p>
			</div>
			<form method="post">
				<input name="totalMoney" style="display: none"
					value="<%=subtotal + tax + 20000%>">
				<button class="button-box">
					<h1 class="green">Xác Nhận</h1>
				</button>
			</form>
		</div>
		<div id="error-box" class="close">
			<div class="dot"></div>
			<div class="dot two"></div>
			<div class="face2">
				<div class="eye"></div>
				<div class="eye right"></div>
				<div class="mouth sad"></div>
			</div>
			<div class="shadow move"></div>
			<div class="message">
				<h1 class="alert">Not Enough Money!</h1>
				<p>Vui lòng nạp thêm tiền!
			</div>
			<button class="button-box">
				<a href="Payment"><h1 class="red"
						style="background-color: #FFFF">Nạp Tiền</h1></a>
			</button>
		</div>
	</div>
	<script type="text/javascript">
let btn_eror= document.querySelector('.btn-eror');
let btn_success= document.querySelector('.btn-success');
if(btn_eror)
	document.querySelector('.btn-eror').addEventListener('click',()=>{ 
		document.getElementById('notify').classList.remove('close');
		document.getElementById('error-box').classList.remove('close');
	});
if(btn_success)
	document.querySelector('.btn-success').addEventListener('click',()=>{
		document.getElementById('notify').classList.remove('close');
		document.getElementById('success-box').classList.remove('close');
	});

</script>
</body>
</html>