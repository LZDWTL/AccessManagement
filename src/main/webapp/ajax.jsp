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
            $("#menu").on("click",".first-menu",function () {
                // $(this).find("ul").css("display","visible");
                $(this).find("ul").slideToggle(500);
                // $(this).find("ul").toggle(500);   //这个和上面这个有点区别

            });

            // 二级菜单点击变色
            $("#menu").on("click",".second-menu>li",function () {
                if (flag == 0) {
                    $(this).css("backgroundColor", "rgb(240, 198, 143)");
                    flag = 1;
                } else {
                    $(this).css("backgroundColor", "antiquewhite");
                    flag = 0;
                }

            });

            //二级菜单阻止事件冒泡
            $("#menu").on("click",".second-menu>li",function (e) {
                e.stopPropagation();
            });
        });

        /**
         * $(function ())等价于$(document).ready(function())
         */
        $(function () {
            $.ajax({
                url: "permission?userid=${user.id}",  //el表达式直接用就可以了
                success: function (data) {   //回调函数
                    // var ulE=document.createElement("ul");
                    // var $ul=$("<ul id='menu'\>");
                    // $ul.appendTo('#wrapper');  //把ul元素放入div的最后面


                    $.each(data, function (index, permission) {
                        if (permission.isParent == "true") {
                            var $li = $("<li class=\"first-menu\"\>");    //jQuery变量前面最好带个$
                            var $a = $("<a href=\"javascript:void(0)\">" + permission.name + "</a>");
                            var $secondul = $("<ul class='second-menu'\>");
                            $("#menu").append($li);
                            $li.append($a);
                            $li.append($secondul);
                            $.each(data, function (index, childpermission) {
                                if (childpermission.pid == permission.id) {
                                    var $secondli = $("<li\>");
                                    var $seconda = $("<a href=\"javascript:void(0)\">" + childpermission.name + "</a>");
                                    $secondli.appendTo($secondul);
                                    $secondli.append($seconda);
                                }
                            });
                        }
                    });
                }
            });
        });
    </script>
</head>
<body>
<div id="wrapper">
    <%-- javascript获取菜单的json数据后，再动态生成dom--%>
    <ul id="menu">

    </ul>
</div>
</body>
</html>
