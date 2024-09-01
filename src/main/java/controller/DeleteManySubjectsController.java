package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.MonHocDAO;
import database.SinhVienDAO;
import database.TaiKhoanDAO;
import model.MonHoc;
import model.SinhVien;
import model.TaiKhoan;

/**
 * Servlet implementation class DeleteManySubjectsController
 */
@WebServlet("/deleteManySubject")
public class DeleteManySubjectsController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteManySubjectsController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String[] monHocIDList = request.getParameterValues("submit_Ids");
		ArrayList<MonHoc> dsMonHoc = new ArrayList<MonHoc>();
		if (monHocIDList != null && monHocIDList.length > 0) {
			int result = 0;
			MonHocDAO monHocDAO = new MonHocDAO();
			for (String maMonHoc : monHocIDList) {
				dsMonHoc.add(new MonHoc(maMonHoc,null,0));
			}
			result = monHocDAO.deleteMany(dsMonHoc);
			HttpSession session = request.getSession();
			if (result != 0) {
				session.setAttribute("titleToast", "Thành công");
				session.setAttribute("messageToast", "Xóa môn học thành công!");
				session.setAttribute("type", "success");
				session.setAttribute("icon", "bxs-check-circle");
			} else {
				session.setAttribute("titleToast", "Cảnh báo");
				session.setAttribute("messageToast", "Xóa môn học không thành công!");
				session.setAttribute("type", "error");
				session.setAttribute("icon", "bxs-error");
			}
			response.sendRedirect(request.getContextPath() + "/subjectManagement");
		}
	}

}
