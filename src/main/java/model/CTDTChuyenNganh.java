package model;

import java.util.Objects;

public class CTDTChuyenNganh {
	private String maCTDTChuyenNganh;
	private Nganh nganh;
	private MonHoc monHoc;
	private int soTinChi;
	private int hocKy;
	public CTDTChuyenNganh() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CTDTChuyenNganh(String maCTDTChuyenNganh, Nganh nganh, MonHoc monHoc, int soTinChi, int hocKy) {
		super();
		this.maCTDTChuyenNganh = maCTDTChuyenNganh;
		this.nganh = nganh;
		this.monHoc = monHoc;
		this.soTinChi = soTinChi;
		this.hocKy = hocKy;
	}
	public String getMaCTDTChuyenNganh() {
		return maCTDTChuyenNganh;
	}
	public void setMaCTDTChuyenNganh(String maCTDTChuyenNganh) {
		this.maCTDTChuyenNganh = maCTDTChuyenNganh;
	}
	public Nganh getNganh() {
		return nganh;
	}
	public void setNganh(Nganh nganh) {
		this.nganh = nganh;
	}
	public MonHoc getMonHoc() {
		return monHoc;
	}
	public void setMonHoc(MonHoc monHoc) {
		this.monHoc = monHoc;
	}
	public int getSoTinChi() {
		return soTinChi;
	}
	public void setSoTinChi(int soTinChi) {
		this.soTinChi = soTinChi;
	}
	public int getHocKy() {
		return hocKy;
	}
	public void setHocKy(int hocKy) {
		this.hocKy = hocKy;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(hocKy, maCTDTChuyenNganh, monHoc, nganh, soTinChi);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CTDTChuyenNganh other = (CTDTChuyenNganh) obj;
		return hocKy == other.hocKy && Objects.equals(maCTDTChuyenNganh, other.maCTDTChuyenNganh)
				&& Objects.equals(monHoc, other.monHoc) && Objects.equals(nganh, other.nganh)
				&& soTinChi == other.soTinChi;
	}
	@Override
	public String toString() {
		return "CTDTChuyenNganh [maCTDTChuyenNganh=" + maCTDTChuyenNganh + ", nganh=" + nganh + ", monHoc=" + monHoc
				+ ", soTinChi=" + soTinChi + ", hocKy=" + hocKy + "]";
	}
	
	
}
