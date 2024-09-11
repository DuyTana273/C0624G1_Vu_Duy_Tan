package exam.util;

import exam.model.MedicalRecord;
import exam.model.RegularPatientRecord;
import exam.model.VipPatientRecord;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {

    private static final String FILE_PATH = "src/exam/data/medical_records.csv";
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    // Đọc dữ liệu từ file CSV
    public static List<MedicalRecord> readRecordsFromFile() {
        List<MedicalRecord> records = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line = reader.readLine(); // Bỏ qua dòng tiêu đề

            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                int recordNumber = Integer.parseInt(data[0].trim());
                String recordId = data[1].trim();
                String patientId = data[2].trim();
                String patientName = data[3].trim();
                LocalDate admissionDate = LocalDate.parse(data[4].trim(), DATE_FORMATTER);
                LocalDate dischargeDate = LocalDate.parse(data[5].trim(), DATE_FORMATTER);
                String reasonForAdmission = data[6].trim();

                if (data.length == 8) { // Bệnh nhân thường
                    double hospitalFee = Double.parseDouble(data[7].trim());
                    records.add(new RegularPatientRecord(recordNumber, recordId, patientId, patientName, admissionDate, dischargeDate, reasonForAdmission, hospitalFee));
                } else if (data.length == 9) { // Bệnh nhân VIP
                    String vipType = data[7].trim();
                    LocalDate vipExpiryDate = LocalDate.parse(data[8].trim(), DATE_FORMATTER);
                    records.add(new VipPatientRecord(recordNumber, recordId, patientId, patientName, admissionDate, dischargeDate, reasonForAdmission, vipType, vipExpiryDate));
                }
            }

        } catch (IOException e) {
            System.err.println("Lỗi khi đọc file: " + e.getMessage());
        }

        return records;
    }

    // Ghi dữ liệu vào file CSV
    public static void writeRecordsToFile(List<MedicalRecord> records) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            // Tiêu đề
            writer.write("Số thứ tự,Mã bệnh án,Mã bệnh nhân,Tên bệnh nhân,Ngày nhập viện,Ngày ra viện,Lý do nhập viện,Phí nằm viện/Loại VIP,Thời hạn VIP");
            writer.newLine();

            for (MedicalRecord record : records) {
                if (record instanceof RegularPatientRecord) {
                    RegularPatientRecord regularRecord = (RegularPatientRecord) record;
                    writer.write(String.format("%d,%s,%s,%s,%s,%s,%s,%.2f",
                            regularRecord.getRecordNumber(),
                            regularRecord.getRecordId(),
                            regularRecord.getPatientId(),
                            regularRecord.getPatientName(),
                            regularRecord.getAdmissionDate().format(DATE_FORMATTER),
                            regularRecord.getDischargeDate().format(DATE_FORMATTER),
                            regularRecord.getReasonForAdmission(),
                            regularRecord.getHospitalFee()));
                } else if (record instanceof VipPatientRecord) {
                    VipPatientRecord vipRecord = (VipPatientRecord) record;
                    writer.write(String.format("%d,%s,%s,%s,%s,%s,%s,%s,%s",
                            vipRecord.getRecordNumber(),
                            vipRecord.getRecordId(),
                            vipRecord.getPatientId(),
                            vipRecord.getPatientName(),
                            vipRecord.getAdmissionDate().format(DATE_FORMATTER),
                            vipRecord.getDischargeDate().format(DATE_FORMATTER),
                            vipRecord.getReasonForAdmission(),
                            vipRecord.getVipType(),
                            vipRecord.getVipExpiryDate().format(DATE_FORMATTER)));
                }
                writer.newLine();
            }

        } catch (IOException e) {
            System.err.println("Lỗi khi ghi file: " + e.getMessage());
        }
    }
}
