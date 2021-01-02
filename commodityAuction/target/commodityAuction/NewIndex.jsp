<%@ page import="com.jsu.bean.Buyer" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%--<!DOCTYPE html>--%>
<html>
<head>
    <meta charset="UTF-8">
    <title>在线拍卖系统</title>
    <link rel="stylesheet" href="css/HomePage.css" />
    <link rel="stylesheet" href="css/SerchBox.css" />
    <link rel="stylesheet" href="css/Carousel.css" />

    <script type="text/javascript" src="js/jquery-2.1.0.js" ></script>
    <script type="text/javascript" src="js/index.js" ></script>
</head>
<body>
<div class="HomePageTitle">
    <!--导航栏标签-->

    <nav>
        <!--超链接标签-->
        <!-- 			<a>个人中心</a> -->
        <%
            if (session.getAttribute("buyer") == null) {
        %>
        <!-- 			<a href="login.jsp">您好,请登录</a> -->
        <a href="/commodityAuction/login.jsp"><font color="#A6686A">您好，请登录 </font></a>
        <%-- 			<a href="<%=path%>/application/reg.jsp"><font color="red">免费注册</font></a> --%>
        <%
        } else {
        %>
        <%-- 			<a href="<%=path%>/user.jsp">个人中心</a> --%>
        <a href="<%=((Buyer)session.getAttribute("buyer")).getBuyerId() %>"><font color="#A6686A">欢迎您,<%=((Buyer) session.getAttribute("buyer")).getBuyerName()%></font></a>
        <a href="addCommodity.jsp?buyerId=<%=((Buyer) session.getAttribute("buyer")).getBuyerId()%>">上架商品</a>
        <a href="/commodityAuction/IndexServlet?login=no" ><font color="#D6686A">注销</font> </a>
        <%
            }
        %>

        <a href="/commodityAuction/index.jsp">拍卖网欢迎您</a>
    </nav>

</div>
<!-- 搜索框 -->
<div class="HomePageTitle2">
    <div class="serchBox">
        <form action="" name="search" class="search" method="post" ><br>
            <input type="text" id="serchInput" name="serchInput" placeholder="搜索"/>
            <input type="submit" id="serchBtn" value="搜索" onclick=" return SumbitJudge()"/>
        </form>
    </div>
</div>
<script type="text/javascript">
    //表单未填写完全禁止提交
    function SumbitJudge() {
// 	alert("sada");
        var input=document.getElementById("serchInput").value;
        if (!input) {
            alert("请输入搜索内容！");
            return false;
        }
        return true;
    }
</script>
<!-- 导航栏 -->
<div class="Navigation1" >
    <nav>
        <!--超链接标签-->
        <a href="/commodityAuction/NewIndex.jsp">首页</a>
        <a href="">腕表</a>
        <a href="">钱币邮票</a>
        <a href="book.jsp">书籍漫画</a>
        <a href="">葡萄酒及威士忌</a>
    </nav>
</div>
<!-- 轮播 -->
<div class="test">
    <div id="Carousel">

        <!--小方格切换-->
        <ul id="List">
            <li class="FillSquare"></li>
            <li></li>
            <li></li>
            <li></li>
        </ul>
        <!--图片-->
        <div id="ImgBox">
            <img class="FirstImage" src="img/S1.jpg" />
            <img src="img/S2.jpg" />
            <img src="img/S3.jpg" />
            <img src="img/S4.jpg" />
        </div>
    </div>
</div>
<div class="popular">
    <p class="PopularP1">流行类别</p>
    <div class="CategoryBox">
        <a href="">
            <div class="Category1">
                <img alt="" src="img/watch.PNG">
            </div>
        </a>
        <a href="book.jsp">
            <div class="Category2">
                <img alt="" src="img/typeForBook.PNG">
            </div>
        </a>
        <a href="">
            <div class="Category3">
                <img alt="" src="img/typeForStamp.PNG">
            </div>
        </a>
        <a href="">
            <div class="Category4">
                <img alt="" src="img/wine.PNG">
            </div>
        </a>
    </div>
</div>
<div class="bookBox" id="bookBox">
    <p class="title">书籍及漫画</p>
    <!-- 		<div class="book1" id="book1">  -->
    <%-- 			<img alt="" src="<%=BookImgUrl[0]%>"> --%>
    <%-- 			<p><%=BookIntroduce[0]%></p> --%>
    <!-- 			<p class="p2"> -->
    <!-- 			截止日期(UTC+8): -->
    <%-- 			<fmt:formatDate type="both" value="<%=BookDate[0]%>" pattern="yyyy年MM月dd日 HH:mm:ss"/> --%>
    <!-- 			</p> -->
    <!-- 		</div> -->
</div>
<div class="stampBox" id="stampBox">
    <p class="title">钱币及邮票</p>
</div>

<script type="text/javascript" src="js/Carousel.js" ></script>
<script type="text/javascript" src="js/CreateIndex.js" ></script>



</body>
</html>