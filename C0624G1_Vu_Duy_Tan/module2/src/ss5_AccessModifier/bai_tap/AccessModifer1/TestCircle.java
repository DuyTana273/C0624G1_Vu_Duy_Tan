package ss5_AccessModifier.bai_tap.AccessModifer1;

public class TestCircle {
    private double radius = 1.0;
    private String color = "red";

    public TestCircle(double radius, String color) {
    }

    protected double getRadius() {
        return radius;
    }

    public double getArea() {
        return Math.PI * radius * radius;
    }

    @Override
    public String toString() {
        return "TestCircle{" +
                "radius=" + radius +
                ", color='" + color + '\'' +
                '}';
    }
}
