import org.junit.Test;
import static org.junit.Assert.*;

/** Perform tests of the DoubleChain class
 */

public class TestDoubleChain {

    /** Tests the constructor of DoubleChain */
    @Test
    public void testConstructor() {
        DoubleChain d = new DoubleChain(5);
        assertEquals(d.val, 5, 1e-6);
        assertEquals(d.prev, null);
        assertEquals(d.next, null);
    }

    /** Tests the DoubleChainClass
     */
    @Test
    public void testRest() {
        DoubleChain a = new DoubleChain(5);
        DoubleChain b = new DoubleChain(6);
        a.next = b;
        b.prev = a;
        assertEquals(6, DoubleChain.getBack(b).val, 1e-6);
        assertEquals(5, DoubleChain.getFront(a).val, 1e-6);
        DoubleChain.insertFront(a, 4);
        assertEquals(4, DoubleChain.getFront(a).val, 1e-6);
        DoubleChain.insertBack(b, 7);
        assertEquals(7, DoubleChain.getBack(b).val, 1e-6);
    }

    public static void main(String[] args) {
        ucb.junit.textui.runClasses(TestDoubleChain.class);
    }
}
