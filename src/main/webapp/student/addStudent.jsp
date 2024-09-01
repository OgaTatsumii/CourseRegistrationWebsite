<%@page import="java.time.LocalDate"%>
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
String errorMessageMaSinhVien = (String) request.getAttribute("errorMessageMaSinhVien");
if (errorMessageMaSinhVien == null) {
	errorMessageMaSinhVien = "";
}
String errorMessageHoTen = (String) request.getAttribute("errorMessageHoTen");
if (errorMessageHoTen == null) {
	errorMessageHoTen = "";
}

String errorMessageDate = (String) request.getAttribute("errorMessageDate");
if (errorMessageDate == null) {
	errorMessageDate = "";
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
				<div id="toast"></div>
				<div class="main-content">
					<div class="form-container">
						<form id="formAddSinhVien" action="addStudent" method="POST">
							<div class="form-content">
								<h2>Thông tin cơ bản</h2>
								<div class="form-group">
									<div class="idStudentContent">
										<label for="MaSinhVien">Mã Sinh Viên :</label> <input
											type="text" id="MaSinhVien" name="MaSinhVien"
											value="<%=maSinhVien%>" required />
										<p class="error">
											<%=errorMessageMaSinhVien%>
										</p>
									</div>
									<div class="nameContent">
										<label for="HoTen">Họ Tên :</label> <input type="text"
											id="HoTen" name="HoTen" value="<%=hoTen%>" required />
										<p class="error">
											<%=errorMessageHoTen%>
										</p>
									</div>
								</div>
								<div class="form-group">
									<div class="yearContent">
										<label for="NgaySinh">Ngày Sinh :</label> <input type="date"
											id="NgaySinh" name="NgaySinh" min="1900-01-01" max="<%=java.time.LocalDate.now() %>>" value="<%=ngaySinhStr%>"
											required />
										<p class="error">
											<%=errorMessageDate %>
										</p>
									</div>
									<div class="sexContent">
										<label for="GioiTinh">Giới Tính :</label> <select
											id="GioiTinh" name="GioiTinh">
											<option value="Nam">Nam</option>
											<option value="Nữ">Nữ</option>
											<option value="Khác">Khác</option>
										</select>
									</div>
								</div>
								<div class="form-group">
									<div class="phoneContent">
										<label for="SDT">Số Điện Thoại :</label> <input type="text"
											id="SDT" name="SDT" value="<%=soDienThoai%>" required />
										<p class="error">
											<%=errorMessageSoDienThoai%>
										</p>
									</div>
									<div class="idContent">
										<label for="CCCD">CCCD/CMND :</label> <input type="text"
											id="CCCD" name="CCCD" value="<%=cccd%>" required />
										<p class="error">
											<%=errorMessageCCCD%>
										</p>
									</div>
								</div>

								<div class="form-group">
									<div class="emailContent">
										<label for="Email">Email :</label> <input type="email"
											id="Email" name="Email" value="<%=email%>" required />
										<p class="error">
											<%=errorMessageEmail%>
										</p>
									</div>
								</div>
								<div class="form-group">
									<div class="placeContent">
										<label for="TamTru">Tạm Trú :</label> <input type="text"
											id="TamTru" name="TamTru" value="<%=tamTru%>" required />
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
											<option value="<%=lop.getMaLop()%>"
											<%= maLop.equals(lop.getMaLop()) ? "selected" : "" %>>
											<%=lop.getMaLop()%>
											</option>
											<%
											}
											}
											%>
										</select>
									</div>
									<div class="provinceContent">
										<label for="MaTinh">Mã Tỉnh :</label> 
										<input list="MaTinh" name="MaTinh" type="text" value="<%=maTinh%>">
										<datalist id="MaTinh">
											<%
											ArrayList<QueQuan> danhSachQueQuans = (ArrayList<QueQuan>) session.getAttribute("dsQueQuan");
											if (danhSachQueQuans != null) {
												for (QueQuan queQuan : danhSachQueQuans) {
											%>
											<option value="<%=queQuan.getMaTinh()%>"><%=queQuan.getTenTinh()%></option>
											<%
											}
											}
											%>
										</datalist>
									</div>
								</div>
								<hr style="color: red; border: 2px solid red; margin: 35px 0;" />
								<h2>Thông tin tài khoản</h2>
								<div class="form-group">
									<div class="userContent">
										<label for="User">Tài khoản :</label> <input type="text"
											id="User" name="User" value="<%=taiKhoan%>" required />
									</div>
								</div>
								<div class="form-group">
									<div class="passWordContent">
										<label for="Password">Mật khẩu:</label> <input type="password"
											id="Password" name="Password" value="<%=matKhau%>" required />
										<span id="togglePassword" style="cursor: pointer;"> <i
											class='bx bx-low-vision'></i>
										</span>
									</div>
								</div>
							</div>
							<button class="addStudent" type="submit">Thêm Sinh Viên</button>
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
        setTimeout(() => {
            main.removeChild(toast);
        }, 3000);
    }
}

const messageToast = "<%=session.getAttribute("messageToast")%>";
if (messageToast != 'null') {
    toast();
}
<%session.removeAttribute("messageToast");%>

</script>

<script src="${pageContext.request.contextPath}/handleJs/addStudentScript.js"></script>




