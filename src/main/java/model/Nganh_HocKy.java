package model;

import java.util.Objects;

public class Nganh_HocKy {
	private String maNganhHocKy;
	private Nganh nganh;
	private HocKy hocKy;
	
	public Nganh_HocKy(){
		
	}

	public Nganh_HocKy(String maNganhHocKy, Nganh nganh, HocKy hocKy) {
		super();
		this.maNganhHocKy = maNganhHocKy;
		this.nganh = nganh;
		this.hocKy = hocKy;
	}

	public String getMaNganhHocKy() {
		return maNganhHocKy;
	}

	public void setMaNganhHocKy(String maNganhHocKy) {
		this.maNganhHocKy = maNganhHocKy;
	}

	public Nganh getNganh() {
		return nganh;
	}

	public void setNganh(Nganh nganh) {
		this.nganh = nganh;
	}

	public HocKy getHocKy() {
		return hocKy;
	}

	public void setHocKy(HocKy hocKy) {
		this.hocKy = hocKy;
	}

	@Override
	public int hashCode() {
		return Objects.hash(hocKy, maNganhHocKy, nganh);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Nganh_HocKy other = (Nganh_HocKy) obj;
		return Objects.equals(hocKy, other.hocKy) && Objects.equals(maNganhHocKy, other.maNganhHocKy)
				&& Objects.equals(nganh, other.nganh);
	}

	@Override
	public String toString() {
		return "Nganh_HocKy [maNganhHocKy=" + maNganhHocKy + ", nganh=" + nganh + ", hocKy=" + hocKy + "]";
	}
	
	
}
