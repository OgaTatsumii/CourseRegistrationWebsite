<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Thông tin cá nhân</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style009.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/reset.css" />
</head>
<script src="https://kit.fontawesome.com/7b97f02ae4.js"
	crossorigin="anonymous"></script>
<style>
.disabled-row {
	pointer-events: none; /* Không thể bấm chọn */
	opacity: 0.5; /* Làm mờ */
}
</style>
<body>
	<div class="adminArea">
		<%@ include file="/layout/dashBoardSinhVien.jsp"%>
		<div class="content">
			<jsp:include page="/layout/navbarSinhVien.jsp" />
			<div class="main">
				<%
				if (title == null) {
				%>
				<h3 id="title_infor">TRANG CHỦ</h3>
				<%
				} else {
				%>
				<h3 id="title_infor"><%=title%></h3>
				<%
				}
				%>
				<div class="main-content"></div>
			</div>
		</div>
</body>

</html>