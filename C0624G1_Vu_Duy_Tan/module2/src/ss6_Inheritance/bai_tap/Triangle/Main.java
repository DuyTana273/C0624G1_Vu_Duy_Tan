package ss6_Inheritance.bai_tap.Triangle;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Nhập dữ liệu cho đối tượng Triangle
        System.out.print("Enter the size 1 of the triangle: ");
        double side1 = scanner.nextDouble();
        System.out.print("Enter the size 2 of the triangle: ");
        double side2 = scanner.nextDouble();
        System.out.print("Enter the size 3 of the triangle: ");
        double side3 = scanner.nextDouble();

        Triangle triangle = new Triangle(side1, side2, side3);

        // Nhập màu sắc và trạng thái của hình
        System.out.print("Enter the color of the triangle: ");
        String color = scanner.next();
        System.out.print("Is the triangle colored (true/false)? ");
        boolean filled = scanner.nextBoolean();

        triangle.setColor(color);
        triangle.setFilled(filled);

        // Hiển thị thông tin tam giác
        System.out.println("Info Triangle" + triangle);
        System.out.println("Area Triangle: " + triangle.getArea());
        System.out.println("Perimeter Triangle: " + triangle.getPerimeter());
    }
}
