<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="../index/css/header.css">
</head>
<body>
	<!-- 标题栏 -->
	<div class="header" id="header">
		<!-- 导航栏 -->
		<div class="top-nav">
			<!-- 导航项目 -->
			<div class="nav-box">
				<!-- logo -->
				<div class="logo">
					<a href="../index.jsp" title="logo">
						<img src="../index/img/logo.png"></img>
						<div class="title">
							<h2>全国大学生创业服务网</h2>
							<p>cy.ncss.cn</p>
						</div>
					</a>
				</div>
				<!-- 标签 -->
				<div class="nav-list">
					<ul class="list">
						<li class="focus"><a href="../index.jsp">首页</a></li>
						<!-- 下拉菜单 -->
						<li class="menu-down"><a href="#" >投融资</a>	
							<ul class="nav-menu">
								<li><a href="searchEn.action?flag=-1">寻找投资</a></li>
								<li><a href="searchPo.action?flag=-1">寻找项目</a></li>
							</ul>
						</li>
						<li><a href="https://cy.ncss.cn/ap/index">创业孵化</a></li>
						<li><a href="https://cy.ncss.cn/redactivity/index">筑梦之旅</a></li>
						<!-- 二维码 -->
						<li class="qrcode-down">	
							<div class="qrcode">
								<img src="../index/img/qrcode2.png">
								<p>大赛官方微信号</p>
								<span>二维码</span>
							</div>
						</li>
					</ul>
				</div>
				<div class="nav-login">
					<!-- 用户入口 -->
					<c:if test="${sessionScope.user==null && sessionScope.entrepreneur==null}">
						<a class="login" href="login.action">登录</a>
					</c:if>
					<!-- 用户 -->
					<div class="user">
						
						<!-- 用户姓名 -->
						<c:if test="${sessionScope.user!=null && sessionScope.entrepreneur==null}">
							<img src="../${sessionScope.user.photo}"><!--${photo}-->
							<a href="user.action">${sessionScope.user.name}</a>
							<em>|</em>
							<a href="userlogout.action">退出</a>
						</c:if>
						
						<!-- 企业家姓名 -->
						<c:if test="${sessionScope.entrepreneur!=null && sessionScope.user==null}">
							<img src="../${sessionScope.entrepreneur.photo}"><!--${photo}-->
							<a href="entrepreneur.action">${sessionScope.entrepreneur.name}</a>
							<em>|</em>
							<a href="enlogout.action">退出</a>
						</c:if>
					</div>
					
				</div>
			</div>				
		</div>
	</div>
	
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