package ss3.MethodAndArray.bai_tap;

import java.util.Arrays;
import java.util.Scanner;

public class MergerArrays {
    public static void main(String[] args) {
        //Nhập mảng thứ nhất
        int size1;
        Scanner sc = new Scanner(System.in);
        System.out.println("===Enter the size of the fist array===");
        do {
            System.out.print("Enter a size 1: ");
            size1 = sc.nextInt();
            if (size1 > 10)
                System.out.println("Size does not exceed 10");
        } while (size1 > 10);

        int[] array1;
        array1 = new int[size1];
        for (int i = 0; i < array1.length; i++) {
            System.out.print("Enter element " + (i + 1) + ": ");
            array1[i] = sc.nextInt();
        }

        //Nhập mảng thứ hai
        int size2;
        System.out.println("===Enter the size of the second array===");
        do {
            System.out.print("Enter a size 2: ");
            size2 = sc.nextInt();
            if (size2 > 10) {
                System.out.println("Size does not exceed 10");
            }
        } while (size2 > 10);

        int[] array2;
        array2 = new int[size2];
        for (int i = 0; i < array2.length; i++) {
            System.out.print("Enter element " + (i + 1) + ": ");
            array2[i] = sc.nextInt();
        }

        // Phần cộng mảng
        int[] array3 = new int[size1 + size2];
        for (int i = 0; i < array1.length; i++) {
            array3[i] = array1[i];
        }
        for (int i = 0; i < array2.length; i++) {
            array3[size1 + i] = array2[i];
        }
        System.out.print("Merged array: " + Arrays.toString(array3));
    }
}
