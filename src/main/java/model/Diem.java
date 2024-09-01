package model;

import java.util.Objects;

public class Diem {
	private SinhVien sinhVien;
	private MonHoc monHoc;
	private float diem;
	public Diem() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Diem(SinhVien sinhVien, MonHoc monHoc, float diem) {
		super();
		this.sinhVien = sinhVien;
		this.monHoc = monHoc;
		this.diem = diem;
	}
	public SinhVien getSinhVien() {
		return sinhVien;
	}
	public void setSinhVien(SinhVien sinhVien) {
		this.sinhVien = sinhVien;
	}
	public MonHoc getMonHoc() {
		return monHoc;
	}
	public void setMonHoc(MonHoc monHoc) {
		this.monHoc = monHoc;
	}
	public float getDiem() {
		return diem;
	}
	public void setDiem(float diem) {
		this.diem = diem;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Diem other = (Diem) obj;
		return diem == other.diem && Objects.equals(monHoc, other.monHoc) && Objects.equals(sinhVien, other.sinhVien);
	}
	@Override
	public String toString() {
		return "Diem [sinhVien=" + sinhVien + ", monHoc=" + monHoc + ", diem=" + diem + "]";
	}
	
}
