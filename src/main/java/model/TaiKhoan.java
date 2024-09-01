package model;

import java.util.Objects;

public class TaiKhoan {
	private String userName;
	private String matKhau;
	private ChucVu chucVu;
	public TaiKhoan() {
		
	}

	public TaiKhoan(String userName, String matKhau, ChucVu chucVu) {
		super();
		this.userName = userName;
		this.matKhau = matKhau;
		this.chucVu = chucVu;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getMatKhau() {
		return matKhau;
	}
	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}
	public ChucVu getChucVu() {
		return chucVu;
	}
	public void setChucVu(ChucVu chucVu) {
		this.chucVu = chucVu;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(chucVu, matKhau, userName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TaiKhoan other = (TaiKhoan) obj;
		return Objects.equals(chucVu, other.chucVu) && Objects.equals(matKhau, other.matKhau)
				&& Objects.equals(userName, other.userName);
	}

	@Override
	public String toString() {
		return "TaiKhoanNhanVien [userName=" + userName + ", matKhau=" + matKhau + ", chucVu=" + chucVu + "]";
	}
	
	
}
