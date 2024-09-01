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

import database.CTLopTinChiDAO;
import database.CTNamHoc_HocKyDAO;
import database.GiangVienDAO;
import database.HocKyDAO;
import database.HocKyNHDAO;
import database.KhoaDAO;
import database.LopTinChiDAO;
import database.MonHocDAO;
import database.NamHocDAO;
import database.NganhDAO;
import database.SinhVienDAO;
import database.TaiKhoanDAO;
import model.CTLopTinChi;
import model.CTNamHoc_HocKy;
import model.GiangVien;
import model.HocKy;
import model.HocKyNH;
import model.LopTinChi;
import model.MonHoc;
import model.NamHoc;
import model.Nganh;
import model.SinhVien;
import model.TaiKhoan;

/**
 * Servlet implementation class CreditCourseManagement
 */
@WebServlet("/creditCourseManage/*")
public class CreditCourseManagement extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CreditCourseManagement() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pathInfo = request.getPathInfo();
		System.out.println(pathInfo);
		if (pathInfo != null) {
			if (pathInfo.equals("/addCreditCourse")) {
				addCreditCourseFE(request, response);
			} else if (pathInfo.equals("/AddCreditCourse")) {
				addCreditCourse(request, response);
			} else if (pathInfo.equals("/DeleteCreditCourse")) {
				deleteCreditCourse(request, response);
			} else if (pathInfo.equals("/detailCreditCourse")) {
				showDetailCreditCourse(request, response);
			}
			else if (pathInfo.equals("/selectCreditCourse")) {
				layThongTinUpdate(request, response);
			}
		} else {
			String url = "/creditCourse/creditCourse.jsp";
			String title = "Quản lý lớp tín chỉ";
			request.setAttribute("title", title);

			LopTinChiDAO lopTinChiDAO = new LopTinChiDAO();
			ArrayList<LopTinChi> dsLopTinChi = lopTinChiDAO.selectAll();

			HocKyNHDAO hocKyNHDAO = new HocKyNHDAO();
			ArrayList<HocKyNH> dsHocKyNH = hocKyNHDAO.selectAll();

			NamHocDAO namHocDAO = new NamHocDAO();
			ArrayList<NamHoc> dsNamHoc = namHocDAO.selectAll();

			System.out.println("Hello2");
			System.out.println(dsLopTinChi);

			NganhDAO nganhDAO = new NganhDAO();
			ArrayList<Nganh> dsNganh = nganhDAO.selectAll();

			HocKyDAO hocKyDAO = new HocKyDAO();
			ArrayList<HocKy> dsHocKy = hocKyDAO.selectAll();

			HttpSession session = request.getSession();
			session.setAttribute("dsNganh", dsNganh);
			session.setAttribute("dsHocKy", dsHocKy);
			session.setAttribute("dsLopTinChi", dsLopTinChi);
			session.setAttribute("dsHocKyNH", dsHocKyNH);
			session.setAttribute("dsNamHoc", dsNamHoc);

			RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
			rd.forward(request, response);
		}

	}

	protected void addCreditCourseFE(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = "/creditCourse/addCreditCourse.jsp";
		String title = "Thêm lớp tín chỉ";
		request.setAttribute("title", title);

		MonHocDAO monHocDAO = new MonHocDAO();
		ArrayList<MonHoc> dsMonHoc = monHocDAO.selectAll();

		GiangVienDAO giangVienDAO = new GiangVienDAO();
		ArrayList<GiangVien> dsGiangVien = giangVienDAO.selectAll();

		NamHocDAO namHocDAO = new NamHocDAO();
		ArrayList<NamHoc> dsNamHoc = namHocDAO.selectAll();
		
		HocKyNHDAO hocKyNHDAO = new HocKyNHDAO();
		ArrayList<HocKyNH> dsHocKyNH = hocKyNHDAO.selectAll();
		
		HttpSession session = request.getSession();
		session.setAttribute("dsMonHoc", dsMonHoc);
		session.setAttribute("dsGiangVien", dsGiangVien);
		session.setAttribute("dsNamHoc", dsNamHoc);
		session.setAttribute("dsHocKyNH", dsHocKyNH);
		RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
		rd.forward(request, response);
	}

	protected void showDetailCreditCourse(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = "/creditCourse/detailCreditCourse.jsp";
		String title = "Chi tiết lớp tín chỉ";
		request.setAttribute("title", title);
		String creditCourseId = request.getParameter("creditCourseId");
		CTLopTinChiDAO ctLopTinChiDAO = new CTLopTinChiDAO();
		ArrayList<CTLopTinChi> dsCTLopTinChi = ctLopTinChiDAO.selectByType(creditCourseId);
		LopTinChiDAO lopTinChiDAO = new LopTinChiDAO();
		LopTinChi ctLopTinChi = lopTinChiDAO.selectByID(creditCourseId);
		HttpSession session = request.getSession();
		session.setAttribute("dsCTLopTinChi", dsCTLopTinChi);
		session.setAttribute("ctLopTinChi", ctLopTinChi);
		RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		if (action != null) {
			if (action.equals("updateCreditCourse")) {
				updateCreditCourse(request, response);
			} else if (action.equals("addCreditCourse")) {
				addCreditCourse(request, response);
			} else if (action.equals("deleteCreditCourse")) {
				deleteCreditCourse(request, response);
			}
		}
	}

	protected void addCreditCourse(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String maLopTinChi = request.getParameter("MaLopTinChi");
		System.out.println(maLopTinChi);
		String maMonHoc = request.getParameter("MaMonHoc");
		String maGiangVien = request.getParameter("MaGiangVien");
		String maNamHoc = request.getParameter("MaNamHoc");
		String maHocKy = request.getParameter("MaHocKy");
		String tietBatDau = request.getParameter("TietBatDau");
		String tietKetThuc = request.getParameter("TietKetThuc");
		String ngayBatDauStr = request.getParameter("NgayBatDau");
		String ngayKetThucStr = request.getParameter("NgayKetThuc");
		String thu = request.getParameter("Thu");
		String phong = request.getParameter("Phong");
		String soLuongSinhVienStr = request.getParameter("SoLuongSinhVien");
		short soLuongSinhVien = Short.parseShort(soLuongSinhVienStr);

		request.setAttribute("maLopTinChi", maLopTinChi);
		request.setAttribute("maMonHoc", maMonHoc);
		request.setAttribute("maGiangVien", maGiangVien);
		request.setAttribute("maNamHoc", maNamHoc);
		request.setAttribute("maHocKy", maHocKy);
		request.setAttribute("tietBatDau", tietBatDau);
		request.setAttribute("tietKetThuc", tietKetThuc);
		request.setAttribute("ngayBatDau", ngayBatDauStr);
		request.setAttribute("ngayKetThuc", ngayKetThucStr);
		request.setAttribute("MaNamHoc",maNamHoc);
		request.setAttribute("MaHocKy", maHocKy);
		request.setAttribute("thu", thu);
		request.setAttribute("phong", phong);
		request.setAttribute("soLuongSinhVien", soLuongSinhVien);

		int result = 0;
		boolean isValidateMaLTC = Validator.validateMaLopTinChi(maLopTinChi);
		boolean isValidatePhong = Validator.validatePhong(phong);
		String errorMessageNgay = Validator.validateNgayLopTinChi(ngayBatDauStr, ngayKetThucStr);
		String errorMessageTiet = Validator.validateTiet(tietBatDau, tietKetThuc);
		String errorMessageMaLTC = "";
		String errorMessagePhong = "";
		if (!isValidateMaLTC) {
			errorMessageMaLTC = "Mã lớp tín chỉ gồm 6 số.";
			request.setAttribute("errorMessageMaLTC", errorMessageMaLTC);
		}
		if (!isValidatePhong) {
			errorMessagePhong = "Mã phòng không hợp lệ.";
			request.setAttribute("errorMessagePhong", errorMessagePhong);
		}
		if (!errorMessageNgay.isEmpty()) {
			request.setAttribute("errorMessageNgay", errorMessageNgay);
		}
		if (!errorMessageTiet.isEmpty()) {
			request.setAttribute("errorMessageTiet", errorMessageTiet);
		}
		if (!errorMessageMaLTC.isEmpty() || !errorMessagePhong.isEmpty() || !errorMessageNgay.isEmpty()
				|| !errorMessageTiet.isEmpty()) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/creditCourse/addCreditCourse.jsp");
			dispatcher.forward(request, response);
		} else {
			MonHocDAO monHocDAO = new MonHocDAO();
			MonHoc monHoc = monHocDAO.selectByID(maMonHoc);
			GiangVienDAO giangVienDAO = new GiangVienDAO();
			GiangVien giangVien = giangVienDAO.selectByID(maGiangVien);
			CTNamHoc_HocKyDAO ctNamHoc_HocKyDAO = new CTNamHoc_HocKyDAO();
			CTNamHoc_HocKy ctNamHoc_HocKy = ctNamHoc_HocKyDAO.selectByNHHK(maNamHoc, maHocKy);
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate ngayBatDau = null;
			ngayBatDau = LocalDate.parse(ngayBatDauStr, formatter);
			LocalDate ngayKetThuc = null;
			ngayKetThuc = LocalDate.parse(ngayKetThucStr, formatter);
			LopTinChi lopTinChi = new LopTinChi(maLopTinChi, monHoc, giangVien, ctNamHoc_HocKy, tietBatDau, tietKetThuc,
					ngayBatDau, ngayKetThuc, thu, phong, soLuongSinhVien, (short) 0);
			LopTinChiDAO lopTinChiDAO = new LopTinChiDAO();
			result = lopTinChiDAO.insert(lopTinChi);
			if (result != 0) {
				HttpSession session = request.getSession();
				session.setAttribute("titleToast", "Thành công");
				session.setAttribute("messageToast", "Tạo lớp TC thành công !");
				session.setAttribute("type", "success");
				session.setAttribute("icon", "bxs-check-circle");
				response.sendRedirect(request.getContextPath() + "/creditCourseManage");

			} else {

				HttpSession session = request.getSession();
				session.setAttribute("titleToast", "Thất bại");
				session.setAttribute("messageToast", "Mã lớp tín chỉ đã tồn tại !");
				session.setAttribute("type", "error");
				session.setAttribute("icon", "bxs-error");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/creditCourse/addCreditCourse.jsp");
				dispatcher.forward(request, response);
			}
		}
	}

	protected void deleteCreditCourse(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String maLopTC = request.getParameter("Id");
		LopTinChiDAO lopTinChiDAO = new LopTinChiDAO();
		LopTinChi lopTinChi = new LopTinChi(maLopTC, null, null, null, null, null, null, null, null, null, (short) 0,
				(short) 0);
		int result = lopTinChiDAO.delete(lopTinChi);
		if (result != 0) {
			HttpSession session = request.getSession();
			session.setAttribute("titleToast", "Thành công");
			session.setAttribute("messageToast", "Xóa lớp TC thành công !");
			session.setAttribute("type", "success");
			session.setAttribute("icon", "bxs-check-circle");
			response.sendRedirect(request.getContextPath() + "/creditCourseManage");
		} else {

			HttpSession session = request.getSession();
			session.setAttribute("titleToast", "Cảnh báo");
			session.setAttribute("messageToast", "Xóa lớp TC không thành công !");
			session.setAttribute("type", "error");
			session.setAttribute("icon", "bxs-error");
			response.sendRedirect(request.getContextPath() + "/creditCourseManage");
			/*
			 * RequestDispatcher dispatcher =
			 * request.getRequestDispatcher("addStudent.jsp"); dispatcher.forward(request,
			 * response);
			 */
		}
	}

	protected void layThongTinUpdate(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String maLTC = request.getParameter("maLTC");
		LopTinChiDAO ltcd = new LopTinChiDAO();
		LopTinChi ltc = ltcd.selectByID(maLTC);
		KhoaDAO kd=new KhoaDAO();
		MonHocDAO mhd=new MonHocDAO();
		GiangVienDAO gvd=new GiangVienDAO();
		HttpSession session = request.getSession();
		session.setAttribute("ltc", ltc);
		session.setAttribute("dsKhoa", kd.selectAll());
		session.setAttribute("dsMonHoc", mhd.selectAll());
		session.setAttribute("dsGiangVien",gvd.selectAll());
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/creditCourse/updateCreditCourse.jsp");
		rd.forward(request, response);
	}

	protected void updateCreditCourse(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String maLTC=request.getParameter("maLTC");
		String maMonHoc=request.getParameter("MaMonHoc");
		System.out.print(maMonHoc);
		String MaGiangVien=request.getParameter("MaGiangVien");
		String NgayBatDau=request.getParameter("NgayBatDau");
		String NgayKetThuc=request.getParameter("NgayKetThuc");
		String Thu=request.getParameter("Thu");
		String Phong=request.getParameter("Phong");
		String TietBatDau=request.getParameter("TietBatDau");
		String TietKetThuc=request.getParameter("TietKetThuc");
		String MaNamHoc=request.getParameter("MaNamHoc");
		String MaHocKy=request.getParameter("MaHocKy");
		String SoLuongSinhVien=request.getParameter("SoLuongSinhVien");
		HttpSession session = request.getSession();
		if(LocalDate.parse(NgayBatDau).isAfter(LocalDate.parse(NgayKetThuc))) {
			LopTinChiDAO ltcd=new LopTinChiDAO();
			System.out.print("xin chào");
			session.setAttribute("error", "Thời gian kết thúc không được nhỏ hơn ngày bắt đầu!");
			session.setAttribute("ltc", ltcd.selectByID(maLTC));
			response.sendRedirect(request.getContextPath() + "/creditCourseManage");
		}else {
			LopTinChiDAO ltcd=new LopTinChiDAO();
			LopTinChi newLtc=new LopTinChi();
			MonHocDAO mhd=new MonHocDAO();
			MonHoc mh=mhd.selectByID(maMonHoc);
			System.out.print(mh.toString());
			GiangVienDAO gvd=new GiangVienDAO();
			CTNamHoc_HocKyDAO ctnhhkd=new CTNamHoc_HocKyDAO();
			newLtc.setMaLopTinChi(maLTC);
			newLtc.setMonHoc(mhd.selectByID(maMonHoc));
			newLtc.setGiangVien(gvd.selectByID(MaGiangVien));
			newLtc.setNgayBatDau(LocalDate.parse(NgayBatDau));
			newLtc.setNgayKetThuc(LocalDate.parse(NgayKetThuc));
			newLtc.setThu(Thu);
			newLtc.setPhong(Phong);
			newLtc.setTietBatDau(TietBatDau);
			newLtc.setTietKetThuc(TietKetThuc);
			newLtc.setCtNamHoc_HocKy(ctnhhkd.selectByNHHK(MaNamHoc, MaHocKy));
			newLtc.setSoLuongSinhVien(Short.parseShort(SoLuongSinhVien));
			short value=0;
			newLtc.setDaDangKy(value);
			System.out.print(newLtc.toString());
			ltcd.update(newLtc);
			System.out.print(newLtc.toString());
			
			if(ltcd.update(newLtc)==0) {
				session.setAttribute("titleToast", "Thất bại");
				session.setAttribute("messageToast", "Cập nhật không thành công !");
				session.setAttribute("type", "error");
				session.setAttribute("icon", "bxs-error");
				response.sendRedirect(request.getContextPath() + "/creditCourseManage");
			}else {
				session.setAttribute("titleToast", "Thành công");
				session.setAttribute("messageToast", "Tạo lớp TC thành công !");
				session.setAttribute("type", "success");
				session.setAttribute("icon", "bxs-check-circle");
				response.sendRedirect(request.getContextPath() + "/creditCourseManage");
			}
		}
		
		
		
	}
}
