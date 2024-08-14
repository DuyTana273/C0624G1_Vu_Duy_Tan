package ss4_ClassAndObject.thuc_hanh.Rectangle;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the size width: ");
        int width = sc.nextInt();
        System.out.print("Enter the size height: ");
        int height = sc.nextInt();
        Calculator calculator = new Calculator(width, height);

        System.out.println("======Your Rectangle====== \n"+ calculator.display());
        System.out.println("Rectangle has Area = " + calculator.getArea());
        System.out.print("Rectangle has Perimeter =  " + calculator.getPerimeter());
    }
}
