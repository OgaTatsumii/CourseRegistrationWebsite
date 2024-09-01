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
String errorMessageMaGiangVien = (String) request.getAttribute("errorMessageMaGiangVien");
if (errorMessageMaGiangVien == null) {
	errorMessageMaGiangVien = "";
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

String maGiangVien = (String) request.getAttribute("MaGiangVien");
String hoTen = (String) request.getAttribute("HoTen");
String ngaySinhStr = (String) request.getAttribute("NgaySinh");
String gioiTinh = (String) request.getAttribute("GioiTinh");
String soDienThoai = (String) request.getAttribute("SDT");
String cccd = (String) request.getAttribute("CCCD");
String email = (String) request.getAttribute("Email");
String maLop = (String) request.getAttribute("MaLop");
String maTinh = (String) request.getAttribute("MaTinh");

maGiangVien = (maGiangVien == null) ? "" : maGiangVien;
hoTen = (hoTen == null) ? "" : hoTen;
ngaySinhStr = (ngaySinhStr == null) ? "" : ngaySinhStr;
gioiTinh = (gioiTinh == null) ? "" : gioiTinh;
soDienThoai = (soDienThoai == null) ? "" : soDienThoai;
cccd = (cccd == null) ? "" : cccd;
email = (email == null) ? "" : email;
maLop = (maLop == null) ? "" : maLop;
maTinh = (maTinh == null) ? "" : maTinh;
%>
<body>
	<div class="adminArea">
		<!-- Include DashBoard -->
		<%@ include file="/layout/dashBoard.jsp"%>
		
		<div class="content">
			<!-- Include NavBar -->
			<jsp:include page="/layout/navbar.jsp" />
			<div class="main">
				<div class="addStudentTitle" style=" display: flex;">
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
				<a class = "back" href="lecturerManagement"> <i class='bx bx-left-arrow-alt'></i></a>
				</div>
				<div id="toast">
                        
                    </div>
				<div class="main-content">
					<div class="form-container">
						<form id="formAddSinhVien" action="lecturerManagement" method="POST">
						<input type="hidden" name="action" value="add">
							<div class="form-content">
								<h2>Thông tin cơ bản</h2>
								<div class="form-group">
									<div class="idStudentContent">
										<label for="MaGiangVien">Mã Giảng Viên :</label> <input
											type="text" id="MaGiangVien" name="MaGiangVien"
											value="<%=maGiangVien%>" required />
										<p class="error">
											<%=errorMessageMaGiangVien%>
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
											id="NgaySinh" name="NgaySinh" value="<%=ngaySinhStr%>"
											required />
										<p class="error"></p>
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
									<div class="provinceContent">
										<label for="MaTinh">Mã Tỉnh :</label> <select id="MaTinh"
											name="MaTinh">
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
										</select>
									</div>
									
								</div>
								<button class="addStudent" type="submit">Thêm Giảng Viên</button>
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




