package graph;

import java.util.ArrayList;

/* See restrictions in Graph.java. */

/** Represents a general unlabeled directed graph whose vertices are denoted by
 *  positive integers. Graphs may have self edges.
 *
 *  @author AlexDotterweich
 */
public class DirectedGraph extends GraphObj {

    @Override
    public boolean isDirected() {
        return true;
    }

    @Override
    public int inDegree(int v) {
        ArrayList<Object> valueV = hashmapReturn().get(v);
        ArrayList<Integer> newValueV = (ArrayList<Integer>) valueV.get(0);
        return newValueV.size();
    }

    @Override
    public int predecessor(int v, int k) {
        if (hashmapReturn().containsKey(v)) {
            ArrayList<Object> valueV = hashmapReturn().get(v);
            ArrayList<Integer> newValueV = (ArrayList<Integer>) valueV.get(0);
            if (k < newValueV.size() && k > -1) {
                int index = (int) newValueV.get(k);
                return index;
            }
        }
        return 0;
    }

    @Override
    public Iteration<Integer> predecessors(int v) {
        ArrayList<Integer> newV = new ArrayList<Integer>();
        if (contains(v)) {
            ArrayList<Object> valueV = hashmapReturn().get(v);
            ArrayList<Integer> newValueV = (ArrayList<Integer>) valueV.get(0);
            for (Object obj : newValueV) {
                newV.add((Integer) obj);
            }
            return Iteration.iteration(newV);
        }
        return Iteration.iteration(newV);
    }

}
