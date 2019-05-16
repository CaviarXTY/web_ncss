<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<title>管理员登录</title>
	<link rel="stylesheet" type="text/css" href="../index/css/login.css">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>

	<%@ include file="../index/header.jsp"%>
	
	<!-- 网页主体 -->
	<div class="wrapper">			

		<!-- 页面标题 -->
<!-- 		<div class="wrapper-title">
			<p><a href="index.action"><</a></p>
			<p>登录</p>
		</div> -->

		<!-- 登录页面 -->
		<div class="login-wrap" id="wrap">
			<div class="login-box">
				
				<!-- 用户登录 -->
				<div class="login">
					<div class="pad">
						<div class="value-box">
							<p>忘记密码请联系上级单位管理员</p>
						</div>

						<div class="box">
							<form action="amlogin.action" method="post">
								<p><span>账号:</span>
									<input type="text" name="admin.username" placeholder="账号" value="" required="required"/>
								</p>
								<p><span>密码:</span>
									<input type="password" name="admin.password" placeholder="密码" value="" required="required"/>
								</p>
								<p><button>登录</button></p>
							</form>
						</div>

						<div class="qrcode-box">
							<!-- <img src="./img/qrcode3.png"> -->
							<div class="download-qrcode-img"></div>
							<p>大创网APP下载</p>
						</div>

						<div class="title"><p>管理</p></div>
					</div>
				</div>

			</div>
		</div>
	</div>
	
	<%@ include file="../index/footer.jsp"%>
	
</body>
</html>