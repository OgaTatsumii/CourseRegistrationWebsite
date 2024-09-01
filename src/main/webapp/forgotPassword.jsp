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
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"
        integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r"
        crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js"
        integrity="sha384-0pUGZvbkm6XF6gxjEnlmuGrJXVbNuzT9qBBavbLwCsOGabYfZo0T0to5eqruptLy"
        crossorigin="anonymous"></script>
    <script src="https://kit.fontawesome.com/7b97f02ae4.js"
        crossorigin="anonymous"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
        rel="stylesheet"
        integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
        crossorigin="anonymous">
</head>
<header>
    <div class="max-width" style="height: 150px">
        <img src="image/banner_ptit.jpg" alt="banner ptit">
    </div>
    <div>
        <nav class="navbar navbar-expand-lg bg-body-tertiary navbar-no-padding-top">
            <div class="container-fluid" style="background-color: rgb(222, 34, 25); height: 53px">
                <ul class="navbar-nav me-auto mb-2 mb-lg-0" style="margin-left: 5%">
                    <li class="nav-item"><a class="nav-link font-white" href="#">TRANG CHỦ</a></li>
                    <li class="nav-item"><a class="nav-link font-white" href="#">TRA CỨU VĂN BẰNG</a></li>
                    <li class="nav-item"><a class="nav-link font-white" href="#">ĐĂNG KÝ HỌC PHẦN</a></li>
                    <li class="nav-item"><a class="nav-link font-white" href="#">NGÀNH</a></li>
                    <li class="nav-item"><a class="nav-link font-white" href="#">THANH TOÁN HỌC PHÍ</a></li>
                </ul>
                <form style="margin-right: 10%" action="/DoAnDKHP_Nhom14/login.jsp">
                    <button class="btn border border-white" type="submit" style="color: white; background-color:">Đăng nhập</button>
                </form>
            </div>
        </nav>
    </div>
</header>
<body>
    <div>
        <div style="margin-top:80px;text-align:center">
            <h2 style="font-weight: bold;font-size:30px;color:red;margin-bottom:0">Quên Mật Khẩu</h2>
            <i class="fa-solid fa-minus" style="color:red;font-size:30px;"></i>
            <p style="font-size:16px;margin-top:0;font-style:italic">Nhập email chúng tôi sẽ gửi đến bạn hướng dẫn</p>
        </div>
        <div style="text-align:center;margin-right:300px">
            <form action="passwordController" method="get">
                <p style="margin:0;margin-right:70px;font-size:18px">Nhập email sinh viên:</p>
                <input type="email" name="email" style="margin-left:250px;width:400px; border-radius:4px;border: 1px solid #ccc;outline: none;border: 2px solid #ccc;height:35px" required> 
                <button class="btn btn-danger" type="submit">Xác nhận</button>
                <% String message = (String) request.getSession().getAttribute("message2");
                   if (message != null) { %>
                    <p><%= message %></p>
                <% } %>
            </form>
            <% String token = (String) request.getSession().getAttribute("token");
            String email = (String) request.getSession().getAttribute("email");
               if (token!=null && !token.isEmpty()) { %>
            <form action="passwordController" method="post" onsubmit="validateToken(event)">
                <input type="hidden" name="token" value="<%= token %>">
                <input type="hidden" name="email" value="<%= email %>">
                <p style="margin:0;margin-right:70px;font-size:18px">Nhập mã xác nhận:</p>
                <input type="text" name="maToken" style="margin-left:250px;width:400px; border-radius:4px;border: 1px solid #ccc;outline: none;border: 2px solid #ccc;height:35px" required> 
                <button class="btn btn-danger" type="submit" name="action" value="nhapMatKhauMoi">Xác nhận</button>
            <% } %>
            </form>
        </div>
    </div>
</body>
<script>
        // JavaScript function to validate the token
        function validateToken(event) {
            const maToken = document.querySelector('input[name="maToken"]').value;
            const token = document.querySelector('input[name="token"]').value;

            if (maToken !== token) {
                event.preventDefault(); // Prevent form submission
                alert('Mã xác nhận không đúng. Vui lòng thử lại.');
            }
        }
    </script>
</html>
