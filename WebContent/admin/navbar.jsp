<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
</head>
<body>
	<!-- 导航条 -->
	<div class="navbar navbar-inverse">
		<div class="navbar-inner">
			<button type="button" class="btn btn-navbar visible-phone" id="menu-toggler">
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
			<a class="brand" href="#" style="font-weight:700;font-family:Microsoft Yahei">大学生创新服务网 - 后台管理</a>
			<ul class="nav pull-right">				
				<li class="hidden-phone">
					<input class="search" type="text" />
				</li>
				<li class="notification-dropdown hidden-phone">
					<a href="#" class="trigger">
						<i class="icon-envelope-alt"></i>
						<span class="count">6</span>
					</a>
					<div class="pop-dialog">
						<div class="pointer right">
							<div class="arrow"></div>
							<div class="arrow_border"></div>
						</div>
						<div class="body">
							<a href="#" class="close-icon"><i class="icon-remove-sign"></i></a>
							<div class="notifications">
								<h3>你有 6 个新通知</h3>
								<a href="#" class="item">
									<i class="icon-briefcase"></i>企业申请
									<span class="time"><i class="icon-time"></i> 13 分钟前.</span>
								</a>
								<a href="#" class="item">
									<i class="icon-book"></i>项目申请
									<span class="time"><i class="icon-time"></i> 18 分钟前.</span>
								</a>
								<a href="#" class="item">
									<i class="icon-book"></i> 项目申请
									<span class="time"><i class="icon-time"></i> 49 分钟前.</span>
								</a>
								<a href="#" class="item">
									<i class="icon-key"></i> 权限申请
									<span class="time"><i class="icon-time"></i> 1 天前.</span>
								</a>
								<div class="footer">
									<a href="#" class="logout">查看所有通知</a>
								</div>
							</div>
						</div>
					</div>
				</li>
				<li class="settings hidden-phone">
					<a href="amlogout.action" role="button">
						<i class="icon-signout"></i>
					</a>
				</li>
			</ul>
		</div>
	</div>
	<!-- 导航栏结束 -->
	
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