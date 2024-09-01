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

import database.HocKyDAO;
import database.KhoaDAO;
import database.KhoaHocDAO;
import database.LopDAO;
import database.MonHocDAO;
import database.NganhDAO;
import database.QueQuanDAO;
import database.SinhVienDAO;
import model.HocKy;
import model.Khoa;
import model.KhoaHoc;
import model.Lop;
import model.MonHoc;
import model.Nganh;
import model.QueQuan;
import model.SinhVien;

/**
 * Servlet implementation class SubjectManagementController
 */
@WebServlet("/subjectManagement")
public class SubjectManagementController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SubjectManagementController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "/subject/subjectManage.jsp";
		String title = "Quản lý môn học";
		request.setAttribute("title", title);
		
		KhoaDAO khoaDAO = new KhoaDAO();
		ArrayList<Khoa> dsKhoa = khoaDAO.selectAll();
		
		NganhDAO nganhDAO = new NganhDAO();
		ArrayList<Nganh> dsNganh = nganhDAO.selectAll();
		
		HocKyDAO hocKyDAO = new HocKyDAO();
		ArrayList<HocKy> dsHocKy = hocKyDAO.selectAll();
		
		MonHocDAO monHocDAO = new MonHocDAO();
		ArrayList<MonHoc> dsMonHoc = monHocDAO.selectAll();
		
		HttpSession session = request.getSession();
		session.setAttribute("dsKhoa", dsKhoa);
		session.setAttribute("dsNganh", dsNganh);
		session.setAttribute("dsHocKy", dsHocKy);
		session.setAttribute("dsMonHoc", dsMonHoc);
		
		RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
