<%@page import="model.GiangVien"%>
<%@page import="model.MonHoc"%>
<%@page import="model.QueQuan"%>
<%@page import="model.NamHoc"%>
<%@page import="model.HocKyNH"%>
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
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/style009.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/reset.css" />
<link rel="preconnect" href="https://fonts.googleapis.com" />
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
<link
	href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;500;600&family=Poppins:wght@300;400;500;600&family=Sora:wght@600;700;800&display=swap"
	rel="stylesheet" />
<link href="https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css"
	rel="stylesheet" />

</head>

<%
String errorMessageMaLTC = (String) request.getAttribute("errorMessageMaLTC");
errorMessageMaLTC = (errorMessageMaLTC == null) ? "" : errorMessageMaLTC;

String errorMessagePhong = (String) request.getAttribute("errorMessagePhong");
errorMessagePhong = (errorMessagePhong == null) ? "" : errorMessagePhong;

String errorMessageNgay = (String) request.getAttribute("errorMessageNgay");
errorMessageNgay = (errorMessageNgay == null) ? "" : errorMessageNgay;

String errorMessageTiet = (String) request.getAttribute("errorMessageTiet");
errorMessageTiet = (errorMessageTiet == null) ? "" : errorMessageTiet;

String maLopTinChi = (String) request.getAttribute("maLopTinChi");
String maMonHoc = (String) request.getAttribute("maMonHoc");
String maGiangVien = (String) request.getAttribute("maGiangVien");
String maNamHoc = (String) request.getAttribute("maNamHoc");
String maHocKy = (String) request.getAttribute("maHocKy");
String tietBatDau = (String) request.getAttribute("tietBatDau");
String tietKetThuc = (String) request.getAttribute("tietKetThuc");
String ngayBatDau = (String) request.getAttribute("ngayBatDau");
String ngayKetThuc = (String) request.getAttribute("ngayKetThuc");
String thu = (String) request.getAttribute("thu");
String phong = (String) request.getAttribute("phong");
String soLuongSinhVien = String.valueOf(request.getAttribute("soLuongSinhVien"));

maLopTinChi = (maLopTinChi == null) ? "" : maLopTinChi;
maMonHoc = (maMonHoc == null) ? "" : maMonHoc;
maGiangVien = (maGiangVien == null) ? "" : maGiangVien;
maNamHoc = (maNamHoc == null) ? "" : maNamHoc;
maHocKy = (maHocKy == null) ? "" : maHocKy;
tietBatDau = (tietBatDau == null) ? "" : tietBatDau;
tietKetThuc = (tietKetThuc == null) ? "" : tietKetThuc;
ngayBatDau = (ngayBatDau == null) ? "" : ngayBatDau;
ngayKetThuc = (ngayKetThuc == null) ? "" : ngayKetThuc;
thu = (thu == null) ? "" : thu;
phong = (phong == null) ? "" : phong;
soLuongSinhVien = (soLuongSinhVien == null) ? "" : soLuongSinhVien;
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
					<a class="back" href="/DoAnDKHP_Nhom14/creditCourseManage"> <i
						class='bx bx-left-arrow-alt'></i></a>
				</div>
				<div id="toast"></div>
				<div class="main-content">
					<div class="form-container">
						<form id="formAddSinhVien" action="creditCourseManage" method="POST">
						<input type="hidden" name="action" value = "addCreditCourse" />
							<div class="form-content">
								<h2>Thông tin lớp học</h2>
								<div class="form-group">
									<div class="idCreditCourseContent">
										<label for="MaLopTinChi">Mã Lớp Tín Chỉ :</label> <input
											type="text" id="MaLopTinChi" name="MaLopTinChi"
											value="<%=maLopTinChi%>" required />
										<p class="error">
											<%=errorMessageMaLTC%>
										</p>
									</div>
									<div class="idSubjectContent">
										<label for="MaMonHoc">Mã Môn Học :</label> <input
											list="MaMonHoc" name="MaMonHoc" type="text"
											value="<%=maMonHoc%>">
										<datalist id="MaMonHoc">
											<%
											ArrayList<MonHoc> danhSachMonHoc = (ArrayList<MonHoc>) session.getAttribute("dsMonHoc");
											if (danhSachMonHoc != null) {
												for (MonHoc monHoc : danhSachMonHoc) {
											%>
											<option value="<%=monHoc.getMaMonHoc()%>"><%=monHoc.getTenMonHoc()%></option>
											<%
											}
											}
											%>
										</datalist>
									</div>
								</div>
								<div class="form-group">
									<div class="idLecturersContent">
										<label for="MaGiangVien">Mã Giảng Viên :</label> <input
											list="MaGiangVien" name="MaGiangVien" type="text"
											value="<%=maGiangVien%>">
										<datalist id="MaGiangVien">
											<%
											ArrayList<GiangVien> danhSachGiangVien = (ArrayList<GiangVien>) session.getAttribute("dsGiangVien");
											if (danhSachGiangVien != null) {
												for (GiangVien giangVien : danhSachGiangVien) {
											%>
											<option value="<%=giangVien.getMaGiangVien()%>"><%=giangVien.getHoTen()%></option>
											<%
											}
											}
											%>
										</datalist>
									</div>
								</div>

								<div class="form-group">
									<div class="startDayContent">
										<label for="NgayBatDau">Ngày bắt đầu :</label> <input
											type="date" id="NgayBatDau" name="NgayBatDau"
											value="<%=ngayBatDau%>" min="<%=java.time.LocalDate.now()%>>"
											required />
										<p class="error">
											<%=errorMessageNgay%>
										</p>
									</div>

									<div class="endDayContent">
										<label for="NgayKetThuc">Ngày kết thúc :</label> <input
											type="date" id="NgayKetThuc" name="NgayKetThuc"
											value="<%=ngayKetThuc%>" required />
									</div>
								</div>
								<div class="form-group">
									<div class="scheduleContent">
										<label for="Thu">Thứ :</label> <select id="Thu" name="Thu"
											style="height: 44%;">
											<%
											for (int i = 2; i < 8; i += 1) {
											%>
											<option value="Thứ <%=i%>"
												<%=thu.equals("Thứ " + i) ? "selected" : ""%>>Thứ
												<%=i%>
											</option>
											<%
											}
											%>
										</select>
									</div>
									<div class="roomContent">
										<label for="Phong">Phòng :</label> <input type="text"
											id="Phong" name="Phong" value="<%=phong%>" required />
										<p class="error">
											<%=errorMessagePhong%>
										</p>
									</div>
								</div>
								<div class="form-group">
									<div class="timeBegin">
										<label for="timeBegin">Tiết bắt đầu</label> <input
											type="number" id="timeBegin" name="TietBatDau"
											value="<%=tietBatDau%>" min=0 max=15
											style="margin-right: 27px;" required />
										<p class="error">
											<%=errorMessageTiet%>
										</p>
									</div>
									<div class="timeEnd">
										<label for="timeEnd">Tiết kết thúc</label> <input
											type="number" id="timeEnd" name="TietKetThuc"
											value="<%=tietKetThuc%>" min=0 max=15 required />
									</div>
								</div>
								<!-- update code by Thiện -->
								<div class="form-group">
									<div class="" style="width: 250px">
										<label for="">Năm học :</label> <select id="" name="MaNamHoc">
											<%
											ArrayList<NamHoc> dsNamHoc = (ArrayList<NamHoc>) session.getAttribute("dsNamHoc");
											if (dsNamHoc != null) {
												for (NamHoc nh : dsNamHoc) {
											%>
											<option value="<%=nh.getMaNamHoc()%>"
												<%=maNamHoc.equals(nh.getMaNamHoc()) ? "selected" : ""%>><%=nh.getTenNamHoc()%></option>
											<%
											}
											}
											%>
										</select>
									</div>
									<div class="">
										<label for="">Học kỳ :</label> <select id="" name="MaHocKy">
											<%
											ArrayList<HocKyNH> dsHocKyNH = (ArrayList<HocKyNH>) session.getAttribute("dsHocKyNH");
											if (dsHocKyNH != null) {
												for (HocKyNH hknh : dsHocKyNH) {
											%>
											<option value="<%=hknh.getMaHocKy()%>" <%=maHocKy.equals(hknh.getMaHocKy()) ? "selected" : ""%> ><%=hknh.getTenHocKy()%></option>
											<%
											}
											}
											%>
										</select>
									</div>
								</div>
								<!--  -->
								<div class="form-group">
									<div class="studentQuatityContent">
										<label for="SoLuongSinhVien">Số lượng sinh viên :</label> <input
											type="number" id="SoLuongSinhVien" name="SoLuongSinhVien"
											value="<%=soLuongSinhVien%>" min=0 max=100 required />
									</div>
								</div>
							</div>
							<button class="addStudent" type="submit">Thêm Lớp Tín
								Chỉ</button>
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

<script
	src="${pageContext.request.contextPath}/handleJs/addStudentScript.js"></script>




