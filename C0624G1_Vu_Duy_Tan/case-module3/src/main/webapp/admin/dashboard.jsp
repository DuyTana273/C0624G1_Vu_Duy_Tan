<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 11/09/2024
  Time: 12:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Dashboard</title>

</head>
<body>
<header>
    <h1>Dashboard</h1>
    <nav>
        <a href="<c:url value='/admin' />">Home</a>
        <a href="<c:url value='/products1?action=admin' />">Manage Products</a>

    </nav>
</header>
<main>
    <h2>Dashboard Statistics</h2>
    <p>Total Products: <c:out value="${totalProducts}" /></p>
    <p>Total Orders: <c:out value="${totalOrders}" /></p>
    <p>Total Customers: <c:out value="${totalCustomers}" /></p>
</main>
</body>
</html>

