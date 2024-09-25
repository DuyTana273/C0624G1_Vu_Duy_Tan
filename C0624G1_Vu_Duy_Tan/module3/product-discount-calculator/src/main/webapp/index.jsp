<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Product Discount Calculator</title>
</head>
<body>
    <h2>Product Discount Calculator</h2>
    <form action="/product-calculator" method="post">
        <label for="description">Product Description:</label><br>
        <input type="text" id="description" name="description" placeholder="Nhập mô tả"><br><br>

        <label for="listPrice">List Price:</label><br>
        <input type="number" id="listPrice" name="listPrice" step="0.1" placeholder="Nhập giá ($)"><br><br>

        <label for="discountPercent">Discount Percent:</label><br>
        <input type="number" id="discountPercent" name="discountPercent" step="0.01" placeholder="Nhập tỷ lệ chiết khấu (%)"><br><br>

        <input type="submit" value="Calculate Discount">
    </form>
</body>
</html>