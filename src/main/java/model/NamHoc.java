package model;

import java.util.Objects;

public class NamHoc {
	private String maNamHoc;
	private String tenNamHoc;
	public NamHoc(String maNamHoc, String tenNamHoc) {
		super();
		this.maNamHoc = maNamHoc;
		this.tenNamHoc = tenNamHoc;
	}
	public NamHoc() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getMaNamHoc() {
		return maNamHoc;
	}
	public void setMaNamHoc(String maNamHoc) {
		this.maNamHoc = maNamHoc;
	}
	public String getTenNamHoc() {
		return tenNamHoc;
	}
	public void setTenNamHoc(String tenNamHoc) {
		this.tenNamHoc = tenNamHoc;
	}
	@Override
	public int hashCode() {
		return Objects.hash(maNamHoc, tenNamHoc);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NamHoc other = (NamHoc) obj;
		return Objects.equals(maNamHoc, other.maNamHoc) && Objects.equals(tenNamHoc, other.tenNamHoc);
	}
	@Override
	public String toString() {
		return "NamHoc [maNamHoc=" + maNamHoc + ", tenNamHoc=" + tenNamHoc + "]";
	}
	
	
}
