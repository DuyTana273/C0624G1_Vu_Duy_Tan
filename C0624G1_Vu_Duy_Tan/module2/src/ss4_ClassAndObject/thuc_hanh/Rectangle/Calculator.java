package ss4_ClassAndObject.thuc_hanh.Rectangle;

public class Calculator {
    private int width, height;
    public Calculator(int width, int height) {
        this.width = width;
        this.height = height;
    }

//    public int getWidth() {
//        return width;
//    }
//
//    public void setWidth(int width) {
//        this.width = width;
//    }
//
//    public int getHeight() {
//        return height;
//    }
//
//    public void setHeight(int height) {
//        this.height = height;
//    }

    public String display() {
        return "Rectangle " + "width = " + width + ", height = " + height + " ";
    }

    public double getArea() {
        return this.width * this.height;
    }

    public double getPerimeter() {
        return (this.width + this.height) * 2;
    }
}
