package case_study.model.user_manage;

public class Admin extends User {

    // ==== Giá trị mặc định ====
    private static final String DEFAULT_USERNAME = "admin";
    private static final String DEFAULT_PASSWORD = "admin";

    // ==== Constructor ====
    public Admin() {
        super(DEFAULT_USERNAME, DEFAULT_PASSWORD, "123456789", "Administrator", "admin@gmail.com");
    }

    @Override
    public String toString() {
        return super.toString();
    }
}