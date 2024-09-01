package controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.GiangVienDAO;
import database.HocKyDAO;
import database.KhoaDAO;
import database.KhoaHocDAO;
import database.LopDAO;
import database.NganhDAO;
import database.GiangVienDAO;
import database.QueQuanDAO;
import database.GiangVienDAO;
import database.GiangVienDAO;
import database.TaiKhoanDAO;
import model.ChucVu;
import model.GiangVien;
import model.HocKy;
import model.Khoa;
import model.KhoaHoc;
import model.Lop;
import model.Nganh;
import model.QueQuan;
import model.GiangVien;
import model.GiangVien;
import model.TaiKhoan;

/**
 * Servlet implementation class LecturerController
 */
@WebServlet("/lecturerManagement")
public class LecturerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LecturerController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if (action == null) {
		String url = "/lecturer/lecturerManage.jsp";
		String title = "Quản lý giảng viên";
		request.setAttribute("title", title);
		KhoaDAO khoaDAO = new KhoaDAO();
		ArrayList<Khoa> dsKhoa = khoaDAO.selectAll();
		
		LopDAO lopDAO = new LopDAO();
		ArrayList<Lop> dsLop = lopDAO.selectAll();
		
		KhoaHocDAO KhoaHocDAO = new KhoaHocDAO();
		ArrayList<KhoaHoc> dsKhoaHoc = KhoaHocDAO.selectAll();
		
		GiangVienDAO gvDAO = new GiangVienDAO();
		ArrayList<GiangVien> dsGiangVien = gvDAO.selectAll();
		
		QueQuanDAO queQuanDAO = new QueQuanDAO();
		ArrayList<QueQuan> dsQueQuan = queQuanDAO.selectAll();
		
		HttpSession session = request.getSession();
		session.setAttribute("dsKhoa", dsKhoa);
		session.setAttribute("dsLop", dsLop);
		session.setAttribute("dsGiangVien", dsGiangVien);
		session.setAttribute("dsKhoaHoc", dsKhoaHoc);
		session.setAttribute("dsQueQuan", dsQueQuan);
		
		RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
		rd.forward(request, response);
		}
		else {
			if (action.equals("update")) {
				UpdateLecturerGet(request, response);
			} else if (action.equals("add")) {
				AddLecturerGet(request, response);
			} else if (action.equals("")) {
				
			}else if (action.equals("")) {
				
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if (action.equals("update")) {
			UpdateLecturerPost(request, response);
		}else if (action.equals("add")) {
			AddLecturerPost(request, response);
		}else if (action.equals("search")) {
			SearchLecturer(request, response);
		}else if (action.equals("delete")) {
			DeleteLecturer(request, response);
		}
	}
	protected void SearchLecturer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "/lecturer/lecturerManage.jsp";
		String maGV = (String) request.getParameter("lecturerID");
		String tenGV = (String) request.getParameter("lecturerName");
		String gioitinh = (String) request.getParameter("Gender");
		
		
		
		GiangVienDAO GiangVienDAO = new GiangVienDAO();
		ArrayList<GiangVien> dsGiangVien = GiangVienDAO.search(maGV, tenGV, gioitinh);
		
		HttpSession session = request.getSession();
		session.setAttribute("dsGiangVien", dsGiangVien);
		
		RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
		rd.forward(request, response);
	}
	
	protected void AddLecturerPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String maGiangVien = request.getParameter("MaGiangVien");
		String hoTen = request.getParameter("HoTen");
		System.out.println(hoTen);
		String ngaySinhStr = request.getParameter("NgaySinh"); // yyyy-mm-dd
		String gioiTinh = request.getParameter("GioiTinh");
		String soDienThoai = request.getParameter("SDT");
		String cccd = request.getParameter("CCCD");
		String email = request.getParameter("Email");
		String tamTru = request.getParameter("TamTru");
		String maLop = request.getParameter("MaLop");
		String maTinh = request.getParameter("MaTinh");
		
		request.setAttribute("errorMessage", "Không thể thêm giảng viên mới");
		request.setAttribute("MaGiangVien", maGiangVien);
		request.setAttribute("HoTen", hoTen);
		request.setAttribute("NgaySinh", ngaySinhStr);
		request.setAttribute("GioiTinh", gioiTinh);
		request.setAttribute("SDT", soDienThoai);
		request.setAttribute("CCCD", cccd);
		request.setAttribute("Email", email);
		request.setAttribute("TamTru", tamTru);
		request.setAttribute("MaLop", maLop);
		request.setAttribute("MaTinh", maTinh);
		
		boolean isValidMaGiangVien = Validator.validateMaGiangVien(maGiangVien);
		boolean isValidHoTen = Validator.validateHoTen(hoTen);
		boolean isValidSoDienThoai = Validator.validateSoDienThoai(soDienThoai);
		boolean isValidCCCD = Validator.validateCCCD(cccd);
		boolean isValidEmail = Validator.validateEmail(email);

		String errorMessageMaGiangVien = "";
		String errorMessageHoTen = "";
		String errorMessageSoDienThoai = "";
		String errorMessageCCCD = "";
		String errorMessageEmail = "";

		if (!isValidMaGiangVien) {
			errorMessageMaGiangVien = "Cú pháp mã giảng viên không hợp lệ.";
			request.setAttribute("errorMessageMaGiangVien", errorMessageMaGiangVien);
		}
		if (!isValidHoTen) {
			errorMessageHoTen = "Họ tên không hợp lệ.";
			request.setAttribute("errorMessageHoTen", errorMessageHoTen);
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
		
		if (!errorMessageMaGiangVien.isEmpty() || !errorMessageHoTen.isEmpty() || !errorMessageSoDienThoai.isEmpty()
				|| !errorMessageCCCD.isEmpty() || !errorMessageEmail.isEmpty()) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/lecturer/addLecturer.jsp");
			dispatcher.forward(request, response);
		} else {

			// Tạo đối tượng GiangVien và set giá trị
			GiangVien GiangVien = new GiangVien();
			GiangVien.setMaGiangVien(maGiangVien);
			GiangVien.setHoTen(hoTen);
			// Chuyển đổi ngày sinh từ String sang Date nếu cần
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate ngaySinh = null;
			ngaySinh = LocalDate.parse(ngaySinhStr, formatter);
			GiangVien.setNgaySinh(ngaySinh);
			GiangVien.setGioiTinh(gioiTinh);
			GiangVien.setSoDienThoai(soDienThoai);
			GiangVien.setCCCD(cccd);
			GiangVien.setEmail(email);
			QueQuan queQuan = (new QueQuanDAO()).selectByID(maTinh);
			GiangVien.setQueQuan(queQuan);
			// Sử dụng DAO để lưu giảng viên vào cơ sở dữ liệu
			GiangVienDAO GiangVienDAO = new GiangVienDAO();
			int result = GiangVienDAO.insert(GiangVien);

			// Kiểm tra kết quả và phản hồi
			if (result != 0) {
				HttpSession session = request.getSession();
				session.setAttribute("titleToast", "Thành công");	
				session.setAttribute("messageToast", "Tạo giảng viên thành công !");	
				session.setAttribute("type", "success");	
				session.setAttribute("icon", "bxs-check-circle");	
				response.sendRedirect(request.getContextPath() + "/lecturerManagement");

			} else {
				
				HttpSession session = request.getSession();
				session.setAttribute("titleToast", "Thất bại");	
				session.setAttribute("messageToast", "Mã giảng viên đã tồn tại !");	
				session.setAttribute("type", "error");	
				session.setAttribute("icon", "bxs-error");	

				RequestDispatcher dispatcher = request.getRequestDispatcher("/lecturer/addLecturer.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	protected void AddLecturerGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	String url = "/lecturer/addLecturer.jsp";
	String title = "Thêm giảng viên";
	request.setAttribute("title", title);
	QueQuanDAO queQuanDAO = new QueQuanDAO();
	ArrayList<QueQuan> dsQueQuan = queQuanDAO.selectAll();
	
	HttpSession session = request.getSession();
	session.setAttribute("dsQueQuan", dsQueQuan);
	
	RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
	rd.forward(request, response);
		}
	
	protected void UpdateLecturerGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "/lecturer/updateLecturer.jsp";
		String title = "Hiệu chỉnh thông tin";
		request.setAttribute("title", title);
		String maGiangVien = request.getParameter("maGV");
		GiangVienDAO GiangVienDAO = new GiangVienDAO();
		GiangVien GiangVien =  GiangVienDAO.selectByID(maGiangVien);
		request.setAttribute("giangVien", GiangVien);
		
		RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
		rd.forward(request, response);
	}
	
protected void UpdateLecturerPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String maGiangVien = request.getParameter("MaGiangVien");
		String hoTen = request.getParameter("HoTen");
		String ngaySinhStr = request.getParameter("NgaySinh"); // yyyy-mm-dd
		String gioiTinh = request.getParameter("GioiTinh");
		String soDienThoai = request.getParameter("SDT");
		String cccd = request.getParameter("CCCD");
		String email = request.getParameter("Email");
		String maLop = request.getParameter("MaLop");
		String maTinh = request.getParameter("MaTinh");
		
		
		request.setAttribute("MaGiangVien", maGiangVien);
		request.setAttribute("HoTen", hoTen);
		request.setAttribute("NgaySinh", ngaySinhStr);
		request.setAttribute("GioiTinh", gioiTinh);
		request.setAttribute("SDT", soDienThoai);
		request.setAttribute("CCCD", cccd);
		request.setAttribute("Email", email);
		request.setAttribute("MaLop", maLop);
		request.setAttribute("MaTinh", maTinh);
		
		boolean isValidMaGiangVien = Validator.validateMaGiangVien(maGiangVien);
		boolean isValidHoTen = Validator.validateHoTen(hoTen);
		boolean isValidSoDienThoai = Validator.validateSoDienThoai(soDienThoai);
		boolean isValidCCCD = Validator.validateCCCD(cccd);
		boolean isValidEmail = Validator.validateEmail(email);

		String errorMessageMaGiangVien = "";
		String errorMessageHoTen = "";
		String errorMessageSoDienThoai = "";
		String errorMessageCCCD = "";
		String errorMessageEmail = "";

		if (!isValidMaGiangVien) {
			errorMessageMaGiangVien = "Cú pháp mã giảng viên không hợp lệ.";
			request.setAttribute("errorMessageMaGiangVien", errorMessageMaGiangVien);
		}
		if (!isValidHoTen) {
			errorMessageHoTen = "Họ tên không hợp lệ.";
			request.setAttribute("errorMessageHoTen", errorMessageHoTen);
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
		
		if (!errorMessageMaGiangVien.isEmpty() || !errorMessageHoTen.isEmpty() || !errorMessageSoDienThoai.isEmpty()
				|| !errorMessageCCCD.isEmpty() || !errorMessageEmail.isEmpty()) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/lecturer/updateLecturer.jsp");
			dispatcher.forward(request, response);
		} else {

			// Tạo đối tượng GiangVien và set giá trị
			GiangVien GiangVien = new GiangVien();
			GiangVien.setMaGiangVien(maGiangVien);
			GiangVien.setHoTen(hoTen);
			// Chuyển đổi ngày sinh từ String sang Date nếu cần
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate ngaySinh = null;
			ngaySinh = LocalDate.parse(ngaySinhStr, formatter);
			GiangVien.setNgaySinh(ngaySinh);
			GiangVien.setGioiTinh(gioiTinh);
			GiangVien.setSoDienThoai(soDienThoai);
			GiangVien.setCCCD(cccd);
			GiangVien.setEmail(email);
			QueQuan queQuan = (new QueQuanDAO()).selectByID(maTinh);
			GiangVien.setQueQuan(queQuan);
			// Sử dụng DAO để lưu giảng viên vào cơ sở dữ liệu
			GiangVienDAO GiangVienDAO = new GiangVienDAO();
			int result = GiangVienDAO.update(GiangVien);

			// Kiểm tra kết quả và phản hồi
			if (result != 0) {
				HttpSession session = request.getSession();
				session.setAttribute("titleToast", "Thành công");	
				session.setAttribute("messageToast", "Cập nhật giảng viên thành công !");	
				session.setAttribute("type", "success");	
				session.setAttribute("icon", "bxs-check-circle");	
				response.sendRedirect(request.getContextPath() + "/lecturerManagement");

			} else {
				HttpSession session = request.getSession();
				session.setAttribute("titleToast", "Thất bại");	
				session.setAttribute("messageToast", "Mã giảng viên đã tồn tại !");	
				session.setAttribute("type", "error");	
				session.setAttribute("icon", "bxs-error");
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("/lecturer/updateLecturer.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	protected void DeleteLecturer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String maGiangVien = request.getParameter("Id");
		GiangVienDAO giangVienDAO = new GiangVienDAO();
		int result = giangVienDAO.delete(new GiangVien(maGiangVien,null,null,null,null,null,null,null));
		if(result !=0) {
			HttpSession session = request.getSession();
			session.setAttribute("titleToast", "Thành công");	
			session.setAttribute("messageToast", "Xóa giảng viên thành công !");	
			session.setAttribute("type", "success");	
			session.setAttribute("icon", "bxs-check-circle");
			response.sendRedirect(request.getContextPath() + "/lecturerManagement");
		}
		else {
			
			HttpSession session = request.getSession();
			session.setAttribute("titleToast", "Cảnh báo");	
			session.setAttribute("messageToast", "Xóa giảng viên không thành công !");	
			session.setAttribute("type", "error");	
			session.setAttribute("icon", "bxs-error");
			response.sendRedirect(request.getContextPath() + "/lecturerManagement");
		}
	}
}
