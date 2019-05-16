<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- <%@ page import="com.entity.User"%> --%>
<!DOCTYPE html>
<html>
<head>
<title>注册</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="./css/register.css">
<script src="https://code.jquery.com/jquery-1.12.4.min.js" integrity="sha256-ZosEbRLbNQzLpnKIkEdrPv7lOy9C27hHQ+Xp8a4MxAQ=" crossorigin="anonymous"></script>
</head>
<body>

	<%@ include file="header.jsp"%>
	

	<!-- 网页主体 -->
	<div class="wrapper">
		<div class="register-wrap">

			<div class="title">
				<p>用户注册</p>
			</div>

			<div class="regist-box">
				<form id="register" action="register.action" method="post">
					<ul>
					<li><span>国籍/地区</span>:
						<select name="user.region">
 							<option value ="中国大陆">中国大陆</option>
  							<option value ="香港">香港</option>
  							<option value="澳门">澳门</option>
  							<option value="台湾">台湾</option>
						</select>
					</li>
					
					<li><span>手机号</span>:
						<select name="user.prefix" class="phone">
 							<option value ="0086">中国大陆(0086)</option>
  							<option value ="00852">香港(00852)</option>
  							<option value="00853">澳门(00853)</option>
  							<option value="00856">台湾(00856)</option>
						</select>
						<input id="phone" type="text" name="user.phone" class="phone" required="required" onblur="checkPhone(this.id)" value="${user.phone}"/>
						<b></b>
					</li>

					<li><span>短信验证</span>:
					 	<input id="code" type="text" name="checkcode" required="required" onblur="checkCode(this.id)" /> 
						<b></b>
						<button id="getcode" onclick="sendcode();return false;">免费获取验证码</button>
					</li>

					<li><span>密码</span>:
						<input id="pw" type="password" name="user.password" required="required" onblur="checkPw(this.id)" />
						<b></b>
					</li>

					<li><span>确认密码</span>:
						<input id="repw" type="password" required="required" onblur="checkRePw('pw',this.id)" />
						<b></b>
					</li>

					<li><span>姓名</span>:
						<input id="name" type="text" name="user.name" required="required" onblur="checkName(this.id)" value="${user.name}"/>
						<b></b>
					</li>

					<li><span>证件类型</span>:
						<select name="user.caretype">
 							<option value ="居民身份证">居民身份证</option>
  							<option value ="香港身份证">香港身份证</option>
  							<option value="澳门身份证">澳门身份证</option>
  							<option value="台湾居民来往大陆通行证">台湾居民来往大陆通行证</option>
  							<option value="华侨身份证/护照号">华侨身份证/护照号</option>
  							<option value="其它">其它</option>
						</select><!-- <b>提示 : 提示提示提示提示提示</b> -->
					</li>

					<li>
						<span>证件号码</span>:
						<input id="card" type="text" name="user.care" required="required" onblur="checkCard(this.id)" value="${user.care}"/>
						<b></b>
					</li>
					
					<li>
						<span>安全邮箱</span>:
						<input id="email" type="text" name="user.email" required="required" onblur="checkEm(this.id)" value="${user.email}"/>
						<b></b>
					</li>

					</ul>

					<p class="agree">
						<input id="agree" type="checkbox" name="agree"/>我已阅读并同意“服务条款”
						<b id="ab"></b>
					</p>

					<button class="submit" onclick="checkForm();return false;">立即注册"</button>
					
					<p class="link"><a href="findpw.action?flag=-1">找回密码</a>|<a href="login.action">登录</a></p>
					
				</form>
			</div>

		</div>
	</div>
	
	
	<%@ include file="footer.jsp"%>
  	
  	<script type="text/javascript" src="./js/index/register.js"></script>
<%-- 	<h2>注册</h2>
	${tip}
	<p>用户注册</p>
	<!-- 用户注册表单 -->
	<form action="register.action" method="post">
		*国籍/地区：
		<select name="user.region">
 			<option value ="中国大陆">中国大陆</option>
  			<option value ="香港">香港</option>
  			<option value="澳门">澳门</option>
  			<option value="台湾">台湾</option>
		</select><br>
		*手机号：
		<select name="user.prefix">
 			<option value ="0086">中国大陆(0086)</option>
  			<option value ="00852">香港(00852)</option>
  			<option value="00853">澳门(00853)</option>
  			<option value="00856">台湾(00856)</option>
		</select>
		<input type="text" name="user.phone" required="required"/><br>
		*短信验证: <input type="text" name="check" required="required"/>
		<button>免费获取</button><br>
		*密码:<input type="text" name="user.password" required="required"/><br>
		*确认密码:<input type="text" name="check2" required="required"/><br>
		*姓名:<input type="text" name="user.name" required="required"/><br>
		*证件类型：		
		<select name="user.caretype">
 			<option value ="居民身份证">居民身份证</option>
  			<option value ="香港身份证">香港身份证</option>
  			<option value="澳门身份证">澳门身份证</option>
  			<option value="台湾居民来往大陆通行证">台湾居民来往大陆通行证</option>
  			<option value="华侨身份证/护照号">华侨身份证/护照号</option>
  			<option value="其它">其它</option>
		</select><br>
		*证件号码：<input type="text" name="user.care" required="required"/><br>
		*安全邮箱：<input type="text" name="user.email" required="required"/><br>
		<p><input type="checkbox" name="agree" value="agree" checked="checked" />我已阅读并同意“服务条款”</p><br>
		<input type="submit" value="立即注册"/>
	</form>
	<p><a href="findpw.action?flag=-1">找回密码</a></p>
	<p><a href="login.action">登录</a></p>
	<p><a href="index.action">返回</a></p> --%>
	
</body>
</html>