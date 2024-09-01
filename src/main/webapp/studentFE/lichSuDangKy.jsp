<%@page import="model.CTLopTinChi"%>
<%@page import="model.NamHoc"%>
<%@page import="model.HocKyNH"%>
<%@page import="model.HocKy"%>
<%@page import="model.Lop"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.time.LocalDateTime"%>
<%@page import="model.LopTinChi"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Thông tin cá nhân</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/style009.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/reset.css" />
<link href="https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css"
	rel="stylesheet" />
</head>
<script src="https://kit.fontawesome.com/7b97f02ae4.js"
	crossorigin="anonymous"></script>
<style>
.disabled-row {
	pointer-events: none; /* Không thể bấm chọn */
	opacity: 0.5; /* Làm mờ */
}

.disabled-row-2 {
	pointer-events: none; /* Không thể bấm chọn */
	opacity: 0.5; /* Làm mờ */
}
</style>

<%
String namHocAllowed = (String) session.getAttribute("namHocAllowed");
String hocKyAllowed = (String) session.getAttribute("hocKyAllowed");
DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm dd/MM/yyyy");
DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");
DateTimeFormatter formatterSubmit = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
LocalDateTime currentTime = LocalDateTime.now();
String action = (String) request.getAttribute("action");
action = (action == null) ? "" : action;
namHocAllowed = (namHocAllowed == null) ? "" : namHocAllowed;
hocKyAllowed = (hocKyAllowed == null) ? "" : hocKyAllowed;
%>

<body>
	<div class="adminArea">
		<%@ include file="/layout/dashBoardSinhVien.jsp"%>
		<div class="content">
			<jsp:include page="/layout/navbarSinhVien.jsp" />
			<div class="main">
				<h3 id="title_infor" style="margin-bottom: 32px;">Kết quả đăng
					ký môn học</h3>
				<div id="toast"></div>
				<div class="main-content">
					<div class="studentManageContainer">
						<div class="studentManageContent">
							<div class="searchStudent">
								<form id="searchCourseForm" action="registerCourseHistory"
									method="POST">
									<input type="hidden" name="action" value="searchCreditCourseHistory">
									<div style="display: flex;">
										<div class="">
											<label style="display: block" for="courseSelect">Năm
												học:</label> <select name="maNamHoc" id="courseSelect">
												<%
												ArrayList<NamHoc> dsNamHoc = (ArrayList<NamHoc>) session.getAttribute("dsNamHoc");
												if (dsNamHoc != null && !dsNamHoc.isEmpty()) {
													for (int i = 0; i < dsNamHoc.size(); i++) {
														NamHoc namHoc = dsNamHoc.get(i);
												%>
												<option value="<%=namHoc.getMaNamHoc()%>"
													<%=namHocAllowed.equals(namHoc.getMaNamHoc()) ? "selected" : ""%>>
													<%=namHoc.getTenNamHoc()%>
												</option>
												<%
												}
												}
												%>
											</select>
										</div>
										<div class="">
											<label style="display: block" for="">Học kỳ</label> <select
												name="maHocKy" id="lopSelect">
												<%
												ArrayList<HocKyNH> dsHocKyNH = (ArrayList<HocKyNH>) session.getAttribute("dsHocKyNH");
												if (dsHocKyNH != null && !dsHocKyNH.isEmpty()) {
													for (int i = 0; i < dsHocKyNH.size(); i++) {
														HocKyNH hocKyNH = dsHocKyNH.get(i);
												%>
												<option value="<%=hocKyNH.getMaHocKy()%>"
													<%=hocKyAllowed.equals(hocKyNH.getMaHocKy()) ? "selected" : ""%>>
													<%=hocKyNH.getMaHocKy()%>
												</option>
												<%
												}
												}
												%>
											</select>
										</div>
										<button class="btn" type="submit">Tìm kiếm</button>
									</div>
								</form>

							</div>
							<div class="studentList">
								<div style="display: flex" class="studentHeader">
									<h2 class="title" style="font-size: 25px">Danh sách môn
										học đã đăng ký</h2>
								</div>

								<table class="table" id="sourceTable">
									<thead
										style="background-color: rgb(238, 70, 70); color: white; font-size: 20px; border-color: black;">
										<tr>
											<th style="width: 13%">Mã MH</th>
											<th style="width: 36%">Tên môn học</th>
											<th style="width: 8%; padding: 11px">Số TC</th>
											<th style="width: 22%; padding: 11px">Ngày bắt đầu</th>
											<th style="width: 12%; padding: 5px">Thứ</th>
											<th style="width: 10%; padding: 8px">Phòng</th>
											<th style="width: 21%; padding: 8px">Thời gian đăng ký</th>
										</tr>
									</thead>

									<tbody>
										<%
										ArrayList<CTLopTinChi> dsCtLopTinChi = (ArrayList<CTLopTinChi>) session.getAttribute("dsCtLopTinChi");
										if (dsCtLopTinChi != null && !dsCtLopTinChi.isEmpty()) {
											for (int i = 0; i < dsCtLopTinChi.size(); i++) {
												CTLopTinChi ctLopTinChi = dsCtLopTinChi.get(i);
										%>
										<tr>
											<td><%=ctLopTinChi.getLopTinChi().getMonHoc().getMaMonHoc()%></td>
											<td><%=ctLopTinChi.getLopTinChi().getMonHoc().getTenMonHoc()%></td>
											<td><%=ctLopTinChi.getLopTinChi().getMonHoc().getSoTinChi()%></td>
											<td><%=ctLopTinChi.getLopTinChi().getNgayBatDau()%></td>
											<td><%=ctLopTinChi.getLopTinChi().getThu()%></td>
											<td><%=ctLopTinChi.getLopTinChi().getPhong()%></td>
											<td><%=ctLopTinChi.getTgdk()%></td>
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
							</div>



						</div>
					</div>

					<div class="modal1">
						<div class="confirmDelete">
							<form id="delete-Course-form" name="delete-Course-form"
								action="registerCourse" method="POST">
								<input type="hidden" name="Id" value="" /> <input type="hidden"
									name="studentId" value="<%=sinhVien.getMaSinhVien()%>" /> <input
									type="hidden" name="action" value="cancelCreditCourse" />
								<div class="desc">Thông báo</div>
								<div class="close2">
									<span class="icon-close"> <i class='bx bx-x'></i>
									</span>
								</div>
								<p>Xác nhận hủy học phần ?</p>
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
</body>

</html>

<script>
		document.addEventListener("DOMContentLoaded", function() {
		    var buttons = document.querySelectorAll('#sourceTable tbody tr td:first-child a.btn-add');
		    var xacNhanButton = document.getElementById('xacNhan');
		    var form = document.getElementById('myForm');
		    
		    const delete_form = document.getElementById('delete-Course-form');;
		    const deleteButtons = document.querySelectorAll('.btn-danger');
		    const modal1 = document.querySelector(".modal1");
		    const confirmDeleteModal = document.querySelector('.confirmDelete');
		    const cancelButton = document.querySelector('.denied');
		    const iconClose2 = document.querySelector(".close2");
		    
		    deleteButtons.forEach(button => {
		    	button.addEventListener('click', function() {
		    		modal1.classList.add("active");
		    		confirmDeleteModal.classList.add('active');
		    		const ID = this.getAttribute("data-Id");
		    		console.log(ID);
		    		delete_form.elements['Id'].value = ID;
		    	
		    });


		    cancelButton.addEventListener('click', function() {
		    	confirmDeleteModal.classList.remove('active');
		    	modal1.classList.remove("active");
		    });

		    iconClose2.addEventListener("click", () => {
		    	confirmDeleteModal.classList.remove('active');
		    	modal1.classList.remove("active");
		    });


		    });
		    
		    var rows = document.querySelectorAll('#sourceTable tbody tr');
		    rows.forEach(function(row) {
		        var remaining = parseInt(row.cells[6].innerText);
		        
		        if (remaining == 0) {
		            row.classList.add('disabled-row');
		        }
		    });
		    		    
		    function updateXacNhanButton() {
		    	
		    	var totalCredits = 0;
		        var registedTotalCredits = 0;
		        var sourcerows = document.querySelectorAll('#sourceTable tbody tr');
		        var rows = document.querySelectorAll('#targetTable tbody tr');
		        
		        rows.forEach(function(row) {
		            var credits = parseInt(row.querySelector('td:nth-child(4)').innerText);
		            totalCredits += credits;
		        });

		        if (totalCredits > 0 && totalCredits <= 10) {
		            xacNhanButton.classList.remove('disabled-row');
		        } else {
		            xacNhanButton.classList.add('disabled-row');
		        }
		        
		        var isRegistedRows = document.querySelectorAll('#registedTable tbody tr');
		        
		        isRegistedRows.forEach(function(row) {
		            registedTotalCredits += parseInt(row.cells[3].innerText);
		        }); 
		        		        
		        if (registedTotalCredits + totalCredits > 10) {
		        	sourcerows.forEach(function(row) {
		                row.classList.add('disabled-row-2');
		            });
		        	xacNhanButton.classList.add('disabled-row-2');
		        } else {
		        	sourcerows.forEach(function(row) {
		                row.classList.remove('disabled-row-2');
		            });
		        	xacNhanButton.classList.remove('disabled-row-2');
		        }
		    			   
		    	
		        
		    }


		    buttons.forEach(function(button) {
		        button.addEventListener('click', function() {
		            var row = this.closest('tr');
		            var selectCell = document.createElement('td');
		            selectCell.style.whiteSpace = 'normal';
		            selectCell.style.wordWrap = 'break-word';
		            selectCell.style.overflowWrap = 'break-word';
		            selectCell.innerHTML = '<a href="#" class="btn btn-danger">Xóa</a>';

		            var currentDate = new Date();
		            var formattedDate = "<%=currentTime.format(formatterSubmit)%>";

		            var cells = row.querySelectorAll('td:nth-child(2), td:nth-child(3), td:nth-child(4), td:nth-child(5), td:nth-child(6), td:nth-child(7), td:nth-child(8)');
		            var newRow = document.createElement('tr');
		            newRow.appendChild(selectCell);

		            var formattedDateCell = document.createElement('td');
		            formattedDateCell.innerHTML = formattedDate;

		            cells.forEach(function(cell, index) {
		                var newCell = document.createElement('td');
		                if (index == 4) {
		                    newRow.appendChild(formattedDateCell);
		                } else if (index == 5) {
		                    newCell.innerHTML = "CDK";
		                    newRow.appendChild(newCell);
		                } else {
		                    newCell.innerHTML = cell.innerHTML;
		                    newRow.appendChild(newCell);
		                }
		            });
		            

		            document.querySelector('#targetTable tbody').appendChild(newRow);
		            row.classList.add('disabled-row');

		            // Create hidden inputs
		            var maLopTinChi = this.getAttribute('data-creditID');
		            var maSinhVien = "<%=sinhVien.getMaSinhVien()%>";
		            var thoiGianDangKy = formattedDate;

		            var hiddenMaLopTinChi = document.createElement('input');
		            hiddenMaLopTinChi.type = 'hidden';
		            hiddenMaLopTinChi.name = 'maLopTinChi[]';
		            hiddenMaLopTinChi.value = maLopTinChi;
		            form.appendChild(hiddenMaLopTinChi);

		            var hiddenMaSinhVien = document.createElement('input');
		            hiddenMaSinhVien.type = 'hidden';
		            hiddenMaSinhVien.name = 'maSinhVien[]';
		            hiddenMaSinhVien.value = maSinhVien;
		            form.appendChild(hiddenMaSinhVien);

		            var hiddenThoiGianDangKy = document.createElement('input');
		            hiddenThoiGianDangKy.type = 'hidden';
		            hiddenThoiGianDangKy.name = 'thoiGianDangKy[]';
		            hiddenThoiGianDangKy.value = thoiGianDangKy;
		            form.appendChild(hiddenThoiGianDangKy);

		            selectCell.querySelector('a.btn-danger').addEventListener('click', function() {
		                newRow.remove();
		                row.classList.remove('disabled-row');

		                // Remove corresponding hidden inputs
		                form.removeChild(hiddenMaLopTinChi);
		                form.removeChild(hiddenMaSinhVien);
		                form.removeChild(hiddenThoiGianDangKy);

		                updateXacNhanButton();
		            });

		            updateXacNhanButton();
		        });
		    });
		    
		    updateXacNhanButton();

		    xacNhanButton.addEventListener('click', function() {
		        document.getElementById('myForm').submit();
		    });

		    document.getElementById("refreshButton").onclick = function() {
		        location.reload();
		    };
		    
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

		    updateXacNhanButton();
		});
		
		document.getElementById('lopSelect').addEventListener('change', function() {
	        document.getElementById('searchForm').submit();
	    });
		
		function submitForm() {
            const selectElement = document.getElementById('courseSelect');
            const selectedOption = selectElement.options[selectElement.selectedIndex];
            const form = document.getElementById('searchCourseForm');
            

            // Remove any existing hidden input for action
            const existingInput = document.querySelector('input[name="action"]');
            if (existingInput) {
                existingInput.remove();
            }

            // Create a hidden input to hold the action value
            const hiddenInput = document.createElement('input');
            hiddenInput.type = 'hidden';
            hiddenInput.name = 'action';
            hiddenInput.value = selectedOption.value;
            form.appendChild(hiddenInput);

            // Submit the form
            form.submit();
        }
		
		</script>

