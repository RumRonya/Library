<%--
  Created by IntelliJ IDEA.
  User: MSI
  Date: 27.06.2019
  Time: 11:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/login" method="post">
    <input type="text" name="user-name"/>
    <input type="password" name="password"/>
    <input type="submit" name="submit" value="Log In"/>
</form>
<a href="/register">Registration</a>
</body>
</html>
