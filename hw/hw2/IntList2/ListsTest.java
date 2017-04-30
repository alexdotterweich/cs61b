import org.junit.Test;
import static org.junit.Assert.*;

/** FIXME
 *
 *  @author Alexandra Dotterweich
 */

public class ListsTest {
    /** It might initially seem daunting to try to set up
    Intlist2 expected.
    There is an easy way to get the IntList2 that you want in just
    few lines of code! Make note of the IntList2.list method that
    takes as input a 2D array. */

    public static void main(String[] args) {
        System.exit(ucb.junit.textui.runClasses(ListsTest.class));
    }

    @Test
    public void testNaturalRuns() {
        IntList a = IntList.list(1, 3, 7, 5, 4, 6, 9, 10);
        IntList b = IntList.list(1, 3, 7);
        IntList c = IntList.list(5);
        IntList d = IntList.list(4, 6, 9, 10);
        IntList2 e = IntList2.list(b, c, d);
        assertEquals(e, Lists.naturalRuns(a));
        IntList f = IntList.list(3, 3, 3);
        IntList g = IntList.list(3);
        IntList2 h = IntList2.list(g, g, g);
        assertEquals(h, Lists.naturalRuns(f));
    }
}
