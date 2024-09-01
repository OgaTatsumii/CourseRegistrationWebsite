package model;

import java.util.Objects;
import java.util.Date;

public class NoiDungThongBao {
	private int maNoiDungThongBao;
	private String tieuDe;
	private String noiDung;
	private Date thoiGianThongBao;
	private LoaiThongBao ltb;
	
	public NoiDungThongBao() {
		
	}
	public NoiDungThongBao(int maNoiDungThongBao){
		this.maNoiDungThongBao=maNoiDungThongBao;
	}

	public NoiDungThongBao( String tieuDe,String noiDung, Date thoiGianThongBao, LoaiThongBao ltb) {		
		this.tieuDe=tieuDe;
		this.noiDung = noiDung;
		this.thoiGianThongBao = thoiGianThongBao;
		this.ltb = ltb;
	}
	
	public NoiDungThongBao( int maNoiDungThongBao,String tieuDe,String noiDung, Date thoiGianThongBao, LoaiThongBao ltb) {		
		this.maNoiDungThongBao=maNoiDungThongBao;
		this.tieuDe=tieuDe;
		this.noiDung = noiDung;
		this.thoiGianThongBao = thoiGianThongBao;
		this.ltb = ltb;
	}

	public int getMaNoiDungThongBao() {
		return maNoiDungThongBao;
	}

	public void setMaNoiDungThongBao(int maNoiDungThongBao) {
		this.maNoiDungThongBao = maNoiDungThongBao;
	}

	public String getNoiDung() {
		return noiDung;
	}

	public void setNoiDung(String noiDung) {
		this.noiDung = noiDung;
	}

	public Date getThoiGianThongBao() {
		return thoiGianThongBao;
	}

	public void setThoiGianThongBao(Date thoiGianThongBao) {
		this.thoiGianThongBao = thoiGianThongBao;
	}

	public LoaiThongBao getLtb() {
		return ltb;
	}

	public void setLtb(LoaiThongBao ltb) {
		this.ltb = ltb;
	}

	public String getTieuDe() {
		return tieuDe;
	}

	public void setTieuDe(String tieuDe) {
		this.tieuDe = tieuDe;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(ltb, maNoiDungThongBao, noiDung, thoiGianThongBao, tieuDe);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NoiDungThongBao other = (NoiDungThongBao) obj;
		return Objects.equals(ltb, other.ltb) && maNoiDungThongBao == other.maNoiDungThongBao
				&& Objects.equals(noiDung, other.noiDung) && Objects.equals(thoiGianThongBao, other.thoiGianThongBao)
				&& Objects.equals(tieuDe, other.tieuDe);
	}

	@Override
	public String toString() {
		return "NoiDungThongBao [maNoiDungThongBao=" + maNoiDungThongBao + ", tieuDe=" + tieuDe + ", noiDung=" + noiDung
				+ ", thoiGianThongBao=" + thoiGianThongBao + ", ltb=" + ltb + "]";
	}
	
}
