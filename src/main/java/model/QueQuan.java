package model;

import java.util.Objects;

public class QueQuan {
	private String maTinh;
	private String tenTinh;
	public QueQuan() {
		
	}
	public QueQuan(String maTinh, String tenTinh) {
		super();
		this.maTinh = maTinh;
		this.tenTinh = tenTinh;
	}
	public String getMaTinh() {
		return maTinh;
	}
	public void setMaTinh(String maTinh) {
		this.maTinh = maTinh;
	}
	public String getTenTinh() {
		return tenTinh;
	}
	public void setTenTinh(String tenTinh) {
		this.tenTinh = tenTinh;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(maTinh, tenTinh);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		QueQuan other = (QueQuan) obj;
		return Objects.equals(maTinh, other.maTinh) && Objects.equals(tenTinh, other.tenTinh);
	}
	@Override
	public String toString() {
		return "QueQuan [maTinh=" + maTinh + ", tenTinh=" + tenTinh + "]";
	}
	
}
