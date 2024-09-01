package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.cj.protocol.Resultset;

import model.QueQuan;

public class QueQuanDAO implements DAOInterface<QueQuan> {

	private ArrayList<QueQuan> data = new ArrayList<>();

	@Override
	public ArrayList<QueQuan> selectAll() {
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: tạo ra đối tượng statement
			String sql = "SELECT * FROM QueQuan";
			PreparedStatement st = con.prepareStatement(sql);

			// Bước 3: thực thi câu lệnh SQL
			System.out.println(sql);
			ResultSet rs = st.executeQuery();

			// Bước 4:
			while (rs.next()) {
				String MaTinh = rs.getString("MaTinh");
				String TenTinh = rs.getString("TenTinh");
				QueQuan QueQuan = new QueQuan(MaTinh, TenTinh);
				data.add(QueQuan);
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
	public QueQuan selectByID(String t) {
		QueQuan result = null;
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: tạo ra đối tượng statement
			String sql = "SELECT * FROM QueQuan WHERE MaTinh = ?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t);

			// Bước 3: thực thi câu lệnh SQL
			System.out.println(sql);
			ResultSet rs = st.executeQuery();

			// Bước 4:
			while (rs.next()) {
				String MaTinh = rs.getString("MaTinh");
				String TenTinh = rs.getString("TenTinh");
				result = new QueQuan(MaTinh, TenTinh);
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
	public int insert(QueQuan t) {
		int ketQua = 0;
		if (this.selectByID(t.getMaTinh()) == null) {
			try {
				// Bước 1: tạo kết nối đến CSDL
				Connection con = JDBCUtil.getConnection();

				// Bước 2: tạo ra đối tượng statement
				String sql = "INSERT INTO QueQuan (MaTinh, TenTinh) " + " VALUES (?,?)";

				PreparedStatement st = con.prepareStatement(sql);
				st.setString(1, t.getMaTinh());
				st.setString(2, t.getTenTinh());

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
	public int insertAll(ArrayList<QueQuan> arr) {
		int dem = 0;
		for (QueQuan QueQuan : arr) {
			dem += insert(QueQuan);
		}
		return dem;
	}

	@Override
	public int delete(QueQuan t) {
		int ketQua = 0;
		if (this.selectByID(t.getMaTinh()) != null) {
			try {
				// Bước 1: tạo kết nối đến CSDL
				Connection con = JDBCUtil.getConnection();

				// Bước 2: tạo ra đối tượng statement
				String sql = "DELETE from QueQuan " + " WHERE MaTinh=?";

				PreparedStatement st = con.prepareStatement(sql);
				st.setString(1, t.getMaTinh());

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
	public int deleteMany(ArrayList<QueQuan> arr) {
		int dem = 0;
		for (QueQuan QueQuan : arr) {
			dem += delete(QueQuan);
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
			String sql = "DELETE FROM QueQuan";

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
	public int update(QueQuan t) {
		int ketQua = 0;
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();
			
			// Bước 2: tạo ra đối tượng statement
			String sql = "UPDATE QueQuan "+
					 " SET " +
					 " MaTinh=?"+
					 ", TenTinh=?"+
					 " WHERE MaTinh=?";
			
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t.getMaTinh());
			st.setString(2, t.getTenTinh());
			st.setString(3, t.getMaTinh());
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
		QueQuanDAO QueQuanDAO = new QueQuanDAO();
		ArrayList<QueQuan> kq = QueQuanDAO.selectAll();
		for (QueQuan QueQuan : kq) {
			System.out.println(QueQuan.getMaTinh() + ": " + QueQuan.getTenTinh() );
		}

		

	}
}
