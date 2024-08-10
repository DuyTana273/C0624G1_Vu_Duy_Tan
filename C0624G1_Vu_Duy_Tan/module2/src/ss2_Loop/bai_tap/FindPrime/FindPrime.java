package ss2_Loop.bai_tap.FindPrime;

import java.util.Scanner;

public class FindPrime {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of primes to print: ");

        if (!scanner.hasNextInt()) {
            System.out.println("Please enter a valid number.");
        } else {
            int numbers = scanner.nextInt();
            int count = 0;
            int N = 2;

            if (numbers < 2) {
                System.out.println("The number is too small");
            } else {
                while (count < numbers) {
                    boolean isPrime = true;

                    for (int i = 2; i <= Math.sqrt(N); i++) {
                        if (N % i == 0) {
                            isPrime = false;
                            break;
                        }
                    }

                    if (isPrime) {
                        System.out.print(N + " ");
                        count++;
                    }
                    N++;
                }
            }
        }
    }
}
