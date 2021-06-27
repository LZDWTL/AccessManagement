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
    <title>Ajax</title>
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

        /**
         * $(function ())等价于$(document).ready(function())
         */
        $(function () {
            $.ajax({
                url: "permission?userid=${user.id}",
                success: function (data) {
                    console.log(data);
                }
            });
        });
    </script>
</head>
<body>
<dvi id="wrapper">
    <%-- javascript获取菜单的json数据后，再动态生成dom--%>
    <ul id="menu">

    </ul>
</dvi>
</body>
</html>
