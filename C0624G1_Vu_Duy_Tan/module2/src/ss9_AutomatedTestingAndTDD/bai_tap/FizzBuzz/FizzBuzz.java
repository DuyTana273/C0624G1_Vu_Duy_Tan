package ss9_AutomatedTestingAndTDD.bai_tap.FizzBuzz;

public class FizzBuzz {
    public static String fizzBuzz(int number) {
        if (number <= 0) {
            throw new IllegalArgumentException("Number must be greater than 0");
        }

        boolean isFizz = number % 3 == 0 || String.valueOf(number).contains("3");
        boolean isBuzz = number % 5 == 0 || String.valueOf(number).contains("5");

        if (isFizz && isBuzz) {
            return "FizzBuzz";
        }
        if (isFizz) {
            return "Fizz";
        }
        if (isBuzz) {
            return "Buzz";
        }
        return numberToWords(number);
    }

    public static String numberToWords(int number) {
        String[] units = {"không", "một", "hai", "ba", "bốn", "năm", "sáu", "bảy", "tám", "chín"};
        if (number < 10) {
            return units[number];
        } else if (number < 100) {
            int tens = number / 10;
            int ones = number % 10;
            return units[tens] + " " + units[ones];
        }
        return "";
    }
}
