package model;

import java.time.LocalDate;
import java.util.Objects;

public class CTNamHoc_HocKy {
	private String maNHHocKy;
	private NamHoc namHoc;
	private HocKy hocKy;
	private LocalDate thoiGianBatDau ;
	private LocalDate thoiGianKetThuc ;
	public CTNamHoc_HocKy () {
		super();
		// TODO Auto-generated constructor stub
		}
	public CTNamHoc_HocKy(String maNHHocKy, NamHoc namHoc,HocKy hocKy,LocalDate thoiGianBatDau,LocalDate thoiGianKetThuc) {
		super();
		this.maNHHocKy = maNHHocKy;
		this.namHoc = namHoc;
		this.hocKy = hocKy ;
		this.thoiGianBatDau = thoiGianBatDau;
		this.thoiGianKetThuc = thoiGianKetThuc;
	}
	public String getmaNHHocKy() {
		return maNHHocKy;
	}
	public void setmaNHHocKy(String maNHHocKy) {
		this.maNHHocKy = maNHHocKy;
	}
	public NamHoc getnamHoc() {
		return namHoc;
	}
	public void setnamHoc(NamHoc namHoc) {
		this.namHoc = namHoc;
	}
	public void sethocKy(HocKy hocKy) {
		this.hocKy = hocKy;
	}
	public HocKy gethocKy() {
		return hocKy;
	}
	public void setThoiGianBatDau(LocalDate ThoiGianBatDau) {
		this.thoiGianBatDau = ThoiGianBatDau;
	}
	public LocalDate getThoiGianBatDau() {
		return thoiGianBatDau;
	}
	public LocalDate getThoiGianKetThuc() {
		return thoiGianKetThuc;
	}
	public void setThoiGianKetThuc(LocalDate ThoiGianKetThuc) {
		this.thoiGianKetThuc = ThoiGianKetThuc;
	}
	@Override
	public int hashCode() {
		return Objects.hash(maNHHocKy, namHoc,hocKy,thoiGianBatDau,thoiGianKetThuc);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CTNamHoc_HocKy  other = (CTNamHoc_HocKy ) obj;
		return Objects.equals(maNHHocKy, other.maNHHocKy) && Objects.equals(namHoc, other.namHoc)
				 && Objects.equals(hocKy, other.hocKy)  && Objects.equals(thoiGianBatDau, other.thoiGianBatDau)
				 && Objects.equals(thoiGianKetThuc, other.thoiGianKetThuc);
	}
	@Override
	public String toString() {
		return "CTNamHoc_HocKy [maNHHocKy=" + getmaNHHocKy() + ", namHoc=" + getnamHoc() 
		+ ", hocKy=" + gethocKy() + ", thoiGianBatDau=" + getThoiGianBatDau()
		+ ", thoiGianKetThuc=" + getThoiGianKetThuc() + "]";
	}
	
	
}
