package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.TaiKhoan;
import model.ChucVu;
import model.Khoa;


public class TaiKhoanDAO implements DAOInterface<TaiKhoan>{
	
	private ArrayList<TaiKhoan> data = new ArrayList<>();

	@Override
	public ArrayList<TaiKhoan> selectAll() {
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: tạo ra đối tượng statement
			String sql = "SELECT * FROM taikhoan";
			PreparedStatement st = con.prepareStatement(sql);

			// Bước 3: thực thi câu lệnh SQL
			System.out.println(sql);
			ResultSet rs = st.executeQuery();

			// Bước 4:
			while (rs.next()) {
				String userName = rs.getString("UserName");
				String matKhau = rs.getString("MatKhau");
				String maChucVu = rs.getString("MaChucVu");
				
				ChucVu chucVu1 = new ChucVu();
				chucVu1.setMaChucVu(maChucVu);
				
				ChucVu chucVu = (new ChucVuDAO()).selectByID(maChucVu);
				
				TaiKhoan taiKhoan = new TaiKhoan(userName,matKhau,chucVu);
				data.add(taiKhoan);
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
	public TaiKhoan selectByID(String t) {
		TaiKhoan result = null;
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: tạo ra đối tượng statement
			String sql = "SELECT * FROM taiKhoan WHERE UserName = ?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t);

			// Bước 3: thực thi câu lệnh SQL
			System.out.println(sql);
			ResultSet rs = st.executeQuery();

			// Bước 4:
			while (rs.next()) {
				String userName = rs.getString("UserName");
				String matKhau = rs.getString("MatKhau");
				String maChucVu = rs.getString("MaChucVu");
				
				ChucVu chucVu1 = new ChucVu();
				chucVu1.setMaChucVu(maChucVu);
				
				ChucVu chucVu = (new ChucVuDAO()).selectByID(maChucVu);
				
				result = new TaiKhoan(userName,matKhau,chucVu);
				
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
	public int insert(TaiKhoan t) {
		int ketQua = 0;
		if (this.selectByID(t.getUserName()) == null) {
			try {
				// Bước 1: tạo kết nối đến CSDL
				Connection con = JDBCUtil.getConnection();

				// Bước 2: tạo ra đối tượng statement
				String sql = "INSERT INTO taiKhoan (UserName, MatKhau, MaChucVu) " + " VALUES (?,MD5(?),?)";

				PreparedStatement st = con.prepareStatement(sql);
				st.setString(1, t.getUserName());
				st.setString(2, t.getMatKhau());
				st.setString(3, t.getChucVu().getMaChucVu());

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
	public int insertAll(ArrayList<TaiKhoan> arr) {
		int dem = 0;
		for (TaiKhoan TaiKhoan : arr) {
			dem += insert(TaiKhoan);
		}
		return dem;
	}

	@Override
	public int delete(TaiKhoan t) {
		int ketQua = 0;
		if (this.selectByID(t.getUserName()) != null) {
			try {
				// Bước 1: tạo kết nối đến CSDL
				Connection con = JDBCUtil.getConnection();

				// Bước 2: tạo ra đối tượng statement
				String sql = "DELETE from taikhoan " + " WHERE UserName=?";

				PreparedStatement st = con.prepareStatement(sql);
				st.setString(1, t.getUserName());

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
	public int deleteMany(ArrayList<TaiKhoan> arr) {
		int dem = 0;
		for (TaiKhoan TaiKhoan : arr) {
			dem += delete(TaiKhoan);
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
			String sql = "DELETE FROM taikhoan";

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
	public int update(TaiKhoan t) {
		int ketQua = 0;
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();
			
			// Bước 2: tạo ra đối tượng statement
			String sql = "UPDATE taikhoan "+
					 " SET " +
					 " UserName=?"+
					 ", MatKhau=MD5(?)"+
					 ", MaChucVu=?"+
					 " WHERE UserName=?";
			
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t.getUserName());
			st.setString(2, t.getMatKhau());
			st.setString(3, t.getChucVu().getMaChucVu());
			st.setString(4, t.getUserName());

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
	
	public TaiKhoan selectByUserNameAndPassWord(TaiKhoan t) {
		TaiKhoan result = null;
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: tạo ra đối tượng statement
			String sql = "SELECT * FROM taiKhoan WHERE UserName = ? AND MatKhau = MD5(?)";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t.getUserName() );
			st.setString(2, t.getMatKhau() );
			
			// Bước 3: thực thi câu lệnh SQL
			System.out.println(sql);
			ResultSet rs	 = st.executeQuery();

			// Bước 4:
			while (rs.next()) {
				String userName = rs.getString("UserName");
				String matKhau = rs.getString("MatKhau");
				String maChucVu = rs.getString("MaChucVu");
				
				ChucVu chucVu1 = new ChucVu();
				chucVu1.setMaChucVu(maChucVu);
				
				ChucVu chucVu = (new ChucVuDAO()).selectByID(maChucVu);
				
				result = new TaiKhoan(userName,matKhau,chucVu);
				
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
	
}
