import org.junit.Test;
import static org.junit.Assert.*;

/** FIXME
 *  @author Alexandra Dotterweich
 */

public class ArraysTest {
    /** FIXME
     */

    public static void main(String[] args) {
        System.exit(ucb.junit.textui.runClasses(ArraysTest.class));
    }

    @Test
    public void remove() {
        int[] a = new int[]{1, 2, 3};
        int[] b = new int[]{1, 3};
        assertTrue(Utils.equals(b, 0, Arrays.remove(a, 1, 1), 0, b.length));
    }

    @Test
    public void catenate() {
        int[] c = new int[]{1, 2, 3};
        int[] d = new int[]{4, 5, 6};
        int[] e = new int[]{1, 2, 3, 4, 5, 6};
        assertTrue(Utils.equals(e, 0, Arrays.catenate(c, d), 0, e.length));
        int[] f = new int[]{};
        int[] g = new int[]{};
        int[] h = new int[]{};
        assertTrue(Utils.equals(h, 0, Arrays.catenate(f, g), 0, h.length));
    }
}
