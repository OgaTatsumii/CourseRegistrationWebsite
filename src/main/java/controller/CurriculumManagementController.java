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

import database.CTNamHoc_HocKyDAO;
import database.HocKyDAO;
import database.KhoaDAO;
import database.KhoaHocDAO;
import database.LopDAO;
import database.MonHocDAO;
import database.NganhDAO;
import database.QueQuanDAO;
import database.SinhVienDAO;
import model.CTNamHoc_HocKy;
import model.HocKy;
import model.Khoa;
import model.KhoaHoc;
import model.Lop;
import model.MonHoc;
import model.Nganh;
import model.QueQuan;
import model.SinhVien;

/**
 * Servlet implementation class CurriculumManagementController
 */
@WebServlet("/curriculumManage")
public class CurriculumManagementController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CurriculumManagementController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if (action != null) {
			if (action.equals("")) {
				
			} else if (action.equals("")) {
			
			} 
		}
		else {
			String url = "/curriculum/curriculumManage.jsp";
			String title = "Quản lý chương trình học";
			request.setAttribute("title", title);
			
			NganhDAO nganhDAO = new NganhDAO();
			ArrayList<Nganh> dsNganh = nganhDAO.selectAll();
			
			HocKyDAO hocKyDAO = new HocKyDAO();
			ArrayList<HocKy> dsHocKy = hocKyDAO.selectAll();
			
			MonHocDAO monHocDAO = new MonHocDAO();
			ArrayList<MonHoc> dsMonHoc = monHocDAO.selectAll();
			
			HttpSession session = request.getSession();
			session.setAttribute("dsNganh", dsNganh);
			session.setAttribute("dsHocKy", dsHocKy);
			session.setAttribute("dsMonHoc", dsMonHoc);
			
			RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
			rd.forward(request, response);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if (action != null) {
			if (action.equals("searchCurriculum")) {
				searchCurriculum(request, response);
			} else if (action.equals("cancelCreditCourse")) {
				//cancelCreditCourse(request, response);
			} 
		}
	}
	
	protected void searchCurriculum(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "/curriculum/curriculumManage.jsp";
		String mnhID = (String) request.getParameter("mnhID");
		String tenNganh= (String) request.getParameter("tenNganh");
		System.out.println(tenNganh);
		NganhDAO nganhDAO = new NganhDAO();
		ArrayList<Nganh> dsNganh= nganhDAO.search(mnhID, tenNganh);
		HttpSession session = request.getSession();
		session.setAttribute("dsNganh", dsNganh);
		RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
		rd.forward(request, response);
	}

}
