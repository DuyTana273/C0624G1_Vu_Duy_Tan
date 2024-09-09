package case_study.model.user_manage;

import java.util.HashSet;
import java.util.Set;

public class User {

    //===== ĐỊNH NGHĨA THUỘC TÍNH =====
    protected String username;
    protected String password;
    protected String phoneNumber;
    protected String fullName;
    protected String email;
    protected Set<Role> roles = new HashSet<>();
    protected boolean isApproved;

    //===== CONSTRUCTOR =====
    public User(String username, String password, String phoneNumber, String fullName, String email) {
        this.username = username;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.fullName = fullName;
        this.email = email;
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

    //===== CHECK ROLE USERS =====
    public boolean hasRole(Role role) {
        return roles.contains(role);
    }

    //===== ADD ROLE USERS =====
    public void addRole(Role role) {
        roles.add(role);
    }

    //===== CHANGE PASSWORD =====
    public void changePassword(String newPassword) {
        this.password = newPassword;
    }

    @Override
    public String toString() {
        return "username='" + getUsername() + '\'' +
                ", Họ và tên:'" + getFullName() + '\'' +
                ", Số điện thoại:'" + getPhoneNumber() + '\'' +
                ", email:'" + getEmail() + '\'' +
                ", Role:'" + getRoles() + '\'';
    }
}