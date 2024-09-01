package model;

import java.util.Objects;

import model.Khoa;

public class Nganh {
	private String maNganh;
	private String tenNganh;
	private int giaMotTinChi;
	private int soTinChi;
	private float thoiGianDaoTao;
	private Khoa khoa;
	public Nganh() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Nganh(String maNganh, String tenNganh, int giaMotTinChi, int soTinChi, float thoiGianDaoTao, Khoa khoa) {
		super();
		this.maNganh = maNganh;
		this.tenNganh = tenNganh;
		this.giaMotTinChi = giaMotTinChi;
		this.soTinChi = soTinChi;
		this.thoiGianDaoTao = thoiGianDaoTao;
		this.khoa = khoa;
	}
	public String getMaNganh() {
		return maNganh;
	}
	public void setMaNganh(String maNganh) {
		this.maNganh = maNganh;
	}
	public String getTenNganh() {
		return tenNganh;
	}
	public void setTenNganh(String tenNganh) {
		this.tenNganh = tenNganh;
	}
	public int getGiaMotTinChi() {
		return giaMotTinChi;
	}
	public void setGiaMotTinChi(int giaMotTinChi) {
		this.giaMotTinChi = giaMotTinChi;
	}
	public int getSoTinChi() {
		return soTinChi;
	}
	public void setSoTinChi(int soTinChi) {
		this.soTinChi = soTinChi;
	}
	public float getThoiGianDaoTao() {
		return thoiGianDaoTao;
	}
	public void setThoiGianDaoTao(float thoiGianDaoTao) {
		this.thoiGianDaoTao = thoiGianDaoTao;
	}
	public Khoa getKhoa() {
		return khoa;
	}
	public void setKhoa(Khoa khoa) {
		this.khoa = khoa;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(giaMotTinChi, khoa, maNganh, soTinChi, tenNganh, thoiGianDaoTao);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Nganh other = (Nganh) obj;
		return giaMotTinChi == other.giaMotTinChi && Objects.equals(khoa, other.khoa)
				&& Objects.equals(maNganh, other.maNganh) && soTinChi == other.soTinChi
				&& Objects.equals(tenNganh, other.tenNganh)
				&& Float.floatToIntBits(thoiGianDaoTao) == Float.floatToIntBits(other.thoiGianDaoTao);
	}
	@Override
	public String toString() {
		return "Nganh [maNganh=" + maNganh + ", tenNganh=" + tenNganh + ", giaMotTinChi=" + giaMotTinChi + ", soTinChi="
				+ soTinChi + ", thoiGianDaoTao=" + thoiGianDaoTao + ", khoa=" + khoa + "]";
	}

	
	
	

}
