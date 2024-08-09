package ss2_Loop.Ex.Ex03;

import java.util.Arrays;

public class TotalAllElementInArray {
    public static void main(String[] args) {
        int[][] matrix = new int[2][2];
        int total1 = 0;
// Tính tổng tất cả các phần tử trong mảng
        for (int row = 0; row < matrix.length; row++) {
            for (int column = 0; column < matrix[row].length; column++) {
                matrix[row][column] = (int) (Math.random() * 100);
                total1 += matrix[row][column];
            }
        }
// Tính tổng từng cột
        for (int column = 0; column < matrix[0].length; column++) {
            int total2 = 0;
            for (int[] its : matrix) total2 += its[column];
            System.out.println("Sum for column " + column + " is " + total2);
        }
        System.out.println(Arrays.deepToString(matrix));
        System.out.println(total1);
    }
}
