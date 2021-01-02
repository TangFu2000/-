<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2021/1/2
  Time: 8:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html lang="en" class="app">
<head>
    <meta charset="utf-8" />
    <title>后台登录页面</title>
    <meta name="description" content="app, web app, responsive, admin dashboard, admin, flat, flat ui, ui kit, off screen nav" />
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
    <link rel="stylesheet" href="css/bootstrap.css" type="text/css" />
    <link rel="stylesheet" href="css/simple-line-icons.css" type="text/css" />
    <link rel="stylesheet" href="css/app.css" type="text/css" />
</head>
<body background="images/bodybg.jpg">
<section id="content" class="m-t-lg wrapper-md animated fadeInUp ">
    <div class="container aside-xl" style="margin-top: 48px;">
        <a class="navbar-brand block"><span class="h1 font-bold" style="color: #ffffff">管理员后台登录</span></a>
        <section class="m-b-lg">
            <header class="wrapper text-center">
                <strong class="text-sucess">管理员登录</strong>
            </header>
            <form action="/commodityAuction/AdminLoginServlet" method="post" >
                <div class="form-group">
                    <input type="username" name="username" placeholder="用户名" class="form-control  input-lg text-center no-border">
                </div>
                <div class="form-group">
                    <input autocomplete="off" type="password" name="password" placeholder="密码" class="form-control  input-lg text-center no-border">
                </div>

                <button type="submit" class="btn btn-lg btn-danger lt b-white b-2x btn-block" id="validate-submit" >
                    <i class="icon-arrow-right pull-right"></i><span class="m-r-n-lg">登录</span></button>
            </form>
        </section>
    </div>
</section>
<!-- footer -->
<footer id="footer">
    <div class="text-center padder">

    </div>
</footer>

</body>
</html>

