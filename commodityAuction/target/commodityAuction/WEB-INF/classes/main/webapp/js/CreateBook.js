
$(function() {
	$.ajax({
		url : "/commodityAuction/CommodityServlet",
		type : "post",
		dataType : "json",
		data:{type:"book"},
		success : function(data) {
			console.log(data.toString());
			var count=1;
			$.each(data, function(i) {
				count++;
				var trString = "";
				var date=new Date(data[i].addTime);
				//trString ="<a ></a>";
				trString =
					"<a"+" name=a"+">"+
					"<div>" +
					"</div>"+
					"<img"+" src="+"imges/"+data[i].img+">"+
					"<p>"+"介绍："+data[i].info+
					"</p>"+
					"<p"+" id="+i+" style.position=absolute"+" style.bottom=2px"+">"+"截止日期："+date.toLocaleDateString()+
					"</p>"+
					"</a>";

				$("#watchBox2").append(trString);

			});
		}
	});
});

