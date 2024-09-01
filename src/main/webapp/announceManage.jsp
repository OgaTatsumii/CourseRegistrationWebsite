<%@page import="model.SinhVien"%>
<%@page import="model.KhoaHoc"%>
<%@page import="model.Lop"%>
<%@page import="model.Nganh"%>
<%@page import="model.Khoa"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.NoiDungThongBao"%>
<%@page import="model.LoaiThongBao"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Announce</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style009.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/reset.css" />
<link rel="preconnect" href="https://fonts.googleapis.com" />
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
<link
	href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;500;600&family=Poppins:wght@300;400;500;600&family=Sora:wght@600;700;800&display=swap"
	rel="stylesheet" />
<link href="https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css"
	rel="stylesheet" />
	<style>
        /* CSS cho phân trang */
        .pagination {
            display: flex;
            justify-content: center;
            align-items: center;
            margin-top: 20px;
        }

        .pagination a {
            padding: 10px;
            margin: 0 5px;
            text-decoration: none;
            color: #333;
            border: 1px solid #ccc;
            border-radius: 5px;
             font-size: 16px;
        }

        .pagination a:hover {
            background-color: #f0f0f0;
            background-color: red;
        }
    </style>
</head>
<%
ArrayList<LoaiThongBao> dsLTB = (ArrayList<LoaiThongBao>)session.getAttribute("dsLTB");
%>
<body>
	<div class="adminArea">
		<!-- Include DashBoard -->
		<%@ include file="/layout/dashBoard.jsp"%>

		<div class="content" style="height:100vh">
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
				<div class="main-content">
					<div class="studentManageContainer" >
						<div class="studentManageContent" >
							<form action="./announceManage" method="GET">
							<input type="hidden" name="hanhDong" value="luuThongTin"/>
								<div class="searchStudent">									
									<div class="">
										<label style="display: block" for="">Tiêu đề</label>									
										 <textarea style="height: 150px;padding: 10px;box-sizing: border-box;border-radius: 10px;font-size: 17px"rows="7" cols="30" name="tieuDe"></textarea>
									</div>
									<div class="">
										<label style="display: block" for="">Nội dung</label>
										<textarea style="height: 150px;padding: 10px;box-sizing: border-box;border-radius: 10px;font-size: 17px"rows="7" cols="75" name="noiDung"></textarea>
									</div>
									<div>
										  <input type="hidden" style="width:200px; height:50px">
								 
									</div>								
								</div>
								<div class="searchStudent" style="margin-top:35px">
									<label style="padding: 10px 10px 10px 180px; "for="loaiThongBao">Loại thông báo</label> 
									<select style="width:40%; margin-right:330px" name="loaiThongBao">										
											<%for(LoaiThongBao ltb:dsLTB) {%>
												<option value="<%=ltb.getMaLoaiThongBao()%>"><%=ltb.getTenLoaiThongBao() %></option>
											<%}%>
									</select>
								</div>	
								<div style="font-size:18px; margin-top:10px">
									<input type="checkbox" id="emailCheckbox">Gửi thông báo bằng email đến sinh viên
								</div>							
										<button class="btn" type="submit" style="margin-left:40%" onclick="showTable()">Gửi thông báo</button>																															
							 <div class="searchStudent" style="margin-top:40px;display:none;" id="thongBaoEmail">
									<div class="khoa">
										<label style="display: block" for="">Khoa</label> 
										<select name="Khoa">
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
											<option value="a">1</option>
										</select>
									</div>
									<div class="nganh">
										<label style="display: block" for="">Ngành</label> <select>
											<%
											ArrayList<Nganh> danhSachNganh = (ArrayList<Nganh>) session.getAttribute("dsNganh");
											if (danhSachKhoa != null) {
												for (Nganh nganh : danhSachNganh) {
											%>
											<option value="<%=nganh.getMaNganh()%>"><%=nganh.getTenNganh()%></option>
											<%
											}
											}
											%>
										</select>
									</div>
									<div class="lop">
										<label style="display: block" for="">Lớp</label> <select>
											 <%
											ArrayList<Lop> danhSachLop = (ArrayList<Lop>) session.getAttribute("dsLop");
											if (danhSachLop != null) {
												for (Lop lop : danhSachLop) {
											%>
											<option value="<%=lop.getMaLop()%>"><%=lop.getMaLop()%></option>
											<%
											}
											}
											%>
										</select>
									</div>
									<div class="year">
										<label style="display: block" for="">Mã sinh viên</label> 										
										<input type="text" name="maSinhVien">
									</div>									
							</div>								
								
							</form>
							<div class="studentList" style="margin-top:10%">
							<hr>
								<div style="display: flex" class="studentHeader">								
									<h2 class="title">Danh sách thông báo</h2>
									<!-- <a href="addStudent" class="addStudent"> Tạo sinh viên</a> -->					
									<div class="pagination">
								        <%-- <a onclick="changeIframeSrc('./thongbao1.jsp?min_page=<%=min_page%>&max_page=<%=max_page%>');"><</a> --%>
								        <a onclick="changeValue('-1'); changeText(); changeIframeSrc('./thongbao1.jsp'); "><</a>
								        <a href="#" id="value_page">0</a>
								        <a onclick="changeValue('1'); changeText();changeIframeSrc('./thongbao1.jsp'); ">></a>
						    		</div>
								</div>
								<!-- iframe table here -->
								<div style="height:100vh">
									<iframe class="" src="" frameborder="0" id="jsp-file" style="width: -webkit-fill-available; height:100%"></iframe>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>		
	</div>
	
	
</body>
<script>
    const emailCheckbox = document.getElementById('emailCheckbox');
    const thongBaoEmail = document.getElementById('thongBaoEmail');

    emailCheckbox.addEventListener('change', function() {
        if (this.checked) {
            thongBaoEmail.style.display = 'flex';
        } else {
            thongBaoEmail.style.display = 'none';
        }
    });   
    
    
    let page=0;
    function changeValue(amount) {    	
       /*  page += amount; */      
       page +=parseInt(amount);
       if(page == -1){
       	page =0;
       }
       console.log("Giá trị sau khi thay đổi: " + page);
        // Thực hiện các hành động khác liên quan đến giá trị
    }
    
    function changeText(){
	    document.getElementById("value_page").innerHTML = page;	    
    } 
    
    function changeIframeSrc(newSrc) {
        document.getElementById("jsp-file").src = newSrc+"?value="+page;
    }
</script>
</html>



