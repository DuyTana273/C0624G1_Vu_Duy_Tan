package exam.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MedicalRecordView {
    private final Scanner scanner = new Scanner(System.in);

    public MedicalRecordView() {

    }

    //===== NHẬN ĐẦU VÀO =====
    public String getInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    //===== XÁC NHẬN HÀNH ĐỘNG =====
    public boolean confirmAction(String message) {
        System.out.print(message + " (yes/no): ");
        String response = scanner.nextLine().trim().toLowerCase();
        return response.equals("yes");
    }

    //===== THÔNG BÁO =====
    public void showMessage(String message) {
        System.out.println(message);
    }

    //======================== HIỂN THỊ MENU ========================
    public void showMenu(String title, List<String> options) {
        System.out.println("----- " + title + " -----");
        for (int i = 0; i < options.size(); i++) {
            System.out.println((i + 1) + ". " + options.get(i));
        }
    }

    //===== HIỂN THỊ MENU BAN ĐẦU =====
    public void showInitialMenu() {
        List<String> options = new ArrayList<>();
        options.add("Thêm mới");
        options.add("Xóa");
        options.add("Xem danh sách các bệnh án");
        options.add("Thoát");
        showMenu("CHƯƠNG TRÌNH QUẢN LÝ BỆNH ÁN", options);
    }
}
