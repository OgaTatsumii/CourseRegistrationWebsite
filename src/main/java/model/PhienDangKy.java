package model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import model.Khoa;
import model.CTNamHoc_HocKy;

public class PhienDangKy {
	private String maPhien;
	private Khoa khoa;
	private LocalDateTime thoiGianBatDau;
	private LocalDateTime thoiGianKetThuc;
	private CTNamHoc_HocKy ctNamHocHocKy;
	
	public PhienDangKy() {
		
	}
	
	public PhienDangKy(String maPhien, Khoa khoa, LocalDateTime thoiGianBatDau, LocalDateTime thoiGianKetThuc,
			CTNamHoc_HocKy ctNamHocHocKy) {
		this.maPhien = maPhien;
		this.khoa = khoa;
		this.thoiGianBatDau = thoiGianBatDau;
		this.thoiGianKetThuc = thoiGianKetThuc;
		this.ctNamHocHocKy = ctNamHocHocKy;
	}

	public String getMaPhien() {
		return maPhien;
	}

	public void setMaPhien(String maPhien) {
		this.maPhien = maPhien;
	}

	public Khoa getKhoa() {
		return khoa;
	}

	public void setKhoa(Khoa khoa) {
		this.khoa = khoa;
	}

	public LocalDateTime getThoiGianBatDau() {
		return thoiGianBatDau;
	}

	public void setThoiGianBatDau(LocalDateTime thoiGianBatDau) {
		this.thoiGianBatDau = thoiGianBatDau;
	}

	public LocalDateTime getThoiGianKetThuc() {
		return thoiGianKetThuc;
	}

	public void setThoiGianKetThuc(LocalDateTime thoiGianKetThuc) {
		this.thoiGianKetThuc = thoiGianKetThuc;
	}

	public CTNamHoc_HocKy getCtNamHocHocKy() {
		return ctNamHocHocKy;
	}

	public void setCtNamHocHocKy(CTNamHoc_HocKy ctNamHocHocKy) {
		this.ctNamHocHocKy = ctNamHocHocKy;
	}

	@Override
	public int hashCode() {
		return Objects.hash(ctNamHocHocKy, khoa, maPhien, thoiGianBatDau, thoiGianKetThuc);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PhienDangKy other = (PhienDangKy) obj;
		return Objects.equals(ctNamHocHocKy, other.ctNamHocHocKy) && Objects.equals(khoa, other.khoa)
				&& Objects.equals(maPhien, other.maPhien) && Objects.equals(thoiGianBatDau, other.thoiGianBatDau)
				&& Objects.equals(thoiGianKetThuc, other.thoiGianKetThuc);
	}

	@Override
	public String toString() {
		return "PhienDangKy [maPhien=" + maPhien + ", khoa=" + khoa + ", thoiGianBatDau=" + thoiGianBatDau
				+ ", thoiGianKetThuc=" + thoiGianKetThuc + ", ctNamHocHocKy=" + ctNamHocHocKy + "]";
	}
	
	
	
		
}
