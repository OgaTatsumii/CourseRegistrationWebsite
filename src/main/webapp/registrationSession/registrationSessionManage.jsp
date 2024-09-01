<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="model.PhienDangKy"%>
<%@page import="model.Khoa"%>
<%@page import="model.LopTinChi"%>
<%@page import="model.MonHoc"%>
<%@page import="model.HocKy"%>
<%@page import="model.Nganh"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Quản lý chương trình học</title>
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
DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm dd/MM/yyyy");
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
				<div id="toast"></div>
				<div class="main-content">
					<div class="CurriculumManageContainer">
						<div class="CurriculumManageContent">
							<form action="searchCurriculum" method="POST">
								<div class="searchCurriculum">
									<div style="display: flex">
										<div class="nganh">
											<label style="display: block" for="">Khoa</label> <select
												name="Khoa">
												<option value=""></option>
												<%
												ArrayList<Khoa> danhSachKhoa = (ArrayList<Khoa>) session.getAttribute("dsKhoa");
												if (danhSachKhoa != null) {
													for (Khoa khoa : danhSachKhoa) {
												%>
												<option value="<%=khoa.getMaKhoa()%>"><%=khoa.getTenKhoa()%></option>
												<%
												}
												}
												%>
											</select>
										</div>
										<div class="id">
										<label style="display: block" for="">Mã phiên</label> <input
											name="maPhienDangKy" type="text" />
									</div>
									</div>
									<button class="btn" type="submit">Tìm kiếm</button>
								</div>
							</form>
							<div class="CurriculumList">
								<div style="display: flex" class="CurriculumHeader">
									<h2 class="title">Danh sách phiên đăng ký</h2>
									<a href="creditCourseManage/addregistrationSession"
										class="addCurriculum"> Tạo phiên</a>
								</div>
								<table class="table">
									<thead
										style="background-color: rgb(238, 70, 70); color: white; font-size: 20px; border-color: black;">
										<tr>
											<th style="width: 7%">STT</th>
											<th style="width: 23%">Mã phiên</th>
											<th style="width: 23%">Mã khoa</th>
											<th style="width: 13%">Tên Khoa</th>
											<th style="width: 15%">Ngày bắt đầu</th>
											<th style="width: 15%">Ngày kết thúc</th>
											<th style="width: 5%"></th>
											<th style="width: 5%"></th>
										</tr>
									</thead>
									<tbody>
										<%
										ArrayList<PhienDangKy> dsPhienDangKy = (ArrayList<PhienDangKy>) session.getAttribute("dsPhienDangKy");
										if (dsPhienDangKy != null) {
											for (int i = 0; i < dsPhienDangKy.size(); i++) {
												PhienDangKy pdk = dsPhienDangKy.get(i);
										%>
										<tr>

											<th scope="row"><%=i + 1%></th>
											<td><%=pdk.getMaPhien()%></td>
											<td><%=pdk.getKhoa().getMaKhoa()%></td>
											<td><%=pdk.getKhoa().getTenKhoa()%></td>
											<td><%=pdk.getThoiGianBatDau().format(formatter)%></td>
											<td><%=pdk.getThoiGianKetThuc().format(formatter)%></td>
											<td><a
												href="updateCurriculum?CurriculumId=<%=pdk.getMaPhien()%>"
												class="btn btn-update "
												data-CurriculumId="<%=pdk.getMaPhien()%>">Sửa</a></td>
											<td><a class="btn btn-danger deleteCreditCourseButton"
												data-Id="<%=pdk.getMaPhien()%>">Xóa</a></td>
										</tr>
										<%
										}
										}
										%>
									</tbody>
								</table>
							</div>


						</div>
					</div>
					<%@ include file="/layout/confirmDelete.jsp"%>
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
	src="${pageContext.request.contextPath}/handleJs/confirmDeleteScript1.js"></script>