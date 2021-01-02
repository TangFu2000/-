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

            <form action="/commodityAuction/SubmitAlterCommodityServlet" method="post" enctype="multipart/form-data">
                <br><br>
                <input type="text" id="postItemId" name="postItemId" style="display: none">
                拍品名：<input name="name" id="inputName" style="width:330px;" onkeyup="if(value.length>40)value=value.slice(0,40)" /><br/>
                拍品起拍价：<input name="price" id="inputPrice" onkeyup="value=value.replace(/[^(\d)]/g,'');if(value.length>7)value=value.slice(0,7)"/><br/>
                拍品的简介：<input name="introduce" id="inputIntroduce" style="width:330px;" onkeyup="if(value.length>40)value=value.slice(0,40)" /><br/>

                拍品截止时间：<input type="date" name="endTime" id="inputEndTime" style="width:330px;" onkeyup="if(value.length>40)value=value.slice(0,40)" min="addTime" onclick="time()" /><br/>
                选择您要修改拍品的类别
                <select name="select">
                    <option value="book">书籍漫画</option>
                    <option value="watch">手表腕表</option>
                    <option value="stamp">邮票钱币</option>
                    <option value="wine">葡萄酒及威士忌</option>
                </select><br/>
                选择拍品图片：<input type="file" name="uploadFile" id="File" onchange="preview(this)"/><br>
                <span id="preview"><img class="updateImg" style="display: block;" id="imgHidden" width="150px" height="150px"/></span>
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
        var inputPrice=document.getElementById("inputPrice").value;
        var inputIntroduce=document.getElementById("inputIntroduce").value;
        var fileVal=document.getElementById("File").value;
        var inputName=document.getElementById("inputName").value;
        var inputEndTime=document.getElementById("inputEndTime").value;
// 	alert(fileVal);

        if(!inputName){
            alert("请输入拍品名！");
            return false;
        }else if (!inputPrice) {
            alert("请输入起拍价！");
            return false;
        }
        else if (!inputIntroduce) {
            alert("请输入拍品简介！");
            return false;
        }else if(!inputEndTime){
            alert("请输入拍品截止时间！");
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
    var itemId=getParameter("itemId");
    var id=document.getElementById("postItemId");
    id.value=itemId;
    console.log(id.value);
        console.log("修改");
        $.ajax({
            url : "/commodityAuction/AlterCommodityServlet",//要访问的后台地址
            type : "post",								//使用post方法访问后台
            dataType : "json",							//返回json格式的数据
            data:{itemId:itemId},						//要发送的数据
            success : function(data) {					//data为返回的数据，在这里做数据绑定
                console.log(data.itemName);
                var inputName=document.getElementById("inputName");
                var inputPrice=document.getElementById("inputPrice");
                var inputIntroduce=document.getElementById("inputIntroduce");
                var inputEndTime=document.getElementById("inputEndTime");
                var select=document.getElementsByName("select");
                var file=document.getElementById("File");
                var img=document.getElementById("imgHidden");

                inputName.value=data.itemName;
                inputPrice.value=data.initPrice;
                inputIntroduce.value=data.info;
                var date=new Date(data.endTime);
                //格式化日，如果小于9，前面补0
                var day = ("0" + date.getDate()).slice(-2);
                //格式化月，如果小于9，前面补0
                var month = ("0" + (date.getMonth() + 1)).slice(-2);
                //拼装完整日期格式
                var date2 = date.getFullYear()+"-"+(month)+"-"+(day) ;
                inputEndTime.value=date2;
                select.value=data.type;
                img.setAttribute("src","imges/"+data.img);
                // img.src="imges/"+data.img;
            }
        });

    // });
</script>

<script>
    //图片回显:
    function preview(file) {
    $("#imgHidden").css("display", "none");
    var prevDiv = document.getElementById('preview');
    if (file.files && file.files[0]) {
        var reader = new FileReader();
            reader.onload = function(evt) {
                prevDiv.innerHTML = '<img width="150px" height="150px" class="updateImg" src="' + evt.target.result + '" />';
        }
        reader.readAsDataURL(file.files[0]);
    } else {
        prevDiv.innerHTML = '<div class="img" style="width: 100px;height:100px;filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src=\'' +
                                    file.value + '\'"></div>';
        }
   }
</script>

<%--<script>--%>
<%--    var itemId=getParameter("itemId");--%>
<%--    $.ajax({--%>
<%--        url : "/commodityAuction/AddOrderServlet",//要访问的后台地址--%>
<%--        type : "post",							//使用post方法访问后台--%>
<%--        dataType : "json",							//返回json格式的数据--%>
<%--        data:{itemId:itemId},							//要发送的数据--%>
<%--        success : function(data) {					//data为返回的数据，在这里做数据绑定--%>
<%--            // console.log(data);--%>
<%--            var img = document.getElementById("img");--%>
<%--            var type = document.getElementById("type");--%>
<%--            var buyerId=document.getElementById("buyerId");--%>
<%--            var itemInfo=document.getElementById("itemInfo");--%>
<%--            var addTime=document.getElementById("addTime");--%>
<%--            var date=new Date(data.addTime);--%>
<%--            var initPrice=document.getElementById("initPrice");--%>
<%--            var maxPrice=document.getElementById("maxPrice");--%>
<%--            var id=document.getElementById("id");--%>

<%--            img.src="imges/"+data.img;--%>
<%--            type.innerHTML=data.type;--%>
<%--            buyerId.innerHTML=data.buyerId;--%>
<%--            itemInfo.innerHTML=data.info;--%>
<%--            addTime.innerHTML=date.toLocaleDateString();--%>
<%--            initPrice.innerHTML=data.initPrice;--%>
<%--            maxPrice.innerHTML=data.maxPrice;--%>
<%--            id.innerHTML=data.buyerId;--%>
<%--        }--%>
<%--    });--%>
<%--</script>--%>

</body>
</html>
