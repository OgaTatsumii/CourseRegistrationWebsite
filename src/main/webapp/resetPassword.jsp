<%@page import="model.NoiDungThongBao"%>
<%@page import="model.LoaiThongBao"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

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
<script src="https://kit.fontawesome.com/7b97f02ae4.js"
	crossorigin="anonymous"></script>
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
<body>
	<div>
		<div style="margin-top: 80px; text-align: center">
			<h2
				style="font-weight: bold; font-size: 30px; color: red; margin-bottom: 0">Thay
				đổi mật khẩu</h2>
			<i class="fa-solid fa-minus" style="color: red; font-size: 30px;"></i>
			<p style="font-size: 16px; margin-top: 0; font-style: italic">Mật
				khẩu có thể thay đổi ở bên dưới</p>

		</div>
		<div style="text-align: center; margin-right: 300px">
			<form action="passwordController" method="post" onsubmit="return validatePasswords()">
				<input type="hidden" name="email" value="${email}">
				<p style="margin: 0; font-size: 18px">Nhập mật khẩu mới:</p>
				<input id="newPassword" type="password" name="newPassword"
					style="margin-left: 250px; width: 400px; border-radius: 4px; border: 1px solid #ccc; outline: none; border: 2px solid #ccc; height: 35px"
					required>
				<p style="margin: 0; font-size: 18px;margin-left:25px">Nhập lại mật khẩu mới:</p>
				<input id="confirmPassword" type="password" name="confirmPassword"
					style="margin-left: 250px; width: 400px; border-radius: 4px; border: 1px solid #ccc; outline: none; border: 2px solid #ccc; height: 35px"
					required>
				<div id="error-message" class="error-message"
					style="color: red; margin-left: 230px"></div>
				<button style="display: block; margin-left: 57%; margin-top: 20px"
					class="btn btn-danger" type="submit" name="action" value="doiMatKhau">Xác nhận</button>
			</form>
		</div>
	</div>
</body>
<script>
function validatePasswords() {
    var newPassword = document.getElementById('newPassword').value;
    var confirmPassword = document.getElementById('confirmPassword').value;
    var errorMessage = document.getElementById('error-message');

 // Kiểm tra độ dài của mật khẩu mới
    if (newPassword.length < 6) {
        errorMessage.textContent = 'Mật khẩu mới phải có ít nhất 6 ký tự.';
        errorMessage.style.display = 'block';
        return false;
    }


    // Kiểm tra xem mật khẩu mới có chứa ít nhất một ký tự in hoa không
    var hasUppercase = /[A-Z]/.test(newPassword);
    if (!hasUppercase) {
        errorMessage.textContent = 'Mật khẩu mới phải chứa ít nhất một ký tự in hoa.';
        errorMessage.style.display = 'block';
        return false;
    }
    
 // Kiểm tra xem mật khẩu mới có chứa ít nhất một ký tự đặc biệt không
    var hasSpecialChar = /[!@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?]/.test(newPassword);
    if (!hasSpecialChar) {
        errorMessage.textContent = 'Mật khẩu mới phải chứa ít nhất một ký tự đặc biệt (!@#$%...)';
        errorMessage.style.display = 'block';
        return false;
    }

    // Kiểm tra mật khẩu mới và mật khẩu xác nhận
    if (newPassword !== confirmPassword) {
        errorMessage.textContent = 'Mật khẩu không khớp. Vui lòng nhập lại.';
        errorMessage.style.display = 'block';
        return false;
    } else {
        errorMessage.style.display = 'none';
        return true;
    }
}


</script>
</html>