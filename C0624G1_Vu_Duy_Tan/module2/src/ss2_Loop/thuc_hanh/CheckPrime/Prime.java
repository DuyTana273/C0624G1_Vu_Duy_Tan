package ss2_Loop.thuc_hanh.CheckPrime;

import java.util.Scanner;

public class Prime {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("enter the number : ");
        int number = sc.nextInt();

        boolean isPrime = true;
        if (number % 2 == 0) {
            isPrime = false;
        }
        for (int i = 2; i < Math.sqrt(number); i++) {
            if (number % i == 0) {
                isPrime = false;
            } else {
                isPrime = true;
            }
        }
        if (isPrime) {
            System.out.print(number + " is a prime number");
        } else {
            System.out.print(number + " is not a prime number");
        }
    }
}
