package exam.model;

import java.time.LocalDate;

public class VipPatientRecord extends MedicalRecord {
    private String vipType;
    private LocalDate vipExpiryDate;

    public VipPatientRecord(int recordNumber, String recordId, String patientId, String patientName,
                            LocalDate admissionDate, LocalDate dischargeDate, String reasonForAdmission, String vipType, LocalDate vipExpiryDate) {
        super(recordNumber, recordId, patientId, patientName, admissionDate, dischargeDate, reasonForAdmission);
        this.vipType = vipType;
        this.vipExpiryDate = vipExpiryDate;
    }

    public String getVipType() {
        return vipType;
    }

    public LocalDate getVipExpiryDate() {
        return vipExpiryDate;
    }

    @Override
    public String getDetails() {
        return String.format("Số thứ tự: %d, Mã bệnh án: %s, Mã bệnh nhân: %s, Tên bệnh nhân: %s, Ngày nhập viện: %s, Ngày ra viện: %s, Lý do nhập viện: %s, Loại VIP: %s, Thời hạn VIP: %s",
                getRecordNumber(), getRecordId(), getPatientId(), getPatientName(), getAdmissionDate(), getDischargeDate(), getReasonForAdmission(), getVipType(), getVipExpiryDate());
    }
}
