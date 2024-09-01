package database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import com.mysql.cj.protocol.Resultset;

import model.PhienDangKy;
import model.QueQuan;
import model.SinhVien;
import model.TaiKhoan;
import model.CTNamHoc_HocKy;
import model.Khoa;
import model.NhanVien;
import database.KhoaDAO;

public class PhienDangKyDAO implements DAOInterface<PhienDangKy> {

	private ArrayList<PhienDangKy> data = new ArrayList<>();

	@Override
	public ArrayList<PhienDangKy> selectAll() {
		ArrayList<PhienDangKy> data = new ArrayList<>();
		try {
			Connection con = JDBCUtil.getConnection();
			String sql = "SELECT * FROM PhienDangKy";
			PreparedStatement st = con.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				String maPhien = rs.getString("MaPhien");
				String maKhoa = rs.getString("MaKhoa");
				String tgbd = rs.getString("ThoiGianBatDau");
				String tgkt = rs.getString("ThoiGianKetThuc");
				String maNHHocKy = rs.getString("MaNHHocKy");

				Khoa khoa = (new KhoaDAO().selectByID(maKhoa));
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
				LocalDateTime thoiGianBatDau = LocalDateTime.parse(tgbd, formatter);
				LocalDateTime thoiGianKetThuc = LocalDateTime.parse(tgkt, formatter);
				
				CTNamHoc_HocKyDAO ctnhhkd=new CTNamHoc_HocKyDAO();
				CTNamHoc_HocKy ctnhhk =ctnhhkd.selectByID(maNHHocKy);
				
				PhienDangKy pdk = new PhienDangKy(maPhien, khoa, thoiGianBatDau, thoiGianKetThuc,ctnhhk);
				data.add(pdk);
			}

			JDBCUtil.closeConnection(con);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return data;
	}

	@Override
	public PhienDangKy selectByID(String t) {
		// TODO Auto-generated method stub
		PhienDangKy result=null;
		try {
			Connection con = JDBCUtil.getConnection();
			String sql = "SELECT * FROM PhienDangKy WHERE MaPhien = ?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t);
			System.out.println(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				String maPhien = rs.getString("MaPhien");
				String maKhoa = rs.getString("MaKhoa");
				String tgbd = rs.getString("ThoiGianBatDau");
				String tgkt = rs.getString("ThoiGianKetThuc");
				String maNHHocKy = rs.getString("MaNHHocKy");

				Khoa khoa = (new KhoaDAO().selectByID(maKhoa));
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
				LocalDateTime thoiGianBatDau = LocalDateTime.parse(tgbd, formatter);
				LocalDateTime thoiGianKetThuc = LocalDateTime.parse(tgkt, formatter);
				
				CTNamHoc_HocKyDAO ctnhhkd=new CTNamHoc_HocKyDAO();
				CTNamHoc_HocKy ctnhhk =ctnhhkd.selectByID(maNHHocKy);
				
				PhienDangKy pdk = new PhienDangKy(maPhien, khoa, thoiGianBatDau, thoiGianKetThuc,ctnhhk);
				result=pdk;
				break;
			}

			JDBCUtil.closeConnection(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int insert(PhienDangKy t) {
		// TODO Auto-generated method stub
		int ketQua = 0;
      	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		if (this.selectByID(t.getMaPhien()) == null) {
			try {
				Connection con = JDBCUtil.getConnection();
				String sql = "INSERT INTO PhienDangKy (MaPhien, MaKhoa, ThoiGianBatDau, ThoiGianKetThuc, MaNHHocKy)"
						+ " VALUES (?,?,?,?,?)";

				PreparedStatement st = con.prepareStatement(sql);
				st.setString(1, t.getMaPhien());
				st.setString(2, t.getKhoa().getMaKhoa());
				st.setString(3, t.getThoiGianBatDau().format(formatter));
				st.setString(4, t.getThoiGianKetThuc().format(formatter));
				st.setString(5, t.getCtNamHocHocKy().getmaNHHocKy());
				ketQua = st.executeUpdate();

				System.out.println("Bạn đã thực thi: " + sql);
				System.out.println("Có " + ketQua + " dòng bị thay đổi!");
				JDBCUtil.closeConnection(con);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return ketQua;
	}

	@Override
	public int insertAll(ArrayList<PhienDangKy> arr) {
		// TODO Auto-generated method stub
		int dem = 0;
		for (PhienDangKy PhienDangKy : arr) {
			dem += insert(PhienDangKy);
		}
		return dem;
	}

	@Override
	public int delete(PhienDangKy t) {
		// TODO Auto-generated method stub
		int ketQua = 0;
		if (this.selectByID(t.getMaPhien()) != null) {
			try {
				Connection con = JDBCUtil.getConnection();
				String sql = "DELETE from PhienDangKy " + " WHERE MaPhien=?";

				PreparedStatement st = con.prepareStatement(sql);
				st.setString(1, t.getMaPhien());

				System.out.println(sql);
				ketQua = st.executeUpdate();

				System.out.println("Bạn đã thực thi: " + sql);
				System.out.println("Có " + ketQua + " dòng bị thay đổi!");

				JDBCUtil.closeConnection(con);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return ketQua;
	}

	@Override
	public int deleteMany(ArrayList<PhienDangKy> arr) {
		// TODO Auto-generated method stub
		int dem = 0;
		for (PhienDangKy PhienDangKy : arr) {
			dem += delete(PhienDangKy);
		}
		return dem;
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		int ketQua = 0;
		try {
			Connection con = JDBCUtil.getConnection();

			String sql = "DELETE FROM PhienDangKy";

			PreparedStatement st = con.prepareStatement(sql);

			System.out.println(sql);
			ketQua = st.executeUpdate();

			System.out.println("Bạn đã thực thi: " + sql);
			System.out.println("Có " + ketQua + " dòng bị thay đổi!");

			JDBCUtil.closeConnection(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public int update(PhienDangKy t) {
		// TODO Auto-generated method stub
		int ketQua = 0;
      	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		try {
			Connection con = JDBCUtil.getConnection();

			String sql = "UPDATE PhienDangKy SET MaKhoa =?, ThoiGianBatDau = ?, ThoiGianKetThuc = ?, MaNHHocKy=? WHERE MaPhien = ?";


			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t.getKhoa().getMaKhoa());
			st.setString(2, t.getThoiGianBatDau().format(formatter));
			st.setString(3, t.getThoiGianKetThuc().format(formatter));
			st.setString(4, t.getCtNamHocHocKy().getmaNHHocKy());
			st.setString(5, t.getMaPhien());
			
			ketQua = st.executeUpdate();

			System.out.println("Bạn đã thực thi: " + sql);
			System.out.println("Có " + ketQua + " dòng bị thay đổi!");

			JDBCUtil.closeConnection(con);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ketQua;
	}
	
	public static void main(String[] args) {
//		PhienDangKyDAO pdkd = new PhienDangKyDAO();
//		// System.out.print(pdkd.selectAll());
//		// System.out.print(pdkd.selectByID("PDK0001"));
//		// -------------------------
//
//		KhoaDAO kd = new KhoaDAO();
//		Khoa k = kd.selectByID("CBII");
//		String a = "2024-12-12 00:30:00";
//		String b = "2024-12-15 00:30:00";
//		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//		LocalDateTime tg1 = LocalDateTime.parse(a, formatter);
//		LocalDateTime tg2 = LocalDateTime.parse(b, formatter);
//		PhienDangKy pdk = new PhienDangKy("PDK0005", k, tg1, tg2); 
//		System.out.print(tg1);
//		System.out.print(pdkd.insert(pdk));
//		// System.out.print(pdkd.delete(pdk)); System.out.print(pdkd.update(pdk));
//
//		// ---------------------------

	}

}
