package ss3_MethodAndArray.thuc_hanh;

import java.util.Scanner;

public class ConvertTemperature {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        double fahrenheit;
        double celsius;
        int choice;

        do {
            System.out.println("\n=== Temperature Converter Menu ===");
            System.out.println("1. Convert Fahrenheit to Celsius");
            System.out.println("2. Convert Celsius to Fahrenheit");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = input.nextInt();

            switch (choice) {
                case 1: {
                    System.out.print("Enter Fahrenheit: ");
                    fahrenheit = input.nextDouble();
                    System.out.printf("Fahrenheit to Celsius: %.2f°C%n", fahrenheitToCelsius(fahrenheit));
                    break;
                }
                case 2: {
                    System.out.print("Enter Celsius: ");
                    celsius = input.nextDouble();
                    System.out.printf("Celsius to Fahrenheit: %.2f°F%n", celsiusToFahrenheit(celsius));
                    break;
                }
                case 0:
                    System.out.println("Exiting program...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (true);
    }

    public static double celsiusToFahrenheit(double celsius) {
        return (9.0 / 5) * celsius + 32;
    }

    public static double fahrenheitToCelsius(double fahrenheit) {
        return (5.0 / 9) * (fahrenheit - 32);
    }
}
