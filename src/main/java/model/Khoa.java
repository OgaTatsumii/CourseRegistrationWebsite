package model;

import java.util.Objects;

public class Khoa {
	private String maKhoa;
	private String tenKhoa;

	public Khoa() {

	}

	public Khoa(String maKhoa, String tenKhoa) {
		this.maKhoa = maKhoa;
		this.tenKhoa = tenKhoa;
	}

	public String getMaKhoa() {
		return maKhoa;
	}

	public void setMaKhoa(String maKhoa) {
		this.maKhoa = maKhoa;
	}

	public String getTenKhoa() {
		return tenKhoa;
	}

	public void setTenKhoa(String tenKhoa) {
		this.tenKhoa = tenKhoa;
	}

	@Override
	public int hashCode() {
		return Objects.hash(maKhoa, tenKhoa);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Khoa other = (Khoa) obj;
		return Objects.equals(maKhoa, other.maKhoa) && Objects.equals(tenKhoa, other.tenKhoa);
	}

	@Override
	public String toString() {
		return "Khoa [maKhoa: " + maKhoa + ", tenKhoa: " + tenKhoa + "]";
	}
	
	
}
