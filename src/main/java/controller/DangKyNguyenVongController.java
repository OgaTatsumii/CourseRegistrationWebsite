package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.KhoaHocDAO;
import database.LopTinChiDAO;
import database.MonHocDAO;
import database.CTLopTinChiDAO;
import database.KhoaDAO;
import model.LopTinChi;
import model.CTLopTinChi;
import model.Khoa;
import database.NganhDAO;
import database.PhienDangKyDAO;
import database.SinhVienDAO;
import model.KhoaHoc;
import model.Lop;
import model.Nganh;
import model.MonHoc;
import model.PhienDangKy;
import model.SinhVien;
import util.Email;

/**
 * Servlet implementation class DangKyNguyenVongController
 */
@WebServlet("/DangKyNguyenVong")
public class DangKyNguyenVongController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DangKyNguyenVongController() {
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
			if (action.equals("dangKyNguyenVong")) {
				 dangKyNguyenVong(request, response);
			} else if (action.equals("xacNhan")) {
				xacNhan(request, response);
			} else if (action.equals("")) {
			} else if (action.equals("")) {
			} else if (action.equals("")) {
			} else if (action.equals("")) {
			} else if (action.equals("")) {
			} else if (action.equals("")) {
			}
		} else {
			MonHocDAO mhd = new MonHocDAO();
			ArrayList<MonHoc> dsMonHoc = mhd.selectAll();
			String url = "/studentFE/dangKyNguyenVong.jsp";
			HttpSession session = request.getSession();
			session.setAttribute("dsMonHoc", dsMonHoc);
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
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private void xacNhan(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String maSinhVien = request.getParameter("maSinhVien");
		String maMonHoc = request.getParameter("maMonHoc");
		String lyDo = request.getParameter("lyDo");
		Email e=new Email();
		//e.sendEmail("", "", "");
		RequestDispatcher dispatcher = request.getRequestDispatcher("/student/a.jsp");
		dispatcher.forward(request, response);
	}
	
	public static boolean kiemTraKhoaCoTrongThoiGianDangKy(String maKhoa) {
		PhienDangKyDAO pdkd=new PhienDangKyDAO();
		ArrayList<PhienDangKy> dsPDK=pdkd.selectAll();
		
        LocalDateTime nowLDT = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedString = nowLDT.format(formatter);
        LocalDateTime parsedDateTime = LocalDateTime.parse(formattedString, formatter);
		
		for(PhienDangKy pdk:dsPDK) {
			if(pdk.getKhoa().getMaKhoa().equals(maKhoa) && parsedDateTime.compareTo(pdk.getThoiGianBatDau())>0 && parsedDateTime.compareTo(pdk.getThoiGianKetThuc())<0 ) {
				return true;
			}
		}
		return false;
    }
	
	public static String kiemTraLopTinChiCoMonHocDangKyPhuHop(String maMonHoc) {
		LopTinChiDAO ltcd=new LopTinChiDAO();
		LocalDate nowLD=LocalDate.now();
		for(LopTinChi ltc:ltcd.selectAll()) {
			if(nowLD.isAfter(ltc.getNgayBatDau()) && maMonHoc.equals(ltc.getMonHoc().getMaMonHoc()) && ltc.getSoLuongSinhVien()>ltc.getDaDangKy() ) {
				return ltc.getMaLopTinChi();
			}
			
		}
		return "";
    }
	
	public static boolean kiemTraCTLTCDaDangKyHayChua(String maLopTinChi, String maSinhVien) {
		CTLopTinChiDAO ctltcd=new CTLopTinChiDAO();
		for(CTLopTinChi ctltc:ctltcd.selectAll()) {
			if(ctltc.getLopTinChi().getMaLopTinChi().equals(maLopTinChi) && ctltc.getSv().getMaSinhVien().equals(maSinhVien)) {
				return false;
			}
		}
		return true;
    }
	
	private void dangKyNguyenVong(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String maSinhVien = request.getParameter("maSinhVien");
		String maMonHoc = request.getParameter("maMonHoc");
		String lyDo = request.getParameter("lyDo");
		MonHocDAO mhd=new MonHocDAO();
		MonHoc mh=mhd.selectByID(maMonHoc);
		System.out.print(maSinhVien);
		System.out.print(maMonHoc);
		String message1="";
		String message2="";
		LocalDateTime nowLDT = LocalDateTime.now();
		SinhVienDAO svdDao=new SinhVienDAO();
		SinhVien sv=svdDao.selectByID(maSinhVien);
		String maKhoa=sv.getLop().getNganh().getKhoa().getMaKhoa();
		boolean a=kiemTraKhoaCoTrongThoiGianDangKy(maKhoa);
		HttpSession session = request.getSession();
		//Kiểm tra Khoa đó có được đăng ký không
		//kiểm tra lớp tín chỉ có môn muốn đăng ký không và thời gian phù hợp
		//kiểm tra ctltc có tồn tại trước đó không
		String maLopTinChi=kiemTraLopTinChiCoMonHocDangKyPhuHop(maMonHoc);
		System.out.println(maLopTinChi);
		boolean b=false;
		
		if(!maLopTinChi.isEmpty()) {
			b=kiemTraCTLTCDaDangKyHayChua(maLopTinChi,maSinhVien);
		}
		
		if (!a) {
			message1="Chưa đến hạn đăng ký!";
		}
		else if(a && maLopTinChi.isEmpty()) {
			message1="Không tìm thấy lớp cần đăng ký. Xác nhận để gửi yêu cầu đến CTSV";
			session.setAttribute("maMH", mh.getMaMonHoc());
			session.setAttribute("tenMH", mh.getTenMonHoc());
			session.setAttribute("lyDo", lyDo);
			session.setAttribute("tgdk", nowLDT.toString());
			
		}else if(a && !b && !maLopTinChi.isEmpty()) {
			message1="Sinh viên đã đăng ký môn!";
			session.setAttribute("lyDo", "");

		}
		else if(a && b && !maLopTinChi.isEmpty()) {
			message1="Đã thêm thành công!";
			CTLopTinChiDAO ctltcd=new CTLopTinChiDAO();
			CTLopTinChi ctltc=new CTLopTinChi();
			LopTinChiDAO ltcd=new LopTinChiDAO();
			LopTinChi ltc=ltcd.selectByID(maLopTinChi);
			ctltc.setLopTinChi(ltc);
			ctltc.setSv(sv);
			ctltc.setTgdk(nowLDT);
			ctltcd.insert(ctltc);
		}
		session.setAttribute("message1", message1);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/studentFE/dangKyNguyenVong.jsp");
		dispatcher.forward(request, response);
	}
	
	
	
}
