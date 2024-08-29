package homework.accessment01.view;

import java.util.Scanner;

public class ExpenseView {
    private final Scanner scanner = new Scanner(System.in);

    public String showMenu() {
        System.out.println("====== MENU TÍNH CHI PHÍ CHI TIÊU TRONG MỘT THÁNG ======");
        System.out.println("1. Tính tiền điện cuối tháng");
        System.out.println("2. Tính tiền taxi cuối tháng");
        System.out.println("3. Tính tiền lương cuối tháng");
        System.out.println("4. Tính tổng thu nhập sau khi chi tiêu cuối tháng");
        System.out.print("Mời bạn chọn chức năng [1->4] hoặc nhấn các số khác để thoát chương trình: ");
        return scanner.next();
    }

    //===== GET INPUT USER =====
    public double getInput(String message) {
        System.out.print(message);
        return scanner.nextDouble();
    }

    //=====  DISPLAY RESULT =====
    public void displayResult(String result) {
        System.out.println(result);
    }

    //=====  DISPLAY LOGOUT =====
    public void displayThankYouMessage() {
        System.out.println("Cảm ơn bạn đã dùng chương trình.");
    }
}
