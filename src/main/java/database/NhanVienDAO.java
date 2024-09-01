package database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import com.mysql.cj.protocol.Resultset;

import model.ChucVu;
import model.NhanVien;
import model.QueQuan;
import model.TaiKhoan;

public class NhanVienDAO implements DAOInterface<NhanVien> {

	private ArrayList<NhanVien> data = new ArrayList<>();

	@Override
	public ArrayList<NhanVien> selectAll() {
		ArrayList<NhanVien> data = new ArrayList<>();

		try {
			Connection con = JDBCUtil.getConnection();
			String sql = "SELECT * FROM NhanVien";
			PreparedStatement st = con.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				String maNhanVien = rs.getString("MaNhanVien");
				String hoTen = rs.getString("HoTen");
				Date ngaySinhDate = rs.getDate("NgaySinh");
				LocalDate ngaySinh = ngaySinhDate.toLocalDate();
				String gioiTinh = rs.getString("GioiTinh");
				String soDienThoai = rs.getString("SDT");
				String CCCD = rs.getString("CCCD");
				String email = rs.getString("Email");
				String maTinh = rs.getString("MaTinh");
				String userName = rs.getString("UserName");

				QueQuan queQuan = (new QueQuanDAO()).selectByID(maTinh);
				TaiKhoan taiKhoan = (new TaiKhoanDAO()).selectByID(userName);

				NhanVien nhanVien = new NhanVien(maNhanVien, hoTen, ngaySinh, gioiTinh, soDienThoai, CCCD, email,
						queQuan, taiKhoan);
				data.add(nhanVien);
			}

			JDBCUtil.closeConnection(con);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return data;
	}

	@Override
	public NhanVien selectByID(String t) {
		NhanVien result = null;
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: tạo ra đối tượng statement
			String sql = "SELECT * FROM NhanVien WHERE MaNhanVien = ?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t);

			// Bước 3: thực thi câu lệnh SQL
			System.out.println(sql);
			ResultSet rs = st.executeQuery();

			// Bước 4:
			while (rs.next()) {
				String maNhanVien = rs.getString("MaNhanVien");
				String hoTen = rs.getString("HoTen");
				Date ngaySinhDate = rs.getDate("NgaySinh");
				LocalDate ngaySinh = ngaySinhDate.toLocalDate();
				String gioiTinh = rs.getString("GioiTinh");
				String soDienThoai = rs.getString("SDT");
				String CCCD = rs.getString("CCCD");
				String email = rs.getString("Email");
				String maTinh = rs.getString("MaTinh");
				String userName = rs.getString("UserName");

				QueQuan queQuan = (new QueQuanDAO()).selectByID(maTinh);
				TaiKhoan taiKhoan = (new TaiKhoanDAO()).selectByID(userName);

				result = new NhanVien(maNhanVien, hoTen, ngaySinh, gioiTinh, soDienThoai, CCCD, email, queQuan,
						taiKhoan);
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
	
	public NhanVien selectByUserName(String t) {
		NhanVien result = null;
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: tạo ra đối tượng statement
			String sql = "SELECT * FROM NhanVien WHERE UserName = ?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t);

			// Bước 3: thực thi câu lệnh SQL
			System.out.println(sql);
			ResultSet rs = st.executeQuery();

			// Bước 4:
			while (rs.next()) {
				String maNhanVien = rs.getString("MaNhanVien");
				String hoTen = rs.getString("HoTen");
				Date ngaySinhDate = rs.getDate("NgaySinh");
				LocalDate ngaySinh = ngaySinhDate.toLocalDate();
				String gioiTinh = rs.getString("GioiTinh");
				String soDienThoai = rs.getString("SDT");
				String CCCD = rs.getString("CCCD");
				String email = rs.getString("Email");
				String maTinh = rs.getString("MaTinh");
				String userName = rs.getString("UserName");

				QueQuan queQuan = (new QueQuanDAO()).selectByID(maTinh);
				TaiKhoan taiKhoan = (new TaiKhoanDAO()).selectByID(userName);

				result = new NhanVien(maNhanVien, hoTen, ngaySinh, gioiTinh, soDienThoai, CCCD, email, queQuan,
						taiKhoan);
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
	public int insert(NhanVien t) {
		int ketQua = 0;
		if (this.selectByID(t.getMaNhanVien()) == null) {
			try {
				// Bước 1: tạo kết nối đến CSDL
				Connection con = JDBCUtil.getConnection();

				// Bước 2: tạo ra đối tượng statement
				String sql = "INSERT INTO NhanVien (MaNhanVien, HoTen, NgaySinh, GioiTinh, SDT, CCCD, Email, MaTinh, UserName)"
						+ " VALUES (?,?,?,?,?,?,?,?,?)";

				PreparedStatement st = con.prepareStatement(sql);
				String maNhanVien = t.getMaNhanVien();
				String hoTen = t.getHoTen();
				LocalDate ngaySinh = t.getNgaySinh();
				String gioiTinh = t.getGioiTinh();
				String soDienThoai = t.getSoDienThoai();
				String CCCD = t.getCCCD();
				String email = t.getEmail();
				String maTinh = t.getQueQuan().getMaTinh();
				String userName = t.getTaiKhoan().getUserName();

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
	public int insertAll(ArrayList<NhanVien> arr) {
		int dem = 0;
		for (NhanVien NhanVien : arr) {
			dem += insert(NhanVien);
		}
		return dem;
	}

	@Override
	public int delete(NhanVien t) {
		int ketQua = 0;
		if (this.selectByID(t.getMaNhanVien()) != null) {
			try {
				// Bước 1: tạo kết nối đến CSDL
				Connection con = JDBCUtil.getConnection();

				// Bước 2: tạo ra đối tượng statement
				String sql = "DELETE from NhanVien " + " WHERE MaNhanVien=?";

				PreparedStatement st = con.prepareStatement(sql);
				st.setString(1, t.getMaNhanVien());

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
	public int deleteMany(ArrayList<NhanVien> arr) {
		int dem = 0;
		for (NhanVien NhanVien : arr) {
			dem += delete(NhanVien);
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
			String sql = "DELETE FROM NhanVien";

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
	public int update(NhanVien t) {
		int ketQua = 0;
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: tạo ra đối tượng statement
			String sql = "UPDATE NhanVien " + "SET " + "TenNhanVien = ?," + "NgaySinh = ?," + "GioiTinh = ?,"
					+ "SDT = ?," + "CCCD = ?," + "Email = ?," + "MaTinh = ?," + "UserName = ?" + "WHERE MaNhanVien = ?";

			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t.getHoTen());
			st.setDate(2, Date.valueOf(t.getNgaySinh()));
			st.setString(3, t.getGioiTinh());
			st.setString(4, t.getSoDienThoai());
			st.setString(5, t.getCCCD());
			st.setString(6, t.getEmail());
			st.setString(7, t.getQueQuan().getMaTinh());
			st.setString(8, t.getTaiKhoan().getUserName());
			st.setString(9, t.getMaNhanVien());

			// Bước 3: thực thi câu lệnh SQL
			ketQua = st.executeUpdate();

			// Bước 4: in kết quả và câu lệnh SQL đã thực thi
			System.out.println("Bạn đã thực thi: " + sql);
			System.out.println("Có " + ketQua + " dòng bị thay đổi!");

			// Bước 5: đóng kết nối
			JDBCUtil.closeConnection(con);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ketQua;
	}

}
