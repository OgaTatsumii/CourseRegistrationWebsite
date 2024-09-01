package controller;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.ChucVuDAO;
import database.KhoaHocDAO;
import database.LopDAO;
import database.NganhDAO;
import database.SinhVienDAO;
import database.TaiKhoanDAO;
import model.KhoaHoc;
import model.Lop;
import model.Nganh;
import model.SinhVien;
import model.TaiKhoan;
import model.ChucVu;
import util.Email;

/**
 * Servlet implementation class ForgotPassword
 */
@WebServlet("/passwordController")
public class PassWordController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PassWordController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("action");
		if (action != null) {
			if (action.equals("nhapMatKhauMoi")) {
				nhapMatKhauMoi(request, response);
			}
			else if (action.equals("doiMatKhau")) {
				doiMatKhau(request, response);
			}
			else if (action.equals("dangNhap")) {
				kiemTraTonTaiEmailPassword(request, response);
			}
			
		}else {
			String email = request.getParameter("email");
			System.out.print(email);
			SinhVienDAO svd=new SinhVienDAO();
			boolean kiemTra=svd.selectByMail(email);
			String message=(kiemTra)?"Kiểm tra email để xem mã xác nhận!":"Không tìm thấy sinh viên!";
			System.out.print(message);
			HttpSession session = request.getSession();
			String url = "/forgotPassword.jsp";
			String token="";
			if(kiemTra) {
				token=generateRandomCode();
				//Email.sendEmail(email, "ITCOTPS", "Mã xác nhận của bạn là: "+token);
				url="/forgotPassword.jsp";
				System.out.print(token);
			}
			session.setAttribute("message2", message);
			session.setAttribute("email", email);
			session.setAttribute("token", token);
			session.setMaxInactiveInterval(15 * 60);
			RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
			rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	public static String getMD5(String input) {
        try {
            // Tạo instance của MessageDigest với thuật toán MD5
            MessageDigest md = MessageDigest.getInstance("MD5");

            // Chuyển đổi chuỗi đầu vào thành mảng byte và cập nhật MessageDigest
            byte[] messageDigest = md.digest(input.getBytes());

            // Chuyển đổi mảng byte thành số nguyên lớn dương
            BigInteger no = new BigInteger(1, messageDigest);

            // Chuyển số nguyên lớn thành chuỗi hexadecimal (hệ 16)
            String hashText = no.toString(16);

            // Thêm tiền tố 0 để đảm bảo chuỗi có độ dài 32 ký tự
            while (hashText.length() < 32) {
                hashText = "0" + hashText;
            }

            return hashText;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
	
	private void kiemTraTonTaiEmailPassword(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String password_md5=getMD5(password);
		SinhVienDAO svd=new SinhVienDAO();
		String url="";
		String message="";
		if(svd.selectByMail(email)) {
			String username = email.substring(0, email.indexOf('@'));
			SinhVien sv=svd.selectByUserName(username);
			if(sv.getTaiKhoan().getMatKhau().equals(password_md5)) {
				url="/resetPassword.jsp";
			}
			else {
				url="/doiMatKhau.jsp";
				message="Mật khẩu không chính xác!";
			}
		}else {
			url="/doiMatKhau.jsp";
			message="Không tìm thấy tài khoản!";
		}
		HttpSession session = request.getSession();
		session.setAttribute("email", email);
		session.setAttribute("message", message);
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
		}
	
	private void nhapMatKhauMoi(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("email");
		HttpSession session = request.getSession();
		session.setAttribute("email", email);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/resetPassword.jsp");
		dispatcher.forward(request, response);
		}
	
	private void doiMatKhau(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("email");
		String matKhauMoi = request.getParameter("newPassword");
		
		SinhVienDAO svd=new SinhVienDAO();
		String username = email.substring(0, email.indexOf('@'));
		SinhVien sv=svd.selectByID(username);
		ChucVuDAO cvd=new ChucVuDAO();
		ChucVu cv=cvd.selectByID(sv.getTaiKhoan().getChucVu().getMaChucVu());
		
		TaiKhoanDAO tkd=new TaiKhoanDAO();
		TaiKhoan tk=new TaiKhoan();
		System.out.print(username);
		System.out.print(matKhauMoi);
		System.out.print(cv.getTenChucVu());
		
		tk.setUserName(username);
		tk.setMatKhau(matKhauMoi);
		tk.setChucVu(cv);
		tkd.update(tk);
		HttpSession session = request.getSession();
		session.setAttribute("email", email);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
		dispatcher.forward(request, response);
		}
	
	public static String generateRandomCode() {
        Random random = new Random();
        int randomNumber = 100000 + random.nextInt(900000); // 100000 đến 999999
        return String.valueOf(randomNumber);
    }

}
