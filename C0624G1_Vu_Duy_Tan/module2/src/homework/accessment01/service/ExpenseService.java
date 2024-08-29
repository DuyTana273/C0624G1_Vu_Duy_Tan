package homework.accessment01.service;

import homework.accessment01.model.ElectricityExpense;
import homework.accessment01.model.Salary;
import homework.accessment01.model.TaxiExpense;

public class ExpenseService {
    private final ElectricityExpense electricityExpense;
    private final TaxiExpense taxiExpense;
    private final Salary salary;

    public ExpenseService(ElectricityExpense electricityExpense, TaxiExpense taxiExpense, Salary salary) {
        this.electricityExpense = electricityExpense;
        this.taxiExpense = taxiExpense;
        this.salary = salary;
    }

    public double calculateElectricityBill(double consumedKwh) {
        return electricityExpense.calculate(consumedKwh);
    }

    public double calculateTaxiBill(double distanceInKm) {
        return taxiExpense.calculate(distanceInKm);
    }

    public double calculateNetSalary(double grossSalary) {
        return salary.calculateSalary(grossSalary);
    }

    public double calculateRemainingIncome(double netSalary, double electricityFee, double taxiFee) {
        return netSalary - electricityFee - taxiFee;
    }
}
