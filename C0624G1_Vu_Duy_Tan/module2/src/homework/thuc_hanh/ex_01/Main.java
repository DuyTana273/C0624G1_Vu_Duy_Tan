package homework.thuc_hanh.ex_01;

public class Main {

    public static void main(String[] args) {
        CustomerManagement customerManagement = new CustomerManagement();
        customerManagement.displayAllCustomers();
        customerManagement.addCustomer(4, "Tí Nị");
        customerManagement.displayAllCustomers();
    }
}
