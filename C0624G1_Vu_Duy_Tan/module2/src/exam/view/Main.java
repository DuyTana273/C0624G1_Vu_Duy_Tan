package exam.view;

import exam.controller.MedicalRecordController;
import exam.service.MedicalRecordService;

public class Main {
    public static void main(String[] args) {

        MedicalRecordView medicalRecordView = new MedicalRecordView();
        MedicalRecordService medicalRecordService = new MedicalRecordService(medicalRecordView);

        MedicalRecordController medicalRecordController = new MedicalRecordController(medicalRecordView, medicalRecordService);

        // Bắt đầu chương trình
        medicalRecordController.start();
    }
}
