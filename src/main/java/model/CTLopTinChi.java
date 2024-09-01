package model;

import java.time.LocalDateTime;
import java.util.Objects;

public class CTLopTinChi {
	private LopTinChi lopTinChi;
	private SinhVien sv;
	private LocalDateTime tgdk;
	
	public CTLopTinChi() {
		
	}

	public CTLopTinChi(LopTinChi lopTinChi, SinhVien sv, LocalDateTime tgdk) {
		super();
		this.lopTinChi = lopTinChi;
		this.sv = sv;
		this.tgdk = tgdk;
	}

	public LopTinChi getLopTinChi() {
		return lopTinChi;
	}

	public void setLopTinChi(LopTinChi lopTinChi) {
		this.lopTinChi = lopTinChi;
	}

	public SinhVien getSv() {
		return sv;
	}

	public void setSv(SinhVien sv) {
		this.sv = sv;
	}

	public LocalDateTime getTgdk() {
		return tgdk;
	}

	public void setTgdk(LocalDateTime tgdk) {
		this.tgdk = tgdk;
	}

	@Override
	public int hashCode() {
		return Objects.hash(lopTinChi, sv, tgdk);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CTLopTinChi other = (CTLopTinChi) obj;
		return Objects.equals(lopTinChi, other.lopTinChi) && Objects.equals(sv, other.sv)
				&& Objects.equals(tgdk, other.tgdk);
	}

	@Override
	public String toString() {
		return "CTLopTinChi [lopTinChi=" + lopTinChi + ", sv=" + sv + ", tgdk=" + tgdk + "]";
	}

	
	
	
	
}
