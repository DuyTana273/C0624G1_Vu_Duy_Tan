package ss9_AutomatedTestingAndTDD.bai_tap.NextDayCalculator;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class NextDayCalculatorTest {

    @Test
    public void testNextDayCase1() {
        assertEquals("2/1/2018", NextDayCalculator.findNextDay(1, 1, 2018));
    }

    @Test
    public void testNextDayCase2() {
        assertEquals("1/2/2018", NextDayCalculator.findNextDay(31, 1, 2018));
    }

    @Test
    public void testNextDayCase3() {
        assertEquals("1/5/2018", NextDayCalculator.findNextDay(30, 4, 2018));
    }

    @Test
    public void testNextDayCase4() {
        assertEquals("1/3/2018", NextDayCalculator.findNextDay(28, 2, 2018));
    }

    @Test
    public void testNextDayCase5() {
        assertEquals("1/3/2020", NextDayCalculator.findNextDay(29, 2, 2020)); // Năm nhuận
    }

    @Test
    public void testNextDayCase6() {
        assertEquals("1/1/2019", NextDayCalculator.findNextDay(31, 12, 2018));
    }
}