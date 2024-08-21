package case_study.productManagement.model;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CustomerManager customerManager = new CustomerManager();

        System.out.print("Nhập tên người dùng: ");
        String userName = scanner.nextLine();

        System.out.print("Nhập mật khẩu: ");
        String password = scanner.nextLine();

        User user;

        if ("admin".equals(userName) && "admin".equals(password)) {
            user = new User(userName, password, "Admin User", "admin@example.com", "123456789");
            user.getRoles().add(Role.ROLE_ADMIN);
            System.out.println("Đăng nhập thành công với vai trò: ADMIN");
        } else if ("employee".equals(userName) && "employee".equals(password)) {
            user = new User(userName, password, "Employee User", "employee@example.com", "987654321");
            user.getRoles().add(Role.ROLE_EMPLOYEE);
            System.out.println("Đăng nhập thành công với vai trò: EMPLOYEE");
        } else {
            user = new User(userName, password, userName, "customer@example.com", "456789123");
            user.getRoles().add(Role.ROLE_CUSTOMER);
            customerManager.addCustomer(user);
            System.out.println("Đăng nhập thành công với vai trò: CUSTOMER");
        }

        customerManager.displayCustomers();

        scanner.close();
    }
}
