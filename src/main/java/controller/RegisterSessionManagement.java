package controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import model.CTNamHoc_HocKy;
import model.Khoa;
import model.PhienDangKy;
import database.PhienDangKyDAO;
import database.CTNamHoc_HocKyDAO;
import database.KhoaDAO;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/registersession")
public class RegisterSessionManagement extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegisterSessionManagement() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("action");
		if (action != null) {
			if (action.equals("timKiem")) {
				timKiem(request, response);
			} else if (action.equals("taoPhien")) {
				taoPhien(request, response);
			} else if (action.equals("dlTaoPhien")) {
				dlTaoPhien(request, response);
			} else if (action.equals("thongTinUpdate")) {
				thongTinUpdate(request, response);
			} else if (action.equals("updatePhien")) {
				updatePhien(request, response);
			}else if(action.equals("delPhien")) {
				DelPhien(request, response);
			}
		} else {
			KhoaDAO khoaDAO = new KhoaDAO();
			ArrayList<Khoa> dsKhoa = khoaDAO.selectAll();
			CTNamHoc_HocKyDAO ctnhhkd=new CTNamHoc_HocKyDAO();
			PhienDangKyDAO pdkd = new PhienDangKyDAO();
			ArrayList<PhienDangKy> dsPdk = pdkd.selectAll();
			dsPdk.sort(Comparator.comparing(PhienDangKy::getThoiGianBatDau).reversed());
			String title = "Quản lý phiên đăng ký";
			String url = "/register_session/sessionManage.jsp";
			HttpSession session = request.getSession();
			session.setAttribute("title", title);
			session.setAttribute("dsKhoa", dsKhoa);
			session.setAttribute("dsPdk", dsPdk);
			session.setAttribute("dsCTNamHoc_HocKy", ctnhhkd.selectAll());
			RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
			rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	private void timKiem(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String khoa = request.getParameter("Khoa");
		String nbd = request.getParameter("ngayBatDau");
		String nkt = request.getParameter("ngayKetThuc");
		System.out.println(nbd + "A");
		System.out.println(nkt);
		PhienDangKyDAO pdkd = new PhienDangKyDAO();
		ArrayList<PhienDangKy> dsPdk = new ArrayList<>();

		if (!nbd.equals("") && !nkt.equals("") && khoa == null) {
			String nbd1 = nbd + " 00:00:00";
			String nkt1 = nkt + " 00:00:00";
			// chuyển thời gian từ String sang LocalDateTime
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			LocalDateTime thoiGianBatDau = LocalDateTime.parse(nbd1, formatter);
			LocalDateTime thoiGianKetThuc = LocalDateTime.parse(nkt1, formatter);
			// ----------------------------------------------

			for (PhienDangKy pdk : pdkd.selectAll()) {
				if (pdk.getThoiGianBatDau().compareTo(thoiGianBatDau) >= 0
						&& pdk.getThoiGianKetThuc().compareTo(thoiGianKetThuc) <= 0) {
					dsPdk.add(pdk);
				}
			}
		} else if (khoa != null && nbd.equals("") && nkt.equals("")) {
			for (PhienDangKy pdk : pdkd.selectAll()) {
				if (pdk.getKhoa().getMaKhoa().equals(khoa)) {
					dsPdk.add(pdk);
				}
			}
		} else if (khoa != null && !nbd.equals("") && !nkt.equals("")) {
			String nbd1 = nbd + " 00:00:00";
			String nkt1 = nkt + " 00:00:00";
			// chuyển thời gian từ String sang LocalDateTime
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			LocalDateTime thoiGianBatDau = LocalDateTime.parse(nbd1, formatter);
			LocalDateTime thoiGianKetThuc = LocalDateTime.parse(nkt1, formatter);
			// ----------------------------------------------

			for (PhienDangKy pdk : pdkd.selectAll()) {
				if (pdk.getThoiGianBatDau().compareTo(thoiGianBatDau) >= 0
						&& pdk.getThoiGianKetThuc().compareTo(thoiGianKetThuc) <= 0
						&& pdk.getKhoa().getMaKhoa().equals(khoa)) {
					dsPdk.add(pdk);
				}
			}
		} else {
			dsPdk = null;
		}
		String url = "/register_session/sessionManage.jsp";
		HttpSession session = request.getSession();
		session.setAttribute("dsPdk", dsPdk);
		RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
		rd.forward(request, response);
	}

	private String taoMaPhien() {
		Random random = new Random();
		String result;
		do {
			// Tạo một ký tự ngẫu nhiên
			char rdChar1 = (char) (random.nextInt(26) + 'A');
			char rdChar2 = (char) (random.nextInt(26) + 'A');
			char rdChar3 = (char) (random.nextInt(26) + 'A');
			char rdChar4 = (char) (random.nextInt(26) + 'A');
			// Chuyển thành dãy String
			result = "PDK" + rdChar1 + rdChar2 + rdChar3 + rdChar4;
		} while (daTonTai(result));

		return result;
	}

	private boolean daTonTai(String maPhien) {
		ArrayList<PhienDangKy> dsPdk = (new PhienDangKyDAO().selectAll());
		for (PhienDangKy pdk : dsPdk) {
			if (maPhien.equals(pdk.getMaPhien())) {
				return true; // Mã đã tồn tại
			}
		}
		return false; // Mã không tồn tại
	}

	private void dlTaoPhien(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = "/register_session/addSession.jsp";
		String title = "Tao phiên đăng ký học phần";
		KhoaDAO khoaD = new KhoaDAO();
		ArrayList<Khoa> dsKhoa = khoaD.selectAll();
		HttpSession session = request.getSession();
		session.setAttribute("title", title);
		session.setAttribute("dsKhoa", dsKhoa);
		session.setAttribute("message", null);
		RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
		rd.forward(request, response);
	}

	private void taoPhien(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String maKhoa = request.getParameter("maKhoa");
		String ngayBatDau = request.getParameter("ngayBatDau");
		String tgBatDau = request.getParameter("tgBatDau");
		String ngayKetThuc = request.getParameter("ngayKetThuc");
		String tgKetThuc = request.getParameter("tgKetThuc");
		String maNHHocKy = request.getParameter("maNHHocKy");
		// xử lý thời gian trên URL
		try {
			// Giải mã chuỗi URL
			tgBatDau = URLDecoder.decode(tgBatDau, "UTF-8");
			tgKetThuc = URLDecoder.decode(tgKetThuc, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		// chuyển ngày bắt đầu và thời gian bắt đầu(kết thúc) về LocalDateTime
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime thoiGianBatDau = LocalDateTime.parse(ngayBatDau + " " + tgBatDau, formatter);
		LocalDateTime thoiGianKetThuc = LocalDateTime.parse(ngayKetThuc + " " + tgKetThuc, formatter);

		boolean error = false;
		String message = "";
		LocalDateTime now = LocalDateTime.now();
		PhienDangKyDAO pdkd = new PhienDangKyDAO();
		// kiểm tra trùng phiên
		for (PhienDangKy pdk : pdkd.selectAll()) {
			if (pdk.getThoiGianBatDau().compareTo(thoiGianBatDau) == 0
					&& pdk.getThoiGianKetThuc().compareTo(thoiGianKetThuc) == 0
					&& pdk.getKhoa().getMaKhoa().equals(maKhoa)) {
				error = true;
				message = "Phiên đăng ký đã tồn tại!";
			}
		}

		// kiểm tra nếu phiên quá cũ
		if (now.compareTo(thoiGianKetThuc) >= 0) {
			error = true;
			message = "Phiên đăng ký không hợp lệ thông tin!";
		}

		if (error) {
			HttpSession session = request.getSession();
			session.setAttribute("message", message);
			session.setAttribute("maKhoa", maKhoa);
			session.setAttribute("ngayBatDau", ngayBatDau);
			session.setAttribute("ngayKetThuc", ngayKetThuc);
			RequestDispatcher dispatcher = request.getRequestDispatcher("register_session/addSession.jsp");
			dispatcher.forward(request, response);
		} else {
			PhienDangKy newPdk = new PhienDangKy();
			Khoa khoa = (new KhoaDAO().selectByID(maKhoa));
			CTNamHoc_HocKyDAO ctnhhkd=new CTNamHoc_HocKyDAO();
			CTNamHoc_HocKy ctnhhk=ctnhhkd.selectByID(maNHHocKy);
			newPdk.setKhoa(khoa);
			newPdk.setThoiGianBatDau(thoiGianBatDau);
			newPdk.setThoiGianKetThuc(thoiGianKetThuc);
			newPdk.setMaPhien(taoMaPhien());
			newPdk.setCtNamHocHocKy(ctnhhk);
			PhienDangKyDAO pdkDAO = new PhienDangKyDAO();
			pdkDAO.insert(newPdk);
			HttpSession session = request.getSession();
			session.setAttribute("message", "Đăng ký Phiên thành công!");
			RequestDispatcher dispatcher = request.getRequestDispatcher("register_session/addSession.jsp");
			dispatcher.forward(request, response);
		}
	}

	private void updatePhien(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String maPhien = request.getParameter("maPhien");
		String maKhoaMoi = request.getParameter("maKhoaMoi");
		String ngayBatDauMoi = request.getParameter("ngayBatDauMoi");
		String tgBatDauMoi = request.getParameter("tgBatDauMoi") + ":00";
		String ngayKetThucMoi = request.getParameter("ngayKetThucMoi");
		String tgKetThucMoi = request.getParameter("tgKetThucMoi") + ":00";
		// xử lý thời gian trên URL
		try {
			// Giải mã chuỗi URL
			tgBatDauMoi = URLDecoder.decode(tgBatDauMoi, "UTF-8");
			tgKetThucMoi = URLDecoder.decode(tgKetThucMoi, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		// chuyển ngày bắt đầu và thời gian bắt đầu(kết thúc) về LocalDateTime
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime thoiGianBatDau = LocalDateTime.parse(ngayBatDauMoi + " " + tgBatDauMoi, formatter);
		LocalDateTime thoiGianKetThuc = LocalDateTime.parse(ngayKetThucMoi + " " + tgKetThucMoi, formatter);

		boolean error = false;
		String message = "";
		LocalDateTime now = LocalDateTime.now();
		PhienDangKyDAO pdkd = new PhienDangKyDAO();
		// kiểm tra trùng phiên
		for (PhienDangKy pdk : pdkd.selectAll()) {
			if (pdk.getThoiGianBatDau().compareTo(thoiGianBatDau) == 0
					&& pdk.getThoiGianKetThuc().compareTo(thoiGianKetThuc) == 0
					&& pdk.getKhoa().getMaKhoa().equals(maKhoaMoi)) {
				error = true;
				message = "Phiên đăng ký đã tồn tại!";
			}
		}

		// kiểm tra nếu phiên quá cũ
		if (now.compareTo(thoiGianKetThuc) >= 0) {
			error = true;
			message = "Phiên đăng ký không hợp lệ thông tin!";
		}
		if (error) {
			HttpSession session = request.getSession();
			session.setAttribute("message", message);
			session.setAttribute("maKhoa", maKhoaMoi);
			session.setAttribute("ngayBatDau", ngayBatDauMoi);
			session.setAttribute("ngayKetThuc", ngayKetThucMoi);
			RequestDispatcher dispatcher = request.getRequestDispatcher("register_session/updateSession.jsp");
			dispatcher.forward(request, response);
		} else {
			PhienDangKyDAO phienOld = new PhienDangKyDAO();
			PhienDangKy oldPdk = phienOld.selectByID(maPhien);
			Khoa khoa = (new KhoaDAO().selectByID(maKhoaMoi));
			oldPdk.setKhoa(khoa);
			oldPdk.setThoiGianBatDau(thoiGianBatDau);
			oldPdk.setThoiGianKetThuc(thoiGianKetThuc);
			oldPdk.setMaPhien(maPhien);

			PhienDangKyDAO pdkDAO = new PhienDangKyDAO();
			pdkDAO.update(oldPdk);
			HttpSession session = request.getSession();

			session.setAttribute("message", "Cập nhật Phiên thành công!");
			RequestDispatcher dispatcher = request.getRequestDispatcher("register_session/updateSession.jsp");
			dispatcher.forward(request, response);
		}
	}

	private void thongTinUpdate(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String maPhien = request.getParameter("maPhien");
		PhienDangKyDAO pdkd = new PhienDangKyDAO();
		PhienDangKy pdk = pdkd.selectByID(maPhien);
		HttpSession session = request.getSession();
		session.setAttribute("pdk", pdk);
		RequestDispatcher dispatcher = request.getRequestDispatcher("register_session/updateSession.jsp");
		dispatcher.forward(request, response);
	}

	private void DelPhien(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String maPhien = request.getParameter("Id");
		PhienDangKyDAO pdkd = new PhienDangKyDAO();
		PhienDangKy pdk = pdkd.selectByID(maPhien);
		if (pdk != null) {
			pdkd.delete(pdk);
			System.out.println("Đã xóa phiên đăng ký với mã: " + maPhien);
		} else {
			System.out.println("Không tìm thấy phiên đăng ký với mã: " + maPhien);
		}
		PhienDangKyDAO pdkd2 = new PhienDangKyDAO();
		HttpSession session = request.getSession();
		ArrayList<PhienDangKy>dsPdk=pdkd2.selectAll();
		dsPdk.sort(Comparator.comparing(PhienDangKy::getThoiGianBatDau).reversed());
		session.setAttribute("message", "Xóa Phiên thành công!");
		session.setAttribute("dsPdk", dsPdk);
		RequestDispatcher dispatcher = request.getRequestDispatcher("register_session/sessionManage.jsp");
		dispatcher.forward(request, response);
	}
}
