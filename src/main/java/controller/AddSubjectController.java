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
import model.Lop;
import model.MonHoc;

/**
 * Servlet implementation class AddSubjectController
 */
@WebServlet("/addSubject")
public class AddSubjectController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddSubjectController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = "/subject/addSubject.jsp";
		String title = "Thêm môn học";
		request.setAttribute("title", title);

		LopDAO lopDAO = new LopDAO();
		ArrayList<Lop> dsLop = lopDAO.selectAll();

		HttpSession session = request.getSession();
		session.setAttribute("dsLop", dsLop);
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
		request.setAttribute("maMonHoc",maMonHoc);
		request.setAttribute("tenMonHoc",tenMonHoc);
		request.setAttribute("soTinChi",soTinChi);
		int result = 0;
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
		if(!errorMessageMaMonHoc.isEmpty()) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/subject/addSubject.jsp");
			dispatcher.forward(request, response);
		}
		else {
			MonHoc monHoc = new MonHoc(maMonHoc, tenMonHoc, soTinChi);
			MonHocDAO monHocDAO = new MonHocDAO();
			result = monHocDAO.insert(monHoc);
			if (result != 0) {
				HttpSession session = request.getSession();
				session.setAttribute("titleToast", "Thành công");
				session.setAttribute("messageToast", "Tạo môn học thành công !");
				session.setAttribute("type", "success");
				session.setAttribute("icon", "bxs-check-circle");
				response.sendRedirect(request.getContextPath() + "/subjectManagement");

			} else {

				HttpSession session = request.getSession();
				session.setAttribute("titleToast", "Thất bại");
				session.setAttribute("messageToast", "Mã môn học đã tồn tại !");
				session.setAttribute("type", "error");
				session.setAttribute("icon", "bxs-error");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/subject/addSubject.jsp");
				dispatcher.forward(request, response);
			}
		}
		
	}

}
