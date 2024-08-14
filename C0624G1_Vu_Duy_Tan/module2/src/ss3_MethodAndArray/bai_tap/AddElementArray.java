package ss3_MethodAndArray.bai_tap;

import java.util.Arrays;
import java.util.Scanner;

public class AddElementArray {
    public static void main(String[] args) {
        int size;
        int[] array;
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.print("Enter a size:");
            size = scanner.nextInt();
            if (size > 10)
                System.out.println("Size does not exceed 10");
        } while (size > 10);

        array = new int[size];
        int i = 0;
        while (i < array.length) {
            System.out.print("Enter element " + (i + 1) + ": ");
            array[i] = scanner.nextInt();
            i++;
        }
        System.out.printf("%-20s%s", "Elements in array: ", "");
        System.out.println("Original array: " + Arrays.toString(array));

        System.out.print("Enter the element to add: ");
        int elementToAdd = scanner.nextInt();

        System.out.print("Enter the position to add: ");
        int position = scanner.nextInt();

        if (position < 0 || position > array.length) {
            System.out.println("Invalid position! Please enter position between 0 and " + array.length);
        } else {
            int[] newArray = new int[array.length + 1];

            for (i = 0; i < position; i++) {
                newArray[i] = array[i];
            }

            newArray[position] = elementToAdd;

            for (i = position; i < array.length; i++) {
                newArray[i + 1] = array[i];
            }

            System.out.print("Array after addition: " + Arrays.toString(newArray));
        }
    }
}
