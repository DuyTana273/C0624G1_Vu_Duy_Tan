package homework.accessment02.controller;

import homework.accessment02.model.Contact;
import homework.accessment02.service.ContactService;

import java.util.Scanner;

public class ContactController {
    ContactService manager = new ContactService();
    Scanner scanner = new Scanner(System.in);
    int choice;

    public void run() {
        do {
            System.out.println("===== QUẢN LÝ DANH BẠ ===== ");
            System.out.println("1. Thêm mới liên lạc");
            System.out.println("2. Sửa thông tin liên lạc");
            System.out.println("3. Xóa thông tin liên lạc");
            System.out.println("4. Tìm kiếm liên lạc theo mã liên lạc");
            System.out.println("5. Hiển thị danh sách liên lạc");
            System.out.println("6. Exit");
            System.out.print("Nhập một tùy chọn: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Nhập tên: ");
                    String name = scanner.nextLine();
                    System.out.print("Nhập SDT: ");
                    String phoneNumber = scanner.nextLine();
                    System.out.print("Nhập email: ");
                    String email = scanner.nextLine();
                    System.out.print("Nhập địa chỉ: ");
                    String address = scanner.nextLine();
                    manager.addContact(new Contact(name, phoneNumber, email, address));
                    break;
                case 2:
                    System.out.print("Nhập ID để cập nhật: ");
                    int updateId = scanner.nextInt();
                    scanner.nextLine();
                    if (manager.findContact(updateId) != null) {
                        System.out.print("Nhập số điện thoại mới: ");
                        String newPhoneNumber = scanner.nextLine();
                        manager.updateContact(updateId, newPhoneNumber);
                    } else {
                        System.out.println("Không tìm thấy liên hệ!");
                    }
                    break;
                case 3:
                    System.out.print("Nhập ID để xóa: ");
                    int deleteId = scanner.nextInt();
                    scanner.nextLine();
                    manager.deleteContact(deleteId);
                    break;
                case 4:
                    System.out.print("Nhập ID để tìm: ");
                    int findId = scanner.nextInt();
                    scanner.nextLine();
                    Contact contact = manager.findContact(findId);
                    if (contact != null) {
                        System.out.println(contact);
                    } else {
                        System.out.println("Không tìm thấy liên hệ!");
                    }
                    break;
                case 5:
                    manager.listContacts();
                    break;
                case 6:
                    System.out.println("Đang thoát...");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng thử lại.");
                    break;
            }
        } while (choice != 6);
    }

}


