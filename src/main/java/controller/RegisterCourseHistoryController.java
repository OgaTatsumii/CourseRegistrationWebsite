package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.CTLopTinChiDAO;
import database.HocKyNHDAO;
import database.NamHocDAO;
import model.CTLopTinChi;
import model.HocKy;
import model.HocKyNH;
import model.LopTinChi;
import model.NamHoc;
import model.SinhVien;

/**
 * Servlet implementation class RegisterCourseHistoryController
 */
@WebServlet("/registerCourseHistory")
public class RegisterCourseHistoryController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegisterCourseHistoryController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		NamHocDAO namHocDAO = new NamHocDAO();
		HttpSession session = request.getSession();
		SinhVien sinhVien = (SinhVien) session.getAttribute("sinhVien");
		ArrayList<NamHoc> dsNamHoc = namHocDAO.selectByLop(sinhVien.getLop().getMaLop());
		session.setAttribute("dsNamHoc", dsNamHoc);
		HocKyNHDAO hocKyNHDAO = new HocKyNHDAO();
		ArrayList<HocKyNH> dsHocKyNH = hocKyNHDAO.selectAll();
		session.setAttribute("dsHocKyNH", dsHocKyNH);
		String namHocAllowed = (String) session.getAttribute("namHocAllowed");
		String hocKyAllowed = (String) session.getAttribute("hocKyAllowed");
		System.out.println(namHocAllowed);
		System.out.println(hocKyAllowed);
		if (sinhVien != null && namHocAllowed != null && hocKyAllowed != null) {
			CTLopTinChiDAO ctLopTinChiDAO = new CTLopTinChiDAO();
			ArrayList<CTLopTinChi> dsCtLopTinChi = ctLopTinChiDAO.selectByNamHocHocKy(sinhVien.getMaSinhVien(),
					namHocAllowed, hocKyAllowed);
			session.setAttribute("dsCtLopTinChi", dsCtLopTinChi);
		} else {
			CTLopTinChiDAO ctLopTinChiDAO = new CTLopTinChiDAO();
			ArrayList<CTLopTinChi> dsCtLopTinChi = ctLopTinChiDAO.selectAll();
			session.setAttribute("dsCtLopTinChi", dsCtLopTinChi);
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher("/studentFE/lichSuDangKy.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		if (action != null) {
			if (action.equals("searchCreditCourseHistory")) {
				searchCreditCourseHistory(request, response);
			} else if (action.equals("cancelCreditCourse")) {

			}
		}
	}
	
	protected void searchCreditCourseHistory(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		SinhVien sinhVien = (SinhVien) session.getAttribute("sinhVien");
		String maNamHoc = request.getParameter("maNamHoc");
		String maHocKy = request.getParameter("maHocKy");
		CTLopTinChiDAO ctLopTinChiDAO = new CTLopTinChiDAO();
		ArrayList<CTLopTinChi> dsCtLopTinChi = ctLopTinChiDAO.selectByNamHocHocKy(sinhVien.getMaSinhVien(),
				maNamHoc, maHocKy);
		session.setAttribute("dsCtLopTinChi", dsCtLopTinChi);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/studentFE/lichSuDangKy.jsp");
		dispatcher.forward(request, response);
	}

}
