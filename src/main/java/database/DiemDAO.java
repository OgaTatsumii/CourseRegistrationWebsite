package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Diem;
import model.Khoa;
import model.MonHoc;
import model.Nganh;
import model.Diem;
import model.Diem;
import model.SinhVien;
import model.Diem;

public class DiemDAO implements DAOInterface<Diem> {
	private ArrayList<Diem> data = new ArrayList<>();
	@Override
	public ArrayList<Diem> selectAll() {
		ArrayList<Diem> data = new ArrayList<>();
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: tạo ra đối tượng statement
			String sql = "SELECT * FROM Diem";
			PreparedStatement st = con.prepareStatement(sql);

			// Bước 3: thực thi câu lệnh SQL
			System.out.println(sql);
			ResultSet rs = st.executeQuery();

			// Bước 4:
			while (rs.next()) {
				String maSinhVien = rs.getString("MaSinhVien");
				String maMonHoc = rs.getString("MaMonHoc");
				float diem = rs.getFloat("Diem");
				SinhVienDAO svDAO = new SinhVienDAO();
				SinhVien sinhVien = svDAO.selectByID(maSinhVien);
				MonHocDAO monhocDAO = new MonHocDAO();
				MonHoc monHoc = monhocDAO.selectByID(maMonHoc);
				
				Diem Diem = new Diem(sinhVien,monHoc,diem);
				data.add(Diem);
			}

			// Bước 5:
			JDBCUtil.closeConnection(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}
	public ArrayList<Diem> selectByMSV(String msv) {
		ArrayList<Diem> data = new ArrayList<>();
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: tạo ra đối tượng statement
			String sql = "SELECT * FROM Diem WHERE MaSinhVien = ?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1,msv);
			// Bước 3: thực thi câu lệnh SQL
			System.out.println(sql);
			ResultSet rs = st.executeQuery();

			// Bước 4:
			while (rs.next()) {
				String maSinhVien = rs.getString("MaSinhVien");
				String maMonHoc = rs.getString("MaMonHoc");
				float diem = rs.getFloat("Diem");
				SinhVienDAO svDAO = new SinhVienDAO();
				SinhVien sinhVien = svDAO.selectByID(maSinhVien);
				MonHocDAO monhocDAO = new MonHocDAO();
				MonHoc monHoc = monhocDAO.selectByID(maMonHoc);
				
				Diem Diem = new Diem(sinhVien,monHoc,diem);
				data.add(Diem);
			}

			// Bước 5:
			JDBCUtil.closeConnection(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}
	
	public Diem selectByID(String msv,String mmh) {
		Diem result = null;
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: tạo ra đối tượng statement
			String sql = "SELECT * FROM Diem WHERE MaSinhVien = ? AND MaMonHoc = ?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, msv);
			st.setString(2,mmh);

			// Bước 3: thực thi câu lệnh SQL
			System.out.println(sql);
			ResultSet rs = st.executeQuery();
			// Bước 4:
			while (rs.next()) {
			String maSinhVien = rs.getString("MaSinhVien");
			String maMonHoc = rs.getString("MaMonHoc");
			float diem = rs.getFloat("Diem");
			SinhVienDAO svDAO = new SinhVienDAO();
			SinhVien sinhVien = svDAO.selectByID(maSinhVien);
			MonHocDAO monhocDAO = new MonHocDAO();
			MonHoc monHoc = monhocDAO.selectByID(maMonHoc);
							
			result = new Diem(sinhVien,monHoc,diem);
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
	public int insert(Diem t) {
			int ketQua = 0;
			if (this.selectByID(t.getSinhVien().getMaSinhVien(),t.getMonHoc().getMaMonHoc()) == null) {
				try {
					// Bước 1: tạo kết nối đến CSDL
					Connection con = JDBCUtil.getConnection();

					// Bước 2: tạo ra đối tượng statement
					String sql = "INSERT INTO Diem (MaSinhVien, MaMonHoc, Diem) " + " VALUES (?,?,?)";

					PreparedStatement st = con.prepareStatement(sql);
					st.setString(1, t.getSinhVien().getMaSinhVien());
					st.setString(2, t.getMonHoc().getMaMonHoc());
					st.setFloat(3, t.getDiem());

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
	public int insertAll(ArrayList<Diem> arr) {
		int dem = 0;
		for (Diem Diem : arr) {
			dem += insert(Diem);
		}
		return dem;
	}

	@Override
	public int delete(Diem t) {
		int ketQua = 0;
		if (this.selectByID(t.getSinhVien().getMaSinhVien(),t.getMonHoc().getMaMonHoc()) == null) {
			try {
				// Bước 1: tạo kết nối đến CSDL
				Connection con = JDBCUtil.getConnection();

				// Bước 2: tạo ra đối tượng statement
				String sql = "DELETE from Diem " + " WHERE MaSinhVien= ? AND MaMonHoc= ?";

				PreparedStatement st = con.prepareStatement(sql);
				st.setString(1, t.getSinhVien().getMaSinhVien());
				st.setString(2, t.getMonHoc().getMaMonHoc());

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
	public int deleteMany(ArrayList<Diem> arr) {
		int dem = 0;
		for (Diem Diem : arr) {
			dem += delete(Diem);
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
			String sql = "DELETE FROM Diem";

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
	public int update(Diem t) {
		int ketQua = 0;
	    try {
	        // Bước 1: tạo kết nối đến CSDL
	        Connection con = JDBCUtil.getConnection();
	        
	        // Bước 2: tạo ra đối tượng statement
	        String sql = "UPDATE Nganh "+
	                     "SET " +
	                     "Diem = ?"+
	                     " WHERE MaSinhVien = ? " +
	                     "AND MaMonHoc = ?";
	        
	        PreparedStatement st = con.prepareStatement(sql);
	        st.setFloat(1, t.getDiem());
			st.setString(2, t.getSinhVien().getMaSinhVien());
			st.setString(3, t.getMonHoc().getMaMonHoc());
	        
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
	public ArrayList<Diem> selectHK_MSV(String hk, String msv) {
		ArrayList<Diem> data = new ArrayList<>();
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: tạo ra đối tượng statement
			String sql = "SELECT DISTINCT MaSinhVien,diem.MaMonHoc,Diem " +
						"FROM  ctmonhoc " +
						"JOIN diem ON ctmonhoc.MaMonHoc = diem.MaMonHoc " + 
						"JOIN nganh_hocky ON ctmonhoc.MaNganh_HK = nganh_hocky.MaNganhHocKy "+
						"WHERE 1=1";
			if (hk != null && !hk.isEmpty()) {
				sql += " AND MaHocKy = '" + hk + "'";
	        }
			if (msv != null && !msv.isEmpty()) {
				sql += " AND MaSinhVien = '" + msv + "'";
	        }
			
			PreparedStatement st = con.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			// Bước 4:
			while (rs.next()) {
				String maSinhVien = rs.getString("MaSinhVien");
				String maMonHoc = rs.getString("MaMonHoc");
				float diem = rs.getFloat("Diem");
				SinhVienDAO svDAO = new SinhVienDAO();
				SinhVien sinhVien = svDAO.selectByID(maSinhVien);
				MonHocDAO monhocDAO = new MonHocDAO();
				MonHoc monHoc = monhocDAO.selectByID(maMonHoc);
				
				Diem Diem = new Diem(sinhVien,monHoc,diem);
				data.add(Diem);
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
	public Diem selectByID(String t) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static void main(String[] args) {
		DiemDAO d = new DiemDAO();
		ArrayList<Diem> dsDiem = d.selectHK_MSV(null, null);
		for (Diem diem : dsDiem) {
			System.out.println(diem.toString());
		}
		
	}
}
