package graph;

import java.util.LinkedList;

/* See restrictions in Graph.java. */

/** Implements a depth-first traversal of a graph.  Generally, the
 *  client will extend this class, overriding the visit and
 *  postVisit methods, as desired (by default, they do nothing).
 *  @author AlexDotterweich
 */
public class DepthFirstTraversal extends Traversal {

    /** A depth-first Traversal of G, using FRINGE as the fringe. */
    protected DepthFirstTraversal(Graph G) {
        super(G, new LL());
    }

    @Override
    protected boolean visit(int v) {
        return super.visit(v);
    }

    @Override
    protected boolean postVisit(int v) {
        return super.postVisit(v);
    }

    /** Wanted to remove the last item in the list and add to the back
     * of the list rather than the front for DepthFirst Traversals, however
     * I couldn't figure out how to do this exactly. */
    private static class LL extends LinkedList<Integer> {
        /** Wanted this to be the constructor for DFT's that extended the
         * linkedlist interface but made remove different. */
        private LL() {
            super(new LinkedList<Integer>());
        }

        @Override
        public Integer remove() {
            return removeLast();
        }
    }

}
