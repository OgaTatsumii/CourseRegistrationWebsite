package model;

import java.util.Objects;

public class HocKy {
	private String maHocKy;
	private String tenHocKy;
	public HocKy() {
		super();
		// TODO Auto-generated constructor stub
	}
	public HocKy(String maHocKy, String tenHocKy) {
		super();
		this.maHocKy = maHocKy;
		this.tenHocKy = tenHocKy;
	}
	public String getMaHocKy() {
		return maHocKy;
	}
	public void setMaHocKy(String maHocKy) {
		this.maHocKy = maHocKy;
	}
	public String getTenHocKy() {
		return tenHocKy;
	}
	public void setTenHocKy(String tenHocKy) {
		this.tenHocKy = tenHocKy;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(maHocKy, tenHocKy);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HocKy other = (HocKy) obj;
		return Objects.equals(maHocKy, other.maHocKy) && Objects.equals(tenHocKy, other.tenHocKy);
	}
	@Override
	public String toString() {
		return "HocKy [maHocKy=" + maHocKy + ", tenHocKy=" + tenHocKy + "]";
	}
	
	
}
