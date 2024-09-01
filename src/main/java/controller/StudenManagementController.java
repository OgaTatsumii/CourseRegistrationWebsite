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

import database.KhoaDAO;
import database.KhoaHocDAO;
import database.LopDAO;
import database.KhoaHocDAO;
import database.NganhDAO;
import database.QueQuanDAO;
import database.SinhVienDAO;
import model.Khoa;
import model.Lop;
import model.KhoaHoc;
import model.Nganh;
import model.QueQuan;
import model.SinhVien;

/**
 * Servlet implementation class StudenManagement
 */
@WebServlet("/studentManage")
public class StudenManagementController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudenManagementController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "/student/studentManage.jsp";
		String title = "Quản lý sinh viên";
		request.setAttribute("title", title);
		KhoaDAO khoaDAO = new KhoaDAO();
		ArrayList<Khoa> dsKhoa = khoaDAO.selectAll();
		
		NganhDAO nganhDAO = new NganhDAO();
		ArrayList<Nganh> dsNganh = nganhDAO.selectAll();
		
		LopDAO lopDAO = new LopDAO();
		ArrayList<Lop> dsLop = lopDAO.selectAll();
		
		KhoaHocDAO KhoaHocDAO = new KhoaHocDAO();
		ArrayList<KhoaHoc> dsKhoaHoc = KhoaHocDAO.selectAll();
		
		SinhVienDAO sinhVienDAO = new SinhVienDAO();
		ArrayList<SinhVien> dsSinhVien = sinhVienDAO.selectAll();
		
		QueQuanDAO queQuanDAO = new QueQuanDAO();
		ArrayList<QueQuan> dsQueQuan = queQuanDAO.selectAll();
		
		HttpSession session = request.getSession();
		session.setAttribute("dsKhoa", dsKhoa);
		session.setAttribute("dsNganh", dsNganh);
		session.setAttribute("dsLop", dsLop);
		session.setAttribute("dsKhoaHoc", dsKhoaHoc);
		session.setAttribute("dsSinhVien", dsSinhVien);
		session.setAttribute("dsQueQuan", dsQueQuan);
		
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
