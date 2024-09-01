<%@page import="model.Nganh"%>
<%@page import="model.KhoaHoc"%>
<%@page import="model.LoaiThongBao"%>
<%@page import="model.NoiDungThongBao"%>
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
NoiDungThongBao tb = (NoiDungThongBao) session.getAttribute("tb");
String maThongBao = (String) session.getAttribute("maThongBao");
ArrayList<LoaiThongBao> dsLTB=(ArrayList<LoaiThongBao>) session.getAttribute("dsLTB");
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
					<h3 id="title_infor">Sửa thông báo</h3>
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
							<input type="hidden" name="action" value="suaThongBao">
							<input type="hidden" name="maThongBao" value="<%=maThongBao%>">
							<div class="form-content">
								<h2>
									Sửa thông báo
									<%=tb.getLtb().getTenLoaiThongBao()%></h2>
								<div class="form-group">
									<div class="">
										<label for="" style="font-weight: bold">Tiêu đề</label>
										<textarea
											style="height: 150px; padding: 10px; box-sizing: border-box; border-radius: 10px; font-size: 17px"
											rows="7" cols="30" name="tieuDe"><%=tb.getTieuDe() %></textarea>
									</div>
									<div class="">
										<label style="font-weight: bold" for="">Nội dung</label>
										<textarea
											style="height: 150px; padding: 10px; box-sizing: border-box; border-radius: 10px; font-size: 17px; width: 100%"
											rows="7" cols="75" name="noiDung"><%=tb.getNoiDung() %></textarea>
									</div>
								</div>
								<div class="form-group">
									<div class="">
										<label for="">Chọn lại loại thông báo :</label> <input type="text" id="search"
											list="op1" name="loaiThongBao">
										<datalist id="op1" for="">
											<%
											if (dsLTB != null) {
												for (LoaiThongBao ltb : dsLTB) {
											%>
											<option value="<%=ltb.getMaLoaiThongBao()%>"><%=ltb.getTenLoaiThongBao()%></option>
											<%
											}
											}
											%>
										</datalist>
									</div>
									</div>
								<hr style="color: red; border: 2px solid red; margin: 35px 0;" />
							</div>
							<button class="" type="submit">Sửa</button>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>


<script src="handleJs/addStudentScript.js"></script>




