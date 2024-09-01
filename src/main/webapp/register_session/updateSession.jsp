<%@page import="java.text.Format"%>
<%@page import="model.Khoa"%>
<%@page import="model.Lop"%>
<%@page import="model.PhienDangKy"%>
<%@page import="model.CTNamHoc_HocKy"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.NhanVien"%>
<%@page import="java.time.LocalDateTime"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Cập nhật thông tin phiên</title>
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
 String maKhoa=(String) session.getAttribute("maKhoa");
 maKhoa= (maKhoa==null) ? "":maKhoa;
 
 PhienDangKy pdk=(PhienDangKy) session.getAttribute("pdk");
 
 int hourStart=(pdk!=null) ?pdk.getThoiGianBatDau().getHour():0;
 String hourStartStr = String.format("%02d", hourStart);
 int minStart =(pdk!=null) ?pdk.getThoiGianBatDau().getMinute():00;
 String minStartStr = String.format("%02d", minStart);
 
 int hourEnd=(pdk!=null) ?pdk.getThoiGianKetThuc().getHour():0;
 String hourEndStr = String.format("%02d", hourStart);
 int minEnd =(pdk!=null) ?pdk.getThoiGianKetThuc().getMinute():0;
 String minEndtStr = String.format("%02d", minStart);

 LocalDateTime date = LocalDateTime.now();
 DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");

 String ngayBatDau= (pdk!=null) ? pdk.getThoiGianBatDau().format(dateFormat):date.format(dateFormat);
 String ngayKetThuc= (pdk!=null) ? pdk.getThoiGianKetThuc().format(dateFormat):date.format(dateFormat);
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
					<h3 id="title_infor">Cập nhật thông tin phiên</h3>
					<%
					} else {
					%>
					<h3 id="title_infor"><%=title%></h3>
					<%
					}
					%>
					<a class="back" href="registersession"> <i
						class='bx bx-left-arrow-alt'></i></a>
				</div>
				<div class="main-content">
					<div class="form-container">
						<form id="formAddSinhVien" action="registersession" method="GET">
						<input type="hidden" name="action" value="updatePhien">
							<div class="form-content">
								<h2>Thông tin phiên</h2>
								<div class="form-group">
								<input type="hidden" value="<%=pdk.getMaPhien()%>" name="maPhien">
									<div class="">
										<label style="display: block" for="">Khoa cũ</label> 
										<select id="" style="margin:0;width:330px">
											<option value="<%=pdk.getKhoa().getMaKhoa()%>"><%=pdk.getKhoa().getTenKhoa()%></option>
										</select>
									</div>
									<div class="" style="margin-left:120px">
										<label style="display: block" for="">Khoa mới:</label> 
										<select id="" name="maKhoaMoi" style="width:300px">
											<option  value="<%=pdk.getKhoa().getMaKhoa()%>"><%=pdk.getKhoa().getTenKhoa()%></option>
											<%
											ArrayList<Khoa> dsKhoa = (ArrayList<Khoa>) session.getAttribute("dsKhoa");
											if (dsKhoa != null) {
												for (Khoa khoa : dsKhoa) {
											%>
											<option  value="<%=khoa.getMaKhoa()%>"><%=khoa.getTenKhoa()%></option>
											<%
											}
											}
											%>
										</select>
									</div>									
								</div>
								<div class="form-group">
									<div class="" style="width:250px">
										<label for="">Ngày đăng ký cũ :</label> <input
											value="<%=ngayBatDau%>" type="date" id="" name="" required />
											<input
											type="time" step="1" style="width:150px; height:40px;"
											value="<%=hourStartStr+":"+minStartStr%>" id="SDT" name="" required />
									</div>
									<div class="" style="width:250px;margin-left:200px"">
										<label for="">Ngày đăng ký mới :</label> <input
											value="<%=ngayBatDau%>" type="date" id="" name="ngayBatDauMoi" required />
											<input
											type="time" step="1" style="width:150px; height:40px;"
											value="<%=hourStartStr+":"+minStartStr%>" id="SDT" name="tgBatDauMoi" required />
									</div>
									
								</div>
								<div class="form-group">
									<div class="" style="width:250px">
										<label for="">Ngày kết thúc cũ :</label> <input
											value="<%=ngayKetThuc%>" type="date" id="" name="" required />
											<input
											type="time" step="1" style="width:150px; height:40px;"
											 value="<%=hourEndStr+":"+minEndtStr%>" id="SDT" name="" required />
									</div>
									<div class="" style="width:250px;margin-left:200px"">
										<label for="">Ngày kết thúc mới :</label> <input
											 value="<%=ngayKetThuc%>" type="date" id="" name="ngayKetThucMoi" required />
											<input
											type="time" step="1" style="width:150px; height:40px;"
											value="<%=hourEndStr+":"+minEndtStr%>" id="SDT" name="tgKetThucMoi" required />
									</div>
								</div>
								<div class="form-group">
									<div class="yearContent">
										<label for="NgaySinh">Năm học và học kỳ cũ :</label>
										<select style="width:300px">
											<option value="<%=pdk.getCtNamHocHocKy().getmaNHHocKy()%>"><%=pdk.getCtNamHocHocKy().getnamHoc().getTenNamHoc()+":"+pdk.getCtNamHocHocKy().gethocKy().getTenHocKy()%></option>
										</select>
									</div>
									<div class="yearContent">
										<label for="NgaySinh">Chọn năm học và học kỳ mới :</label>
										<select name="maNHHocKyMoi" style="width:300px">
											<option value="<%=pdk.getCtNamHocHocKy().getmaNHHocKy()%>"><%=pdk.getCtNamHocHocKy().getnamHoc().getTenNamHoc()+":"+pdk.getCtNamHocHocKy().gethocKy().getTenHocKy()%></option>
											<%
											ArrayList<CTNamHoc_HocKy> dsCTNHHocKy = (ArrayList<CTNamHoc_HocKy>) session.getAttribute("dsCTNamHoc_HocKy");
											if (dsCTNHHocKy != null) {
												for (CTNamHoc_HocKy CTNamHoc_HocKy : dsCTNHHocKy) {
											%>
											<option value="<%=CTNamHoc_HocKy.getmaNHHocKy()%>"><%=CTNamHoc_HocKy.getnamHoc().getTenNamHoc()+":"+CTNamHoc_HocKy.gethocKy().getTenHocKy()%></option>
											<%
											}
											}
											%>
										</select>
									</div>
								</div>
								<%String message=(String) session.getAttribute("message"); 
								if(message!=null){%>
								<p style="margin-left:250px;margin-top:25px;color:red;font-style:italic;font-size:20px"><%=message %></p>
								
								<%} %>
								<div class="form-group"></div>
								<hr style="color: red; border: 2px solid red; margin: 35px 0;" />
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



<script src="handleJs/addStudentScript.js"></script>




