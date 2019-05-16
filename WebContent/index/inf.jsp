<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${title}</title>
<link rel="stylesheet" type="text/css" href="./css/inf.css">
</head>
<body>

	<%@ include file="header.jsp"%>
	
	<!-- 网页主体 -->
	<div class="wrapper">
	
		<!-- 项目 -->
		<c:if test="${project != null && id != 0}">
		<div class="peoject-wrap">

			<div class="pro-title">
				<div class="title-box">
					<div class="logo-box"><img src="../photo/3.jpg"></div>
					<div class="value-box">
						<p>${project.name}</p>
						<p>广东省</p>
						<p>类型</p>
					</div>
					<%
						Object ent = request.getAttribute("entrepreneur");
    					if(ent != null && !"".equals(ent)){//查看登录状态
					%>
						<c:if test="${project != null && id != 0}">
							<c:if test="${isfocus}">
								<a href="#">已关注</a>
							</c:if>
							
							<c:if test="${!isfocus}">
								<a href="enfocus.action?pid=${project.id}">关注</a>
							</c:if>
						</c:if>
  					<%
  						}
  					%>
					<!-- <a href="#">点赞10W+</a> -->
				</div>
				
			</div>

			<div class="pro-inf-wrap">
				<ul>
					<li>
						<b>参赛信息</b>
						<span>你暂无权查看该信息</span>
					</li>
					
					<li>
						<b>项目信息</b>
						<span>
							<p class="th">项目概述</p>
							<p>
								${project.presentation},项目介绍真的真的真的真的真的真的真的真的真的真的真的真的真的真的真的真的真的真的真的真的真的真的真的真的真的真的真的真的真的真的很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长
							</p>
							<p class="th">项目进展</p>
							<p>您暂无权查看该信息</p>
						</span>
					</li>

					<li>
						<b>专利情况</b>
						<span>你暂无权查看该信息</span>
					</li>

					<li>
						<b>工商信息</b>
						<span>你暂无权查看该信息</span>
					</li>

				</ul>
			</div>
			
		</div>
		</c:if>


		<!-- 企业 -->
		<c:if test="${en != null && id != 0}">
		<div class="en-wrap">

			<div class="en-title">
				<div class="title-box">
					<div class="photo-box"><img src="../photo/3.jpg"></div>
					<div class="value-box">
						<p>${en.entrepreneurs}</p>
						<p>${en.name}</p>
						<p>${en.provinceName}</p>
						<p>${en.typeName}</p>
					</div>
					<!-- <a href="#">投票10W+</a> -->
				</div>
			</div>

		</div>

		<div class="en-inf-wrap">
			<ul>
				<li>
					<b class="en-inf-title">个人简介</b>
					<span class="text">${en.personal}介绍真的真的真的真的真的真的真的真的真的真的真的真的真的真的真的真的真的真的真的真的真的真的真的真的真的真的真的真的真的真的很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长</span>
				</li>

				<li>
					<b class="en-inf-title">机构信息</b>
					<span>
						<p><b>机构名称：</b>${en.name}</p>
						<p><b>机构所在地：</b>${en.address}</p>
						<p><b>机构资金规模：</b>${en.capital}</p>
						<p><b>机构简介：</b></p>
						<p class="text">
							${en.introduce}介绍真的真的真的真的真的真的真的真的真的真的真的真的真的真的真的真的真的真的真的真的真的真的真的真的真的真的真的真的真的真的很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长
						</p>
					</span>
				</li>

				<li>
					<b class="en-inf-title">投资案例</b>
					<br>
					<span>
						<p class="txet">无投资案例</p>
					</span>
				</li>
			</ul>
		</div>
		</c:if>
		
	</div>
	
	<%@ include file="footer.jsp"%>

	
</body>
</html>