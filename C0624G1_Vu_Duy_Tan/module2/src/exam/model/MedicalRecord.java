package exam.model;

import java.time.LocalDate;

public abstract class MedicalRecord {
    protected int recordNumber;
    protected String recordId;
    protected String patientId;
    protected String patientName;
    protected LocalDate admissionDate;
    protected LocalDate dischargeDate;
    protected String reasonForAdmission;

    // Constructor bao gồm patientId
    public MedicalRecord(int recordNumber, String recordId, String patientId, String patientName,
                         LocalDate admissionDate, LocalDate dischargeDate, String reasonForAdmission) {
        this.recordNumber = recordNumber;
        this.recordId = recordId;
        this.patientId = patientId;
        this.patientName = patientName;
        this.admissionDate = admissionDate;
        this.dischargeDate = dischargeDate;
        this.reasonForAdmission = reasonForAdmission;
    }

    // Getter methods
    public int getRecordNumber() {
        return recordNumber;
    }

    public String getRecordId() {
        return recordId;
    }

    public String getPatientId() {
        return patientId;
    }

    public String getPatientName() {
        return patientName;
    }

    public LocalDate getAdmissionDate() {
        return admissionDate;
    }

    public LocalDate getDischargeDate() {
        return dischargeDate;
    }

    public String getReasonForAdmission() {
        return reasonForAdmission;
    }

    public abstract String getDetails();
}
