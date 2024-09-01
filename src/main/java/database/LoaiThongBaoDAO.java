package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.cj.protocol.Resultset;

import model.LoaiThongBao;

public class LoaiThongBaoDAO implements DAOInterface<LoaiThongBao> {

	private ArrayList<LoaiThongBao> data = new ArrayList<>();

	@Override
	public ArrayList<LoaiThongBao> selectAll() {
		try {
			Connection con = JDBCUtil.getConnection();
			String sql = "SELECT * FROM LoaiThongBao";
			PreparedStatement st = con.prepareStatement(sql);
			System.out.println(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				String maLoaiThongBao = rs.getString("MaLoaiThongBao");
				String tenLoaiThongBao = rs.getString("TenLoaiThongBao");
				LoaiThongBao LoaiThongBao = new LoaiThongBao(maLoaiThongBao, tenLoaiThongBao);
				data.add(LoaiThongBao);
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
	public LoaiThongBao selectByID(String t) {
		LoaiThongBao result = null;
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: tạo ra đối tượng statement
			String sql = "SELECT * FROM LoaiThongBao WHERE MaLoaiThongBao = ?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t);

			// Bước 3: thực thi câu lệnh SQL
			System.out.println(sql);
			ResultSet rs = st.executeQuery();

			// Bước 4:
			while (rs.next()) {
				String maLoaiThongBao = rs.getString("MaLoaiThongBao");
				String tenLoaiThongBao = rs.getString("TenLoaiThongBao");
				result = new LoaiThongBao(maLoaiThongBao, tenLoaiThongBao);
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
	public int insert(LoaiThongBao t) {
		int ketQua = 0;
		if (this.selectByID(t.getMaLoaiThongBao()) == null) {
			try {
				// Bước 1: tạo kết nối đến CSDL
				Connection con = JDBCUtil.getConnection();

				// Bước 2: tạo ra đối tượng statement
				String sql = "INSERT INTO LoaiThongBao (MaLoaiThongBao, TenLoaiThongBao) " + " VALUES (?,?)";

				PreparedStatement st = con.prepareStatement(sql);
				st.setString(1, t.getMaLoaiThongBao());
				st.setString(2, t.getTenLoaiThongBao());

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
	public int insertAll(ArrayList<LoaiThongBao> arr) {
		int dem = 0;
		for (LoaiThongBao LoaiThongBao : arr) {
			dem += insert(LoaiThongBao);
		}
		return dem;
	}

	@Override
	public int delete(LoaiThongBao t) {
		int ketQua = 0;
		if (this.selectByID(t.getMaLoaiThongBao()) != null) {
			try {
				// Bước 1: tạo kết nối đến CSDL
				Connection con = JDBCUtil.getConnection();

				// Bước 2: tạo ra đối tượng statement
				String sql = "DELETE from LoaiThongBao " + " WHERE MaLoaiThongBao=?";

				PreparedStatement st = con.prepareStatement(sql);
				st.setString(1, t.getMaLoaiThongBao());

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
	public int deleteMany(ArrayList<LoaiThongBao> arr) {
		int dem = 0;
		for (LoaiThongBao LoaiThongBao : arr) {
			dem += delete(LoaiThongBao);
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
			String sql = "DELETE FROM LoaiThongBao";

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
	public int update(LoaiThongBao t) {
		int ketQua = 0;
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: tạo ra đối tượng statement
			String sql = "UPDATE LoaiThongBao " + " SET " + " MaLoaiThongBao=?" + ", TenLoaiThongBao=?"
					+ " WHERE MaLoaiThongBao=?";

			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t.getMaLoaiThongBao());
			st.setString(2, t.getTenLoaiThongBao());
			st.setString(3, t.getMaLoaiThongBao());
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
		LoaiThongBaoDAO LoaiThongBaoDAO = new LoaiThongBaoDAO();

		/*
		 * ArrayList<LoaiThongBao> kq = LoaiThongBaoDAO.selectAll(); for (LoaiThongBao
		 * LoaiThongBao : kq) { System.out.println(LoaiThongBao.getMaLoaiThongBao() +
		 * ": " + LoaiThongBao.getTenLoaiThongBao() );
		 * 
		 * }
		 */

		//System.out.println(LoaiThongBaoDAO.selectByID("TBNB").toString());

		/*
		 * LoaiThongBao ltb=new LoaiThongBao("TBR","Thông báo riêng");
		 * LoaiThongBaoDAO.insert(ltb);
		 */

		
		  //LoaiThongBao ltb =new LoaiThongBao("TBR","update");
		  //LoaiThongBaoDAO.delete(ltb);
		 //LoaiThongBaoDAO.update(ltb);

	}
}
