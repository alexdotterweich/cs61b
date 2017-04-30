/* NOTE: The file Utils.java contains some functions that may be useful
 * in testing your answers. */

/** HW #2, Problem #1. */

/** List problem.
 *  @author Alexandra Dotterweich
 */
class Lists {
    /** Return the list of lists formed by breaking up L into "natural runs":
     *  that is, maximal ascending sublists, in the same order as
     *  the original.  For example, if L is (1, 3, 7, 5, 4, 6, 9, 10),
     *  then result is the three-item list ((1, 3, 7), (5), (4, 6, 9, 10)).
     *  Destructive: creates no new IntList items, and may modify the
     *  original list pointed to by L. */
    static IntList2 naturalRuns(IntList L) {
        IntList origin = L;
        IntList next = L;
        IntList2 list = new IntList2();
        IntList2 list2 = list;
        while (origin.tail != null) {
            if (origin.head < origin.tail.head) {
                origin = origin.tail;
                next = origin;
            } else {
                next = origin.tail;
                origin.tail = null;
                if (list.head == null) {
                    list.head = L;
                } else {
                    list2.tail = new IntList2(L, null);
                    list2 = list2.tail;
                }
                L = next;
                origin = next;
            }
        }
        list2.tail = new IntList2(L, null);
        return list;
    }
}
