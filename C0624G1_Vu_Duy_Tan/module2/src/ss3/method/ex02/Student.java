package ss3.method.ex02;

public class Student {
    public static void main(String[] args) {
        String[] studentNames = new String[4];
        studentNames[0] = "Tân";
        studentNames[1] = "Trung";
        studentNames[2] = "Hoàng";
        studentNames[3] = "Khang";

        for (String sName : studentNames) {
            System.out.println(sName);
        }
    }
}
