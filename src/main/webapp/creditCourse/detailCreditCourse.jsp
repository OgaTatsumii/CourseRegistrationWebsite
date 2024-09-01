<%@page import="model.CTLopTinChi"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="model.LopTinChi"%>
<%@page import="model.KhoaHoc"%>
<%@page import="model.SinhVien"%>
<%@page import="model.Lop"%>
<%@page import="model.Nganh"%>
<%@page import="model.Khoa"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Document</title>
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
LopTinChi ctLopTinChi = (LopTinChi) session.getAttribute("ctLopTinChi");
DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
%>

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
				<h3 id="title_infor">TRANG CHỦ</h3>
				<%
				} else {
				%>
				<h3 id="title_infor"><%=title%></h3>
				<%
				}
				%>
				<a class="back_detail_creditcourse"
					href="/DoAnDKHP_Nhom14/creditCourseManage"> <i
					class='bx bx-left-arrow-alt'></i></a>
				<div id="toast"></div>
				<div class="main-content">
					<div class="CreditCourseContainer">
						<div class="CreditCourseManageContent">
							<h2 class="title">Thông tin lớp tín chỉ</h2>
							<div class="basicInfo">
								<div class="container">
									<h3 class="class_title">
										Mã lớp: <span class="class_content"><%=ctLopTinChi.getMaLopTinChi()%></span>
									</h3>
									<h3 class="subject_title">
										Môn học: <span class="subject_content"><%=ctLopTinChi.getMonHoc().getTenMonHoc()%></span>
									</h3>
								</div>
								<div class="container">
									<h3 class="begin_title">
										Thời gian: <span class="begin_content"><%=ctLopTinChi.getNgayBatDau().format(formatter)%></span>
									</h3>
									<h3 class="end_title">
										- <span class="end_content"><%=ctLopTinChi.getNgayKetThuc().format(formatter)%></span>
									</h3>
								</div>
								<div class="container">
									<h3 class="begintime_title">
										Tiết: <span class="begintime_content"><%=ctLopTinChi.getTietBatDau()%></span>
									</h3>
									<h3 class="endtime_title">
										- <span class="endtime_content"><%=ctLopTinChi.getTietKetThuc()%></span>
									</h3>
									<h3 class="room_title">
										Phòng: <span class="room_content"><%=ctLopTinChi.getPhong()%></span>
									</h3>
								</div>
								<h3 class="lecturers_title">
									Giảng viên: <span class="lecturers_content"><%=ctLopTinChi.getGiangVien().getHoTen()%></span>
								</h3>
								<h3 class="numberStudent_title">
									Số lượng sinh viên: <span class="numberStudent_content"><%=ctLopTinChi.getSoLuongSinhVien()%></span>
								</h3>
							</div>
						</div>
						<div class="CreditCourseStudentListManageContainer">
							<h2 class="title">Danh sách sinh viên của lớp</h2>
							<div class="studentList">
								<table class="table">
									<thead
										style="background-color: rgb(238, 70, 70); color: white; font-size: 20px; border-color: black;">
										<tr>
											<th style="width: 10%">STT</th>
											<th style="width: 23%">Mã sinh viên</th>
											<th style="width: 27%">Họ tên</th>
											<th style="width: 25%">Ngày sinh</th>
											<th style="width: 17%">Giới tính</th>
											<th style="width: 29%">Lớp</th>
											<th style="width: 25%">Email</th>
											<th style="width: 16%"></th>
											<th style="width: 16%"></th>

										</tr>
									</thead>
									<tbody>
										<%
										ArrayList<CTLopTinChi> dsCTLopTinChi = (ArrayList<CTLopTinChi>) session.getAttribute("dsCTLopTinChi");
										if (dsCTLopTinChi != null && !dsCTLopTinChi.isEmpty()) {
											for (int i = 0; i < dsCTLopTinChi.size(); i++) {
												CTLopTinChi ctLop = dsCTLopTinChi.get(i);
										%>
										<tr>
											<th scope="row"><%=i + 1%></th>
											<td><%=ctLop.getSv().getMaSinhVien()%></td>
											<td><%=ctLop.getSv().getHoTen()%></td>
											<td><%=ctLop.getSv().getNgaySinh()%></td>
											<td><%=ctLop.getSv().getGioiTinh()%></td>
											<td><%=ctLop.getSv().getLop().getMaLop()%></td>
											<td><%=ctLop.getSv().getEmail()%></td>
											<td><a
												href="upDateStudent?studentId=<%=ctLop.getSv().getMaSinhVien()%>"
												class="btn btn-update"
												data-studentId="<%=ctLop.getSv().getMaSinhVien()%>">Sửa</a></td>
											<td><a class="btn btn-danger deleteStudentButton"
												data-Id="<%=ctLop.getSv().getMaSinhVien()%>">Xóa</a></td>
										</tr>
										<%
										}
										} else {
										%>
										<tr>
											<td colspan="20"><h2 style="font-size: 18px;padding: 16px;">Hiện chưa có sinh viên nào đăng
													ký</h2></td>
										</tr>
										<%
										}
										%>
									</tbody>
								</table>
							</div>
						</div>
					</div>
					<div class="modal1">
						<div class="confirmDelete">
							<form name="delete-form" action="deleteStudent" method="POST">
								<input type="hidden" name="Id" value="" />
								<div class="desc">Thông báo</div>
								<div class="close2">
									<span class="icon-close"> <i class='bx bx-x'></i>
									</span>
								</div>
								<p>Xác nhận xóa đi một sinh viên ?</p>
								<div class="action">
									<button type="submit" id="btn-delete-user" class="btn">
										Đúng</button>
									<a class="btn denied"> Hủy </a>
								</div>
							</form>
						</div>
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

<script
	src="${pageContext.request.contextPath}/handleJs/confirmDeleteScript1.js"></script>


