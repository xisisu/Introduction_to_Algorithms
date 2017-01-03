package com.GraphAlgorithms;

import com.AdvancedDataStructures.DisjointSetForest;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

import static com.GraphAlgorithms.GraphExamples.constructMSTExample;

/**
 * See also: http://www.geeksforgeeks.org/greedy-algorithms-set-2-kruskals-minimum-spanning-tree-mst/
 *
 * Created by xisisu on 16-11-27.
 */
public class MinimalSpanningTreeKruskal {
    /**
     * for undirected edges use only, get all edges in a priority queue
     */
    private static PriorityQueue<WeightedEdge> getAllEdges(final GraphInterface g) {
        final PriorityQueue<WeightedEdge> result = new PriorityQueue<>();
        for (final Integer i : g.getNodes()) {
            for (final WeightedNode w : g.getAdjacencyNodesWithWeight(i)) {
                if (w._node >= i) { // only adding 1 direction of edge
                    result.add(new WeightedEdge(i, w._node, w._weight));
                }
            }
        }
        return result;
    }

    /**
     * keeping selecting edges that combines two disjoints forests
     */
    public static void minimalSpanningTreeKruskal(final GraphInterface g) {
        if (g == null) { return; }

        final PriorityQueue<WeightedEdge> q = getAllEdges(g);

        final Map<Integer, DisjointSetForest> nodeToDisjointSet = new HashMap<>();
        for (final Integer i : g.getNodes()) {
            nodeToDisjointSet.put(i, new DisjointSetForest(i));
        }

        while (!q.isEmpty()) {
            final WeightedEdge cur = q.poll();
            final DisjointSetForest a = DisjointSetForest.getRoot(nodeToDisjointSet.get(cur._from));
            final DisjointSetForest b = DisjointSetForest.getRoot(nodeToDisjointSet.get(cur._to));
            if (a.equals(b)) {
                continue;
            }
            System.out.println("Select edge: " + cur);
            a.unionWith(b);
        }
    }

    @Test
    public void test() {
        final GraphInterface g = constructMSTExample();
        minimalSpanningTreeKruskal(g);
    }
}
