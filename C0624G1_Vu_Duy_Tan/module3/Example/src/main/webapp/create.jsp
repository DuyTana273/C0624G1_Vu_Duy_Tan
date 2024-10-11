<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>User Management Application</title>
</head>
<body>
<center>
    <h1>User Management</h1>
    <h2>
        <!-- Đảm bảo URL chính xác để dẫn đến danh sách người dùng -->
        <a href="users?action=list">List All Users</a>
    </h2>
</center>
<div align="center">
    <!-- Form gửi yêu cầu POST đến /user với action create -->
    <form method="post" action="users?action=create">
        <table border="1" cellpadding="5">
            <caption>
                <h2>Add New User</h2>
            </caption>
            <tr>
                <th>User Name:</th>
                <td>
                    <input type="text" name="name" id="name" size="45" required/>
                </td>
            </tr>
            <tr>
                <th>User Email:</th>
                <td>
                    <input type="email" name="email" id="email" size="45" required/>
                </td>
            </tr>
            <tr>
                <th>Country:</th>
                <td>
                    <input type="text" name="country" id="country" size="15" required/>
                </td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <input type="submit" value="Save"/>
                </td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
