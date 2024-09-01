package model;

import java.util.Objects;

public class MonHoc {
	private String maMonHoc;
	private String tenMonHoc;
	private int soTinChi;
	public MonHoc() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MonHoc(String maMonHoc, String tenMonHoc, int soTinChi) {
		this.maMonHoc = maMonHoc;
		this.tenMonHoc = tenMonHoc;
		this.soTinChi = soTinChi;
	}
	public String getMaMonHoc() {
		return maMonHoc;
	}
	public void setMaMonHoc(String maMonHoc) {
		this.maMonHoc = maMonHoc;
	}
	public String getTenMonHoc() {
		return tenMonHoc;
	}
	public void setTenMonHoc(String tenMonHoc) {
		this.tenMonHoc = tenMonHoc;
	}
	public int getSoTinChi() {
		return soTinChi;
	}
	public void setSoTinChi(int soTinChi) {
		this.soTinChi = soTinChi;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(maMonHoc, soTinChi, tenMonHoc);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MonHoc other = (MonHoc) obj;
		return Objects.equals(maMonHoc, other.maMonHoc) && soTinChi == other.soTinChi
				&& Objects.equals(tenMonHoc, other.tenMonHoc);
	}
	@Override
	public String toString() {
		return "MonHoc [maMonHoc=" + maMonHoc + ", tenMonHoc=" + tenMonHoc + ", soTinChi=" + soTinChi + "]";
	}
	
	
	
}
