package controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.CTNamHoc_HocKyDAO;
import database.GiangVienDAO;
import database.HocKyDAO;
import database.KhoaDAO;
import database.KhoaHocDAO;
import database.LopDAO;
import database.NamHocDAO;
import database.QueQuanDAO;
import model.CTNamHoc_HocKy;
import model.GiangVien;
import model.HocKy;
import model.Khoa;
import model.KhoaHoc;
import model.Lop;
import model.NamHoc;
import model.QueQuan;

/**
 * Servlet implementation class MH_HKController
 */
@WebServlet("/manhochocky")
public class MH_HKController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MH_HKController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if (action == null) {
		String url = "/MH_HK/MH_HKManage.jsp";
		String title = "Quản lý học kì - năm học";
		request.setAttribute("title", title);
		
		CTNamHoc_HocKyDAO mhhkDAO = new CTNamHoc_HocKyDAO();
		ArrayList<CTNamHoc_HocKy> dsNamHocHocKi= mhhkDAO.selectAll();
		HttpSession session = request.getSession();
		session.setAttribute("dsNamHocHocKi", dsNamHocHocKi);
		
		NamHocDAO mhDAO = new NamHocDAO();
		ArrayList<NamHoc> dsNamHoc = mhDAO.selectAll();
		session.setAttribute("dsNamHoc", dsNamHoc);
		
		RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
		rd.forward(request, response);
		}
		else {
			if (action.equals("update")) {
				UpdateGet(request, response);
			} else if (action.equals("add")) {
				AddGet(request, response);
			} else if (action.equals("")) {
				
			}else if (action.equals("")) {
				
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if (action.equals("update")) {
			UpdatePost(request, response);
		}else if (action.equals("add")) {
			AddPost(request, response);
		}else if (action.equals("search")) {
			SearchPost(request,response);
		}else if (action.equals("delete")) {
			Delete(request, response);
		}
	}
	
	protected void SearchPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "/MH_HK/MH_HKManage.jsp";
		String mhhkID = (String) request.getParameter("mhhkID");
		String MaNamHoc= (String) request.getParameter("MaNamHoc");
		String MaHocKy = (String) request.getParameter("MaHocKy");
		CTNamHoc_HocKyDAO mhhkDAO = new CTNamHoc_HocKyDAO();
		ArrayList<CTNamHoc_HocKy> dsNamHocHocKi= mhhkDAO.search(mhhkID, MaNamHoc, MaHocKy);
		HttpSession session = request.getSession();
		session.setAttribute("dsNamHocHocKi", dsNamHocHocKi);
		RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
		rd.forward(request, response);
	}
	
	protected void AddGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "/MH_HK/AddMH_HK.jsp";
		String title = "Quản lý học kì - năm học";
		request.setAttribute("title", title);

		HttpSession session = request.getSession();
		
		NamHocDAO mhDAO = new NamHocDAO();
		ArrayList<NamHoc> dsNamHoc = mhDAO.selectAll();
		session.setAttribute("dsNamHoc", dsNamHoc);
		
		RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
		rd.forward(request, response);
	}
	protected void AddPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String namHocHocKy = (String) request.getParameter("NamHocHocKy");
		String namHoc = (String) request.getParameter("NamHoc");
		String hocKy = (String) request.getParameter("HocKy");
		String batDau =  (String) request.getParameter("BatDau");
		String ketThuc =  (String) request.getParameter("KetThuc");
		
		request.setAttribute("errorMessage", "Không thể thêm năm học học kì mới");
		request.setAttribute("NamHocHocKy", namHocHocKy);
		request.setAttribute("NamHoc", namHoc);
		request.setAttribute("HocKy", hocKy);
		request.setAttribute("BatDau", batDau);
		request.setAttribute("KetThuc", ketThuc);
		
		boolean isValidMaNHHK = Validator.validateMaNHHK(namHocHocKy);
//		boolean isValidNamHoc = Validator.validateNamHoc(namHoc);
		
		String errorMessageNamHoc = Validator.validateNamHoc(namHoc);
		String errorMessageMaNHHK  = "";

		if (!isValidMaNHHK) {
			errorMessageMaNHHK = "Cú pháp mã năm học-học kì không hợp lệ.";
			request.setAttribute("errorMessageMaNHHK", errorMessageMaNHHK);
		}
		if (!errorMessageNamHoc.isEmpty()) {
			request.setAttribute("errorMessageNamHoc", errorMessageNamHoc);
		}
		if (!errorMessageMaNHHK.isEmpty() || !errorMessageNamHoc.isEmpty()) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/MH_HK/AddMH_HK.jsp");
			dispatcher.forward(request, response);
			
		} else {
			CTNamHoc_HocKy CTnhhk = new CTNamHoc_HocKy();
			CTNamHoc_HocKyDAO nhhk = new CTNamHoc_HocKyDAO();
			CTnhhk.setmaNHHocKy(namHocHocKy);
			NamHoc namhoc = new NamHoc(namHoc.substring(0,5)+namHoc.substring(namHoc.length()-2,namHoc.length()),namHoc);
			CTnhhk.setnamHoc(namhoc);
			HocKyDAO hkd = new HocKyDAO();
			HocKy hocky = hkd.selectByID(hocKy);
			CTnhhk.sethocKy(hocky);
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate batdau = LocalDate.parse(batDau, formatter);
			LocalDate ketthuc = LocalDate.parse(ketThuc, formatter);
			CTnhhk.setThoiGianBatDau(batdau);
			CTnhhk.setThoiGianKetThuc(ketthuc);
			int result=nhhk.insert(CTnhhk);
			if (result != 0) {
				HttpSession session = request.getSession();
				session.setAttribute("titleToast", "Thành công");	
				session.setAttribute("messageToast", "Tạo năm học học kì thành công !");	
				session.setAttribute("type", "success");	
				session.setAttribute("icon", "bxs-check-circle");	
				response.sendRedirect(request.getContextPath() + "/manhochocky");

			} else {
				
				HttpSession session = request.getSession();
				session.setAttribute("titleToast", "Thất bại");	
				session.setAttribute("messageToast", "Năm học học kì đã tồn tại !");	
				session.setAttribute("type", "error");	
				session.setAttribute("icon", "bxs-error");	

				RequestDispatcher dispatcher = request.getRequestDispatcher("/MH_HK/AddMH_HK.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	protected void UpdateGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "/MH_HK/MH_HKupdate.jsp";
		String title = "Hiệu chỉnh học kì - năm học";
		String namHocHocKy = request.getParameter("maNHHK");
		request.setAttribute("title", title);

		HttpSession session = request.getSession();
		CTNamHoc_HocKyDAO mhhkDAO = new CTNamHoc_HocKyDAO();
		NamHocDAO mhDAO = new NamHocDAO();
		ArrayList<NamHoc> dsNamHoc = mhDAO.selectAll();
		session.setAttribute("dsNamHoc", dsNamHoc);
		CTNamHoc_HocKy namHocHK = mhhkDAO.selectByID(namHocHocKy);
		request.setAttribute("namHocHK", namHocHK);
		RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
		rd.forward(request, response);
	}

	protected void UpdatePost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String namHocHocKy = (String) request.getParameter("NamHocHocKy");
		String namHoc = (String) request.getParameter("NamHoc");
		String hocKy = (String) request.getParameter("HocKy");
		String batDau =  (String) request.getParameter("BatDau");
		String ketThuc =  (String) request.getParameter("KetThuc");
		
		request.setAttribute("errorMessage", "Không thể thêm năm học học kì mới");
		request.setAttribute("NamHocHocKy", namHocHocKy);
		request.setAttribute("NamHoc", namHoc);
		request.setAttribute("HocKy", hocKy);
		request.setAttribute("BatDau", batDau);
		request.setAttribute("KetThuc", ketThuc);
		
		boolean isValidMaNHHK = Validator.validateMaNHHK(namHocHocKy);
//		boolean isValidNamHoc = Validator.validateNamHoc(namHoc);
		
		String errorMessageNamHoc = "";
		String errorMessageMaNHHK  = "";

		if (!isValidMaNHHK) {
			errorMessageMaNHHK = "Cú pháp mã năm học-học kì không hợp lệ.";
			request.setAttribute("errorMessageMaNHHK", errorMessageMaNHHK);
		}
//		if (!isValidNamHoc) {
//			errorMessageNamHoc = "Năm học không hợp lệ.";
//			request.setAttribute("errorMessageNamHoc", errorMessageNamHoc);
//		}
		if (!errorMessageMaNHHK.isEmpty()) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/MH_HK/updateNH_HK.jsp");
			dispatcher.forward(request, response);
			
		} else {
			CTNamHoc_HocKy CTnhhk = new CTNamHoc_HocKy();
			CTNamHoc_HocKyDAO nhhk = new CTNamHoc_HocKyDAO();
			CTnhhk.setmaNHHocKy(namHocHocKy);
			NamHoc namhoc = new NamHoc(namHoc.substring(0,5)+namHoc.substring(namHoc.length()-2,namHoc.length()),namHoc);
			CTnhhk.setnamHoc(namhoc);
			HocKyDAO hkd = new HocKyDAO();
			HocKy hocky = hkd.selectByID(hocKy);
			CTnhhk.sethocKy(hocky);
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate batdau = LocalDate.parse(batDau, formatter);
			LocalDate ketthuc = LocalDate.parse(ketThuc, formatter);
			CTnhhk.setThoiGianBatDau(batdau);
			CTnhhk.setThoiGianKetThuc(ketthuc);
			int result=nhhk.insert(CTnhhk);
			if (result != 0) {
				HttpSession session = request.getSession();
				session.setAttribute("titleToast", "Thành công");	
				session.setAttribute("messageToast", "Tạo năm học học kì thành công !");	
				session.setAttribute("type", "success");	
				session.setAttribute("icon", "bxs-check-circle");	
				response.sendRedirect(request.getContextPath() + "/manhochocky");

			} else {
				
				HttpSession session = request.getSession();
				session.setAttribute("titleToast", "Thất bại");	
				session.setAttribute("messageToast", "Tạo năm học học kỳ thất bại !");	
				session.setAttribute("type", "error");	
				session.setAttribute("icon", "bxs-error");	

				RequestDispatcher dispatcher = request.getRequestDispatcher("/MH_HK/MH_HKupdate.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	protected void Delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String manhhk = request.getParameter("Id");
		CTNamHoc_HocKy CTnhhk = new CTNamHoc_HocKy(manhhk,null,null,null,null);
		CTNamHoc_HocKyDAO nhhk = new CTNamHoc_HocKyDAO();
		int result=nhhk.delete(CTnhhk);
		if (result != 0) {
			HttpSession session = request.getSession();
			session.setAttribute("titleToast", "Thành công");	
			session.setAttribute("messageToast", "Xóa thành công !");	
			session.setAttribute("type", "success");	
			session.setAttribute("icon", "bxs-check-circle");	
			response.sendRedirect(request.getContextPath() + "/manhochocky");

		} else {
			
			HttpSession session = request.getSession();
			session.setAttribute("titleToast", "Thất bại");	
			session.setAttribute("messageToast", "Xóa thất bại !");	
			session.setAttribute("type", "error");	
			session.setAttribute("icon", "bxs-error");	

			response.sendRedirect(request.getContextPath() + "/manhochocky");
		}
	}
}
