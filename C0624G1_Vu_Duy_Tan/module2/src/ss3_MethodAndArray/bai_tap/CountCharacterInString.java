package ss3_MethodAndArray.bai_tap;

import java.util.Scanner;

public class CountCharacterInString {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter a string: ");
        String s = sc.nextLine();
        System.out.print("Enter a character: ");
        char c = sc.next().charAt(0);

        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == c) {
                count++;
            }
        }
        System.out.print("Count: " + count);
    }
}
