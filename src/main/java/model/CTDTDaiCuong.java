package model;

import java.util.Objects;

public class CTDTDaiCuong {
	private String maCTDTDaiCuong;
	private Khoa khoa;
	private MonHoc monHoc;
	private int soTinChi;
	private int hocKy;

	public CTDTDaiCuong() {
		
	}

	public CTDTDaiCuong(String maCTDTDaiCuong, Khoa khoa, MonHoc monHoc, int soTinChi, int hocKy) {
		super();
		this.maCTDTDaiCuong = maCTDTDaiCuong;
		this.khoa = khoa;
		this.monHoc = monHoc;
		this.soTinChi = soTinChi;
		this.hocKy = hocKy;
	}

	public String getMaCTDTDaiCuong() {
		return maCTDTDaiCuong;
	}

	public void setMaCTDTDaiCuong(String maCTDTDaiCuong) {
		this.maCTDTDaiCuong = maCTDTDaiCuong;
	}

	public Khoa getKhoa() {
		return khoa;
	}

	public void setKhoa(Khoa khoa) {
		this.khoa = khoa;
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
		return Objects.hash(hocKy, khoa, maCTDTDaiCuong, monHoc, soTinChi);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CTDTDaiCuong other = (CTDTDaiCuong) obj;
		return hocKy == other.hocKy && Objects.equals(khoa, other.khoa)
				&& Objects.equals(maCTDTDaiCuong, other.maCTDTDaiCuong) && Objects.equals(monHoc, other.monHoc)
				&& soTinChi == other.soTinChi;
	}

	@Override
	public String toString() {
		return "CTDTDaiCuong [maCTDTDaiCuong=" + maCTDTDaiCuong + ", khoa=" + khoa + ", monHoc=" + monHoc
				+ ", soTinChi=" + soTinChi + ", hocKy=" + hocKy + "]";
	}

}
