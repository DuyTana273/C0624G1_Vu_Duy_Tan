package ss10_DSA.bai_tap.ArrayList;

import java.util.Scanner;

public class MyListTest {
    public static void main(String[] args) {
        MyList<Integer> list = new MyList<>();
        Scanner scanner = new Scanner(System.in);

        int n = -1;
        while (n <= 0) {
            System.out.print("Nhập số lượng phần tử cần thêm vào danh sách (n > 0): ");
            String input = scanner.next();
            if (isNumeric(input)) {
                n = Integer.parseInt(input);
                if (n <= 0) {
                    System.out.println("Số lượng phần tử phải lớn hơn 0. Vui lòng thử lại.");
                }
            } else {
                System.out.println("Vui lòng chỉ nhập số nguyên dương.");
            }
        }

        for (int i = 0; i < n; i++) {
            int element = 0;
            boolean validInput = false;
            while (!validInput) {
                System.out.print("Nhập phần tử thứ " + (i + 1) + ": ");
                String input = scanner.next();
                if (isNumeric(input)) {
                    element = Integer.parseInt(input);
                    validInput = true;
                } else {
                    System.out.println("Phần tử phải là một số nguyên. Vui lòng nhập lại.");
                }
            }
            list.add(element);
        }

        System.out.println("Danh sách hiện tại: ");
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i) + " ");
        }
        System.out.println();

        int index = -1;
        while (index < 0 || index > list.size()) {
            System.out.print("Nhập vị trí muốn thêm phần tử mới (từ 0 đến " + list.size() + "): ");
            String input = scanner.next();
            if (isNumeric(input)) {
                index = Integer.parseInt(input);
                if (index < 0 || index > list.size()) {
                    System.out.println("Vị trí không hợp lệ. Vui lòng nhập lại.");
                }
            } else {
                System.out.println("Vị trí phải là một số nguyên. Vui lòng nhập lại.");
            }
        }

        int newElement = 0;
        boolean validNewElement = false;
        while (!validNewElement) {
            System.out.print("Nhập phần tử mới: ");
            String input = scanner.next();
            if (isNumeric(input)) {
                newElement = Integer.parseInt(input);
                validNewElement = true;
            } else {
                System.out.println("Phần tử phải là một số nguyên. Vui lòng nhập lại.");
            }
        }
        list.add(index, newElement);

        System.out.println("Danh sách sau khi thêm phần tử: ");
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i) + " ");
        }
        System.out.println();

        int removeIndex = -1;
        while (removeIndex < 0 || removeIndex >= list.size()) {
            System.out.print("Nhập vị trí muốn xóa phần tử (từ 0 đến " + (list.size() - 1) + "): ");
            String input = scanner.next();
            if (isNumeric(input)) {
                removeIndex = Integer.parseInt(input);
                if (removeIndex < 0 || removeIndex >= list.size()) {
                    System.out.println("Vị trí không hợp lệ. Vui lòng nhập lại.");
                }
            } else {
                System.out.println("Vị trí phải là một số nguyên. Vui lòng nhập lại.");
            }
        }

        list.remove(removeIndex);

        System.out.println("Danh sách sau khi xóa phần tử: ");
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i) + " ");
        }
        System.out.println();

        scanner.close();
    }

    private static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
