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
import model.Khoa;
import model.Lop;
import model.SinhVien;
import model.QueQuan;
import model.TaiKhoan;

public class SinhVienDAO implements DAOInterface<SinhVien> {

	private ArrayList<SinhVien> data = new ArrayList<>();

	@Override
	public ArrayList<SinhVien> selectAll() {

		try {
			Connection con = JDBCUtil.getConnection();
			String sql = "SELECT * FROM SinhVien";
			PreparedStatement st = con.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				String maSinhVien = rs.getString("MaSinhVien");
				String hoTen = rs.getString("HoTen");
				Date ngaySinhDate = rs.getDate("NgaySinh");
				LocalDate ngaySinh = ngaySinhDate.toLocalDate();
				String gioiTinh = rs.getString("GioiTinh");
				String soDienThoai = rs.getString("SDT");
				String CCCD = rs.getString("CCCD");
				String email = rs.getString("Email");
				String tamTru = rs.getString("TamTru");
				String maLop = rs.getString("MaLop");
				String maTinh = rs.getString("MaTinh");
				String userName = rs.getString("UserName");

				Lop lop = (new LopDAO()).selectByID(maLop);
				QueQuan queQuan = (new QueQuanDAO()).selectByID(maTinh);
				TaiKhoan taiKhoan = (new TaiKhoanDAO()).selectByID(userName);

				SinhVien SinhVien = new SinhVien(maSinhVien, hoTen, ngaySinh, gioiTinh, soDienThoai, CCCD, email,
						tamTru, lop, queQuan, taiKhoan);
				data.add(SinhVien);
			}

			JDBCUtil.closeConnection(con);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return data;
	}

	@Override
	public SinhVien selectByID(String t) {
		SinhVien result = null;
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: tạo ra đối tượng statement
			String sql = "SELECT * FROM SinhVien WHERE MaSinhVien = ?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t);

			// Bước 3: thực thi câu lệnh SQL
			System.out.println(sql);
			ResultSet rs = st.executeQuery();

			// Bước 4:
			while (rs.next()) {
				String maSinhVien = rs.getString("MaSinhVien");
				String hoTen = rs.getString("HoTen");
				Date ngaySinhDate = rs.getDate("NgaySinh");
				LocalDate ngaySinh = ngaySinhDate.toLocalDate();
				String gioiTinh = rs.getString("GioiTinh");
				String soDienThoai = rs.getString("SDT");
				String CCCD = rs.getString("CCCD");
				String email = rs.getString("Email");
				String tamTru = rs.getString("TamTru");
				String maLop = rs.getString("MaLop");
				String maTinh = rs.getString("MaTinh");
				String userName = rs.getString("UserName");

				Lop lop = (new LopDAO()).selectByID(maLop);
				QueQuan queQuan = (new QueQuanDAO()).selectByID(maTinh);
				TaiKhoan taiKhoan = (new TaiKhoanDAO()).selectByID(userName);

				result = new SinhVien(maSinhVien, hoTen, ngaySinh, gioiTinh, soDienThoai, CCCD, email, tamTru, lop,
						queQuan, taiKhoan);
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

	public boolean selectByCCCD(String cccd) {
		boolean result = false;
		try {
			Connection con = JDBCUtil.getConnection();
			String sql = "SELECT * FROM SinhVien WHERE CCCD = ?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, cccd);
			System.out.println(sql);
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				result = true;
			} else {
				result = false;
			}
			JDBCUtil.closeConnection(con);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	public boolean selectBySDT(String sdt) {
		boolean result = false;
		try {
			Connection con = JDBCUtil.getConnection();
			String sql = "SELECT * FROM SinhVien WHERE SDT = ?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, sdt);
			System.out.println(sql);
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				result = true;
			} else {
				result = false;
			}
			JDBCUtil.closeConnection(con);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public SinhVien selectByUserName(String t) {
		SinhVien result = null;
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: tạo ra đối tượng statement
			String sql = "SELECT * FROM SinhVien WHERE UserName = ?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t);

			// Bước 3: thực thi câu lệnh SQL
			System.out.println(sql);
			ResultSet rs = st.executeQuery();

			// Bước 4:
			while (rs.next()) {
				String maSinhVien = rs.getString("MaSinhVien");
				String hoTen = rs.getString("HoTen");
				Date ngaySinhDate = rs.getDate("NgaySinh");
				LocalDate ngaySinh = ngaySinhDate.toLocalDate();
				String gioiTinh = rs.getString("GioiTinh");
				String soDienThoai = rs.getString("SDT");
				String CCCD = rs.getString("CCCD");
				String email = rs.getString("Email");
				String tamTru = rs.getString("TamTru");
				String maLop = rs.getString("MaLop");
				String maTinh = rs.getString("MaTinh");
				String userName = rs.getString("UserName");

				Lop lop = (new LopDAO()).selectByID(maLop);
				QueQuan queQuan = (new QueQuanDAO()).selectByID(maTinh);
				TaiKhoan taiKhoan = (new TaiKhoanDAO()).selectByID(userName);

				result = new SinhVien(maSinhVien, hoTen, ngaySinh, gioiTinh, soDienThoai, CCCD, email, tamTru, lop,
						queQuan, taiKhoan);
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
	
	public ArrayList<SinhVien> selectByLop(String t) {
		ArrayList<SinhVien> data = new ArrayList<>();
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: tạo ra đối tượng statement
			String sql = "SELECT * FROM SinhVien WHERE MaLop = ?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t);

			// Bước 3: thực thi câu lệnh SQL
			System.out.println(sql);
			ResultSet rs = st.executeQuery();

			// Bước 4:
			while (rs.next()) {
				String maSinhVien = rs.getString("MaSinhVien");
				String hoTen = rs.getString("HoTen");
				Date ngaySinhDate = rs.getDate("NgaySinh");
				LocalDate ngaySinh = ngaySinhDate.toLocalDate();
				String gioiTinh = rs.getString("GioiTinh");
				String soDienThoai = rs.getString("SDT");
				String CCCD = rs.getString("CCCD");
				String email = rs.getString("Email");
				String tamTru = rs.getString("TamTru");
				String maLop = rs.getString("MaLop");
				String maTinh = rs.getString("MaTinh");
				String userName = rs.getString("UserName");

				Lop lop = (new LopDAO()).selectByID(maLop);
				QueQuan queQuan = (new QueQuanDAO()).selectByID(maTinh);
				TaiKhoan taiKhoan = (new TaiKhoanDAO()).selectByID(userName);

				SinhVien sv = new SinhVien(maSinhVien, hoTen, ngaySinh, gioiTinh, soDienThoai, CCCD, email, tamTru, lop,
						queQuan, taiKhoan);
				data.add(sv);
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
	public int insert(SinhVien t) {
		int ketQua = 0;
		if (this.selectByID(t.getMaSinhVien()) == null) {
			try {
				// Bước 1: tạo kết nối đến CSDL
				Connection con = JDBCUtil.getConnection();

				// Bước 2: tạo ra đối tượng statement
				String sql = "INSERT INTO SinhVien (MaSinhVien, HoTen, NgaySinh, GioiTinh, SDT, CCCD, Email, TamTru, MaLop, MaTinh, UserName)"
						+ " VALUES (?,?,?,?,?,?,?,?,?,?,?)";

				PreparedStatement st = con.prepareStatement(sql);
				st.setString(1, t.getMaSinhVien());
				st.setString(2, t.getHoTen());
				st.setDate(3, java.sql.Date.valueOf(t.getNgaySinh())); // Chuyển đổi từ LocalDate sang java.sql.Date
				st.setString(4, t.getGioiTinh());
				st.setString(5, t.getSoDienThoai());
				st.setString(6, t.getCCCD());
				st.setString(7, t.getEmail());
				st.setString(8, t.getTamTru());
				st.setString(9, t.getLop().getMaLop());
				st.setString(10, t.getQueQuan().getMaTinh());
				st.setString(11, t.getTaiKhoan().getUserName());

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
	public int insertAll(ArrayList<SinhVien> arr) {
		int dem = 0;
		for (SinhVien SinhVien : arr) {
			dem += insert(SinhVien);
		}
		return dem;
	}

	@Override
	public int delete(SinhVien t) {
		int ketQua = 0;
		if (this.selectByID(t.getMaSinhVien()) != null) {
			try {
				// Bước 1: tạo kết nối đến CSDL
				Connection con = JDBCUtil.getConnection();

				// Bước 2: tạo ra đối tượng statement
				String sql = "DELETE from SinhVien " + " WHERE MaSinhVien=?";

				PreparedStatement st = con.prepareStatement(sql);
				st.setString(1, t.getMaSinhVien());

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
	public int deleteMany(ArrayList<SinhVien> arr) {
		int dem = 0;
		for (SinhVien SinhVien : arr) {
			dem += delete(SinhVien);
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
			String sql = "DELETE FROM SinhVien";

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
	public int update(SinhVien t) {
		int ketQua = 0;
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: tạo ra đối tượng statement
			String sql = "UPDATE SinhVien " + "SET " + "HoTen = ?," + "NgaySinh = ?," + "GioiTinh = ?," + "SDT = ?,"
					+ "CCCD = ?," + "Email = ?," + "TamTru = ?," + "MaLop = ?," + "MaTinh = ?," + "UserName = ?"
					+ "WHERE MaSinhVien = ?";

			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t.getHoTen());
			st.setDate(2, Date.valueOf(t.getNgaySinh()));
			st.setString(3, t.getGioiTinh());
			st.setString(4, t.getSoDienThoai());
			st.setString(5, t.getCCCD());
			st.setString(6, t.getEmail());
			st.setString(7, t.getTamTru());
			st.setString(8, t.getLop().getMaLop());
			st.setString(9, t.getQueQuan().getMaTinh());
			st.setString(10, t.getTaiKhoan().getUserName());
			st.setString(11, t.getMaSinhVien());

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

	public ArrayList<SinhVien> searchStudent(String khoa, String nganh, String lop, String namHoc, String studentID) {
		try {
			Connection con = JDBCUtil.getConnection();
			String sql = "SELECT * " + "FROM sinhvien sv " + "JOIN lop l ON sv.MaLop = l.MaLop "
					+ "JOIN nganh n ON l.MaNganh = n.MaNganh " + "WHERE 1=1";
			if (khoa != null && !khoa.isEmpty()) {
				sql += " AND n.MaKhoa = '" + khoa + "'";
			}
			if (nganh != null && !nganh.isEmpty()) {
				sql += " AND n.MaNganh = '" + nganh + "'";
			}
			if (lop != null && !lop.isEmpty()) {
				sql += " AND l.MaLop = '" + lop + "'";
			}
			if (namHoc != null && !namHoc.isEmpty()) {
				sql += " AND l.MaKhoaHoc = '" + namHoc + "'";
			}
			if (studentID != null && !studentID.isEmpty()) {
				sql += " AND MaSinhVien = '" + studentID + "'";
			}
			PreparedStatement st = con.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				String maSinhVien = rs.getString("MaSinhVien");
				String hoTen = rs.getString("HoTen");
				Date ngaySinhDate = rs.getDate("NgaySinh");
				LocalDate ngaySinh = ngaySinhDate.toLocalDate();
				String gioiTinh = rs.getString("GioiTinh");
				String soDienThoai = rs.getString("SDT");
				String CCCD = rs.getString("CCCD");
				String email = rs.getString("Email");
				String tamTru = rs.getString("TamTru");
				String maLop = rs.getString("MaLop");
				String maTinh = rs.getString("MaTinh");
				String userName = rs.getString("UserName");

				Lop lop2 = (new LopDAO()).selectByID(maLop);
				QueQuan queQuan = (new QueQuanDAO()).selectByID(maTinh);
				TaiKhoan taiKhoan = (new TaiKhoanDAO()).selectByID(userName);

				SinhVien SinhVien = new SinhVien(maSinhVien, hoTen, ngaySinh, gioiTinh, soDienThoai, CCCD, email,
						tamTru, lop2, queQuan, taiKhoan);
				data.add(SinhVien);
			}

			JDBCUtil.closeConnection(con);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return data;
	}
	
	public boolean selectByMail(String email) {
		boolean result = false;
		try {
			Connection con = JDBCUtil.getConnection();
			String sql = "SELECT * FROM SinhVien WHERE Email = ?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, email);
			System.out.println(sql);
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				result = true;
			} else {
				result = false;
			}
			JDBCUtil.closeConnection(con);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

}
