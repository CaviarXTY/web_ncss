<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>大学生创新服务网 - 后台管理</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<!-- bootstrap -->
	<link href="css/bootstrap/bootstrap.css" rel="stylesheet" />
	<link href="css/bootstrap/bootstrap-responsive.css" rel="stylesheet" />
	<link href="css/bootstrap/bootstrap-overrides.css" type="text/css" rel="stylesheet" />
	<!-- global styles -->
	<link rel="stylesheet" type="text/css" href="css/layout.css" />
	<link rel="stylesheet" type="text/css" href="css/elements.css" />
	<link rel="stylesheet" type="text/css" href="css/icons.css" />
	<!-- libraries -->
	<link href="css/lib/font-awesome.css" type="text/css" rel="stylesheet" />
	<!-- this page specific styles -->
	<link rel="stylesheet" href="css/compiled/user-list.css" type="text/css" media="screen" />
</head>
<body>
	
	<%@ include file="navbar.jsp"%>
	
	<%@ include file="sidebar.jsp"%>
	
	<!-- 主要容器 -->
	<div class="content">
		<div class="container-fluid">
			<div id="pad-wrapper" class="users-list">
			
				<!-- 操作栏 -->
				<div class="row-fluid header">
					<h3>用户</h3>
					<div class="span10 pull-right">
						<input type="text" class="span5 search" placeholder="输入查找的姓名或手机号" />
						
						<a href="new-user.html" class="btn-flat success pull-right"><span>&#43;</span>添加用户</a>
					</div>
				</div>

				<!-- 列表栏 -->
				<div class="row-fluid table">
					<table class="table table-hover">
						

						<!-- 管理员 -->
						<c:if test="${adminList!=null && flag==1}">
							<thead>
								<tr>
									<th class="span4 sortable">管理员</th>
									<th class="span3 sortable"><span class="line"></span>省份</th>
									<th class="span2 sortable"><span class="line"></span>权限等级</th>
									<th class="span3 sortable align-right"><span class="line"></span>操作</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="admin" items="${adminList}">
								<tr>
									<td><img src="../${admin.photo}" class="img-circle avatar hidden-phone" />
										<a href="user-profile.html" class="name">${admin.name}</a>
										<span class="subtext">${admin.organization}</span>
									</td>
									<td>${admin.provinceName}</td>
									<td>${admin.permissions}</td>
									<td class="align-right"><a href="#">详情</a> | <a href="adminDel.action?admin.id=${admin.id}">删除</a></td>
								</tr>
								</c:forEach>
							</tbody>
						</c:if>
							
						<!-- 企业 -->
						<c:if test="${enList!=null && flag==2}">
							<thead>
								<tr>
									<th class="span4 sortable">企业名称</th>
									<th class="span3 sortable"><span class="line"></span>省份</th>
									<th class="span2 sortable"><span class="line"></span>企业家</th>
									<th class="span3 sortable align-right"><span class="line"></span>操作</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="en" items="${enList}">
								<tr>
									<td><img src="../${en.photo}" class="img-circle avatar hidden-phone" />
										<a href="user-profile.html" class="name">${en.name}</a>
										<span class="subtext">${en.address}</span>
									</td>
									<td>${en.provinceName}</td>
									<td>${en.entrepreneurs}</td>
									<td class="align-right"><a href="#">详情</a> | <a href="enDel.action?en.id=${en.id}">删除</a></td>
								</tr>
								</c:forEach>
							</tbody>
						</c:if>
							
						<!-- 企业家 -->
						<c:if test="${erList!=null && flag==3}">
							<thead>
								<tr>
									<th class="span4 sortable">企业家</th>
									<th class="span3 sortable"><span class="line"></span>省份</th>
									<th class="span2 sortable"><span class="line"></span>邮箱</th>
									<th class="span3 sortable align-right"><span class="line"></span>操作</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="er" items="${erList}">
								<tr>
									<td><img src="../${er.photo}" class="img-circle avatar hidden-phone" />
										<a href="user-profile.html" class="name">${er.name}</a>
										<span class="subtext">${er.email}</span>
									</td>
									<td>${er.entreprise}</td>
									<td>${er.email}</td>
									<td class="align-right"><a href="#">详情</a> | <a href="erDel.action?er.id=${er.id}">删除</a></td>
								</tr>
								</c:forEach>
							</tbody>
						</c:if>
							
						<!-- 动态文章 -->
						<c:if test="${infList!=null && flag==4}">
							<thead>
								<tr>
									<th class="span4 sortable">标题</th>
									<th class="span3 sortable"><span class="line"></span>省份</th>
									<th class="span2 sortable"><span class="line"></span>日期</th>
									<th class="span3 sortable align-right"><span class="line"></span>操作</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="inf" items="${infList}">
								<tr>
									<td><a href="user-profile.html" class="name">${inf.title}</a></td>
									<td>${inf.provinceName}</td>
									<td>${inf.date}</td>
									<td class="align-right"><a href="#">详情</a> | <a href="infDel.action?inf.id=${inf.id}">删除</a></td>
								</tr>
								</c:forEach>
							</tbody>
						</c:if>
							
						<!-- 宣传栏 -->
						<c:if test="${pubList!=null && flag==5}">
							<thead>
								<tr>
									<th class="span4 sortable">宣传栏号</th>
									<th class="span3 sortable"><span class="line"></span>标题</th>
									<th class="span2 sortable"><span class="line"></span>日期</th>
									<th class="span3 sortable align-right"><span class="line"></span>操作</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="pub" items="${pubList}">
								<tr>
									<td><a href="#" class="name">1</a></td>
									<td>${pub.title}</td>
									<td>${pub.data}</td>
									<td class="align-right"><a href="#">详情</a></td>
								</tr>
								</c:forEach>
							</tbody>
						</c:if>
							
						<!-- 项目 -->
						<c:if test="${proList!=null && flag==6}">
							<thead>
								<tr>
									<th class="span4 sortable">项目名</th>
									<th class="span3 sortable"><span class="line"></span>类型</th>
									<th class="span2 sortable"><span class="line"></span>省份</th>
									<th class="span3 sortable align-right"><span class="line"></span>操作</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="pro" items="${proList}">
								<tr>
									<td><img src="../${pro.logo}" class="img-circle avatar hidden-phone" />
										<a href="#" class="name">${pro.name}</a>
										<span class="subtext">${pro.orginators}</span>
									</td>
									<td>${pro.typeName}</td>
									<td>${pro.provinceName}</td>
									<td class="align-right"><a href="#">详情</a> | <a href="proDel.action?pro.id=${pro.id}">删除</a></td>
								</tr>
								</c:forEach>
							</tbody>
						</c:if>
							
						<!-- 用户 -->
						<c:if test="${userList!=null && flag==7}">
							<thead>
								<tr>
									<th class="span4 sortable">姓名</th>
									<th class="span3 sortable"><span class="line"></span>联系方式</th>
									<th class="span2 sortable"><span class="line"></span>邮箱</th>
									<th class="span3 sortable align-right"><span class="line"></span>操作</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="user" items="${userList}">
								<tr>
									<td><img src="../${user.photo}" class="img-circle avatar hidden-phone" />
										<a href="user-profile.html" class="name">${user.name}</a>
										<span class="subtext">${user.school}</span>
									</td>
									<td>${user.phone}</td>
									<td>${user.email}</td>
									<td class="align-right"><a href="#">详情</a> | <a href="userDel.action?user.id=${user.id}">删除</a></td>
								</tr>
								</c:forEach>
							</tbody>
						</c:if>
													
					</table>
				</div>
				<!-- 列表栏  结束 -->
				
				<!-- 页码 -->
				<div class="pagination pull-right">
					<ul>
						<li><a href="#">&#8249;</a></li>
						<li><a class="active" href="#">1</a></li>
						<li><a href="#">2</a></li>
						<li><a href="#">3</a></li>
						<li><a href="#">4</a></li>
						<li><a href="#">5</a></li>
						<li><a href="#">&#8250;</a></li>
					</ul>
				</div>
				
			</div>
		</div>
	</div>
	<!-- 主要容器 结束 -->
	
	<!-- scripts -->
	<script src="js/jquery-latest.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/theme.js"></script>
	
</body>
</html>