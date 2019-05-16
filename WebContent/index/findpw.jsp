<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>找回密码</title>
<link rel="stylesheet" type="text/css" href="./css/findpw.css">
</head>
<body>
	<%@ include file="header.jsp"%>
	
	<!-- 网页主体 -->
	<div class="wrapper">

		<div class="inputpad">
	
		<!-- 用户 -->
		<div>
			<!-- 输入手机号 -->
			<c:if test="${flag==0}">
			<div class="first">
				<div class="tip">
					<p>请登录的输入手机号</p>
				</div>
				<form action="findpw.action?flag=1" method="post">
					<p>手机号：<input type="text" name="user.phone" required="required"/>
					<button>提交</button></p>
				</form>
			</div>
			</c:if>
			
		<!-- 选择验证方式 -->
		<c:if test="${flag==1}">
			<div class="second">
				<div class="tip">
					<p>请选择验证方式</p>
				</div>
				<div class="link">
					<a href="findpw.action?flag=2&user.phone=${user.phone}">短信验证</a>
					<span></span>
					<a href="findpw.action?flag=3&user.phone=${user.phone}">邮箱验证</a>
				</div>
			</div>
		</c:if>
		
		<!-- 输入手机验证码 -->
		<c:if test="${flag==2}">
			<div class="third first">
				<div class="tip">
					<p>请输入手机收到的验证码</p>
				</div>
				<form action="findpw.action?flag=4" method="post">
					<input type="hidden" name="user.phone" value="${user.phone}"/>
					<p>验证码：<input type="text" name="checkcode" required="required"/>
					<button>提交</button></p>
				</form>
			</div>
			
		</c:if>
		
		<!-- 输入邮箱验证码 -->
		<c:if test="${flag==3}">
			<div class="third first">
				<div class="tip">
					<p>请输入邮箱收到的验证码</p>
				</div>
				<form action="findpw.action?flag=5" method="post">
					<input type="hidden" name="user.phone" value="${user.phone}"/>
					<p>验证码：<input type="text" name="checkcode" required="required"/>
					<button>提交</button></p>
				</form>
			</div>
		</c:if>
		
		<!-- 重设密码 -->
		<c:if test="${flag==4 || flag==5}">
			<div class="fourth">
				<div class="tip">
					<p>请输入新的密码</p>
				</div>
				<form action="findpw.action?flag=123" method="post">
					<input type="hidden" name="user.phone" value="${user.phone}"/>
					<input type="hidden" name="checkcode" value="${checkcode}"/>
					<p><span>新密码</span>:<input type="password" name="user.password" required="required"/></p>
					<p><span>确认密码</span>:<input type="password" name="check" required="required"/></p>
					<button>提交</button>
				</form>
			</div>
		</c:if>
		</div>
	
	
	
	<!-- 企业家 -->
	<div>
		<!-- 输入账号 -->
		<c:if test="${flag==8}">
			<div class="first">
				<div class="tip">
					<p>请输入登录的账号</p>
				</div>
				<form action="enfindpw.action?flag=6" method="post">
					<p>用户名：<input type="text" name="entrepreneur.username" required="required"/>
					<button>提交</button></p>
				</form>
			</div>
		</c:if>
		<!-- 输入邮箱验证码 -->
		<c:if test="${flag==6}">
			<div class="third first">
				<div class="tip">
					<p>请输入邮箱收到的验证码</p>
				</div>
				<form action="enfindpw.action?flag=7" method="post">
					<input type="hidden" name="entrepreneur.username" value="${entrepreneur.username}"/>
					<p>验证码：<input type="text" name="checkcode" required="required"/>
					<button>提交</button></p>
				</form>
			</div>
		</c:if>
		
		<!-- 重设密码 -->
		<c:if test="${flag==7}">
			<div class="fourth">
				<div class="tip">
					<p>请输入新的密码</p>
				</div>
				<form action="enfindpw.action?flag=456" method="post">
					<input type="hidden" name="entrepreneur.username" value="${entrepreneur.username}"/>
					<input type="hidden" name="checkcode" value="${checkcode}"/>
					<p><span>新密码</span>:<input type="password" name="entrepreneur.password" required="required"/></p>
					<p><span>确认密码</span>:<input type="password" name="check" required="required"/></p>
					<button>提交</button>
				</form>
			</div>
		</c:if>
		</div>
	
	
	
		</div>
	</div>
	
	<%@ include file="footer.jsp"%>
	
</body>
</html>