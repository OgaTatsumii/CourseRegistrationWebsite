<%@page import="model.PhienDangKy"%>
<%@page import="model.Khoa"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.time.LocalDateTime"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Quản lý phiên đăng ký</title>
<link rel="stylesheet" href="css/style009.css" />
<link rel="stylesheet" href="css/reset.css" />
<link rel="preconnect" href="https://fonts.googleapis.com" />
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
<link
	href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;500;600&family=Poppins:wght@300;400;500;600&family=Sora:wght@600;700;800&display=swap"
	rel="stylesheet" />
<link href="https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css"
	rel="stylesheet" />
<style>
.faded-row {
	opacity: 0.5;
}
</style>


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
				<h3 id="title_infor">Quản lý Phiên đăng ký</h3>
				<%
				} else {
				%>
				<h3 id="title_infor"><%=title%></h3>
				<%
				}
				%>
				<div class="main-content">
					<div class="studentManageContainer">
						<div class="studentManageContent">
							<form action="registersession" method="GET"
								onsubmit="return validateDateRange()">
								<input type="hidden" name="action" value="timKiem">
								<div class="searchStudent">
									<div class="khoa">
										<label style="display: block" for="">Khoa</label> <select
											id="khoaSelect" name="Khoa">
											<option value="" disabled selected style="color: white"><i>Khoa...</i></option>
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

									<div class="date-search" id="test" style="margin-left: 150px">
										<label style="display: block" for="ngayBatDau">Từ</label> <input
											id="startDateInput" type="date" style="width: 200px"
											name="ngayBatDau">
									</div>

									<div class="date-search" id="test1">
										<label style="display: block" for="ngayKetThuc">Đến</label> <input
											id="endDateInput" type="date" style="width: 200px"
											name="ngayKetThuc">
									</div>

									<button class="btn" type="submit">Tìm kiếm</button>
								</div>
							</form>

							<div class="studentList">
								<div style="display: flex" class="studentHeader">
									<h2 class="title">Danh sách phiên đăng ký</h2>
									
								</div>
								<table class="table">
									<thead
										style="background-color: rgb(238, 70, 70); color: white; font-size: 20px; border-color: black;">
										<tr>
											<!-- <th style="width: 15%">Mã phiên</th> -->
											<th style="width: 17%">Năm học</th>
											<th style="width: 10%">Học kỳ</th>
											<th style="width: 13%">Mã khoa</th>
											<th style="width: 40%">Tên khoa</th>
											<th style="width: 26%">Thời gian bắt đầu</th>
											<th style="width: 26%">Thời gian kết thúc</th>
											<th style="width: 12%"></th>
											<th style="width: 12%"></th>
										</tr>
									</thead>
									<tbody>
										<%
										DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
										ArrayList<PhienDangKy> dsPdk = (ArrayList<PhienDangKy>) session.getAttribute("dsPdk");
										if (dsPdk != null && !dsPdk.isEmpty()) {
											for (int i = 0; i < dsPdk.size(); i++) {
												PhienDangKy pdk = dsPdk.get(i);
										%>
										<tr id="row-<%=i%>"
											data-end-time="<%=pdk.getThoiGianKetThuc().format(formatter)%>">
											<%-- <td><%=pdk.getMaPhien()%></td> --%>
											<td style="white-space: normal; word-wrap: break-word; overflow-wrap: break-word;" ><%=pdk.getCtNamHocHocKy().getnamHoc().getTenNamHoc()%></td>
											<td><%=pdk.getCtNamHocHocKy().gethocKy().getMaHocKy()%></td>
											<td><%=pdk.getKhoa().getMaKhoa()%></td>
											<td style="white-space: normal; word-wrap: break-word; overflow-wrap: break-word;"><%=pdk.getKhoa().getTenKhoa()%></td>
											<td style="white-space: normal; word-wrap: break-word; overflow-wrap: break-word;"><%=pdk.getThoiGianBatDau().format(formatter)%></td>
											<td style="white-space: normal; word-wrap: break-word; overflow-wrap: break-word;"><%=pdk.getThoiGianKetThuc().format(formatter)%></td>
											<td><a
												href="registersession?action=thongTinUpdate&maPhien=<%=pdk.getMaPhien()%>"
												class="btn btn-update">Sửa</a></td>
											<td><a class="btn btn-danger deleteStudentButton"
												data-Id="<%=pdk.getMaPhien()%>">Xóa</a></td>
										</tr>
										<%
										}
										} else {
										%>
										<tr>
											<td colspan="20"><h2
													style="font-size: 18px; padding: 16px;">Không tìm thấy
													kết quả hợp lệ</h2></td>
										</tr>
										<%
										}
										%> 
									</tbody>

								</table>
								
								<div style="display: flex" class="studentHeader">
								<div>
									<a href="registersession?action=dlTaoPhien" class="addStudent">
										Tạo phiên</a>
								</div>
								<div style="margin-right:79%">
									
								</div>
									
								</div>
							</div>
							<%if (dsPdk!=null) {%>
							<div class="studentList">
								<div style="display: flex" class="studentHeader">
									<h2 class="title">Thống kê</h2>
								</div>
								<div style="display:flex">
									<div>
										<p style="margin-left:20px;font-size:21px;font-style:italic">Số lượng phiên đăng ký còn tồn tại là: </p>
										<p style="margin-left:20px;margin-top:12px;font-size:21px;font-style:italic">Số lượng phiên đăng ký hết hạn là: </p>
									</div>
									<%
										int soLuongCon=0;
										int soLuongHet=0;
										LocalDateTime now= LocalDateTime.now();
										for(PhienDangKy pdk:dsPdk){
											if(now.compareTo(pdk.getThoiGianKetThuc())<=0){
												soLuongCon+=1;
											}else{
												soLuongHet+=1;
											}
										}%>								
									<div>
										<p style="margin-left:17px;font-size:21px;font-weight:bold"><%=soLuongCon %></p>
										<p style="margin-left:18px;margin-top:12px;font-size:21px;font-weight:bold"><%=soLuongHet %></p>
									</div>
								</div>
							</div>
							<%} %>
					</div>
					<div class="modal1">
						<div class="confirmDelete">
							<form name="delete-form" action="registersession" method="Get">
								<input type="hidden" name="Id" value="" />
								<input type="hidden" name="action" value="delPhien" />
								<div class="desc">Thông báo</div>
								<div class="close2">
									<span class="icon-close"> <i class='bx bx-x'></i>
									</span>
								</div>
								<p>Xác nhận xóa đi phiên ?</p>
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
<script>
        function validateDateRange() {
            const startDate = new Date(document.getElementById("startDateInput").value);
            const endDate = new Date(document.getElementById("endDateInput").value);

            if (startDate >= endDate) {
                alert("Ngày bắt đầu phải nhỏ hơn ngày kết thúc.");
                return false; // Ngăn form gửi đi
            }
            // Nếu ngày hợp lệ, form sẽ được gửi đi
            return true;
        }
    </script>
<script>
    document.addEventListener("DOMContentLoaded", function() {
        const rows = document.querySelectorAll("tbody tr");

        rows.forEach(row => {
            const endTime = new Date(row.getAttribute("data-end-time"));
            const currentTime = new Date();

            if (endTime < currentTime) {
                row.classList.add("faded-row");
            }
        });
    });
</script>

<script src="handleJs/confirmDeleteScript1.js"></script>

