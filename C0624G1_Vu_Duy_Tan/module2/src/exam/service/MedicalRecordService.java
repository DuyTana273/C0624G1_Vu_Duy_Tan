package exam.service;

import exam.exception.DuplicateMedicalRecordException;
import exam.model.MedicalRecord;
import exam.model.RegularPatientRecord;
import exam.model.VipPatientRecord;
import exam.util.FileHandler;
import exam.util.Validate;
import exam.view.MedicalRecordView;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MedicalRecordService {
    private List<MedicalRecord> records;
    private MedicalRecordView medicalRecordView;
    private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public MedicalRecordService(MedicalRecordView medicalRecordView) {
        this.medicalRecordView = medicalRecordView;
        try {
            this.records = FileHandler.readRecordsFromFile();
        } catch (Exception e) {
            this.records = new ArrayList<>();
            medicalRecordView.showMessage("Lỗi đọc dữ liệu từ file. Danh sách bệnh án được khởi tạo rỗng.");
        }
    }

    // Lấy số thứ tự bệnh án mới nhất
    private int getNextRecordNumber() {
        return records.stream()
                .mapToInt(MedicalRecord::getRecordNumber)
                .max()
                .orElse(0) + 1;
    }

    // Kiểm tra mã bệnh án trùng lặp
    private void checkDuplicateRecordId(String recordId) throws DuplicateMedicalRecordException {
        Optional<MedicalRecord> existingRecord = findRecordById(recordId);
        if (existingRecord.isPresent()) {
            throw new DuplicateMedicalRecordException("Mã bệnh án đã tồn tại. Vui lòng nhập lại.");
        }
    }

    // Thêm bệnh án bệnh nhân thường
    public void addRegularPatientRecord() {
        while (true) {
            try {
                String recordId = medicalRecordView.getInput("Nhập mã bệnh án (định dạng BA-XXX): ");
                String patientId = medicalRecordView.getInput("Nhập mã bệnh nhân (định dạng BN-XXX): ");
                String patientName = medicalRecordView.getInput("Nhập tên bệnh nhân: ");
                String admissionDateStr = medicalRecordView.getInput("Nhập ngày nhập viện (dd/MM/yyyy): ");
                String dischargeDateStr = medicalRecordView.getInput("Nhập ngày ra viện (dd/MM/yyyy): ");
                String reasonForAdmission = medicalRecordView.getInput("Nhập lý do nhập viện: ");
                double hospitalFee = Double.parseDouble(medicalRecordView.getInput("Nhập phí nằm viện: "));

                Validate.validateInputs(recordId, patientId, admissionDateStr, dischargeDateStr, null);

                LocalDate admissionDate = LocalDate.parse(admissionDateStr, dateFormatter);
                LocalDate dischargeDate = LocalDate.parse(dischargeDateStr, dateFormatter);

                MedicalRecord record = new RegularPatientRecord(
                        getNextRecordNumber(),
                        recordId,
                        patientId,
                        patientName,
                        admissionDate,
                        dischargeDate,
                        reasonForAdmission,
                        hospitalFee
                );

                records.add(record);
                FileHandler.writeRecordsToFile(records);
                medicalRecordView.showMessage("Thêm bệnh án bệnh nhân thường thành công!");
                break;
            } catch (IllegalArgumentException e) {
                medicalRecordView.showMessage("Dữ liệu không hợp lệ: " + e.getMessage());
            } catch (Exception e) {
                medicalRecordView.showMessage("Lỗi khi thêm bệnh án: " + e.getMessage());
            }
        }
    }

    // Thêm bệnh án bệnh nhân VIP
    public void addVipPatientRecord() {
        while (true) {
            try {
                String recordId = medicalRecordView.getInput("Nhập mã bệnh án (định dạng BA-XXX): ");
                checkDuplicateRecordId(recordId);

                String patientId = medicalRecordView.getInput("Nhập mã bệnh nhân (định dạng BN-XXX): ");
                String patientName = medicalRecordView.getInput("Nhập tên bệnh nhân: ");
                String admissionDateStr = medicalRecordView.getInput("Nhập ngày nhập viện (dd/MM/yyyy): ");
                String dischargeDateStr = medicalRecordView.getInput("Nhập ngày ra viện (dd/MM/yyyy): ");
                String reasonForAdmission = medicalRecordView.getInput("Nhập lý do nhập viện: ");
                String vipType = medicalRecordView.getInput("Nhập loại VIP (VIP1, VIP2, VIP3): ");
                String vipExpiryDateStr = medicalRecordView.getInput("Nhập ngày hết hạn VIP (dd/MM/yyyy): ");

                Validate.validateInputs(recordId, patientId, admissionDateStr, dischargeDateStr, vipType);

                LocalDate admissionDate = LocalDate.parse(admissionDateStr, dateFormatter);
                LocalDate dischargeDate = LocalDate.parse(dischargeDateStr, dateFormatter);
                LocalDate vipExpiryDate = LocalDate.parse(vipExpiryDateStr, dateFormatter);

                MedicalRecord record = new VipPatientRecord(
                        getNextRecordNumber(),
                        recordId,
                        patientId,
                        patientName,
                        admissionDate,
                        dischargeDate,
                        reasonForAdmission,
                        vipType,
                        vipExpiryDate
                );
                records.add(record);
                FileHandler.writeRecordsToFile(records);
                medicalRecordView.showMessage("Thêm bệnh án bệnh nhân VIP thành công!");
                break;
            } catch (DuplicateMedicalRecordException e) {
                medicalRecordView.showMessage(e.getMessage());
            } catch (IllegalArgumentException e) {
                medicalRecordView.showMessage("Dữ liệu không hợp lệ: " + e.getMessage());
            } catch (Exception e) {
                medicalRecordView.showMessage("Lỗi khi thêm bệnh án: " + e.getMessage());
            }
        }
    }

    // Tìm kiếm bệnh án theo mã
    public Optional<MedicalRecord> findRecordById(String recordId) {
        return records.stream()
                .filter(record -> record.getRecordId().equals(recordId))
                .findFirst();
    }

    // Hiển thị tất cả bệnh án
    public void displayAllRecords() {
        if (records.isEmpty()) {
            medicalRecordView.showMessage("Danh sách bệnh án trống.");
        } else {
            for (MedicalRecord record : records) {
                medicalRecordView.showMessage(record.getDetails());
            }
        }
    }

    // Xóa bệnh án theo mã
    public void removeRecordById() {
        displayAllRecords();

        boolean confirm = medicalRecordView.confirmAction("Bạn có chắc chắn muốn xóa bệnh án này?");
        if (!confirm) {
            medicalRecordView.showMessage("Hành động xóa đã bị hủy.");
            return;
        }

        String recordId = medicalRecordView.getInput("Nhập mã bệnh án cần xóa: ").toUpperCase();

        boolean isRemoved = records.removeIf(record -> record.getRecordId().equals(recordId));
        if (isRemoved) {
            FileHandler.writeRecordsToFile(records);
            medicalRecordView.showMessage("Xóa bệnh án thành công.");
        } else {
            medicalRecordView.showMessage("Bệnh án không tồn tại.");
        }
    }
}
