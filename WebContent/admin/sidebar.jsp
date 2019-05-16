<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
</head>
<body>
	<!-- 侧边栏 -->
	<div id="sidebar-nav">
		<ul id="dashboard-menu">
			<li class="active">
				<div class="pointer">
					<div class="arrow"></div>
					<div class="arrow_border"></div>
				</div>
				<a href="#">
					<i class="icon-home"></i>
					<span>后台首页</span>
				</a>
			</li>
			<li>
				<a class="dropdown-toggle" href="#">
					<i class="icon-book"></i>
						<span>项目管理</span>
					<i class="icon-chevron-down"></i>
				</a>
				<ul class="submenu">
					<li><a href="proList.action">项目列表</a></li>
					<!-- <li><a href="proAdd.action?flag=-1">加入新项目</a></li> -->
					<li><a href="#">项目信息</a></li>
				</ul>
			</li>
			<li>
				<a class="dropdown-toggle" href="userList.action">
					<i class="icon-group"></i>
						<span>用户管理</span>
					<i class="icon-chevron-down"></i>
				</a>
				<ul class="submenu">
					<li><a href="userList.action">用户列表</a></li>
					<!-- <li><a href="userAdd.action?flag=-1">加入新用户</a></li> -->
					<li><a href="#">用户信息</a></li>
				</ul>
			</li>
			<li>
				<a class="dropdown-toggle" href="erList.action">
					<i class="icon-briefcase"></i>
						<span>企业管理</span>
					<i class="icon-chevron-down"></i>
				</a>
				<ul class="submenu">
					<li><a href="erList.action">企业家列表</a></li>
					<li><a href="erAdd.action?flag=-1">企业家添加</a></li>
					<li><a href="#">企业家信息</a></li>
					<li><a href="enList.action">企业列表</a></li>
					<li><a href="enAdd.action?flag=-1">企业添加</a></li>
					<li><a href="#">企业信息</a></li>
				</ul>
			</li>
			<li>
				<a class="dropdown-toggle" href="#">
					<i class="icon-key"></i>
					<span>管理员</span>
					<i class="icon-chevron-down"></i>
				</a>
				<ul class="submenu">
					<li><a href="adminList.action">管理员列表</a></li>
					<li><a href="adminAdd.action?flag=-1">管理员添加</a></li>
					<li><a href="#">管理员信息</a></li>
				</ul>
			</li>
			<li>
				<a href="pubList.action">
					<i class="icon-picture"></i>
					<span>宣传栏</span>
				</a>
			</li>
			<li>
				<a class="dropdown-toggle" href="#">
					<i class="icon-edit"></i>
						<span>动态管理</span>
					<i class="icon-chevron-down"></i>
				</a>
				<ul class="submenu">
					<li><a href="infList.action">动态列表</a></li>
					<li><a href="infAdd.action?flag=-1">发布动态</a></li>
				</ul>
			</li>
			<li>
				<a href="#">
					<i class="icon-cog"></i>
					<span>我的信息</span>
				</a>
			</li>
		</ul>
	</div>
	<!-- 侧边栏 结束 -->
</body>
</html>