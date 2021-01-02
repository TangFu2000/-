
$(function() {
	$.ajax({
		url : "/commodityAuction/CommodityServlet",//要访问的后台地址
		type : "post",								//使用post方法访问后台
		dataType : "json",							//返回json格式的数据
		data:{type:"book"},							//要发送的数据
		success : function(data) {				//data为返回的数据，在这里做数据绑定
			console.log(data.toString());
			var count=1;
			$.each(data, function(i) {
				console.log("data[i].endTime"+data[i].endTime);
				var time=parseInt(data[i].endTime)-new Date().getTime();
				console.log("time"+data[i].endTime);
				if (time<=0||data[i].state==2) {
					return true;
				}
				if (count==5) {
					return false;
				}
				count++;
				var trString = "";
				var date=new Date(data[i].addTime);
				trString =
					"<a"+" name=a"+" href="+"/commodityAuction/auction.jsp?itemId="+data[i].itemId+">"+
						"<div>" +
						"</div>"+
						"<img"+" src="+"imges/"+data[i].img+">"+
						"<p>"+"介绍："+data[i].info+
						"</p>"+
						"<p"+" id="+i+" style.position=absolute"+" style.bottom=2px"+">"+"起拍日期："+date.toLocaleDateString()+
						"</p>"+
					"</a>";
				$("#bookBox").append(trString);

			});
		}
	});
});


$(function() {
	$.ajax({
		url : "/commodityAuction/CommodityServlet",//要访问的后台地址
		type : "post",								//使用post方法访问后台
		dataType : "json",							//返回json格式的数据
		data:{type:"stamp"},							//要发送的数据
		success : function(data) {				//data为返回的数据，在这里做数据绑定
			console.log(data.toString());
			var count=1;
			$.each(data, function(i) {
				console.log("data[i].endTime"+data[i].endTime);
				var time=parseInt(data[i].endTime)-new Date().getTime();
				console.log("time"+data[i].endTime);
				if (time<=0||data[i].state==2) {
					return true;
				}
				if (count==5) {
					return false;
				}
				count++;
				var trString = "";
				var date=new Date(data[i].addTime);
				trString =
					"<a"+" name=a"+" href="+"/commodityAuction/auction.jsp?itemId="+data[i].itemId+">"+
					"<div>" +
					"</div>"+
					"<img"+" src="+"imges/"+data[i].img+">"+
					"<p>"+"介绍："+data[i].info+
					"</p>"+
					"<p"+" id="+i+" style.position=absolute"+" style.bottom=2px"+">"+"起拍日期："+date.toLocaleDateString()+
					"</p>"+
					"</a>";
				$("#stampBox").append(trString);
			});
		}
	});
});

