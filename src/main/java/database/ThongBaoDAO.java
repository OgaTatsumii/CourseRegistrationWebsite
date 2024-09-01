package database;

import java.sql.Connection;
//import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLData;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.SimpleFormatter;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.time.LocalDateTime;

import com.mysql.cj.protocol.Resultset;

import model.NoiDungThongBao;
import model.LoaiThongBao;


public class ThongBaoDAO implements DAOInterface<NoiDungThongBao> {

	private ArrayList<NoiDungThongBao> data = new ArrayList<>();

	@Override
	public ArrayList<NoiDungThongBao> selectAll() {
		ArrayList<NoiDungThongBao> data = new ArrayList<>();
		try {
			Connection con = JDBCUtil.getConnection();
			String sql = "SELECT * FROM NoiDungThongBao";
			PreparedStatement st = con.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				int maNoiDungThongBao =rs.getInt("MaNoiDungThongBao");
				String tieuDe =rs.getString("TieuDe");
				String noiDungThongBao = rs.getString("NoiDung");
				Date ngayThongBao = rs.getDate("NgayThongBao");
				String maLoaiThongBao = rs.getString("MaLoaiThongBao");
				LoaiThongBao loaiThongBao = (new LoaiThongBaoDAO()).selectByID(maLoaiThongBao);
				
				NoiDungThongBao ndtb = new NoiDungThongBao(maNoiDungThongBao,tieuDe,noiDungThongBao, ngayThongBao, loaiThongBao);
				data.add(ndtb);
			}

			JDBCUtil.closeConnection(con);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return data;
	}

	@Override
	public NoiDungThongBao selectByID(String t) {
		
		NoiDungThongBao result = null;
		try {
			Connection con = JDBCUtil.getConnection();

			// Bước 2: tạo ra đối tượng statement
			String sql = "SELECT * FROM NoiDungThongBao WHERE MaNoiDungThongBao = ?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, Integer.parseInt(t));

			// Bước 3: thực thi câu lệnh SQL
			System.out.println(sql);
			ResultSet rs = st.executeQuery();

			// Bước 4:
			while (rs.next()) {
				int maNoiDungThongBao =rs.getInt("MaNoiDungThongBao");
				String tieuDe =rs.getString("TieuDe");
				String noiDungThongBao = rs.getString("NoiDung");
				Date ngayThongBao = rs.getDate("NgayThongBao");
				String maLoaiThongBao = rs.getString("MaLoaiThongBao");
				LoaiThongBao loaiThongBao = (new LoaiThongBaoDAO()).selectByID(maLoaiThongBao);
				
				result = new NoiDungThongBao(Integer.parseInt(t),tieuDe,noiDungThongBao, ngayThongBao, loaiThongBao);
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
	
	public ArrayList<NoiDungThongBao> selectByType(String t) {

		//NoiDungThongBao result = null;
		ArrayList<NoiDungThongBao> data = new ArrayList<>();
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: tạo ra đối tượng statement
			String sql = "SELECT * FROM NoiDungThongBao WHERE MaLoaiThongBao = ?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t);

			// Bước 3: thực thi câu lệnh SQL
			System.out.println(sql);
			ResultSet rs = st.executeQuery();

			// Bước 4:
			while (rs.next()) {
				int maNoiDungThongBao =rs.getInt("MaNoiDungThongBao");
				String tieuDe =rs.getString("TieuDe");
				String noiDungThongBao = rs.getString("NoiDung");
				Date ngayThongBao = rs.getDate("NgayThongBao");
				LoaiThongBao loaiThongBao = (new LoaiThongBaoDAO()).selectByID(t);
				
				NoiDungThongBao ndtb= new NoiDungThongBao(tieuDe,noiDungThongBao, ngayThongBao, loaiThongBao);	
				data.add(ndtb);
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
	public int insert(NoiDungThongBao t) {
		int ketQua = 0;		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			try {				
				// Bước 1: tạo kết nối đến CSDL
				Connection con = JDBCUtil.getConnection();

				// Bước 2: tạo ra đối tượng statement
				String sql = "INSERT INTO NoiDungThongBao (TieuDe,NoiDung, NgayThongBao, MaLoaiThongBao)"
						+ " VALUES (?,?,?,?)";

				PreparedStatement st = con.prepareStatement(sql);
				st.setString(1, t.getTieuDe());
				st.setString(2, t.getNoiDung());
				st.setString(3, formatter.format(t.getThoiGianThongBao()));
				st.setString(4, t.getLtb().getMaLoaiThongBao());
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
		
		return ketQua;
	}

	@Override
	public int insertAll(ArrayList<NoiDungThongBao> arr) {
		int dem = 0;
		for (NoiDungThongBao NoiDungThongBao : arr) {
			dem += insert(NoiDungThongBao);
		}
		return dem;
	}

	@Override
	public int delete(NoiDungThongBao t) {
		int ketQua = 0;	
			try {
				// Bước 1: tạo kết nối đến CSDL
				Connection con = JDBCUtil.getConnection();

				// Bước 2: tạo ra đối tượng statement
				String sql = "DELETE from NoiDungThongBao " + " WHERE MaNoiDungThongBao=?";

				PreparedStatement st = con.prepareStatement(sql);
				st.setInt(1, t.getMaNoiDungThongBao());

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

	@Override
	public int deleteMany(ArrayList<NoiDungThongBao> arr) {
		int dem = 0;
		for (NoiDungThongBao NoiDungThongBao : arr) {
			dem += delete(NoiDungThongBao);
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
			String sql = "DELETE FROM NoiDungThongBao";

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
	public int update(NoiDungThongBao t) {
		int ketQua = 0;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Connection con = JDBCUtil.getConnection();
			// Bước 2: tạo ra đối tượng statement
			String sql = "UPDATE NoiDungThongBao " + "SET " +"TieuDe = ? ,"+ "NoiDung = ? ," + "NgayThongBao = ? ," + "MaLoaiThongBao = ? "
					+ "WHERE MaNoiDungThongBao = ? ";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t.getTieuDe());
			st.setString(2, t.getNoiDung());
			st.setString(3, formatter.format(t.getThoiGianThongBao()));
			st.setString(4, t.getLtb().getMaLoaiThongBao());
			st.setInt(5, t.getMaNoiDungThongBao());
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
	
	public static void main(String[] args) {
		
		ThongBaoDAO tbd=new ThongBaoDAO();
		LoaiThongBaoDAO ltbd=new LoaiThongBaoDAO();
		//System.out.println(tbd.selectAll().toString());
		//System.out.println(tbd.selectByType("TBNB").toString());

		Date now = new Date();
		
		//System.out.print(tbd.update(ndtb));
//		NoiDungThongBao ndtb=new NoiDungThongBao(7);
		tbd.delete(tbd.selectByID("14"));;
	}
	
}

