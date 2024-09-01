<%@page import="model.SinhVien"%>
<%@page import="model.QueQuan"%>
<%@page import="model.Lop"%>
<%@page import="java.util.ArrayList"%>
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

<%
SinhVien sinhVien = (SinhVien) request.getAttribute("sinhVien");
String errorMessageMaSinhVien = (String) request.getAttribute("errorMessageMaSinhVien");
if (errorMessageMaSinhVien == null) {
	errorMessageMaSinhVien = "";
}
String errorMessageHoTen = (String) request.getAttribute("errorMessageHoTen");
if (errorMessageHoTen == null) {
	errorMessageHoTen = "";
}

String errorMessageSoDienThoai = (String) request.getAttribute("errorMessageSoDienThoai");
if (errorMessageSoDienThoai == null) {
	errorMessageSoDienThoai = "";
}

String errorMessageCCCD = (String) request.getAttribute("errorMessageCCCD");
if (errorMessageCCCD == null) {
	errorMessageCCCD = "";
}

String errorMessageEmail = (String) request.getAttribute("errorMessageEmail");
if (errorMessageEmail == null) {
	errorMessageEmail = "";
}

String maSinhVien = (String) request.getAttribute("MaSinhVien");
String hoTen = (String) request.getAttribute("HoTen");
String ngaySinhStr = (String) request.getAttribute("NgaySinh");
String gioiTinh = (String) request.getAttribute("GioiTinh");
String soDienThoai = (String) request.getAttribute("SDT");
String cccd = (String) request.getAttribute("CCCD");
String email = (String) request.getAttribute("Email");
String tamTru = (String) request.getAttribute("TamTru");
String maLop = (String) request.getAttribute("MaLop");
String maTinh = (String) request.getAttribute("MaTinh");
String taiKhoan = (String) request.getAttribute("User");
String matKhau = (String) request.getAttribute("Password");

maSinhVien = (maSinhVien == null) ? "" : maSinhVien;
maSinhVien = (maSinhVien == null) ? "" : maSinhVien;
hoTen = (hoTen == null) ? "" : hoTen;
ngaySinhStr = (ngaySinhStr == null) ? "" : ngaySinhStr;
gioiTinh = (gioiTinh == null) ? "" : gioiTinh;
soDienThoai = (soDienThoai == null) ? "" : soDienThoai;
cccd = (cccd == null) ? "" : cccd;
email = (email == null) ? "" : email;
tamTru = (tamTru == null) ? "" : tamTru;
maLop = (maLop == null) ? "" : maLop;
maTinh = (maTinh == null) ? "" : maTinh;
taiKhoan = (taiKhoan == null) ? "" : taiKhoan;
matKhau = (matKhau == null) ? "" : matKhau;
%>

<body>
	<div class="adminArea">
		<!-- Include DashBoard -->
		<%@ include file="/layout/dashBoard.jsp"%>

		<div class="content">
			<!-- Include NavBar -->
			<jsp:include page="/layout/navbar.jsp" />
			<div class="main">
				<div class="addStudentTitle" style="display: flex;">
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
					<a class="back" href="studentManage"> <i
						class='bx bx-left-arrow-alt'></i></a>
				</div>
				<div class="main-content">
					<div class="form-container">
						<form id="formAddSinhVien" action="upDateStudent" method="POST">
							<div class="form-content">
								<h2>Thông tin cơ bản</h2>
								<div class="form-group">
									<div class="idStudentContent">
										<label for="MaSinhVien">Mã Sinh Viên :</label>
										<%
										if (sinhVien != null) {
										%>
										<input type="text" id="MaSinhVien" name="MaSinhVien"
											value="<%=sinhVien.getMaSinhVien()%>" required />
										<%
										} else {
										%>
										<input type="text" id="MaSinhVien" name="MaSinhVien"
											value="<%=maSinhVien%>" required />
										<%
										}
										%>

										<p class="error">
											<%=errorMessageMaSinhVien%>
										</p> 
									</div>
									<div class="nameContent">
										<label for="HoTen">Họ Tên :</label>
										<%
										if (sinhVien != null) {
										%>
										<input type="text" id="HoTen" name="HoTen"
											value="<%=sinhVien.getHoTen()%>" required />
										<%
										} else {
										%>
										<input type="text" id="HoTen" name="HoTen" value="<%=hoTen%>"
											required />
										<%
										}
										%>

										<p class="error">
											<%=errorMessageHoTen%>
										</p>
									</div>
								</div>
								<div class="form-group">
									<div class="yearContent">
										<label for="NgaySinh">Ngày Sinh :</label>
										<%
										if (sinhVien != null && sinhVien.getNgaySinh() != null) {
										%>
										<input type="date" id="NgaySinh" name="NgaySinh"
											value="<%=sinhVien.getNgaySinh()%>" required />
										<%
										} else {
										%>
										<input type="date" id="NgaySinh" name="NgaySinh"
											value="<%=ngaySinhStr%>" required />
										<%
										}
										%>

										<p class="error">
										</p>
									</div>
									<div class="sexContent">
										<label for="GioiTinh">Giới Tính :</label>
										<select id="GioiTinh" name="GioiTinh">
											<%
											if (sinhVien != null) {
											%>
											<option value="Nam"
												<%="Nam".equals(sinhVien.getGioiTinh()) ? "selected" : ""%>>Nam</option>
											<option value="Nữ"
												<%="Nữ".equals(sinhVien.getGioiTinh()) ? "selected" : ""%>>Nữ</option>
											<option value="Khác"
												<%="Khác".equals(sinhVien.getGioiTinh()) ? "selected" : ""%>>Khác</option>
											<%
											} else {
											%>
											<option value="Nam"
												<%="Nam".equals(gioiTinh) ? "selected" : ""%>>Nam</option>
											<option value="Nữ"
												<%="Nữ".equals(gioiTinh) ? "selected" : ""%>>Nữ</option>
											<option value="Khác"
												<%="Khác".equals(gioiTinh) ? "selected" : ""%>>Khác</option>
											<%
											}
											%>
										</select>

									</div>

								</div>
								<div class="form-group">
									<div class="phoneContent">
										<label for="SDT">Số Điện Thoại :</label> <input type="text"
											id="SDT" name="SDT"
											value="<%=sinhVien != null ? sinhVien.getSoDienThoai() : (soDienThoai != null ? soDienThoai : "")%>"
											required />
										 <p class="error">
											<%=errorMessageSoDienThoai%>
										</p> 
									</div>
									<div class="idContent">
										<label for="CCCD">CCCD/CMND :</label> <input type="text"
											id="CCCD" name="CCCD"
											value="<%=sinhVien != null ? sinhVien.getCCCD() : (cccd != null ? cccd : "")%>"
											required />
										 <p class="error">
											<%=errorMessageCCCD%>
										 </p>
									</div>
								</div>

								<div class="form-group">
									<div class="emailContent">
										<label for="Email">Email :</label> <input type="email"
											id="Email" name="Email"
											value="<%=sinhVien != null ? sinhVien.getEmail() : (email != null ? email : "")%>"
											required />
										<p class="error">
											<%=errorMessageEmail%>
										</p>
									</div>
								</div>
								<div class="form-group">
									<div class="placeContent">
										<label for="TamTru">Tạm Trú :</label> <input type="text"
											id="TamTru" name="TamTru"
											value="<%=sinhVien != null ? sinhVien.getTamTru() : (tamTru != null ? tamTru : "")%>"
											required />
									</div>
								</div>
								<div class="form-group">
									<div class="classContent">
										<label for="MaLop">Lớp :</label> <select id="MaLop"
											name="MaLop">
											<%
											ArrayList<Lop> danhSachLop = (ArrayList<Lop>) session.getAttribute("dsLop");
											if (danhSachLop != null) {
												for (Lop lop : danhSachLop) {
											%>
											<option value="<%= lop.getMaLop() %>"
    										<%= (sinhVien != null && sinhVien.getLop().getMaLop() != null && lop.getMaLop().equals(sinhVien.getLop().getMaLop())) 
    										|| (sinhVien == null && maLop.equals(lop.getMaLop())) ? "selected" : "" %>>
    										<%= lop.getMaLop() %>
											</option>																					
											<%
											}
											}
											%>
										</select>
									</div>
									<div class="provinceContent">
										<label for="MaTinh">Mã Tỉnh :</label> <select id="MaTinh"
											name="MaTinh">
											<%
											ArrayList<QueQuan> danhSachQueQuans = (ArrayList<QueQuan>) session.getAttribute("dsQueQuan");
											if (danhSachQueQuans != null) {
												for (QueQuan queQuan : danhSachQueQuans) {
											%>
											<option value="<%= (sinhVien != null ? queQuan.getMaTinh() : maTinh) %>"
    										<%= (sinhVien != null && sinhVien.getQueQuan() != null && queQuan.getMaTinh().equals(sinhVien.getQueQuan().getMaTinh()))
    										|| (sinhVien == null && maTinh.equals(queQuan.getMaTinh()))? "selected" : "" %>>
    										<%= queQuan.getTenTinh() %>
											</option>										
											<%
											}
											}
											%>
										</select>
										<input type="text" id="User" name="User" style="display: none;" value="<%= (sinhVien != null && sinhVien.getTaiKhoan() != null) ? sinhVien.getTaiKhoan().getUserName() : (taiKhoan != null ? taiKhoan : "") %>" required />
										
									</div>
								</div>
								
							</div>
							<button class="addStudent" type="submit">Cập nhật</button>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>

<script src="${pageContext.request.contextPath}/handleJs/addStudentScript.js"></script>




