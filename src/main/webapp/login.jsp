<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Document</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style009.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/reset.css" />
<link rel="preconnect" href="https://fonts.googleapis.com" />
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
<link
	href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;500;600&family=Poppins:wght@300;400;500;600&family=Sora:wght@600;700;800&display=swap"
	rel="stylesheet" />
<link href="https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css"
	rel="stylesheet" />
<script src="${pageContext.request.contextPath}/handleJs/loginScript9.js"></script>
</head>

<style>
  body, body * {
    box-sizing: content-box;
  }
</style>

<body>
	<div class="AdminHomePage">
		<div class="imagePTIT">
			<img class="thumpNail" src="image/2022-10-17.jpg"
				alt="Anh truong PTIT" />
		</div>
		<div class="login">
			<div class="information">
				<div class="logoPTIT">
					<img
						src="image/Logo Học Viện Công Nghệ Bưu Chính Viễn Thông - PTIT(Simple).svg"
						alt="logoPTIT" />
				</div>
				<h1>
					<span>HỌC VIỆN CÔNG NGHỆ BƯU CHÍNH VIỄN THÔNG</span> <span>CƠ
						SỞ TẠI TP. HỒ CHÍ MINH</span>
				</h1>
			</div>
			<div class="login_form">
				<form action="login" method="POST">
					<div class="inf">
						<h2>ĐĂNG NHẬP</h2>
						<label>Cổng thông tin đào tạo</label>
					</div>
					<div class="input_box input_box_userName">
						<i class="bx bxs-user"></i> <input id="usernameInput" name="user"
							type="text" placeholder="Tên đăng nhập" required />
						<p class="warning warning_user"></p>
					</div>
					<div class="input_box input_box_passWord">
						<i class="bx bxs-lock-alt"></i> <input id="passwordInput"
							name="password" type="password" placeholder="Mật khẩu" required />
						<p class="warning warning_password"></p>
					</div>
									<a href="doiMatKhau.jsp" class="forget_password">Đổi mật khẩu</a>
					
					<%
					String baoLoi = (String) request.getAttribute("baoLoi");
					if (baoLoi == null){
						baoLoi = "";
					}
					%>
					<p class="errorLogin">
						<%=baoLoi%>
					</p>
					<button type="submit" class="btn">Đăng nhập</button>
				</form>
				<hr style="margin-top: 20px" />
				<a href="forgotPassword.jsp" class="forget_password">Sinh viên quên mật khẩu ?</a>
			</div>
		</div>
	</div>
</body>
</html>
