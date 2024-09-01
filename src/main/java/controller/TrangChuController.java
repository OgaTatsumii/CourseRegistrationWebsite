package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.KhoaHocDAO;
import database.LoaiThongBaoDAO;
import database.LopDAO;
import database.NganhDAO;
import database.ThongBaoDAO;
import model.KhoaHoc;
import model.LoaiThongBao;
import model.Lop;
import model.Nganh;
import model.NoiDungThongBao;

/**
 * Servlet implementation class TrangChuController
 */
@WebServlet("/trangChu")
public class TrangChuController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TrangChuController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		if (action != null) {
			if (action.equals("duyetPhanTrang")) {
				duyet_theo_loai_va_theo_trang(request, response);
			} else if (action.equals("")) {
			} else if (action.equals("")) {
			} else if (action.equals("")) {
			} else if (action.equals("")) {
			} else if (action.equals("")) {
			} else if (action.equals("")) {
			} else if (action.equals("")) {
			}
		} else {
			String url = "/homepage/trangChu.jsp";
			ThongBaoDAO thongBaoDAO = new ThongBaoDAO();
			LoaiThongBaoDAO loaiThongBaoDAO = new LoaiThongBaoDAO();
			HttpSession session = request.getSession();
			session = request.getSession();
			session.setAttribute("dsNDTB", thongBaoDAO.selectAll());
			session.setAttribute("dsLTB", loaiThongBaoDAO.selectAll());
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

	private void duyet_theo_loai_va_theo_trang(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String loai_thong_bao = request.getParameter("loaiThongBao");
		String trang = request.getParameter("trang");
		int trang_int = Integer.parseInt(trang);

		ThongBaoDAO tbd = new ThongBaoDAO();
		ArrayList<NoiDungThongBao> dsNDTB = tbd.selectByType(loai_thong_bao);
		ArrayList<NoiDungThongBao> result = new ArrayList<NoiDungThongBao>();
		String message="Không tìm thấy dữ liệu";
		if(trang_int==0) {
			HttpSession s = request.getSession();
			s.setAttribute("trang", "1");
			s.setAttribute("loaiThongBao", loai_thong_bao);
			String url = "/homepage/thongbao.jsp";
			RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
			rd.forward(request, response);
		}
		
		if (trang_int == 1) {
			for (int i = 0; i < Math.min(7, dsNDTB.size()); i++) {
				result.add(dsNDTB.get(i));
			}
		} else {
			for (int i = (trang_int - 1) * 7; i < Math.min(trang_int * 7, dsNDTB.size()); i++) {
				result.add(dsNDTB.get(i));
			}
		}
		int totalPage = (int) Math.ceil(dsNDTB.size() / 7.0)+1;
		if (result.isEmpty()) {
			HttpSession s = request.getSession();
			s.setAttribute("result", result);
			s.setAttribute("trang", Integer.toString(totalPage));
			s.setAttribute("message", message);
			s.setAttribute("loaiThongBao", loai_thong_bao);
			String url = "/homepage/thongbao.jsp";
			RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
			rd.forward(request, response);
		}else {
			HttpSession s = request.getSession();
			s.setAttribute("result", result);
			s.setAttribute("trang", trang);
			s.setAttribute("loaiThongBao", loai_thong_bao);
			s.setAttribute("message", null);
			String url = "/homepage/thongbao.jsp";
			RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
			rd.forward(request, response);
		}
		

	}

}
