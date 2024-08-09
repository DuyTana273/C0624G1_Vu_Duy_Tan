package ss2_Loop.thuc_hanh.CalculatorInterest;

import java.util.Scanner;

public class Intersest {
    public static void main(String[] args) {
        double money;
        double interest;
        int month;

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter money: ");
        money = sc.nextDouble();
        System.out.print("Enter monthly interest rate: ");
        interest = sc.nextDouble();
        System.out.print("Enter the month number: ");
        month = sc.nextInt();

        double  totalInterest = 0.0;
        for (int i = 0; i < month; i++) {
            totalInterest += money * (interest / 100)/12 * month;
        }
        System.out.print("Total of interest = " + totalInterest);
    }
}
