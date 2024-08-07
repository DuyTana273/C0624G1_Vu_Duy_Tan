package ss1_introduction_java.bai_tap.AppCurrencyConverter;

import java.util.Scanner;

public class AppCurrencyConverter {
    public static void main(String[] args) {
        int VND = 23000;
        int USD;
        System.out.print("enter amount USD: ");
        Scanner sc = new Scanner(System.in);
        USD = sc.nextInt();
        int convert = USD * VND;

        System.out.println("Price after conversion: " + convert + " VND");
    }
}
