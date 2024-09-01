const maSinhVienInput = document.getElementById('MaSinhVien');
	const emailInput = document.getElementById('Email');
	const userInput = document.getElementById('User');
	const passWordInput = document.getElementById('');
	const ngaySinhInput = document.getElementById('NgaySinh');
	const passwordInput = document.getElementById('Password');

	// Bắt sự kiện khi trường ngày sinh thay đổi
	ngaySinhInput.addEventListener('input', function() {
		const ngaySinh = ngaySinhInput.value;
		const parts = ngaySinh.split('-');
		passwordInput.value = parts[2] + parts[1] + parts[0].substring(2);
		;
	});

	// Bắt sự kiện khi trường mã sinh viên thay đổi
	maSinhVienInput.addEventListener('input', function() {
		const maSinhVien = maSinhVienInput.value.trim();
		const emailDomain = '@student.ptithcm.edu.vn';

		// Tạo email từ mã sinh viên
		const email = maSinhVien.toLowerCase() + emailDomain;
		// Gán giá trị email vào trường Email
		emailInput.value = email;

		// Tạo tài khoản từ mã sinh viên
		const taiKhoan = maSinhVien;
		// Gán giá trị tài khoản vào trường User
		userInput.value = taiKhoan;
	});

	const togglePassword = document.querySelector('#togglePassword');
	const password = document.querySelector('#Password');

	togglePassword.addEventListener('click', function(e) {
		// Toggle the type attribute of the password input field
		const type = password.getAttribute('type') === 'password' ? 'text'
				: 'password';
		password.setAttribute('type', type);

		// Toggle the eye icon between open and closed eyes
		const eyeIcon = this.querySelector('i');
		eyeIcon.classList.toggle('bx-low-vision');
		eyeIcon.classList.toggle('bx-show-alt');
	});