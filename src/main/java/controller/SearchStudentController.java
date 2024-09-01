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
import database.SinhVienDAO;
import model.Lop;
import model.SinhVien;

/**
 * Servlet implementation class SearchStudentController
 */
@WebServlet("/searchStudent")
public class SearchStudentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchStudentController() {
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
		// TODO Auto-generated method stub
		String url = "/student/studentManage.jsp";		
		String khoa = request.getParameter("Khoa");
        String nganh = request.getParameter("Nganh");
        String lop = request.getParameter("Lop");
        String namHoc = request.getParameter("Year");
        String studentID = request.getParameter("studentID");
		System.out.println(khoa);
		SinhVienDAO sinhVienDAO = new SinhVienDAO();
		ArrayList<SinhVien> dsSinhVien = sinhVienDAO.searchStudent(khoa, nganh, lop, namHoc, studentID);
		System.out.println(dsSinhVien);
		HttpSession session = request.getSession();
		session.setAttribute("dsSinhVien", dsSinhVien);
		RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
		rd.forward(request, response);
	}

}
