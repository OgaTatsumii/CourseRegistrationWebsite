package model;

import java.time.LocalDate;
import java.util.Objects;

public class LopTinChi {
	private String maLopTinChi;
	private MonHoc monHoc;
	private GiangVien giangVien;
	private CTNamHoc_HocKy ctNamHoc_HocKy;
	private String tietBatDau;
	private String tietKetThuc;
	private LocalDate ngayBatDau;
	private LocalDate ngayKetThuc;
	private String thu;
	private String phong;
	private short soLuongSinhVien;
	private short daDangKy;
	
	public LopTinChi() {
		super();
	}
	
	public LopTinChi(String maLopTinChi, MonHoc monHoc, GiangVien giangVien, CTNamHoc_HocKy ctNamHoc_HocKy,
			String tietBatDau, String tietKetThuc, LocalDate ngayBatDau, LocalDate ngayKetThuc, String thu,
			String phong, short soLuongSinhVien, short daDangKy) {
		super();
		this.maLopTinChi = maLopTinChi;
		this.monHoc = monHoc;
		this.giangVien = giangVien;
		this.ctNamHoc_HocKy = ctNamHoc_HocKy;
		this.tietBatDau = tietBatDau;
		this.tietKetThuc = tietKetThuc;
		this.ngayBatDau = ngayBatDau;
		this.ngayKetThuc = ngayKetThuc;
		this.thu = thu;
		this.phong = phong;
		this.soLuongSinhVien = soLuongSinhVien;
		this.daDangKy = daDangKy;
	}

	public String getMaLopTinChi() {
		return maLopTinChi;
	}

	public void setMaLopTinChi(String maLopTinChi) {
		this.maLopTinChi = maLopTinChi;
	}

	public MonHoc getMonHoc() {
		return monHoc;
	}

	public void setMonHoc(MonHoc monHoc) {
		this.monHoc = monHoc;
	}

	public GiangVien getGiangVien() {
		return giangVien;
	}

	public void setGiangVien(GiangVien giangVien) {
		this.giangVien = giangVien;
	}

	public CTNamHoc_HocKy getCtNamHoc_HocKy() {
		return ctNamHoc_HocKy;
	}

	public void setCtNamHoc_HocKy(CTNamHoc_HocKy ctNamHoc_HocKy) {
		this.ctNamHoc_HocKy = ctNamHoc_HocKy;
	}

	public String getTietBatDau() {
		return tietBatDau;
	}

	public void setTietBatDau(String tietBatDau) {
		this.tietBatDau = tietBatDau;
	}

	public String getTietKetThuc() {
		return tietKetThuc;
	}

	public void setTietKetThuc(String tietKetThuc) {
		this.tietKetThuc = tietKetThuc;
	}

	public LocalDate getNgayBatDau() {
		return ngayBatDau;
	}

	public void setNgayBatDau(LocalDate ngayBatDau) {
		this.ngayBatDau = ngayBatDau;
	}

	public LocalDate getNgayKetThuc() {
		return ngayKetThuc;
	}

	public void setNgayKetThuc(LocalDate ngayKetThuc) {
		this.ngayKetThuc = ngayKetThuc;
	}

	public String getThu() {
		return thu;
	}

	public void setThu(String thu) {
		this.thu = thu;
	}

	public String getPhong() {
		return phong;
	}

	public void setPhong(String phong) {
		this.phong = phong;
	}

	public short getSoLuongSinhVien() {
		return soLuongSinhVien;
	}

	public void setSoLuongSinhVien(short soLuongSinhVien) {
		this.soLuongSinhVien = soLuongSinhVien;
	}

	public short getDaDangKy() {
		return daDangKy;
	}

	public void setDaDangKy(short daDangKy) {
		this.daDangKy = daDangKy;
	}

	@Override
	public int hashCode() {
		return Objects.hash(ctNamHoc_HocKy, daDangKy, giangVien, maLopTinChi, monHoc, ngayBatDau, ngayKetThuc, phong,
				soLuongSinhVien, thu, tietBatDau, tietKetThuc);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LopTinChi other = (LopTinChi) obj;
		return Objects.equals(ctNamHoc_HocKy, other.ctNamHoc_HocKy) && daDangKy == other.daDangKy
				&& Objects.equals(giangVien, other.giangVien) && Objects.equals(maLopTinChi, other.maLopTinChi)
				&& Objects.equals(monHoc, other.monHoc) && Objects.equals(ngayBatDau, other.ngayBatDau)
				&& Objects.equals(ngayKetThuc, other.ngayKetThuc) && Objects.equals(phong, other.phong)
				&& soLuongSinhVien == other.soLuongSinhVien && Objects.equals(thu, other.thu)
				&& Objects.equals(tietBatDau, other.tietBatDau) && Objects.equals(tietKetThuc, other.tietKetThuc);
	}

	@Override
	public String toString() {
		return "LopTinChi [maLopTinChi=" + maLopTinChi + ", monHoc=" + monHoc + ", giangVien=" + giangVien
				+ ", ctNamHoc_HocKy=" + ctNamHoc_HocKy + ", tietBatDau=" + tietBatDau + ", tietKetThuc=" + tietKetThuc
				+ ", ngayBatDau=" + ngayBatDau + ", ngayKetThuc=" + ngayKetThuc + ", thu=" + thu + ", phong=" + phong
				+ ", soLuongSinhVien=" + soLuongSinhVien + ", daDangKy=" + daDangKy + "]";
	}
	
	

	
	
}
