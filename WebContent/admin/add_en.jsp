<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加企业</title>
</head>
<body>
	<h3>添加企业</h3>
	<form action="enAdd.action" method="post">
		<br>企业名: <input type="text" name="en.name" placeholder="企业名" value="" required="required"/>
		<br>企业家名:<input type="text" name="en.entrepreneurs" placeholder="企业家名" value="" required="required"/>
		<br>个人简介：<input type="textarea" name="en.personal" placeholder="个人简介" value="" required="required"/>
		<br>所在地：<input type="text" name="en.address" placeholder="所在地" value="" required="required"/>
		<br>资金规模：<input type="text" name="en.capital" placeholder="资金规模" value="" required="required"/>
		<br>企业简介：<input type="textarea" name="en.introduce" placeholder="企业简介" value="" required="required"/>
		<br>类型：
			<select name="en.type">
				<c:forEach var="type" items="${typeList}">
					<option value="${type.id}">${type.name}</option>
				</c:forEach>
			</select>
		<br>地区：
			<select name="en.province">
				<c:forEach var="province" items="${provinceList}">
					<option value="${province.id}">${province.name}</option>
				</c:forEach>
			</select>
		<input type="submit" value="添加"/>
		<a href="enListaction">返回</a>
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