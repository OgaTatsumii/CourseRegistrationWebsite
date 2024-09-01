<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@page import="java.time.LocalDateTime"%>
 <%@page import="java.time.format.DateTimeFormatter"%>
<!DOCTYPE html>
<html lang="vi">
	<head>
	    <meta charset="UTF-8">
	    <title>Thông Tin Cá Nhân</title>
	    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
	    <style>
	        .custom-bg-color{
	            background-color: rgb(238, 70, 70);
	        }
	        .tdt{
	            font-size: 20px;
	            width: 100%;
	            font-weight: 500;
	            color:rgb(87, 27, 27)
	        }
			.image{
				border-radius: 50%;
				width: 120px;
				height: 120px;
				}
			.font-title{
				font-size: 20px;
			}
			.font {
				font-size: 17px;
				font-family: "Poppins", sans-serif;
				}
		.styled {
            border: 2px solid #3498db; /* Màu xanh dương cho viền */
            border-radius: 15px; /* Viền bo tròn 15px */
            padding: 20px; /* Khoảng cách bên trong div */
            background-color: #f1f1f1; /* Màu nền xám nhạt */
        }
        .hidden-element {
            display: none;
        }
	    </style>
	    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
	    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.4/Chart.js"></script>
	   
	</head>
	<%
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	%>
	<body>
		<div class="adminArea">
		<!-- Include DashBoard -->
		<%@ include file="/layout/dashBoardSinhVien.jsp"%>

		<div class="content">
			<!-- Include NavBar -->
			<jsp:include page="/layout/navbarSinhVien.jsp" />
			<div class="main">
				<%
				if (title == null) {
				%>
				<h3 id="title_infor">Thông tin cá nhân</h3>
				<%
				} else {
				%>
				<h3 id="title_infor"><%=title%></h3>
				<%
				}
				%>
				<div id="toast" ></div>
				<div class="main-content">
				<div class="CurriculumManageContainer">
				<div class="CurriculumManageContent">
				 <script id="json-pie" class="hidden-element">
					var piec = [<%=session.getAttribute("soTinChiTL")%>,<%=session.getAttribute("tongTinChi")%>];
					var json = [<%=session.getAttribute("json")%>];
				</script>
				<div class="row" style="display: flex;">
					<div class="col-5">
						<div class="ten">
							<!--Ảnh kèm tên-->
							<div class="card shadow-lg">
								<div class="card-body">
									<div class="text-center">
									  <img class="image" src="image/default-avatar-profile-icon.jpg" style="border: 2px solid black;">
									</div>
									<div style="text-align: center; margin-top: 20px; font-size: 25px;" >
										<%= sinhVien.getHoTen() %>
									</div>
								</div>
							</div>
						</div>
							<!--Thông tin chung-->
							
							<div class="card shadow-lg my-3 thongtinchung">
								<div class="card-header tdt">
									Thông Tin Chung
								</div>
								<div class="card-body py-2 font">
								<div class="container-fluid p-0">
									<div class="row my-1 ng-star-inserted">
										<p class="card-title col-4 col-lg-4 pr-1">Mã Sinh Viên</p>
										<p class="card-text col-8 col-lg-6 px-1"><%= sinhVien.getMaSinhVien() %></p>
									</div>
									<div class="row my-1 ng-star-inserted">
										<p class="card-title col-4 col-lg-4 pr-1">Họ Và Tên</p>
										<p class="card-text col-8 col-lg-8 px-1"><%= sinhVien.getHoTen() %></p>
									</div>
									<div class="row my-1 ng-star-inserted">
										<p class="card-title col-2 col-lg-4 psr-1">Ngày sinh</p>
										<p class="card-text col-8 col-lg-8 px-1"><%= sinhVien.getNgaySinh().format(formatter) %></p>
									</div>
									<div class="row my-1 ng-star-inserted">
										<p class="card-title col-4 col-lg-4 pr-1">Giới tính</p>
										<p class="card-text col-8 col-lg-8 px-1"><%= sinhVien.getGioiTinh() %></p>
									</div>
									<div class="row my-1 ng-star-inserted">
										<p class="card-title col-4 col-lg-4 pr-1">Số điện thoại</p>
										<p class="card-text col-8 col-lg-8 px-1"><%= sinhVien.getSoDienThoai() %></p>
									</div>
									<div class="row my-1 ng-star-inserted">
										<p class="card-title col-4 col-lg-4 pr-1">Số CMND/ CCCD</p>
										<p class="card-text col-8 col-lg-8 px-1"><%= sinhVien.getCCCD() %></p>
									</div>
									<div class="row my-1 ng-star-inserted">
										<p class="card-title col-4 col-lg-4 pr-1">Email</p>
										<p class="card-text col-8 col-lg-8 px-1"><%= sinhVien.getEmail() %></p>
									</div>
									<div class="row my-1 ng-star-inserted">
										<p class="card-title col-4 col-lg-4 pr-1">Hộ khẩu</p>
										<p class="card-text col-8 col-lg-8 px-1"><%= sinhVien.getTamTru() %></p>
									</div>
								</div>
								</div>
							</div>
							</div>
							<div class="container col" style="flex-direction: column;">
								<div class="hoctap row " style="height: 60%;">
								<!-- Kết quả học tập -->
								<div class="ketquahoctap styled" style="width:60%;">
											<div style="text-align: center; font-size: 20px;">
												<p class="card-title ">Kết Quả Học Tập</p>
											</div>
											<div style="display: flex; align-items: center; justify-content: center;">
												<canvas id="barchart" style="width:100%; height: 300px; max-width:300px;"></canvas>
												<script src="handleJs/barchart.js"> </script>
											</div>
									</div>
								<!-- Tiến độ học tập -->
								
								<div class="tiendohoctap styled" style="width:39%; margin-left: 1%;" >
											<div style="text-align: center; font-size: 20px;">
												<h5 class="card-title">Tiến độ học tập</h5>
											</div>
											<div style=" align-items: center; justify-content: center;">
												<canvas id="piechart" style="width:100%;height: 260px; max-width:300px;"></canvas>
												<script src="handleJs/piechart.js"></script>
											</div>
									</div>
								</div>
								<!-- Thông tin khóa học -->
								<div class="thongtinkhoahoc" style="margin-left:-15px;">						
									<div class="card shadow-lg my-3">
										<div class="card-header tdt">
											Thông Tin Khóa Học
										</div>
										<div class="card-body py-2 font">
											<div class="container-fluid p-0">
												<div class="row my-1 ng-star-inserted">
													<p class="card-title col-4 col-lg-4 text-nowrap pr-1">Lớp</p>
													<p class="card-text col-8 col-lg-8 px-1"><%= sinhVien.getLop().getMaLop() %> </p>
												</div>
												<div class="row my-1 ng-star-inserted">
													<p class="card-title col-4 col-lg-4 text-nowrap pr-1">Ngành</p>
													<p class="card-text col-8 col-lg-8 px-1"><%= sinhVien.getLop().getNganh().getTenNganh() %> </p>
												</div>
												<div class="row my-1 ng-star-inserted">
													<p class="card-title col-4 col-lg-4 text-nowrap pr-1">Khoa</p>
													<p class="card-text col-8 col-lg-8 px-1"><%= sinhVien.getLop().getNganh().getKhoa().getTenKhoa()%></p>
												</div>
												<div class="row my-1 ng-star-inserted">
													<p class="card-title col-4 col-lg-4 text-nowrap pr-1">Niên Khóa</p>
													<p class="card-text col-8 col-lg-8 px-1"><%= sinhVien.getLop().getKhoaHoc().getNgayBatDau().format(formatter) %> - <%= sinhVien.getLop().getThoiGianKetThuc().format(formatter) %></p>
												</div>
												
											</div>
										</div>
									</div>
								</div>
								</div>
							</div>
						</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>