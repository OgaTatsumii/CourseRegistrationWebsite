package database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import model.CTNamHoc_HocKy;
import model.ChucVu;
import model.GiangVien;
import model.HocKy;
import model.NamHoc;
import model.NhanVien;
import model.CTNamHoc_HocKy;
import database.NamHocDAO;

public class CTNamHoc_HocKyDAO implements DAOInterface<CTNamHoc_HocKy>{

	private ArrayList<CTNamHoc_HocKy> data = new ArrayList<>();
	@Override
	public ArrayList<CTNamHoc_HocKy> selectAll() {
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: tạo ra đối tượng statement
			String sql = "SELECT * FROM CTNamHoc_HocKy";
			PreparedStatement st = con.prepareStatement(sql);

			// Bước 3: thực thi câu lệnh SQL
			System.out.println(sql);
			ResultSet rs = st.executeQuery();

			// Bước 4:
			while (rs.next()) {
				String maNHHocKy = rs.getString("MaNHHocKy");
				String maNamHoc = rs.getString("MaNamHoc");
				NamHocDAO nhDAO = new NamHocDAO();
				NamHoc namHoc    = nhDAO.selectByID(maNamHoc);
				String maHocKy = rs.getString("MaHocKy");
				HocKyDAO hkd = new HocKyDAO();
				HocKy hocky = hkd.selectByID(maHocKy);
				Date tgbd =  rs.getDate("ThoiGianBatDau");
				LocalDate thoiGianBatDAU = tgbd.toLocalDate();
				Date tgkt =  rs.getDate("ThoiGianKetThuc");
				LocalDate thoiGianKetThuc = tgkt.toLocalDate();
				data.add(new CTNamHoc_HocKy(maNHHocKy,namHoc,hocky,thoiGianBatDAU,thoiGianKetThuc)); 
			}

			// Bước 5:
			JDBCUtil.closeConnection(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}

	@Override
	public CTNamHoc_HocKy selectByID(String t) {
		CTNamHoc_HocKy result = new CTNamHoc_HocKy();
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: tạo ra đối tượng statement
			String sql = "SELECT * FROM CTNamHoc_HocKy WHERE MaNHHocKy = ?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t);
			// Bước 3: thực thi câu lệnh SQL
			System.out.println(sql);
			ResultSet rs = st.executeQuery();

			// Bước 4:
			while (rs.next()) {
				String maNHHocKy = rs.getString("MaNHHocKy");
				String maNamHoc = rs.getString("MaNamHoc");
				NamHocDAO nhDAO = new NamHocDAO();
				NamHoc namHoc    = nhDAO.selectByID(maNamHoc);
				String maHocKy = rs.getString("MaHocKy");
				HocKyDAO hkd = new HocKyDAO();
				HocKy hocky = hkd.selectByID(maHocKy);
				Date tgbd =  rs.getDate("ThoiGianBatDau");
				LocalDate thoiGianBatDAU = tgbd.toLocalDate();
				Date tgkt =  rs.getDate("ThoiGianKetThuc");
				LocalDate thoiGianKetThuc = tgkt.toLocalDate();
				result = new CTNamHoc_HocKy(maNHHocKy,namHoc,hocky,thoiGianBatDAU,thoiGianKetThuc);
			}

			// Bước 5:
			JDBCUtil.closeConnection(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public CTNamHoc_HocKy selectByNHHK(String maNamHocHK, String maHocKyIP) {
		CTNamHoc_HocKy result = new CTNamHoc_HocKy();
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: tạo ra đối tượng statement
			String sql = "SELECT * FROM CTNamHoc_HocKy WHERE MaNamHoc = ? AND MaHocKy = ?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, maNamHocHK);
			st.setString(2, maHocKyIP);
			// Bước 3: thực thi câu lệnh SQL
			System.out.println(sql);
			ResultSet rs = st.executeQuery();

			// Bước 4:
			while (rs.next()) {
				String maNHHocKy = rs.getString("MaNHHocKy");
				String maNamHoc = rs.getString("MaNamHoc");
				NamHocDAO nhDAO = new NamHocDAO();
				NamHoc namHoc    = nhDAO.selectByID(maNamHoc);
				String maHocKy = rs.getString("MaHocKy");
				HocKyDAO hkd = new HocKyDAO();
				HocKy hocky = hkd.selectByID(maHocKy);
				Date tgbd =  rs.getDate("ThoiGianBatDau");
				LocalDate thoiGianBatDAU = tgbd.toLocalDate();
				Date tgkt =  rs.getDate("ThoiGianKetThuc");
				LocalDate thoiGianKetThuc = tgkt.toLocalDate();
				result = new CTNamHoc_HocKy(maNHHocKy,namHoc,hocky,thoiGianBatDAU,thoiGianKetThuc);
			}

			// Bước 5:
			JDBCUtil.closeConnection(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int insert(CTNamHoc_HocKy t) {
		int ketQua = 0;
		if (this.selectByID(t.getmaNHHocKy()) != null) {
			try {
				// Bước 1: tạo kết nối đến CSDL
				Connection con = JDBCUtil.getConnection();

				// Bước 2: tạo ra đối tượng statement
				String sql = "INSERT INTO CTNamHoc_HocKy (MaNHHocKy, MaNamHoc, MaHocKy, ThoiGianBatDau , ThoiGianKetThuc)"
						+ " VALUES (?,?,?,?,?)";

				PreparedStatement st = con.prepareStatement(sql);
				st.setString(1, t.getmaNHHocKy());
				st.setString(2, t.getnamHoc().getMaNamHoc());
				st.setString(3, t.gethocKy().getMaHocKy());
				st.setDate(4, Date.valueOf(t.getThoiGianBatDau()));
				st.setDate(5, Date.valueOf(t.getThoiGianKetThuc()));

				// Bước 3: thực thi câu lệnh SQL
				ketQua = st.executeUpdate();

				// Bước 4:
				System.out.println("Bạn đã thực thi: " + sql);
				System.out.println("Có " + ketQua + " dòng bị thay đổi!");

				// Bước 5:
				JDBCUtil.closeConnection(con);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return ketQua;
	}


	@Override
	public int insertAll(ArrayList<CTNamHoc_HocKy> arr) {
		int dem = 0;
		for (CTNamHoc_HocKy CTNamHoc_HocKy : arr) {
			dem += insert(CTNamHoc_HocKy);
		}
		return dem;
	}

	@Override
	public int delete(CTNamHoc_HocKy t) {
		int ketQua = 0;
		if (this.selectByID(t.getmaNHHocKy()) != null) {
			try {
				// Bước 1: tạo kết nối đến CSDL
				Connection con = JDBCUtil.getConnection();

				// Bước 2: tạo ra đối tượng statement
				String sql = "DELETE from ctnamhoc_hocky " + " WHERE MaNHHocKy=?";

				PreparedStatement st = con.prepareStatement(sql);
				st.setString(1, t.getmaNHHocKy());
				ketQua = st.executeUpdate();
				System.out.println(sql);
				

				// Bước 4:
				System.out.println("Bạn đã thực thi: " + sql);
				System.out.println("Có " + ketQua + " dòng bị thay đổi!");

				// Bước 5:
				JDBCUtil.closeConnection(con);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return ketQua;
	}
	@Override
	public int deleteMany(ArrayList<CTNamHoc_HocKy> arr) {
		int dem = 0;
		for (CTNamHoc_HocKy CTNamHoc_HocKy : arr) {
			dem += delete(CTNamHoc_HocKy);
		}
		return dem;
	}

	@Override
	public void deleteAll() {
		int ketQua = 0;
			try {
				// Bước 1: tạo kết nối đến CSDL
				Connection con = JDBCUtil.getConnection();

				// Bước 2: tạo ra đối tượng statement
				String sql = "DELETE from CTNamHoc_HocKy";

				PreparedStatement st = con.prepareStatement(sql);
				System.out.println(sql);
				ketQua = st.executeUpdate();

				// Bước 4:
				System.out.println("Bạn đã thực thi: " + sql);
				System.out.println("Có " + ketQua + " dòng bị thay đổi!");

				// Bước 5:
				JDBCUtil.closeConnection(con);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	@Override
	public int update(CTNamHoc_HocKy t) {
		int ketQua = 0;
		if (this.selectByID(t.getmaNHHocKy()) != null) {
			try {
				// Bước 1: tạo kết nối đến CSDL
				Connection con = JDBCUtil.getConnection();

				// Bước 2: tạo ra đối tượng statement
				String sql = "UPDATE CTNamHoc_HocKy SET MaNHHocKy=?, MaNamHoc=?, MaHocKy=?, ThoiGianBatDau=? , ThoiGianKetThuc=?";

				PreparedStatement st = con.prepareStatement(sql);
				st.setString(1, t.getmaNHHocKy());
				st.setString(2, t.getnamHoc().getMaNamHoc());
				st.setString(3, t.gethocKy().getMaHocKy());
				st.setDate(4, Date.valueOf(t.getThoiGianBatDau()));
				st.setDate(5, Date.valueOf(t.getThoiGianKetThuc()));

				// Bước 3: thực thi câu lệnh SQL
				ketQua = st.executeUpdate();

				// Bước 4:
				System.out.println("Bạn đã thực thi: " + sql);
				System.out.println("Có " + ketQua + " dòng bị thay đổi!");

				// Bước 5:
				JDBCUtil.closeConnection(con);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return ketQua;
	}
	public ArrayList<CTNamHoc_HocKy> search(String ma,String nh,String hk) {
		ArrayList<CTNamHoc_HocKy> data = new ArrayList<>();
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: tạo ra đối tượng statement
			String sql = "SELECT * FROM CTNamHoc_HocKy WHERE 1=1";
			if (ma != null && !ma.isEmpty()) {
				sql += " AND MaNHHocKy LIKE '" + ma +"%"+ "'";
	        }
			if (nh != null && !nh.isEmpty()) {
				sql += " AND MaNamHoc = '"+ nh + "'";
	        }
			if (hk != null && !hk.isEmpty()) {
				sql += " AND MaHocKy = '" + hk + "'";
	        }
			PreparedStatement st = con.prepareStatement(sql);

			// Bước 3: thực thi câu lệnh SQL
			System.out.println(sql);
			ResultSet rs = st.executeQuery();

			// Bước 4:
			while (rs.next()) {
				String maNHHocKy = rs.getString("MaNHHocKy");
				String maNamHoc = rs.getString("MaNamHoc");
				NamHocDAO nhDAO = new NamHocDAO();
				NamHoc namHoc    = nhDAO.selectByID(maNamHoc);
				String maHocKy = rs.getString("MaHocKy");
				HocKyDAO hkd = new HocKyDAO();
				HocKy hocky = hkd.selectByID(maHocKy);
				Date tgbd =  rs.getDate("ThoiGianBatDau");
				LocalDate thoiGianBatDAU = tgbd.toLocalDate();
				Date tgkt =  rs.getDate("ThoiGianKetThuc");
				LocalDate thoiGianKetThuc = tgkt.toLocalDate();
				data.add(new CTNamHoc_HocKy(maNHHocKy,namHoc,hocky,thoiGianBatDAU,thoiGianKetThuc)); 
			}
			// Bước 5:
			JDBCUtil.closeConnection(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}
	public static void main(String[] args) {
		CTNamHoc_HocKyDAO d= new CTNamHoc_HocKyDAO();
		CTNamHoc_HocKy t= new CTNamHoc_HocKy("342332",null,null,null,null);
		System.out.print(d.delete(t));
	}
}
