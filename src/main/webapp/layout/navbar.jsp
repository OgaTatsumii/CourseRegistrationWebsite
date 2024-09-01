<%@page import="model.NhanVien"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
NhanVien nhanVien = (NhanVien) session.getAttribute("nhanVien");
String title = (String) request.getAttribute("title");
%>
<div class="navbar">
	<h1>HỌC VIỆN CÔNG NGHỆ BƯU CHÍNH VIỄN THÔNG - TP.HCM</h1>
	<div class="avatar" for="imageLogo">
		<img class="imageLogo" src="${pageContext.request.contextPath}/image/user-circle-solid-60.png"
			alt="logoAvatar" />
	</div>
	<ul class="sub-menu">
		<li>
			<p class="nameObject"><%=nhanVien.getTaiKhoan().getChucVu().getTenChucVu()%>
			</p>
		</li>
		<li class="containerButton"><a href="dang-xuat"
			style="display: block" class="btn">Đăng xuất</a></li>
	</ul>
</div>
<script src="handleJs/navbar.js"></script>