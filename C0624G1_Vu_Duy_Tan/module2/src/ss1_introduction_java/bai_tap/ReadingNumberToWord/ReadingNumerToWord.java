package ss1_introduction_java.bai_tap.ReadingNumberToWord;

import java.util.Scanner;

class ReadingNumerToWord {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a number with up to three digits: ");
        int number = sc.nextInt();

        if (number < 0 || number > 999) {
            System.out.println("Out of ability");
        } else {
            System.out.println("after conversion is: " + convertNumberToWord(number));
        }
    }

    public static String convertNumberToWord(int number) {
        if (number < 10) {
            return oneDigitToWord(number);
        } else if (number < 20) {
            return teenToWord(number);
        } else if (number < 100) {
            return twoDigitToWord(number);
        } else {
            return threeDigitToWord(number);
        }
    }

    public static String oneDigitToWord(int number) {
        switch (number) {
            case 0: return "Zero";
            case 1: return "One";
            case 2: return "Two";
            case 3: return "Three";
            case 4: return "Four";
            case 5: return "Five";
            case 6: return "Six";
            case 7: return "Seven";
            case 8: return "Eight";
            case 9: return "Nine";
            default: return "";
        }
    }

    public static String teenToWord(int number) {
        switch (number) {
            case 10: return "Ten";
            case 11: return "Eleven";
            case 12: return "Twelve";
            case 13: return "Thirteen";
            case 14: return "Fourteen";
            case 15: return "Fifteen";
            case 16: return "Sixteen";
            case 17: return "Seventeen";
            case 18: return "Eighteen";
            case 19: return "Nineteen";
            default: return "";
        }
    }

    public static String twoDigitToWord(int number) {
        int tens = number / 10;
        int ones = number % 10;
        String tensWord = "";
        String onesWord = oneDigitToWord(ones);

        switch (tens) {
            case 2: tensWord = "Twenty"; break;
            case 3: tensWord = "Thirty"; break;
            case 4: tensWord = "Forty"; break;
            case 5: tensWord = "Fifty"; break;
            case 6: tensWord = "Sixty"; break;
            case 7: tensWord = "Seventy"; break;
            case 8: tensWord = "Eighty"; break;
            case 9: tensWord = "Ninety"; break;
        }

        if (ones == 0) {
            return tensWord;
        } else {
            return tensWord + " " + onesWord;
        }
    }

    public static String threeDigitToWord(int number) {
        int hundreds = number / 100;
        int remainder = number % 100;
        String hundredsWord = oneDigitToWord(hundreds) + " Hundred";

        if (remainder == 0) {
            return hundredsWord;
        } else {
            return hundredsWord + " and " + convertNumberToWord(remainder);
        }
    }
}

