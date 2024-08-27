package case_study.model;

public class Buyer extends User {

    //===== CONSTRUCTOR =====
    public Buyer(String username, String password, String phoneNumber, String fullName, String email) {
        super(username, password, phoneNumber, fullName, email, Role.ROLE_BUYER);
    }

    //===== CÁC PHƯƠNG THỨC CHO BUYER =====
}
