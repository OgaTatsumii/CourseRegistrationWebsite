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
import model.Diem;
import model.SinhVien;

/**
 * Servlet implementation class ThongTinCaNhan
 */
@WebServlet("/ThongTinCaNhan")
public class ThongTinCaNhan extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ThongTinCaNhan() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DiemDAO diemDAO = new DiemDAO();
		HttpSession session = request.getSession();
		SinhVien sinhVien = (SinhVien)session.getAttribute("sinhVien");
		Map<String, String> dsHK_Diem = new HashMap<>();
		int soTinChiTL = 0 ;
		for(int i=1;i<=9;i++) {
			String hk = "HK" + String.valueOf(i);
			ArrayList<Diem> danhSachDiem = diemDAO.selectHK_MSV(hk, sinhVien.getMaSinhVien());
			if (danhSachDiem.size()==0){
				continue;
			}
			float TBHK = 0;
			int dem = 0;
			for(int j=0;j < danhSachDiem.size(); j++) {
				Diem diem = danhSachDiem.get(j);
				if(diem.getDiem()!= -1.0){TBHK += diem.getDiem();}
				if(diem.getDiem()!= -1.0){soTinChiTL += diem.getMonHoc().getSoTinChi();}
				if(diem.getDiem()!= -1.0){ dem++;}
			}
			dsHK_Diem.put(hk, String.format("%.01f",TBHK/dem));
			
		}
		String json = "";
		for(String key : dsHK_Diem.keySet()){
			json +=dsHK_Diem.get(key) +",";
		}
		json = json.substring(0, json.length()-1);
		session.setAttribute("json",json);
		session.setAttribute("soTinChiTL",soTinChiTL);
		session.setAttribute("tongTinChi", sinhVien.getLop().getNganh().getSoTinChi());
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/thongtinsinhvien.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
