package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.MonHoc;

public class Nganh_HKDAO {
	String MaNganhHocKy = "";
	public String selectByNganhHocKy(String maNganh, String maHocKy) {
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: tạo ra đối tượng statement
			String sql = "SELECT MaNganhHocKy "
					+ "FROM nganh_hocky "
					+ "WHERE MaNganh = ? AND MaHocKy = ? ;";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, maNganh);
			st.setString(2, maHocKy);
			// Bước 3: thực thi câu lệnh SQL
			System.out.println(sql);
			ResultSet rs = st.executeQuery();

			// Bước 4:
			while (rs.next()) {
				MaNganhHocKy = rs.getString("MaNganhHocKy");
			}

			// Bước 5:
			JDBCUtil.closeConnection(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return MaNganhHocKy;
	}
}
