<%@page import="model.GiangVien"%>
<%@page import="model.MonHoc"%>
<%@page import="model.QueQuan"%>
<%@page import="model.NamHoc"%>
<%@page import="model.HocKyNH"%>
<%@page import="model.LopTinChi"%>
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
/* String errorMessageMaLTC = (String) request.getAttribute("errorMessageMaLTC");
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
String soLuongSinhVien = String.valueOf(request.getAttribute("soLuongSinhVien")); */

LopTinChi ltc=(LopTinChi) session.getAttribute("ltc");
String error=(String) session.getAttribute("error");
error=(error==null)?" ":error;
/* maLopTinChi = (maLopTinChi == null) ? "" : maLopTinChi;
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
soLuongSinhVien = (soLuongSinhVien == null) ? "" : soLuongSinhVien; */
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
						<form id="formAddSinhVien" action="updateCreditCourse" method="POST">
						<input type="hidden" name="action" value="updateCreditCourse">
						<input type="hidden" name="maLTC" value="<%=ltc.getMaLopTinChi()%>">
							<div class="form-content">
								<h2>Thông tin lớp học</h2>
								<div class="form-group">
									<div class="idCreditCourseContent">
										<label for="">Mã Lớp Tín Chỉ :</label> <select>
											<option value="<%=ltc.getMaLopTinChi()%>"><%=ltc.getMaLopTinChi()%></option>
										</select>
									</div>
								</div>
								<div class="form-group">
									<div class="idSubjectContent">
										<label for="">Mã Môn Học Cũ:</label> <select name=""id="" style="width:300px">
											<option value="<%=ltc.getMonHoc().getMaMonHoc()%>"><%=ltc.getMonHoc().getTenMonHoc()%></option>
										</select>
									</div>
									<div class="idSubjectContent">
										<label for="MaMonHoc" style="margin-left:100px">Mã Môn Học Mới:</label> <select name="MaMonHoc"id="" style="margin-left:100px;width:300px">
											<option value="<%=ltc.getMonHoc().getMaMonHoc()%>"><%=ltc.getMonHoc().getTenMonHoc()%></option>
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
										</select>
									</div>
								</div>

								<div class="form-group">
									<div class="idCreditCourseContent">
										<label for="" >Mã Giảng viên cũ :</label> <select style="width:300px">
											<option value="<%=ltc.getGiangVien().getMaGiangVien()%>"><%=ltc.getGiangVien().getHoTen()%></option>
										</select>
									</div>
									<div class="idLecturersContent">
										<label for="MaGiangVien" style="margin-left:100px;">Mã Giảng Viên Mới :</label> <input style="margin-left:100px"
											list="MaGiangVien" name="MaGiangVien" type="text"
											value="<%=ltc.getGiangVien().getMaGiangVien()%>">
										<datalist id="MaGiangVien" style="width:300px">
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
									<div class="endDayContent">
										<label for="NgayKetThuc">Ngày bắt đầu cũ:</label> <select style="width:300px">
											<option value="<%=ltc.getNgayBatDau()%>"><%=ltc.getNgayBatDau()%></option>
										</select>
									</div>
									<div class="startDayContent" style="margin-right:200px">
										<label for="NgayBatDau" style="margin-left:100px">Ngày bắt đầu mới:</label> <input
											type="date" id="NgayBatDau" name="NgayBatDau"
											value="<%=ltc.getNgayBatDau()%>" style="width:300px;margin-left:100px"/>
									</div>
								</div>
								<div class="form-group">
									<div class="endDayContent">
										<label for="NgayKetThuc">Ngày kết thúc cũ:</label> <select style="width:300px">
											<option value="<%=ltc.getNgayKetThuc()%>"><%=ltc.getNgayKetThuc()%></option>
										</select>
										
									</div>
									<div class="endDayContent">
										<label for="NgayKetThuc" style="margin-left:100px">Ngày kết thúc mới:</label> <input
											type="date" id="NgayKetThuc" name="NgayKetThuc"
											value="<%=ltc.getNgayKetThuc()%>" style="width:300px;margin-left:100px" />
											<p style="font-size:16px;color:red;margin-left:50px"><%=error %></p>
											
									</div>
								</div>
								<div class="form-group">
									<div class="scheduleContent">
										<label for="Thu">Thứ cũ :</label> <select id="Thu" name=""
											style="height: 50%;">
											<option><%=ltc.getThu() %></option>
										</select>
									</div>
									<div class="scheduleContent">
										<label for="Thu" style="margin-left:70px">Thứ mới :</label> 
										<select id="Thu" name="Thu"
											style="height: 50%;margin-left:70px">
											<option value="2">2</option>
											<option value="3">3</option>
											<option value="4">4</option>
											<option value="5">5</option>
											<option value="6">6</option>
											<option value="7">7</option>
										</select>
									</div>
								</div>
								<div class="form-group">
									<div class="roomContent" style="margin:0">
										<label for="Phong">Phòng cũ:</label> <select style="width:150px">
											<option><%=ltc.getPhong() %></option>
										</select>
									</div>
									<div class="roomContent" style="margin:0;margin-left:250px">
										<label for="Phong">Phòng mới:</label> <input type="text"
											id="Phong" name="Phong" value="<%=ltc.getPhong()%>" required />
									</div>
								</div>
								<div class="form-group">
									<div class="timeBegin">
										<label for="timeBegin">Tiết BĐ cũ</label> <input
											type="number" id="timeBegin" name=""
											value="<%=ltc.getTietBatDau()%>" min=0 max=15
											style="margin-right:20px" required />
									</div>
									<div class="timeBegin">
										<label for="timeBegin">Tiết BĐ mới</label> <input
											type="number" id="timeBegin" name="TietBatDau"
											value="<%=ltc.getTietBatDau()%>" min=0 max=15
											style="margin-right:20px" required />
									</div>
									<div class="timeEnd">
										<label for="timeEnd">Tiết KT cũ</label> <input
											type="number" id="timeEnd" name=""
											value="<%=ltc.getTietKetThuc()%>" min=0 max=15 required style="margin-right:20px"/>
									</div>
									<div class="timeEnd">
										<label for="timeEnd">Tiết KT mới</label> <input
											type="number" id="timeEnd" name="TietKetThuc"
											value="<%=ltc.getTietKetThuc()%>" min=0 max=15 required style="margin-right:20px"/>
									</div>
								</div>
								<!-- update code by Thiện -->
								<div class="form-group">
									<div class="" style="width: 250px">
										<label for="">Năm học cũ:</label> <select id="" name="">
											<option value="<%=ltc.getCtNamHoc_HocKy().getnamHoc().getMaNamHoc()%>">
												<%=ltc.getCtNamHoc_HocKy().getnamHoc().getMaNamHoc()%></option>
										</select>
									</div>
									<div class="" style="width: 250px;margin-right:30px">
										<label for="">Năm học mới:</label> <select id="" name="MaNamHoc">
										<option value="<%=ltc.getCtNamHoc_HocKy().getnamHoc().getMaNamHoc()%>"><%=ltc.getCtNamHoc_HocKy().getnamHoc().getTenNamHoc() %></option>
											<%
											ArrayList<NamHoc> dsNamHoc = (ArrayList<NamHoc>) session.getAttribute("dsNamHoc");
											if (dsNamHoc != null) {
												for (NamHoc nh : dsNamHoc) {
											%>
											<option value="<%=nh.getMaNamHoc()%>"><%=nh.getTenNamHoc() %></option>
											<%
											}
											}
											%>
										</select>
									</div>
									<div class="">
										<label for="">Học kỳ cũ :</label> <select id="" name="">
											
											<option value="<%=ltc.getCtNamHoc_HocKy().gethocKy().getMaHocKy()%>">
												<%=ltc.getCtNamHoc_HocKy().gethocKy().getTenHocKy()%></option>
											
										</select>
									</div>
									<div class="">
										<label for="">Học kỳ mới :</label> <select id="" name="MaHocKy">
											<%
											ArrayList<HocKyNH> dsHocKyNH = (ArrayList<HocKyNH>) session.getAttribute("dsHocKyNH");
											if (dsHocKyNH != null) {
												for (HocKyNH hknh : dsHocKyNH) {
											%>
											<option value="<%=hknh.getMaHocKy()%>"><%=hknh.getTenHocKy()%></option>
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
										<label for="SoLuongSinhVien">Số lượng sinh viên cũ:</label> <input
											type="number" id="SoLuongSinhVien" style="width:400px;margin:0"name=""
											value="<%=ltc.getSoLuongSinhVien()%>" min=0 max=100 required />
									</div>
									<div class="studentQuatityContent">
										<label for="SoLuongSinhVien">Số lượng sinh viên mới:</label> <input
											type="number" id="SoLuongSinhVien" style="width:400px" name="SoLuongSinhVien"
											value="<%=ltc.getSoLuongSinhVien()%>" min=0 max=100 required />
									</div>
								</div>
							</div>
							<button class="addStudent" type="submit">Cập nhật Tín Chỉ</button>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
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
</html>






