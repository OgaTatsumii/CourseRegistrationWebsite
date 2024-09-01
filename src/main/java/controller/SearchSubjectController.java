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

import database.MonHocDAO;
import database.SinhVienDAO;
import model.MonHoc;
import model.SinhVien;

/**
 * Servlet implementation class SearchSubjectController
 */
@WebServlet("/searchSubject")
public class SearchSubjectController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchSubjectController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "/subject/subjectManage.jsp";		
		String hocKy = request.getParameter("HocKy");
        String nganh = request.getParameter("Nganh");
        String subjectID = request.getParameter("subjectID");
        String subjectName = request.getParameter("subjectName");
		MonHocDAO monHocDAO = new MonHocDAO();
		ArrayList<MonHoc> dsMonHoc = monHocDAO.searchSubject(nganh, hocKy, subjectID, subjectName);
		HttpSession session = request.getSession();
		session.setAttribute("dsMonHoc", dsMonHoc);
		RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
		rd.forward(request, response);
	}

}
