package ss6_Inheritance.bai_tap.CircleAndCylinder;

public class Cylinder extends Circle{
    private double height;

    public Cylinder() {
        super();
        this.height = 1.0;
    }

    public Cylinder(double radius, String color, double height) {
        super(radius, color);
        this.height = height;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getVolume() {
        return getArea() * height;
    }

    @Override
    public String toString() {
        return "Cylinder[radius=" + getRadius() + ", color=" + getColor() + ", height=" + height + ", volume=" + getVolume() + "]";
    }
}
