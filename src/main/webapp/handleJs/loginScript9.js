// script.js

document.addEventListener("DOMContentLoaded", function () {
    const usernameInput = document.getElementById("usernameInput");
    const passwordInput = document.getElementById("passwordInput");
    const containerUserName =
        document.getElementsByClassName("input_box_userName")[0];
    const containerPassword =
        document.getElementsByClassName("input_box_passWord")[0];
    const usernameLabel = document.getElementsByClassName("warning_user")[0];
    const passwordLabel =
        document.getElementsByClassName("warning_password")[0];
	const errorLogin = document.getElementsByClassName("errorLogin")[0];
    usernameInput.focus();
    usernameInput.addEventListener("blur", function () {
        // Kiểm tra nếu ô tên đăng nhập trống và không có focus
        if (usernameInput.value.trim() === "") {
            usernameLabel.textContent = "Tên đăng nhập là bắt buộc";
            containerUserName.classList.add("input_error");
        }
    });

    usernameInput.addEventListener("input", function () {
        // Kiểm tra nếu có nhập vào ô tên đăng nhập, xóa thông báo
        if (usernameInput.value.trim() !== "") {
            usernameLabel.textContent = "";
            errorLogin.textContent="";
            containerUserName.classList.remove("input_error");
        }
    });

    passwordInput.addEventListener("blur", function () {
        if (passwordInput.value.trim() === "") {
            passwordLabel.textContent = "Mật khẩu là bắt buộc";
            containerPassword.classList.add("input_error");
        }
    });

    passwordInput.addEventListener("input", function () {
        if (passwordInput.value.trim() !== "") {
            passwordLabel.textContent = "";
            errorLogin.textContent="";
            containerPassword.classList.remove("input_error");
        }
    });
    
    
});

    document.addEventListener("DOMContentLoaded", function () {
    // Lấy phần tử login form
    const loginForm = document.querySelector(".login_form");

    // Thêm lớp 'show' để kích hoạt hiệu ứng fade-in
    setTimeout(function () {
        loginForm.classList.add("show");
    }, 200);
});
/**
 * 
 */