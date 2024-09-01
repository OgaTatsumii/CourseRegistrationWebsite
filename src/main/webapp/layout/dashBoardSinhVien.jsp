<%@page import="model.SinhVien"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link rel="stylesheet" href="css/style005.css" />
<link rel="stylesheet" href="css/reset.css" />
<script src="https://kit.fontawesome.com/7b97f02ae4.js" crossorigin="anonymous"></script>
<%
SinhVien sinhVien = (SinhVien) session.getAttribute("sinhVien");
String title = (String) request.getAttribute("title");
%>

<div class="dashBoard">
	<div class="logoPTIT">
		<img
			src="${pageContext.request.contextPath}/image/Logo Học Viện Công Nghệ Bưu Chính Viễn Thông - PTIT(Simple).svg"
			alt="LogoPTIT" />
	</div>
	<div class="infObject">
		<div class="logoAvatar">
			<img src="${pageContext.request.contextPath}/image/user-solid-60.png" alt="" />
		</div>
		<div class="information">
			<p class="name"><%=sinhVien.getHoTen()%></p>
			<p class="id"><%=sinhVien.getMaSinhVien()%></p>
			<p class="role"><%=sinhVien.getTaiKhoan().getChucVu().getTenChucVu()%></p>
		</div>
	</div>
	<div class="features">
		<h2>Trang cá nhân</h2>
		<ul class="listFeatures">
			<li class="ftct"><a href="/DoAnDKHP_Nhom14/ketquahoctap"><i class="fa-solid fa-bars-progress"></i><span class="text">Tiến độ học tập</span></a></li>
			<li class="ftct"><a href="/DoAnDKHP_Nhom14/ThongTinCaNhan"><i class="fa-solid fa-user"></i><span class="text">Thông tin cá nhân</span></a></li>
			<li class="ftct"><a href=""><i class="fa-solid fa-bell"></i><span>Thông báo cá nhân</span></a></li>
		</ul>
		
		<h2>Tra cứu thông tin</h2>
		<ul class="listFeatures">
			<li class="ftct"><a href=""><i class="fa-solid fa-clipboard-list"></i><span class="text">Chương trình đào tạo</span></a></li>
			<li class="ftct"><a href=""><i class="fa-solid fa-calendar"></i><span class="text">Thời khóa biểu</span></a></li>
			<li class="ftct"><a href=""><i class="fa-solid fa-calendar-week"></i><span class="text">Lịch thi</span></a></li>
			<li class="ftct"><a href=""><i class="fa-solid fa-clipboard-check"></i><span class="text">Kết quả rèn luyện</span></a></li>
			<li class="ftct"><a href=""><i class="fa-solid fa-trophy"></i><span class="text">Kết quả học tập</span></a></li>
			<li class="ftct"><a href=""><i class="fa-solid fa-money-check-dollar"></i><span class="text">Thông tin học phí</span></a></li>
			<li class="ftct"><a href="/DoAnDKHP_Nhom14/registerCourseHistory"><i class="fa-regular fa-calendar-check"></i><span class="text">Kết quả Đăng ký học phần</span></a></li>
		</ul>
		
		<h2>Chức năng trực tuyến</h2>
		<ul class="listFeatures">
			<li class="ftct"><a href="/DoAnDKHP_Nhom14/registerCourse"><i class="fa-solid fa-pen-to-square"></i><span class="text">Đăng ký học phần</span></a></li>
			<li class="ftct"><a href="/DoAnDKHP_Nhom14/DangKyNguyenVong"><i class="fa-solid fa-gavel"></i><span class="text">Đăng ký nguyện vọng</span></a></li>
			<li class="ftct"><a href=""><i class="fa-solid fa-square-check"></i><span class="text">Giấy xác nhận sinh viên</span></a></li>
			<li class="ftct"><a href=""><i class="fa-solid fa-file-word"></i><span class="text">Đăng ký phiếu điểm</span></a></li>
			<li class="ftct"><a href=""><i class="fa-solid fa-rotate-right"></i><span class="text">Đăng ký phúc khảo</span></a></li>
			<li class="ftct"><a href=""><i class="fa-solid fa-shuffle"></i><span class="text">Đăng ký chuyển điểm</span></a></li>
			<li class="ftct"><a href=""><i class="fa-solid fa-money-check-dollar"></i><span class="text">Thanh toán học phí</span></a></li>
		</ul>
	</div>
</div>