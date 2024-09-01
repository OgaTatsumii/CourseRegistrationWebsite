package model;

import java.util.Objects;

public class CTMonHoc {
	private MonHoc monHoc;
	private Nganh_HocKy nganhHocKy;
	
	public CTMonHoc() {
		
	}

	public CTMonHoc(MonHoc monHoc, Nganh_HocKy nganhHocKy) {
		super();
		this.monHoc = monHoc;
		this.nganhHocKy = nganhHocKy;
	}

	public MonHoc getMonHoc() {
		return monHoc;
	}

	public void setMonHoc(MonHoc monHoc) {
		this.monHoc = monHoc;
	}

	public Nganh_HocKy getNganhHocKy() {
		return nganhHocKy;
	}

	public void setNganhHocKy(Nganh_HocKy nganhHocKy) {
		this.nganhHocKy = nganhHocKy;
	}

	@Override
	public int hashCode() {
		return Objects.hash(monHoc, nganhHocKy);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CTMonHoc other = (CTMonHoc) obj;
		return Objects.equals(monHoc, other.monHoc) && Objects.equals(nganhHocKy, other.nganhHocKy);
	}

	@Override
	public String toString() {
		return "CTMonHoc [monHoc=" + monHoc + ", nganhHocKy=" + nganhHocKy + "]";
	}
	
	
}
