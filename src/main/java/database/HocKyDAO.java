package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.cj.protocol.Resultset;

import model.HocKy;

public class HocKyDAO implements DAOInterface<HocKy> {

	private ArrayList<HocKy> data = new ArrayList<>();

	@Override
	public ArrayList<HocKy> selectAll() {
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: tạo ra đối tượng statement
			String sql = "SELECT * FROM HocKy";
			PreparedStatement st = con.prepareStatement(sql);

			// Bước 3: thực thi câu lệnh SQL
			System.out.println(sql);
			ResultSet rs = st.executeQuery();

			// Bước 4:
			while (rs.next()) {
				String MaHocKy = rs.getString("MaHocKy");
				String TenHocKy = rs.getString("TenHocKy");
				HocKy HocKy = new HocKy(MaHocKy, TenHocKy);
				data.add(HocKy);
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
	public HocKy selectByID(String t) {
		HocKy result = null;
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: tạo ra đối tượng statement
			String sql = "SELECT * FROM HocKy WHERE MaHocKy = ?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t);

			// Bước 3: thực thi câu lệnh SQL
			System.out.println(sql);
			ResultSet rs = st.executeQuery();

			// Bước 4:
			while (rs.next()) {
				String MaHocKy = rs.getString("MaHocKy");
				String TenHocKy = rs.getString("TenHocKy");
				result = new HocKy(MaHocKy, TenHocKy);
				break;
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
	public int insert(HocKy t) {
		int ketQua = 0;
		if (this.selectByID(t.getMaHocKy()) == null) {
			try {
				// Bước 1: tạo kết nối đến CSDL
				Connection con = JDBCUtil.getConnection();

				// Bước 2: tạo ra đối tượng statement
				String sql = "INSERT INTO HocKy (MaHocKy, TenHocKy) " + " VALUES (?,?)";

				PreparedStatement st = con.prepareStatement(sql);
				st.setString(1, t.getMaHocKy());
				st.setString(2, t.getTenHocKy());

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
	public int insertAll(ArrayList<HocKy> arr) {
		int dem = 0;
		for (HocKy HocKy : arr) {
			dem += insert(HocKy);
		}
		return dem;
	}

	@Override
	public int delete(HocKy t) {
		int ketQua = 0;
		if (this.selectByID(t.getMaHocKy()) != null) {
			try {
				// Bước 1: tạo kết nối đến CSDL
				Connection con = JDBCUtil.getConnection();

				// Bước 2: tạo ra đối tượng statement
				String sql = "DELETE from HocKy " + " WHERE MaHocKy=?";

				PreparedStatement st = con.prepareStatement(sql);
				st.setString(1, t.getMaHocKy());

				// Bước 3: thực thi câu lệnh SQL
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
		return ketQua;
	}

	@Override
	public int deleteMany(ArrayList<HocKy> arr) {
		int dem = 0;
		for (HocKy HocKy : arr) {
			dem += delete(HocKy);
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
			String sql = "DELETE FROM HocKy";

			PreparedStatement st = con.prepareStatement(sql);

			// Bước 3: thực thi câu lệnh SQL
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
	public int update(HocKy t) {
		int ketQua = 0;
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();
			
			// Bước 2: tạo ra đối tượng statement
			String sql = "UPDATE HocKy "+
					 " SET " +
					 " MaHocKy=?"+
					 ", TenHocKy=?"+
					 " WHERE MaHocKy=?";
			
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t.getMaHocKy());
			st.setString(2, t.getTenHocKy());
			st.setString(3, t.getMaHocKy());
			// Bước 3: thực thi câu lệnh SQL

			System.out.println(sql);
			ketQua = st.executeUpdate();
			
			// Bước 4:
			System.out.println("Bạn đã thực thi: "+ sql);
			System.out.println("Có "+ ketQua+" dòng bị thay đổi!");
			
			// Bước 5:
			JDBCUtil.closeConnection(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ketQua;
	}

	public ArrayList<HocKy> selectByCurriculum(String CurriculumId) {
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: tạo ra đối tượng statement
			String sql = "SELECT h.MaHocKy, h.TenHocKy "
					+ "FROM nganh_hocky nh "
					+ "INNER JOIN nganh n ON nh.MaNganh = n.MaNganh "
					+ "INNER JOIN hocky h ON nh.MaHocKy = h.MaHocKy "
					+ "WHERE nh.MaNganh = ? "
					+ "ORDER BY nh.MaNganh, nh.MaHocKy;";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, CurriculumId);
			// Bước 3: thực thi câu lệnh SQL
			System.out.println(sql);
			ResultSet rs = st.executeQuery();

			// Bước 4:
			while (rs.next()) {
				String MaHocKy = rs.getString("MaHocKy");
				String TenHocKy = rs.getString("TenHocKy");
				HocKy HocKy = new HocKy(MaHocKy, TenHocKy);
				data.add(HocKy);
			}

			// Bước 5:
			JDBCUtil.closeConnection(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}
	
}
