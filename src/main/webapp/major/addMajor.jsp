<%@page import="java.util.ArrayList"%>
<%@page import="model.Khoa"%>
<%@page import="model.Nganh"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Admin</title>
<link rel="stylesheet" href="css/style009.css" />
<link rel="stylesheet" href="css/reset.css" />
<link rel="preconnect" href="https://fonts.googleapis.com" />
<link rel="preconnect" href="https://fonts.gstatic.com"  />
<link
	href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;500;600&family=Poppins:wght@300;400;500;600&family=Sora:wght@600;700;800&display=swap"
	rel="stylesheet" />
<link href="https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css"
	rel="stylesheet" />

</head>
<%

Nganh Nganh = (Nganh) request.getAttribute("Nganh");
String errorMessageMaNganh = (String) request.getAttribute("errorMessageMaNganh");
if (errorMessageMaNganh == null) {
	errorMessageMaNganh = "";
}
String errorMessageTenNganh = (String) request.getAttribute("errorMessageTenNganh");
if (errorMessageTenNganh == null) {
	errorMessageTenNganh = "";
}

String maNganh = (String) request.getAttribute("MaNganh");
String tenNganh = (String) request.getAttribute("TenNganh");
String giaMotTinChi = String.valueOf(request.getAttribute("GiaMotTinChi"));
String soTinChiNganh = String.valueOf(request.getAttribute("SoTinChiNganh"));
String thoiGianDaoTao = String.valueOf(request.getAttribute("ThoiGianDaoTao"));
String maKhoa = (String) request.getAttribute("MaKhoa");

maNganh = (maNganh == null) ? "" : maNganh;
tenNganh = (tenNganh == null) ? "" : tenNganh;
giaMotTinChi = (giaMotTinChi==null) ? "" : giaMotTinChi;
soTinChiNganh =(soTinChiNganh==null) ? "" : soTinChiNganh;
thoiGianDaoTao = (thoiGianDaoTao== "null") ? "" : thoiGianDaoTao;
maKhoa = (maKhoa==null) ? "" : maKhoa;
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
					<a class="back" href="MajorManagement"> <i
						class='bx bx-left-arrow-alt'></i></a>
					</div>
					<div id="toast"></div>
						<div class="main-content">
						<div class="form-container">
						<form id="formAddSinhVien" action="MajorManagement" method="POST">
						<input type="hidden" name="action" value="add">
						<div class="form-content">
							<h2>Thông tin ngành học</h2>
							<div class="form-group">
									<div class="idNganh">
										<label for="MaNganh">Mã Ngành :</label> <input
											style="width: 90%" type="text" id="MaNganh" name="MaNganh"
											value="<%=maNganh%>"
											required />
 										<p class="error">
											<%=errorMessageMaNganh%>
										</p> 
									</div>
								</div>
								<div class="form-group">
									<div class="tenNganh">
										<label for="TenNganh">Tên Ngành :</label> <input
											style="width: 90%" type="text" id="TenNganh" name="TenNganh"
											value="<%=tenNganh%>"
											required />
										<p class="error">
											<%=errorMessageTenNganh%>
										</p>
									</div>
								</div>
								<div class="form-group">
									<div class="gia1tinchi">
										<label for="GiaMotTinChi">Giá một tín chỉ :</label> <input
											style="width: 90%" type="number" id="GiaMotTinChi" name="GiaMotTinChi"
											value="<%=giaMotTinChi%>"
											min="100000" required />
									</div>
								</div>
								<div class="form-group">
									<div class="SoTinChiNganh">
										<label for="SoTinChiNganh">Tổng số tín chỉ :</label> <input
											style="width: 90%" type="number" id="SoTinChiNganh" name="SoTinChiNganh"
											min="1" value="<%=soTinChiNganh%>"
											required />
									</div>
								</div>
								<div class="form-group">
									<div class="thoiGianDaoTao">
										<label for="ThoiGianDaoTao">Thời gian đào tạo :</label> <input
											style="width: 90%" type="text" id="ThoiGianDaoTao" name="ThoiGianDaoTao"
											 value="<%=thoiGianDaoTao%>"
											required />
									</div>
								</div>
								<div class="form-group">
									<div class="maKhoa">
										<label for="MaKhoa">Khoa :</label> <select id="MaKhoa" name="MaKhoa" required>
											<option value=""> </option>
											<%
											ArrayList<Khoa> danhSachKhoas = (ArrayList<Khoa>) session.getAttribute("dsKhoa");
											if (danhSachKhoas != null) {
												for (Khoa Khoa : danhSachKhoas) {
											%>
											<option value="<%=Khoa.getMaKhoa()%>"><%=Khoa.getTenKhoa()%></option>
											<%
											}
											}
											%>
											</select>
										
									</div>
								</div>
								
							</div>
							<button class="addStudent" type="submit">Thêm Ngành Học</button>
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

const messageToast = "<%=session.getAttribute("messageToast")%>";
if (messageToast != 'null') {
    toast();
}
<%session.removeAttribute("messageToast");%>

</script>

<script src="handleJs/addStudentScript.js"></script>