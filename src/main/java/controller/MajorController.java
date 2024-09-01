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
import database.NganhDAO;
import model.Khoa;
import model.Nganh;

/**
 * Servlet implementation class MajorController
 */
@WebServlet("/MajorManagement")
public class MajorController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MajorController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if (action == null) {
			String url = "/major/MajorManage.jsp";
			String title = "Quản lý ngành học";
			request.setAttribute("title", title);
			
			NganhDAO nganhDAO = new NganhDAO();
			ArrayList<Nganh> dsNganh = nganhDAO.selectAll();
			KhoaDAO khoaDAO = new KhoaDAO();
			ArrayList<Khoa> dsKhoa = khoaDAO.selectAll();
			
			HttpSession session = request.getSession();
			session.setAttribute("dsNganh", dsNganh);
			session.setAttribute("dsKhoa", dsKhoa);
			
			RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
			rd.forward(request, response);
		}
		else {
			if (action.equals("update")) {
				UpdateMajorGet(request, response);
			} else if (action.equals("add")) {
				AddMajorGet(request, response);
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
		UpdateMajorPost(request, response);
		}else if (action.equals("add")) {
			AddMajorPost(request, response);
		}else if (action.equals("search")) {
			SearchMajor(request, response);
		}else if (action.equals("delete")) {
			DeleteMajor(request, response);
		}
	}
	
	protected void UpdateMajorGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "/major/updateMajor.jsp";
		String title = "Hiệu chỉnh ngành học";
		String MajorID = request.getParameter("majorID");
		request.setAttribute("title", title);
		
		NganhDAO nganhDAO = new NganhDAO();
		Nganh nganh = nganhDAO.selectByID(MajorID);
		request.setAttribute("Nganh", nganh);
		
		RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
		rd.forward(request, response);
	}
	protected void UpdateMajorPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		NganhDAO nganhDAO = new NganhDAO();
		String maNganh = request.getParameter("MaNganh");
		String tenNganh = request.getParameter("TenNganh");
		int giaMotTinChi = Integer.parseInt(request.getParameter("GiaMotTinChi"));
		int soTinChiNganh = Integer.parseInt(request.getParameter("SoTinChiNganh"));
		float thoiGianDaoTao = Float.parseFloat(request.getParameter("ThoiGianDaoTao"));
		String maKhoa = request.getParameter("MaKhoa");
		
		request.setAttribute("MaNganh", maNganh);
		request.setAttribute("TenNganh", tenNganh);
		request.setAttribute("GiaMotTinChi", giaMotTinChi);
		request.setAttribute("SoTinChiNganh", soTinChiNganh);
		request.setAttribute("ThoiGianDaoTao", thoiGianDaoTao);
		request.setAttribute("MaKhoa", maKhoa);
		
		boolean isValidateMaNganh = Validator.validateMaNganh(maNganh);
		boolean isValidateTenNganh = Validator.validateTenNganh(maNganh);
		String errorMessageMaNganh = "";
		String errorMessageTenNganh= "";
		if (!isValidateMaNganh) {
			errorMessageMaNganh = "Mã ngành gồm 7 số.";
			request.setAttribute("errorMessageMaNganh", errorMessageMaNganh);
		}
		if (!isValidateTenNganh) {
			errorMessageTenNganh = "Tên ngành không hợp lệ.";
			request.setAttribute("errorMessageTenNganh", errorMessageTenNganh);
		}
		if (!errorMessageMaNganh.isEmpty()) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/major/updateMajor.jsp");
			dispatcher.forward(request, response);
		} else {
			Nganh Nganh = new Nganh();
			Nganh.setMaNganh(maNganh);
			Nganh.setTenNganh(tenNganh);
			Nganh.setGiaMotTinChi(giaMotTinChi);
			Nganh.setSoTinChi(soTinChiNganh);
			Nganh.setThoiGianDaoTao(thoiGianDaoTao);
			KhoaDAO khoaDAO = new KhoaDAO();
			Khoa Khoa = khoaDAO.selectByID(maKhoa);
			Nganh.setKhoa(Khoa);
			int result = nganhDAO.update(Nganh);
			if (result != 0) {
				HttpSession session = request.getSession();
				session.setAttribute("titleToast", "Thành công");
				session.setAttribute("messageToast", "Cập nhật môn học thành công !");
				session.setAttribute("type", "success");
				session.setAttribute("icon", "bxs-check-circle");
				response.sendRedirect(request.getContextPath() + "/MajorManagement");

			} else {
				HttpSession session = request.getSession();
				session.setAttribute("titleToast", "Thất bại");
				session.setAttribute("messageToast", "Cập nhật môn học đã tồn tại !");
				session.setAttribute("type", "error");
				session.setAttribute("icon", "bxs-error");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/major/updateMajor.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	protected void AddMajorGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "/major/addMajor.jsp";
		String title = "Thêm ngành học";
		request.setAttribute("title", title);
			
		RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
		rd.forward(request, response);
		}
	protected void AddMajorPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	NganhDAO nganhDAO = new NganhDAO();
	String maNganh = request.getParameter("MaNganh");
	String tenNganh = request.getParameter("TenNganh");
	int giaMotTinChi = Integer.parseInt(request.getParameter("GiaMotTinChi"));
	int soTinChiNganh = Integer.parseInt(request.getParameter("SoTinChiNganh"));
	float thoiGianDaoTao = Float.parseFloat(request.getParameter("ThoiGianDaoTao"));
	String maKhoa = request.getParameter("MaKhoa");
	
	request.setAttribute("MaNganh", maNganh);
	request.setAttribute("TenNganh", tenNganh);
	request.setAttribute("GiaMotTinChi", giaMotTinChi);
	request.setAttribute("SoTinChiNganh", soTinChiNganh);
	request.setAttribute("ThoiGianDaoTao", thoiGianDaoTao);
	request.setAttribute("MaKhoa", maKhoa);
	
	boolean isValidateMaNganh = Validator.validateMaNganh(maNganh);
	boolean isValidateTenNganh = Validator.validateTenNganh(maNganh);
	String errorMessageMaNganh = "";
	String errorMessageTenNganh= "";
	if (!isValidateMaNganh) {
		errorMessageMaNganh = "Mã ngành gồm 7 số.";
		request.setAttribute("errorMessageMaNganh", errorMessageMaNganh);
	}
	if (!isValidateTenNganh) {
		errorMessageTenNganh = "Tên ngành không hợp lệ.";
		request.setAttribute("errorMessageTenNganh", errorMessageTenNganh);
	}
	if (!errorMessageMaNganh.isEmpty()) {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/major/addMajor.jsp");
		dispatcher.forward(request, response);
	} else {
		Nganh Nganh = new Nganh();
		Nganh.setMaNganh(maNganh);
		Nganh.setTenNganh(tenNganh);
		Nganh.setGiaMotTinChi(giaMotTinChi);
		Nganh.setSoTinChi(soTinChiNganh);
		Nganh.setThoiGianDaoTao(thoiGianDaoTao);
		KhoaDAO khoaDAO = new KhoaDAO();
		Khoa Khoa = khoaDAO.selectByID(maKhoa);
		Nganh.setKhoa(Khoa);
		int result = nganhDAO.insert(Nganh);
		if (result != 0) {
			HttpSession session = request.getSession();
			session.setAttribute("titleToast", "Thành công");
			session.setAttribute("messageToast", "Thêm môn học thành công !");
			session.setAttribute("type", "success");
			session.setAttribute("icon", "bxs-check-circle");
			response.sendRedirect(request.getContextPath() + "/MajorManagement");

		} else {
			HttpSession session = request.getSession();
			session.setAttribute("titleToast", "Thất bại");
			session.setAttribute("messageToast", "Thêm môn học đã tồn tại !");
			session.setAttribute("type", "error");
			session.setAttribute("icon", "bxs-error");
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/major/addMajor.jsp");
			dispatcher.forward(request, response);
		}
	}
	}
	protected void DeleteMajor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String maNganh = request.getParameter("Id");
		NganhDAO nganhDAO = new NganhDAO();
		int result = nganhDAO.delete(new Nganh(maNganh,null,0,0,0,null));
		if(result !=0) {
			HttpSession session = request.getSession();
			session.setAttribute("titleToast", "Thành công");	
			session.setAttribute("messageToast", "Xóa môn học thành công !");	
			session.setAttribute("type", "success");	
			session.setAttribute("icon", "bxs-check-circle");
			response.sendRedirect(request.getContextPath() + "/MajorManagement");
		}
		else {
			
			HttpSession session = request.getSession();
			session.setAttribute("titleToast", "Cảnh báo");	
			session.setAttribute("messageToast", "Xóa môn học không thành công !");	
			session.setAttribute("type", "error");	
			session.setAttribute("icon", "bxs-error");
			response.sendRedirect(request.getContextPath() + "/MajorManagement");
		}
		
	}
	protected void SearchMajor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "/major/MajorManage.jsp";
		String maKhoa = (String) request.getParameter("MaKhoa");
		String maNganh = (String) request.getParameter("MaNganh");
		String tenNganh = (String) request.getParameter("TenNganh");
		
		request.setAttribute("MaNganh", maNganh);
		request.setAttribute("TenNganh", tenNganh);
		
		NganhDAO nganhDAO = new NganhDAO();
		ArrayList<Nganh> dsNganh = nganhDAO.search(maKhoa, maNganh, tenNganh);
		
		HttpSession session = request.getSession();
		session.setAttribute("dsNganh", dsNganh);
		
		RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
		rd.forward(request, response);
	}
}
