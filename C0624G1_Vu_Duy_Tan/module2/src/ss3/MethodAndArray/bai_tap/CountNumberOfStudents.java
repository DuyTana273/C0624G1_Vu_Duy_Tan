package ss3.MethodAndArray.bai_tap;

import java.util.Arrays;
import java.util.Scanner;

public class CountNumberOfStudents {
    public static void main(String[] args) {
        int size = 0;
        boolean validInput = false;
        Scanner sc = new Scanner(System.in);

        do {
            System.out.print("Enter the number of students: ");
            if (sc.hasNextInt()) {
                size = sc.nextInt();
                if (size > 0 && size <= 30) {
                    validInput = true;
                } else {
                    System.out.println("The entered number must be between 1 and 30");
                }
            } else {
                System.out.println("Invalid input. Please enter an integer.");
                sc.next();
            }
        } while (!validInput);

        int[] array = new int[size];
        int i = 0;
        while (i < array.length) {
            validInput = false;
            do {
                System.out.print("Student " + (i + 1) + ": ");
                if (sc.hasNextInt()) {
                    array[i] = sc.nextInt();
                    validInput = true;
                } else {
                    System.out.println("Invalid input. Please enter an integer.");
                    sc.next();
                }
            } while (!validInput);
            i++;
        }
//        System.out.printf("%-20s%s", "Students of class: ", Arrays.toString(array));
        int count = 0;
        for (int j : array) {
            if (j >= 5 && j <= 10)
                count++;
        }
        System.out.print("The number of students passing the exam is " + count);
    }
}
