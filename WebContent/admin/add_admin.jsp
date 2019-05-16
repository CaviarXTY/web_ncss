<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加管理员</title>
</head>
<body>
	<h3>添加管理员</h3>
	<form action="adminAdd.action" method="post">
		用户名: <input type="text" name="admin.username" placeholder="用户名" value="" required="required"/><br>
		密码:<input type="text" name="admin.password" placeholder="密码" value="" required="required"/><br>
		姓名：<input type="text" name="admin.name" placeholder="姓名" value="" required="required"/><br>
		组织：<input type="text" name="admin.organization" placeholder="组织" value="" required="required"/><br>
		省份：
		<select name="admin.province">
			<c:forEach var="province" items="${provinceList}">
				<option value="${province.id}">${province.name}</option>
			</c:forEach>
		</select>
		权限：
		<select name="admin.permissions">
			<option value="1">AAM</option>
			<option value="2">EAM</option>
			<option value="3">IAM</option>
			<option value="4">PAM</option>
			<option value="0">NULL</option>
		</select>
		<input type="submit" value="添加"/>
		<a href="adminList.action">返回</a>
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