<%@page import="database.DiemDAO"%>
<%@page import="model.KhoaHoc"%>
<%@page import="model.SinhVien"%>
<%@page import="model.Lop"%>
<%@page import="model.Diem"%>
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
<title>Kết quả học tập</title>
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
.label-row {
	background-color: #ffcccc; /* Màu nền đỏ nhạt */
}

.label-cell {
	padding: 10px; /* Khoảng cách trong ô chứa label */
}

.label-full-width {
	display: block; /* Đảm bảo label là khối để chiếm toàn bộ chiều rộng */
	width: 100%;
	text-align: left; /* Căn chữ bên trái */
	font-weight: bold; /* Chữ đen in đậm */
	color: black; /* Màu chữ đen */
}

.zoomed {
     transform: scale(0.9); /* Ví dụ phóng to 80% */
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
				<h3 id="title_infor">Kết quả học tập</h3>
				<%
				} else {
				%>
				<h3 id="title_infor"><%=title%></h3>
				<%
				}
				%>
				<div id="toast" ></div>
				<div class="main-content ">
					<div class="studentManageContainer ">
						<div class="studentManageContent">
							<div class="studentList">
								<div style="display: flex" class="studentHeader">
									<h2 class="title">Kết quả</h2>
								</div>
								<%
								DiemDAO diemDAO = new DiemDAO();
								SinhVien sinhvien = (SinhVien) session.getAttribute("sinhVien");
								for(int i = 9; i >= 1; i--){
									String hk = "HK" + String.valueOf(i);
									ArrayList<Diem> danhSachDiem = diemDAO.selectHK_MSV(hk, sinhvien.getMaSinhVien());
									if (danhSachDiem.size()==0){
										continue;
									}
								%>
								<table class="table" style="margin-bottom: 35px;">
									<thead
										style="background-color: rgb(238, 70, 70); color: white; font-size: 20px; border-color: black;">
										<tr>
											<th style="width: 9%">Stt</th>
											<th style="width: 15%">Mã MH</th>
											<th style="width: 13%">Nhóm MH</th>
											<th style="width: 55%">Tên môn học</th>
											<th style="width: 10%">Số TC</th>
											<th style="width: 14%">Điểm thi</th>
											<th style="width: 13%">Điểm TK(10)</th>
											<th style="width: 12%">Điểm TK(4)</th>
											<th style="width: 12%">Điểm TK(C)</th>
											<th style="width: 12%">Kết quả</th>
											<th style="width: 12%">Chi tiết</th>
										</tr>
									</thead>
									<tbody>
									<tr class="label-row">
											<td class="label-cell" colspan="11"><label
												class="label-full-width">Học kỳ <%= i %></label></td>
										</tr>
									<%
									//ArrayList<Diem> danhSachDiem = (ArrayList<Diem>) session.getAttribute("dsDiem");
									String diem10 = "";
									String diem4 = "";
									String diemC = "";
									String kq = "" ;
									int j = 0;
									float TBHK = 0 ;
									int soTinChiHK = 0;
									if (danhSachDiem != null) {
										for (; j < danhSachDiem.size(); j++) {
											Diem diem = danhSachDiem.get(j);
											if(diem.getDiem()!= -1.0){TBHK += diem.getDiem();}
											if(diem.getDiem()!= -1.0){soTinChiHK += diem.getMonHoc().getSoTinChi();}
											
									%>
										<tr>
											<th scope="row"><%= j+1 %></th>
											<td><%=diem.getMonHoc().getMaMonHoc()%></td>
											<td>02</td>
											<td><%=diem.getMonHoc().getTenMonHoc()%></td>
											<td><%=diem.getMonHoc().getSoTinChi()%></td>
											<td>0</td>
											<%if(diem.getDiem()== 0){diem10= "0.0";}else if(diem.getDiem()> 0.0){diem10 = String.valueOf(diem.getDiem());}else{diem10="";}%>
											<td><%= diem10%></td>
											<%if(diem.getDiem()== 0){diem4= "0.0";}else if(diem.getDiem()> 0.0){diem4 = String.valueOf(diem.getDiem()/2.5);}else{diem4="";}%>
											<td><%=diem4 %></td>
											<%if(diem.getDiem()< 4.0){diemC= "F";}
											else if(diem.getDiem()< 5.0){diemC= "D";}else if(diem.getDiem()< 6.0){diemC= "D+";}
											else if(diem.getDiem()< 6.5){diemC= "C";}else if(diem.getDiem()< 7.0){diemC= "C+";}
											else if(diem.getDiem()< 8.0){diemC= "B";}else if(diem.getDiem()< 9.0){diemC= "B+";}
											else if(diem.getDiem()< 9.0){diemC= "A";}else if(diem.getDiem()< 10.0){diemC= "A+";}
											%>
											<td><%= diemC %></td>
											
											<%if(diem.getDiem()== -1.0){kq= "";}else if(diem.getDiem()<= 4.0){kq= "Rớt";}else{kq= "Đậu";}%>
											<td><%=kq%></td>
											<td>!!!</td>
										</tr>
										<%
										}
										}
										%>
										<tr class="">
											<td class="" colspan="11" style="margin:0;padding:0">
											<div style="display:flex;background:#e3e3e3">
												<div style="margin-left:50px;text-align: left;margin-top:11px;margin-bottom:11px;">
													<p style="font-size:17px;color:#ee4646">- Điểm trung bình học kỳ hệ 4: <%= String.format("%.02f", TBHK/j/2.5) %></p>
													<p style="margin-top:12px;margin-bottom:12px;font-size:17px;color:#ee4646">- Điểm trung bình học kỳ hệ 10: <%=String.format("%.02f", TBHK/j)%></p>
													<p style="font-size:17px;color:#ee4646">- Số tín chỉ đạt học kỳ:<%= soTinChiHK %></p>
												</div>
												<div style="margin-left:200px;text-align: left;margin-top:11px;margin-bottom:11px">
													<p style="font-size:17px;color:#ee4646">- Điểm trung bình tích lũy hệ 4: <%= session.getAttribute("DiemTBTL4") %>	</p>
													<p style="margin-top:12px;margin-bottom:12px;font-size:17px;color:#ee4646">- Điểm trung bình tích lũy hệ 10: <%=session.getAttribute("DiemTBTL")%></p>
													<p style="font-size:17px;color:#ee4646">- Số tín chỉ tích lũy: <%= session.getAttribute("soTinChiTL") %></p>
												</div>
											</div>
											</td>
										</tr>

									</tbody>
								</table>
								
								<%
								}
								%>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>


