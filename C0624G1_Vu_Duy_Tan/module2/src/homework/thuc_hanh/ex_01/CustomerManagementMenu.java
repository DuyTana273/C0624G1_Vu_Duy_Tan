package homework.thuc_hanh.ex_01;

import java.util.ArrayList;
import java.util.List;

public class CustomerManagementMenu {

    private static CustomerManagementMenu customerManagementMenu = null;
    public List<Customer> customers;
    private CustomerManagementMenu() {
        customers = new ArrayList<Customer>();
        customers.add(new Customer(1, "Tý"));
        customers.add(new Customer(2, "Tèo"));
        customers.add(new Customer(3, "Tồ"));
    }

    public static CustomerManagementMenu getInstance() {

        if (customerManagementMenu == null) {
            customerManagementMenu = new CustomerManagementMenu();
        }
        return customerManagementMenu;
    }
}
