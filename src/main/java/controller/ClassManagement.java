package controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Random;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import model.Lop;
import model.LopTinChi;
import model.Nganh;
import model.Nganh_HocKy;
import model.CTLopTinChi;
import model.CTMonHoc;
import model.KhoaHoc;
import model.SinhVien;
import database.LopDAO;
import database.LopTinChiDAO;
import database.NganhDAO;
import database.Nganh_HocKyDAO;
import database.CTLopTinChiDAO;
import database.CTMonHocDAO;
import database.KhoaHocDAO;
import database.SinhVienDAO;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/classManage")
public class ClassManagement extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ClassManagement() {
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
			if (action.equals("layDuLieuThem")) {
				duLieuThemLop(request, response);
			} else if (action.equals("taoClass")) {
				taoClass(request, response);
			} else if (action.equals("timKiem")) {
				timKiem(request, response);
			} else if (action.equals("suaClass")) {
				suaClass(request, response);
			} else if (action.equals("dsDelClass")) {
				dsDelClass(request, response);
			} else if (action.equals("delClass")) {
				delClass(request, response);
			} else if (action.equals("suaDuLieuClass")) {
				suaDuLieuClass(request, response);
			} else if (action.equals("xemSinhVien")) {
				xemSinhVien(request, response);
			} else if (action.equals("dangKyMonChoLop")) {
				dangKyMonChoLop(request, response);
			}
		} else {
			LopDAO LopDAO = new LopDAO();
			ArrayList<Lop> dsLop = LopDAO.selectAll();
			NganhDAO nganhDAO = new NganhDAO();
			ArrayList<Nganh> dsNganh = nganhDAO.selectAll();
			KhoaHocDAO khoaHocDAO = new KhoaHocDAO();
			ArrayList<KhoaHoc> dsKhoaHoc = khoaHocDAO.selectAll();
			String title = "Quản lý lớp học";
			String url = "/classes/classManage.jsp";
			HttpSession session = request.getSession();
			session.setAttribute("title", title);
			session.setAttribute("dsLop", dsLop);
			session.setAttribute("dsNganh", dsNganh);
			session.setAttribute("dsKhoaHoc", dsKhoaHoc);
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

	protected void timKiem(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String maNganh = request.getParameter("Nganh");
		String khoaHoc = request.getParameter("KhoaHoc");
		LopDAO ld = new LopDAO();
		ArrayList<Lop> ds = ld.selectAll();
		ArrayList<Lop> dsLop = new ArrayList<>();

		if (khoaHoc == null && maNganh != null) {
			for (Lop l : ds) {
				if (l.getNganh().getMaNganh().equals(maNganh)) {
					dsLop.add(l);
				}
			}
		} else if (maNganh == null && khoaHoc != null) {
			for (Lop l : ds) {
				if (l.getKhoaHoc().getMaNamHoc().equals(khoaHoc)) {
					dsLop.add(l);
				}
			}
		} else if (khoaHoc == null && maNganh == null) {

		} else {
			for (Lop l : ds) {
				if (l.getKhoaHoc().getMaNamHoc().equals(khoaHoc) && l.getNganh().getMaNganh().equals(maNganh)) {
					dsLop.add(l);
				}
			}
		}
		if (dsLop.isEmpty()) {
			dsLop = null;
		}
		String url = "/classes/classManage.jsp";
		HttpSession session = request.getSession();
		session.setAttribute("dsLop", dsLop);
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);

	}

	public static String filterNameofClass(String a) {
		if (a.equals("CNĐPT")) {
			return "PT";
		} else if (a.equals("CNKTĐĐT")) {
			return "ĐT";
		} else if (a.equals("KTĐTVT")) {
			return "VT";
		} else if (a.equals("KT")) {
			return "KT";
		} else if (a.equals("CNTT")) {
			return "CN";
		} else if (a.equals("ATTT")) {
			return "AT";
		} else if (a.equals("M")) {
			return "MK";
		} else if (a.equals("KTĐKVTĐH")) {
			return "TĐ";
		} else if (a.equals("QTKD")) {
			return "QT";
		} else {
			return null;
		}
	}

	public static String getFirstLetters(String input) {
		if (input == null || input.isEmpty()) {
			// Xử lý trường hợp chuỗi null hoặc rỗng
			return "";
		}
		StringBuilder result = new StringBuilder();
		String[] words = input.split("\\s+"); // Tách chuỗi thành các từ dựa trên khoảng trắng
		for (String word : words) {
			if (!word.isEmpty()) {
				result.append(Character.toUpperCase(word.charAt(0))); // Lấy ký tự đầu tiên và chuyển thành chữ hoa
			}
		}
		return filterNameofClass(result.toString()); // Trả về chuỗi kết quả
	}

	public static String generateNextString(String input) {
		// Kiểm tra xem chuỗi đã tồn tại hay chưa
		if (input.matches(".{7}\\d{2}-.")) {
			// Lấy số cuối cùng trong chuỗi
			int lastNumber = Integer.parseInt(input.substring(7, 9));
			// Tạo số tiếp theo
			int nextNumber = lastNumber + 1;
			// Tạo chuỗi mới với 7 ký tự đầu và ký tự cuối không đổi
			String prefix = input.substring(0, 7);
			String suffix = input.substring(10, 11);
			String nextString = String.format("%s%02d-%s", prefix, nextNumber, suffix);
			return nextString;
		} else {
			// Nếu chuỗi không hợp lệ, trả về thông báo
			return "Chuỗi không hợp lệ!";
		}
	}

	public static String taoMaLop(String a) {
		LopDAO ld = new LopDAO();
		String res = a;
		while (ld.selectByID(res) != null) {
			res = generateNextString(res);
		}
		return res;
	}

	private void taoClass(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String maNganh = request.getParameter("maNganh");
		String khoaHoc = request.getParameter("khoaHoc");
		String heDaoTao = request.getParameter("heDaoTao");
		String coSoDaoTao = request.getParameter("coSoDaoTao");
		String soLuongSinhVien = request.getParameter("soLuongSinhVien");

		NganhDAO nD = new NganhDAO();
		Nganh ng = nD.selectByID(maNganh);

		KhoaHocDAO khD = new KhoaHocDAO();
		KhoaHoc kh = khD.selectByID(khoaHoc);

		String a = khoaHoc + heDaoTao + getFirstLetters(ng.getTenNganh()) + "01" + "-" + coSoDaoTao;
		String maLop = taoMaLop(a);
		System.out.println(maLop);

		LopDAO ld = new LopDAO();
		Lop lop = new Lop();
		lop.setMaLop(maLop);
		lop.setNganh(ng);
		lop.setKhoaHoc(kh);
		lop.setSoLuongSinhVien(Integer.parseInt(soLuongSinhVien));
		ld.insert(lop);

		HttpSession session = request.getSession();
		session.setAttribute("maLop", maLop);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/classes/addClass.jsp");
		dispatcher.forward(request, response);
	}

	private void duLieuThemLop(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = "/classes/addClass.jsp";
		String title = "Tao lớp học mới";
		String maLop = null;
		NganhDAO nganhD = new NganhDAO();
		ArrayList<Nganh> dsNganh = nganhD.selectAll();
		KhoaHocDAO khD = new KhoaHocDAO();
		ArrayList<KhoaHoc> dsKhoaHoc = khD.selectAll();
		HttpSession session = request.getSession();
		session.setAttribute("maLop", maLop);
		session.setAttribute("title", title);
		session.setAttribute("dsNganh", dsNganh);
		session.setAttribute("dsKhoaHoc", dsKhoaHoc);
		RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
		rd.forward(request, response);
	}

	private void suaClass(HttpServletRequest request, HttpServletResponse response)

			throws ServletException, IOException {
		String maLop = request.getParameter("MaLop");
		LopDAO ld = new LopDAO();
		Lop lop = ld.selectByID(maLop);

		NganhDAO nd = new NganhDAO();
		ArrayList<Nganh> dsNganh = nd.selectAll();
		KhoaHocDAO khd = new KhoaHocDAO();
		ArrayList<KhoaHoc> dsKhoaHoc = khd.selectAll();

		HttpSession session = request.getSession();
		session.setAttribute("maLop", maLop);
		session.setAttribute("lop", lop);
		session.setAttribute("dsNganh", dsNganh);
		session.setAttribute("dsKhoaHoc", dsKhoaHoc);
		session.setAttribute("maLopMoi", null);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/classes/updateClass.jsp");
		dispatcher.forward(request, response);
	}

	private void suaDuLieuClass(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String nganhHocMoi = request.getParameter("nganhHocMoi");
		String khoaHocMoi = request.getParameter("khoaHocMoi");
		String heDaoTaoMoi = request.getParameter("heDaoTaoMoi");
		String soLuongSinhVienMoi = request.getParameter("soLuongSinhVienMoi");
		String coSoDaoTaoMoi = request.getParameter("coSoDaoTaoMoi");
		String maLop = request.getParameter("maLop");

		NganhDAO nD = new NganhDAO();
		Nganh ng = nD.selectByID(nganhHocMoi);

		KhoaHocDAO khD = new KhoaHocDAO();
		KhoaHoc kh = khD.selectByID(khoaHocMoi);

		String a = khoaHocMoi + heDaoTaoMoi + getFirstLetters(ng.getTenNganh()) + "01" + "-" + coSoDaoTaoMoi;
		String maLopMoi = taoMaLop(a);
		System.out.print(maLopMoi);
		LopDAO ld = new LopDAO();
		ld.delete(ld.selectByID(maLop));

		Lop lop = new Lop();
		lop.setMaLop(maLopMoi);
		lop.setNganh(ng);
		lop.setKhoaHoc(kh);
		lop.setSoLuongSinhVien(Integer.parseInt(soLuongSinhVienMoi));
		ld.insert(lop);
		HttpSession session = request.getSession();

		session.setAttribute("maLopMoi", maLopMoi);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/classes/updateClass.jsp");
		dispatcher.forward(request, response);
	}

	private void dsDelClass(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String maLopParam = request.getParameter("maLop");
		String[] maLopArray = maLopParam.split(",");
		ArrayList<Lop> dsXoa = new ArrayList<>();
		LopDAO ld = new LopDAO();
		for (String maLop : maLopArray) {
			Lop l = ld.selectByID(maLop);
			dsXoa.add(l);
		}
		HttpSession session = request.getSession();
		session.setAttribute("dsXoa", dsXoa);
		session.setAttribute("message", null);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/classes/delClass.jsp");
		dispatcher.forward(request, response);
	}

	private void delClass(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String maLopParam = request.getParameter("maLop");
		String[] maLopArray = maLopParam.split(",");

		for (String maLop : maLopArray) {
			LopDAO ld = new LopDAO();
			Lop l = ld.selectByID(maLop);
			ld.delete(l);
		}
		HttpSession session = request.getSession();
		session.setAttribute("message", "Đã xóa thành công!");
		session.setAttribute("dsXoa", null);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/classes/delClass.jsp");
		dispatcher.forward(request, response);
	}

	private void xemSinhVien(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String maLop = request.getParameter("maLop");
		LopDAO ld = new LopDAO();
		Lop l = ld.selectByID(maLop);
		SinhVienDAO svd = new SinhVienDAO();
		ArrayList<SinhVien> dsSV = new ArrayList<>();
		for (SinhVien sv : svd.selectAll()) {
			if (sv.getLop().getMaLop().equals(l.getMaLop())) {
				dsSV.add(sv);
			}
		}
		HttpSession session = request.getSession();
		session.setAttribute("message", "Dữ liệu sinh viên!");
		session.setAttribute("dsSV", dsSV);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/classes/sinhVienTrongLop.jsp");
		dispatcher.forward(request, response);
	}

	private void dangKyMonChoLop(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String maLopParam = request.getParameter("maLop");
		String[] maLopArray = maLopParam.split(",");
		for (String maLop : maLopArray) {
			System.out.print(maLop);
			LopDAO ld = new LopDAO();
			Lop l = ld.selectByID(maLop);
			LocalDate now = LocalDate.now();
			LocalDate ngayNhapHoc = l.getKhoaHoc().getNgayBatDau();
			long soNgayDaTroiQua = ChronoUnit.DAYS.between(ngayNhapHoc, now);
			float thoiGianDaoTao = l.getNganh().getThoiGianDaoTao();
			float soNgayTrongKhoa = thoiGianDaoTao * 365;
			int currentHocKy = (int) (soNgayDaTroiQua / (soNgayTrongKhoa / (thoiGianDaoTao * 2))) + 1;
			CTMonHocDAO ctmhd = new CTMonHocDAO();
			SinhVienDAO svd = new SinhVienDAO();
			// đã tìm được môn học cho sinh viên
			Nganh_HocKyDAO nhkdao=new Nganh_HocKyDAO();
			System.out.print("HK"+Integer.toString(currentHocKy));
			Nganh_HocKy nhk=nhkdao.selectByNganhHK(l.getNganh().getMaNganh(), "HK"+Integer.toString(currentHocKy));
			//đã 
			System.out.print(nhk.getMaNganhHocKy());
			ArrayList<CTMonHoc> dsCTMonHoc = ctmhd
					.selectByType(nhk.getMaNganhHocKy());
			ArrayList<SinhVien> dsSV = svd.selectByLop(l.getMaLop());
			int themKhongThanhCong=0;
			for (CTMonHoc ctmh : dsCTMonHoc) {
				System.out.println(ctmh.getMonHoc().getTenMonHoc());
				for (SinhVien sv : dsSV) {
					LopTinChiDAO ltcd = new LopTinChiDAO();
					LopTinChi ltc = ltcd.selectAvailableBySubjectTimeQuantity(ctmh.getMonHoc().getMaMonHoc());
					if (ltc != null) {
						LocalDateTime now1 = LocalDateTime.now();
						CTLopTinChiDAO ctltcd = new CTLopTinChiDAO();
						CTLopTinChi ctltc = new CTLopTinChi();
						ctltc.setLopTinChi(ltc);
						ctltc.setSv(sv);
						ctltc.setTgdk(now1);
						ctltcd.insert(ctltc);
					}
					else {
						themKhongThanhCong+=1;
					}
					
				}
			}
		}
		HttpSession session = request.getSession();
		session.setAttribute("message", "Đã thêm thành công!");
		session.setAttribute("dsXoa", null);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/classes/delClass.jsp");
		dispatcher.forward(request, response);
	}
}
