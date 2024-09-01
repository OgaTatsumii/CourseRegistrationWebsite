package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.cj.protocol.Resultset;

import model.ChucVu;

public class ChucVuDAO implements DAOInterface<ChucVu> {

	private ArrayList<ChucVu> data = new ArrayList<>();

	@Override
	public ArrayList<ChucVu> selectAll() {
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: tạo ra đối tượng statement
			String sql = "SELECT * FROM chucVu";
			PreparedStatement st = con.prepareStatement(sql);

			// Bước 3: thực thi câu lệnh SQL
			System.out.println(sql);
			ResultSet rs = st.executeQuery();

			// Bước 4:
			while (rs.next()) {
				String maChucVu = rs.getString("MaChucVu");
				String tenChucVu = rs.getString("TenChucVu");
				ChucVu ChucVu = new ChucVu(maChucVu, tenChucVu);
				data.add(ChucVu);
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
	public ChucVu selectByID(String t) {
		ChucVu result = null;
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: tạo ra đối tượng statement
			String sql = "SELECT * FROM ChucVu WHERE MaChucVu = ?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t);

			// Bước 3: thực thi câu lệnh SQL
			System.out.println(sql);
			ResultSet rs = st.executeQuery();

			// Bước 4:
			while (rs.next()) {
				String maChucVu = rs.getString("MaChucVu");
				String tenChucVu = rs.getString("TenChucVu");
				result = new ChucVu(maChucVu, tenChucVu);
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
	public int insert(ChucVu t) {
		int ketQua = 0;
		if (this.selectByID(t.getMaChucVu()) == null) {
			try {
				// Bước 1: tạo kết nối đến CSDL
				Connection con = JDBCUtil.getConnection();

				// Bước 2: tạo ra đối tượng statement
				String sql = "INSERT INTO ChucVu (MaChucVu, TenChucVu) " + " VALUES (?,?)";

				PreparedStatement st = con.prepareStatement(sql);
				st.setString(1, t.getMaChucVu());
				st.setString(2, t.getTenChucVu());

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
	public int insertAll(ArrayList<ChucVu> arr) {
		int dem = 0;
		for (ChucVu ChucVu : arr) {
			dem += insert(ChucVu);
		}
		return dem;
	}

	@Override
	public int delete(ChucVu t) {
		int ketQua = 0;
		if (this.selectByID(t.getMaChucVu()) != null) {
			try {
				// Bước 1: tạo kết nối đến CSDL
				Connection con = JDBCUtil.getConnection();

				// Bước 2: tạo ra đối tượng statement
				String sql = "DELETE from ChucVu " + " WHERE MaChucVu=?";

				PreparedStatement st = con.prepareStatement(sql);
				st.setString(1, t.getMaChucVu());

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
	public int deleteMany(ArrayList<ChucVu> arr) {
		int dem = 0;
		for (ChucVu ChucVu : arr) {
			dem += delete(ChucVu);
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
			String sql = "DELETE FROM ChucVu";

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
	public int update(ChucVu t) {
		int ketQua = 0;
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();
			
			// Bước 2: tạo ra đối tượng statement
			String sql = "UPDATE ChucVu "+
					 " SET " +
					 " MaChucVu=?"+
					 ", TenChucVu=?"+
					 " WHERE MaChucVu=?";
			
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t.getMaChucVu());
			st.setString(2, t.getTenChucVu());
			st.setString(3, t.getMaChucVu());
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

	public static void main(String[] args) {
		ChucVuDAO ChucVuDAO = new ChucVuDAO();
		ArrayList<ChucVu> kq = ChucVuDAO.selectAll();
		for (ChucVu ChucVu : kq) {
			System.out.println(ChucVu.toString());
		}

		ChucVu idSelect = ChucVuDAO.selectByID("GV");
		System.out.println(idSelect);
		
		ChucVuDAO.insert(new ChucVu("GV","Giảng Viên"));

	}
}
