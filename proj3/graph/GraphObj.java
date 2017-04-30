package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/* See restrictions in Graph.java. */

/** A partial implementation of Graph containing elements common to
 *  directed and undirected graphs.
 *
 *  @author AlexDotterweich
 */
abstract class GraphObj extends Graph {

    /** A new, empty Graph. */
    GraphObj() {

    }

    @Override
    public int vertexSize() {
        return _hashmap.size();
    }

    @Override
    public int maxVertex() {
        int currMax = 0;
        for (Integer key : _hashmap.keySet()) {
            if (key > currMax) {
                currMax = key;
            }
        }
        return currMax;
    }

    @Override
    public int edgeSize() {
        int total = 0;
        for (ArrayList<Object> value : _hashmap.values()) {
            total += value.size() - 1;
        }
        return total;
    }

    @Override
    public abstract boolean isDirected();

    @Override
    public int outDegree(int v) {
        ArrayList<Object> value = _hashmap.get(v);
        if (contains(v)) {
            if (value.size() != 0) {
                return value.size() - 1;
            }
        }
        return 0;
    }

    @Override
    public abstract int inDegree(int v);

    @Override
    public boolean contains(int u) {
        if (_hashmap.containsKey(u) && !_removed.contains(u)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean contains(int u, int v) {
        if (contains(u) && contains(v)) {
            int howMany = 0;
            ArrayList<Object> valueU = _hashmap.get(u);
            ArrayList<Object> valueV = _hashmap.get(v);
            for (int i = 1; i < valueU.size(); i += 1) {
                if (valueU.get(i) == (Object) v) {
                    howMany += 1;
                }
            }
            ArrayList<Integer> newValueV = (ArrayList<Integer>) valueV.get(0);
            for (int q = 0; q < newValueV.size(); q += 1) {
                if (newValueV.get(q) == u) {
                    howMany += 1;
                }
            }
            if (howMany == 2) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int add() {
        if (!_removed.isEmpty()) {
            int currSmallest = 100;
            for (int item : _removed) {
                if (item < currSmallest) {
                    currSmallest = item;
                }
            }
            int index = _removed.indexOf(currSmallest);
            _removed.remove(index);
            ArrayList<Object> list1 = new ArrayList<Object>();
            ArrayList<Integer> list2 = new ArrayList<Integer>();
            list1.add(0, list2);
            ArrayList<Object> value = list1;
            _hashmap.put(currSmallest, value);
            return currSmallest;
        }
        int counter = 1;
        for (ArrayList<Object> value : _hashmap.values()) {
            if (value.size() == 0) {
                ArrayList<Object> list1 = new ArrayList<Object>();
                ArrayList<Integer> list2 = new ArrayList<Integer>();
                list1.add(0, list2);
                value = list1;
                return counter;
            }
            counter += 1;
        }
        int newKey = maxVertex() + 1;
        ArrayList<Object> list1 = new ArrayList<Object>();
        ArrayList<Integer> list2 = new ArrayList<Integer>();
        list1.add(0, list2);
        ArrayList<Object> value = list1;
        _hashmap.put(newKey, value);
        return newKey;
    }

    @Override
    public int add(int u, int v) {
        for (int s : successors(u)) {
            if (v == s) {
                return u;
            }
        }
        ArrayList<Object> valueU = _hashmap.get(u);
        ArrayList<Object> valueV = _hashmap.get(v);
        @SuppressWarnings("unchecked")
        ArrayList<Integer> newValueV = (ArrayList<Integer>) valueV.get(0);
        valueU.add(v);
        newValueV.add(u);
        return u;
    }

    @Override
    public void remove(int v) {
        for (int i : vertices()) {
            if (i != v) {
                remove(v, i);
                remove(i, v);
            }
        }
        _removed.add(v);
        _hashmap.remove(v);
    }

    @Override
    public void remove(int u, int v) {
        ArrayList<Object> valueU = _hashmap.get(u);
        ArrayList<Object> valueV = _hashmap.get(v);
        @SuppressWarnings("unchecked")
        ArrayList<Integer> newValueV = (ArrayList<Integer>) valueV.get(0);
        int indexV = valueU.indexOf(v);
        int indexU = newValueV.indexOf(u);
        if (indexU != -1) {
            valueU.remove(indexV);
        }
        if (indexV != -1) {
            newValueV.remove(indexU);
        }
    }

    @Override
    public Iteration<Integer> vertices() {
        Set<Integer> h = _hashmap.keySet();
        return Iteration.iteration(h);
    }

    @Override
    public int successor(int v, int k) {
        if (_hashmap.containsKey(v)) {
            ArrayList<Object> valueV = _hashmap.get(v);
            int howMany = valueV.size() - 1;
            if (howMany > k) {
                return (int) valueV.get(k + 1);
            }
        }
        return 0;
    }

    @Override
    public abstract int predecessor(int v, int k);

    @Override
    public Iteration<Integer> successors(int v) {
        ArrayList<Integer> newValueV = new ArrayList<Integer>();
        if (mine(v)) {
            ArrayList<Object> valueV = _hashmap.get(v);
            List<Object> ee = valueV.subList(1, valueV.size());
            for (Object obj : ee) {
                newValueV.add((Integer) obj);
            }
            return Iteration.iteration(newValueV);
        }
        return Iteration.iteration(newValueV);
    }

    @Override
    public abstract Iteration<Integer> predecessors(int v);

    @Override
    public Iteration<int[]> edges() {
        ArrayList<int[]> toReturn = new ArrayList<int[]>();
        Set<Integer> h = _hashmap.keySet();
        for (int v : h) {
            ArrayList<Object> valueV = _hashmap.get(v);
            List<Object> ee = valueV.subList(1, valueV.size());
            for (Object obj : ee) {
                int[] toAdd = new int[]{v, (int) obj};
                toReturn.add(toAdd);
            }
        }
        return Iteration.iteration(toReturn);
    }

    @Override
    protected boolean mine(int v) {
        if (_hashmap.containsKey(v) && !_removed.contains(v)) {
            return true;
        }
        return false;
    }

    @Override
    protected void checkMyVertex(int v) {
        if (!mine(v)) {
            throw new IllegalArgumentException("vertice not mine");
        }
    }

    @Override
    protected int edgeId(int u, int v) {
        return 0;
    }

    /** Returns the _hashmap. */
    protected HashMap<Integer, ArrayList<Object>> hashmapReturn() {
        return _hashmap;
    }

    /** Returns the list of values that have been removed. */
    protected ArrayList<Integer> removedReturn() {
        return _removed;
    }

    /** HashMap that stores the graphs. */
    private HashMap<Integer, ArrayList<Object>> _hashmap =
            new HashMap<Integer, ArrayList<Object>>();

    /** Stores the values that have been removed.*/
    private ArrayList<Integer> _removed = new ArrayList<Integer>();

}
