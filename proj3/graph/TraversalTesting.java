package graph;

import java.util.ArrayList;
import java.util.Arrays;
import org.junit.Test;

import static org.junit.Assert.*;

/** Unit tests for the Graph class.
*  @author AlexDotterweich
*/
public class TraversalTesting {

    @Test
    public void breathFirstTraversal() {
        DirectedGraph g = new DirectedGraph();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add(1, 2);
        g.add(1, 3);
        g.add(2, 4);
        g.add(2, 5);
        BreathFirstTester d = new BreathFirstTester(g);
        d.traverse(1);
        ArrayList<Integer> t1 =
                new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5));
        ArrayList<Integer> t2 =
                new ArrayList<Integer>(Arrays.asList(1, 2, 3, 5, 4));
        ArrayList<Integer> t3 =
                new ArrayList<Integer>(Arrays.asList(1, 3, 2, 4, 5));
        ArrayList<Integer> t4 =
                new ArrayList<Integer>(Arrays.asList(1, 3, 2, 5, 4));
        assertEquals(true, equals(t1, _outputHere) || equals(t2, _outputHere)
                || equals(t3, _outputHere) || equals(t4, _outputHere));
    }

    @Test
    public void depthFirstTraversal() {
        DirectedGraph g = new DirectedGraph();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add(1, 2);
        g.add(1, 3);
        g.add(2, 4);
        g.add(2, 5);
        DepthFirstTester d = new DepthFirstTester(g);
        d.traverse(1);
        ArrayList<Integer> t1 =
                new ArrayList<Integer>(Arrays.asList(1, 2, 4, 5, 3));
        ArrayList<Integer> t2 =
                new ArrayList<Integer>(Arrays.asList(1, 3, 2, 4, 5));
        ArrayList<Integer> t3 =
                new ArrayList<Integer>(Arrays.asList(1, 2, 5, 4, 3));
        ArrayList<Integer> t4 =
                new ArrayList<Integer>(Arrays.asList(1, 3, 2, 5, 4));
        assertEquals(true, equals(t1, _outputHere) || equals(t2, _outputHere)
                || equals(t3, _outputHere) || equals(t4, _outputHere));
    }

    @Test
    public void depthFirstTraversal2() {
        DirectedGraph g = new DirectedGraph();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add(1, 2);
        g.add(1, 5);
        g.add(2, 3);
        g.add(2, 4);
        g.add(5, 6);
        DepthFirstTester d = new DepthFirstTester(g);
        d.traverse(1);
        ArrayList<Integer> t1 =
                new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5, 6));
        ArrayList<Integer> t2 =
                new ArrayList<Integer>(Arrays.asList(1, 5, 6, 2, 4, 3));
        ArrayList<Integer> t3 =
                new ArrayList<Integer>(Arrays.asList(1, 2, 4, 3, 5, 6));
        ArrayList<Integer> t4 =
                new ArrayList<Integer>(Arrays.asList(1, 5, 6, 3, 4, 3));
        assertEquals(true, equals(t1, _outputHere) || equals(t2, _outputHere)
                || equals(t3, _outputHere) || equals(t4, _outputHere));
    }

    @Test
    public void breathFirstTraversal2() {
        DirectedGraph g = new DirectedGraph();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add(1, 2);
        g.add(1, 5);
        g.add(2, 3);
        g.add(2, 4);
        g.add(5, 6);
        BreathFirstTester d = new BreathFirstTester(g);
        d.traverse(1);
        ArrayList<Integer> t1 =
                new ArrayList<Integer>(Arrays.asList(1, 2, 5, 3, 4, 6));
        ArrayList<Integer> t2 =
                new ArrayList<Integer>(Arrays.asList(1, 5, 2, 3, 4, 6));
        ArrayList<Integer> t3 =
                new ArrayList<Integer>(Arrays.asList(1, 2, 5, 4, 3, 6));
        ArrayList<Integer> t4 =
                new ArrayList<Integer>(Arrays.asList(1, 5, 2, 4, 3, 6));
        assertEquals(true, equals(t1, _outputHere) || equals(t2, _outputHere)
                || equals(t3, _outputHere) || equals(t4, _outputHere));
    }

    public void postOrderDF() {
        DirectedGraph g = new DirectedGraph();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add(1, 2);
        g.add(1, 3);
        g.add(2, 4);
        g.add(2, 5);
        DepthFirstTester d = new DepthFirstTester(g);
        d.traverse(1);
        ArrayList<Integer> t1 =
                new ArrayList<Integer>(Arrays.asList(3, 5, 4, 1, 2));
        ArrayList<Integer> t2 =
                new ArrayList<Integer>(Arrays.asList(5, 4, 3, 2, 1));
        ArrayList<Integer> t3 =
                new ArrayList<Integer>(Arrays.asList(3, 4, 5, 2, 1));
        ArrayList<Integer> t4 =
                new ArrayList<Integer>(Arrays.asList(4, 5, 2, 3, 1));
        assertEquals(true, equals(t1, reverse(_outputHere))
                || equals(t2, reverse(_outputHere))
                || equals(t3, reverse(_outputHere))
                || equals(t4, reverse(_outputHere)));
    }

    /** Reverses the output. */
    private ArrayList<Integer> reverse(ArrayList<Integer> a) {
        ArrayList<Integer> toReturn = new ArrayList<Integer>();
        for (int i = a.size(); i > 0; i -= 1) {
            toReturn.add(a.get(i), i);
        }
        return a;
    }
    /** Sees if two ArrayLists are identical. */
    private Boolean equals(ArrayList<Integer> t, ArrayList<Integer> m) {
        int howMany = 0;
        for (int i = 0; i < t.size(); i += 1) {
            if (t.get(i) == m.get(i)) {
                howMany += 1;
            }
        }
        if (howMany == t.size()) {
            return true;
        }
        return false;
    }

    public class DepthFirstTester extends DepthFirstTraversal {
        /** A depth-first Traversal of G, using FRINGE as the fringe. */
        protected DepthFirstTester(Graph G) {
            super(G);
        }

        @Override
        protected boolean visit(int v) {
            _outputHere.add(v);
            return super.visit(v);
        }

        @Override
        protected boolean postVisit(int v) {
            return super.postVisit(v);
        }
    }

    public class BreathFirstTester extends BreadthFirstTraversal {
        /** A depth-first Traversal of G, using FRINGE as the fringe. */
        protected BreathFirstTester(Graph G) {
            super(G);
        }

        @Override
        protected boolean visit(int v) {
            _outputHere.add(v);
            return super.visit(v);
        }

    }
    ArrayList<Integer> _outputHere = new ArrayList<Integer>();
}
