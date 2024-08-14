package ss3_MethodAndArray.bai_tap;

import java.util.Scanner;

public class MainDiagonalOfMatrix {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int rows = 0;
        int cols = 0;

        //Nhập mảng 2 chiều
        boolean validInput = false;
        System.out.println("=====Enter size of the matrix======");
        do {
            System.out.print("Enter the number of rows (1-10): ");
            if (sc.hasNextInt()) {
                rows = sc.nextInt();
                if (rows >= 1 && rows <= 10) {
                    validInput = true;
                } else {
                    System.out.println("Number of rows must be between 1 and 10.");
                }
            } else {
                System.out.println("Invalid input. Please enter an integer.");
                sc.next();
            }
        } while (!validInput);

        validInput = false;
        do {
            System.out.print("Enter the number of columns (1-10): ");
            if (sc.hasNextInt()) {
                cols = sc.nextInt();
                if (cols >= 1 && cols <= 10) {
                    validInput = true;
                    if (rows != cols) {
                        System.out.println("Please enter number rows = number cols");
                        validInput = false;
                    }
                } else {
                    System.out.println("Number of columns must be between 1 and 10.");
                }
            } else {
                System.out.println("Invalid input. Please enter an integer.");
                sc.next();
            }

        } while (!validInput);

        double[][] matrix = new double[rows][cols];
        System.out.println("=====Enter the matrix values=====");

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                boolean validValue = false;
                do {
                    System.out.printf("Enter element at position (%d, %d): ", i, j);
                    if (sc.hasNextDouble()) {
                        matrix[i][j] = sc.nextDouble();
                        validValue = true;
                    } else {
                        System.out.println("Invalid input. Please enter a valid number.");
                        sc.next();
                    }
                } while (!validValue);
            }
        }

        //In mảng 2 chiều
        System.out.println("=======MATRIX=======");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.printf("%.2f\t", matrix[i][j]);
            }
            System.out.println();
        }

        double sum = 0;
        for (int i = 0; i < matrix.length; i++) {
            sum += matrix[i][i];
        }
        System.out.println("Total Main Diagonal of matrix is: " + sum);
    }
}
