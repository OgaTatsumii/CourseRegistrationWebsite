package database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.mysql.cj.protocol.Resultset;

import model.LopTinChi;
import model.MonHoc;
import model.QueQuan;
import model.SinhVien;
import model.TaiKhoan;
import model.CTMonHoc;
import model.CTNamHoc_HocKy;
import model.GiangVien;
import model.Lop;
import database.CTMonHocDAO;

public class LopTinChiDAO implements DAOInterface<LopTinChi> {

	private ArrayList<LopTinChi> data = new ArrayList<>();

	@Override
	public ArrayList<LopTinChi> selectAll() {
		ArrayList<LopTinChi> data = new ArrayList<>();
		try {
			Connection con = JDBCUtil.getConnection();
			String sql = "SELECT * FROM LopTinChi";
			PreparedStatement st = con.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				String maLopTinChi = rs.getString("MaLopTinChi");
				String maMonHoc = rs.getString("MaMonHoc");
				String maGiangVien = rs.getString("MaGiangVien");
				String maCTNHHK = rs.getString("CTNHHK");
				String tietBatDau = rs.getString("TietBatDau");
				String tietKetThuc = rs.getString("TietKetThuc");
				String ngayBD = rs.getString("NgayBatDau");
				String ngayKT = rs.getString("NgayKetThuc");
				String thu = rs.getString("Thu");
				String phong = rs.getString("Phong");
				short soLuongSinhVien = rs.getShort("SoLuongSinhVien");
				short daDangKy = rs.getShort("daDangKy");

				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				LocalDate ngayBatDau = null;
				LocalDate ngayKetThuc = null;
				try {
					// Chuyển đổi chuỗi thành LocalDate
					ngayBatDau = LocalDate.parse(ngayBD, formatter);
					ngayKetThuc = LocalDate.parse(ngayKT, formatter);
				} catch (Exception e) {
					System.out.println("Không thể chuyển đổi chuỗi thành LocalDate: " + e.getMessage());
				}
				// -----------------------------------
				MonHoc monHoc = (new MonHocDAO().selectByID(maMonHoc));
				GiangVien giangVien = (new GiangVienDAO().selectByID(maGiangVien));
				CTNamHoc_HocKy ctNamHoc_HocKy = (new CTNamHoc_HocKyDAO().selectByID(maCTNHHK));
				LopTinChi ltc = new LopTinChi(maLopTinChi, monHoc, giangVien, ctNamHoc_HocKy, tietBatDau, tietKetThuc,
						ngayBatDau, ngayKetThuc, thu, phong, soLuongSinhVien, daDangKy);
				data.add(ltc);
			}
			JDBCUtil.closeConnection(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}

	public ArrayList<LopTinChi> selectByMaLop(String maLop, String maCTNHHKIP) {
		ArrayList<LopTinChi> data = new ArrayList<>();
		try {
			Connection con = JDBCUtil.getConnection();
			String sql = "SELECT DISTINCT" + " lt.MaLopTinChi," + " lt.MaMonHoc," + " lt.MaGiangVien," + "lt.CTNHHK,"
					+ " lt.TietBatDau," + " lt.TietKetThuc," + " lt.NgayBatDau," + " lt.NgayKetThuc," + " lt.Thu,"
					+ " lt.Phong," + " lt.SoLuongSinhVien," + " lt.DaDangKy" + " FROM" + " loptinchi lt" + " JOIN"
					+ " ctmonhoc ctmh ON lt.MaMonHoc = ctmh.MaMonHoc" + " JOIN"
					+ " nganh_hocky nhk ON ctmh.MaNganh_HK = nhk.MaNganhHocKy" + " JOIN"
					+ " lop l ON nhk.MaNganh = l.MaNganh" + " WHERE" + " l.MaLop = ? AND lt.CTNHHK = ?;";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, maLop);
			st.setString(2, maCTNHHKIP);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				String maLopTinChi = rs.getString("MaLopTinChi");
				String maMonHoc = rs.getString("MaMonHoc");
				String maGiangVien = rs.getString("MaGiangVien");
				String maCTNHHK = rs.getString("CTNHHK");
				String tietBatDau = rs.getString("TietBatDau");
				String tietKetThuc = rs.getString("TietKetThuc");
				String ngayBD = rs.getString("NgayBatDau");
				String ngayKT = rs.getString("NgayKetThuc");
				String thu = rs.getString("Thu");
				String phong = rs.getString("Phong");
				short soLuongSinhVien = rs.getShort("SoLuongSinhVien");
				short daDangKy = rs.getShort("daDangKy");

				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				LocalDate ngayBatDau = null;
				LocalDate ngayKetThuc = null;
				try {
					// Chuyển đổi chuỗi thành LocalDate
					ngayBatDau = LocalDate.parse(ngayBD, formatter);
					ngayKetThuc = LocalDate.parse(ngayKT, formatter);
				} catch (Exception e) {
					System.out.println("Không thể chuyển đổi chuỗi thành LocalDate: " + e.getMessage());
				}
				// -----------------------------------
				MonHoc monHoc = (new MonHocDAO().selectByID(maMonHoc));
				GiangVien giangVien = (new GiangVienDAO().selectByID(maGiangVien));
				CTNamHoc_HocKy ctNamHoc_HocKy = (new CTNamHoc_HocKyDAO().selectByID(maCTNHHK));
				LopTinChi ltc = new LopTinChi(maLopTinChi, monHoc, giangVien, ctNamHoc_HocKy, tietBatDau, tietKetThuc,
						ngayBatDau, ngayKetThuc, thu, phong, soLuongSinhVien, daDangKy);
				data.add(ltc);
			}
			JDBCUtil.closeConnection(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}

	public ArrayList<LopTinChi> selectByDiemSV(String maSinhVien, String maNHHKIP) {
		ArrayList<LopTinChi> data = new ArrayList<>();
		try {
			Connection con = JDBCUtil.getConnection();
			String sql = "SELECT " + "ltc.* " + "FROM " + "diem d " + "JOIN "
					+ "loptinchi ltc ON d.MaMonHoc = ltc.MaMonHoc " + "WHERE "
					+ "d.Diem < 4 AND d.Diem!=-1 AND d.MaSinhVien = ? AND ltc.CTNHHK= ?;";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, maSinhVien);
			st.setString(2, maNHHKIP);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				String maLopTinChi = rs.getString("MaLopTinChi");
				String maMonHoc = rs.getString("MaMonHoc");
				String maGiangVien = rs.getString("MaGiangVien");
				String maCTNHHK = rs.getString("CTNHHK");
				String tietBatDau = rs.getString("TietBatDau");
				String tietKetThuc = rs.getString("TietKetThuc");
				String ngayBD = rs.getString("NgayBatDau");
				String ngayKT = rs.getString("NgayKetThuc");
				String thu = rs.getString("Thu");
				String phong = rs.getString("Phong");
				short soLuongSinhVien = rs.getShort("SoLuongSinhVien");
				short daDangKy = rs.getShort("daDangKy");

				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				LocalDate ngayBatDau = null;
				LocalDate ngayKetThuc = null;
				try {
					// Chuyển đổi chuỗi thành LocalDate
					ngayBatDau = LocalDate.parse(ngayBD, formatter);
					ngayKetThuc = LocalDate.parse(ngayKT, formatter);
				} catch (Exception e) {
					System.out.println("Không thể chuyển đổi chuỗi thành LocalDate: " + e.getMessage());
				}
				// -----------------------------------
				MonHoc monHoc = (new MonHocDAO().selectByID(maMonHoc));
				GiangVien giangVien = (new GiangVienDAO().selectByID(maGiangVien));
				CTNamHoc_HocKy ctNamHoc_HocKy = (new CTNamHoc_HocKyDAO().selectByID(maCTNHHK));
				LopTinChi ltc = new LopTinChi(maLopTinChi, monHoc, giangVien, ctNamHoc_HocKy, tietBatDau, tietKetThuc,
						ngayBatDau, ngayKetThuc, thu, phong, soLuongSinhVien, daDangKy);
				data.add(ltc);
			}
			JDBCUtil.closeConnection(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}

	@Override
	public LopTinChi selectByID(String t) {
		// TODO Auto-generated method stub
		LopTinChi result = null;
		try {
			Connection con = JDBCUtil.getConnection();
			String sql = "SELECT * FROM LopTinChi WHERE MaLopTinChi = ?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t);
			System.out.println(sql);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				String maLopTinChi = rs.getString("MaLopTinChi");
				String maMonHoc = rs.getString("MaMonHoc");
				String maGiangVien = rs.getString("MaGiangVien");
				String maCTNHHK = rs.getString("CTNHHK");
				String tietBatDau = rs.getString("TietBatDau");
				String tietKetThuc = rs.getString("TietKetThuc");
				String ngayBD = rs.getString("NgayBatDau");
				String ngayKT = rs.getString("NgayKetThuc");
				String thu = rs.getString("Thu");
				String phong = rs.getString("Phong");
				short soLuongSinhVien = rs.getShort("SoLuongSinhVien");
				short daDangKy = rs.getShort("daDangKy");

				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				LocalDate ngayBatDau = null;
				LocalDate ngayKetThuc = null;
				try {
					// Chuyển đổi chuỗi thành LocalDate
					ngayBatDau = LocalDate.parse(ngayBD, formatter);
					ngayKetThuc = LocalDate.parse(ngayKT, formatter);
				} catch (Exception e) {
					System.out.println("Không thể chuyển đổi chuỗi thành LocalDate: " + e.getMessage());
				}
				// -----------------------------------
				MonHoc monHoc = (new MonHocDAO().selectByID(maMonHoc));
				GiangVien giangVien = (new GiangVienDAO().selectByID(maGiangVien));
				CTNamHoc_HocKy ctNamHoc_HocKy = (new CTNamHoc_HocKyDAO().selectByID(maCTNHHK));
				LopTinChi ltc = new LopTinChi(maLopTinChi, monHoc, giangVien, ctNamHoc_HocKy, tietBatDau, tietKetThuc,
						ngayBatDau, ngayKetThuc, thu, phong, soLuongSinhVien, daDangKy);
				result = ltc;
			}
			JDBCUtil.closeConnection(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int insert(LopTinChi t) {
		int ketQua = 0;
		if (this.selectByID(t.getMaLopTinChi()) == null) {
			try {
				Connection con = JDBCUtil.getConnection();
				String sql = "INSERT INTO LopTinChi (MaLopTinChi, MaMonHoc, MaGiangVien,CTNHHK, TietBatDau, TietKetThuc, NgayBatDau, NgayKetThuc, Thu, Phong, SoLuongSinhVien, DaDangKy)"
						+ " VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
				PreparedStatement st = con.prepareStatement(sql);
				st.setString(1, t.getMaLopTinChi());
				st.setString(2, t.getMonHoc().getMaMonHoc());
				st.setString(3, t.getGiangVien().getMaGiangVien());
				st.setString(4, t.getCtNamHoc_HocKy().getmaNHHocKy());
				st.setString(5, t.getTietBatDau());
				st.setString(6, t.getTietKetThuc());
				st.setString(7, t.getNgayBatDau().toString());
				st.setString(8, t.getNgayKetThuc().toString());
				st.setString(9, t.getThu());
				st.setString(10, t.getPhong());
				st.setShort(11, t.getSoLuongSinhVien());
				st.setShort(12, t.getDaDangKy());

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
	public int insertAll(ArrayList<LopTinChi> arr) {
		// TODO Auto-generated method stub
		int dem = 0;
		for (LopTinChi LopTinChi : arr) {
			dem += insert(LopTinChi);
		}
		return dem;
	}

	@Override
	public int delete(LopTinChi t) {
		// TODO Auto-generated method stub
		int ketQua = 0;
		if (this.selectByID(t.getMaLopTinChi()) != null) {
			try {
				Connection con = JDBCUtil.getConnection();
				String sql = "DELETE from LopTinChi " + " WHERE MaLopTinChi=?";

				PreparedStatement st = con.prepareStatement(sql);
				st.setString(1, t.getMaLopTinChi());

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
	public int deleteMany(ArrayList<LopTinChi> arr) {
		// TODO Auto-generated method stub
		int dem = 0;
		for (LopTinChi LopTinChi : arr) {
			dem += delete(LopTinChi);
		}
		return dem;
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		int ketQua = 0;
		try {
			Connection con = JDBCUtil.getConnection();
			String sql = "DELETE FROM LopTinChi";

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
	public int update(LopTinChi t) {
	    int ketQua = 0;
	    String sql = "UPDATE LopTinChi " +
	                 "SET MaMonHoc = ?, " +
	                 "MaGiangVien = ?, " +
	                 "CTNHHK = ?, " +
	                 "TietBatDau = ?, " +
	                 "TietKetThuc = ?, " +
	                 "NgayBatDau = ?, " +
	                 "NgayKetThuc = ?, " +
	                 "Thu = ?, " +
	                 "Phong = ?, " +
	                 "SoLuongSinhVien = ?, " +
	                 "DaDangKy = ? " +
	                 "WHERE MaLopTinChi = ?";

	    try (Connection con = JDBCUtil.getConnection();
	         PreparedStatement st = con.prepareStatement(sql)) {
	         
	        st.setString(1, t.getMonHoc().getMaMonHoc());
	        st.setString(2, t.getGiangVien().getMaGiangVien());
	        st.setString(3, t.getCtNamHoc_HocKy().getmaNHHocKy());
	        st.setString(4, t.getTietBatDau());
	        st.setString(5, t.getTietKetThuc());
	        st.setString(6, t.getNgayBatDau().toString());
	        st.setString(7, t.getNgayKetThuc().toString());
	        st.setString(8, t.getThu());
	        st.setString(9, t.getPhong());
	        st.setShort(10, t.getSoLuongSinhVien());
	        st.setShort(11, t.getDaDangKy());
	        st.setString(12, t.getMaLopTinChi());

	        ketQua = st.executeUpdate();
	        System.out.println("Bạn đã thực thi: " + sql);
	        System.out.println("Có " + ketQua + " dòng bị thay đổi!");

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return ketQua;
	}

	public ArrayList<LopTinChi> selectByNganhHK(String maNganh, String maHocKy, String maCTNHHKIP) {
		ArrayList<LopTinChi> data = new ArrayList<>();
		try {
			Connection con = JDBCUtil.getConnection();
			String sql = "SELECT " + "ltc.MaLopTinChi, " + "ltc.MaMonHoc, " + "ltc.MaGiangVien, " + "ltc.CTNHHK, "
					+ "ltc.TietBatDau, " + "ltc.TietKetThuc, " + "ltc.NgayBatDau, " + "ltc.NgayKetThuc, " + "ltc.Thu, "
					+ "ltc.Phong, " + "ltc.SoLuongSinhVien, " + "ltc.DaDangKy " + "FROM " + "loptinchi ltc "
					+ "JOIN ctmonhoc ctmh ON ltc.MaMonHoc = ctmh.MaMonHoc "
					+ "JOIN nganh_hocky nhk ON ctmh.MaNganh_HK = nhk.MaNganhHocKy " + "WHERE " + "nhk.MaNganh = ? "
					+ "AND nhk.MaHocKy = ? AND ltc.CTNHHK = ?; ";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, maNganh);
			st.setString(2, maHocKy);
			st.setString(3, maCTNHHKIP);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				String maLopTinChi = rs.getString("MaLopTinChi");
				String maMonHoc = rs.getString("MaMonHoc");
				String maGiangVien = rs.getString("MaGiangVien");
				String maCTNHHK = rs.getString("CTNHHK");
				String tietBatDau = rs.getString("TietBatDau");
				String tietKetThuc = rs.getString("TietKetThuc");
				String ngayBD = rs.getString("NgayBatDau");
				String ngayKT = rs.getString("NgayKetThuc");
				String thu = rs.getString("Thu");
				String phong = rs.getString("Phong");
				short soLuongSinhVien = rs.getShort("SoLuongSinhVien");
				short daDangKy = rs.getShort("daDangKy");

				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				LocalDate ngayBatDau = null;
				LocalDate ngayKetThuc = null;
				try {
					// Chuyển đổi chuỗi thành LocalDate
					ngayBatDau = LocalDate.parse(ngayBD, formatter);
					ngayKetThuc = LocalDate.parse(ngayKT, formatter);
				} catch (Exception e) {
					System.out.println("Không thể chuyển đổi chuỗi thành LocalDate: " + e.getMessage());
				}
				// -----------------------------------
				MonHoc monHoc = (new MonHocDAO().selectByID(maMonHoc));
				GiangVien giangVien = (new GiangVienDAO().selectByID(maGiangVien));
				CTNamHoc_HocKy ctNamHoc_HocKy = (new CTNamHoc_HocKyDAO().selectByID(maCTNHHK));
				LopTinChi ltc = new LopTinChi(maLopTinChi, monHoc, giangVien, ctNamHoc_HocKy, tietBatDau, tietKetThuc,
						ngayBatDau, ngayKetThuc, thu, phong, soLuongSinhVien, daDangKy);
				data.add(ltc);
			}
			JDBCUtil.closeConnection(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}

	public ArrayList<LopTinChi> selectByMaSinhVien(String maSinhVien, String maCTNHHKIP) {
		ArrayList<LopTinChi> data = new ArrayList<>();
		try {
			Connection con = JDBCUtil.getConnection();
			String sql = "SELECT " + "ltc.MaLopTinChi, " + "ltc.MaMonHoc, " + "ltc.MaGiangVien, " + "ltc.CTNHHK, "
					+ "ltc.TietBatDau, " + "ltc.TietKetThuc, " + "ltc.NgayBatDau, " + "ltc.NgayKetThuc, " + "ltc.Thu, "
					+ "ltc.Phong, " + "ltc.SoLuongSinhVien, " + "ltc.DaDangKy " + "FROM " + "loptinchi ltc "
					+ "INNER JOIN ctloptinchi c ON ltc.MaLopTinChi = c.MaLopTinChi " + "WHERE c.MaSinhVien = ? AND ltc.CTNHHK = ?; ";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, maSinhVien);
			st.setString(2, maCTNHHKIP);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				String maLopTinChi = rs.getString("MaLopTinChi");
				String maMonHoc = rs.getString("MaMonHoc");
				String maGiangVien = rs.getString("MaGiangVien");
				String maCTNHHK = rs.getString("CTNHHK");
				String tietBatDau = rs.getString("TietBatDau");
				String tietKetThuc = rs.getString("TietKetThuc");
				String ngayBD = rs.getString("NgayBatDau");
				String ngayKT = rs.getString("NgayKetThuc");
				String thu = rs.getString("Thu");
				String phong = rs.getString("Phong");
				short soLuongSinhVien = rs.getShort("SoLuongSinhVien");
				short daDangKy = rs.getShort("daDangKy");

				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				LocalDate ngayBatDau = null;
				LocalDate ngayKetThuc = null;
				try {
					// Chuyển đổi chuỗi thành LocalDate
					ngayBatDau = LocalDate.parse(ngayBD, formatter);
					ngayKetThuc = LocalDate.parse(ngayKT, formatter);
				} catch (Exception e) {
					System.out.println("Không thể chuyển đổi chuỗi thành LocalDate: " + e.getMessage());
				}
				// -----------------------------------
				MonHoc monHoc = (new MonHocDAO().selectByID(maMonHoc));
				GiangVien giangVien = (new GiangVienDAO().selectByID(maGiangVien));
				CTNamHoc_HocKy ctNamHoc_HocKy = (new CTNamHoc_HocKyDAO().selectByID(maCTNHHK));
				LopTinChi ltc = new LopTinChi(maLopTinChi, monHoc, giangVien, ctNamHoc_HocKy, tietBatDau, tietKetThuc,
						ngayBatDau, ngayKetThuc, thu, phong, soLuongSinhVien, daDangKy);
				data.add(ltc);
			}
			JDBCUtil.closeConnection(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}
	
	public LopTinChi selectAvailableBySubjectTimeQuantity(String t) {
		LopTinChi result = null;
		try {
			Connection con = JDBCUtil.getConnection();
			String sql = "SELECT * "
					+ "FROM loptinchi"
					+ " WHERE CURRENT_DATE < NgayBatDau"
					+ "  AND DaDangKy < SoLuongSinhVien"
					+ "  AND MaMonHoc = ?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t);
			System.out.println(sql);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				String maLopTinChi = rs.getString("MaLopTinChi");
				String maMonHoc = rs.getString("MaMonHoc");
				String maGiangVien = rs.getString("MaGiangVien");
				String maCTNHHK = rs.getString("CTNHHK");
				String tietBatDau = rs.getString("TietBatDau");
				String tietKetThuc = rs.getString("TietKetThuc");
				String ngayBD = rs.getString("NgayBatDau");
				String ngayKT = rs.getString("NgayKetThuc");
				String thu = rs.getString("Thu");
				String phong = rs.getString("Phong");
				short soLuongSinhVien = rs.getShort("SoLuongSinhVien");
				short daDangKy = rs.getShort("daDangKy");

				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				LocalDate ngayBatDau = null;
				LocalDate ngayKetThuc = null;
				try {
					// Chuyển đổi chuỗi thành LocalDate
					ngayBatDau = LocalDate.parse(ngayBD, formatter);
					ngayKetThuc = LocalDate.parse(ngayKT, formatter);
				} catch (Exception e) {
					System.out.println("Không thể chuyển đổi chuỗi thành LocalDate: " + e.getMessage());
				}
				// -----------------------------------
				MonHoc monHoc = (new MonHocDAO().selectByID(maMonHoc));
				GiangVien giangVien = (new GiangVienDAO().selectByID(maGiangVien));
				CTNamHoc_HocKy ctNamHoc_HocKy = (new CTNamHoc_HocKyDAO().selectByID(maCTNHHK));
				LopTinChi ltc = new LopTinChi(maLopTinChi, monHoc, giangVien, ctNamHoc_HocKy, tietBatDau, tietKetThuc,
						ngayBatDau, ngayKetThuc, thu, phong, soLuongSinhVien, daDangKy);
				result = ltc;
				break;
	
			}
			JDBCUtil.closeConnection(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	
	
	public static void main(String[] args) {
		LopTinChiDAO lopTinChiDAO = new LopTinChiDAO();
		ArrayList<LopTinChi> dsLopTinChi = lopTinChiDAO.selectAll();
		System.out.println(dsLopTinChi);
//		MonHoc idSelect = MonHocDAO.selectByID("CNTTII");
//		System.out.println(idSelect);
	}
}
