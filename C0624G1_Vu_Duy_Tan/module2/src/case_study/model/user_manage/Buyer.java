package case_study.model.user_manage;

public class Buyer extends User {

    //===== CONSTRUCTOR =====
    public Buyer(String username, String password, String phoneNumber, String fullName, String email) {
        super(username, password, phoneNumber, fullName, email);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
