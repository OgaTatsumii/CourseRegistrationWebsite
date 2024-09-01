package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Nganh;
import model.Nganh_HocKy;
import model.HocKy;

public class Nganh_HocKyDAO implements DAOInterface<Nganh_HocKy>  {
private ArrayList<Nganh_HocKy> data =new ArrayList<>();
	
	@Override
	public ArrayList<Nganh_HocKy> selectAll() {
		ArrayList<Nganh_HocKy> data = new ArrayList<>();
		try {
			Connection con = JDBCUtil.getConnection();
			String sql = "SELECT * FROM Nganh_HocKy";
			PreparedStatement st = con.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				String mnhk = rs.getString("MaNganhHocKy");
				String mn = rs.getString("MaNganh");
				String mhk = rs.getString("MaHocKy");
				
				Nganh nganh = (new NganhDAO().selectByID(mn));
				HocKy hk= (new HocKyDAO().selectByID(mhk));				
				Nganh_HocKy nganh_hocky =new Nganh_HocKy(mnhk,nganh,hk);
				data.add(nganh_hocky);
			}

			JDBCUtil.closeConnection(con);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return data;
	}
	
	@Override
	public Nganh_HocKy selectByID(String t) {		
		Nganh_HocKy result = null;
		try {
			Connection con = JDBCUtil.getConnection();
			String sql = "SELECT * FROM Nganh_HocKy WHERE MaNganhHocKy = ?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				String mnhk = rs.getString("MaNganhHocKy");
				String mn = rs.getString("MaNganh");
				String mhk = rs.getString("MaHocKy");
				
				Nganh nganh = (new NganhDAO().selectByID(mn));
				HocKy hk= (new HocKyDAO().selectByID(mhk));				
				Nganh_HocKy nganh_hocky =new Nganh_HocKy(mnhk,nganh,hk);
				result=nganh_hocky;
			}

			// Bước 5:
			JDBCUtil.closeConnection(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public Nganh_HocKy selectByNganhHK(String maNganh, String maHK) {		
		Nganh_HocKy result = null;
		try {
			Connection con = JDBCUtil.getConnection();
			String sql = "SELECT * FROM Nganh_HocKy WHERE MaNganh = ? AND MaHocKy= ?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, maNganh);
			st.setString(2, maHK);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				String mnhk = rs.getString("MaNganhHocKy");
				String mn = rs.getString("MaNganh");
				String mhk = rs.getString("MaHocKy");
				
				Nganh nganh = (new NganhDAO().selectByID(mn));
				HocKy hk= (new HocKyDAO().selectByID(mhk));				
				Nganh_HocKy nganh_hocky =new Nganh_HocKy(mnhk,nganh,hk);
				result=nganh_hocky;
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
	public int insert(Nganh_HocKy t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertAll(ArrayList<Nganh_HocKy> arr) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Nganh_HocKy t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteMany(ArrayList<Nganh_HocKy> arr) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int update(Nganh_HocKy t) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	public static void main(String[] args) {
		
		Nganh_HocKyDAO nhkdao=new Nganh_HocKyDAO();
		
		//System.out.println(nhkdao.selectAll().toString());
		//System.out.println(nhkdao.selectByID("CNDPT1"));
	}
}
