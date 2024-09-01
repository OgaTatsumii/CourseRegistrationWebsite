package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.SinhVienDAO;
import database.TaiKhoanDAO;
import model.SinhVien;
import model.TaiKhoan;

/**
 * Servlet implementation class DeleteStudentController
 */
@WebServlet("/deleteStudent")
public class DeleteStudentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteStudentController() {
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
		String maSinhVien = request.getParameter("Id");
		SinhVienDAO sinhVienDAO = new SinhVienDAO();
		int result = sinhVienDAO.delete(new SinhVien(maSinhVien,null,null,null,null,null,null,null,null,null,null));
		TaiKhoanDAO taiKhoanDAO = new TaiKhoanDAO();
		result += taiKhoanDAO.delete(new TaiKhoan(maSinhVien,null,null));
		if(result !=0) {
			HttpSession session = request.getSession();
			session.setAttribute("titleToast", "Thành công");	
			session.setAttribute("messageToast", "Xóa sinh viên thành công !");	
			session.setAttribute("type", "success");	
			session.setAttribute("icon", "bxs-check-circle");
			response.sendRedirect(request.getContextPath() + "/studentManage");
		}
		else {
			
			HttpSession session = request.getSession();
			session.setAttribute("titleToast", "Cảnh báo");	
			session.setAttribute("messageToast", "Xóa sinh viên không thành công !");	
			session.setAttribute("type", "error");	
			session.setAttribute("icon", "bxs-error");
			response.sendRedirect(request.getContextPath() + "/studentManage");
			/*
			 * RequestDispatcher dispatcher =
			 * request.getRequestDispatcher("addStudent.jsp"); dispatcher.forward(request,
			 * response);
			 */
		}
	}

}
