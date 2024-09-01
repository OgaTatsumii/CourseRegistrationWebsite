package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.NoiDungThongBao;
import model.SinhVien;
import util.Email;
import database.ThongBaoDAO;
import database.LoaiThongBaoDAO;
import database.KhoaDAO;
import database.NganhDAO;
import database.LopDAO;
import database.SinhVienDAO;

@WebServlet("/announceManage")
public class AnnounceManagement extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AnnounceManagement() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.print("hello");
		String action = request.getParameter("action");
		if (action != null) {
			switch (action) {
			case "duyetPhanTrang":
				duyet_theo_trang(request, response);
				break;
			case "guiMailSinhVien":
				guiMailtoObj(request, response);
				break;
			case "guiMail":
				guiMail(request, response);
				break;
			case "layThongTinSua":
				layThongTinSua(request, response);
				break;
			case "suaThongBao":
				suaThongBao(request, response);
				break;
			case "xoaThongBao":
				xoaThongBao(request, response);
				break;
			case "luuThongTin":
				luuThongBao(request, response);
				break;
			default:
				response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Action không hợp lệ");
				break;
			}
		} else {
			String url = "/announce/announceManage.jsp";
			LoaiThongBaoDAO ltbd = new LoaiThongBaoDAO();
			ThongBaoDAO tbd = new ThongBaoDAO();
			HttpSession session = request.getSession();
			session.setAttribute("dsLTB", ltbd.selectAll());
			session.setAttribute("dsNDTB", tbd.selectAll());
			RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
			rd.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	public static String normalizeText(String text) {
        if (text == null || text.isEmpty()) {
            return text;
        }

        // Chuyển sang chữ thường
        text = text.toLowerCase();

        // Loại bỏ các ký tự đặc biệt nhưng giữ lại các ký tự tiếng Việt, dấu chấm, và dấu chấm than
        text = text.replaceAll("[^a-zA-Z0-9\\sáàảãạăắằẳẵặâấầẩẫậéèẻẽẹêếềểễệíìỉĩịóòỏõọôốồổỗộơớờởỡợúùủũụưứừửữựýỳỷỹỵđĐ.!]", "");

        // Xóa khoảng trắng thừa
        text = text.trim().replaceAll("\\s+", " ");

        // Viết hoa chữ cái đầu câu sau dấu chấm và dấu chấm than
        StringBuilder normalizedText = new StringBuilder(text.length());
        boolean capitalizeNext = true;

        for (int i = 0; i < text.length(); i++) {
            char currentChar = text.charAt(i);
            if (capitalizeNext && Character.isLetter(currentChar)) {
                normalizedText.append(Character.toUpperCase(currentChar));
                capitalizeNext = false;
            } else {
                normalizedText.append(currentChar);
            }
            if (currentChar == '.' || currentChar == '!') {
                capitalizeNext = true;
            }
        }

        return normalizedText.toString();
    }
	
	private void guiMailtoObj(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		HttpSession session = request.getSession();
		KhoaDAO kd = new KhoaDAO();
		NganhDAO nd = new NganhDAO();
		LopDAO ld = new LopDAO();
		SinhVienDAO svd = new SinhVienDAO();
		if (id != null) {
			if (id.equalsIgnoreCase("khoa")) {
				session.setAttribute("dsKhoa", kd.selectAll());
			} else if (id.equalsIgnoreCase("nganh")) {
				session.setAttribute("dsNganh", nd.selectAll());
			} else if (id.equalsIgnoreCase("lop")) {
				session.setAttribute("dsLop", ld.selectAll());
			} else if (id.equalsIgnoreCase("sv")) {
				session.setAttribute("dsSV", svd.selectAll());
			}
		}
		String url = "/announce/guiMailSinhVien.jsp";
		session.setAttribute("id", id);
		RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
		rd.forward(request, response);
	}

	private void guiMail(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    String id = request.getParameter("id");
	    String tieuDe = request.getParameter("tieuDe");
	    String noiDung = request.getParameter("noiDung");
	    String type = "";
	    ArrayList<SinhVien> res = new ArrayList<>();
	    SinhVienDAO svd = new SinhVienDAO();

	    if (id != null) {
	        if (id.equalsIgnoreCase("khoa")) {
	            type = request.getParameter("maKhoa");
	            for (SinhVien sv : svd.selectAll()) {
	                if (sv.getLop().getNganh().getKhoa().getMaKhoa().equalsIgnoreCase(type)) {
	                    res.add(sv);
	                }
	            }
	        } else if (id.equalsIgnoreCase("nganh")) {
	            type = request.getParameter("maNganh");
	            for (SinhVien sv : svd.selectAll()) {
	                if (sv.getLop().getNganh().getMaNganh().equalsIgnoreCase(type)) {
	                    res.add(sv);
	                }
	            }
	        } else if (id.equalsIgnoreCase("lop")) {
	            type = request.getParameter("maLop");
	            for (SinhVien sv : svd.selectAll()) {
	                if (sv.getLop().getMaLop().equalsIgnoreCase(type)) {
	                    res.add(sv);
	                }
	            }
	        } else if (id.equalsIgnoreCase("sv")) {
	            type = request.getParameter("maSinhVien");
	            SinhVien sv = svd.selectByID(type);
	            if (sv != null) {
	                res.add(sv);
	            }
	        }
	    }

	    HttpSession session = request.getSession();
	    String tieuDeNormalized = normalizeText(tieuDe);
	    String noiDungNormalized = normalizeText(noiDung);
	    Email e = new Email();
	    for(SinhVien sv:res) {
	    	System.out.println(sv.getMaSinhVien());
	    }
	    for (SinhVien sv : res) {
	        e.sendEmail(sv.getMaSinhVien()+"@student.ptithcm.edu.vn", tieuDeNormalized, noiDungNormalized);
	    }
	    String message = "Đã gửi thành công!";
	    session.setAttribute("sl", Integer.toString(res.size()));
	    session.setAttribute("message", message);
	    RequestDispatcher rd = getServletContext().getRequestDispatcher("/announce/guiMailSinhVien.jsp");
	    rd.forward(request, response);
	}

	private void layThongTinSua(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    String maThongBao = request.getParameter("maThongBao");
	    ThongBaoDAO tbd=new ThongBaoDAO();
	    LoaiThongBaoDAO ltbd=new LoaiThongBaoDAO();
	    HttpSession session = request.getSession();
	    if(maThongBao!=null) {
	    	 session.setAttribute("tb", tbd.selectByID(maThongBao));
	    }
	    session.setAttribute("maThongBao", maThongBao);
	    session.setAttribute("dsLTB", ltbd.selectAll());
	    RequestDispatcher rd = getServletContext().getRequestDispatcher("/announce/suaThongBao.jsp");
	    rd.forward(request, response);
	}
	
	private void suaThongBao(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String maThongBao = request.getParameter("maThongBao");
		String tieuDe=request.getParameter("tieuDe");
		String noiDung=request.getParameter("noiDung");
		String loaiThongBao=request.getParameter("loaiThongBao");

		
		LoaiThongBaoDAO ltbd=new LoaiThongBaoDAO();
		
		NoiDungThongBao ndtb=new NoiDungThongBao();
		Date now=new Date();
		ndtb.setMaNoiDungThongBao(Integer.parseInt(maThongBao));
		ndtb.setTieuDe(tieuDe);
		ndtb.setNoiDung(noiDung);
		ndtb.setLtb(ltbd.selectByID(loaiThongBao));
		ndtb.setThoiGianThongBao(now);
		ThongBaoDAO tbd = new ThongBaoDAO();
		tbd.update(ndtb);
		String url = "/announce/announceManage.jsp";
		String message="Cập nhật thành công!";
		RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
		rd.forward(request, response);
	}
	
	private void duyet_theo_trang(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String trangStr = request.getParameter("trang");
		int trang = 1; // Mặc định trang đầu tiên
		try {
			trang = Integer.parseInt(trangStr);
		} catch (NumberFormatException e) {
			trang = 1; // Nếu không thể phân tích số trang, quay về trang đầu tiên
		}

		ThongBaoDAO tbd = new ThongBaoDAO();
		ArrayList<NoiDungThongBao> dsNDTB = tbd.selectAll();
		ArrayList<NoiDungThongBao> result = new ArrayList<>();

		int itemsPerPage = 10;
		int totalItems = dsNDTB.size();
		int totalPages = (int) Math.ceil((double) totalItems / itemsPerPage);

		int startIndex = (trang - 1) * itemsPerPage;
		int endIndex = Math.min(startIndex + itemsPerPage, totalItems);

		if (startIndex < totalItems) {
			result.addAll(dsNDTB.subList(startIndex, endIndex));
		}

		HttpSession session = request.getSession();
		session.setAttribute("result", result);
		session.setAttribute("trang", trang);
		session.setAttribute("totalPages", totalPages);
		session.setAttribute("message", result.isEmpty() ? "Không tìm thấy dữ liệu" : null);
		String url = "/announce/thongbao.jsp";
		RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
		rd.forward(request, response);
	}
	
	private void xoaThongBao(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String maThongBao = request.getParameter("maThongBao");
		System.out.print("hello");
		ThongBaoDAO tbd=new ThongBaoDAO();
		tbd.delete(tbd.selectByID(maThongBao));
		String url = "/announce/announceManage.jsp";
		String message="Xóa thành công!";
		HttpSession session = request.getSession();
		session.setAttribute("message", message);
		RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
		rd.forward(request, response);
	}
	
	private void luuThongBao(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String tieuDe = request.getParameter("tieuDe");
		String noiDung = request.getParameter("noiDung");
		String loaiThongBao = request.getParameter("loaiThongBao");
		LoaiThongBaoDAO ltbd=new LoaiThongBaoDAO();
		ThongBaoDAO tbd=new ThongBaoDAO();
		Date now =new Date();
		NoiDungThongBao ndtb = new NoiDungThongBao(tieuDe,noiDung,now,ltbd.selectByID("TBC"));
		tbd.insert(ndtb);
		String url = "/announce/announceManage.jsp";
		String message="Xóa thành công!";
		HttpSession session = request.getSession();
		session.setAttribute("message", message);
		RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
		rd.forward(request, response);
	}
	

}