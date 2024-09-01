package model;

import java.time.LocalDate;
import java.util.Objects;

public class KhoaHoc {
	private String maNamHoc;
	private LocalDate ngayBatDau;
	public KhoaHoc() {
		super();
		// TODO Auto-generated constructor stub
	}
	public KhoaHoc(String maNamHoc, LocalDate ngayBatDau) {
		super();
		this.maNamHoc = maNamHoc;
		this.ngayBatDau = ngayBatDau;
	}
	public String getMaNamHoc() {
		return maNamHoc;
	}
	public void setMaNamHoc(String maNamHoc) {
		this.maNamHoc = maNamHoc;
	}
	public LocalDate getNgayBatDau() {
		return ngayBatDau;
	}
	public void setNgayBatDau(LocalDate ngayBatDau) {
		this.ngayBatDau = ngayBatDau;
	}
	@Override
	public int hashCode() {
		return Objects.hash(maNamHoc, ngayBatDau);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		KhoaHoc other = (KhoaHoc) obj;
		return Objects.equals(maNamHoc, other.maNamHoc) && Objects.equals(ngayBatDau, other.ngayBatDau);
	}
	@Override
	public String toString() {
		return "KhoaHoc [maNamHoc=" + maNamHoc + ", ngayBatDau=" + ngayBatDau + "]";
	}
	
	
}
