package ss7_AbstractClassAndInterface.bai_tap.Shape;

import java.util.Random;

public class ResizeableTest {
    public static void main(String[] args) {
        Resizeable[] shapes = new Resizeable[3];
        shapes[0] = new Circle(3.0);
        shapes[1] = new Rectangle(2.0, 4.0);
        shapes[2] = new Square(5.0);

        Random rand = new Random();

        for (Resizeable shape : shapes) {
            double areaBefore = 0;
            if (shape instanceof Circle) {
                areaBefore = ((Circle) shape).getArea();
            } else if (shape instanceof Rectangle) {
                areaBefore = ((Rectangle) shape).getArea();
            }

            System.out.println("Area before resize: " + areaBefore);

            double percent = 1 + rand.nextInt(100);
            shape.resize(percent);

            double areaAfter = 0;
            if (shape instanceof Circle) {
                areaAfter = ((Circle) shape).getArea();
            } else if (shape instanceof Rectangle) {
                areaAfter = ((Rectangle) shape).getArea();
            }

            System.out.println("Resize percent: " + percent + "%");
            System.out.println("Area after resize: " + areaAfter);
            System.out.println();
        }
    }
}
