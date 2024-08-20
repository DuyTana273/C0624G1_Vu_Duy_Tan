package ss7_AbstractClassAndInterface.bai_tap.Shape2;

public class ColorableTest {
    public static void main(String[] args) {
        Shape[] shapes = new Shape[4];
        shapes[0] = new Circle(3.0);
        shapes[1] = new Rectangle(2.0, 4.0);
        shapes[2] = new Square(5.0);
        shapes[3] = new Square(2.0);

        for (Shape shape : shapes) {
            System.out.println(shape);
            if (shape instanceof Colorable colorable) {
                colorable.howToColor();
            }
            System.out.println();
        }
    }
}
