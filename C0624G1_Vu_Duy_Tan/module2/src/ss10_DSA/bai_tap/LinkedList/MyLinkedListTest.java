package ss10_DSA.bai_tap.LinkedList;

import java.util.Scanner;

public class MyLinkedListTest {
    public static void main(String[] args) {
        MyLinkedList<Integer> list = new MyLinkedList<>(10);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Nhập một số nguyên (hoặc 'exit' để thoát): ");
            String input = scanner.nextLine();

            // Kiểm tra nếu người dùng muốn thoát
            if (input.equalsIgnoreCase("exit")) {
                break;
            }

            try {
                // Kiểm tra xem người dùng có nhập số không
                int number = Integer.parseInt(input);

                // Thêm số vào cuối danh sách
                list.addLast(number);

                // Hiển thị danh sách
                System.out.println("Danh sách hiện tại: ");
                list.printList();
            } catch (NumberFormatException e) {
                System.out.println("Lỗi: Vui lòng nhập một số nguyên hợp lệ.");
            }
        }

        // Hiển thị danh sách cuối cùng sau khi người dùng kết thúc nhập
        System.out.println("Danh sách cuối cùng: ");
        list.printList();

        scanner.close();
    }
}

