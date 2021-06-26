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
    <link rel="stylesheet" href="./css/menu.css">
    <script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.8.0.js"></script>
    <script>
        $(document).ready(function () {
            var flag = 0;

            // 一级菜单点击之后会展开或者折叠二级菜单
            $(".first-menu").click(function () {
                // $(this).find("ul").css("display","visible");
                $(this).find("ul").slideToggle(500);
                // $(this).find("ul").toggle(500);   //这个和上面这个有点区别

            });

            // 防止一级菜单点击冒泡
            $(".first-menu").bind("click", function (event) {
                event.stopPropagation();   //阻止冒泡的方法
            });

            // 二级菜单点击变色
            $(".second-menu>li").click(function () {
                if (flag == 0) {
                    $(this).css("backgroundColor", "rgb(240, 198, 143)");
                    flag = 1;
                } else {
                    $(this).css("backgroundColor", "antiquewhite");
                    flag = 0;
                }

            });
            $(".second-menu").on("click", function (e) {
                e.stopPropagation();
            });
        });
    </script>
</head>
<body>
<dvi id="wrapper">
    <ul id="menu">
        <c:forEach items="${user.roleList[0].permissionList}" var="menu">
            <c:if test="${menu.isParent eq true}">
                <li class="first-menu">
                    <a href="javascript:void(0)">${menu.name}</a>
                    <ul class="second-menu">
                        <c:forEach items="${user.roleList[0].permissionList}" var="childMenu">
                            <c:if test="${childMenu.pid eq menu.id}">
                                <li>
                                    <a href="javascript:void(0)">${childMenu.name}</a>
                                </li>
                            </c:if>
                        </c:forEach>
                    </ul>
                </li>
            </c:if>
        </c:forEach>
    </ul>
</dvi>
</body>
</html>
