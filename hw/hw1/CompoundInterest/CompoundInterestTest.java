import static org.junit.Assert.*;
import org.junit.Test;

public class CompoundInterestTest {

    @Test
    public void testNumYears() {
    /** Example assert statement for comparing integers.

    assertEquals(0, 0); */

        CompoundInterest numYearsTest = new CompoundInterest();
        int targetYear = 2015;
        assertEquals(1, numYearsTest.numYears(targetYear));
    }

    @Test
    public void testFutureValue() {
        double tolerance = 0.01;
        CompoundInterest futureValueTest = new CompoundInterest();
        assertEquals(12.544, futureValueTest.futureValue(10, 12, 2016), tolerance);
    }

    @Test
    public void testFutureValueReal() {
        double tolerance = 0.01;
        CompoundInterest futureValueRealTest = new CompoundInterest();
        assertEquals(11.8026496, futureValueRealTest.futureValueReal(10, 12, 2016, 3),
            tolerance);
    }

    @Test
    public void testTotalSavings() {
        double tolerance = 0.01;
        CompoundInterest totalSavingsTest = new CompoundInterest();
        assertEquals(16550, totalSavingsTest.totalSavings(5000, 2016, 10),
        tolerance);
    }

    @Test
    public void testTotalSavingsReal() {
        double tolerance = 0.01;
        CompoundInterest totalSR = new CompoundInterest();
        assertEquals(16027.445, totalSR.totalSavingsReal(5000, 2016, 10, 3), tolerance);
    }


    /* Run the unit tests in this file. */
    public static void main(String... args) {
        System.exit(ucb.junit.textui.runClasses(CompoundInterestTest.class));
    }
}
