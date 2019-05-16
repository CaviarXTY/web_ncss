<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>企业主页</title>
<link rel="stylesheet" type="text/css" href="./css/user.css">
<script src="./js/jquery-1.11.0.js"></script>
</head>
<body>
	
	<%@ include file="header.jsp"%>
	
	<!-- 网页主体 -->
	<div class="wrapper">

		<!-- 个人信息 -->
		<div class="inf-wrap">
			<div class="wrap">
				<form id="imgf" class="hidden" action="enimg.action" method="post" enctype="multipart/form-data">
					<input id="imginput" type="file" name="img" required="required" accept="image/x-png,image/gif,image/jpeg,image/bmp"
					 onchange="document.getElementById('imgf').submit()">
					<input type="submit" value="更换头像"/>
				</form>
				<div class="img-box"><img src="../${entrepreneur.photo}"  onclick="document.getElementById('imginput').click()"></div>
				<div class="inf-box">
					<p class="name"><b>${entrepreneur.name}</b>企业认证通过</p>
					<p>邮	 箱：${entrepreneur.email}</p>
				</div>
			</div>
		</div>

		<!-- 选项卡目录 -->
		<div class="tab-herder-wrap wrap">
			<ul>
				<a id="focus_btn" class="focus" href="enproject.action"><li>关注项目</li></a>
				<a id="news_btn" href="ennews.action"><li >我的消息</li></a>
				<a id="set_btn" href="enset.action"><li >个人设置</li></a>
			</ul>
		</div>
	
		<!-- 选项卡 -->
		<div class="tab-wrap wrap">

			<!-- 项目选项卡 -->
			<c:if test="${flag == 0}">
			<div class="focus-wrap">
				<ul>
					<c:if test="${proList != null}">
					<c:forEach var="pro" items="${proList}">
					<li class="pro-box">
						<a class="pro-li" href="">
							<p>${pro.name}</p>
							<p>${pro.data}</p>
							<ul class="pro-list">
								<!--  <li class="teacher-li"><span>指导老师</span><b>null</b></li>-->
								<li class="orginators-li"><span>创始人</span><b>${pro.name}</b></li>
								<c:forEach var="mem" items="${pro.memList}">
									<li class="members-li"><b>${mem}</b></li>
								</c:forEach>
							</ul>
						</a>
					</li>
					</c:forEach>
<!-- 					<li class="pro-box">
						<a href="">取消关注</a>
						<a class="pro-li" href="">
							<p>项目名</p>
							<p>2018-8-8</p>
							<ul class="pro-list">
								<li class="teacher-li"><span>指导老师</span><b>老师壹</b></li>
								<li class="orginators-li"><span>创始人</span><b>创始人</b></li>
								<li class="members-li"><b>成员壹</b></li>
								<li class="members-li"><b>成员贰</b></li>
								<li class="members-li"><b>成员叁</b></li>
								<li class="members-li"><b>成员肆</b></li>
							</ul>
						</a>
					</li> -->
					</c:if>
					<li class="pro-box"><a class="pro-li" href="searchPo.action?flag=-1"><p class="findpro">点我有趣的项目</p></a></li>

				</ul>
			</div>
			</c:if>



	
			<!-- 信息选项卡 -->
			<c:if test="${flag == 1}">
			<div class="news-wrap">
				<div class="news-box">
					<div class="news-nav">
						
					</div>

					<div class="list">
						<ul>
							<li>
								<p class="name">姓名</p>
								<p class="value">姓名已经接受你的邀请，成为你的团队成员。</p>
								<p class="data">2018-05-17</p>
							</li>
							<li>
								<p class="name">姓名</p>
								<p class="value">姓名已经接受你的邀请，成为你的团队成员。</p>
								<p class="data">2018-05-17</p>
							</li>
							<li>
								<p class="name">姓名</p>
								<p class="value">姓名已经接受你的邀请，成为你的团队成员。</p>
								<p class="data">2018-05-17</p>
							</li>
							<li>
								<p class="name">姓名</p>
								<p class="value">姓名已经接受你的邀请，成为你的团队成员。</p>
								<p class="data">2018-05-17</p>
							</li>
							<li>
								<p class="name">姓名</p>
								<p class="value">姓名已经接受你的邀请，成为你的团队成员。姓名已经接受你的邀请，成为你的团队成员。</p>
								<p class="data">2018-05-17</p>
							</li>
							<li>
								<p class="name">姓名</p>
								<p class="value">姓名已经接受你的邀请，成为你的团队成员。</p>
								<p class="data">2018-05-17</p>
							</li>
							<li>
								<p class="name">姓名</p>
								<p class="value">姓名已经接受你的邀请，成为你的团队成员。</p>
								<p class="data">2018-05-17</p>
							</li>
						</ul>
						
					</div>

					<div class="value-wrap">
						<p class="value-title">姓名</p>
						<ul>
							<li class="send"><p>2018-8-8</p><p>姓名已经接受你的邀请成为你的团队成员。</p></li>
							<li class="get"><p>2018-8-8</p><p>姓名已经接受你的邀请，成为你的团队成员。</p></li>
						</ul>
					</div>

<%-- 	<div>
		<p>消息列（接受邀请）</p>
		<c:if test="${sessionList != null}">
			<c:forEach var="se" items="${sessionList}">
				<p>id:${se.id}</p>
				<p>姓名:${se.userName}</p>
				<p>用户id:${se.userId}</p>
				<p>对话内容:${se.value}</p>
				<p>最新日期:${se.date}</p>
				<c:if test="${se.isread}">
					<p>有消息</p>
				</c:if>
				<p>test:${se.newsList}</p>
				<c:forEach var="news" items="${se.newsList}">
					<p>type=${news.type}</p>
					<p>date=${news.date}</p>
					<p>value=${news.value}</p>
				</c:forEach>
			</c:forEach>
		</c:if>
	</div> --%>

				</div>
			</div>
			</c:if>

			<!-- 个人设置选项卡 -->
			<c:if test="${flag == 2}">
			<div class="set-wrap">
				<div class="show-box">
					<p>登录账号：<b>${entrepreneur.username}</b><a href="javascript:showPad('repw');">修改密码-></a></p>
					<p>真实姓名：<b>${entrepreneur.name}</b></p>
					<p>企业名称：<b>${en.name}有限公司</b></p>
					<p>电子邮箱：<b>${entrepreneur.email}</b><a href="javascript:showPad('reemail');">修改邮箱-></a></p>	
				</div>
				<div class="en set-box">
					<div id="set-pad" class="write  hidden">
						<form action="enReset.action" method="post">
							<p><b>企业名称</b>：<input type="text" name="en.name" value="${en.name}"/></p>
							<p><b>个人简介</b>：<input type="text" name="en.personal" value="${en.personal}"/></p>
							<p><b>企业所在地</b>：<input type="text" name="en.address" value="${en.address}"/></p>
							<p><b>资金规模</b>：<input type="text" name="en.capital" value="${en.capital}"/></p>
							<p><b>企业简介</b>：<input type="text" name="en.introduce" value="${en.introduce}"/></p>
							<p><b>企业地区</b>：
							<select name="en.province">
								<c:if test="${province!= null}">
									<option value="${province.id}"  selected = "selected">${province.name}</option>
								</c:if>
								<c:forEach var="province" items="${provinceList}">
									<option value="${province.id}">${province.name}</option>
								</c:forEach>
							</select></p>
							<p><b>企业领域</b>：
							<select name="en.type">
								<c:if test="${type!= null}">
									<option value="${type.id}"  selected = "selected">${type.name}</option>
								</c:if>
								<c:forEach var="type" items="${typeList}">
									<option value="${type.id}">${type.name}</option>
								</c:forEach>
							</select></p>
							<button>保存</button>
							<a href="javascript:closePad('setpad');">取消</a>
						</form>
					</div>
					<div class="read">
						<p><a href="javascript:showPad('setpad');">修改信息</a></p>
						<p><b>企业名称</b>：<span>${en.name}</span></p>
						<p><b>个人简介</b>：<span>${en.personal}</span></p>
						<p><b>企业所在地</b>：<span>${en.address}</span></p>
						<p><b>资金规模</b>：<span>${en.capital}</span></p>
						<p><b>企业简介</b>：<span>${en.introduce}</span></p>
						<p><b>企业类型</b>：<span>${en.type}</span></p>
						<p><b>企业地区</b>：<span>${en.province}</span></p>
					</div>
				</div>

			</div>
			</c:if>

			<!-- 遮罩层 -->
			<div id="repw-pad" class="pad hidden">
				<div class="repw-pad x-pad">
				<form action="enRePw.action" method="post">
					<p><b>旧密码</b><input type="text" name="entrepreneur.password" required="required"></p>
					<p><b>新密码</b><input type="text" name="newpassword" required="required"></p>
					<p><b>确认密码</b><input type="text" name="check" required="required"></p>
					<p class="btn"><button>修改</button><a href="javascript:closePad('repw');">取消</a></p>
				</form>
				</div>
			</div>
			<div id="reemail-pad" class="pad hidden">
				<div class="reemail-pad x-pad">
				<form action="enEmail.action" method="post">
					<p><b>新邮箱</b><input id="emainput" type="text" name="entrepreneur.email" required="required"></p>
					<p><b>验证码</b><input type="text" name="checkcode" required="required">
					<button id="sendbtn" onclick="sendcode(this.id);return false;">获取验证码</button></p>
					<p><b>密码</b><input type="text" name="entrepreneur.password" required="required"></p>
					<p class="btn"><button>修改</button><a href="javascript:closePad('reemail');">取消</a></p>
				</form>
				</div>
			</div>

		</div>
	</div>
	
	<%@ include file="footer.jsp"%>
	
	<c:if test="${flag == 0}">
		<script>
			$("#focus_btn").addClass("focus");
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
						url:"enEmailcode.action",
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