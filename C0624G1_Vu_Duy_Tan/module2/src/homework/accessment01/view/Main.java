package homework.accessment01.view;

import homework.accessment01.controller.ExpenseController;
import homework.accessment01.model.ElectricityExpense;
import homework.accessment01.model.Salary;
import homework.accessment01.model.TaxiExpense;
import homework.accessment01.service.ExpenseService;

public class Main {

    public static void main(String[] args) {
        ExpenseView view = new ExpenseView();
        ElectricityExpense electricityExpense = new ElectricityExpense();
        TaxiExpense taxiExpense = new TaxiExpense();
        Salary salary = new Salary();
        ExpenseService service = new ExpenseService(electricityExpense, taxiExpense, salary);

        ExpenseController controller = new ExpenseController(view, service);
        controller.run();
    }
}
