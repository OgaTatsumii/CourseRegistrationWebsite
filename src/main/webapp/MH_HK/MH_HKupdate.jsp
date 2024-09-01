<%@page import="model.NamHoc"%>
<%@page import="model.CTNamHoc_HocKy"%>
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
CTNamHoc_HocKy namHocHK =(CTNamHoc_HocKy) request.getAttribute("namHocHK");
String errorMessageMaNHHK = (String) request.getAttribute("errorMessageMaNHHK");
if (errorMessageMaNHHK == null) {
	errorMessageMaNHHK = "";
}
String errorMessageNamHoc = (String) request.getAttribute("errorMessageNamHoc");
if (errorMessageNamHoc == null) {
	errorMessageNamHoc = "";
}

String namHocHocKy = (String) request.getParameter("NamHocHocKy");
String namHoc = (String) request.getParameter("NamHoc");
String hocKy = (String) request.getParameter("HocKy");
String batDau =  (String) request.getParameter("BatDau");
String ketThuc =  (String) request.getParameter("KetThuc");

namHocHocKy = (namHocHocKy == null) ? "" : namHocHocKy;
namHoc = (namHoc == null) ? "" : namHoc;
hocKy = (hocKy == null) ? "" : hocKy;
batDau= (batDau== null) ? "" : batDau;
ketThuc= (ketThuc== null) ? "" : ketThuc;
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
				<a class = "back" href="manhochocky"> <i class='bx bx-left-arrow-alt'></i></a>
				</div>
				<div id="toast">
                        
                    </div>
				<div class="main-content">
					<div class="form-container">
						<form id="formAddSinhVien" action="manhochocky" method="POST">
						<input type="hidden" name="action" value="add">
							<div class="form-content">
								<h2>Thông tin cơ bản</h2>
								<div class="form-group">
									<div class="idStudentContent">
										<label for="MaNHHK">Mã NH-HK :</label> 
											<%
											if (namHocHK != null) {
											%>
										<input
											type="text" id="NamHocHocKy" name="NamHocHocKy"
											value="<%=namHocHK.getmaNHHocKy()%>" required />
											<%
											} else {
											%>
											<input
											type="text" id="NamHocHocKy" name="NamHocHocKy"
											value="<%=namHocHocKy%>" required />
											<%
											}
											%>
										<p class="error">
											<%=errorMessageMaNHHK%>
										</p>
									</div>
									<div class="nameContent">
									
										<label for="NamHoc">Năm học :</label> <select type="text"
											id="NamHoc" name="NamHoc" required="required">
											<%ArrayList<NamHoc> dsNamHoc = (ArrayList<NamHoc>)session.getAttribute("dsNamHoc");
												if(dsNamHoc != null){
													for(NamHoc nh : dsNamHoc){
												%>		
											<option value="<%= (namHocHK != null ? nh.getMaNamHoc() : namHoc) %>"
    										<%= (namHocHK != null && namHocHK.getnamHoc() != null && nh.getMaNamHoc().equals(namHocHK.getnamHoc().getTenNamHoc()))
    										|| (namHocHK == null && namHoc.equals(nh.getMaNamHoc()))? "selected" : "" %>>
    										<%= nh.getTenNamHoc() %>
											</option>			
											<%
											}
											} %>
											</select>
										<p class="error">
											<%=errorMessageNamHoc%>
										</p>
									</div>
								</div>
								<div class="form-group">
									<div class="emailContent">
										<label for="HocKy">Học Kỳ :</label> <select name="HocKy" required>
										<%
											if (namHocHK != null) {
											%>
											<option value="HK1"
												<%="HK1".equals(namHocHK.gethocKy().getMaHocKy()) ? "selected" : ""%>>Học kỳ 1</option>
											<option value="HK2"
												<%="Nữ".equals(namHocHK.gethocKy().getMaHocKy()) ? "selected" : ""%>>Học kỳ 2</option>
											<option value="HK3"
												<%="Khác".equals(namHocHK.gethocKy().getMaHocKy()) ? "selected" : ""%>>Học kỳ 3</option>
											<%
											} else {
											%>
											<option value="HK1"
												<%="HK1".equals(hocKy) ? "selected" : ""%>>Học kỳ 1</option>
											<option value="HK2"
												<%="HK2".equals(hocKy) ? "selected" : ""%>>Học kỳ 2</option>
											<option value="HK3"
												<%="HK3".equals(hocKy) ? "selected" : ""%>>Học kỳ 3</option>
											<%
											}
											%>
										</select>
										<p class="error">
										
										</p>
									</div>
								</div>
								<div class="form-group">
									<div class="phoneContent">
										<label for="BatDau"> Thời gian bắt đầu :</label> <input type="date"
											id="BatDau" name="BatDau" value="<%=namHocHK != null ? namHocHK.getThoiGianBatDau() : batDau%>" required />
										<p class="error">
											
										</p>
									</div>
									<div class="idContent">
										<label for="KetThuc">Thời gian kết thúc :</label> <input type="date"
											id="KetThuc" name="KetThuc" value="<%=namHocHK != null ? namHocHK.getThoiGianKetThuc() : ketThuc%>" required />
										<p class="error">
											
										</p>
									</div>
								</div>
								</div>
								<button class="addStudent" type="submit">Sửa NH-HK</button>
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




