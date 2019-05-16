<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>投融资</title>
<link rel="stylesheet" type="text/css" href="./css/results.css">
</head>
<body>

	<%@ include file="header.jsp"%>
	
	<!-- 网页主体 -->
	<div class="wrapper">
	
		<!-- 标题 -->
		<div class="title-wrap">
			
			<!-- 标题 -->
			<c:if test="${reAction != 'searchPo'}">
				<b>寻找融资</b>
			</c:if>
			<c:if test="${reAction == 'searchPo'}">
				<b>投资</b>
			</c:if>


			<!-- 搜索框 -->
			<form action="${reAction}.action?flag=1" method="post">
				<input type="text" name="value" placeholder="请输入搜索名称" required="required"/>
				<button>搜索</button>
			</form>
			
		</div>

		<!-- 筛选列 -->
		<div class="search-wrap">

			<!-- 类型 -->
			<div class="box type">
				<form id="typef" action="${reAction}.action?flag=2" method="post">
					<input type="text" name="typeid" required="required"/>
				</form>
				<b>投资领域</b>
				<a class="all focus" href="${reAction}.action?flag=-1">不限</a>
				<ul>
					<c:forEach var="type" items="${typeList}">
						<li><a href="${reAction}.action?flag=2&typeid=${type.id}">${type.name}</a></li>
					</c:forEach>
				</ul>
			</div>


			<!-- 省份 -->
			<div class="box province">
				<form id="provf" action="${action}.action?flag=3" method="post">
					<input type="text" name="provinceid" required="required"/>
				</form>
				<b>投资领域</b>
				<a class="all focus" href="${reAction}.action?flag=-1">不限</a>
				<ul>
					<c:forEach var="province" items="${provinceList}">
						<li><a href="${reAction}.action?flag=3&provinceid=${province.id}">${province.name}</a></li>
					</c:forEach>
				</ul>
			</div>

		</div>

		<!-- 列表 -->
		<div class="table-wrap">
			<ul class="table">
			
				<!-- 项目 -->
				<c:if test="${projectList!=null}">
					<li id="th">
						<b>项目名</b><b>负责人</b><b>类型</b><b>所在地区</b><b>建立时间</b>
					</li>
					<c:forEach var="project" items="${projectList}">
						<a href="infPro.action?id=${project.id}"><li>
							<b><span><img src="../${project.logo}"></span>${project.name}</b>
							<b>${project.orgName}</b>
							<b>${project.typeName}</b>
							<b>${project.provinceName}</b>
							<b>${project.date}</b>
						</li></a>
					</c:forEach>
				</c:if>
				
				<!-- 企业 -->
				<c:if test="${enterpriseList!=null}">
					<li id="th" class="enlist">
						<b>投资人</b><b>企业</b><b>所在地区</b><b>投资领域</b><b>注册资金</b>
					</li>
					<c:forEach var="en" items="${enterpriseList}">
						<a href="infEnt.action?id=${en.id}"><li class="enlist">
							<b><span><img src="../${en.photo}"></span>${en.entrepreneurs}</b>
							<b>${en.name}</b>
							<b>${en.provinceName}</b>
							<b>${en.typeName}</b>
							<b>${en.capital}</b>
						</li></a>
					</c:forEach>
				</c:if>
				
			</ul>
			
			<!-- 页码 -->
			<%-- <div class="page">
				<ul>
					<li><a href="${reAction}.action?page=1&flag=${flag}" id="prev">上一页</a></li></li>
					<li><a href="${reAction}.action?page=1&flag=${flag}">1</a></li>
					<li><a href="${reAction}">2</a></li>
					<li><a href="${reAction}">3</a></li>
					<li><a href="${reAction}">4</a></li>
					<li><a href="${reAction}">5</a></li>
					<li><a href="${reAction}">6</a></li>
					<li><a href="${reAction}">7</a></li>
					<li><a href="${reAction}">8</a></li>
					<li><a href="" id="next">下一页</a></li>
					<li>到第<input type="text" name="page">页<a id="page-btn" href="">Go</a></li>
				</ul>
			</div> --%>
			${pageTool}
		</div>

	</div>
	
	<%@ include file="footer.jsp"%>
		
	
	<script>
		var typef = document.getElementById("typef");
		var provf = document.getElementById("provf");
		//var tinput = document.querySelector("");
	</script>
</body>
</html>