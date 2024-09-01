<%@page import="model.MonHoc"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Quản lý chương trình học</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style009.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/reset.css" />
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
		<%@ include file="/layout/dashBoard.jsp"%>

		<div class="content">
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
				<a class="back" href="curriculumManage"> <i
						class='bx bx-left-arrow-alt'></i></a>
				<div id="toast"></div>
				<div class="main-content">
					<div class="CurriculumManageContainer">
						<div class="CurriculumManageContent">
							<%
							String nganhTitle = (String) request.getAttribute("nganhTitle");
							%>
							<h2 id="major_title">
								Ngành
								<%=nganhTitle%>
							</h2>
							<%
							HashMap<String, ArrayList<MonHoc>> hashMapMonHoc = (HashMap<String, ArrayList<MonHoc>>) request
									.getAttribute("hocKyMonHocMap");
							%>
							<%
							if (hashMapMonHoc != null) {
							%>
							<%
							ArrayList<String> hocKyList = new ArrayList<>(hashMapMonHoc.keySet());
							%>
							<%
							for (int i = 0; i < hocKyList.size(); i++) {
							%>
							<%
							String hocKy = hocKyList.get(i);
							%>
							<%
							ArrayList<MonHoc> dsMonHoc = hashMapMonHoc.get(hocKy);
							%>
							<div style="display: flex">
								<div class="HocKy">
									<h2 id="semester_title"><%=hocKy%></h2>
								</div>

							</div>
							<div class="CurriculumList">
								<table class="table">
									<thead
										style="background-color: rgb(238, 70, 70); color: white; font-size: 20px; border-color: black;">
										<tr>
											<th style="width: 0%">STT</th>
											<th style="width: 9%">Mã môn học</th>
											<th style="width: 28%">Tên môn học</th>
											<th style="width: 8%">Số tín chỉ</th>
											<th style="width: 5%"></th>
											<th style="width: 5%"></th>
											<th style="width: 5%"></th>

										</tr>
									</thead>
									<tbody>
										<%
										if (dsMonHoc != null) {
										%>
										<%
										for (int j = 0; j < dsMonHoc.size(); j++) {
										%>
										<%
										MonHoc mh = dsMonHoc.get(j);
										%>
										<tr>
											<th scope="row"><%=j + 1%></th>
											<td><%=mh.getMaMonHoc()%></td>
											<td><%=mh.getTenMonHoc()%></td>
											<td><%=mh.getSoTinChi()%></td>
											<td><a
												href="updateCurriculum?CurriculumId=<%=mh.getMaMonHoc()%>"
												class="btn btn-update "
												data-CurriculumId="<%=mh.getMaMonHoc()%>">Chi tiết</a></td>
											<td><a class="btn btn-danger deleteCurriculumButton"
												data-Id="<%=mh.getMaMonHoc()%>" data-maHocKy="<%=hocKy%>">Xóa</a></td>
											<td><a
												href="creditCourseManage/addCreditCourse?CurriculumId=<%=mh.getMaMonHoc()%>"
												class="btn btn-update " >Thêm LTC</a></td>
										</tr>
										<%
										}
										%>
										<tr>
											<th scope="row"><div class="add-new" id="addCourseIcon" data-maHocKy="<%=hocKy%>">
													<i class='bx bxs-plus-circle' ></i>
												</div></th>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
										</tr>
										<%
										}
										%>
									</tbody>
								</table>
							</div>
							<%
							}
							%>
							<%
							}
							%>
						</div>
					</div>
					<div class="modal2">
						<div class="addSubject">
							<h2>Thêm môn học cho học kì</h2>
							<div class="close3">
									<span class="icon-close"> <i class='bx bx-x'></i></span>
								</div>
							<form action="updateCurriculum" method="POST">
							<input type="hidden" id="action" name="action" value="addSubjectForSemester">
							<input type="hidden" id="maHocKyInput" name="maHocKyInput" value="">
							<%String maNganh = (String) request.getAttribute("maNganh"); %>
							<input type="hidden" id="maNganh" name="maNganh" value="<%= maNganh %>">
								<table class="table" style="table-layout: fixed;">
									<thead
										style="background-color: rgb(238, 70, 70); color: white; font-size: 20px; border-color: black;">
										<tr>
											<th style="width: 7%">Chọn</th>
											<th style="width: 7%">STT</th>
											<th style="width: 15%">Mã môn học</th>
											<th style="width: 22%">Tên môn học</th>
											<th style="width: 6%">STC</th>
										</tr>
									</thead>
									<tbody>
										<%
										ArrayList<MonHoc> monHocListAll = (ArrayList<MonHoc>) request.getAttribute("monHocListAll");
										if (monHocListAll != null) {
											for (int j = 0; j < monHocListAll.size(); j++) {
												MonHoc mh = monHocListAll.get(j);
										%>
										<tr>
											<th scope="row"><input type="checkbox"
												name="selectedCourse" value="<%=mh.getMaMonHoc()%>" /></th>
											<th scope="row"><%=j + 1%></th>
											<td><%=mh.getMaMonHoc()%></td>
											<td style="white-space: nowrap; overflow: hidden; text-overflow: ellipsis;"><%=mh.getTenMonHoc()%></td>
											<td><%=mh.getSoTinChi()%></td>
										</tr>
										<%
										}
										}
										%>
									</tbody>
								</table>
								<button type="submit" class="addSubjectBtn">Thêm môn học</button>
							</form>
							<div class="pagination"></div>
						</div>
						
					</div>

					<div class="modal1">
						<div class="confirmDelete">
							<form name="delete-form" action="updateCurriculum" method="POST">
								<input type="hidden" id="action" name="action" value="deleteSubjectForSemester">
								<input type="hidden" id="maNganh" name="maNganh" value="<%= maNganh %>">
								<input type="hidden" id="maHocKyInput" name="maHocKyInput" value="">
								<input type="hidden" name="Id" value="" />
								<div class="desc">Thông báo</div>
								<div class="close2">
									<span class="icon-close"> <i class='bx bx-x'></i></span>
								</div>
								<p>Xóa đi một môn học trong học kỳ ?</p>
								<div class="action">
									<button type="submit" id="btn-delete-subject" class="btn">Đúng</button>
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

//Lấy thẻ div có ID là addCourseIcon
const addCourseIcons = document.querySelectorAll('#addCourseIcon');
const modal2 = document.querySelector(".modal2");
const addSubjectModal =  document.querySelector('.addSubject');
const iconClose3 = document.querySelector(".close3");

// Lắng nghe sự kiện click trên addCourseIcon	
addCourseIcons.forEach(icon => {
    icon.addEventListener('click', function() {
        modal2.classList.add("active");
        addSubjectModal.classList.add('active');
        const maHocKy = this.getAttribute("data-maHocKy");
        const maHocKyInput = document.getElementById('maHocKyInput');
        maHocKyInput.value = maHocKy;
    });
});

iconClose3.addEventListener("click", () => {
	addSubjectModal.classList.remove('active');
	modal2.classList.remove("active");
});

// ---------- Pagination ------------------

// Lấy danh sách môn học và tính số trang
const monHocList = document.querySelectorAll('.addSubject .table tbody tr');
const pagination = document.querySelector('.pagination');
const itemsPerPage = 5; // Số môn học hiển thị trên mỗi trang
let currentPage = 1;

// Hiển thị trang đầu tiên khi modal được mở
showPage(currentPage);

// Function để hiển thị danh sách môn học cho trang hiện tại
function showPage(page) {
    const startIndex = (page - 1) * itemsPerPage;
    const endIndex = Math.min(startIndex + itemsPerPage, monHocList.length);
    monHocList.forEach((monHoc, index) => {
        if (index >= startIndex && index < endIndex) {
            monHoc.style.display = 'table-row';
        } else {
            monHoc.style.display = 'none';
        }
    });
}

// Tạo nút chuyển đến trang trước
const prevButton = document.createElement('button');
prevButton.innerHTML = "<i class='bx bx-left-arrow'></i>";
prevButton.addEventListener('click', () => {
    if (currentPage > 1) {
        currentPage--;
        showPage(currentPage);
        page.textContent = currentPage;
    }
});

const page = document.createElement('h3');
page.textContent = currentPage;

// Tạo nút chuyển đến trang sau
const nextButton = document.createElement('button');
nextButton.innerHTML = "<i class='bx bx-right-arrow' ></i>";
nextButton.addEventListener('click', () => {
    if (currentPage < Math.ceil(monHocList.length / itemsPerPage)) {
        currentPage++;
        showPage(currentPage);
        page.textContent = currentPage;
    }
});

// Thêm nút vào modal
const paginationContainer = document.createElement('div');
paginationContainer.classList.add('pagination');
paginationContainer.appendChild(prevButton);


paginationContainer.appendChild(page);
paginationContainer.appendChild(nextButton);

// Thêm container phân trang vào modal
pagination.appendChild(paginationContainer);


//---------- Toast ------------------

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

<script src="${pageContext.request.contextPath}/handleJs/confirmDeleteScript2.js"></script>