
package database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import com.mysql.cj.protocol.Resultset;

import model.CTMonHoc;
import model.MonHoc;
import model.Nganh_HocKy;
import model.SinhVien;
import database.Nganh_HocKyDAO;
import database.MonHocDAO;

public class CTMonHocDAO implements DAOInterface<CTMonHoc> {

	private ArrayList<CTMonHoc> data = new ArrayList<>();

	@Override
	public ArrayList<CTMonHoc> selectAll() {
		ArrayList<CTMonHoc> data = new ArrayList<>();
		try {
			Connection con = JDBCUtil.getConnection();
			String sql = "SELECT * FROM CTMonHoc";
			PreparedStatement st = con.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				String maMonHoc = rs.getString("MaMonHoc");
				String maNganh_Hk = rs.getString("MaNganh_HK");
				MonHoc monHoc = (new MonHocDAO().selectByID(maMonHoc));
				Nganh_HocKy nganhHocKy = (new Nganh_HocKyDAO().selectByID(maNganh_Hk));

				CTMonHoc ctMonHoc = new CTMonHoc(monHoc, nganhHocKy);
				data.add(ctMonHoc);
			}
			JDBCUtil.closeConnection(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}

	public ArrayList<CTMonHoc> selectByType(String t) {
		// NoiDungThongBao result = null;
		ArrayList<CTMonHoc> data = new ArrayList<>();
		try {
			Connection con = JDBCUtil.getConnection();
			String sql = "SELECT * FROM CTMonHoc WHERE MaNganh_HK = ?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				String maMonHoc = rs.getString("MaMonHoc");
				String maNganh_Hk = rs.getString("MaNganh_HK");
				MonHoc monHoc = (new MonHocDAO().selectByID(maMonHoc));
				Nganh_HocKy nganhHocKy = (new Nganh_HocKyDAO().selectByID(maNganh_Hk));
				CTMonHoc ctMonHoc = new CTMonHoc(monHoc, nganhHocKy);
				data.add(ctMonHoc);
			}
			JDBCUtil.closeConnection(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}

	@Override
	public CTMonHoc selectByID(String t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insert(CTMonHoc t) {
		int ketQua = 0;
		if (this.selectByID(t.getNganhHocKy().getMaNganhHocKy()) == null) {
			try {
				Connection con = JDBCUtil.getConnection();

				String sql = "INSERT INTO CTMonHoc (MaMonHoc, MaNganh_HK)" + " VALUES (?,?)";

				PreparedStatement st = con.prepareStatement(sql);
				st.setString(1, t.getMonHoc().getMaMonHoc());
				st.setString(2, t.getNganhHocKy().getMaNganhHocKy());

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
	public int insertAll(ArrayList<CTMonHoc> arr) {
		// TODO Auto-generated method stub
		int dem = 0;
		for (CTMonHoc CTMonHoc : arr) {
			dem += insert(CTMonHoc);
		}
		return dem;
	}

	@Override
	public int delete(CTMonHoc t) {
		// TODO Auto-generated method stub
		int ketQua = 0;
		if (this.selectByID(t.getMonHoc().getMaMonHoc()) != null) {
			try {
				Connection con = JDBCUtil.getConnection();
				String sql = "DELETE from CTMonHoc " + " WHERE MaMonHoc=? and MaNganh_HK=?";

				PreparedStatement st = con.prepareStatement(sql);
				st.setString(1, t.getMonHoc().getMaMonHoc());
				st.setString(1, t.getNganhHocKy().getMaNganhHocKy());

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

	public int deleteByNganhHk(String MaNganh_HK, String MaMon) {
		// TODO Auto-generated method stub
		int ketQua = 0;
		try {
			Connection con = JDBCUtil.getConnection();
			String sql = "DELETE from CTMonHoc " + " WHERE MaMonHoc=? and MaNganh_HK=?";

			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, MaMon);
			st.setString(2, MaNganh_HK);

			ketQua = st.executeUpdate();

			System.out.println("Bạn đã thực thi: " + sql);
			System.out.println("Có " + ketQua + " dòng bị thay đổi!");

			JDBCUtil.closeConnection(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ketQua;
	}

	@Override
	public int deleteMany(ArrayList<CTMonHoc> arr) {
		// TODO Auto-generated method stub
		int dem = 0;
		for (CTMonHoc CTMonHoc : arr) {
			dem += delete(CTMonHoc);
		}
		return dem;
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		int ketQua = 0;
		try {
			Connection con = JDBCUtil.getConnection();
			String sql = "DELETE FROM CTMonHoc";

			PreparedStatement st = con.prepareStatement(sql);
			System.out.println(sql);
			ketQua = st.executeUpdate();

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
	public int update(CTMonHoc t) {
		// TODO Auto-generated method stub
		// update môn học cho ngành-học kì
		int ketQua = 0;
		try {
			Connection con = JDBCUtil.getConnection();

			String sql = "UPDATE CTMonHoc " + "SET " + "MaMonHoc = ?," + "MaNganh_HK = ?" + "WHERE MaMonHoc = ?";

			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t.getMonHoc().getMaMonHoc());
			st.setString(2, t.getNganhHocKy().getMaNganhHocKy());

			ketQua = st.executeUpdate();

			System.out.println("Bạn đã thực thi: " + sql);
			System.out.println("Có " + ketQua + " dòng bị thay đổi!");
			JDBCUtil.closeConnection(con);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ketQua;

	}

	public static void main(String[] args) {
		CTMonHocDAO ctmhd = new CTMonHocDAO();
		// System.out.println(ctmhd.selectAll());
		System.out.println(ctmhd.selectByType("ATTT6"));
//		MonHoc idSelect = MonHocDAO.selectByID("CNTTII");
//		System.out.println(idSelect);
	}
}
