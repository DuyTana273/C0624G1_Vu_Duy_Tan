package ss5_AccessModifier.Ex.Ex02;

import java.util.Scanner;

//Nang cap bai quan ly sinh vien len de quan ly n sinh vien mot lop
public class StudentManagement2 {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.print("Vui lòng nhập số lượng học viên: ");
        int n = scanner.nextInt();

        Student[] c06StudentsArray = new Student[n];

        System.out.println("\nMời bạn nhập thông tin các học viên...\n");
        for (int i = 0; i < n; i++) {
            System.out.printf("Mời bạn nhập mã số học viên thứ %d: ", i + 1);
            int id = scanner.nextInt();
            scanner.nextLine();
            System.out.printf("Mời bạn nhập họ tên học viên thứ %d: ", i + 1);
            String name = scanner.nextLine();
            System.out.printf("Mời bạn nhập email học viên thứ %d: ", i + 1);
            String email = scanner.nextLine();
            System.out.printf("Mời bạn nhập số điện thoại học viên thứ %d: ", i + 1);
            String phone = scanner.nextLine();
            System.out.printf("Mời bạn nhập lớp học viên thứ %d: ", i + 1);
            String clazz = scanner.nextLine();

            Student newStudent = new Student(id, name, email, phone, clazz);

            c06StudentsArray[i] = newStudent;

            System.out.println("-----");
        }

        //Xuat ra mang lop hoc cac sinh vien vua nhap thong tin
        System.out.println("\nDanh sách các học viên vừa nhập là: ");
        for (Student student : c06StudentsArray) {
            System.out.println(student);
        }
    }
}
