package case_study.model;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class User {

    //===== ĐỊNH NGHĨA THUỘC TÍNH =====
    protected String username;
    protected String password;
    protected String phoneNumber;
    protected String fullName;
    protected String email;
    protected Set<Role> roles;
    protected boolean isApproved;

    //===== CONSTRUCTOR =====
    public User(String username, String password, String phoneNumber, String fullName, String email, Role initialRole) {
        this.username = username;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.fullName = fullName;
        this.email = email;
        this.roles = new HashSet<>();
        this.roles.add(initialRole);
        this.isApproved = false;
    }

    //===== GETTERS & SETTERS =====
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    //===== KIỂM TRA NGƯỜI DÙNG CÓ VAI TRÒ NÀO ĐÓ KHÔNG =====
    public boolean hasRole(Role role) {
        return roles.contains(role);
    }

    //===== THÊM VAI TRÒ CHO NGƯỜI DÙNG =====
    public void addRole(Role role) {
        if (!roles.contains(role)) {
            roles.add(role);
        }
    }
    public Role getRole() {
        if (!roles.isEmpty()) {
            Iterator<Role> iterator = roles.iterator();
            return iterator.next(); // Lấy phần tử đầu tiên
        }
        return null; // Trả về null nếu không có vai trò nào
    }

    //===== XÓA VAI TRÒ CỦA NGƯỜI DÙNG =====
    public void removeRole(Role role) {
        roles.remove(role);
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", roles=" + roles +
                '}';
    }
}
