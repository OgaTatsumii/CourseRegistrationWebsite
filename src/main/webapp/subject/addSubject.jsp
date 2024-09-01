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

<%
String errorMessageMaMonHoc = (String) request.getAttribute("errorMessageMaMonHoc");
if (errorMessageMaMonHoc == null) {
	errorMessageMaMonHoc = "";
}
String errorMessageTenMonHoc = (String) request.getAttribute("errorMessageTenMonHoc");
if (errorMessageTenMonHoc == null) {
	errorMessageTenMonHoc = "";
}

String maMonHoc = (String) request.getAttribute("maMonHoc");
String tenMonHoc = (String) request.getAttribute("tenMonHoc");
String soTinChi = String.valueOf(request.getAttribute("soTinChi"));

maMonHoc = (maMonHoc == null) ? "" : maMonHoc;
tenMonHoc = (tenMonHoc == null) ? "" : tenMonHoc;
soTinChi = (soTinChi == null) ? "" : soTinChi;

%>

<body>
	<div class="adminArea">
		<!-- Include DashBoard -->
		<%@ include file="/layout/dashBoard.jsp"%>

		<div class="content">
			<!-- Include NavBar -->
			<jsp:include page="/layout/navbar.jsp" />
			<div class="main">
				<div class="addStudentTitle" style="display: flex;">
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
					<a class="back" href="subjectManagement"> <i
						class='bx bx-left-arrow-alt'></i></a>
				</div>
				<div id="toast"></div>
				<div class="main-content">
					<div class="form-container">
						<form id="formAddMonHoc" action="addSubject" method="POST">
							<div class="form-content">
								<h2>Thông tin môn học</h2>
								<div class="form-group">
									<div class="idStudentContent">
										<label for="MaMonHoc">Mã Môn Học :</label> <input  style="width:90%" type="text"
											id="MaMonHoc" name="MaMonHoc" value="<%=maMonHoc%>" required />
										<p class="error">
											<%=errorMessageMaMonHoc%>
										</p>
									</div>
								</div>

								<div class="form-group">
									<div class="phoneContent">
										<label for="SDT">Tên môn học :</label> <input style="width:125%" type="text"
											id="TenMonHoc" name="TenMonHoc" value="<%=tenMonHoc%>"
											required />
										<p class="error">
											<%=errorMessageTenMonHoc%>
										</p>
									</div>
								</div>

								<div class="form-group">
									<div class="idContent">
										<label for="SoTinChi">Số tín chỉ :</label> <input
											 style="width:110%" type="number" id="SoTinChi" name="SoTinChi"
											value="<%=soTinChi%>" min="1" max="4" required />
									</div>
								</div>

							</div>
							<button class="addSubjectButton" type="submit">Thêm Môn Học</button>
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

const messageToast = "<%=session.getAttribute("messageToast")%>";
if (messageToast != 'null') {
    toast();
}
<%session.removeAttribute("messageToast");%>

</script>

<script src="${pageContext.request.contextPath}/handleJs/addStudentScript.js"></script>




