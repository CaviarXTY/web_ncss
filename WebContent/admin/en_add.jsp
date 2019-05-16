<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加企业家</title>
</head>
<body>
	添加企业家
	<form action="enSave.action" method="post">
		用户名: <input type="text" name="entrepreneur.username" placeholder="用户名" value="" required="required"/><br>
		密码:<input type="text" name="entrepreneur.password" placeholder="密码" value="" required="required"/><br>
		姓名：<input type="text" name="entrepreneur.name" placeholder="姓名" value="" required="required"/><br>
		企业：<input type="text" name="entrepreneur.entreprise" placeholder="企业" value="" required="required"/><br>
		<input type="submit" value="登录"/>
	</form>
</body>
</html>