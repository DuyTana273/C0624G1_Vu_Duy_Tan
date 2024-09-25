<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Currency Converter</title>
</head>
<body>
    <h2>Currency Converter</h2>
    <form action="/convert" method="post" style="width: 200px">
        <label>Rate: </label> <br/>
        <input type="text" name="rate" value="22000"/> <br/>
        <label>USD: </label>
        <input type="number" name="usd" value="0"/> <br/>
        <input type="submit" id="submit" value="Converter"/>
    </form>
</body>
</html>