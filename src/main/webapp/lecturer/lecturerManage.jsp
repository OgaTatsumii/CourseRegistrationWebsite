<%@page import="model.KhoaHoc"%>
<%@page import="model.GiangVien"%>
<%@page import="model.Lop"%>
<%@page import="model.Nganh"%>
<%@page import="model.Khoa"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.time.LocalDateTime"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Document</title>
<link rel="stylesheet" href="css/style009.css" />
<link rel="stylesheet" href="css/reset.css" />
<link rel="preconnect" href="https://fonts.googleapis.com" />
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
<link
	href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;500;600&family=Poppins:wght@300;400;500;600&family=Sora:wght@600;700;800&display=swap"
	rel="stylesheet" />
<link href="https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css"
	rel="stylesheet" />

<style type="text/css">
.pagination button {
	cursor: pointer;
	border: black solid 1px;
	padding: 3px;
	border-radius: 10px;
	height: 23px;
	width: 34px;
	margin: -2px 5px;
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
					<div class="studentManageContainer">
						<div class="studentManageContent">
							<form action="lecturerManagement" method="POST">
								<input type="hidden" name="action" value="search">
								<div class="searchStudent">
									<div class="id">
										<label style="display: block" for="">Mã giảng viên</label> <input
											name="lecturerID" type="text" />
									</div>
									<div class="ten">
										<label style="display: block" for="">Tên giảng viên</label> <input
											name="lecturerName" type="text" />
									</div>
									<div class="khoa">
										<label style="display: block" for="">Giới tính</label> <select
											name="Gender">
											<option value=""></option>
											<option value="Nam">Nam</option>
											<option value="Nữ">Nữ</option>
										</select>
									</div>
									<button class="btn" type="submit">Tìm kiếm</button>
								</div>
							</form>
							<div class="studentList">
								<div style="display: flex" class="studentHeader">
									<h2 class="title">Danh sách giảng viên</h2>
									<a href="lecturerManagement?action=add" class="addStudent">
										Tạo giảng viên</a>
								</div>
								<table class="table">
									<thead
										style="background-color: rgb(238, 70, 70); color: white; font-size: 20px; border-color: black;">
										<tr>
											<th style="width: 10%">STT</th>
											<th style="width: 23%">Mã giảng viên</th>
											<th style="width: 25%">Họ tên</th>
											<th style="width: 25%">Ngày sinh</th>
											<th style="width: 17%">Giới tính</th>
											<th style="width: 25%">Số Điện Thoại</th>
											<th style="width: 29%">Email</th>
											<th style="width: 15%"></th>
											<th style="width: 15%"></th>

										</tr>
									</thead>
									<tbody>
										<%
										DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
										ArrayList<GiangVien> dsGiangVien = (ArrayList<GiangVien>) session.getAttribute("dsGiangVien");
										if (dsGiangVien != null && !dsGiangVien.isEmpty()) {
											for (int i = 0; i < dsGiangVien.size(); i++) {
												GiangVien gv = dsGiangVien.get(i);
										%>
										<tr>
											<th scope="row"><%=i + 1%></th>
											<td><%=gv.getMaGiangVien()%></td>
											<td><%=gv.getHoTen()%></td>
											<td><%=gv.getNgaySinh().format(formatter)%></td>
											<td><%=gv.getGioiTinh()%></td>
											<td><%=gv.getSoDienThoai()%></td>
											<td><%=gv.getEmail()%></td>
											<td><a
												href="lecturerManagement?action=update&maGV=<%=gv.getMaGiangVien()%>"
												class="btn btn-update ">Sửa</a></td>
											<td><a class="btn btn-danger deleteStudentButton"
												data-Id="<%=gv.getMaGiangVien()%>">Xóa</a></td>
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
								<div class="pagination"
									style="justify-content: center; display: flex; margin-top: 31px;"></div>
							</div>
						</div>


					</div>
				</div>
				<div class="modal1">
					<div class="confirmDelete">
						<form name="delete-form" action="lecturerManagement" method="POST">
							<input type="hidden" name="action" value="delete" /> <input
								type="hidden" name="Id" value="" />
							<div class="desc">Thông báo</div>
							<div class="close2">
								<span class="icon-close"> <i class='bx bx-x'></i>
								</span>
							</div>
							<p>Xác nhận xóa đi một giảng viên ?</p>
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

document.addEventListener("DOMContentLoaded", function() {
    const studentList = document.querySelectorAll('.studentList .table tbody tr');
    const pagination = document.querySelector('.pagination');
    const itemsPerPage = 5; // Số sinh viên hiển thị trên mỗi trang
    let currentPage = 1;

    // Hiển thị trang đầu tiên khi modal được mở
    showPage(currentPage);

    // Function để hiển thị danh sách sinh viên cho trang hiện tại
    function showPage(page) {
        const startIndex = (page - 1) * itemsPerPage;
        const endIndex = Math.min(startIndex + itemsPerPage, studentList.length);
        studentList.forEach((sinhVien, index) => {
            if (index >= startIndex && index < endIndex) {
                sinhVien.style.display = 'table-row';
            } else {
                sinhVien.style.display = 'none';
            }
        });
    }

	 // Tạo một phần tử để hiển thị trang hiện tại
	const currentPageElement = document.createElement('h3');
	
	// Thêm nội dung cho phần tử h3
	currentPageElement.textContent = currentPage;
	
	currentPageElement.style.fontSize = '20px';
	currentPageElement.style.margin = '0 24px';

    currentPageElement.textContent = currentPage;
    
    // Tạo nút chuyển đến trang trước
    const prevButton = document.createElement('button');
    prevButton.innerHTML = "<i class='bx bx-left-arrow'></i>";
    prevButton.addEventListener('click', () => {
        if (currentPage > 1) {
            currentPage--;
            showPage(currentPage);
            currentPageElement.textContent = currentPage;
        }
    });

    // Tạo nút chuyển đến trang sau
    const nextButton = document.createElement('button');
    nextButton.innerHTML = "<i class='bx bx-right-arrow' ></i>";
    nextButton.addEventListener('click', () => {
        if (currentPage < Math.ceil(studentList.length / itemsPerPage)) {
            currentPage++;
            showPage(currentPage);
            currentPageElement.textContent = currentPage;
        }
    });

    // Thêm nút vào phân trang
    const paginationContainer = document.querySelector('.pagination');
    paginationContainer.appendChild(prevButton);
    paginationContainer.appendChild(currentPageElement);
    paginationContainer.appendChild(nextButton);

    // Thêm container phân trang vào modal
    pagination.appendChild(paginationContainer);
});

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

<script src="handleJs/confirmDeleteScript1.js"></script>


