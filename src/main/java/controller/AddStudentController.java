package controller;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.HocKyDAO;
import database.KhoaDAO;
import database.LopDAO;
import database.KhoaHocDAO;
import database.NganhDAO;
import database.QueQuanDAO;
import database.SinhVienDAO;
import database.TaiKhoanDAO;
import model.ChucVu;
import model.HocKy;
import model.Khoa;
import model.Lop;
import model.KhoaHoc;
import model.Nganh;
import model.QueQuan;
import model.SinhVien;
import model.TaiKhoan;

/**
 * Servlet implementation class AddStudentController
 */
@WebServlet("/addStudent")
public class AddStudentController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddStudentController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = "/student/addStudent.jsp";
		String title = "Thêm sinh viên";
		request.setAttribute("title", title);
		
		NganhDAO nganhDAO = new NganhDAO();
		ArrayList<Nganh> dsNganh = nganhDAO.selectAll();
		
		HocKyDAO hocKyDAO = new HocKyDAO();
		ArrayList<HocKy> dsHocKy = hocKyDAO.selectAll();
		
		HttpSession session = request.getSession();
		session.setAttribute("dsNganh", dsNganh);
		session.setAttribute("dsHocKy", dsHocKy);
		RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String maSinhVien = request.getParameter("MaSinhVien");
		String hoTen = request.getParameter("HoTen");
		String ngaySinhStr = request.getParameter("NgaySinh"); // yyyy-mm-dd
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate ngaySinh = null;
		String gioiTinh = request.getParameter("GioiTinh");
		String soDienThoai = request.getParameter("SDT");
		String cccd = request.getParameter("CCCD");
		String email = request.getParameter("Email");
		String tamTru = request.getParameter("TamTru");
		String maLop = request.getParameter("MaLop");
		String maTinh = request.getParameter("MaTinh");
		String taiKhoan = request.getParameter("User");
		String matKhau = request.getParameter("Password");
		
		SinhVienDAO sinhVienDAO = new SinhVienDAO();
		String error = "";
		
		request.setAttribute("errorMessage", "Không thể thêm sinh viên mới");
		request.setAttribute("MaSinhVien", maSinhVien);
		request.setAttribute("HoTen", hoTen);
		request.setAttribute("NgaySinh", ngaySinhStr);
		request.setAttribute("GioiTinh", gioiTinh);
		request.setAttribute("SDT", soDienThoai);
		request.setAttribute("CCCD", cccd);
		request.setAttribute("Email", email);
		request.setAttribute("TamTru", tamTru);
		request.setAttribute("MaLop", maLop);
		request.setAttribute("MaTinh", maTinh);
		request.setAttribute("User", taiKhoan);
		request.setAttribute("Password", matKhau);
		
		boolean isValidMaSinhVien = Validator.validateMaSinhVien(maSinhVien);
		boolean isValidHoTen = Validator.validateHoTen(hoTen);
		boolean isValidSoDienThoai = Validator.validateSoDienThoai(soDienThoai);
		boolean isValidCCCD = Validator.validateCCCD(cccd);
		boolean isValidEmail = Validator.validateEmail(email);
		

		String errorMessageMaSinhVien = "";
		String errorMessageHoTen = "";
		String errorMessageSoDienThoai = "";
		String errorMessageCCCD = "";
		String errorMessageEmail = "";
		String errorMessageDate = "";

		if (!isValidMaSinhVien) {
			errorMessageMaSinhVien = "Cú pháp mã sinh viên không hợp lệ.";
			request.setAttribute("errorMessageMaSinhVien", errorMessageMaSinhVien);
		}

		if (!isValidHoTen) {
			errorMessageHoTen = "Họ tên không hợp lệ.";
			request.setAttribute("errorMessageHoTen", errorMessageHoTen);
		}
		
		try {
	        ngaySinh = LocalDate.parse(ngaySinhStr, formatter);
	        boolean isValidAge = Validator.validateNgaySinh(ngaySinh);
	        if (ngaySinh!= null && !isValidAge) {
				errorMessageDate = "Sinh viên phải đủ 18 tuổi.";
		        request.setAttribute("errorMessageDate", errorMessageDate);
		    }
	    } catch (DateTimeParseException e) {
	    	errorMessageDate = "Định dạng ngày sinh không hợp lệ.";
	    	request.setAttribute("errorMessageDate", errorMessageDate);
	    }
		
		if (!isValidSoDienThoai) {
			errorMessageSoDienThoai = "Số điện thoại không hợp lệ.";
			request.setAttribute("errorMessageSoDienThoai", errorMessageSoDienThoai);
		}
		if (!isValidCCCD) {
			errorMessageCCCD = "Số CCCD không hợp lệ.";
			request.setAttribute("errorMessageCCCD", errorMessageCCCD);
		}
		if (!isValidEmail) {
			errorMessageEmail = "Email không hợp lệ.";
			request.setAttribute("errorMessageEmail", errorMessageEmail);
		}
		if(sinhVienDAO.selectByCCCD(cccd)) {
			errorMessageCCCD = "Số CCCD đã tồn tại !";
			request.setAttribute("errorMessageCCCD", errorMessageCCCD);
		}
		if(sinhVienDAO.selectBySDT(soDienThoai)) {
			errorMessageSoDienThoai = "Số điện thoại đã tồn tại !";
			request.setAttribute("errorMessageSoDienThoai", errorMessageSoDienThoai);
		}
		
		if (!errorMessageMaSinhVien.isEmpty() || !errorMessageHoTen.isEmpty() || !errorMessageSoDienThoai.isEmpty()
				|| !errorMessageCCCD.isEmpty() || !errorMessageEmail.isEmpty()) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/student/addStudent.jsp");
			dispatcher.forward(request, response);
		} else {
			TaiKhoan newTaiKhoan = new TaiKhoan();
			newTaiKhoan.setUserName(taiKhoan);
			newTaiKhoan.setMatKhau(matKhau);
			ChucVu chucVu = new ChucVu();
			chucVu.setMaChucVu("SV");
			chucVu.setTenChucVu("Sinh Viên");
			newTaiKhoan.setChucVu(chucVu);
			TaiKhoanDAO taiKhoanDAO = new TaiKhoanDAO();
			taiKhoanDAO.insert(newTaiKhoan);

			// Tạo đối tượng SinhVien và set giá trị
			SinhVien sinhVien = new SinhVien();
			sinhVien.setMaSinhVien(maSinhVien);
			sinhVien.setHoTen(hoTen);
			sinhVien.setNgaySinh(ngaySinh);
			sinhVien.setGioiTinh(gioiTinh);
			sinhVien.setSoDienThoai(soDienThoai);
			sinhVien.setCCCD(cccd);
			sinhVien.setEmail(email);
			sinhVien.setTamTru(tamTru);
			Lop lop = (new LopDAO()).selectByID(maLop);
			sinhVien.setLop(lop);
			QueQuan queQuan = (new QueQuanDAO()).selectByID(maTinh);
			sinhVien.setQueQuan(queQuan);
			sinhVien.setTaiKhoan(newTaiKhoan);

			// Sử dụng DAO để lưu sinh viên vào cơ sở dữ liệu
			
			int result = sinhVienDAO.insert(sinhVien);

			// Kiểm tra kết quả và phản hồi
			if (result != 0) {
				HttpSession session = request.getSession();
				session.setAttribute("titleToast", "Thành công");	
				session.setAttribute("messageToast", "Tạo sinh viên thành công !");	
				session.setAttribute("type", "success");	
				session.setAttribute("icon", "bxs-check-circle");	
				response.sendRedirect(request.getContextPath() + "/studentManage");

			} else {
				HttpSession session = request.getSession();
				session.setAttribute("titleToast", "Thất bại");	
				session.setAttribute("messageToast", "Mã sinh viên đã tồn tại !");	
				session.setAttribute("type", "error");	
				session.setAttribute("icon", "bxs-error");	

				RequestDispatcher dispatcher = request.getRequestDispatcher("/student/addStudent.jsp");
				dispatcher.forward(request, response);
			}
		}

	}

}
