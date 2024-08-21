package case_study.productManagement.model;

public class RoleManager {

    public static void addRole(User user, Role role) {
        user.getRoles().add(role);
    }

    public static void removeRole(User user, Role role) {
        user.getRoles().remove(role);
    }

    public static boolean hasRole(User user, Role role) {
        return user.getRoles().contains(role);
    }
}
