package graph;

/* See restrictions in Graph.java. */

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/** The shortest paths through an edge-weighted graph.
 *  By overrriding methods getWeight, setWeight, getPredecessor, and
 *  setPredecessor, the client can determine how to represent the weighting
 *  and the search results.  By overriding estimatedDistance, clients
 *  can search for paths to specific destinations using A* search.
 *  @author AlexDotterweich
 */
public abstract class ShortestPaths {

    /** Wanted to make shortest path extend traversal and use
     * the traverse algorithm, but manipulate the constructor
     * such that it uses a priority queue. In addition, wanted
     * to check weights of edges, set them, and then store the
     * running total of all weights in that path somewhere. */
    public class ShortestPathTraversal extends Traversal {
        /** Extended Traversal so that I could make graph G a PriorityQueue
         * when I call traverse and store nodes along with a key. */
        protected ShortestPathTraversal(Graph G) {
            super(G, new PriorityQueue<Integer>());
        }

        @Override
        protected boolean visit(int v) {
            setWeight(v, getWeight(v));
            for (int i : _G.predecessors(v)) {
                double totalWeight = getWeight(v) + i;
            }
            return super.visit(v);
        }

        @Override
        protected boolean postVisit(int v) {
            return super.postVisit(v);
        }
    }

    /** The shortest paths in G from SOURCE. */
    public ShortestPaths(Graph G, int source) {
        this(G, source, 0);
    }

    /** A shortest path in G from SOURCE to DEST. */
    public ShortestPaths(Graph G, int source, int dest) {
        _G = G;
        _source = source;
        _dest = dest;
        _initialized = true;
    }

    /** Initialize the shortest paths.  Must be called before using
     *  getWeight, getPredecessor, and pathTo.
     *
     *  In a perfect world, this would traverse the graph, and
     *  when it gets to a point in the traverse algorithm that visits
     *  a node, it would set that thing's weight, updating the place holder
     *  weight. By doing this, each of the nodes in the graph and their
     *  respective weights would be recorded. I wanted to then identify the
     *  paths to get to the destination and set the weight of that path equal
     *  to the weight of both of the nodes. */
    public void setPaths() {
        ShortestPathTraversal g = new ShortestPathTraversal(_G);
        g.traverse(_source);
    }

    /** Returns the starting vertex. */
    public int getSource() {
        return _source;
    }

    /** Returns the target vertex, or 0 if there is none. */
    public int getDest() {
        if (_initialized) {
            return _dest;
        }
        return 0;
    }

    /** Returns the current weight of vertex V in the graph.  If V is
     *  not in the graph, returns positive infinity. */
    public abstract double getWeight(int v);

    /** Set getWeight(V) to W. Assumes V is in the graph. */
    protected abstract void setWeight(int v, double w);

    /** Returns the current predecessor vertex of vertex V in the graph, or 0 if
     *  V is not in the graph or has no predecessor. */
    public abstract int getPredecessor(int v);

    /** Set getPredecessor(V) to U. */
    protected abstract void setPredecessor(int v, int u);

    /** Returns an estimated heuristic weight of the shortest path from vertex
     *  V to the destination vertex (if any).  This is assumed to be less
     *  than the actual weight, and is 0 by default. */
    protected double estimatedDistance(int v) {
        return 0.0;
    }

    /** Returns the current weight of edge (U, V) in the graph.  If (U, V) is
     *  not in the graph, returns positive infinity. */
    protected abstract double getWeight(int u, int v);

    /** Returns a list of vertices starting at _source and ending
     *  at V that represents a shortest path to V.  Invalid if there is a
     *  destination vertex other than V.
     *  After calling setPaths, the weights should be initialized,
     *  then for every node, want to call getPredecessors to obtain
     *  the weights of nodes that came before. From this, want to add
     *  up the weights of each of these paths to find the shortest.*/
    public List<Integer> pathTo(int v) {
        v = _source;
        setPaths();
        return _shortestPath;
    }

    /** Returns a list of vertices starting at the source and ending at the
     *  destination vertex. Invalid if the destination is not specified. */
    public List<Integer> pathTo() {
        return pathTo(getDest());
    }



    /** The graph being searched. */
    protected final Graph _G;
    /** The starting vertex. */
    private final int _source;
    /** The target vertex. */
    private final int _dest;
    /** Initialized vertices will return true. */
    private boolean _initialized = false;
    /** Returns the shortest path. */
    private ArrayList<Integer> _shortestPath;

}
