package ss2_Loop.bai_tap.ShowGeometry;

import java.util.Scanner;

public class ShowGeometry {
    public static void main(String[] args) {
        int choice;

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the choice");
        System.out.println("Menu");
        System.out.println("1. Print the rectangle");
        System.out.println("2. Print the square triangle");
        System.out.println("3. Print isosceles triangle");
        System.out.println("4. Exit");
        System.out.print("Enter your choice: ");
        choice = sc.nextInt();

        int i;
        int j;
        switch (choice) {
            case 1:
                for (i = 0; i < 3; i++) {
                    for (j = 0; j < 6; j++) {
                        System.out.print("*");
                    }
                    System.out.println();
                }
                break;
            case 2:
                for (i = 1; i <= 5; i++) {
                    for (j = 1; j < i; j++) {
                        System.out.print("*");
                    }
                    System.out.println();
                }
                break;
            case 3:
                for (i = 7; i >= 1; i--) {
                    for (j = 1; j <= i; j++) {
                        System.out.print("*");
                    }
                    System.out.println();
                }
                break;
            case 4:
                System.exit(0);
        }
    }
}