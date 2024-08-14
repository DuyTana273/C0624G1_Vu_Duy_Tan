package ss3_MethodAndArray.thuc_hanh;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;

public class MaxInArray {
    public static void main(String[] args) {
        int size;
        int[] array;
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.print("Enter a size (up to 20): ");
            size = scanner.nextInt();
            if (size > 20) {
                System.out.println("Size should not exceed 20.");
            }
        } while (size > 20);

        array = new int[size];
        int max = array[0];
        int maxIndex = 0;
        for (int i = 0; i < array.length; i++) {
            System.out.print("Enter element " + (i + 1) + ": ");
            array[i] = scanner.nextInt();

            if (array[i] > max) {
                max = array[i];
                maxIndex = i;
            }
        }
        System.out.println("Property list: " + Arrays.toString(array));
        System.out.println("The largest property value in the list is " + max + ", at position " + (maxIndex + 1));
    }
}
