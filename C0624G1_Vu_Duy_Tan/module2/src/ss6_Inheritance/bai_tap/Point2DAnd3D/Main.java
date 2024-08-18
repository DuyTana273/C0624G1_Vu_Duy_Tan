package ss6_Inheritance.bai_tap.Point2DAnd3D;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Nhập dữ liệu cho Point2D
        System.out.print("Enter x coordinate of Point2D: ");
        float x2D = scanner.nextFloat();
        System.out.print("Enter y coordinate of Point2D: ");
        float y2D = scanner.nextFloat();

        Point2D point2D = new Point2D(x2D, y2D);
        System.out.println("Point2D: " + point2D.toString());

        // Nhập dữ liệu cho Point3D
        System.out.print("Enter x coordinate of Point3D: ");
        float x3D = scanner.nextFloat();
        System.out.print("Enter y coordinate of Point3D: ");
        float y3D = scanner.nextFloat();
        System.out.print("Enter z coordinate of Point3D: ");
        float z3D = scanner.nextFloat();

        Point3D point3D = new Point3D(x3D, y3D, z3D);
        System.out.println("Point3D: " + point3D.toString());

        scanner.close();
    }
}
