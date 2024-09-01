<%@page import="model.Nganh"%>
<%@page import="model.KhoaHoc"%>
<%@page import="model.Khoa"%>
<%@page import="model.SinhVien"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.Lop"%>
<%@page import="model.NhanVien"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Gửi mail</title>
<link rel="stylesheet" href="css/style009.css" />
<link rel="stylesheet" href="css/reset.css" />
<link rel="preconnect" href="https://fonts.googleapis.com" />
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
<link
	href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;500;600&family=Poppins:wght@300;400;500;600&family=Sora:wght@600;700;800&display=swap"
	rel="stylesheet" />
<link href="https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css"
	rel="stylesheet" />

</head>
<%
String id = (String) session.getAttribute("id");
ArrayList<Khoa> dsKhoa = null;
ArrayList<Nganh> dsNganh = null;
ArrayList<Lop> dsLop = null;
ArrayList<SinhVien> dssv = null;
if (id != null) {
	if (id.equalsIgnoreCase("khoa")) {
		dsKhoa = (ArrayList<Khoa>) session.getAttribute("dsKhoa");
	} else if (id.equalsIgnoreCase("nganh")) {
		dsNganh = (ArrayList<Nganh>) session.getAttribute("dsNganh");
	} else if (id.equalsIgnoreCase("lop")) {
		dsLop = (ArrayList<Lop>) session.getAttribute("dsLop");
	} else if (id.equalsIgnoreCase("sv")) {
		dssv = (ArrayList<SinhVien>) session.getAttribute("dsSV");
	}
}
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
					<h3 id="title_infor">Gửi mail</h3>
					<%
					} else {
					%>
					<h3 id="title_infor"><%=title%></h3>
					<%
					}
					%>
					<a class="back" href="classManage"> <i
						class='bx bx-left-arrow-alt'></i></a>
				</div>
				<div id="toast"></div>
				<div class="main-content">
					<div class="form-container">
						<form id="formAddSinhVien" action="announceManage" method="GET">
							<input type="hidden" name="action" value="guiMail">
							<input type="hidden" name="id" value="<%=id%>">
							<div class="form-content">
								<h2>
									Gửi Mail đến
									<%=id%></h2>
								<div class="form-group">
									<div class="">
										<label for="" style="font-weight: bold">Tiêu đề</label>
										<textarea
											style="height: 150px; padding: 10px; box-sizing: border-box; border-radius: 10px; font-size: 17px"
											rows="7" cols="30" name="tieuDe"></textarea>
									</div>
									<div class="">
										<label style="font-weight: bold" for="">Nội dung</label>
										<textarea
											style="height: 150px; padding: 10px; box-sizing: border-box; border-radius: 10px; font-size: 17px; width: 100%"
											rows="7" cols="75" name="noiDung"></textarea>
									</div>
								</div>
								<div class="form-group">
									<%
									if (id.equalsIgnoreCase("khoa")) {
									%>
									<div class="">
										<label for="">Chọn Khoa :</label> <input type="text" id="search"
											list="op1" name="maKhoa">
										<datalist id="op1" for="">
											<%
											if (dsKhoa != null) {
												for (Khoa khoa : dsKhoa) {
											%>
											<option value="<%=khoa.getMaKhoa()%>"><%=khoa.getTenKhoa()%></option>
											<%
											}
											}
											%>
										</datalist>
									</div>
									<%
									} else if (id.equalsIgnoreCase("nganh")) {
									%>
									<div class="">
										<label for="">Chọn Ngành :</label> <input type="text" id="search"
											list="op2" name="maNganh">
										<datalist id="op2" for="">
											<%
											if (dsNganh != null) {
												for (Nganh nganh : dsNganh) {
											%>
											<option value="<%=nganh.getMaNganh()%>"><%=nganh.getTenNganh()%></option>
											<%
											}
											}
											%>
										</datalist>
									</div>
									<%
									} else if (id.equalsIgnoreCase("lop")) {
									%>
									<div class="">
										<label for="">Chọn Lớp :</label> <input type="text" id="search"
											list="op3" name="maLop">
										<datalist id="op3" for="">
											<%
											if (dsLop != null) {
												for (Lop lop : dsLop) {
											%>
											<option value="<%=lop.getMaLop()%>"><%=lop.getMaLop()%></option>
											<%
											}
											}
											%>
										</datalist>
									</div>
									<%
									}else if (id.equalsIgnoreCase("sv")) {
										%>
										<div class="">
										<label for="">Chọn Sinh viên :</label> <input type="text" id="search"
											list="op4" name="maSinhVien">
										<datalist id="op4" for="">
											<%
											if (dssv != null) {
												for (SinhVien sv : dssv) {
											%>
											<option value="<%=sv.getMaSinhVien()%>"><%=sv.getHoTen()+" : "+sv.getMaSinhVien()%></option>
											<%
											}
											}
											%>
										</datalist>
									</div>
										<%
										}
										%>
								</div>
								<%String message=(String) session.getAttribute("message");
								String sl=(String) session.getAttribute("sl");
										if(message!=null){
										%>
										<p><%=message %></p>
										<p><%=sl %></p>
								<%} %>
								<hr style="color: red; border: 2px solid red; margin: 35px 0;" />
							</div>
							<button class="" type="submit">Gửi</button>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>


<script src="handleJs/addStudentScript.js"></script>




