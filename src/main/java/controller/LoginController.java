package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.DiemDAO;
import database.NhanVienDAO;
import database.SinhVienDAO;
import database.TaiKhoanDAO;
import model.Diem;
import model.NhanVien;
import model.SinhVien;
import model.TaiKhoan;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
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
		String userName = request.getParameter("user");
		String matKhau = request.getParameter("password");
		TaiKhoan tk = new TaiKhoan();
		tk.setUserName(userName);
		tk.setMatKhau(matKhau);
		TaiKhoanDAO tkDAO = new TaiKhoanDAO();
		TaiKhoan taiKhoan = tkDAO.selectByUserNameAndPassWord(tk);
		String url = "";
		if (taiKhoan!=null) {
			if(taiKhoan.getChucVu().getMaChucVu().equals("NV")) {
				NhanVien nhanVien = new NhanVien();
				NhanVienDAO nvd = new NhanVienDAO();
				nhanVien = nvd.selectByUserName(userName);
				HttpSession session = request.getSession();
				session.setAttribute("nhanVien", nhanVien);
				url = "/admin.jsp";
			}else if (taiKhoan.getChucVu().getMaChucVu().equals("SV")) {
				SinhVien sinhVien = new SinhVien();
				SinhVienDAO svd = new SinhVienDAO();
				System.out.println(userName);
				sinhVien = svd.selectByUserName(userName);
				HttpSession session = request.getSession();
				session.setAttribute("taiKhoan", taiKhoan);
				session.setAttribute("sinhVien", sinhVien);
				url = "/sinhVien.jsp";
			}
		}else {
			request.setAttribute("baoLoi", "Tài khoản hoặc mật khẩu không chính xác !");
			url = "/login.jsp";
		}
		RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
		rd.forward(request, response);
	}

}
