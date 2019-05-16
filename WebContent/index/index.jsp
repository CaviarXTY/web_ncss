<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>全国大学生创业服务网</title>
	<link rel="stylesheet" type="text/css" href="./css/index.css">
	<script src="https://code.jquery.com/jquery-1.12.4.min.js" integrity="sha256-ZosEbRLbNQzLpnKIkEdrPv7lOy9C27hHQ+Xp8a4MxAQ=" crossorigin="anonymous"></script>
	<script src="./js/jquery-1.11.0.js"></script>
</head>
<body>

	<%@ include file="header.jsp"%>

	<!-- 网页主体 -->
	<div class="wrapper" id="wrapper">

		<div class="inf-wrap">
			<div class="img-wrap"></div>
			<a href="https://cloud.tencent.com/act/campus/match">立即领取</a>
		</div>

		<!-- 宣传首页 -->
		<div class="ad-wrap" id="wrap1">


			<div class="box">

				<div class="ad-title">
					<p>发现青年创变者</p>
				</div>
				
				<div class="ad">
					<c:forEach var="publicity" items="${publicityList}" begin="${adNum}">
					<a href="./information.jsp" id="ad1">
						<div class="box">
							<div class="photo">
								<img src="../${publicity.img}">
								<div class="title">
									<p>${publicity.title}</p>
									<p>${publicity.subtitle}</p>
								</div>
								<span>${publicity.date}</span>
							</div>
						</div>
					</a>
					</c:forEach>
					
					<a href="./information.jsp" id="ad5">
						<div class="box">
							<div class="photo">
								<img src="./img/5.png">
								<div class="title">
									<p>这是一个非常非常非常非常非常非常非常非常长的主标题</p>
									<p>这是短标题，日期我生日</p>
								</div>
								<span>2019-3-5</span>
							</div>
						</div>
					</a>
					
					<a href="./information.jsp" id="ad6">
						<div class="box">
							<div class="photo">
								<img src="./img/6.png">
								<div class="title">
									<p>这是一个非常非常非常非常非常非常非常非常长的主标题</p>
									<p>这是短标题，日期我生日</p>
								</div>
								<span>2019-3-5</span>
							</div>
						</div>
					</a>
					
				</div>
			</div>


 
		</div>

		<div class="joinbtn-wrap">
			<div class="joinbtn-box">
				<ul>
					<li class="join"><a href="#">报名参赛</a></li>
					<li><span></span></li>
					<li class="join"><a href="#">寻找融资</a></li>
					<li><span></span></li>
					<li class="join"><a href="#">寻找项目</a></li>
				</ul>
			</div>
		</div>

		<!-- 视频播放器 -->
		<div class="video-wrap" id="wrap2">


			<div class="box">
				<!-- 标题 -->
				<div class="title">
					<p>大赛宣传视频</p>
				</div>
				<!-- 视频盒子 -->
				<div class="video-box">
					<video id="video" src="./video/第四届创业竞赛宣传视频.mp4" controls="	controls">您的浏览器不支持 video 标签。</video>
				</div>
			</div>
			<canvas id="videoCanvas"></canvas>
		</div>

		<div class="timeline-wrap">
			<div class="timeline-box">
				<ul>
					<li><span></span></li>
					<li class="box a">报名启动</li>
					<li><span></span></li>
					<li class="box">
						<div class="point"><p>3-5月</p>参加报名</div></li>
					<li><span></span></li>
					<li class="box">
						<div class="point"><p>6月</p>高校初赛</div></li>
					<li><span></span></li>
					<li class="box">
						<div class="point"><p>7-9月</p>省市复赛</div></li>
					<li><span></span></li>
					<li class="box">
						<div class="point"><p>10月</p>全国总决赛</div></li>
					<li><span></span></li>
					<li class="box">
						<div class="point"><p>10月</p>颁奖典礼</div></li>
					<li><span></span></li>
					<li class="box a">大赛结束</li>
					<li><span></span></li>
				</ul>
			</div>
		</div>

		<!-- 上一届获奖名单 -->
		<div class="matchinf-wrap">
			
		</div>
	</div>

	<%@ include file="footer.jsp"%>

	<!-- ///////////////////Test///////////////////
	<form action="test.action" id="fff" method="post">
		<h1>用户登录:</h1>
		用户名:<input id="usern" type="text" name="name"/><br>
		密码:<input id="pass" type="password" name="pw"/><br>
		<span id="sp"></span><br>
		<input id="btn" type="button" value="登陆"/>
	</form> 
	<a href="test.action">test</a>
	//////////////////////////////////////////
	<br>-->
	
<%-- 
	<a href="register.action?flag=-1">注册</a>|
	<a href="../admin.jsp">管理入口</a>
--%>

	<!-- 轮播图 -->
<%-- 	<div>
		轮播图数：${adNum}
		<c:forEach var="publicity" items="${publicityList}" end="${adNum-1}">
			<p><a href="information.action?id=${publicity.information}">
			幻灯片位：${publicity.id-1} |
			urlId：${publicity.information} |
			宣传图：${publicity.img} |
			标题：${publicity.title} |
			</a></p>
		</c:forEach>
	</div>
	<!-- 动态宣传位 -->
	<h3>竞赛动态</h3>
	<div>
		<c:forEach var="publicity" items="${publicityList}" begin="${adNum}">
		<p><a href="information.action?informationId=${publicity.information}">
			广告位：${publicity.id} |
			id：${publicity.information} |
			宣传图：${publicity.img} |
			标题：${publicity.title} |
			副标题：${publicity.subtitle} |
			发布日期：${publicity.date}</a></p>
		</c:forEach>
	</div> --%>

	<script>

		function getScrollOffset(){
			if(window.pageOffset){
				return {
					x : window.pageXOffset,
					y : window.pageYOffset
				}
			}else{
				return {
					x : document.body.scrollLeft+
						document.documentElement.scrollLeft,
					y : document.body.scrollTop+ 
						document.documentElement.scrollTop
				}
			}
		}
		var hd = document.getElementById("header");
		hd.className += " nobg";
		var black = true;
		headerFun = function(){
			if(getScrollOffset().y>300 && !black){
				black = true;
				// hd.style.backgroundColor = "#2f435e";
				hd.className = "header";
			}
			if(getScrollOffset().y<300 && black){
				black = false;
				// hd.style.backgroundColor = "";
				hd.className += " nobg";
			}
		}

		//给页面绑定滑轮滚动事件 
		if (document.addEventListener) {//firefox
			document.addEventListener('DOMMouseScroll', headerFun, false); 
		}
		//滚动滑轮触发scrollFunc方法 //ie 谷歌
		window.onmousewheel = document.onmousewheel = headerFun;
    </script>

	<script>
		//改变canvas宽度
		//var width= window.innerWidth;//记录浏览器与canvas宽
		//console.log("w="+width);
		var canvas = document.getElementById("videoCanvas");
		var video = document.getElementById("video");
		var wrap = document.getElementById("wrap2");
		var ctx = canvas.getContext('2d');

		function getStyle(elem, prop){
			if(window.getComputedStyle(elem, null)){
				return window.getComputedStyle(elem, null)[prop];
			}else{
				return ele.currentStyle[prop];
			}
		}

		var width,height;//记录canvas大小
		var playw=1080,playh=608;//记录视频原尺寸
		var x,cw;//记录视频裁剪x起点,录裁剪宽
		~~function setCanvasSize(){//监听浏览器
			window.onresize = arguments.callee;//?
			//width = window.innerWidth;//浏览器宽
			width = parseInt(getStyle(wrap,"width"));
			height = parseInt(getStyle(wrap,"height"));
			canvas.width = width;//设置canva宽度
			canvas.height = height;//设置canva高度
		}();
		//每20毫秒画一次图
		video.addEventListener('play', function() {
			var i = window.setInterval(function() {
				ctx.drawImage(video, 0, 0,width,height);
				//打印当前视频的播放时间
				//console.log(video.currentTime);
				//当视频结束的时候去掉循环
				if(video.paused){
					ctx.clearRect(0, 0,width,height);
					wrap.className = "video-wrap";
				}else{
					wrap.className += " play";
				}
				if(video.ended){
					ctx.clearRect(0, 0,width,height);
					wrap.className = "video-wrap";
					clearIntervideoal(i)
				}
			}, 20);
		}, false);
		// //将视频暂停，然后观察canvoas里面的效果
		// setTimeout(function(){
		// 	video.pause();
		// },4000);
		// //将视频播放，然后观察canvas里面的效果
		// setTimeout(function(){
		// 	video.play();
		// },7000)
    </script>
	
	
</body>
</html>