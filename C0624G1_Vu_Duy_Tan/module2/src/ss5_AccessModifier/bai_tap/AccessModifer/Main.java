package ss5_AccessModifier.bai_tap.AccessModifer;

public class Main {
    public static void main(String[] args) {
        TestCircle circle = new TestCircle(2.0, "red");
        
        System.out.println("Circle: " + circle.toString());
        System.out.println("Area: " + circle.getArea());
    }
}
