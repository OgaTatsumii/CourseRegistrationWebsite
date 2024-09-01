package database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import com.mysql.cj.protocol.Resultset;

import model.KhoaHoc;
import model.Lop;
import model.MonHoc;
import model.Nganh;
import model.QueQuan;
import model.SinhVien;
import model.TaiKhoan;

public class MonHocDAO implements DAOInterface<MonHoc> {

	private ArrayList<MonHoc> data = new ArrayList<>();

	@Override
	public ArrayList<MonHoc> selectAll() {
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: tạo ra đối tượng statement
			String sql = "SELECT * FROM MonHoc";
			PreparedStatement st = con.prepareStatement(sql);

			// Bước 3: thực thi câu lệnh SQL
			System.out.println(sql);
			ResultSet rs = st.executeQuery();

			// Bước 4:
			while (rs.next()) {
				String maMonHoc = rs.getString("MaMonHoc");
				String maNganh = rs.getString("TenMonHoc");
				int soTinChi = rs.getInt("SoTinChi");
				MonHoc MonHoc = new MonHoc(maMonHoc, maNganh, soTinChi);
				data.add(MonHoc);
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
	public MonHoc selectByID(String t) {
		MonHoc result = null;
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: tạo ra đối tượng statement
			String sql = "SELECT * FROM MonHoc WHERE MaMonHoc = ?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t);

			// Bước 3: thực thi câu lệnh SQL
			System.out.println(sql);
			ResultSet rs = st.executeQuery();

			// Bước 4:
			while (rs.next()) {
				String maMonHoc = rs.getString("MaMonHoc");
				String maNganh = rs.getString("TenMonHoc");
				int soTinChi = rs.getInt("SoTinChi");
				result = new MonHoc(maMonHoc, maNganh, soTinChi);
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

	public ArrayList<MonHoc> selectMany(String[] monHocIDList) {
		ArrayList<MonHoc> result = new ArrayList<>();
		for (String monHocID : monHocIDList) {
			MonHoc foundMonHoc = selectByID(monHocID);
			if (foundMonHoc != null) {
				result.add(foundMonHoc);
			}
		}
		return result;
	}

	@Override
	public int insert(MonHoc t) {
		int ketQua = 0;
		if (this.selectByID(t.getMaMonHoc()) == null) {
			try {
				// Bước 1: tạo kết nối đến CSDL
				Connection con = JDBCUtil.getConnection();

				// Bước 2: tạo ra đối tượng statement
				String sql = "INSERT INTO MonHoc (MaMonHoc, TenMonHoc, SoTinChi) " + " VALUES (?,?,?)";

				PreparedStatement st = con.prepareStatement(sql);
				st.setString(1, t.getMaMonHoc());
				st.setString(2, t.getTenMonHoc());
				st.setInt(3, t.getSoTinChi());

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
	public int insertAll(ArrayList<MonHoc> arr) {
		int dem = 0;
		for (MonHoc MonHoc : arr) {
			dem += insert(MonHoc);
		}
		return dem;
	}

	@Override
	public int delete(MonHoc t) {
		int ketQua = 0;
		if (this.selectByID(t.getMaMonHoc()) != null) {
			try {
				// Bước 1: tạo kết nối đến CSDL
				Connection con = JDBCUtil.getConnection();

				// Bước 2: tạo ra đối tượng statement
				String sql = "DELETE from MonHoc " + " WHERE MaMonHoc=?";

				PreparedStatement st = con.prepareStatement(sql);
				st.setString(1, t.getMaMonHoc());

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
	public int deleteMany(ArrayList<MonHoc> arr) {
		int dem = 0;
		for (MonHoc MonHoc : arr) {
			dem += delete(MonHoc);
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
			String sql = "DELETE FROM MonHoc";

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
	public int update(MonHoc t) {
		int ketQua = 0;
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: tạo ra đối tượng statement

			String sql = "UPDATE MonHoc SET  TenMonHoc = ?, SoTinChi = ? " + "WHERE MaMonHoc = ?";

			PreparedStatement st = con.prepareStatement(sql);
			st.setString(3, t.getMaMonHoc());
			st.setString(1, t.getTenMonHoc());
			st.setInt(2, t.getSoTinChi());

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

	public ArrayList<MonHoc> selectByNganhHocKy(String MaNganh, String maHocKy) {
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: tạo ra đối tượng statement
			String sql = "SELECT mh.MaMonHoc, mh.TenMonHoc, mh.SoTinChi " + "FROM monhoc mh "
					+ "JOIN ctmonhoc ctmh ON mh.MaMonHoc = ctmh.MaMonHoc "
					+ "JOIN nganh_hocky nh ON ctmh.MaNganh_HK = nh.MaNganhHocKy "
					+ "JOIN hocky hk ON nh.MaHocKy = hk.MaHocKy " + "JOIN nganh n ON nh.MaNganh = n.MaNganh "
					+ "WHERE n.MaNganh = ? AND hk.MaHocKy = ?;";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, MaNganh);
			st.setString(2, maHocKy);
			// Bước 3: thực thi câu lệnh SQL
			System.out.println(sql);
			ResultSet rs = st.executeQuery();

			// Bước 4:
			while (rs.next()) {
				String maMonHoc = rs.getString("MaMonHoc");
				String maNganh = rs.getString("TenMonHoc");
				int soTinChi = rs.getInt("SoTinChi");
				MonHoc MonHoc = new MonHoc(maMonHoc, maNganh, soTinChi);
				data.add(MonHoc);
			}

			// Bước 5:
			JDBCUtil.closeConnection(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}

	public ArrayList<MonHoc> searchSubject(String nganh, String hocKy, String subjectID, String subjectName) {
	    ArrayList<MonHoc> data = new ArrayList<>();
	    try {
	        Connection con = JDBCUtil.getConnection();
	        String sql = "SELECT DISTINCT mh.MaMonHoc, mh.TenMonHoc, mh.SoTinChi " +
	                     "FROM monhoc mh " +
	                     "JOIN ctmonhoc ctmh ON mh.MaMonHoc = ctmh.MaMonHoc " +
	                     "JOIN nganh_hocky nhk ON ctmh.MaNganh_HK = nhk.MaNganhHocKy " +
	                     "WHERE 1=1";

	        if (nganh != null && !nganh.isEmpty()) {
	            sql += " AND nhk.MaNganh = ?";
	        }
	        if (hocKy != null && !hocKy.isEmpty()) {
	            sql += " AND nhk.MaHocKy = ?";
	        }
	        if (subjectID != null && !subjectID.isEmpty()) {
	            sql += " AND mh.MaMonHoc = ?";
	        }
	        if (subjectName != null && !subjectName.isEmpty()) {
	            sql += " AND mh.TenMonHoc LIKE ?";
	        }

	        PreparedStatement st = con.prepareStatement(sql);

	        int paramIndex = 1;
	        if (nganh != null && !nganh.isEmpty()) {
	            st.setString(paramIndex++, nganh);
	        }
	        if (hocKy != null && !hocKy.isEmpty()) {
	            st.setString(paramIndex++, hocKy);
	        }
	        if (subjectID != null && !subjectID.isEmpty()) {
	            st.setString(paramIndex++, subjectID);
	        }
	        if (subjectName != null && !subjectName.isEmpty()) {
	            st.setString(paramIndex++, "%" + subjectName + "%");
	        }

	        ResultSet rs = st.executeQuery();

	        while (rs.next()) {
	            String maMonHoc = rs.getString("MaMonHoc");
	            String tenMonHoc = rs.getString("TenMonHoc");
	            int soTinChi = rs.getInt("SoTinChi");
	            MonHoc monHoc = new MonHoc(maMonHoc, tenMonHoc, soTinChi);
	            data.add(monHoc);
	        }

	        JDBCUtil.closeConnection(con);
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return data;
	}


}
