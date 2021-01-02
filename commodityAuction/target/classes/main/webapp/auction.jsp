<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"
    import="com.jsu.bean.Buyer"
%>

<%

//    String path = request.getContextPath();
//    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    if(session.getAttribute("buyer")==null){
//    if(session.getAttribute("buyer")==null){
        response.sendRedirect("/commodityAuction/login.jsp");
    }else{
%>

<html>
<head>
    <title>拍卖品详情</title>
    <script src="js/getParameter.js"></script>
    <script src="js/jquery-2.1.0.js"></script>
</head>
<body bgcolor="#f9f9f9">


<input type = "text" style ="display:none" name = "sessionId" value="${sessionScope.buyer.buyerId}">


<div><a href="/commodityAuction/NewIndex.jsp"><font siz e="+2">返回</font></a> </div><br>
<br><br>
<div style="width: 60%; height: 5%; text-align: center;display: inline-block;margin-left: auto;margin-right: auto" align="center" >
    距离拍卖结束时间还有:
    <span id="remainingTime">

    </span>
<%--    距离拍卖结束时间还有:<font size="5" id="remainingTime"> </font><font size="6" color="red" id="time"></font>--%>
</div><br>

<%--<form action="/commodityAuction/AddOrderServlet" method="get" onsubmit="submit()">--%>
<div style="margin:0 auto; width: 60%;height: 55%; background-color: rgb(255,255,255);">
    <div style="float: left; width:25%; height:100%; margin: 50 60;">
        <style type="text/css">
            div img{
                cursor: pointer;
                transition: all 0.1s;
            }
            div img:hover{
                transform: scale(1.2);
            }
        </style>
        <img src="" style="width: 200px;height: 200px;" id="img"> <br><br><br>
        物品类型: <span><font size="+1" id="type"></font></span><br><br><br>
        拍卖人: <span><font size="+1" color="#000000" id="buyerId"></font></span><br>
    </div>
    <div style="float: left; width:40%; height:80%; margin: 50 60;">

        物品介绍: <span><font size="" color="#000000" id="itemInfo"> </font></span><br><br><br>
        起拍日期:<span id="addTime"></span><br><br><br>
        <input type="text" value="" style="display: none" id="time" value="">
        <input type="text" value="" style="display: none" id="endTime" value="">
<%--        <p in="endTime" style="display: none" ></p>--%>
        起拍价格:<span id="initPrice"></span> <br><br><br>
        当前最高出价:$:<span id="maxPrice"></span><br><br><br>
        当前最高出价者id:<span id="id"></span><br>
    </div>
</div>
<div style="margin:0 auto; width: 60%;height: 20%; background-color: #FdFdFd;">
    <div style="margin:0 auto; float:left; margin-right: 20%;">
<%--        <form action="/commodityAuction/AddOrderServlet" method="get" onsubmit="submit()">--%>
<%--            return submit()--%>
            <input type="hidden" name="user_id" value="">
            <input type="hidden" name="goods_id" value="">
            <input type="hidden" name="type" value="">

            <%if(session.getAttribute("buyer") == null){ %>
            <input type="hidden" name="winner_id" value="error" id="winner_id" >
            <%}else{ %>
            <input type="hidden" name="winner_id" value="" id="winner_id">
            <%} %>
            输入竞拍价格：<input type="text" name="max_price" id="price" style="border-bottom:1 solid black;background:;" onkeyup="value=value.replace(/[^(\d)]/g,'');if(value.length>7)value=value.slice(0,7)"> <input type="submit" value="参与竞拍" id="submit" onclick="submit()">

<%--        </form>--%>
    </div>
    <div style="text-align:center; float:left; margin-right: 20%;">
    </div>
</div>
<%--</form>--%>

<%} %>


<script>
    var itemId=getParameter("itemId");
    $.ajax({
        url : "/commodityAuction/AddOrderServlet",//要访问的后台地址
        type : "post",							//使用post方法访问后台
        dataType : "json",							//返回json格式的数据
        data:{itemId:itemId},							//要发送的数据
        success : function(data) {					//data为返回的数据，在这里做数据绑定
            // console.log(data);
            var img = document.getElementById("img");
            var type = document.getElementById("type");
            var buyerId=document.getElementById("buyerId");
            var itemInfo=document.getElementById("itemInfo");
            var addTime=document.getElementById("addTime");
            var date=new Date(data.addTime);
            var initPrice=document.getElementById("initPrice");
            var maxPrice=document.getElementById("maxPrice");
            var id=document.getElementById("id");

            img.src="imges/"+data.img;
            type.innerHTML=data.type;
            buyerId.innerHTML=data.buyerId;
            itemInfo.innerHTML=data.info;
            addTime.innerHTML=date.toLocaleDateString();
            initPrice.innerHTML=data.initPrice;
            maxPrice.innerHTML=data.maxPrice;
            id.innerHTML=data.buyerId;


            var add=document.getElementById("time");
            var end=document.getElementById("endTime");
            var datef=new Date(data.endTime);

            console.log("add:"+data.addTime);
            console.log("end:"+data.endTime);
            add.value=data.addTime;
            end.value=data.endTime;
            // date.value=data.endTime;
            if(data.state==1){
                timer();
            }else{
                var date=document.getElementById("remainingTime");
                var submit = document.getElementById("submit");

                time=end.value-new Date().getTime();
                submit.value = "已结束拍卖";
                submit.disabled=true;
                date.innerHTML = "该物品已拍出";
            }
        }
    });
</script>
<%session.getAttribute("buyer");%>

<script>
    //提交判断
    function submit() {
        console.log("提交");
        var initPrice=document.getElementById("initPrice").innerHTML;
        console.log("initPrice:"+initPrice);
        var maxprice=document.getElementById("maxPrice").innerHTML;
        console.log("maxprice:"+maxprice);
        var price=document.getElementById("price").value;
        console.log("price:"+price);
        // var buyerId=document.getElementById("sessionId").value;
        // console.log("buyerID:"+buyerId);

        if(maxprice==null){
            alert("竞拍价格不能为空!");
            return false;
        }
        if(maxprice!=0){
            if(price<=maxprice){
                alert("竞拍价格不能小于等于当前最高出价!");
                return false;
            }else{
                alert("成功！");
                // location.href="/app/target.jsp?param1='aaaa'&parpam2='bbbbb'";
                //bid();
                location.href="/commodityAuction/AddOrderServlet?"+"itemId="+itemId+"&"+"price="+price;
            }
        }else{
            if(price<initPrice){
                alert("竞拍价格不能小于起拍价!");
                return false;
            }else{
                alert("成功！");
                // location.href="/commodityAuction/AddOrderServlet?"+"itemId="+itemId+"&"+"buyerId="+buyerId+"&"+"price="+price;
                //bid();
                location.href="/commodityAuction/AddOrderServlet?"+"itemId="+itemId+"&"+"price="+price;
            }
        }
    }
</script>

<script>
    // window.onload=timer;
    //计算倒计时
    function timer() {
        var add=document.getElementById("time");
        var end=document.getElementById("endTime");
        console.log("add:"+add.value);
        console.log("end:"+end.value);

        console.log("add:"+new Date(add.value));
        console.log("end:"+new Date((parseInt(end.value))));

        console.log("add:"+new Date(add.value).toLocaleDateString());
        console.log("end:"+new Date(parseInt(end.value)).toLocaleDateString());

        console.log("end.value-add.value:"+(end.value-add.value));

        var date=document.getElementById("remainingTime");
        var submit = document.getElementById("submit");
        var button = document.getElementById("endprice");

        time=end.value-new Date().getTime();
        if(time<=0){
            submit.value = "已结束拍卖";
            submit.disabled=true;
            button.disabled=true;
            date.innerHTML = "该物品已拍出";
        }
        else{
// 			var s=parseInt(ss % 60); // 秒
// 			var mi=parseInt((ss - s) / 60 % 60); // 分钟
// 			var h=parseInt(((ss - s) / 60 - mi) / 60 % 24); // 小时
// 			var d=parseInt((((ss - s) / 60 - mi) / 60 - h) / 24) // 天
//                 alert(time);
            var ss=time/1000;
            var seconds = parseInt(ss % 60);
            var minutes = parseInt((ss - seconds) / 60 % 60);
            var hours = parseInt(((ss-seconds)/60-minutes)/60%24);
            var days = parseInt((((ss-seconds)/60-minutes)/60-hours) / 24);
            console.log(days+"天"+hours+"时"+minutes +"分"+seconds+"秒");
            var interval = setInterval(function(){
                time = days+"天"+hours+"时"+minutes +"分"+seconds+"秒";
                date.innerHTML = time;
                seconds--;
                if(seconds <=0){
                    if(minutes<=0){
                        if(hours<=0){
                            if(days<=0){
                                //结束
                                submit.disabled=true;
                                //button.disabled=true;
                                days=0;hours=0;minute=0;seconds=0;
                                alert("拍卖已结束");
                                submit.value = "已结束拍卖";
                                clearInterval(interval);
                                bid();
                            }
                            days--;
                            hours=24;
                        }
                        hours--;
                        minutes=60;
                    }
                    minutes--;
                    seconds=59;
                }

            },1000);
        }

        //结束竞拍
        function bid(){
            alert("竞拍结束，返回首页！");
            location.href="/commodityAuction/EndBidServlet?"+"itemId="+itemId;
        }

    }
</script>



<%--<script type="text/javascript">--%>
<%--    window.onload=timer;--%>
<%--    function timer(){--%>
<%--        var date = document.getElementById("time");--%>
<%--        var submit = document.getElementById("submit");--%>
<%--        var button = document.getElementById("endprice");--%>
<%--        var second = "<%=request.getAttribute("time")%>";--%>
<%--        var time=second;--%>
<%--        if(time<=0){--%>
<%--            submit.value = "已结束拍卖";--%>
<%--            submit.disabled=true;--%>
<%--            button.disabled=true;--%>
<%--            date.innerHTML = "该物品已拍出";--%>
<%--        }else{--%>
<%--            var ss=time/1000;--%>
<%--            var seconds = parseInt(ss % 60);--%>
<%--            var minutes = parseInt((ss - seconds) / 60 % 60);--%>
<%--            var hours = parseInt(((ss-seconds)/60-minutes)/60%24);--%>
<%--            var days = parseInt((((ss-seconds)/60-minutes)/60-hours) / 24);--%>
<%--            var interval = setInterval(function(){--%>
<%--                time = days+"天"+hours+"时"+minutes +"分"+seconds+"秒";--%>
<%--                date.innerHTML = time;--%>
<%--                seconds--;--%>
<%--                if(seconds <=0){--%>
<%--                    if(minutes<=0){--%>
<%--                        if(hours<=0){--%>
<%--                            if(days<=0){--%>
<%--                                //结束--%>
<%--                                submit.disabled=true;--%>
<%--                                button.disabled=true;--%>
<%--                                days=0;hours=0;minute=0;seconds=0;--%>
<%--                                alert("拍卖已结束");--%>
<%--                                submit.value = "已结束拍卖";--%>
<%--                                clearInterval(interval);--%>
<%--                                bid();--%>
<%--                            }--%>
<%--                            days--;--%>
<%--                            hours=24;--%>
<%--                        }--%>
<%--                        hours--;--%>
<%--                        minutes=60;--%>
<%--                    }--%>
<%--                    minutes--;--%>
<%--                    seconds=59;--%>
<%--                }--%>
<%--            },1000);--%>
<%--        }--%>
<%--    }--%>
<%--</script>--%>


<%--<script>--%>
<%--/*--%>
<%--    //竞拍后，保存竞拍数据--%>
<%--    function bid(){--%>
<%--        var itemId=document.getElementById("itemId");--%>
<%--        var buyerId=document.getElementById("buyerId");--%>
<%--        var price=document.getElementById("max_price");--%>
<%--        $.ajax({--%>
<%--            url : "/commodityAuction/AddOrderServlet",//要访问的后台地址--%>
<%--            type : "get",						//使用get方法访问后台--%>
<%--            dataType : "json",							//返回json格式的数据--%>
<%--            data:{itemId:itemId,buyerId:buyerId,price:price},					//要发送的数据--%>
<%--            success : function(data) {					//data为返回的数据，在这里做数据绑定--%>
<%--                console.log(data.toString());--%>
<%--            }--%>
<%--        });--%>
<%--    }*/--%>
<%--</script>--%>


<%--<script>--%>
<%--    js时间戳转成日期格式--%>
<%--    //第三种 格式为：2010-10-20 10:00:00--%>
<%--    function getLocalTime(nS) {--%>
<%--        return new Date(parseInt(nS) * 1000).toLocaleString().replace(/年|月/g, "-").replace(/日/g, " ");--%>
<%--    }--%>
<%--    alert(getLocalTime(1177824835));--%>
<%--</script>--%>

</body>
</html>