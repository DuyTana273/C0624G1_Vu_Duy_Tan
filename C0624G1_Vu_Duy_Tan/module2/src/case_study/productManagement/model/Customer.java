package case_study.productManagement.model;

public class Customer extends User {

    public Customer(String userName, String password, String fullName, String email, String phone, String address) {
        super(userName, password, fullName, email, phone);
        this.roles.add(Role.ROLE_EMPLOYEE);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
