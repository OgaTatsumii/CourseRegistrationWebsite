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

public class KhoaHocDAO implements DAOInterface<KhoaHoc> {

	private ArrayList<KhoaHoc> data = new ArrayList<>();

	@Override
	public ArrayList<KhoaHoc> selectAll() {
		try {
			Connection con = JDBCUtil.getConnection();

			String sql = "SELECT * FROM KhoaHoc";
			PreparedStatement st = con.prepareStatement(sql);

			System.out.println(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				String maKhoaHoc = rs.getString("MaNamHoc");
				LocalDate ngayBatDau = rs.getDate("NgayBatDau").toLocalDate();								
				KhoaHoc KhoaHoc = new KhoaHoc(maKhoaHoc, ngayBatDau);
				data.add(KhoaHoc);
			}

			JDBCUtil.closeConnection(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}

	@Override
	public KhoaHoc selectByID(String t) {
		KhoaHoc result = null;
		try {
			Connection con = JDBCUtil.getConnection();

			String sql = "SELECT * FROM KhoaHoc WHERE MaNamHoc = ?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t);

			// Bước 3: thực thi câu lệnh SQL
			System.out.println(sql);
			ResultSet rs = st.executeQuery();

			// Bước 4:
			while (rs.next()) {
				String maKhoaHoc = rs.getString("MaNamHoc");
				LocalDate ngayBatDau = rs.getDate("NgayBatDau").toLocalDate();				
				result = new KhoaHoc(maKhoaHoc, ngayBatDau);
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
	public int insert(KhoaHoc t) {
		int ketQua = 0;
		if (this.selectByID(t.getMaNamHoc()) == null) {
			try {
				// Bước 1: tạo kết nối đến CSDL
				Connection con = JDBCUtil.getConnection();

				// Bước 2: tạo ra đối tượng statement
				String sql = "INSERT INTO KhoaHoc (MaNamHoc, NgayBatDau) " + " VALUES (?,?)";

				PreparedStatement st = con.prepareStatement(sql);
				st.setString(1, t.getMaNamHoc());
				st.setDate(2, Date.valueOf(t.getNgayBatDau()));

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
	public int insertAll(ArrayList<KhoaHoc> arr) {
		int dem = 0;
		for (KhoaHoc KhoaHoc : arr) {
			dem += insert(KhoaHoc);
		}
		return dem;
	}

	@Override
	public int delete(KhoaHoc t) {
		int ketQua = 0;
		if (this.selectByID(t.getMaNamHoc()) != null) {
			try {
				// Bước 1: tạo kết nối đến CSDL
				Connection con = JDBCUtil.getConnection();

				// Bước 2: tạo ra đối tượng statement
				String sql = "DELETE from KhoaHoc " + " WHERE MaNamHoc=?";

				PreparedStatement st = con.prepareStatement(sql);
				st.setString(1, t.getMaNamHoc());

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
	public int deleteMany(ArrayList<KhoaHoc> arr) {
		int dem = 0;
		for (KhoaHoc KhoaHoc : arr) {
			dem += delete(KhoaHoc);
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
			String sql = "DELETE FROM KhoaHoc";

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
	public int update(KhoaHoc t) {
	    int ketQua = 0;
	    try {
	        // Bước 1: tạo kết nối đến CSDL
	        Connection con = JDBCUtil.getConnection();

	        // Bước 2: tạo ra đối tượng statement
	        String sql = "UPDATE KhoaHoc SET NgayBatDau = ? WHERE MaNamHoc = ?";
	        PreparedStatement st = con.prepareStatement(sql);
	        st.setDate(1, Date.valueOf(t.getNgayBatDau()));
	        st.setString(2, t.getMaNamHoc());

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

	public static void main(String[] args) {
		KhoaHocDAO khDAO = new KhoaHocDAO();
		ArrayList<KhoaHoc> kq = khDAO.selectAll();
		for (KhoaHoc KhoaHoc : kq) {
			System.out.println(KhoaHoc.getNgayBatDau().toString());
		}

//		Lop idSelect = LopDAO.selectByID("CNTTII");
//		System.out.println(idSelect);
	}


}
