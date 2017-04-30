import org.junit.Test;
import static org.junit.Assert.*;

/** FIXME
 *  @author Alexandra Dotterweich
 */

public class MatrixUtilsTest {
    /** FIXME
     */

    public static void main(String[] args) {
        System.exit(ucb.junit.textui.runClasses(MatrixUtilsTest.class));
    }

    @Test
    public void accumulateVertical() {
        double[][] a = new double[][] {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        double[][] b = new double[][] {{1, 2, 3}, {5, 6, 8}, {12, 13, 15}};
        assert(b == MatrixUtils.accumulateVertical(a));
    }

    @Test
    public void accumulate() {
        double[][] a = new double[][] {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        double[][] b = new double[][] {{1, 5, 12}, {2, 6, 13}, {3, 8, 15}};
        double[][] c = MatrixUtils.accumulateVertical(a);
        double[][] d = MatrixUtils.helper(c);
        assertTrue(b == d);
    }
}