<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<%@page import="entity.User"%>--%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="com.jsu.bean.Buyer" %>

<%--<%--%>
<%--    String path = request.getContextPath();--%>
<%--    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";--%>
<%--%>--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="">

    <title>修改</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width,initial-scale=1" />
    <link rel="stylesheet" type="text/css" href="css/user.css">
    <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">

    <script src="js/getParameter.js"></script>
    <script src="js/jquery-2.1.0.js"></script>
</head>

<div class="content-div">
    <div class="content">
        <div class="content-center" >

            <form action="/commodityAuction/AlterCommodityManageServlet" method="post" >
                <br><br>
                <input type="text" id="postBuyerId" name="postBuyerId" style="display: none">
                用户名：<input name="name" id="inputName" style="width:330px;" onkeyup="if(value.length>40)value=value.slice(0,40)" /><br/>
                电子邮箱：<input name="email" id="inputEmail" onkeyup="value=value.replace(/[^(\d)]/g,'');if(value.length>7)value=value.slice(0,7)"/><br/>
                密码：<input name="password" id="inputPassword" style="width:330px;" onkeyup="if(value.length>40)value=value.slice(0,40)" /><br/>
                余额：<input name="balance" id="inputBalance" style="width:330px;" onkeyup="if(value.length>40)value=value.slice(0,40)" /><br/>
                <br/><br/>
                <input type="submit" value="确认修改" onclick=" return SumbitJudge()"/>

            </form>
        </div>
    </div>
</div>

<br>
<script type="text/javascript">
    //表单未填写完全禁止提交
    function SumbitJudge() {
// 	alert("sada");
        var inputName=document.getElementById("inputName").value;
        var inputEmail=document.getElementById("inputEmail").value;
        var inputPassword=document.getElementById("inputPassword").value;
        var inputBalance=document.getElementById("inputBalance").value;

        if(!inputName){
            alert("请输入用户名！");
            return false;
        }else if (!inputEmail) {
            alert("请输入电子邮箱！");
            return false;
        }
        else if (!inputPassword) {
            alert("请输入密码！");
            return false;
        }else if(!inputBalance){
            alert("请输入余额！");
            return false;
        }
        return true;
    }
</script>
<script type="text/javascript" src="js/user.js"></script>

<script>
    /**
     * 不能选取当前日期之前的日期
     */
    function time() {
        var endTime =document.getElementById("inputEndTime");
        var date=new Date();
        var str=date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate();
        endTime.setAttribute("min",str);
    }
</script>


<script>
    // $(function() {
    var buyerId=getParameter("buyerId");
    var id=document.getElementById("postBuyerId");
    id.value=buyerId;
    console.log(id.value);
    console.log("修改");
    $.ajax({
        url : "/commodityAuction/UserAlterServlet",//要访问的后台地址
        type : "post",								//使用post方法访问后台
        dataType : "json",							//返回json格式的数据
        data:{buyerId:buyerId},						//要发送的数据
        success : function(data) {					//data为返回的数据，在这里做数据绑定
            console.log(data.buyerId);
            var inputName=document.getElementById("inputName");
            var inputEmail=document.getElementById("inputEmail");
            var inputPassword=document.getElementById("inputPassword");
            var inputBalance=document.getElementById("inputBalance");

            inputName.value=data.buyerName;
            inputEmail.value=data.email;
            inputPassword.value=data.password;
            inputBalance.value=data.balance;
        }
    });

    // });
</script>

</body>
</html>
