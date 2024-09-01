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
<!-- Thư viện SweetAlert2 -->
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>
<!--  -->
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

<body>
	<div class="adminArea">
		<!-- Include DashBoard -->
		<%@ include file="/layout/dashBoard.jsp"%>

		<div class="content" style="height: 100vh">
			<!-- Include NavBar -->
			<jsp:include page="/layout/navbar.jsp" />
			<div class="main">
				<%
				if (title == null) {
				%>
				<h3 id="title_infor">Quản lí thông báo</h3>
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
							<form action="./announceManage" method="GET">
								<input type="hidden" name="action" value="luuThongTin" />
								<div class="searchStudent">
									<div class="">
										<label style="display: block" for="">Tiêu đề</label>
										<textarea
											style="height: 150px; padding: 10px; box-sizing: border-box; border-radius: 10px; font-size: 17px"
											rows="7" cols="30" name="tieuDe"></textarea>
									</div>
									<div class="">
										<label style="display: block" for="">Nội dung</label>
										<textarea
											style="height: 150px; padding: 10px; box-sizing: border-box; border-radius: 10px; font-size: 17px"
											rows="7" cols="75" name="noiDung"></textarea>
									</div>
									<div>
										<input type="hidden" style="width: 200px; height: 50px">

									</div>
								</div>
								<div class="searchStudent" style="margin-top: 35px">
									<label style="padding: 10px 10px 10px 180px;"
										for="loaiThongBao">Loại thông báo</label> <select
										style="width: 40%; margin-right: 330px" name="loaiThongBao">
										<%
											ArrayList<LoaiThongBao> dsLTB = (ArrayList<LoaiThongBao>)session.getAttribute("dsLTB");
										for(LoaiThongBao ltb:dsLTB) {%>
										<option value="<%=ltb.getMaLoaiThongBao()%>"><%=ltb.getTenLoaiThongBao() %></option>
										<%}%>
									</select>
								</div>
								
								<button class="btn" type="submit" style="margin-left: 40%"
									onclick="showTable()">Gửi thông báo</button>
								<div style="font-size: 18px; margin-top: 10px">
									<a id="guiMail" href="#" style="font-style:italic;text-decoration:underline">Gửi thông báo
									bằng email đến sinh viên</a>
								</div>
								<hr>
								

							</form>
							<div class="studentList">
								
								<div style="display: flex" class="studentHeader">
									<h2 class="title">Danh sách thông báo</h2>
									<div class="pagination">
										<a
											onclick="changeValue('-1'); changeText(); changeIframeSrc('./announceManage'); "><</a>
										<a href="#" id="value_page">1</a> <a
											onclick="changeValue('1'); changeText(); changeIframeSrc('./announceManage'); ">></a>
									</div>
								</div>
								<!-- iframe table here -->
								<div style="height: 100vh">
									<iframe class="" src="" frameborder="0" id="jsp-file"
										style="width: -webkit-fill-available; height: 100%"></iframe>
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

    //const emailCheckbox = document.getElementById('emailCheckbox');
    //const thongBaoEmail = document.getElementById('thongBaoEmail');

    /* emailCheckbox.addEventListener('change', function() {
        if (this.checked) {
            thongBaoEmail.style.display = 'flex';
        } else {
            thongBaoEmail.style.display = 'none';
        }
    });  */  
    
    
    let page=1;
    function changeValue(amount) {    	
       /*  page += amount; */      
       page +=parseInt(amount);
       if(page == 0){
       	page =1;
       }
       console.log("Giá trị sau khi thay đổi: " + page);
        // Thực hiện các hành động khác liên quan đến giá trị
    }
    
    function changeText(){
	    document.getElementById("value_page").innerHTML = page;	    
    } 
    
    function changeIframeSrc(newSrc) {
        document.getElementById("jsp-file").src = newSrc+"?action=duyetPhanTrang&trang="+page;
    }

     window.onload = function() {
	 	var initialSrc = "./announceManage"; // URL ban đầu của iframe
	 	changeIframeSrc(initialSrc); // Gọi hàm để thay đổi src của iframe
}; 
</script>
<script>
document.addEventListener("DOMContentLoaded", function() {
    document.getElementById("guiMail").addEventListener("click", function(event) {
        event.preventDefault(); // Ngăn chặn hành động mặc định của thẻ a
        
        // Hiển thị message box với 4 lựa chọn
        Swal.fire({
            title: 'Chọn đối tượng gửi email',
            showCancelButton: true,
            showConfirmButton: false,
            showCloseButton: true,
            cancelButtonText: 'Hủy bỏ',
            html:
              '<button id="khoa" class="swal2-confirm swal2-styled" style="display: inline-block; border: 1px solid transparent; margin-right: 5px;">Khoa</button>' +
              '<button id="nganh" class="swal2-confirm swal2-styled" style="display: inline-block; border: 1px solid transparent; margin-right: 5px;">Ngành</button>' +
              '<button id="lop" class="swal2-confirm swal2-styled" style="display: inline-block; border: 1px solid transparent; margin-right: 5px;">Lớp</button>'+
              '<button id="sv" class="swal2-confirm swal2-styled" style="display: inline-block; border: 1px solid transparent; margin-right: 5px;">Sinh viên</button>'
        });

        // Xử lý hành động khi nhấn các nút trong message box
        document.getElementById("khoa").addEventListener("click", function() {
            // Thực hiện hành động gửi thông báo bằng email
            // Ở đây bạn có thể thêm code để gửi email
        	 window.location.href = `announceManage?action=guiMailSinhVien&id=khoa`;
        });

        document.getElementById("nganh").addEventListener("click", function() {
            // Thực hiện hành động cho lựa chọn 2
            window.location.href = `announceManage?action=guiMailSinhVien&id=nganh`;
        });

        document.getElementById("lop").addEventListener("click", function() {
            // Thực hiện hành động cho lựa chọn 3
        	 window.location.href = `announceManage?action=guiMailSinhVien&id=lop`;
        });
        
        document.getElementById("sv").addEventListener("click", function() {
            // Thực hiện hành động cho lựa chọn 3
        	 window.location.href = `announceManage?action=guiMailSinhVien&id=sv`;
        });
    });
});
</script>

</html>



