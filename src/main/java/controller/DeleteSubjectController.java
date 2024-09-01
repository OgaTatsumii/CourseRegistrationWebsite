package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.MonHocDAO;
import database.TaiKhoanDAO;
import model.MonHoc;
import model.TaiKhoan;

/**
 * Servlet implementation class DeleteSubjectController
 */
@WebServlet("/deleteSubject")
public class DeleteSubjectController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteSubjectController() {
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
		String maMonHoc = request.getParameter("Id");
		MonHocDAO monHocDAO = new MonHocDAO();
		MonHoc monHoc = new MonHoc(maMonHoc, null, 0);
		int result = monHocDAO.delete(monHoc);
		if(result !=0) {
			HttpSession session = request.getSession();
			session.setAttribute("titleToast", "Thành công");	
			session.setAttribute("messageToast", "Xóa môn học thành công !");	
			session.setAttribute("type", "success");	
			session.setAttribute("icon", "bxs-check-circle");
			response.sendRedirect(request.getContextPath() + "/subjectManagement");
		}
		else {
			
			HttpSession session = request.getSession();
			session.setAttribute("titleToast", "Cảnh báo");	
			session.setAttribute("messageToast", "Xóa môn học không thành công !");	
			session.setAttribute("type", "error");	
			session.setAttribute("icon", "bxs-error");
			response.sendRedirect(request.getContextPath() + "/subjectManagement");
			/*
			 * RequestDispatcher dispatcher =
			 * request.getRequestDispatcher("addStudent.jsp"); dispatcher.forward(request,
			 * response);
			 */
		}
	}

}
