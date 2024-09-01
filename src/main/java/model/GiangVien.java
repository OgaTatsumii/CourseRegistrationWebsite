package model;

import java.time.LocalDate;
import java.util.Objects;

public class GiangVien {
	private String maGiangVien;
	private String hoTen;
	private LocalDate ngaySinh;
	private String gioiTinh;
	private String soDienThoai;
	private String CCCD;
	private String email;
	private QueQuan queQuan;
	
	public GiangVien() {
		super();
		// TODO Auto-generated constructor stub
	}
	public GiangVien(String maGiangVien, String hoTen, LocalDate ngaySinh, String gioiTinh, String soDienThoai,
			String cCCD, String email, QueQuan queQuan) {
		super();
		this.maGiangVien = maGiangVien;
		this.hoTen = hoTen;
		this.ngaySinh = ngaySinh;
		this.gioiTinh = gioiTinh;
		this.soDienThoai = soDienThoai;
		CCCD = cCCD;
		this.email = email;
		this.queQuan = queQuan;
	}
	public String getMaGiangVien() {
		return maGiangVien;
	}
	public void setMaGiangVien(String maGiangVien) {
		this.maGiangVien = maGiangVien;
	}
	public String getHoTen() {
		return hoTen;
	}
	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}
	public LocalDate getNgaySinh() {
		return ngaySinh;
	}
	public void setNgaySinh(LocalDate ngaySinh) {
		this.ngaySinh = ngaySinh;
	}
	public String getGioiTinh() {
		return gioiTinh;
	}
	public void setGioiTinh(String gioiTinh) {
		this.gioiTinh = gioiTinh;
	}
	public String getSoDienThoai() {
		return soDienThoai;
	}
	public void setSoDienThoai(String soDienThoai) {
		this.soDienThoai = soDienThoai;
	}
	public String getCCCD() {
		return CCCD;
	}
	public void setCCCD(String cCCD) {
		CCCD = cCCD;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public QueQuan getQueQuan() {
		return queQuan;
	}
	public void setQueQuan(QueQuan queQuan) {
		this.queQuan = queQuan;
	}
	
	
	@Override
	public int hashCode() {
		return Objects.hash(CCCD, email, gioiTinh, hoTen, maGiangVien, ngaySinh, queQuan, soDienThoai);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GiangVien other = (GiangVien) obj;
		return Objects.equals(CCCD, other.CCCD) && Objects.equals(email, other.email)
				&& Objects.equals(gioiTinh, other.gioiTinh) && Objects.equals(hoTen, other.hoTen)
				&& Objects.equals(maGiangVien, other.maGiangVien) && Objects.equals(ngaySinh, other.ngaySinh)
				&& Objects.equals(queQuan, other.queQuan) && Objects.equals(soDienThoai, other.soDienThoai);
	}
	@Override
	public String toString() {
		return "GiangVien [maGiangVien=" + maGiangVien + ", hoTen=" + hoTen + ", ngaySinh=" + ngaySinh + ", gioiTinh="
				+ gioiTinh + ", soDienThoai=" + soDienThoai + ", CCCD=" + CCCD + ", email=" + email + ", queQuan="
				+ queQuan + "]";
	}
	
}
