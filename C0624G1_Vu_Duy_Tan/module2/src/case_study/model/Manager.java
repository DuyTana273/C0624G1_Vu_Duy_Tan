package case_study.model;

public class Manager extends User {

    //===== CONSTRUCTOR =====
    public Manager(String username, String password, String phoneNumber, String fullName, String email) {
        super(username, password, phoneNumber, fullName, email, Role.ROLE_MANAGER);
    }

    //==== Xét duyệt ====
    public void approveSeller(String username, UserService userService) {
        userService.approveSeller(username);
    }

    //==== Method Check quyền phê duyệt Seller =====
    public boolean canApproveSellers() {
        return true;
    }
}
