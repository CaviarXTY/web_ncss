<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>王小雨</title>
	<script src="https://code.jquery.com/jquery-1.12.4.min.js"
	integrity="sha256-ZosEbRLbNQzLpnKIkEdrPv7lOy9C27hHQ+Xp8a4MxAQ="
	crossorigin="anonymous"></script>
<style type="text/css">
*{
	margin: 0;
	padding: 0;
	color: #424242;
	list-style: none;/*列表样式*/
	text-decoration: none;/*文字装饰*/
	font-style:normal;/*字体样式*/
}
html,body{
	width: 100%;
	height: 100%;
	overflow-x: hidden; /*隐藏横向滚动条*/
	/*background: url(../picture/background.jpg)no-repeat fixed center top;*/
}
a{
	border-radius: 20px;
	display: inline-block;
	text-decoration: none;
	width: 400px;
	height: 100px;
	line-height: 100px;
	text-align: center;
	font-size: 40px;
	box-shadow: 10px 10px 10px #999;
	-moz-box-shadow: 10px 10px 10px #999;
}
.wrap{
	position: fixed;
	top:50%;
	margin-top: -380px;
	width: 100%;
	height: 660px;
}
h1{
	width: 100%;
	height: 400px;
	line-height: 400px;
	text-align: center;
	font-size: 50px;
}
.box{
	display: flex;
	width: 100%;
	justify-content: space-around;
	margin-top: 50px;
}
.green{
	background-color: #9f9;
	color: #444;
}
.red{
	background-color: #f99;
	color: #888;
}
.grey{
	background-color: #999;
}
.gf{
	color: #9f9;
	text-shadow: 2px 4px 2px #999;
	font-size: 60px;
}
.rf{
	color: #f99;
	text-shadow: 2px 4px 2px #999;
	font-size: 60px;
}
.display{
	display:none;
}
</style>
</head>
<body>
	<div class="wrap">
		<h1>王小雨是否承认许少爷最帅</h1>
		<div class="box">
			<a class="green" href="send.action" onclick="yes();return false;">承认 (有惊喜) </a>
			<a class="red" href="#" onclick="no()">不承认</a>
		</div>
	</div>	
<script>
	var h1 = $("h1");
	var num = 0;
	function yes(){
		h1.html("承认许少爷<b class='gf'>最帅</b>的王小雨小仙女<b class='rf'>最可爱</b>");
		$(".green").html("惊喜正在路上...");
		$(".red").html("").addClass("display");
		$.ajax({
			url:"send.action",
			type:"post",
				dataType:"json",//返回的类型必须为json
				data:null,//数据为序列化之后的。
				success:function(data){}})
	}
	function no(){
		if(num == 0){
			h1.html("皮痒了？再给你一次机会");
		}else if(num == 1){
			h1.html("第二次了，差不多够了啊");
		}else if(num == 2){
			h1.html("事不过三啊，再按我生气了");
		}else if(num == 3){
			h1.html("不让你按了，<b class='rf'>生气了!!</b>");
			$(".red").html("").addClass("grey");
			$.ajax({
				url:"badsend.action",
				type:"post",
					dataType:"json",//返回的类型必须为json
					data:null,//数据为序列化之后的。
					success:function(data){}})
		}
		num++;
	}
</script>
</body>
</html>