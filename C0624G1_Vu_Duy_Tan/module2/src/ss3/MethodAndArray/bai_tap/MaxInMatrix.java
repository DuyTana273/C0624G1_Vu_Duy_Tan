package ss3.MethodAndArray.bai_tap;

import java.util.Scanner;

public class MaxInMatrix {
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

        //Kiểm tra phần tử Max
        double maxValue = matrix[0][0];
        int maxRow = 0;
        int maxCol = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] > maxValue) {
                    maxValue = matrix[i][j];
                    maxRow = i;
                    maxCol = j;
                }
            }
        }

        System.out.println("========MATRIX========");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.printf("%.2f\t", matrix[i][j]);
            }
            System.out.println();
        }
        System.out.printf("Max value: %.2f at position (%d, %d)\n", maxValue, maxRow, maxCol);
    }
}
