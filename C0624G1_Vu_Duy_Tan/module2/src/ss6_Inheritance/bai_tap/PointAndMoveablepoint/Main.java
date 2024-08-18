package ss6_Inheritance.bai_tap.PointAndMoveablepoint;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Nhập dữ liệu cho đối tượng Point
        System.out.print("Nhập giá trị x cho Point: ");
        float x = scanner.nextFloat();
        System.out.print("Nhập giá trị y cho Point: ");
        float y = scanner.nextFloat();

        Point point = new Point(x, y);
        System.out.println("Point: " + point);

        // Nhập dữ liệu cho đối tượng MoveablePoint
        System.out.print("Nhập giá trị x cho MoveablePoint: ");
        x = scanner.nextFloat();
        System.out.print("Nhập giá trị y cho MoveablePoint: ");
        y = scanner.nextFloat();
        System.out.print("Nhập giá trị xSpeed cho MoveablePoint: ");
        float xSpeed = scanner.nextFloat();
        System.out.print("Nhập giá trị ySpeed cho MoveablePoint: ");
        float ySpeed = scanner.nextFloat();

        MoveablePoint moveablePoint = new MoveablePoint(x, y, xSpeed, ySpeed);
        System.out.println("Trước khi di chuyển: " + moveablePoint);
        moveablePoint.move();
        System.out.println("Sau khi di chuyển: " + moveablePoint);
    }
}
