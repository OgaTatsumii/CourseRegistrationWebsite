package controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.CTLopTinChiDAO;
import database.HocKyDAO;
import database.LopDAO;
import database.LopTinChiDAO;
import database.NganhDAO;
import database.PhienDangKyDAO;
import database.SinhVienDAO;
import model.CTLopTinChi;
import model.HocKy;
import model.Lop;
import model.LopTinChi;
import model.Nganh;
import model.PhienDangKy;
import model.SinhVien;

/**
 * Servlet implementation class RegisterCourseController
 */
@WebServlet("/registerCourse/*")
public class RegisterCourseController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegisterCourseController() {
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
			if (pathInfo.equals("/registerCreditCourse")) {
				registerCreditCourse(request, response);
			} else if (pathInfo.equals("")) {
//				searchCreditCourse(request, response);
			}
		} else {
			processRequest(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		if (action != null) {
			if (action.equals("searchCreditCourse")) {
				searchCreditCourse(request, response);
			} else if (action.equals("cancelCreditCourse")) {
				cancelCreditCourse(request, response);
			} else if (action.equals("monCanHocLai")) {
				searchRelearnCreditCourse(request, response);
			}
		}
	}

	protected void searchCreditCourse(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = "/studentFE/dangKyMonHoc.jsp";
		HttpSession session = request.getSession();
		String lop = request.getParameter("lop");
		String currentSessionAllowed = (String) session.getAttribute("currentSessionAllowed");
		System.out.println(lop);
		System.out.println("Phiên hợp lệ: " + currentSessionAllowed);
		LopTinChiDAO lopTinChiDAO = new LopTinChiDAO();
		ArrayList<LopTinChi> dsLopTinChi = lopTinChiDAO.selectByMaLop(lop, currentSessionAllowed);
		System.out.println("Danh sách môn học của lớp " + lop + " là: ");
		System.out.println(dsLopTinChi);
		request.setAttribute("MaLop", lop);
		session.setAttribute("dsLopTinChi", dsLopTinChi);
		RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
		rd.forward(request, response);

	}

	protected void searchRelearnCreditCourse(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = "/studentFE/dangKyMonHoc.jsp";
		HttpSession session = request.getSession();
		SinhVien sinhVien = (SinhVien) session.getAttribute("sinhVien");
		String currentSessionAllowed = (String) session.getAttribute("currentSessionAllowed");
		LopTinChiDAO lopTinChiDAO = new LopTinChiDAO();
		ArrayList<LopTinChi> dsLopTinChi = lopTinChiDAO.selectByDiemSV(sinhVien.getMaSinhVien(), currentSessionAllowed);
		String action = request.getParameter("action");
		request.setAttribute("action", action);
		session.setAttribute("dsLopTinChi", dsLopTinChi);
		RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
		rd.forward(request, response);

	}

	protected void cancelCreditCourse(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String maLTC = request.getParameter("Id");
		String studentId = request.getParameter("studentId");
		CTLopTinChiDAO ctLopTinChiDAO = new CTLopTinChiDAO();
		int result = ctLopTinChiDAO.cancelCreditCourse(maLTC, studentId);
		if (result != 0) {
			HttpSession session = request.getSession();
			session.setAttribute("titleToast", "Thành công");
			session.setAttribute("messageToast", "Hủy học phần thành công !");
			session.setAttribute("type", "success");
			session.setAttribute("icon", "bxs-check-circle");
			response.sendRedirect(request.getContextPath() + "/registerCourse");
		} else {

			HttpSession session = request.getSession();
			session.setAttribute("titleToast", "Cảnh báo");
			session.setAttribute("messageToast", "Hủy học phần không thành công !");
			session.setAttribute("type", "error");
			session.setAttribute("icon", "bxs-error");
			response.sendRedirect(request.getContextPath() + "/registerCourse");
			/*
			 * RequestDispatcher dispatcher =
			 * request.getRequestDispatcher("addStudent.jsp"); dispatcher.forward(request,
			 * response);
			 */
		}

	}

	private void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String url = "/studentFE/dangKyMonHoc.jsp";
		String title = "Đăng ký lớp tín chỉ";
		request.setAttribute("title", title);
		SinhVien sinhVien = (SinhVien) session.getAttribute("sinhVien");

		if (sinhVien == null) {
			response.sendRedirect("/login.jsp");
			return;
		}

		LopDAO lopDAO = new LopDAO();
		String maLop = lopDAO.getMaLopByMaSinhVien(sinhVien.getMaSinhVien());

		if (maLop == null) {
			response.sendRedirect("/errorPage.jsp");
			return;
		}

		Lop lop = lopDAO.selectByID(maLop);
		if (lop == null) {
			response.sendRedirect("/errorPage.jsp");
			return;
		}

		PhienDangKyDAO phienDangKyDAO = new PhienDangKyDAO();
		ArrayList<PhienDangKy> dsPhienDangKy = phienDangKyDAO.selectAll();
		LocalDateTime now = LocalDateTime.now();

		boolean isRegistrationOpen = false;
		int currentHocKy = 0;

		for (PhienDangKy phienDangKy : dsPhienDangKy) {
			System.out.println(phienDangKy);
			if (lop.getNganh().getKhoa().getMaKhoa().equals(phienDangKy.getKhoa().getMaKhoa())
					&& now.isAfter(phienDangKy.getThoiGianBatDau()) && now.isBefore(phienDangKy.getThoiGianKetThuc())) {

				LocalDate ngayNhapHoc = lop.getKhoaHoc().getNgayBatDau();
				long soNgayDaTroiQua = ChronoUnit.DAYS.between(ngayNhapHoc, now);

				float thoiGianDaoTao = lop.getNganh().getThoiGianDaoTao();
				float soNgayTrongKhoa = thoiGianDaoTao * 365;
				currentHocKy = (int) (soNgayDaTroiQua / (soNgayTrongKhoa / (thoiGianDaoTao * 2))) + 1;
				isRegistrationOpen = true;
				break;
			}
		}

		LopTinChiDAO lopTinChiDAO = new LopTinChiDAO();
		
		
		if (isRegistrationOpen) {
			String hocKyStr = "HK" + currentHocKy;
			System.out.println(hocKyStr);
			String maNHHKIP = "";
			ArrayList<LopTinChi> dsLopTinChi = new ArrayList<LopTinChi>();

			for (PhienDangKy phienDangKy : dsPhienDangKy) {
				dsLopTinChi = lopTinChiDAO.selectByNganhHK(lop.getNganh().getMaNganh(),hocKyStr,phienDangKy.getCtNamHocHocKy().getmaNHHocKy());
				if (!dsLopTinChi.isEmpty()) {
					session.setAttribute("currentSessionAllowed", phienDangKy.getCtNamHocHocKy().getmaNHHocKy());
					maNHHKIP = phienDangKy.getCtNamHocHocKy().getmaNHHocKy();
					session.setAttribute("namHocAllowed", phienDangKy.getCtNamHocHocKy().getnamHoc().getMaNamHoc());
					session.setAttribute("namHocAllowedFE", phienDangKy.getCtNamHocHocKy().getnamHoc().getTenNamHoc());
					session.setAttribute("hocKyAllowed", phienDangKy.getCtNamHocHocKy().gethocKy().getMaHocKy());
					break;
				}
			}

			if (hocKyStr.equals("HK1")) {
				CTLopTinChiDAO ctLopTinChiDAO = new CTLopTinChiDAO();
				for (int i = 0; i < dsLopTinChi.size(); i++) {
					String maLopTinChi = dsLopTinChi.get(i).getMaLopTinChi();
					LocalDateTime thoiGianDangKy = LocalDateTime.now();
					LopTinChi lopTinChi = lopTinChiDAO.selectByID(maLopTinChi);
					CTLopTinChi ctLopTinChi = new CTLopTinChi(lopTinChi, sinhVien, thoiGianDangKy);
					ctLopTinChiDAO.insert(ctLopTinChi);

				}
				ArrayList<LopTinChi> dsLopTinChiDK = lopTinChiDAO.selectByMaSinhVien(sinhVien.getMaSinhVien(),maNHHKIP);
				ArrayList<Lop> dsLop = lopDAO.selectAll();
				session.setAttribute("dsLop", dsLop);
				session.setAttribute("dsLopTinChi", dsLopTinChi);
				session.setAttribute("dsLopTinChiDK", dsLopTinChiDK);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/studentFE/dangKyMonHoc.jsp");
				dispatcher.forward(request, response);
				return;
			}
			ArrayList<LopTinChi> dsLopTinChiDK = lopTinChiDAO.selectByMaSinhVien(sinhVien.getMaSinhVien(),maNHHKIP);
			ArrayList<Lop> dsLop = lopDAO.selectAll();
			session.setAttribute("dsLop", dsLop);
			session.setAttribute("dsLopTinChi", dsLopTinChi);
			session.setAttribute("dsLopTinChiDK", dsLopTinChiDK);

			if (dsLopTinChi != null && !dsLopTinChi.isEmpty()) {
				for (int i = 0; i < dsLopTinChi.size(); i++) {
					LopTinChi ltc = dsLopTinChi.get(i);
					boolean isRegistered = false;

					if (dsLopTinChiDK != null && !dsLopTinChiDK.isEmpty()) {
						for (int j = 0; j < dsLopTinChiDK.size(); j++) {
							LopTinChi ltcdk = dsLopTinChiDK.get(j);
							if (ltc.equals(ltcdk)) {
								isRegistered = true;
								break;
							}
						}
					}
				}
			}
			RequestDispatcher dispatcher = request.getRequestDispatcher("/studentFE/dangKyMonHoc.jsp");
			dispatcher.forward(request, response);
		} else {
			session.setAttribute("errorMessage", "Hiện tại không phải thời gian đăng ký.");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/studentFE/dangKyMonHoc.jsp");
			dispatcher.forward(request, response);
		}
	}

	protected void registerCreditCourse(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String[] maLopTinChiArray = request.getParameterValues("maLopTinChi[]");
		String[] maSinhVienArray = request.getParameterValues("maSinhVien[]");
		String[] thoiGianDangKyArray = request.getParameterValues("thoiGianDangKy[]");
		int result = 0;
		if (maLopTinChiArray != null && maSinhVienArray != null && thoiGianDangKyArray != null) {
			for (int i = 0; i < maLopTinChiArray.length; i++) {
				String maLopTinChi = maLopTinChiArray[i];
				String maSinhVien = maSinhVienArray[i];
				String thoiGianDangKyStr = thoiGianDangKyArray[i];
				LopTinChiDAO lopTinChiDAO = new LopTinChiDAO();
				LopTinChi lopTinChi = lopTinChiDAO.selectByID(maLopTinChi);
				SinhVienDAO sinhVienDAO = new SinhVienDAO();
				SinhVien sinhVien = sinhVienDAO.selectByID(maSinhVien);
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
				LocalDateTime thoiGianDangKy = LocalDateTime.parse(thoiGianDangKyStr, formatter);
				CTLopTinChi ctLopTinChi = new CTLopTinChi(lopTinChi, sinhVien, thoiGianDangKy);
				CTLopTinChiDAO ctLopTinChiDAO = new CTLopTinChiDAO();
				result += ctLopTinChiDAO.insert(ctLopTinChi);

			}
			if (result != 0) {
				HttpSession session = request.getSession();
				session.setAttribute("titleToast", "Thành công");
				session.setAttribute("messageToast", "Đăng ký môn học thành công !");
				session.setAttribute("type", "success");
				session.setAttribute("icon", "bxs-check-circle");
				response.sendRedirect(request.getContextPath() + "/registerCourse");

			} else {
				HttpSession session = request.getSession();
				session.setAttribute("titleToast", "Thất bại");
				session.setAttribute("messageToast", "Môn học đã được đăng ký trước đó !");
				session.setAttribute("type", "error");
				session.setAttribute("icon", "bxs-error");
				response.sendRedirect(request.getContextPath() + "/registerCourse");
			}
		}
	}

}
