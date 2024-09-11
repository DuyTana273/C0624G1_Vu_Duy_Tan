package exam.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;

public class Validate {

    // Định dạng mã bệnh án: BA-XXX (với XXX là số)
    public static boolean validateMedicalRecordId(String recordId) {
        if (recordId == null || recordId.isEmpty()) {
            throw new IllegalArgumentException("Mã bệnh án không được để trống.");
        }
        recordId = recordId.toUpperCase();
        String regex = "BA-\\d{3}";
        return Pattern.matches(regex, recordId);
    }

    // Định dạng mã bệnh nhân: BN-XXX (với XXX là số)
    public static boolean validatePatientId(String patientId) {
        if (patientId == null || patientId.isEmpty()) {
            throw new IllegalArgumentException("Mã bệnh nhân không được để trống.");
        }
        patientId = patientId.toUpperCase();
        String regex = "BN-\\d{3}";
        return Pattern.matches(regex, patientId);
    }

    // Định dạng ngày: dd/MM/yyyy
    public static boolean validateDate(String dateStr) {
        if (dateStr == null || dateStr.isEmpty()) {
            throw new IllegalArgumentException("Ngày không được để trống.");
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        try {
            LocalDate.parse(dateStr, formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    // Kiểm tra ngày nhập viện phải nhỏ hơn hoặc bằng ngày ra viện
    public static boolean validateAdmissionDischargeDate(String admissionDateStr, String dischargeDateStr) {
        if (!validateDate(admissionDateStr) || !validateDate(dischargeDateStr)) {
            throw new IllegalArgumentException("Định dạng ngày không hợp lệ.");
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        try {
            LocalDate admissionDate = LocalDate.parse(admissionDateStr, formatter);
            LocalDate dischargeDate = LocalDate.parse(dischargeDateStr, formatter);
            return !admissionDate.isAfter(dischargeDate);
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    // Kiểm tra gói VIP phải là VIP1, VIP2 hoặc VIP3
    public static boolean validateVipPackage(String vipPackage) {
        if (vipPackage == null || vipPackage.isEmpty()) {
            throw new IllegalArgumentException("Gói VIP không được để trống.");
        }
        return vipPackage.equals("VIP1") || vipPackage.equals("VIP2") || vipPackage.equals("VIP3");
    }

    // Phương thức kiểm tra tất cả các điều kiện với thông báo lỗi
    public static void validateInputs(String recordId, String patientId, String admissionDateStr,
                                      String dischargeDateStr, String vipPackage) {
        if (!validateMedicalRecordId(recordId)) {
            throw new IllegalArgumentException("Mã bệnh án không hợp lệ. Định dạng: BA-XXX, với XXX là số.");
        }

        if (!validatePatientId(patientId)) {
            throw new IllegalArgumentException("Mã bệnh nhân không hợp lệ. Định dạng: BN-XXX, với XXX là số.");
        }

        if (!validateDate(admissionDateStr)) {
            throw new IllegalArgumentException("Ngày nhập viện không hợp lệ. Định dạng: dd/MM/yyyy.");
        }

        if (!validateDate(dischargeDateStr)) {
            throw new IllegalArgumentException("Ngày ra viện không hợp lệ. Định dạng: dd/MM/yyyy.");
        }

        if (!validateAdmissionDischargeDate(admissionDateStr, dischargeDateStr)) {
            throw new IllegalArgumentException("Ngày nhập viện phải nhỏ hơn hoặc bằng ngày ra viện.");
        }

        if (vipPackage != null && !validateVipPackage(vipPackage)) {
            throw new IllegalArgumentException("Gói VIP không hợp lệ. Chỉ chọn VIP1, VIP2 hoặc VIP3.");
        }
    }
}
