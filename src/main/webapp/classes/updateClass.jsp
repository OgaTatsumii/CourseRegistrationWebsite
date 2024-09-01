<%@page import="model.Nganh"%>
<%@page import="model.KhoaHoc"%>
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
<title>Cập nhật thông tin lớp</title>
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
Lop lop = (Lop) session.getAttribute("lop");
String maLop = (String)session.getAttribute("maLop");
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
					<h3 id="title_infor">Cập nhật thông tin lớp</h3>
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
						<form id="formAddSinhVien" action="classManage" method="GET">
						<input type="hidden" name="action" value="suaDuLieuClass">
							<div class="form-content">
								<h2>Thông tin cơ bản :</h2>
								<div class="form-group">
								<input type="hidden" value="<%=maLop%>" name="maLop">
									<div class="">
										<label style="display: block" for="">Ngành học cũ</label> 
										<select id="" style="margin:0;width:400px">
											<option value="<%=lop.getNganh().getMaNganh()%>"><%=lop.getNganh().getTenNganh()%></option>
										</select>
									</div>
									<div class="" style="margin-left:8%">
										<label style="display: block" for="">Ngành học mới:</label> 
										<select id="" name="nganhHocMoi">
											<option value=""></option>
											<%
											ArrayList<Nganh> danhSachNganh = (ArrayList<Nganh>) session.getAttribute("dsNganh");
											if (danhSachNganh != null) {
												for (Nganh nganh : danhSachNganh) {
											%>
											<option  value="<%=nganh.getMaNganh()%>"><%=nganh.getTenNganh()%></option>
											<%
											}
											}
											%>
										</select>
									</div>									
									
								</div>
								<div class="form-group">
									<div class="">
										<label for=""> Khóa học cũ :</label>
										<select id="" for="" name ="" required>
											<option value="<%=lop.getKhoaHoc().getMaNamHoc()%>"><%=lop.getKhoaHoc().getMaNamHoc()%></option>
										</select>
									</div>
									<div class="" style="margin-left:22%">
										<label for=""> Khóa học mới :</label>
										<select id="" for="" name ="khoaHocMoi"required>
											<option value=""></option>
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
								</div>
								<div class="form-group">
									<div class="">
										<label for=""> Hệ đào tạo cũ :</label>
										<select id="" for="" name=""required>
											<%String originalString = lop.getMaLop();
											String extractedString = originalString.substring(3, 5);
												if(extractedString.equals("CQ")){
											%>
												<option value="CQ">Chính qui</option>
											<%}else{ %>
											<option value="CL">Chất lượng cao</option>
										<%} %>
										</select>
									</div>
									<div class="" style="margin-left:17%">
										<label for=""> Hệ đào tạo mới :</label>
										<select id="" for="" name="heDaoTaoMoi"required>
											<option value=""></option>
											<option value="CQ">Chính qui</option>
											<option value="CL">Chất lượng cao</option>
										</select>
									</div>
								</div>
								<div class="form-group">
									<div class="">
										<label for=""> Số lượng sinh viên cũ :</label>
										<select id="" for="" name="" required>
											<option><%=lop.getSoLuongSinhVien() %></option>
										</select>
									</div>
									<div class="" style="margin-left:245px">
										<label for=""> Số lượng sinh viên mới :</label>
										<input type="number" min="1" name=soLuongSinhVienMoi>
									</div>
								</div>
								<div class="form-group">
									<div class="">
										<label for=""> Cơ sở đào tạo cũ :</label>
										<select id="" for="" name=""required>
											<%String originalString1 = lop.getMaLop();
											String lastCharacter1 = originalString1.substring(originalString.length() - 1);
											if(lastCharacter1.equals("N")){%>
												<option value="N">TP Hồ Chí Minh</option>
											<%}else{ %>
											<option value="B">Hà Nội</option>
										<%} %>
										</select>
									</div>
									<div class="" style="margin-left:12%">
										<label for=""> Cơ sở đào tạo mới:</label>
										<select id="" for="" name="coSoDaoTaoMoi"required>
											<option value=""></option>
											<option value="N">TP Hồ Chí Minh</option>
											<option value="B">Hà Nội</option>
										</select>
									</div>
								</div>
								<div class="form-group">
									<div class="">
										<%String maLopMoi=(String)session.getAttribute("maLopMoi");
										if (maLopMoi!=null){%>
											<p style="color:red">Đã sửa<strong><%=maLop%></strong> thành <strong><%=maLopMoi%></strong> !</p>
										<%}else{ %>
											<p></p>
										<%} %>
									</div>
								</div>
								<div class="form-group"></div>
								<hr style="color: red; border: 2px solid red; margin: 35px 0;" />
							</div>
							<button class="" type="submit">Sửa Lớp</button>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>


<script src="handleJs/addStudentScript.js"></script>




