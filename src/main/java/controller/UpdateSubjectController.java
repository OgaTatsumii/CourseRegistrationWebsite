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

import database.LopDAO;
import database.MonHocDAO;
import database.SinhVienDAO;
import model.Lop;
import model.MonHoc;
import model.SinhVien;

/**
 * Servlet implementation class UpdateSubjectController
 */
@WebServlet("/updateSubject")
public class UpdateSubjectController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateSubjectController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = "/subject/updateSubject.jsp";
		String title = "Hiệu chỉnh thông tin môn học";
		request.setAttribute("title", title);
		String subjectId = request.getParameter("subjectId");
		MonHocDAO MonHocDAO = new MonHocDAO();
		MonHoc MonHoc = MonHocDAO.selectByID(subjectId);
		request.setAttribute("MonHoc", MonHoc);

		RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String maMonHoc = request.getParameter("MaMonHoc");
		String tenMonHoc = request.getParameter("TenMonHoc");
		String soTinChiStr = request.getParameter("SoTinChi");
		int soTinChi = Integer.parseInt(soTinChiStr);

		request.setAttribute("MaMonHoc", maMonHoc);
		request.setAttribute("TenMonHoc", tenMonHoc);
		request.setAttribute("SoTinChi", soTinChi);

		boolean isValidateMaMonHoc = Validator.validateMaMonHoc(maMonHoc);
		boolean isValidateTenMonHoc = Validator.validateTenMonHoc(tenMonHoc);
		String errorMessageMaMonHoc = "";
		String errorMessageTenMonHoc = "";
		if (!isValidateMaMonHoc) {
			errorMessageMaMonHoc = "Mã môn học gồm 6 số.";
			request.setAttribute("errorMessageMaMonHoc", errorMessageMaMonHoc);
		}
		if (!isValidateTenMonHoc) {
			errorMessageTenMonHoc = "Tên môn học không hợp lệ.";
			request.setAttribute("errorMessageTenMonHoc", errorMessageTenMonHoc);
		}
		if (!errorMessageMaMonHoc.isEmpty()) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/subject/updateSubject.jsp");
			dispatcher.forward(request, response);
		} else {
			MonHoc monHoc = new MonHoc();
			monHoc.setMaMonHoc(maMonHoc);
			monHoc.setTenMonHoc(tenMonHoc);
			monHoc.setSoTinChi(soTinChi);
			MonHocDAO monHocDAO = new MonHocDAO();
			int result = monHocDAO.update(monHoc);
			if (result != 0) {
				HttpSession session = request.getSession();
				session.setAttribute("titleToast", "Thành công");
				session.setAttribute("messageToast", "Cập nhật môn học thành công !");
				session.setAttribute("type", "success");
				session.setAttribute("icon", "bxs-check-circle");
				response.sendRedirect(request.getContextPath() + "/subjectManagement");

			} else {
				RequestDispatcher dispatcher = request.getRequestDispatcher("/subject/updateSubject.jsp");
				dispatcher.forward(request, response);
			}
		}
	}

}
