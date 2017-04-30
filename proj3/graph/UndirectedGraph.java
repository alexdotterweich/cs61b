package graph;

import java.util.ArrayList;

/* See restrictions in Graph.java. */

/** Represents an undirected graph.  Out edges and in edges are not
 *  distinguished.  Likewise for successors and predecessors.
 *
 *  @author AlexDotterweich
 */
public class UndirectedGraph extends GraphObj {

    @Override
    public boolean isDirected() {
        return false;
    }

    @Override
    public int inDegree(int v) {
        return outDegree(v);
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

    @Override
    public boolean contains(int u, int v) {
        if (contains(u) && contains(v)) {
            ArrayList<Object> valueU = hashmapReturn().get(u);
            ArrayList<Object> valueV = hashmapReturn().get(v);
            for (Object item : valueU) {
                if (item instanceof ArrayList<?>) {
                    @SuppressWarnings("unchecked")
                    ArrayList<Integer> newValueU =
                        (ArrayList<Integer>) valueU.get(0);
                    for (int item2 : newValueU) {
                        if (item2 == v) {
                            return true;
                        }
                    }
                } else {
                    for (int i = 1; i < valueU.size() - 1; i += 1) {
                        if ((Integer) valueU.get(i) == v) {
                            return true;
                        }
                    }
                }
            }
            for (Object itemz : valueV) {
                if (itemz instanceof ArrayList<?>) {
                    @SuppressWarnings("unchecked")
                    ArrayList<Integer> newValueV =
                        (ArrayList<Integer>) valueV.get(0);
                    for (int item2 : newValueV) {
                        if (item2 == u) {
                            return true;
                        }
                    }
                } else {
                    for (int i = 1; i < valueV.size() - 1; i += 1) {
                        if ((Integer) valueV.get(i) == u) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    @Override
    public int add(int u, int v) {
        if (contains(u, v)) {
            return u;
        }
        for (int s : successors(u)) {
            if (v == s) {
                return u;
            }
        }
        ArrayList<Object> valueU = hashmapReturn().get(u);
        ArrayList<Object> valueV = hashmapReturn().get(v);
        @SuppressWarnings("unchecked")
        ArrayList<Integer> newValueV = (ArrayList<Integer>) valueV.get(0);
        valueU.add(v);
        newValueV.add(u);
        return u;
    }
}
