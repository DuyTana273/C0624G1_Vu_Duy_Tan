package homework.thuc_hanh.ex_01;

import java.util.Scanner;

public class CustomerManagement {
    private CustomerManagementMenu menu;
    Scanner sc = new Scanner(System.in);

    public CustomerManagement() {
        menu = CustomerManagementMenu.getInstance();
    }

    public void addCustomer(int id, String name) {
        menu.customers.add(new Customer(id, name));
        System.out.println("Khách hàng " + name + " đã được thêm vào.");
    }

    public void displayAllCustomers() {
        System.out.println("Danh sách khách hàng:");
        for (Customer khachHang : menu.customers) {
            System.out.println(khachHang);
        }
    }
}
