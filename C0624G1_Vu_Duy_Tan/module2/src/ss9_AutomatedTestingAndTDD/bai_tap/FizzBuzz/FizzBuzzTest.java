package ss9_AutomatedTestingAndTDD.bai_tap.FizzBuzz;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FizzBuzzTest {

    @Test
    public void testFizz() {
        assertEquals("Fizz", FizzBuzz.fizzBuzz(3)); // Chia hết cho 3
        assertEquals("Fizz", FizzBuzz.fizzBuzz(13)); // Chứa số 3
    }

    @Test
    public void testBuzz() {
        assertEquals("Buzz", FizzBuzz.fizzBuzz(5)); // Chia hết cho 5
        assertEquals("Buzz", FizzBuzz.fizzBuzz(52)); // Chứa số 5
    }

    @Test
    public void testFizzBuzz() {
        assertEquals("FizzBuzz", FizzBuzz.fizzBuzz(15)); // Chia hết cho cả 3 và 5
        assertEquals("FizzBuzz", FizzBuzz.fizzBuzz(53)); // Chứa cả số 3 và 5
    }

    @Test
    public void testNumberToWords() {
        assertEquals("một", FizzBuzz.fizzBuzz(1)); // Không chia hết cho 3 hoặc 5, không chứa số 3 hoặc 5
        assertEquals("hai sáu", FizzBuzz.fizzBuzz(26)); // Không chia hết cho 3 hoặc 5, không chứa số 3 hoặc 5
    }
}
