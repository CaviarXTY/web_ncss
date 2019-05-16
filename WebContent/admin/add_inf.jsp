<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加动态</title>
</head>
<body>
	<h3>添加动态</h3>
	<form action="infAdd.action" method="post">
		<br>标题: <input type="text" name="inf.title" placeholder="企业名" value="" required="required"/>
		<br>发布日期:<input type="text" name="inf.date" placeholder="企业家名" value="" required="required"/>
		<br>文章内容：<input type="textarea" name="inf.content" placeholder="企业简介" value="" required="required"/>
		<br>省份：
			<select name="inf.province">
				<c:forEach var="province" items="${provinceList}">
					<option value="${province.id}">${province.name}</option>
				</c:forEach>
			</select>
		<input type="submit" value="添加"/>
		<a href="infList.action">返回</a>
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
