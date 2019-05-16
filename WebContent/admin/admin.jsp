<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>大学生创新服务网 - 后台管理</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<!-- bootstrap -->
<link href="./css/bootstrap/bootstrap.css" rel="stylesheet" />
<!-- 	<link href="css/bootstrap/bootstrap-responsive.css" rel="stylesheet" /> -->
<link href="./css/bootstrap/bootstrap-overrides.css" type="text/css" rel="stylesheet" />
<!-- global styles -->
<link rel="stylesheet" type="text/css" href="./css/layout.css" />
<link rel="stylesheet" type="text/css" href="./css/elements.css" />
<!-- 	<link rel="stylesheet" type="text/css" href="css/icons.css" /> -->
<!-- libraries -->
<link href="./css/lib/font-awesome.css" type="text/css" rel="stylesheet" />
<!-- this page specific styles -->
<!-- 	<link rel="stylesheet" href="css/compiled/user-list.css" type="text/css" media="screen" /> -->
<link rel="stylesheet" type="text/css" href="./css/com/admin.css" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>
<body>

	<%@ include file="navbar.jsp"%>
	
	<%@ include file="sidebar.jsp"%>
	
	<!-- 主要容器 -->
	<div class="content">
		<div class="container-fluid">
			<div id="pad-wrapper" class="nsp-c">
				<div class="row-fluid header">
					<div class="c-admin-photo"><img src="../file/photo/3.jpg"></div>
					<div class="c-admin-title">
						<h3>张三<p>校级管理员 | 中山大学南方学院</p></h3>
					</div>
				</div>
				<div class="row-fluid table">
					<table class="table table-hover">
						<thead>
							<tr>
								<th class="span4 sortable">
									公告
								</th>
								<th class="span1 sortable">
									<span class="line"></span>发布时间
								</th>
								<th class="span1 sortable align-right">
									<span class="line"></span>发布人
								</th>
							</tr>
						</thead>
						<tbody>
							<!-- row -->
							<tr>
								<td>
									<a href="user-profile.html" class="name">这是一个很随便的公告</a>
								</td>
								<td>
									2018 - 8 - 8
								</td>
								<td class="align-right">
									李四（省级管理员）
								</td>
							</tr>
							<!-- row -->
							<tr>
								<td>
									<a href="user-profile.html" class="name">这也是一个很随便的公告</a>
								</td>
								<td>
									2018 - 8 - 8
								</td>
								<td class="align-right">
									李四（省级管理员）
								</td>
							</tr>
							<!-- row -->
							<tr>
								<td>
									<a href="user-profile.html" class="name">这还是一个很随便的公告</a>
								</td>
								<td>
									2018 - 8 - 8
								</td>
								<td class="align-right">
									李四（省级管理员）
								</td>
							</tr>
							<!-- row -->
							<tr>
								<td>
									<a href="user-profile.html" class="name">这任然是一个很随便的公告</a>
								</td>
								<td>
									2018 - 8 - 8
								</td>
								<td class="align-right">
									李四（省级管理员）
								</td>
							</tr>
							<!-- row -->
							<tr>
								<td>
									<a href="user-profile.html" class="name">除了随便的公告就没了</a>
								</td>
								<td>
									2018 - 8 - 8
								</td>
								<td class="align-right">
									李四（省级管理员）
								</td>
							</tr>
							<!-- row -->
							<tr>
								<td>
									<a href="user-profile.html" class="name">最后一个很随便的公告了</a>
								</td>
								<td>
									2018 - 8 - 8
								</td>
								<td class="align-right">
									李四（省级管理员）
								</td>
							</tr>

						</tbody>
					</table>
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