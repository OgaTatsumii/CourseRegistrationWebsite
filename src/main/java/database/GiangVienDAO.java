package database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import com.mysql.cj.protocol.Resultset;

import model.GiangVien;
import model.Lop;
import model.QueQuan;
import model.SinhVien;
import model.TaiKhoan;
import database.QueQuanDAO;

public class GiangVienDAO implements DAOInterface<GiangVien> {

	private ArrayList<GiangVien> data = new ArrayList<>();

	@Override
	public ArrayList<GiangVien> selectAll() {
		ArrayList<GiangVien> data = new ArrayList<>();
		try {
			Connection con = JDBCUtil.getConnection();
			String sql = "SELECT * FROM GiangVien";
			PreparedStatement st = con.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				String maGiangVien = rs.getString("MaGiangVien");
				String hoTen = rs.getString("HoTen");				
				String nS= rs.getString("NgaySinh");
				String gioiTinh= rs.getString("GioiTinh");
				String sdt= rs.getString("SDT");
				String cccd= rs.getString("CCCD");
				String email= rs.getString("Email");
				String maTinh= rs.getString("MaTinh");
				
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				LocalDate ngaySinh = null;
				try {
		            // Chuyển đổi chuỗi thành LocalDate
		             ngaySinh = LocalDate.parse(nS, formatter);
		        } catch (Exception e) {
		            System.out.println("Không thể chuyển đổi chuỗi thành LocalDate: " + e.getMessage());
		        }
				
				QueQuan queQuan = (new QueQuanDAO().selectByID(maTinh));
				GiangVien giangVien=new GiangVien(maGiangVien,hoTen,ngaySinh,gioiTinh,sdt,cccd,email,queQuan);
				data.add(giangVien);
			}
			JDBCUtil.closeConnection(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}
	
	
	
	@Override
	public GiangVien selectByID(String t) {
		// TODO Auto-generated method stub
			GiangVien result = null;
		try {
			Connection con = JDBCUtil.getConnection();
			String sql = "SELECT * FROM GiangVien WHERE MaGiangVien = ?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				String maGiangVien = rs.getString("MaGiangVien");
				String hoTen = rs.getString("HoTen");				
				String nS= rs.getString("NgaySinh");
				String gioiTinh= rs.getString("GioiTinh");
				String sdt= rs.getString("SDT");
				String cccd= rs.getString("CCCD");
				String email= rs.getString("Email");
				String maTinh= rs.getString("MaTinh");
				
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				LocalDate ngaySinh = null;
				try {
		            // Chuyển đổi chuỗi thành LocalDate
		             ngaySinh = LocalDate.parse(nS, formatter);
		        } catch (Exception e) {
		            System.out.println("Không thể chuyển đổi chuỗi thành LocalDate: " + e.getMessage());
		        }
				
				QueQuan queQuan = (new QueQuanDAO().selectByID(maTinh));
				GiangVien giangVien=new GiangVien(maGiangVien,hoTen,ngaySinh,gioiTinh,sdt,cccd,email,queQuan);
				result =giangVien;
				break;
			}

			JDBCUtil.closeConnection(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int insert(GiangVien t) {
		int ketQua = 0;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		if (this.selectByID(t.getMaGiangVien()) == null) {
			try {
				Connection con = JDBCUtil.getConnection();
				String sql = "INSERT INTO GiangVien (MaGiangVien, HoTen, NgaySinh, GioiTinh, SDT, CCCD, Email, MaTinh"
						
						+ " VALUES (?,?,?,?,?,?,?,?)";

				PreparedStatement st = con.prepareStatement(sql);
				st.setString(1, t.getMaGiangVien());
				st.setString(2, t.getHoTen());
				st.setString(3, formatter.format(t.getNgaySinh()));
				st.setString(4, t.getGioiTinh());
				st.setString(5, t.getSoDienThoai());
				st.setString(6, t.getCCCD());
				st.setString(7, t.getEmail());
				st.setString(8, t.getQueQuan().getMaTinh());
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
	public int insertAll(ArrayList<GiangVien> arr) {
		// TODO Auto-generated method stub
		int dem = 0;
		for (GiangVien GiangVien : arr) {
			dem += insert(GiangVien);
		}
		return dem;
	}

	@Override
	public int delete(GiangVien t) {
		// TODO Auto-generated method stub
		int ketQua = 0;
		if (this.selectByID(t.getMaGiangVien()) != null) {
			try {
				Connection con = JDBCUtil.getConnection();
				String sql = "DELETE from GiangVien " + " WHERE MaGiangVien=?";
				PreparedStatement st = con.prepareStatement(sql);
				st.setString(1, t.getMaGiangVien());
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
	public int deleteMany(ArrayList<GiangVien> arr) {
		// TODO Auto-generated method stub
		int dem = 0;
		for (GiangVien GiangVien : arr) {
			dem += delete(GiangVien);
		}
		return dem;
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		int ketQua = 0;
		try {
			Connection con = JDBCUtil.getConnection();
			String sql = "DELETE FROM GiangVien";
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
	public int update(GiangVien t) {
		// TODO Auto-generated method stub
		int ketQua = 0;
		try {
			Connection con = JDBCUtil.getConnection();

			String sql = "UPDATE GiangVien " + "SET " + "HoTen = ?," + "NgaySinh = ?," + "GioiTinh = ?,"
					+ "SDT = ?," + "CCCD = ?," + "Email = ?," + "MaTinh = ? " + "WHERE MaGiangVien = ?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t.getHoTen());
			st.setString(2, t.getNgaySinh().toString());
			st.setString(3, t.getGioiTinh());
			st.setString(4, t.getSoDienThoai());
			st.setString(5, t.getCCCD());
			st.setString(6, t.getEmail());
			st.setString(7, t.getQueQuan().getMaTinh());
			st.setString(8, t.getMaGiangVien());
			ketQua = st.executeUpdate();

			System.out.println("Bạn đã thực thi: " + sql);
			System.out.println("Có " + ketQua + " dòng bị thay đổi!");
			JDBCUtil.closeConnection(con);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ketQua;
		
	
	}
	public ArrayList<GiangVien> search(String maGV,String tenGV,String gioitinh) {
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: tạo ra đối tượng statement
			String sql = "SELECT * FROM GiangVien WHERE 1=1";
			if (maGV != null && !maGV.isEmpty()) {
				sql += " AND MaGiangVien LIKE '" + maGV +"%"+ "'";
	        }
			if (tenGV != null && !tenGV.isEmpty()) {
				sql += " AND HoTen LIKE '"+"%" + tenGV + "%"+ "'";
	        }
			if (gioitinh != null && !gioitinh.isEmpty()) {
				sql += " AND GioiTinh = '" + gioitinh + "'";
	        }
			PreparedStatement st = con.prepareStatement(sql);

			// Bước 3: thực thi câu lệnh SQL
			System.out.println(sql);
			ResultSet rs = st.executeQuery();

			// Bước 4:
			while (rs.next()) {
				String maGiangVien = rs.getString("MaGiangVien");
				String hoTen = rs.getString("HoTen");
				Date ngaySinhDate = rs.getDate("NgaySinh");
				LocalDate ngaySinh = ngaySinhDate.toLocalDate();
				String gioiTinh = rs.getString("GioiTinh");
				String soDienThoai = rs.getString("SDT");
				String CCCD = rs.getString("CCCD");
				String email = rs.getString("Email");
				String maTinh = rs.getString("MaTinh");
				QueQuan queQuan = (new QueQuanDAO()).selectByID(maTinh);
			
				GiangVien GiangVien = new GiangVien(maGiangVien, hoTen, ngaySinh, gioiTinh, soDienThoai, CCCD, email, queQuan);
				data.add(GiangVien);
			}

			// Bước 5:
			JDBCUtil.closeConnection(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}
		
	public static void main(String[] args) {
		CTMonHocDAO ctmhd=new CTMonHocDAO();
		//System.out.println(ctmhd.selectAll());
		System.out.println(ctmhd.selectByType("ATTT6"));
//		MonHoc idSelect = MonHocDAO.selectByID("CNTTII");
//		System.out.println(idSelect);
	}
}
