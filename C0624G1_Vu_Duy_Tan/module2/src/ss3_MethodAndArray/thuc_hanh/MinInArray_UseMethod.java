package ss3_MethodAndArray.thuc_hanh;

import java.util.Arrays;
import java.util.Scanner;

public class MinInArray_UseMethod {
    public static void main(String[] args) {
        int size;
        int[] array;
        Scanner sc = new Scanner(System.in);

        do {
            System.out.print("Enter the size of the array: ");
            size = sc.nextInt();
            if (size > 10) {
                System.out.println("Size does not exceed 20");
            }
        } while (size > 10);
        array = new int[size];
        int i = 0;
        while (i < array.length) {
            System.out.print("Enter element " + (i + 1) + ": ");
            array[i] = sc.nextInt();
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
