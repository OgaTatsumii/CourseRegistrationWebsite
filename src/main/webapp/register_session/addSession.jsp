<%@page import="java.text.Format"%>
<%@page import="model.Khoa"%>
<%@page import="model.Lop"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.NhanVien"%>
<%@page import="model.CTNamHoc_HocKy"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Tạo phiên</title>
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
Date date = new Date();
SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

String ngayBatDau=(String) session.getAttribute("ngayBatDau");
ngayBatDau= (ngayBatDau==null) ? dateFormat.format(date):ngayBatDau;


String ngayKetThuc=(String) session.getAttribute("ngayKetThuc");
 ngayKetThuc= (ngayKetThuc==null) ? dateFormat.format(date):ngayKetThuc;
 
 String maKhoa=(String) session.getAttribute("maKhoa");
 maKhoa= (maKhoa==null) ? "":maKhoa;
 
 
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
					<h3 id="title_infor">Tạo phiên đăng ký</h3>
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
						<input type="hidden" name="action" value="taoPhien">
							<div class="form-content">
								<h2>Thông tin phiên</h2>
								<div class="form-group">
									<div class="">
										<label for=""> Khoa :</label> <input type="text" id="search"
											list="options" name="maKhoa">
										<datalist id="options" for="">
											<option value="<%=maKhoa%>"><%=maKhoa%></option>
											<%
											ArrayList<Khoa> dsKhoa = (ArrayList<Khoa>) session.getAttribute("dsKhoa");
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
								</div>
								<div class="form-group">
									<div class="yearContent">
										<label for="NgaySinh">Ngày đăng ký :</label> <input
											value="<%=ngayBatDau%>" type="date" id="NgaySinh" name="ngayBatDau" required />
									</div>
									<div class="phoneContent">
										<label for="SDT">Thời gian đăng ký :</label> <input
											type="time" step="1" style="width: 50%; height: 55%;"
											id="SDT" name="tgBatDau" required />
									</div>
								</div>
								<div class="form-group">
									<div class="yearContent">
										<label for="NgaySinh">Ngày kết thúc :</label> <input
											value="<%=ngayKetThuc%>" type="date" id="NgaySinh" name="ngayKetThuc" required />
									</div>
									<div class="phoneContent">
										<label for="SDT">Thời gian kết thúc :</label> <input
											type="time" step="1" style="width: 50%; height: 55%;"
											id="SDT" name="tgKetThuc" required />
									</div>
								</div>
								<div class="form-group">
									<div class="yearContent">
										<label for="NgaySinh">Chọn năm học và học kỳ :</label>
										<select name="maNHHocKy" style="width:300px">
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
							<button class="addStudent" type="submit">Tạo phiên</button>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>

<script>

function toast(){
	const main = document.getElementById('toast');
    if(main){
        const toast = document.createElement('div');
        const icons = {
            success: 'bxs-check-circle',
            error: 'bxs-error'
        }

        toast.classList.add('toast',`toast--${type}`);
        toast.innerHTML = `
        <div class="toast__icon">
            <i class='bx ${icon}' ></i>
        </div>
        <div class="toast__body">
            <h3 class="toast__title">${titleToast}</h3>
            <p class="toast__msg">
                ${messageToast}
            </p>
        </div>
        <div class="toast__close">
            <i class='bx bx-x bx-rotate-90' ></i>
        </div>                                
        `
        main.appendChild(toast);
    }
}

const messageToast = "<%= session.getAttribute("messageToast") %>";
if (messageToast != 'null') {
    toast();
}
<% session.removeAttribute("messageToast"); %>

</script>

<script src="handleJs/addStudentScript.js"></script>




