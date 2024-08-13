package ss3.MethodAndArray.bai_tap;

import java.util.Arrays;
import java.util.Scanner;

public class MinInArray {
    public static void main(String[] args) {
        int size = 0;
        boolean validInput = false;
        Scanner sc = new Scanner(System.in);

        do {
            System.out.print("Enter the size of the array (1-10): ");
            if (sc.hasNextInt()) {
                size = sc.nextInt();
                if (size > 0 && size <= 10) {
                    validInput = true;
                } else {
                    System.out.println("Size must be between 1 and 10.");
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
                System.out.print("Enter element " + (i + 1) + ": ");
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
        System.out.printf("%-20s%s", "Elements in array: ", Arrays.toString(array));

        int index = minValue(array);
        System.out.println(" The minimum value is " + array[index] + " at position " + index);
    }

    public static int minValue(int[] array) {
        int index = 0;
        for (int i = 1; i < array.length; i++) {
            if (array[i] < array[index]) {
                index = i;
            }
        }
        return index;
    }
}
