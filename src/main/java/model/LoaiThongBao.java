package model;

import java.util.Objects;

public class LoaiThongBao {
	private String maLoaiThongBao;
	private String tenLoaiThongBao;
	
	public LoaiThongBao() {
	}
	
	public LoaiThongBao(String maLoaiThongBao, String tenLoaiThongBao) {
		this.maLoaiThongBao = maLoaiThongBao;
		this.tenLoaiThongBao = tenLoaiThongBao;
	}

	public String getMaLoaiThongBao() {
		return maLoaiThongBao;
	}

	public void setMaLoaiThongBao(String maLoaiThongBao) {
		this.maLoaiThongBao = maLoaiThongBao;
	}

	public String getTenLoaiThongBao() {
		return tenLoaiThongBao;
	}

	public void setTenLoaiThongBao(String tenLoaiThongBao) {
		this.tenLoaiThongBao = tenLoaiThongBao;
	}

	
	@Override
	public int hashCode() {
		return Objects.hash(maLoaiThongBao, tenLoaiThongBao);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LoaiThongBao other = (LoaiThongBao) obj;
		return Objects.equals(maLoaiThongBao, other.maLoaiThongBao)
				&& Objects.equals(tenLoaiThongBao, other.tenLoaiThongBao);
	}

	@Override
	public String toString() {
		return "LoaiThongBao [maLoaiThongBao=" + maLoaiThongBao + ", tenLoaiThongBao=" + tenLoaiThongBao + "]";
	}
	
}
