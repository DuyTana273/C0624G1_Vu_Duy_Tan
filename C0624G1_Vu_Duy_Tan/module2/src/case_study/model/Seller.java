package case_study.model;

public class Seller extends User {

    //===== CONSTRUCTOR =====
    public Seller(String username, String password, String phoneNumber, String fullName, String email) {
        super(username, password, phoneNumber, fullName, email, Role.ROLE_SELLER);
    }

    //===== CÁC PHƯƠNG THỨC CHO SELLER =====

}
