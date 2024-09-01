package model;

import java.time.LocalDate;
import java.util.Objects;

public class NhanVien {
	private String maNhanVien;
	private String hoTen;
	private LocalDate ngaySinh;
	private String gioiTinh;
	private String soDienThoai;
	private String CCCD;
	private String email;
	private QueQuan queQuan;
	private TaiKhoan taiKhoan;
	public NhanVien() {
		super();
		// TODO Auto-generated constructor stub
	}
	public NhanVien(String maNhanVien, String hoTen, LocalDate ngaySinh, String gioiTinh, String soDienThoai,
			String CCCD, String email, QueQuan queQuan, TaiKhoan taiKhoan) {
		super();
		this.maNhanVien = maNhanVien;
		this.hoTen = hoTen;
		this.ngaySinh = ngaySinh;
		this.gioiTinh = gioiTinh;
		this.soDienThoai = soDienThoai;
		this.CCCD = CCCD;
		this.email = email;
		this.queQuan = queQuan;
		this.taiKhoan = taiKhoan;
	}
	public String getMaNhanVien() {
		return maNhanVien;
	}
	public void setMaNhanVien(String maNhanVien) {
		this.maNhanVien = maNhanVien;
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
	public void setCCCD(String CCCD) {
		this.CCCD = CCCD;
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
	public TaiKhoan getTaiKhoan() {
		return taiKhoan;
	}
	public void setTaiKhoan(TaiKhoan taiKhoan) {
		this.taiKhoan = taiKhoan;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(CCCD, email, gioiTinh, hoTen, maNhanVien, ngaySinh, queQuan, soDienThoai, taiKhoan);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NhanVien other = (NhanVien) obj;
		return Objects.equals(CCCD, other.CCCD) && Objects.equals(email, other.email)
				&& Objects.equals(gioiTinh, other.gioiTinh) && Objects.equals(hoTen, other.hoTen)
				&& Objects.equals(maNhanVien, other.maNhanVien) && Objects.equals(ngaySinh, other.ngaySinh)
				&& Objects.equals(queQuan, other.queQuan) && Objects.equals(soDienThoai, other.soDienThoai)
				&& Objects.equals(taiKhoan, other.taiKhoan);
	}
	@Override
	public String toString() {
		return "NhanVien [maNhanVien=" + maNhanVien + ", hoTen=" + hoTen + ", ngaySinh=" + ngaySinh + ", gioiTinh="
				+ gioiTinh + ", soDienThoai=" + soDienThoai + ", CCCD=" + CCCD + ", email=" + email + ", queQuan="
				+ queQuan + ", taiKhoan=" + taiKhoan + "]";
	}
	
	
}
