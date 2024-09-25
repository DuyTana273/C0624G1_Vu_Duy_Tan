<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Map" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Simple Dictionary</title>
</head>
<body>
    <%!
        Map<String, String> dic = new HashMap<>();
    %>

    <%
        dic.put("hello", "Xin chào");
        dic.put("how", "Thế nào");
        dic.put("book", "Quyển vở");
        dic.put("computer", "Máy tính");
        String search = request.getParameter("search");
        String result = dic.get(search);
        if (result != null) {
            out.println("Word: " + search);
            out.println("Result: " + result);
        } else {
            out.println("Not found");
        }
    %>
</body>
</html>
