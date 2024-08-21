package case_study.productManagement.model;

public class Employee extends User {

    public Employee(String userName, String password, String fullName, String email, String phone, String address) {
        super(userName, password, fullName, email, phone);
        this.roles.add(Role.ROLE_EMPLOYEE);
    }
}
