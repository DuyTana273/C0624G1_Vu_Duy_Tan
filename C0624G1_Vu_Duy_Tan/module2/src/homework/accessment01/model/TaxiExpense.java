package homework.accessment01.model;

public class TaxiExpense {
    private double ratePerKm = 10000;

    public double calculate(double distanceInKm) {
        return distanceInKm * ratePerKm;
    }
}
