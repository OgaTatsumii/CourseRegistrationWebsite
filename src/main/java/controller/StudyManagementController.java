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

import database.DiemDAO;
import model.Diem;
import model.SinhVien;

/**
 * Servlet implementation class StudyManagementController
 */
@WebServlet("/ketquahoctap")
public class StudyManagementController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudyManagementController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		SinhVien sinhvien = (SinhVien) session.getAttribute("sinhVien");
		String url = "/ketquahoctap.jsp";
		String title = "";
		
		DiemDAO diemDAO = new DiemDAO();
		ArrayList<Diem> dsDiem = diemDAO.selectHK_MSV(null, sinhvien.getMaSinhVien());
		float DiemTBTL = 0;
		int soTinChiTL = 0;
		int dem=0;
		for (int j=0; j < dsDiem.size(); j++) {
			Diem diem = dsDiem.get(j);
			if(diem.getDiem()!= -1.0){DiemTBTL += diem.getDiem();}
			if(diem.getDiem()!= -1.0){soTinChiTL += diem.getMonHoc().getSoTinChi();}
			if(diem.getDiem()!= -1.0){dem+=1;}
		}
			DiemTBTL = DiemTBTL/dem;
			float DiemTBTL4 = (float) (DiemTBTL / 2.5) ;
		session.setAttribute("dsDiem", dsDiem);
		session.setAttribute("DiemTBTL", String.format("%.02f", DiemTBTL));
		session.setAttribute("DiemTBTL4", String.format("%.02f", DiemTBTL4));
		session.setAttribute("soTinChiTL", soTinChiTL);
		RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
