<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>个人主页</title>
	<link rel="stylesheet" type="text/css" href="../index/css/user.css">
	<script src="./js/jquery-1.11.0.js"></script>
</head>
<body>
	
	<%@ include file="header.jsp"%>
	
	<!-- 网页主体 -->
	<div class="wrapper">

		<!-- 个人信息 -->
		<div class="inf-wrap">
			<div class="wrap">
				<form id="imgf" class="hidden" action="userimg.action" method="post" enctype="multipart/form-data">
					<input id="imginput" type="file" name="img" required="required" accept="image/x-png,image/gif,image/jpeg,image/bmp"
					  onchange="document.getElementById('imgf').submit()">
				</form>
				<div class="img-box"><img src="../${user.photo}" onclick="document.getElementById('imginput').click()"></div>
				<div class="inf-box">
					<p class="name"><b>${user.name}</b>学籍认证通过</p>
					<p>手	 机：${user.phone}</p>
					<p>邮	 箱：${user.email}</p>
				</div>
			</div>
		</div>
		
		<!-- 选项卡目录 -->
		<div class="tab-herder-wrap wrap">
			<ul>
				<a id="pro_btn" href="project.action"><li>我的项目</li></a>
				<a id="news_btn" href="news.action"><li>我的消息</li></a>
				<a id="set_btn" href="set.action"><li>个人设置</li></a>
			</ul>
		</div>
		
		<!-- 选项卡 -->
		<div class="tab-wrap wrap">

			<!-- 项目选项卡 -->
			<c:if test="${flag == 0}">
			<div class="project-wrap">

				<!-- 创建项目 -->
				<c:if test="${project == null}">
				<div class="newpro-wrap">
					<a id="newpro" href="newproject.action?flag=-1">添加项目</a>
				</div>
				</c:if>
						
				<!-- 现有项目 -->
				<c:if test="${project != null}">
				<div class="pro-wrap">
					<div class="proinf-wrap">
						<a href="" id="project"><div class="pro-box">
							<div class="pro-logo"><img src="../${project.logo}">项目logo</div>
							<p class="pro-name">${project.name}</p>
							<p class="pro-org">项目创始人：${orgName}</p>
							<p class="pro-date">${project.date}</p>
						</div></a>
					</div>

					<div class="members-wrap pro">
						<p class="title"><b>团队成员</b><a href="javascript:showPad('members');">添加</a></p>
						<ul>
							<c:if test="${memList != null}">
							<c:forEach var="mem" items="${memList}">
							<%-- <li class="have"><p>项目创始人</p><p>${mem}</p><p>中山大学南方学院</p></li> --%>
							<li class="have"><p>团队成员</p><p>${mem.name}</p><p>${mem.school}</p><a class="del" href="">×</a></li>
							</c:forEach>
								<c:if test="${memList.size()<5}">
									<li><b>待添加</b><a class="add" href="javascript:showPad('members');">+</a></li>
								</c:if>
							</c:if>
							<c:if test="${memList == null}">
								<li><b>待添加</b><a class="add" href="javascript:showPad('members');">+</a></li>
							</c:if>
							
						</ul>
					</div>

					<div class="teacher-wrap pro">
						<p class="title"><b>指导老师</b><a href="javascript:showPad('teacher');">添加</a></p>
						<ul>
							<li class="have"><p>姓名</p><p>职称：助理教授</p><p>中山大学南方学院</p><a class="del" href="">×</a></li>
							<li><b>可添加</b><a class="add" href="">+</a></li>
						</ul>
					</div>

					<div class="patent-wrap pro">
						<p class="title"><b>已获专利</b><a href="javascript:showPad('patent');">添加</a></p>
						<ul>
							<li class="have"><p>专利名</p><p>实用新型专利</p><a class="del" href="">×</a></li>
							<li><b>可添加</b><a class="add" href="">+</a></li>
						</ul>
					</div>
				</div>
				</c:if>
				
			</div>
			</c:if>

			<!-- 信息选项卡 -->
			<c:if test="${flag == 1}">
			<div class="news-wrap">
				<div class="news-box">
					<div class="news-nav">
						<!-- 装饰栏 -->
					</div>
					
					<c:if test="${sessionList != null}">
					
					<div class="list">
						<ul>
							<c:forEach var="se" items="${sessionList}">
							<li>
								<p class="name">${se.userName}</p>
								<p class="value">${se.value}</p>
								<p class="data">${se.date}</p>
								<c:if test="${se.isread}">
									<p>有消息</p>
								</c:if>
							</li>
							</c:forEach>
						</ul>
						
					</div>
					<c:forEach var="se" items="${sessionList}">
							<div class="value-wrap">
								<p class="value-title">${se.userName}</p>
								<c:forEach var="news" items="${se.newsList}">
								<ul>
									<c:if test="${news.type < 2}">
										<li class="send"><p>${news.date}</p><p>${news.value}</p></li>
									</c:if>
									<c:if test="${news.type == 2}">
										<li class="get"><p>${news.date}</p><p>${news.value}</p></li>
									</c:if>
								</ul>
								</c:forEach>
							</div>
					</c:forEach>
					
					</c:if>
				</div>
			</div>
			</c:if>
			
			<!-- 个人设置选项卡 -->
			<c:if test="${flag == 2}">
			<div class="set-wrap">
				<div class="show-box">
					<p>登录账号：<b>${user.phone}</b><a href="javascript:showPad('repw');">修改密码-></a></p>
					<p>真实姓名：<b>${user.name}</b></p>
					<p>证件类型：<b>${user.caretype}</b></p>
					<p>证件号码：<b>${user.care}</b></p>
					<p>电子邮箱：<b>${user.email}</b><a href="javascript:showPad('reemail');">修改邮箱-></a></p>	
				</div>
				<div class="set-box">
					<div id="set-pad" class="write  hidden">
						<!-- 修改信息 -->
						<form action="userReset.action" method="post">
							<p><b>学历</b>：<input type="text" name="user.education" value="${user.education}"/></p>
							<p><b>所在院校</b>：<input type="text" name="user.school" value="${user.school}"/></p>
							<p><b>入学时间</b>：<input type="text" name="user.admission" value="${user.admission}"/></p>
							<p><b>毕业时间</b>：<input type="text" name="user.graduation" value="${user.graduation}"/></p>
							<p><b>专业名称</b>：<input type="text" name="user.specialty" value="${user.specialty}"/></p>
							<button>保存</button>
							<a href="javascript:closePad('setpad');">取消</a>
						</form>
					</div>
					<div class="read">
						<p><a href="javascript:showPad('setpad');">修改信息</a></p>
						<p><b>学历</b>：<span>${user.education}</span></p>
						<p><b>所在院校</b>：<span>${user.school}</span></p>
						<p><b>入学时间</b>：<span>${user.admission}</span></p>
						<p><b>毕业时间</b>：<span>${user.graduation}</span></p>
						<p><b>专业名称</b>：<span>${user.specialty}</span></p>
					</div>
				</div>

			</div>
			</c:if>

			<!--遮罩层-->
			
			<!-- 邀请成员 -->
			<div id="member-pad" class="pad hidden">
				<div class="member-pad x-pad">
					<form action="invite.action" method="post">
						<p><b>真实姓名</b><input type="text" name="username" placeholder="团队成员的真实姓名" required="required"/></p>
						<p><b>手机号码</b><input type="text" name="userphone" placeholder="成员的注册的手机号码" required="required"/></p>
						<p class="btn"><button>邀请</button><a href="javascript:closePad('members');">取消</a></p>
					</form>
				</div>
			</div>
			
			<!-- 添加老师 -->
			<div id="tercher-pad" class="pad hidden">
				<div class="tercher-pad x-pad">
				<form action="" method="post">
					<p><b>真实姓名</b><input type="text" name="name" placeholder="指导老师的真实姓名" required="required"/></p>
					<p><b>手机号码</b><input type="text" name="phone" placeholder="指导老师的手机号码" required="required"/></p>
					<p><b>电子邮箱</b><input type="text" name="email" placeholder="请填写电子邮箱" required="required"/></p>
					<p><b>所在院校</b><input type="text" name="school" placeholder="请填写所在院校" required="required"/></p>
					<p><b>所在部门</b><input type="text" name="department" placeholder="指导老师的所在部门" required="required"/></p>
					<p><b>导师职称</b><input type="text" name="title" placeholder="指导老师的项目职称" required="required"/></p>
					<p class="btn"><button>保存</button><a href="javascript:closePad('teacher');">取消</a></p>
				</form>
				</div>
			</div>
			
			<!-- 添加专利 -->
			<div id="patent-pad" class="pad hidden">
				<div class="patent-pad x-pad">
				<form action="invite.action" method="post">
					<p><b>专利名称</b><input type="text" name="name" placeholder="项目所获专利名称" required="required"/></p>
					<p><b>专利类别</b>
						<select name="type" required="required">
	 						<option value ="0">发明专利</option>
	  						<option value ="1">实用新型专利</option>
	  						<option value="2">外观设计专利</option>
	  					</select>
	  				</p>
					<p><b>专利号</b><input type="text" name="id" placeholder="请填写" required="required"/></p>
					<p class="btn"><button>保存</button><a href="javascript:closePad('patent');">取消</a></p>
				</form>
				</div>
			</div>
			
			<!-- 修改密码 -->
			<div id="repw-pad" class="pad hidden">
				<div class="repw-pad x-pad">
				<form action="rePassword.action" method="post">
					<p><b>旧密码</b><input type="password" name="user.password" required="required"></p>
					<p><b>新密码</b><input type="password" name="newpassword" required="required"></p>
					<p><b>确认密码</b><input type="password" name="check" required="required"></p>
					<p class="btn"><button>修改</button><a href="javascript:closePad('repw');">取消</a></p>
				</form>
				</div>
			</div>
			
			<!-- 修改邮箱 -->
			<div id="reemail-pad" class="pad hidden">
				<div class="reemail-pad x-pad">
				<form action="reEmail.action" method="post">
					<p><b>新邮箱</b><input id="emainput" type="text" name="user.email" required="required"></p>
					<p><b>验证码</b><input type="text" name="checkcode" required="required">
					<button  onclick="sendcode(this.id);return false;">获取验证码</button></p>
					<p><b>密码</b><input type="password" name="user.password" required="required"></p>
					<p class="btn"><button>修改</button><a href="javascript:closePad('reemail');">取消</a></p>
				</form>
				</div>
			</div>


		</div>
	</div>
	
	<%@ include file="footer.jsp"%>
		
	<c:if test="${flag == 0}">
		<script>
			$("#pro_btn").addClass("focus");
		</script>
	</c:if>
	<c:if test="${flag == 1}">
		<script>
			$("#news_btn").addClass("focus");
		</script>
	</c:if>
	<c:if test="${flag == 2}">
		<script>
			$("#set_btn").addClass("focus");
		</script>
	</c:if>
	
		<script>
		/*
		var tab = 1;
		var $pro = $(".project-wrap");
		var $news = $(".news-wrap");
		var $set = $(".set-wrap"); 
		var $pro_btn = $("#pro_btn");
		var $news_btn = $("#news_btn");
		var $set_btn = $("#set_btn");*/
		
/* 		function proTab(){
			tab = 1;
			$pro.removeClass("hidden");
			$news.addClass("hidden");
			$set.addClass("hidden");
			$pro_btn.addClass("focus");
			$news_btn.removeClass("focus");
			$set_btn.removeClass("focus");
		}
		function newsTab(){
			tab = 2;
			$news.removeClass("hidden");
			$pro.addClass("hidden");
			$set.addClass("hidden");
			$news_btn.addClass("focus");
			$pro_btn.removeClass("focus");
			$set_btn.removeClass("focus");
		}
		function setTab(){
			tab = 3;
			$set.removeClass("hidden");
			$pro.addClass("hidden");
			$news.addClass("hidden");
			$set_btn.addClass("focus");
			$pro_btn.removeClass("focus");
			$news_btn.removeClass("focus");
		} */
		var padObj = {
			members : $("#member-pad"),
			teacher : $("#tercher-pad"),
			patent : $("#patent-pad"),
			repw : $("#repw-pad"),
			reemail : $("#reemail-pad"),
			setpad : $("#set-pad"),
		}
		function showPad(elem){
			padObj[elem].removeClass("hidden");
		}
		function closePad(elem){
			padObj[elem].addClass("hidden");
		}
		
		var countdown = 60;
		/*
		发送短信验证请求
		*/
		function sendcode(id){
			var btn = $("#"+id)
			var emi = $("#emainput");
			if(emi.val()!=null){
				var ret = /^[\w-]+(\.[\w-]+)*@[\w-]+(\.[\w-]+)+$/;
				if(ret.test(emi.val())){
					var emt = $("#emainput").serialize();
					console.log("send:");
					console.log(emt);
					$.ajax({
						url:"uEmailcode.action",
						type:"post",
						dataType:"json",//返回的类型必须为json
						data:emt,//数据
						success:function(data){
							if(data.meg=="success"){//成功
								settime(btn);//倒数
								console.log("success:"+data);
								/* obj.prev().html("已发送"); */	
								alert("已发送");
							}//如果失败就在当前页面显示
						}
					})
				}
			}else{
				alert("请先正确填写邮箱");
			}
		}

		/*
			重新发送计时
		*/
		function settime(obj) { //发送验证码倒计时
			if (countdown == 0) {
				obj.removeClass("lock");
				obj.attr('disabled',false); 
				//obj.removeattr("disabled"); 
				obj.html("获取验证码");
				countdown = 60;
				return false;
			} else { 
				obj.attr('disabled',true);
				obj.html("重新发送(" + countdown + ")");
				countdown--;
			} 
			setTimeout(function() {settime(obj)},1000);
		}
	</script>
</body>
</html>