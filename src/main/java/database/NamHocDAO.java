package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.cj.protocol.Resultset;

import model.NamHoc;

public class NamHocDAO implements DAOInterface<NamHoc> {

	private ArrayList<NamHoc> data = new ArrayList<>();

	@Override
	public ArrayList<NamHoc> selectAll() {
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: tạo ra đối tượng statement
			String sql = "SELECT * FROM NamHoc";
			PreparedStatement st = con.prepareStatement(sql);

			// Bước 3: thực thi câu lệnh SQL
			System.out.println(sql);
			ResultSet rs = st.executeQuery();

			// Bước 4:
			while (rs.next()) {
				String MaNamHoc = rs.getString("MaNamHoc");
				String TenNamHoc = rs.getString("TenNamHoc");
				NamHoc NamHoc = new NamHoc(MaNamHoc, TenNamHoc);
				data.add(NamHoc);
			}

			// Bước 5:
			JDBCUtil.closeConnection(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}

	public ArrayList<NamHoc> selectByLop(String maLopIP) {
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: tạo ra đối tượng statement
			String sql = "SELECT nh.* " +
		             "FROM lop l " +
		             "JOIN khoahoc kh ON l.MaKhoaHoc = kh.MaNamHoc " +
		             "JOIN namhoc nh ON YEAR(kh.NgayBatDau) <= SUBSTRING_INDEX(nh.TenNamHoc, '-', 1) " +
		             "AND YEAR(l.ThoiGianKetThuc) >= SUBSTRING_INDEX(nh.TenNamHoc, '-', -1) " +
		             "WHERE l.MaLop = ?";

			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, maLopIP);
			// Bước 3: thực thi câu lệnh SQL
			System.out.println(sql);
			ResultSet rs = st.executeQuery();

			// Bước 4:
			while (rs.next()) {
				String MaNamHoc = rs.getString("MaNamHoc");
				String TenNamHoc = rs.getString("TenNamHoc");
				NamHoc NamHoc = new NamHoc(MaNamHoc, TenNamHoc);
				data.add(NamHoc);
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
	public NamHoc selectByID(String t) {
		NamHoc result = null;
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: tạo ra đối tượng statement
			String sql = "SELECT * FROM NamHoc WHERE MaNamHoc = ?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t);

			// Bước 3: thực thi câu lệnh SQL
			System.out.println(sql);
			ResultSet rs = st.executeQuery();

			// Bước 4:
			while (rs.next()) {
				String MaNamHoc = rs.getString("MaNamHoc");
				String TenNamHoc = rs.getString("TenNamHoc");
				result = new NamHoc(MaNamHoc, TenNamHoc);
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
	public int insert(NamHoc t) {
		int ketQua = 0;
		if (this.selectByID(t.getMaNamHoc()) == null) {
			try {
				// Bước 1: tạo kết nối đến CSDL
				Connection con = JDBCUtil.getConnection();

				// Bước 2: tạo ra đối tượng statement
				String sql = "INSERT INTO NamHoc (MaNamHoc, TenNamHoc) " + " VALUES (?,?)";

				PreparedStatement st = con.prepareStatement(sql);
				st.setString(1, t.getMaNamHoc());
				st.setString(2, t.getTenNamHoc());

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
	public int insertAll(ArrayList<NamHoc> arr) {
		int dem = 0;
		for (NamHoc NamHoc : arr) {
			dem += insert(NamHoc);
		}
		return dem;
	}

	@Override
	public int delete(NamHoc t) {
		int ketQua = 0;
		if (this.selectByID(t.getMaNamHoc()) != null) {
			try {
				// Bước 1: tạo kết nối đến CSDL
				Connection con = JDBCUtil.getConnection();

				// Bước 2: tạo ra đối tượng statement
				String sql = "DELETE from NamHoc " + " WHERE MaNamHoc=?";

				PreparedStatement st = con.prepareStatement(sql);
				st.setString(1, t.getMaNamHoc());

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
	public int deleteMany(ArrayList<NamHoc> arr) {
		int dem = 0;
		for (NamHoc NamHoc : arr) {
			dem += delete(NamHoc);
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
			String sql = "DELETE FROM NamHoc";

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
	public int update(NamHoc t) {
		int ketQua = 0;
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: tạo ra đối tượng statement
			String sql = "UPDATE NamHoc " + " SET " + " TenNamHoc=?" + " WHERE MaNamHoc=?";

			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t.getTenNamHoc());
			st.setString(2, t.getMaNamHoc());

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
		return ketQua;
	}

	public static void main(String[] args) {
		NamHocDAO NamHocDAO = new NamHocDAO();
		ArrayList<NamHoc> kq = NamHocDAO.selectAll();
		for (NamHoc NamHoc : kq) {
			System.out.println(NamHoc.getMaNamHoc() + ": " + NamHoc.getTenNamHoc());
		}

		System.out.println(NamHocDAO.selectByID("111111"));

		NamHoc nh = new NamHoc();
		nh.setMaNamHoc("444444");
		nh.setTenNamHoc("0");
		// NamHocDAO.insert(nh);
		NamHocDAO.update(nh);

	}
}
