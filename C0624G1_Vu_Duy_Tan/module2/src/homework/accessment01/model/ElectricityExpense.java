package homework.accessment01.model;

public class ElectricityExpense {
    private double ratePerKwh = 4000;

    public double calculate(double consumedKwh) {
        return consumedKwh * ratePerKwh;
    }
}