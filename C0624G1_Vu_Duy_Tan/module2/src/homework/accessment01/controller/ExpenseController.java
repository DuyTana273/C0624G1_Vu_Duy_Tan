package homework.accessment01.controller;

import homework.accessment01.service.ExpenseService;
import homework.accessment01.view.ExpenseView;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ExpenseController {
    private final ExpenseView view;
    private final ExpenseService service;
    private final Scanner scanner = new Scanner(System.in);

    public ExpenseController(ExpenseView view, ExpenseService service) {
        this.view = view;
        this.service = service;
    }

    public void run() {
        double electricityFee = 0, taxiFee = 0, netSalary = 0;
        boolean validInput;

        while (true) {
            String choice = String.valueOf(view.showMenu());
            switch (choice) {
                case "1":
                    double consumedKwh = 0.0;
                    validInput = false;
                    do {
                        try {
                            System.out.println("** Giá tiền/kw: 4000 VND **");
                            System.out.print("Nhập số điện đã sử dụng (kWh): ");
                            consumedKwh = scanner.nextDouble();

                            if (consumedKwh < 0) {
                                System.out.println("Số kWh không thể là số âm. Vui lòng nhập lại.");
                            } else {
                                validInput = true;
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Giá trị vừa nhập không hợp lệ. Vui lòng nhập một số.");
                            scanner.next();
                        }
                    } while (!validInput);

                    electricityFee = service.calculateElectricityBill(consumedKwh);
                    view.displayResult("=====> Tiền điện cuối tháng: " + formatCurrency(electricityFee));
                    break;

                case "2":
                    double distanceInKm = 0.0;
                    validInput = false;
                    do {
                        try {
                            System.out.println("** Giá tiền/km: 10000 VND **");
                            System.out.print("Nhập khoảng cách đã đi (Km): ");
                            distanceInKm = scanner.nextDouble();

                            if (distanceInKm < 0) {
                                System.out.println("Số Km không thể là số âm. Vui lòng nhập lại.");
                            } else {
                                validInput = true;
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Giá trị vừa nhập không hợp lệ. Vui lòng nhập một số.");
                            scanner.next();
                        }
                    } while (!validInput);

                    taxiFee = service.calculateTaxiBill(distanceInKm);
                    view.displayResult("=====> Tiền taxi cuối tháng: " + formatCurrency(taxiFee));
                    break;

                case "3":
                    double grossSalary = 0.0;
                    validInput = false;
                    do {
                        try {
                            System.out.print("Nhập tiền lương hàng tháng: ");
                            grossSalary = scanner.nextDouble();

                            if (grossSalary < 0) {
                                System.out.println("Tiền lương không thể là số âm. Vui lòng nhập lại.");
                            } else {
                                validInput = true;
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Giá trị vừa nhập không hợp lệ. Vui lòng nhập một số.");
                            scanner.next();
                        }
                    } while (!validInput);

                    netSalary = service.calculateNetSalary(grossSalary);
                    view.displayResult("=====> Tiền lương sau thuế cuối tháng: " + formatCurrency(netSalary));
                    break;

                case "4":
                    double totalIncome = service.calculateRemainingIncome(netSalary, electricityFee, taxiFee);
                    view.displayResult("=====> Tổng thu nhập sau khi chi tiêu: " + formatCurrency(totalIncome));
                    break;

                default:
                    view.displayThankYouMessage();
                    return;
            }
        }
    }

    private String formatCurrency(double amount) {
        return String.format("%,.0f VND", amount);
    }
}
