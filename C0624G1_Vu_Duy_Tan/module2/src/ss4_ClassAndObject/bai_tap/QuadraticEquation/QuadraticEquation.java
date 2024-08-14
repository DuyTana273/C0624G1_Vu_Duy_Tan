package ss4_ClassAndObject.bai_tap.QuadraticEquation;

import java.util.Scanner;

import static java.lang.Double.NaN;

public class QuadraticEquation {
    private double a, b, c;

    public QuadraticEquation(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public double getA() {
        return a;
    }

    public void setA(double a) {
        this.a = a;
    }

    public double getB() {
        return b;
    }

    public void setB(double b) {
        this.b = b;
    }

    public double getC() {
        return c;
    }

    public void setC(double c) {
        this.c = c;
    }

    public double getDiscriminant() {
        return Math.pow(b, 2) - 4 * a * c;
    }

    public double getRoot1() {
        double delta = getDiscriminant();

        if (delta < 0) {
            System.out.println("Vô nghiệm");
        }
        return (-b - Math.sqrt(delta)) / (2 * a);
    }

    public double getRoot2() {
        double delta = getDiscriminant();

        if (delta < 0) {
            return NaN;
        }
        return (-b + Math.sqrt(delta)) / (2 * a);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number a: ");
        double a = sc.nextDouble();
        System.out.print("Enter number b: ");
        double b = sc.nextDouble();
        System.out.print("Enter number c: ");
        double c = sc.nextDouble();
        QuadraticEquation qe = new QuadraticEquation(a, b, c);
        double root1 = qe.getRoot1();
        double root2 = qe.getRoot2();

        System.out.println("Root 1: " + root1);
        System.out.println("Root 2: " + root2);
    }
}
