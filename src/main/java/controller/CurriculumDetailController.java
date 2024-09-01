package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Collections;
import java.util.Comparator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.CTMonHocDAO;
import database.HocKyDAO;
import database.MonHocDAO;
import database.NganhDAO;
import database.Nganh_HKDAO;
import model.CTMonHoc;
import model.HocKy;
import model.MonHoc;
import model.Nganh;
import model.Nganh_HocKy;

@WebServlet("/updateCurriculum")
public class CurriculumDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CurriculumDetailController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String url = "/curriculum/curriculumDetail.jsp";
		String title = "Quản lý chương trình học";
		request.setAttribute("title", title);
		String nganhId = request.getParameter("CurriculumId");
		NganhDAO nganhDAO = new NganhDAO();
		Nganh nganh = nganhDAO.selectByID(nganhId);
		String nganhTitle = nganh.getTenNganh();
		String maNganh = nganh.getMaNganh();
		HocKyDAO hocKyDAO = new HocKyDAO();
		ArrayList<HocKy> curriculumList = hocKyDAO.selectByCurriculum(nganhId);
		HashMap<String, ArrayList<MonHoc>> hocKyMonHocMap = new HashMap<>();
		for (HocKy hocKy : curriculumList) {
			String maHocKy = hocKy.getMaHocKy();
			MonHocDAO monHocDAO = new MonHocDAO();
			ArrayList<MonHoc> monHocList = monHocDAO.selectByNganhHocKy(nganhId, maHocKy);
			hocKyMonHocMap.put(maHocKy, monHocList);
		}

		// Sắp xếp HashMap theo thứ tự của các học kỳ từ HK1 đến HK9
		List<Map.Entry<String, ArrayList<MonHoc>>> list = new ArrayList<>(hocKyMonHocMap.entrySet());
		Collections.sort(list, new Comparator<Map.Entry<String, ArrayList<MonHoc>>>() {
			public int compare(Map.Entry<String, ArrayList<MonHoc>> o1, Map.Entry<String, ArrayList<MonHoc>> o2) {
				int hk1 = Integer.parseInt(o1.getKey().substring(2));
				int hk2 = Integer.parseInt(o2.getKey().substring(2));
				return Integer.compare(hk1, hk2);
			}
		});
		HashMap<String, ArrayList<MonHoc>> sortedHocKyMonHocMap = new LinkedHashMap<>();
		for (Map.Entry<String, ArrayList<MonHoc>> entry : list) {
			sortedHocKyMonHocMap.put(entry.getKey(), entry.getValue());
		}
		MonHocDAO monHocDAO = new MonHocDAO();
		ArrayList<MonHoc> monHocListAll = monHocDAO.selectAll();
		request.setAttribute("nganhTitle", nganhTitle);
		request.setAttribute("hocKyMonHocMap", sortedHocKyMonHocMap);
		request.setAttribute("monHocListAll", monHocListAll);
		request.setAttribute("maNganh", maNganh);
		RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
		rd.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		if (action != null) {
			if (action.equals("addSubjectForSemester")) {
				addSubjectForSemester(request, response);
			} else if (action.equals("deleteSubjectForSemester")) {
				System.out.println("Hello");
				deleteSubjectForSemester(request, response);
			}
		}
	}

	protected void addSubjectForSemester(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String maNganh = request.getParameter("maNganh");
		String maHocKy = request.getParameter("maHocKyInput");
		Nganh_HKDAO nganh_HKDAO = new Nganh_HKDAO();
		String maNganhHocKy = nganh_HKDAO.selectByNganhHocKy(maNganh, maHocKy);
		String[] monHocIDList = request.getParameterValues("selectedCourse");
		MonHocDAO monHocDAO = new MonHocDAO();
		ArrayList<MonHoc> monHocList = monHocDAO.selectMany(monHocIDList);
		ArrayList<CTMonHoc> danhSachCTMonHoc = new ArrayList<>();
		for (MonHoc monHoc : monHocList) {
			Nganh nganh = new Nganh(maNganh, null, 0, 0, 0, null);
			HocKy hocKy = new HocKy(maHocKy, null);
			Nganh_HocKy nganhHocKy = new Nganh_HocKy(maNganhHocKy, nganh, hocKy);
			CTMonHoc ctMonHoc = new CTMonHoc(monHoc, nganhHocKy);
			danhSachCTMonHoc.add(ctMonHoc);
		}
		CTMonHocDAO ctMonHocDAO = new CTMonHocDAO();
		int result = 0;
		result = ctMonHocDAO.insertAll(danhSachCTMonHoc);
		if (result != 0) {
			HttpSession session = request.getSession();
			session.setAttribute("titleToast", "Thành công");
			session.setAttribute("messageToast", "Thêm môn học thành công !");
			session.setAttribute("type", "success");
			session.setAttribute("icon", "bxs-check-circle");
			response.sendRedirect(request.getContextPath() + "/updateCurriculum?CurriculumId=" + maNganh);

		} else {

			HttpSession session = request.getSession();
			session.setAttribute("titleToast", "Thất bại");
			session.setAttribute("messageToast", "Thêm môn học thất bại !");
			session.setAttribute("type", "error");
			session.setAttribute("icon", "bxs-error");
			response.sendRedirect(request.getContextPath() + "/updateCurriculum?CurriculumId=" + maNganh);
		}
		System.out.println(maNganhHocKy);
	}

	protected void deleteSubjectForSemester(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String maNganh = request.getParameter("maNganh");
		System.out.println(maNganh);
		String maHocKy = request.getParameter("maHocKyInput");
		System.out.println(maHocKy);
		String maMon = request.getParameter("Id");
		Nganh_HKDAO nganh_HKDAO = new Nganh_HKDAO();
		String maNganhHocKy = nganh_HKDAO.selectByNganhHocKy(maNganh, maHocKy);
	    System.out.println(maNganhHocKy);
		CTMonHocDAO ctMonHocDAO = new CTMonHocDAO();
		int result = 0;
		result = ctMonHocDAO.deleteByNganhHk(maNganhHocKy,maMon);
		if (result != 0) {
			HttpSession session = request.getSession();
			session.setAttribute("titleToast", "Thành công");
			session.setAttribute("messageToast", "Xóa môn học thành công !");
			session.setAttribute("type", "success");
			session.setAttribute("icon", "bxs-check-circle");
			response.sendRedirect(request.getContextPath() + "/updateCurriculum?CurriculumId=" + maNganh);

		} else {

			HttpSession session = request.getSession();
			session.setAttribute("titleToast", "Thất bại");
			session.setAttribute("messageToast", "Xóa môn học thất bại !");
			session.setAttribute("type", "error");
			session.setAttribute("icon", "bxs-error");
			response.sendRedirect(request.getContextPath() + "/updateCurriculum?CurriculumId=" + maNganh);
		}
	}

}
