<%@page import="model.MonHoc"%>
<%@page import="model.SinhVien"%>
<%@page import="model.Lop"%>
<%@page import="model.Nganh"%>
<%@page import="model.Khoa"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Kết quả học tập</title>
<link rel="stylesheet" href="css/style009.css" />
<link rel="stylesheet" href="css/reset.css" />
<link rel="preconnect" href="https://fonts.googleapis.com" />
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
<link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;500;600&family=Poppins:wght@300;400;500;600&family=Sora:wght@600;700;800&display=swap" rel="stylesheet" />
<link href="https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css" rel="stylesheet" />
<style>
.input-container {
	position: relative;
	width: 400px; /* Chiều rộng của input */
}

.input-container input {
	width: 100%;
	padding: 8px;
	font-size: 18px;
	box-sizing: border-box;
}

.input-container .dropdown {
	position: absolute;
	top: 100%;
	left: 0;
	right: 0;
	border: 1px solid #ccc;
	background: #fff;
	z-index: 1000;
	max-height: 150px; /* Chiều cao tối đa */
	overflow-y: auto;
	display: none;
	box-sizing: border-box;
}

.input-container .dropdown div {
	padding: 8px;
	cursor: pointer;
	box-sizing: border-box;
}

.input-container .dropdown div:hover {
	background: #f0f0f0;
}
</style>
</head>

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
				<h3 id="title_infor">Đăng ký nguyện vọng</h3>
				<%
				} else {
				%>
				<h3 id="title_infor"><%=title%></h3>
				<%
				}
				%>
				<div id="toast"></div>
				<div class="main-content">
					<div class="studentManageContainer">
						<div class="studentManageContent">
							<form action="" method="get">
								<input type="hidden" name="maSinhVien" value="<%=sinhVien.getMaSinhVien() %>">
								<div class="container">
									<label for="monHoc" style="font-size: 22px; margin-top: 10px; margin-right: 17px">Môn học:</label>
									<div class="input-container">
										<input list="dsMonHoc" id="monHoc" name="maMonHoc" placeholder="Chọn môn học..." style="width: 300px" required>
										<datalist id="dsMonHoc">
											<% ArrayList<MonHoc> dsMonHoc = (ArrayList<MonHoc>) session.getAttribute("dsMonHoc");
											if (dsMonHoc != null) {
												for (MonHoc n : dsMonHoc) { %>
													<option value="<%=n.getMaMonHoc()%>"><%=n.getTenMonHoc()%></option>
											<% }
											} %>
										</datalist>
									</div>
								</div>
								<div style="margin-top: 20px">
									<label style="display: block; font-size: 22px" for=""  >Lý do: </label>
									<textarea style="height: 80px; padding: 10px; box-sizing: border-box; border-radius: 10px; font-size: 17px; margin-top: 10px; font-family: Arial" rows="7" cols="120" name="lyDo" placeholder="Nhập lý do..." required="required"></textarea>
								</div>
								<div>
									<button id="" type="submit" name="action" value="dangKyNguyenVong" style="background-color: red; color: white; height: 50px; margin-top: 10px; border-color: white; border-radius: 15px; font-size: 17px; margin-left: 40%">
										Đăng ký
									</button>
								</div>
							</form>
							
							<div class="studentList">
								<div style="display: flex" class="studentHeader">
									<h2 class="title">Danh sách đã đăng ký nguyện vọng</h2>
								</div>
								<table class="table">
									<thead style="background-color: rgb(238, 70, 70); color: white; font-size: 20px; border-color: black;">
										<tr>
											<th style="width: 12%">Mã MH</th>
											<th style="width: 33%;white-space: normal; word-wrap: break-word; overflow-wrap: break-word;">Tên môn học</th>
											<th style="width: 10%;white-space: normal; word-wrap: break-word; overflow-wrap: break-word;">Mã nhóm</th>
											<th style="width: 10%">Tổ TH</th>
											<th style="width: 20%">Lý do</th>
											<th style="width: 16%;white-space: normal; word-wrap: break-word; overflow-wrap: break-word;">Ngày đăng ký</th>
											<th style="width: 10%;">TT</th>
											<th style="width: 15%"></th>
										</tr>
									</thead>
									<tbody>
									<% String message1 = (String) session.getAttribute("message1");
									String maMH = (String) session.getAttribute("maMH");
									String tenMH = (String) session.getAttribute("tenMH");
									String maNhom = "*";
									String toTH = "*";
									String lyDo = (String) session.getAttribute("lyDo");
									String tgdk = (String) session.getAttribute("tgdk");
									String tt = "Chờ duyệt";
									if (lyDo!=null) { %>
										<tr class="label-row">
										<form action="/DoAnDKHP_Nhom14/DangKyNguyenVong" method="post">
											<td class="label-cell" style="white-space: normal; word-wrap: break-word; overflow-wrap: break-word;" name="maMH"><%=maMH%></td>
											<td class="label-cell" style="white-space: normal; word-wrap: break-word; overflow-wrap: break-word;" name="tenMH"><%=tenMH%></td>
											<td class="label-cell" style="white-space: normal; word-wrap: break-word; overflow-wrap: break-word;" name="maNhom"><%=maNhom%></td>
											<td class="label-cell" style="white-space: normal; word-wrap: break-word; overflow-wrap: break-word;"><%=toTH%></td>
											<td class="label-cell" style="white-space: normal; word-wrap: break-word; overflow-wrap: break-word;" name="lyDo"><%=lyDo%></td>
											<td class="label-cell" style=""><%=tgdk%></td>
											<td class="label-cell" style="white-space: normal; word-wrap: break-word; overflow-wrap: break-word;" name="trangThai"><%=tt%></td>
											<td class="label-cell" style=""><button type="submit" class="btn btn-update" value="xacNhan" name="action" style="height:38px;width:100px">Xác nhận</a></td>
										</form>
										</tr>
									<% } if(message1 != null){ %>
										
										<tr class="label-row">
											<td class="label-cell" colspan="11"><label class="label-full-width"><%=message1%></label></td>
										</tr>
									<%} %>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- JavaScript để ngăn người dùng nhập thủ công -->
	<script>
	document.addEventListener("DOMContentLoaded", function() {
		const input = document.getElementById("monHoc");
		const datalist = document.getElementById("dsMonHoc");
		const options = Array.from(datalist.options).map(option => option.value);

		// Ngăn chặn nhập từ bàn phím
		input.addEventListener("keydown", function(event) {
			event.preventDefault();
		});

		// Xóa giá trị hiện tại khi nhấp vào input
		input.addEventListener("click", function() {
			input.value = '';
		});

		// Kiểm tra giá trị nhập vào
		input.addEventListener("input", function() {
			const enteredValue = input.value;
			if (!options.includes(enteredValue)) {
				input.setCustomValidity("Vui lòng chọn một giá trị hợp lệ từ danh sách.");
			} else {
				input.setCustomValidity("");
			}
		});
	});
	</script>
</body>
</html>
