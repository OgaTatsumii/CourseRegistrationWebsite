<%@page import="model.NhanVien"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Admin</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style009.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/reset.css" />
<link rel="preconnect" href="https://fonts.googleapis.com" />
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
<link
	href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;500;600&family=Poppins:wght@300;400;500;600&family=Sora:wght@600;700;800&display=swap"
	rel="stylesheet" />
<link href="https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css"
	rel="stylesheet" />

</head>



<body>
	<div class="adminArea">
		<%@ include file = "layout/dashBoard.jsp" %>
		<div class="content">
			<jsp:include page="layout/navbar.jsp"  />
			<div class="main">
				<% if (title == null) { %>
					<h3 id="title_infor">TRANG CHỦ</h3>
				<% } else { %>
					<h3 id="title_infor"><%= title %></h3>
				<% } %>
				<div class="main-content">
					
				</div>
			</div>
		</div>
	</div>
</body>
</html>




