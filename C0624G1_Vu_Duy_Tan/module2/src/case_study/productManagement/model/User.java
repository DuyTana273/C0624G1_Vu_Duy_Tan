package case_study.productManagement.model;

public class User {
    private String userName;
    private String password;
    private String fullName;
    private String email;
    private String phone;
    private String address;

    public User() {}

    public User(String userName, String password, String fullName, String email, String phone, String address) {
        this.userName = userName;
        this.password = password;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return  "username='" + userName + '\'' +
                ", name='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phone + '\'';
    }
}
