package ss8_CleanCodeAndRefactoring.thuc_hanh.FizzBuzz;

public class FizzBuzz {
    public static String fizzBuzz(int number) {
        if (number % 3 == 0 && number % 5 == 0)
            return "FizzBuzz";

        if (number % 3 == 0)
            return "Fizz";

        if (number % 5 == 0)
            return "Buzz";

        return number + "";
    }
}