package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.cj.protocol.Resultset;

import model.Khoa;
import model.Nganh;

public class NganhDAO implements DAOInterface<Nganh> {

	private ArrayList<Nganh> data = new ArrayList<>();

	@Override
	public ArrayList<Nganh> selectAll() {
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: tạo ra đối tượng statement
			String sql = "SELECT * FROM Nganh";
			PreparedStatement st = con.prepareStatement(sql);

			// Bước 3: thực thi câu lệnh SQL
			System.out.println(sql);
			ResultSet rs = st.executeQuery();

			// Bước 4:
			while (rs.next()) {
				String maNganh = rs.getString("MaNganh");
				String tenNganh = rs.getString("TenNganh");
				int giaMotTinChi = rs.getInt("GiaMotTinChi");
				int soTinChiNganh = rs.getInt("SoTinChiNganh");
				float thoiGianDaoTao = rs.getFloat("ThoiGianDaoTao");
				String maKhoa = rs.getString("MaKhoa");
				Khoa khoa = new Khoa();
				KhoaDAO khoaDAO = new KhoaDAO();
				khoa = khoaDAO.selectByID(maKhoa);
				Nganh Nganh = new Nganh(maNganh, tenNganh, giaMotTinChi,soTinChiNganh,thoiGianDaoTao,khoa);
				data.add(Nganh);
			}

			// Bước 5:
			JDBCUtil.closeConnection(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}

	public ArrayList<Nganh> search(String mnhID, String tenNganhIP) {
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: tạo ra đối tượng statement
			String sql = "SELECT * FROM Nganh WHERE 1 = 1";
			if (mnhID != null && !mnhID.isEmpty()) {
	            sql += " AND MaNganh = ?";
	        }
	        if (tenNganhIP != null && !tenNganhIP.isEmpty()) {
	            sql += " AND TenNganh LIKE ?";
	        }
			PreparedStatement st = con.prepareStatement(sql);
			
			int paramIndex = 1;
	        if (mnhID != null && !mnhID.isEmpty()) {
	            st.setString(paramIndex++, mnhID);
	        }
	        if (tenNganhIP != null && !tenNganhIP.isEmpty()) {
	        	st.setString(paramIndex++, "%" + tenNganhIP + "%");
	        }

			// Bước 3: thực thi câu lệnh SQL
			System.out.println(sql);
			ResultSet rs = st.executeQuery();

			// Bước 4:
			while (rs.next()) {
				String maNganh = rs.getString("MaNganh");
				String tenNganh = rs.getString("TenNganh");
				int giaMotTinChi = rs.getInt("GiaMotTinChi");
				int soTinChiNganh = rs.getInt("SoTinChiNganh");
				float thoiGianDaoTao = rs.getFloat("ThoiGianDaoTao");
				String maKhoa = rs.getString("MaKhoa");
				Khoa khoa = new Khoa();
				KhoaDAO khoaDAO = new KhoaDAO();
				khoa = khoaDAO.selectByID(maKhoa);
				Nganh Nganh = new Nganh(maNganh, tenNganh, giaMotTinChi,soTinChiNganh,thoiGianDaoTao,khoa);
				data.add(Nganh);
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
	public Nganh selectByID(String t) {
		Nganh result = null;
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: tạo ra đối tượng statement
			String sql = "SELECT * FROM Nganh WHERE MaNganh = ?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t);

			// Bước 3: thực thi câu lệnh SQL
			System.out.println(sql);
			ResultSet rs = st.executeQuery();

			// Bước 4:
			while (rs.next()) {
				String maNganh = rs.getString("MaNganh");
				String tenNganh = rs.getString("TenNganh");
				int giaMotTinChi = rs.getInt("GiaMotTinChi");
				int soTinChiNganh = rs.getInt("SoTinChiNganh");
				float thoiGianDaoTao = rs.getFloat("ThoiGianDaoTao");
				String maKhoa = rs.getString("MaKhoa");
				Khoa khoa = new Khoa();
				KhoaDAO khoaDAO = new KhoaDAO();
				khoa = khoaDAO.selectByID(maKhoa);
				result = new Nganh(maNganh, tenNganh, giaMotTinChi,soTinChiNganh,thoiGianDaoTao,khoa);			
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
	public int insert(Nganh t) {
		int ketQua = 0;
		if (this.selectByID(t.getMaNganh()) == null) {
			try {
				// Bước 1: tạo kết nối đến CSDL
				Connection con = JDBCUtil.getConnection();

				// Bước 2: tạo ra đối tượng statement
				String sql = "INSERT INTO Nganh (MaNganh, TenNganh, GiaMotTinChi, SoTinChiNganh,ThoiGianDaoTao, MaKhoa ) " + " VALUES (?,?,?,?,?,?)";

				PreparedStatement st = con.prepareStatement(sql);
				st.setString(1, t.getMaNganh());
				st.setString(2, t.getTenNganh());
				st.setInt(3, t.getGiaMotTinChi());
				st.setInt(4, t.getSoTinChi());
				st.setFloat(5, t.getThoiGianDaoTao());
				st.setString(6, t.getKhoa().getMaKhoa());

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
	public int insertAll(ArrayList<Nganh> arr) {
		int dem = 0;
		for (Nganh Nganh : arr) {
			dem += insert(Nganh);
		}
		return dem;
	}

	@Override
	public int delete(Nganh t) {
		int ketQua = 0;
		if (this.selectByID(t.getMaNganh()) != null) {
			try {
				// Bước 1: tạo kết nối đến CSDL
				Connection con = JDBCUtil.getConnection();

				// Bước 2: tạo ra đối tượng statement
				String sql = "DELETE from Nganh " + " WHERE MaNganh=?";

				PreparedStatement st = con.prepareStatement(sql);
				st.setString(1, t.getMaNganh());

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
	public int deleteMany(ArrayList<Nganh> arr) {
		int dem = 0;
		for (Nganh Nganh : arr) {
			dem += delete(Nganh);
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
			String sql = "DELETE FROM Nganh";

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
	public int update(Nganh t) {
	    int ketQua = 0;
	    try {
	        // Bước 1: tạo kết nối đến CSDL
	        Connection con = JDBCUtil.getConnection();
	        
	        // Bước 2: tạo ra đối tượng statement
	        String sql = "UPDATE Nganh "+
	                     "SET " +
	                     "TenNganh = ?"+
	                     ", GiaMotTinChi = ?"+
	                     ", SoTinChiNganh = ?"+
	                     ", MaKhoa = ?"+
	                     " WHERE MaNganh = ?";
	        
	        PreparedStatement st = con.prepareStatement(sql);
	        st.setString(1, t.getTenNganh());
	        st.setInt(2, t.getGiaMotTinChi());
	        st.setInt(3, t.getSoTinChi());
	        st.setString(4, t.getKhoa().getMaKhoa());
	        st.setString(5, t.getMaNganh());
	        
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
	public ArrayList<Nganh> search(String Khoa,String MaNganh,String TenNganh) {
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: tạo ra đối tượng statement
			String sql = "SELECT * FROM Nganh WHERE 1=1";
			if (Khoa != null && !Khoa.isEmpty()) {
				sql += " AND MaKhoa = '" + Khoa + "'";
	        }
			if (MaNganh != null && !MaNganh.isEmpty()) {
				sql += " AND MaNganh LIKE '" + MaNganh + "%"+ "'";
	        }
			if (TenNganh != null && !TenNganh.isEmpty()) {
				sql += " AND TenNganh LIKE '"+"%" + TenNganh + "%"+"'";
	        }
			PreparedStatement st = con.prepareStatement(sql);

			// Bước 3: thực thi câu lệnh SQL
			System.out.println(sql);
			ResultSet rs = st.executeQuery();

			// Bước 4:
			while (rs.next()) {
				String maNganh = rs.getString("MaNganh");
				String tenNganh = rs.getString("TenNganh");
				int giaMotTinChi = rs.getInt("GiaMotTinChi");
				int soTinChiNganh = rs.getInt("SoTinChiNganh");
				float thoiGianDaoTao = rs.getFloat("ThoiGianDaoTao");
				String maKhoa = rs.getString("MaKhoa");
				Khoa khoa = new Khoa();
				KhoaDAO khoaDAO = new KhoaDAO();
				khoa = khoaDAO.selectByID(maKhoa);
				Nganh Nganh = new Nganh(maNganh, tenNganh, giaMotTinChi,soTinChiNganh,thoiGianDaoTao,khoa);
				data.add(Nganh);
			}

			// Bước 5:
			JDBCUtil.closeConnection(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}
}
