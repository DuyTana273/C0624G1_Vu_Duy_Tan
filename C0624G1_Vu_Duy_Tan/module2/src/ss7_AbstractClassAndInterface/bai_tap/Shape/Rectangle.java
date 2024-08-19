package ss7_AbstractClassAndInterface.bai_tap.Shape;

public class Rectangle extends Shape implements Resizeable {
    private double width;
    private double length;

    public Rectangle() {
    }

    public Rectangle(double width, double length) {
        this.width = width;
        this.length = length;
    }

    public Rectangle(double width, double length, String color, boolean filled) {
        super(color, filled);
        this.width = width;
        this.length = length;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public double getArea() {
        return width * length;
    }

    public double getPerimeter() {
        return 2 * (width + length);
    }

    @Override
    public void resize(double percent) {
        this.width *= (1 + percent / 100);
        this.length *= (1 + percent / 100);
    }

    @Override
    public String toString() {
        return "A Rectangle with width=" + getWidth() + " and length=" + getLength() + ", which is a subclass of " + super.toString();
    }
}
