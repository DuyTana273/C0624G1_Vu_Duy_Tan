package exam.model;

import java.time.LocalDate;

public class RegularPatientRecord extends MedicalRecord {
    private double hospitalFee;

    public RegularPatientRecord(int recordNumber, String recordId, String patientId, String patientName,
                                LocalDate admissionDate, LocalDate dischargeDate, String reasonForAdmission, double hospitalFee) {
        super(recordNumber, recordId, patientId, patientName, admissionDate, dischargeDate, reasonForAdmission);
        this.hospitalFee = hospitalFee;
    }

    public double getHospitalFee() {
        return hospitalFee;
    }

    @Override
    public String getDetails() {
        return String.format("Số thứ tự: %d, Mã bệnh án: %s, Mã bệnh nhân: %s, Tên bệnh nhân: %s, Ngày nhập viện: %s, Ngày ra viện: %s, Lý do nhập viện: %s, Phí nằm viện: %.2f",
                getRecordNumber(), getRecordId(), getPatientId(), getPatientName(), getAdmissionDate(), getDischargeDate(), getReasonForAdmission(), getHospitalFee());
    }
}
