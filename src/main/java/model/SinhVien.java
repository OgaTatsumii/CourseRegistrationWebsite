package model;

import java.time.LocalDate;
import java.util.Objects;

public class SinhVien {
	private String maSinhVien;
	private String hoTen;
	private LocalDate ngaySinh;
	private String gioiTinh;
	private String soDienThoai;
	private String CCCD;
	private String email;
	private String tamTru;
	private Lop lop;
	private QueQuan queQuan;
	private TaiKhoan taiKhoan;
	public SinhVien() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SinhVien(String maSinhVien, String hoTen, LocalDate ngaySinh, String gioiTinh, String soDienThoai,
			String cCCD, String email, String tamTru, Lop lop, QueQuan queQuan, TaiKhoan taiKhoan) {
		super();
		this.maSinhVien = maSinhVien;
		this.hoTen = hoTen;
		this.ngaySinh = ngaySinh;
		this.gioiTinh = gioiTinh;
		this.soDienThoai = soDienThoai;
		CCCD = cCCD;
		this.email = email;
		this.tamTru = tamTru;
		this.lop = lop;
		this.queQuan = queQuan;
		this.taiKhoan = taiKhoan;
	}
	public String getMaSinhVien() {
		return maSinhVien;
	}
	public void setMaSinhVien(String maSinhVien) {
		this.maSinhVien = maSinhVien;
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
	public String getTamTru() {
		return tamTru;
	}
	public void setTamTru(String tamTru) {
		this.tamTru = tamTru;
	}
	public Lop getLop() {
		return lop;
	}
	public void setLop(Lop lop) {
		this.lop = lop;
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
		return Objects.hash(CCCD, email, gioiTinh, hoTen, lop, maSinhVien, ngaySinh, queQuan, soDienThoai, taiKhoan,
				tamTru);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SinhVien other = (SinhVien) obj;
		return Objects.equals(CCCD, other.CCCD) && Objects.equals(email, other.email)
				&& Objects.equals(gioiTinh, other.gioiTinh) && Objects.equals(hoTen, other.hoTen)
				&& Objects.equals(lop, other.lop) && Objects.equals(maSinhVien, other.maSinhVien)
				&& Objects.equals(ngaySinh, other.ngaySinh) && Objects.equals(queQuan, other.queQuan)
				&& Objects.equals(soDienThoai, other.soDienThoai) && Objects.equals(taiKhoan, other.taiKhoan)
				&& Objects.equals(tamTru, other.tamTru);
	}
	@Override
	public String toString() {
		return "SinhVien [maSinhVien=" + maSinhVien + ", hoTen=" + hoTen + ", ngaySinh=" + ngaySinh + ", gioiTinh="
				+ gioiTinh + ", soDienThoai=" + soDienThoai + ", CCCD=" + CCCD + ", email=" + email + ", tamTru="
				+ tamTru + ", lop=" + lop + ", queQuan=" + queQuan + ", taiKhoan=" + taiKhoan + "]";
	}

	

}
