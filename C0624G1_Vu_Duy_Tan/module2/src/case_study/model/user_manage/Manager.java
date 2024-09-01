package case_study.model.user_manage;

public class Manager extends User {

    //===== CONSTRUCTOR =====
    public Manager(String username, String password, String phoneNumber, String fullName, String email) {
        super(username, password, phoneNumber, fullName, email);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
