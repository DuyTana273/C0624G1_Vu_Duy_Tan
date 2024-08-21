package case_study.productManagement.model;

import java.util.ArrayList;
import java.util.List;

public class CustomerManager {
    private List<User> customers;

    public CustomerManager() {
        this.customers = new ArrayList<>();
    }

    public void addCustomer(User customer) {
        customers.add(customer);
    }

    public List<User> getCustomers() {
        return customers;
    }

    public void displayCustomers() {
        System.out.println("Danh sách khách hàng:");
        for (User customer : customers) {
            System.out.println(customer.toString());
        }
    }
}

