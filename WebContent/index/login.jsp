<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="./css/login.css">
	<title>登录</title>
</head>
<body>
	
	<%@ include file="header.jsp"%>
	
	<!-- 网页主体 -->
	<div class="wrapper">			

		<!-- 页面标题 -->
<!-- 		<div class="wrapper-title">
			<p><a href="index.action"><</a></p>
			<p>登录</p>
		</div> -->

		<!-- 登录页面 -->
		<div class="login-wrap" id="wrap"> 		<!-- flip-container -->

			<div class="login-box">			<!-- flipper -->
				
				<!-- 用户登录 -->
				<div class="login">				<!-- front		A -->

					<div class="pad">

						<div class="value-box">
							<p>大创网老用户请使用和原大创网账号（手机号）相同的学信账号进行登录，以便找回原大创网账号信息。</p>
						</div>

						<div class="box">
							<form action="userlogin.action" method="post">
								<p><span>手机号:</span>
									<input type="text" name="user.phone" placeholder="手机号"	value="" required="required"/>
								</p>
								<p><span>密码:</span>
									<input type="password" name="user.password" placeholder="密码" value="" required="required"/>
								</p>
								<p><button>登录</button></p>
							</form>
							<p class="findpw"><a href="findpw.action?flag=-1">找回密码</a>
								<span>&nbsp&nbsp|&nbsp&nbsp</span><a href="register.action?flag=-1">注册&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</a></p>
						</div>

						<div class="qrcode-box">
							<!-- <img src="./img/qrcode3.png"> -->
							<div class="download-qrcode-img"></div>
							<p>大创网APP下载</p>
						</div>

						<a href="javascript:changetype(true);">转到企业登录</a>
						<div class="title"><p>用户</p></div>

					</div>

				</div>

				<!-- 企业家登录 -->
				<div class="enlogin">			<!-- back		B -->

					<div class="pad">

						<div class="value-box">
							<p>大创网老用户请使用和原大创网账号，以便找回原大创网账号信息。申请企业用户请关注大创官方微信平台，联系管理员提交申请	</p>
						</div>

						<div class="box">
							<form action="enlogin.action" method="post">
								<p><span>账 号:</span>
									<input type="text" name="entrepreneur.username" placeholder="用户名" value="" required="required"/>
								</p>
								<p><span>密 码:</span>
									<input type="password" name="entrepreneur.password" placeholder="密码" value="" required="required"/>
								</p>
								<p><button>登录</button></p>
							</form>
							<p class="findpw"><a href="enfindpw.action?flag=-2">找回密码</a></p>
						</div>

						<div class="qrcode-box">
							<div class="download-qrcode-img"></div>
							<!-- <img src="./img/qrcode3.png"> -->
							<p>大创网APP下载</p>

						</div>

						<a href="javascript:changetype(false);">转到用户登录</a>
						<div class="title"><p>企业</p></div>

					</div>

				</div>

			</div>

		</div>

	</div>
	
	
	<%@ include file="footer.jsp"%>
<%-- 	<h2>登录</h2>
	${tip}
	<p>用户登录</p>
	<form action="userlogin.action" method="post">
		手机号码: <input type="text" name="user.phone" placeholder="手机号码" value="" required="required"/>  <br>
		密码:<input type="text" name="user.password" placeholder="密码" value="" required="required"/><br>
		<input type="submit" value="登录"/>
	</form>
	<p>企业家登录</p>
	<form action="enlogin.action" method="post">
		用户名: <input type="text" name="entrepreneur.username" placeholder="用户名" value="" required="required"/>  <br>
		密码:<input type="text" name="entrepreneur.password" placeholder="密码" value="" required="required"/><br>
		<input type="submit" value="登录"/>
	</form>
	<p><a href="findpw.action?flag=-1">找回密码</a></p>
	<p><a href="register.action?flag=-1">注册</a></p>
	<p><a href="index.action">返回</a></p> --%>
	
  	<script>
		var wrap = document.getElementById("wrap");
		function changetype(type){
			if (type) {
				type = false;
				wrap.className += " ch";
			}else{
				type = true;
				wrap.className = "login-wrap";
			}
		}
	</script>
</body>
</html>