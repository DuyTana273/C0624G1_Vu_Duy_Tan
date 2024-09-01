package case_study.model.user_manage;

public class Seller extends User {

    //===== CONSTRUCTOR =====
    public Seller(String username, String password, String phoneNumber, String fullName, String email) {
        super(username, password, phoneNumber, fullName, email);
    }

    @Override
    public String toString() {
        return super.toString();
    }

}
