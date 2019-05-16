<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加企业家</title>
</head>
<body>
	<h3>添加企业家</h3>
	<form action="erAdd.action" method="post">
		用户名: <input type="text" name="er.username" placeholder="用户名" value="" required="required"/><br>
		密码:<input type="text" name="er.password" placeholder="密码" value="" required="required"/><br>
		姓名：<input type="text" name="er.name" placeholder="姓名" value="" required="required"/><br>
		企业id：<input type="text" name="er.entreprise" placeholder="企业" value="" required="required"/><br>
		邮箱：<input type="text" name="er.email" placeholder="邮箱" value="" required="required"/><br>
		<input type="submit" value="添加"/>
		<a href="erList.action">返回</a>
	</form>
	
	<!-- 提示框 -->
    <%
    	Object message = request.getAttribute("message");
    	if(message != null && !"".equals(message)){
    		//生成提示框
	%>
		<script type="text/javascript">
			alert("<%=message%>");
		</script>
  	<%	
  			session.removeAttribute("message");//销毁当前内容
  		} 	
  	%>
</body>
</html>