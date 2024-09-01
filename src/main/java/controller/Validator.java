package controller;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
	public static boolean validateMaSinhVien(String maSinhVien) {
		String maSinhVienPattern = "N\\d{2}[A-Z]{4}\\d{3}";
		return maSinhVien.matches(maSinhVienPattern);
	}
	
	public static boolean validateMaGiangVien(String maGiangVien) {
		String maSinhVienPattern = "GV\\d{3}";
		return maGiangVien.matches(maSinhVienPattern);
	}
	
	public static boolean validateHoTen(String hoTen) {
		String hoTenPattern = "^[A-ZÀ-Ỹ][a-zà-ỹ]*(?:\\s[A-ZÀ-Ỹ][a-zà-ỹ]*)*$";
		return hoTen.matches(hoTenPattern);
	}
	
	public static boolean validateSoDienThoai(String soDienThoai) {
		String soDienThoaiPattern = "^0\\d{9}$";
		return soDienThoai.matches(soDienThoaiPattern);
	}

	public static boolean validateCCCD(String cccd) {
		String cccdPattern = "^\\d{12}$";
		return cccd.matches(cccdPattern);
	}

	public static boolean validateEmail(String email) {
		String emailPattern = "^\\w+@\\w+(\\.\\w+)+(\\.\\w+)*$";
		return email.matches(emailPattern);
	}
	public static boolean validateMaNganh(String maNganh) {
        String mnPattern = "^\\d{7}$"; 
        return maNganh.matches(mnPattern);
    }
    
    public static boolean validateTenNganh(String tenNganh) {
        String tnPattern = "^[a-zA-ZÀ-ỹ0-9\\s]+$"; 
        return tenNganh.matches(tnPattern);
    }
	public static boolean validateMaMonHoc(String maMonHoc) {
		String mmhPattern = "^\\d{6}$";
		return maMonHoc.matches(mmhPattern);
	}
	public static boolean validateMaNHHK(String maNHHK) {
		String mmhPattern = "^\\d{6}$";
		return maNHHK.matches(mmhPattern);
	}
	public static boolean validateTenMonHoc(String tenMonHoc) {
		String tmhPattern = "^[a-zA-ZÀ-ỹ0-9\\s]+$";
		return tenMonHoc.matches(tmhPattern);
	}

	public static boolean validateMaLopTinChi(String maLopTinChi) {
		String mltcPattern = "^\\d{6}$";
		return maLopTinChi.matches(mltcPattern);
	}

	public static boolean validatePhong(String phong) {
		String phongPattern = "^[a-zA-Z0-9]{1,7}$";
		return phong.matches(phongPattern);
	}

	public static boolean isValidDateFormat(String dateStr) {
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate.parse(dateStr, formatter);
			return true;
		} catch (DateTimeParseException e) {
			return false;
		}
	}

	public static String validateNgayLopTinChi(String ngayBatDau, String ngayKetThuc) {
		String message = "";
		if (!isValidDateFormat(ngayBatDau) || !isValidDateFormat(ngayKetThuc)) {
			message = "Định dạng ngày không hợp lệ !";
			return message;
		}

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate startDate = LocalDate.parse(ngayBatDau, formatter);
		LocalDate endDate = LocalDate.parse(ngayKetThuc, formatter);
		LocalDate currentDate = LocalDate.now();

		if (startDate.isBefore(currentDate)) {
			message = "Ngày bắt đầu không được là ngày trong quá khứ !";
			return message;
		}

		if (endDate.isBefore(startDate) || endDate.isEqual(startDate)) {
			message = "Ngày kết thúc phải sau ngày bắt đầu !";
			return message;
		}

		return message;
	}
	
	public static String validateNamHoc(String namHoc) {
		String errorMessageNamHoc = "";
		Pattern pattern = Pattern.compile("^\\d{4}-\\d{4}$");
        if (!pattern.matcher(namHoc).matches()) {
            errorMessageNamHoc = "Vui lòng nhập năm học theo định dạng yyyy-yyyy";
            return errorMessageNamHoc;
        } else {
            String[] years = namHoc.split("-");
            int startYear = Integer.parseInt(years[0]);
            int endYear = Integer.parseInt(years[1]);
            if (endYear <= startYear) {
                errorMessageNamHoc = "Năm kết thúc phải lớn hơn năm bắt đầu";
                return errorMessageNamHoc;
            }
        }

		return errorMessageNamHoc;
	}
	
	public static String validateTiet(String tietBatDau, String tietKetThuc) {
		String message = "";
		int tietBD = Integer.parseInt(tietBatDau);
        int tietKT = Integer.parseInt(tietKetThuc);
        if (tietBD >= tietKT) {
            message =  "Tiết kết thúc phải lớn hơn tiết bắt đầu !";
            return message;
        }
        return message;
	}
	
	public static boolean validateNgaySinh(LocalDate ngaySinh) {
		LocalDate today = LocalDate.now();
		Period period = Period.between(ngaySinh, today);
		return period.getYears() >= 18;
	}

//    public static void main(String[] args) {
//        // Lấy ngày hiện tại
//        LocalDate ngayHienTai = LocalDate.now();
//        
//        // Trừ đi 18 năm từ ngày hiện tại để lấy ngày sinh tối đa để đủ 18 tuổi
//        LocalDate ngaySinhToiDa = ngayHienTai.minusYears(18);
//        
//        System.out.println("Ngày sinh tối đa để đủ 18 tuổi: " + ngaySinhToiDa);
//    }

}
