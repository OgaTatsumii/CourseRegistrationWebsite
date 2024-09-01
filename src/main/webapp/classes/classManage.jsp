<%@page import="model.Lop"%>
<%@page import="model.Nganh"%>
<%@page import="model.KhoaHoc"%>
<%@page import="java.util.ArrayList"%>
<%@page import=" java.time.format.DateTimeFormatter"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Quản lý các lớp</title>
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
				<h3 id="title_infor">Quản lý lớp học</h3>
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
							<form action="classManage" method="GET">
								<input type="hidden" name="action" value="timKiem">
								<div class="searchStudent">
									<div class="Nganh">
										<label style="display: block" for="">Ngành học</label> <select
											id="khoaSelect" name="Nganh">
											<option value="" disabled selected style="color: white"><i>Ngành
													học...</i></option>
											<%
											ArrayList<Nganh> danhSachNganh = (ArrayList<Nganh>) session.getAttribute("dsNganh");
											if (danhSachNganh != null) {
												for (Nganh nganh : danhSachNganh) {
											%>

											<option value="<%=nganh.getMaNganh()%>"><%=nganh.getTenNganh()%></option>
											<%
											}
											}
											%>
										</select>
									</div>
									<div class="KhoaHoc">
										<label style="display: block" for="">Khóa học</label> <select
											id="khoaSelect" name="KhoaHoc">
											<option value="" disabled selected style="color: white"><i>Khóa
													học...</i></option>
											<%
											ArrayList<KhoaHoc> dsKhoaHoc = (ArrayList<KhoaHoc>) session.getAttribute("dsKhoaHoc");
											if (dsKhoaHoc != null) {
												for (KhoaHoc khoaHoc : dsKhoaHoc) {
											%>
											<option value="<%=khoaHoc.getMaNamHoc()%>"><%=khoaHoc.getMaNamHoc()%></option>
											<%
											}
											}
											%>
										</select>
									</div>
									<button class="btn" type="submit">Tìm kiếm</button>
								</div>
							</form>

							<div class="studentList">
								<div style="display: flex" class="studentHeader">
									<h2 class="title">Danh sách Lớp học</h2>
									<button class="addStudent btn-delete-selected-class"
										style="background: red; margin-left: 53%; color: white">Xử
										lý lớp</button>
									<a href="classManage?action=layDuLieuThem" class="addStudent">Thêm
										lớp</a>
								</div>
								<table class="table">
									<thead
										style="background-color: rgb(238, 70, 70); color: white; font-size: 20px; border-color: black;">
										<tr>
											<th style="width: 10%">Chọn<input type="checkbox"
												id="selectAll"></th>
											<th style="width: 20%">Mã Lớp</th>
											<th style="width: 37%">Tên Ngành</th>
											<th style="width: 10%">Số lượng</th>
											<th style="width: 18%">Ngày kết thúc</th>
											<th style="width: 10%"></th>
										</tr>
									</thead>
									<tbody>
										<%
										ArrayList<Lop> dsLop = (ArrayList<Lop>) session.getAttribute("dsLop");
										if (dsLop != null) {
											for (Lop lop : dsLop) {
										%>
										<tr>
											<td><input type="checkbox" class="classCheckbox"
												data-maLop="<%=lop.getMaLop()%>"></td>
											<td><a
												href="classManage?action=xemSinhVien&maLop=<%=lop.getMaLop()%>"><%=lop.getMaLop()%></a></td>
											<td
												style="white-space: normal; word-wrap: break-word; overflow-wrap: break-word;"><%=lop.getNganh().getTenNganh()%></td>
											<td><%=lop.getSoLuongSinhVien()%></td>
											<td><%=lop.getThoiGianKetThuc()%></td>
											<td><a
												href="classManage?action=suaClass&MaLop=<%=lop.getMaLop()%>"
												class="btn btn-update " data-studentId="<%=lop.getMaLop()%>">Sửa</a></td>
											<%-- <td><a class="btn btn-danger deleteStudentButton" data-Id="<%=lop.getMaLop()%>">Xóa</a></td> --%>
										</tr>
										<%
										}
										}
										%>
									</tbody>
								</table>
								<div class="pagination"
									style="justify-content: center; display: flex; margin-top: 31px;"></div>
								<%
								if (dsLop == null) {
								%>
								<p
									style="font-style: italic; font-size: 20px; color: red; text-align: center">Không
									tìm thấy dữ liệu</p>
								<%
								}
								%>
							</div>
						</div>
					</div>
					<div class="modal1">
						<div class="confirmDelete">
							<form name="delete-form" action="deleteStudent" method="POST">
								<input type="hidden" name="Id" value="" />
								<div class="desc">Thông báo</div>
								<div class="close2">
									<span class="icon-close"> <i class='bx bx-x'></i>
									</span>
								</div>
								<p>Xác nhận xóa Lớp ?</p>
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
<script>
    const selectAllCheckbox = document.getElementById('selectAll');
    const otherCheckboxes = document.querySelectorAll('tbody input[type="checkbox"]');
    // Xử lý sự kiện khi checkbox ở đầu bảng được nhấn
    selectAllCheckbox.addEventListener('change', function () {
        const isChecked = this.checked;
        otherCheckboxes.forEach(checkbox => {
            checkbox.checked = isChecked;
        });
    });
</script>

<script>
    document.addEventListener('DOMContentLoaded', function () {
        const classCheckboxes = document.querySelectorAll('.classCheckbox');
        
        // Additional code to verify the content of each checkbox
        classCheckboxes.forEach((checkbox, index) => {
            console.log(`Checkbox ${index + 1}:`, checkbox.getAttribute('data-maLop'));
        });
        const selectAllCheckbox = document.getElementById('selectAll');
        const otherCheckboxes = document.querySelectorAll('tbody input[type="checkbox"]');
        // Xử lý sự kiện khi checkbox ở đầu bảng được nhấn
        selectAllCheckbox.addEventListener('change', function () {
            const isChecked = this.checked;
            otherCheckboxes.forEach(checkbox => {
                checkbox.checked = isChecked;
            });
        });
        const deleteSelectedClassButton = document.querySelector('.btn-delete-selected-class');
        // Xử lý sự kiện khi nút "Xóa lớp đã chọn" được nhấn
        deleteSelectedClassButton.addEventListener('click', function () {
            const selectedClasses = [];
            classCheckboxes.forEach(checkbox => {
                if (checkbox.checked) {
                    selectedClasses.push(checkbox.getAttribute('data-maLop'));
                    console.log(`Checkbox `, checkbox.getAttribute('data-maLop'));
                }
            });
            // Gửi danh sách mã lớp đến delClass.jsp
            console.log(selectedClasses);
            const selectedClassIds = selectedClasses.join(',');
            console.log(selectedClassIds);
            if (selectedClassIds) {
                window.location.href = `classManage?action=dsDelClass&maLop=`+selectedClassIds;
            } else {
                alert('Vui lòng chọn ít nhất một lớp.');
            }
        });
    });
</script>

<script src="handleJs/confirmDeleteScript1.js"></script>

