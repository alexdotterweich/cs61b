import static org.junit.Assert.*;
import org.junit.Test;

public class IntListTest {

    /** Example test that verifies correctness of the IntList.list static 
     *  method. The main point of this is to convince you that 
     *  assertEquals knows how to handle IntLists just fine.
     */

    @Test 
    public void testList() {
        IntList one = new IntList(1, null);
        IntList twoOne = new IntList(2, one);
        IntList threeTwoOne = new IntList(3, twoOne);

        IntList x = IntList.list(3, 2, 1);
        assertEquals(threeTwoOne, x);
    }

    /** Do not use the new keyword in your tests. You can create
     *  lists using the handy IntList.list method.  
     * 
     *  Make sure to include test cases involving lists of various sizes
     *  on both sides of the operation. That includes the empty list, which
     *  can be instantiated, for example, with 
     *  IntList empty = IntList.list(). 
     *
     *  Keep in mind that dcatenate(A, B) is NOT required to leave A untouched.
     *  Anything can happen to A. 
     */

    @Test 
    public void testDcatenate() {
        IntList a = IntList.list(2, 3, 4);
        IntList b = IntList.list(5, 6);
        IntList c = IntList.list(1, 2, 3, 4, 5, 6, 7, 8, 9, 0);
        IntList d = IntList.list(2, 4, 6, 8, 10);
        IntList e = new IntList();
        IntList dcatenateTest = new IntList();
        assertEquals(IntList.list(2, 3, 4, 5, 6), dcatenateTest.dcatenate(a, b));
        IntList dcatenateTest2 = new IntList();
        assertEquals(IntList.list(1, 2, 3, 4, 5, 6, 7, 8, 9, 0, 2, 4 ,6, 8, 10), dcatenateTest2.dcatenate(c, d));
        IntList dcatenateTest3 = new IntList();
        assertEquals(IntList.list(0, 2, 3, 4, 5, 6), dcatenateTest3.dcatenate(e, a));

    }

    /** Tests that subtail works properly. Again, don't use new.
     *   
     *  Make sure to test that subtail does not modify the list. 
     */

    @Test 
    public void testSubtail() {
      IntList a = IntList.list(2, 3, 4);
      IntList b = IntList.list(1, 2, 3, 4, 5, 6, 7, 8, 9, 0);
      assertEquals(IntList.list(3, 4), a.subTail(a, 1));
      assertEquals(b, b.subTail(b, 0));
      assertEquals(IntList.list(5, 6, 7, 8, 9, 0), b.subTail(b, 4));


    }

    /** Tests that sublist works properly. Again, don't use new.
     *   
     *  Make sure to test that sublist does not modify the list. 
     */

    @Test 
    public void testSublist() {
      IntList a = IntList.list(2, 3, 4);
      IntList b = IntList.list(1, 2, 3, 4, 5, 6, 7, 8, 9, 0);
      assertEquals(IntList.list(2), a.sublist(a, 0, 1));
      assertEquals(IntList.list(4, 5, 6), b.sublist(b, 3, 3));

    }

    /** Tests that dSublist works properly. Again, don't use new.
     *   
     *  As with testDcatenate, it is not safe to assume that list passed
     *  to dSublist is the same after any call to dSublist 
     */

    @Test 
    public void testDsublist() {
      IntList a = IntList.list(2, 3, 4);
      IntList b = IntList.list(1, 2, 3, 4, 5, 6, 7, 8, 9, 0);
      assertEquals(IntList.list(2), a.dsublist(a, 0, 1));
      assertEquals(IntList.list(4, 5, 6), b.dsublist(b, 3, 3));
    }


    /* Run the unit tests in this file. */
    public static void main(String... args) {
        System.exit(ucb.junit.textui.runClasses(IntListTest.class));
    }       
}   
