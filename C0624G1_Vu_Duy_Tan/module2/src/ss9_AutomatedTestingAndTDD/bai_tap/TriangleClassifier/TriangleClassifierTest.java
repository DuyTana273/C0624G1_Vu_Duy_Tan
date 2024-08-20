package ss9_AutomatedTestingAndTDD.bai_tap.TriangleClassifier;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TriangleClassifierTest {

    @Test
    public void testEquilateralTriangle() {
        assertEquals("tam giác đều", TriangleClassifier.classifyTriangle(2, 2, 2));
    }

    @Test
    public void testIsoscelesTriangle() {
        assertEquals("tam giác cân", TriangleClassifier.classifyTriangle(2, 2, 3));
    }

    @Test
    public void testNormalTriangle() {
        assertEquals("tam giác thường", TriangleClassifier.classifyTriangle(3, 4, 5));
    }

    @Test
    public void testNotATriangleCase1() {
        assertEquals("không phải là tam giác", TriangleClassifier.classifyTriangle(8, 2, 3));
    }

    @Test
    public void testNotATriangleCase2() {
        assertEquals("không phải là tam giác", TriangleClassifier.classifyTriangle(-1, 2, 1));
    }

    @Test
    public void testNotATriangleCase3() {
        assertEquals("không phải là tam giác", TriangleClassifier.classifyTriangle(0, 1, 1));
    }
}
