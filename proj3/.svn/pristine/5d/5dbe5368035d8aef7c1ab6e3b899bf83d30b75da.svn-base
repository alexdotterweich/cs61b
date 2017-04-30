package graph;

import org.junit.Test;

public class ShortestPathTesting {

    class GraphPaths extends SimpleShortestPaths {
        public GraphPaths(Graph g, int source, int dest) {
            super(g, source, dest);
        }

        @Override
        public double getWeight(int u, int v) {
            if (u == 1 && v == 2) {
                return 2.2;
            }
            if (u == 2 && v == 3) {
                return 3.3;
            }
            if (u == 1 && v == 4) {
                return 4.4;
            }
            if (u == 4 && v == 3) {
                return 7.7;
            }
            return Double.POSITIVE_INFINITY;
        }

        @Override
        protected double estimatedDistance(int v) {
            return getWeight(v) - 1;
        }
    }

    @Test
    public void testWeights() {
        DirectedGraph dg = new DirectedGraph();
        dg.add();
        dg.add();
        dg.add();
        GraphPaths gp = new GraphPaths(dg, 1, 2);
        gp.setPaths();
    }

}
