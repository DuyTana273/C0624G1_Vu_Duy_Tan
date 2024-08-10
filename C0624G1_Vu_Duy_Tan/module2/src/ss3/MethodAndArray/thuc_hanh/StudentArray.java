package ss3.MethodAndArray.thuc_hanh;

import java.util.Scanner;

public class StudentArray {
    public static void main(String[] args) {
        String[] students = {"Christian", "Michael", "Camila", "Sienna", "Tanya", "Connor", "Zachariah", "Mallory", "Zoe", "Emily"};
        Scanner scanner = new Scanner(System.in);
        String input_name;
        boolean isExist;

        do {
            System.out.print("Enter a name’s student: ");
            input_name = scanner.nextLine();

            isExist = false;
            for (int i = 0; i < students.length; i++) {
                if (students[i].equals(input_name)) {
                    System.out.println("Position of the student " + input_name + " in the list is: " + i);
                    isExist = true;
                    break;
                }
            }

            if (!isExist) {
                System.out.println("Not found " + input_name + " in the list. Please try again.");
            }
        } while (!isExist);
    }
}
