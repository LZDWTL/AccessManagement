<%--
  Created by IntelliJ IDEA.
  User: 10237
  Date: 2021/6/23
  Time: 15:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
    <form action="login" method="POST">
        <label for="username">用户名：</label><input type="text" name="username" id="username">
        <label for="password-label">密码：</label><input type="password" name="password" id="password-label">
        <input type="submit" value="登录">
    </form>
</body>
</html>
