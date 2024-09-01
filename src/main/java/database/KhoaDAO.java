package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.cj.protocol.Resultset;

import model.Khoa;

public class KhoaDAO implements DAOInterface<Khoa> {

	private ArrayList<Khoa> data = new ArrayList<>();

	@Override
	public ArrayList<Khoa> selectAll() {
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: tạo ra đối tượng statement
			String sql = "SELECT * FROM khoa";
			PreparedStatement st = con.prepareStatement(sql);

			// Bước 3: thực thi câu lệnh SQL
			System.out.println(sql);
			ResultSet rs = st.executeQuery();

			// Bước 4:
			while (rs.next()) {
				String maKhoa = rs.getString("MaKhoa");
				String tenKhoa = rs.getString("TenKhoa");
				Khoa khoa = new Khoa(maKhoa, tenKhoa);
				data.add(khoa);
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
	public Khoa selectByID(String t) {
		Khoa result = null;
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: tạo ra đối tượng statement
			String sql = "SELECT * FROM khoa WHERE MaKhoa = ?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t);

			// Bước 3: thực thi câu lệnh SQL
			System.out.println(sql);
			ResultSet rs = st.executeQuery();

			// Bước 4:
			while (rs.next()) {
				String maKhoa = rs.getString("MaKhoa");
				String tenKhoa = rs.getString("TenKhoa");
				result = new Khoa(maKhoa, tenKhoa);
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
	public int insert(Khoa t) {
		int ketQua = 0;
		if (this.selectByID(t.getMaKhoa()) == null) {
			try {
				// Bước 1: tạo kết nối đến CSDL
				Connection con = JDBCUtil.getConnection();

				// Bước 2: tạo ra đối tượng statement
				String sql = "INSERT INTO Khoa (MaKhoa, TenKhoa) " + " VALUES (?,?)";

				PreparedStatement st = con.prepareStatement(sql);
				st.setString(1, t.getMaKhoa());
				st.setString(2, t.getTenKhoa());

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
	public int insertAll(ArrayList<Khoa> arr) {
		int dem = 0;
		for (Khoa khoa : arr) {
			dem += insert(khoa);
		}
		return dem;
	}

	@Override
	public int delete(Khoa t) {
		int ketQua = 0;
		if (this.selectByID(t.getMaKhoa()) != null) {
			try {
				// Bước 1: tạo kết nối đến CSDL
				Connection con = JDBCUtil.getConnection();

				// Bước 2: tạo ra đối tượng statement
				String sql = "DELETE from Khoa " + " WHERE MaKhoa=?";

				PreparedStatement st = con.prepareStatement(sql);
				st.setString(1, t.getMaKhoa());

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
	public int deleteMany(ArrayList<Khoa> arr) {
		int dem = 0;
		for (Khoa khoa : arr) {
			dem += delete(khoa);
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
			String sql = "DELETE FROM Khoa";

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
	public int update(Khoa t) {
		int ketQua = 0;
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();
			
			// Bước 2: tạo ra đối tượng statement
			String sql = "UPDATE Khoa "+
					 " SET " +
					 " MaKhoa=?"+
					 ", TenKhoa=?"+
					 " WHERE MaKhoa=?";
			
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t.getMaKhoa());
			st.setString(2, t.getTenKhoa());
			st.setString(3, t.getMaKhoa());
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
		KhoaDAO khoaDAO = new KhoaDAO();
		ArrayList<Khoa> kq = khoaDAO.selectAll();
		for (Khoa khoa : kq) {
			System.out.println(khoa.toString());
		}

		Khoa idSelect = khoaDAO.selectByID("CNTTII");
		System.out.println(idSelect);

		Khoa newKhoa = new Khoa("CNTTII", "Khoa công nghê thông tin 3");
		khoaDAO.update(newKhoa);
	}
}
