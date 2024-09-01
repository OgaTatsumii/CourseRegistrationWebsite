<%@page import="model.Lop"%>
<%@page import="model.Nganh"%>
<%@page import="model.KhoaHoc"%>
<%@page import="java.util.ArrayList"%>
<%@page import=" java.time.format.DateTimeFormatter" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Quản lý lớp</title>
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
				<h3 id="title_infor">Quản lí lớp</h3>
				<%
				} else {
				%>
				<h3 id="title_infor"><%=title%></h3>
				<%
				}
				%>
				<a class="back" href="classManage"> <i
						class='bx bx-left-arrow-alt' style="color:red;margin-left:95%"></i></a>
				<div class="main-content">
					<div class="studentManageContainer">
						<div class="studentManageContent">
							<div class="studentList">
								<div style="display: flex" class="studentHeader">
									<h2 class="title">Danh sách Lớp</h2>
									<button class="addStudent btn-register-selected-class"style="background:red;color:white;">Đăng ký môn</button>
									<button class="addStudent btn-delete-selected-class"style="background:red;color:white">Xóa lớp</button>
								</div>
								<table class="table">
									<thead
										style="background-color: rgb(238, 70, 70); color: white; font-size: 20px; border-color: black;">
										<tr>
											<th style="width: 15%">Xác nhận xóa<input type="checkbox" id="selectAll"></th>
											<th style="width: 20%">Mã Lớp</th>
											<th style="width: 37%">Tên Ngành</th>
											<th style="width: 10%">Số lượng</th>
											<th style="width: 18%">Ngày kết thúc</th>
											<th style="width: 10%"></th>
										</tr>
									</thead>
									<tbody>
										<%
										ArrayList<Lop> dsLop = (ArrayList<Lop>) session.getAttribute("dsXoa");
										if (dsLop!=null){
											for (Lop lop:dsLop) {
										%>
										<tr>
											<td><input type="checkbox" class="classCheckbox" data-maLop="<%=lop.getMaLop()%>"></td>
											<td><a href="#"><%=lop.getMaLop()%></a></td>
											<td style="white-space: normal; word-wrap: break-word; overflow-wrap: break-word;"><%=lop.getNganh().getTenNganh()%></td>
											<td><%=lop.getSoLuongSinhVien()%></td>
											<td><%=lop.getThoiGianKetThuc()%></td>
											<td><a class="btn btn-danger huyButton">Hủy</a></td>
										</tr>
										<%}
											} %>
									</tbody>
								</table>
								<% String message=(String) session.getAttribute("message");
								if(message!=null){ %>
								<p style="font-style:italic;font-size:20px;color:red;text-align:center"><%=message %></p>
								<%} %>
							</div>
						</div>
					</div>
					
				</div>
			</div>
		</div>
	</div>
</body>
</html>


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
        const deleteButtons = document.querySelectorAll('.huyButton');
        
     // Xử lý sự kiện khi nhấn nút "Hủy" để xóa hàng
        deleteButtons.forEach(button => {
            button.addEventListener('click', function() {
                const row = this.closest('tr');
                row.parentNode.removeChild(row);
            });
        });

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
                window.location.href = `classManage?action=delClass&maLop=`+selectedClassIds;
            } else {
                alert('Vui lòng chọn ít nhất một lớp.');
            }
        });
        
        //register
        const registerSelectedClassButton = document.querySelector('.btn-register-selected-class');
        // Xử lý sự kiện khi nút "Xóa lớp đã chọn" được nhấn
        registerSelectedClassButton.addEventListener('click', function () {
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
                window.location.href = `classManage?action=dangKyMonChoLop&maLop=`+selectedClassIds;
            } else {
                alert('Vui lòng chọn ít nhất một lớp.');
            }
        });
    });
</script>

<script src="handleJs/confirmDeleteScript1.js"></script> 

