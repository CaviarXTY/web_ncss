<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>创建项目</title>
<link rel="stylesheet" type="text/css" href="./css/register.css">
</head>
<body>

	<%@ include file="header.jsp"%>
	
		<!-- 网页主体 -->
	<div class="wrapper">
		<div class="register-wrap">

			<div class="title">
				<p>项目申请</p>
			</div>

			<div class="regist-box">
				<form action="newproject.action" method="post" enctype="multipart/form-data">
					<ul>
					<li class="file"><span>logo</span>:
						<input type="text" required="required" id="imgtext" disabled="true"/>
						<a href="javascript:;">选择文件<input id="img" type="file" name="img" required="required" accept="image/*" 
							onchange="imgbtn()"></a>
						<!-- <b>提示 : 提示提示提示提示提示</b> --></li>
					<li><span>项目名称</span>:
						<input type="text" name="project.name" required="required"/>
						<!-- <b>提示 : 提示提示提示提示提示</b> --></li>
					<li><span>所在地</span>:
						<select name="project.province">
							<c:forEach var="province" items="${provinceList}">
								<option value="${province.id}">${province.name}</option>
							</c:forEach>
						</select><!-- <b>提示 : 提示提示提示提示提示</b> --></li>
					<li><span>所属领域</span>:
						<select name="project.type">
							<c:forEach var="type" items="${typeList}">
								<option value="${type.id}">${type.name}</option>
							</c:forEach>
						</select><!-- <b>提示 : 提示提示提示提示提示</b> --></li>
					<li class="textarea"><span>项目概述</span>:
						<textarea name="project.presentation" required="required"/></textarea>
						<!-- <b>提示 : 提示提示提示提示提示</b> --></li>
					<li class="raido"><span>项目进展</span>:
						<label><input name="company" checked type="radio" value="0"/>创意计划阶段 </label> 
						<label><input name="company" type="radio" value="1" />已注册运营公司 </label></li>
					<li class="file"><span>计划书</span>:
						<input type="text" required="required" id="filetext" disabled="true"/>
						<a href="javascript:;">选择文件
						<input id="file" type="file" name="plan" required="required" 
							accept="application/vnd.openxmlformats-officedocument.wordprocessingml.document,application/msword,application/vnd.ms-works,application/pdf" onchange="filebtn()"></a>
						<!-- <b>提示 : 提示提示提示提示提示</b> --></li>
					<li class="raido"><span>项目进展</span>:
						<label><input name="project.ispublic" checked type="radio" value="保密"/>保密</label> 
						<label><input name="project.ispublic" type="radio" value="公开" />公开</label></li>
					<p class="agree"><input type="checkbox" name="agree" value="agree" checked="checked"/>我已阅读并同意“服务条款”</p>
					<button class="submit">提交申请</button>

				</form>
			</div>

		</div>
	</div>
	
	<%@ include file="footer.jsp"%>
	
	
	<script>
		var imginput = document.getElementById("img");
		var	fileinput = document.getElementById("file");
		var imgtext = document.getElementById("imgtext");
		var filetext = document.getElementById("filetext");
		function imgbtn(){
			imgtext.value = imginput.value;
		}
		function filebtn(){
			filetext.value = fileinput.value;
		}
	</script>
		
</body>
</html>