<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Dictionary</title>
    <meta charset="utf-8">
</head>
<body>
    <h1>Vietnamese Dictionary</h1>
    <form action="TranslateServlet?action=search" method="post">
        <input type="text" name="search"/>
        <input type="submit" value="Search" id="submit"/>
    </form>
</body>
</html>