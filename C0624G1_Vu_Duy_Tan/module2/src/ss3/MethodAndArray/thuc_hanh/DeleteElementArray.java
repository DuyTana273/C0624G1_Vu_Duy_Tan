package ss3.MethodAndArray.thuc_hanh;

import java.util.Arrays;
import java.util.Scanner;

public class DeleteElementArray {
    public static void main(String[] args) {
        int[] array = {10, 4, 6, 7, 8, 6, 11, 2, 5};
        Scanner sc = new Scanner(System.in);

        System.out.println("Original array: " + Arrays.toString(array));
        System.out.print("Enter the element to be deleted:");
        int element = sc.nextInt();

        int count = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == element) {
                count++;
            }
        }

        if (count == 0) {
            System.out.println("Element not found in the array.");
        } else {
            int[] newArray = new int[array.length - count];
            int index = 0;

            for (int i = 0; i < array.length; i++) {
                if (array[i] != element) {
                    newArray[index] = array[i];
                    index++;
                }
            }
            System.out.print("Array after deletion: " + Arrays.toString(newArray));
        }
    }
}
