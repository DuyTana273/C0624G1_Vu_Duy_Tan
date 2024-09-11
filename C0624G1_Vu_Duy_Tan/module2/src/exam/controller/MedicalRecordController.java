package exam.controller;

import exam.service.MedicalRecordService;
import exam.view.MedicalRecordView;

public class MedicalRecordController {
    private MedicalRecordView medicalRecordView;
    private MedicalRecordService medicalRecordService;

    public MedicalRecordController(MedicalRecordView medicalRecordView, MedicalRecordService medicalRecordService) {
        this.medicalRecordView = medicalRecordView;
        this.medicalRecordService = medicalRecordService;
    }

    //********************* BẮT ĐẦU CHƯƠNG TRÌNH *********************

    public void start() {
        while (true) {
            medicalRecordView.showInitialMenu();
            String choice = medicalRecordView.getInput("Chọn chức năng: ");
            switch (choice) {
                case "1" -> {
                    String patientType = medicalRecordView.getInput("Chọn loại bệnh nhân (1: Thường, 2: VIP): ");
                    if (patientType.equals("1")) {
                        medicalRecordService.addRegularPatientRecord();
                    } else if (patientType.equals("2")) {
                        medicalRecordService.addVipPatientRecord();
                    } else {
                        medicalRecordView.showMessage("Lựa chọn không hợp lệ.");
                    }
                }
                case "2" -> medicalRecordService.removeRecordById();
                case "3" -> medicalRecordService.displayAllRecords();
                case "4" -> {
                    medicalRecordView.showMessage("Cảm ơn bạn đã sử dụng chương trình!");
                    return;
                }
                default -> medicalRecordView.showMessage("Lựa chọn không hợp lệ. Vui lòng thử lại!");
            }
        }
    }
}
