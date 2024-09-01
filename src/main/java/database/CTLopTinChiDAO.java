package database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import com.mysql.cj.protocol.Resultset;

import model.CTLopTinChi;
import model.LoaiThongBao;
import model.LopTinChi;
import model.NoiDungThongBao;
import model.SinhVien;
import database.SinhVienDAO;

public class CTLopTinChiDAO implements DAOInterface<CTLopTinChi> {

	private ArrayList<CTLopTinChi> data = new ArrayList<>();

	@Override
	public ArrayList<CTLopTinChi> selectAll() {
		ArrayList<CTLopTinChi> data = new ArrayList<>();
		try {
			Connection con = JDBCUtil.getConnection();
			String sql = "SELECT * FROM CTLopTinChi";
			PreparedStatement st = con.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				String maLopTinChi = rs.getString("MaLopTinChi");
				String msv = rs.getString("MaSinhVien");
				String tgdk = rs.getString("ThoiGianDK");

				LopTinChi lopTinChi = new LopTinChiDAO().selectByID(maLopTinChi);

				SinhVien sv = (new SinhVienDAO().selectByID(msv));
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
				LocalDateTime thoiGianDangKy = LocalDateTime.parse(tgdk, formatter);

				CTLopTinChi ctLopTinChi = new CTLopTinChi(lopTinChi, sv, thoiGianDangKy);
				data.add(ctLopTinChi);
			}

			JDBCUtil.closeConnection(con);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return data;
	}

	public ArrayList<CTLopTinChi> selectByNamHocHocKy(String maSVIP, String maNamHocIP, String maHocKyIP) {
		ArrayList<CTLopTinChi> data = new ArrayList<>();
		try {
			Connection con = JDBCUtil.getConnection();
			String sql = "SELECT ctl.* " +
		             "FROM ctloptinchi ctl " +
		             "JOIN loptinchi ltc ON ctl.MaLopTinChi = ltc.MaLopTinChi " +
		             "JOIN ctnamhoc_hocky ctnhhk ON ltc.CTNHHK = ctnhhk.MaNHHocKy " +
		             "JOIN namhoc nh ON ctnhhk.MaNamHoc = nh.MaNamHoc " +
		             "WHERE ctl.MaSinhVien = ? " +
		             "  AND nh.MaNamHoc = ? " +
		             "  AND ctnhhk.MaHocKy = ?;";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, maSVIP);
			st.setString(2, maNamHocIP);
			st.setString(3, maHocKyIP);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				String maLopTinChi = rs.getString("MaLopTinChi");
				String msv = rs.getString("MaSinhVien");
				String tgdk = rs.getString("ThoiGianDK");

				LopTinChi lopTinChi = new LopTinChiDAO().selectByID(maLopTinChi);

				SinhVien sv = (new SinhVienDAO().selectByID(msv));
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
				LocalDateTime thoiGianDangKy = LocalDateTime.parse(tgdk, formatter);

				CTLopTinChi ctLopTinChi = new CTLopTinChi(lopTinChi, sv, thoiGianDangKy);
				data.add(ctLopTinChi);
			}

			JDBCUtil.closeConnection(con);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return data;
	}

	@Override
	public CTLopTinChi selectByID(String t) {
		// TODO Auto-generated method stub
		CTLopTinChi result = null;
		try {
			Connection con = JDBCUtil.getConnection();
			String sql = "SELECT * FROM CTLopTinChi WHERE MaLopTinChi = ?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t);
			System.out.println(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				String maLopTinChi = rs.getString("MaLopTinChi");
				String msv = rs.getString("MaSinhVien");
				String tgdk = rs.getString("ThoiGianDK");

				LopTinChi lopTinChi = new LopTinChiDAO().selectByID(maLopTinChi);
				SinhVien sv = (new SinhVienDAO().selectByID(msv));
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
				LocalDateTime thoiGianDangKy = LocalDateTime.parse(tgdk, formatter);

				CTLopTinChi ctLopTinChi = new CTLopTinChi(lopTinChi, sv, thoiGianDangKy);
				result = ctLopTinChi;
				break;
			}

			JDBCUtil.closeConnection(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public CTLopTinChi selectByMaLopMaSV(String maLop, String maSinhVien) {
		// TODO Auto-generated method stub
		CTLopTinChi result = null;
		try {
			Connection con = JDBCUtil.getConnection();
			String sql = "SELECT * FROM ctloptinchi WHERE MaLopTinChi = ? AND MaSinhVien = ?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, maLop);
			st.setString(2, maSinhVien);
			System.out.println(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				String maLopTinChi = rs.getString("MaLopTinChi");
				String msv = rs.getString("MaSinhVien");
				String tgdk = rs.getString("ThoiGianDK");

				LopTinChi lopTinChi = new LopTinChiDAO().selectByID(maLopTinChi);
				SinhVien sv = (new SinhVienDAO().selectByID(msv));
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
				LocalDateTime thoiGianDangKy = LocalDateTime.parse(tgdk, formatter);

				CTLopTinChi ctLopTinChi = new CTLopTinChi(lopTinChi, sv, thoiGianDangKy);
				result = ctLopTinChi;
				break;
			}

			JDBCUtil.closeConnection(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<CTLopTinChi> selectByType(String t) {
		// NoiDungThongBao result = null;
		ArrayList<CTLopTinChi> data = new ArrayList<>();
		try {
			Connection con = JDBCUtil.getConnection();
			String sql = "SELECT * FROM CTLopTinChi WHERE MaLopTinChi = ?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t);

			System.out.println(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				String maLopTinChi = rs.getString("MaLopTinChi");
				String msv = rs.getString("MaSinhVien");
				String tgdk = rs.getString("ThoiGianDK");
				LopTinChi lopTinChi = new LopTinChiDAO().selectByID(maLopTinChi);
				SinhVien sv = (new SinhVienDAO().selectByID(msv));
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
				LocalDateTime thoiGianDangKy = LocalDateTime.parse(tgdk, formatter);

				CTLopTinChi ctLopTinChi = new CTLopTinChi(lopTinChi, sv, thoiGianDangKy);
				data.add(ctLopTinChi);
			}

			JDBCUtil.closeConnection(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}

	@Override
	public int insert(CTLopTinChi t) {
		int ketQua = 0;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		if (this.selectByMaLopMaSV(t.getLopTinChi().getMaLopTinChi(), t.getSv().getMaSinhVien()) == null) {
			try {
				Connection con = JDBCUtil.getConnection();
				String sql = "INSERT INTO CTLopTinChi (MaLopTinChi, MaSinhVien, ThoiGianDK)" + " VALUES (?,?,?)";

				PreparedStatement st = con.prepareStatement(sql);
				st.setString(1, t.getLopTinChi().getMaLopTinChi());
				st.setString(2, t.getSv().getMaSinhVien());
				st.setString(3, t.getTgdk().format(formatter));
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
	public int insertAll(ArrayList<CTLopTinChi> arr) {
		// TODO Auto-generated method stub
		int dem = 0;
		for (CTLopTinChi CTLopTinChi : arr) {
			dem += insert(CTLopTinChi);
		}
		return dem;
	}

	@Override
	public int delete(CTLopTinChi t) {
		// TODO Auto-generated method stub
		int ketQua = 0;
		if (this.selectByID(t.getLopTinChi().getMaLopTinChi()) != null) {
			try {
				Connection con = JDBCUtil.getConnection();
				String sql = "DELETE from CTLopTinChi " + " WHERE MaLopTinChi=? AND MaSinhVien=?";

				PreparedStatement st = con.prepareStatement(sql);
				st.setString(1, t.getLopTinChi().getMaLopTinChi());
				st.setString(2, t.getSv().getMaSinhVien());

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
		return ketQua;
	}

	public int cancelCreditCourse(String maLop, String maSinhVien) {
		// TODO Auto-generated method stub
		int ketQua = 0;
		if (this.selectByID(maLop) != null) {
			try {
				Connection con = JDBCUtil.getConnection();
				String sql = "DELETE from CTLopTinChi " + " WHERE MaLopTinChi=? AND MaSinhVien=?";

				PreparedStatement st = con.prepareStatement(sql);
				st.setString(1, maLop);
				st.setString(2, maSinhVien);

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
		return ketQua;
	}

	@Override
	public int deleteMany(ArrayList<CTLopTinChi> arr) {
		// TODO Auto-generated method stub
		int dem = 0;
		for (CTLopTinChi CTLopTinChi : arr) {
			dem += delete(CTLopTinChi);
		}
		return dem;
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		int ketQua = 0;
		try {
			Connection con = JDBCUtil.getConnection();

			String sql = "DELETE FROM CTLopTinChi";

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
	public int update(CTLopTinChi t) {
		// TODO Auto-generated method stub
		int ketQua = 0;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		try {
			Connection con = JDBCUtil.getConnection();

			String sql = "UPDATE CTLopTinChi SET ThoiGianDK = ? WHERE MaLopTinChi=? AND MaSinhVien=?";

			PreparedStatement st = con.prepareStatement(sql);

			st.setString(1, t.getTgdk().format(formatter));
			st.setString(2, t.getLopTinChi().getMaLopTinChi());
			st.setString(3, t.getSv().getMaSinhVien());

			ketQua = st.executeUpdate();

			System.out.println("Bạn đã thực thi: " + sql);
			System.out.println("Có " + ketQua + " dòng bị thay đổi!");

			JDBCUtil.closeConnection(con);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ketQua;
	}

}
