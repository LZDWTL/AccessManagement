<%--
  Created by IntelliJ IDEA.
  User: liwei
  Date: 2021-06-16
  Time: 21:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Index</title>
</head>
<body>
${user.username}
<c:forEach items="${user.roleList}" var="role">
    该用户的角色：${role.name}
</c:forEach>
</body>
</html>
