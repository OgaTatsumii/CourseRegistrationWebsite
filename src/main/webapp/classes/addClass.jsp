<%@page import="model.Nganh"%>
<%@page import="model.KhoaHoc"%>
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
<title>Tạo lớp học</title>
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
					<h3 id="title_infor">Thêm lớp học</h3>
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
				<div class="main-content">
					<div class="form-container">
						<form id="formAddSinhVien" action="classManage" method="GET">
						<input type="hidden" name="action" value="taoClass">
							<div class="form-content">
								<h2>Thông tin lớp</h2>
								<div class="form-group">
									<div class="">
										<label for=""> Ngành :</label> <input type="text" id="search"
											list="options" name="maNganh" required>
										<datalist id="options" for="" >
											<%
											ArrayList<Nganh> dsNganh = (ArrayList<Nganh>) session.getAttribute("dsNganh");
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
									<div class="" style="margin-left:40px">
										<label for=""> Khóa học :</label>
										<select id="" for="" name ="khoaHoc"required>
											<%
											ArrayList<KhoaHoc> dsKhoaHoc = (ArrayList<KhoaHoc>) session.getAttribute("dsKhoaHoc");
											if (dsKhoaHoc != null) {
												for (KhoaHoc khoaHoc : dsKhoaHoc) {
											%>
											<option value="<%=khoaHoc.getMaNamHoc()%>"><%=khoaHoc.getMaNamHoc()%></option>
											<%
											}
											}
											%>
										</select>
									</div>
									<div class="" style="margin-left:40px">
										<label for=""> Hệ đào tạo :</label>
										<select id="" for="" name="heDaoTao"required>
											<option value="CQ">Chính qui</option>
											<option value="CL">Chất lượng cao</option>
										</select>
									</div>
									<div class="" style="margin-left:20px">
										<label for=""> Số lượng sinh viên:</label>
										<input type="number" name="soLuongSinhVien" min="1" required>
									</div>
								</div>
								<div class="form-group" style="margin-top:40px">
									<div class="">
										<label for="">   đào tạo :</label>
										<select id="" for="" name="coSoDaoTao"required style="width: 88%;">
											<option value="N">TP Hồ Chí Minh</option>
											<option value="B">Hà Nội</option>
										</select>
									</div>
								</div>
								<div class="form-group">
									<div class="" style="margin-top:40px">
										<%String maLop=(String)session.getAttribute("maLop");
										if (maLop!=null){%>
											<p style="color:red">Lớp <strong><%=maLop%></strong> đã được tạo thành công !</p>
										<%}else{ %>
											<p></p>
										<%} %>
									</div>
								</div>
								<div class="form-group" style="margin-top:10%"></div>
								<hr style="color: red; border: 2px solid red; margin: 35px 0;" />
							</div>
							<button class="" type="submit">Tạo Lớp</button>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>

<script src="handleJs/addStudentScript.js"></script>




