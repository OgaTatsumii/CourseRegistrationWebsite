<%@page import="model.Lop"%>
<%@page import="model.Nganh"%>
<%@page import="model.KhoaHoc"%>
<%@page import="model.SinhVien"%>
<%@page import="java.util.ArrayList"%>
<%@page import=" java.time.format.DateTimeFormatter" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Sinh viên lớp học</title>
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
				<%
				if (title == null) {
				%>
				<h3 id="title_infor">Danh sách sinh viên</h3>
				<%
				} else {
				%>
				<h3 id="title_infor"><%=title%></h3>
				<%
				}
				%>
				<a class="back" href="classManage"> <i
						class='bx bx-left-arrow-alt' style="color:red;margin-left:95%"></i></a>
				<div class="main-content">
					<div class="studentManageContainer">
						<div class="studentManageContent">
							
							
							<div class="studentList">
								<div style="display: flex" class="studentHeader">
									<h2 class="title">Bảng dữ liệu</h2>
								</div>
								<table class="table">
									<thead
										style="background-color: rgb(238, 70, 70); color: white; font-size: 20px; border-color: black;">
										<tr>
											<th style="width: 5%">STT</th>
											<th style="width: 12%">Mã sinh viên</th>
											<th style="width: 16%">Họ tên</th>
											<th style="width: 10%">Ngày sinh</th>
											<th style="width: 9%">Giới tính</th>
											<th style="width: 11%">Lớp</th>
											<th style="width: 10%">SĐT</th>
											<th style="width: 10%">Tỉnh</th>
										</tr>
									</thead>
									<tbody>
										<%
										ArrayList<SinhVien> dsSV = (ArrayList<SinhVien>) session.getAttribute("dsSV");
										if (dsSV!=null){
											int dem=1;
											for (SinhVien sv:dsSV) {
										%>
										<tr>
											<td><%=dem%></td>
											<td><%=sv.getMaSinhVien() %></td>
											<td><%=sv.getHoTen() %></td>
											<td style="white-space: normal; word-wrap: break-word; overflow-wrap: break-word;"><%=sv.getNgaySinh()%></td>
											<td><%=sv.getGioiTinh()%></td>
											<td><%=sv.getLop().getMaLop()%></td>
											<td><%=sv.getSoDienThoai()%></td>
											<td><%=sv.getQueQuan().getTenTinh()%></td>
										</tr>
										<%
										dem+=1;}
											} %>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>

<script src="handleJs/confirmDeleteScript1.js"></script> 

