package homework.accessment01.model;

public class Salary {
    private double tax = 0.15;

    public double calculateSalary(double grossSalary) {
        return grossSalary - (grossSalary * tax);
    }
}
