<%@page import="model.NoiDungThongBao"%>
<%@page import="model.LoaiThongBao"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Trang Chủ</title>
<link rel="stylesheet" type="text/css" href="css/trangChu.css">
<script
	src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"
	integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js"
	integrity="sha384-0pUGZvbkm6XF6gxjEnlmuGrJXVbNuzT9qBBavbLwCsOGabYfZo0T0to5eqruptLy"
	crossorigin="anonymous"></script>
<script src="https://kit.fontawesome.com/7b97f02ae4.js" crossorigin="anonymous"></script>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
	crossorigin="anonymous">
</head>
<header>
	<div class="max-width" style="height: 150px">
		<img src="image/banner_ptit.jpg" alt="banner ptit">

	</div>
	<!-- Navigation -->
	<div>
		<nav
			class="navbar navbar-expand-lg bg-body-tertiary navbar-no-padding-top">
			<div class="container-fluid"
				style="background-color: rgb(222, 34, 25); height: 53px">

				<ul class="navbar-nav me-auto mb-2 mb-lg-0" style="margin-left: 5%">
					<li class="nav-item"><a class="nav-link font-white" href="#">TRANG
							CHỦ</a></li>
					<li class="nav-item"><a class="nav-link font-white" href="#">TRA
							CỨU VĂN BẰNG</a></li>
					<li class="nav-item"><a class="nav-link font-white" href="#">ĐĂNG
							KÝ HỌC PHẦN</a></li>
					<li class="nav-item"><a class="nav-link font-white" href="#">NGÀNH</a>
					</li>
					<li class="nav-item"><a class="nav-link font-white" href="#">THANH
							TOÁN HỌC PHÍ</a></li>
				</ul>
				<form style="margin-right: 10%" action="/DoAnDKHP_Nhom14/login.jsp">
					<button class="btn border border-white" type="submit"
						style="color: white; background-color:">Đăng nhập</button>
				</form>
			</div>
		</nav>
	</div>
</header>
<body class="body-margin">
	<div class="container">
		<div class="">
			<a><img src="image/2022-10-17.jpg" alt="body image"
				style="height: 461px; width: 1218px; margin-top: 73px"></a>
		</div>

		<div class="container text-center">
			<div class="row" style="margin-top: 60px">
				<div class="col"></div>
				<button type="submit" class="btn col button_gird font-white">
					NGHỈ HỌC - HỌC BÙ - ĐĂNG KÝ RÚT HỌC PHẦN</button>
				<button type="submit" class="btn col button_gird font-white">
					NỘP CHỨNG CHỈ NGOẠI NGỮ, TIN HỌC</button>
				<button type="submit" class="btn col button_gird font-white">
					THEO DÕI SỬ DỤNG PHÒNG HỌC</button>
				<button type="submit" class="btn col button_gird font-white">
					GIẢNG VIÊN ĐĂNG KÝ NGHỈ GIẢNG, GIẢNG BÙ</button>
				<button type="submit" class="btn col button_gird font-white">
					ĐĂNG KÝ GIẤY CHỨNG NHẬN TỐT NGHIỆP TẠM THỜI</button>
				<div class="col"></div>
			</div>
		</div>
	</div>
	<!-- Thông báo -->
	
<%	
	ArrayList<LoaiThongBao> dsLTB = (ArrayList<LoaiThongBao>)session.getAttribute("dsLTB");
%>	
	<div class="infor-container" style="margin-top: 60px">
		<div class="news-container news">
			<div class="news-header">
				<h4 class="news-title"><i style="margin-right:10px" class="fa-solid fa-earth-americas"></i>Tin tức</h4>
			</div>
			<div class="news-content">
				<ul class="news-list-items" >
					 <%for(LoaiThongBao ltb:dsLTB){%>
							<li class="news-item" onclick="changeTitle('<%=ltb.getTenLoaiThongBao()%>'); changeIframeSrc('./trangChu?action=duyetPhanTrang&loaiThongBao=<%=ltb.getMaLoaiThongBao()%>&trang=1');"><i style="margin-right:10px" class="fa-solid fa-caret-up"></i><%=ltb.getTenLoaiThongBao()%></li>
						<hr>
					<%}%>																						
				</ul>
			</div>
		</div>		
				
		<!--  -->
		<div class="news-container notice">
                <div class="news-header notice-header">
                	<i style="font-size:23px"class="fa-solid fa-bell"></i>
					<h4 class="news-title" id="title-announce" style="margin-right:250px">Thông báo mới</h4>
					<div class="notice-search">
						<input type="text" class="search-area">
						<button class="search-btn">Tìm kiếm</button>
					</div>
				</div>
			<div class="announce1" style="height: 100vh">
                <iframe class="" src="" frameborder="0" id="jsp-file" style="height:100%"></iframe>               
            </div>
				
        </div>
	</div>
	<script>
        function changeTitle(txt){
		    document.getElementById("title-announce").innerHTML = txt;
		    
	    }
        
        window.onload = function() {
            var initialSrc = "./trangChu?action=duyetPhanTrang&loaiThongBao=TBC&trang=1"; // URL ban đầu của iframe
            changeIframeSrc(initialSrc); // Gọi hàm để thay đổi src của iframe
        };
        
        function changeIframeSrc(newSrc) {
            document.getElementById("jsp-file").src = newSrc;
        }
        
    </script>
</html>