package com.GraphAlgorithms;

import org.junit.Test;

import java.util.*;

import static com.GraphAlgorithms.GraphExamples.*;
import static com.GraphAlgorithms.TopologicalSort.topologicalSort;
import static org.junit.Assert.assertEquals;

/**
 * Created by xisisu on 16-11-27.
 */
public class SingleSourceShortestPath {
    // BellmanFord method
    private static Set<WeightedEdge> getAllEdges(final GraphInterface g) {
        final Set<WeightedEdge> result = new HashSet<>();
        for (final Integer i : g.getNodes()) {
            for (final WeightedNode n : g.getAdjacencyNodesWithWeight(i)) {
                result.add(new WeightedEdge(i, n._node, n._weight));
            }
        }
        return result;
    }

    public static Map<Integer, Integer> getShortestPathFromSourceBellmanFord(final GraphInterface g, final Integer source) {
        final Map<Integer, Integer> sourceToNodeDistance = new HashMap<>();
        for (final Integer i : g.getNodes()) {
            sourceToNodeDistance.put(i, i == source ? 0 : Integer.MAX_VALUE);
        }

        final Set<WeightedEdge> allEdges = getAllEdges(g);
        for (int i = 0; i < g.getSize(); ++i) {
            for (final WeightedEdge e : allEdges) {
                final Integer fromWeight = sourceToNodeDistance.get(e._from);
                if (fromWeight.equals(Integer.MAX_VALUE)) {
                    continue;
                }
                final Integer toWeight = Math.min(sourceToNodeDistance.get(e._to), fromWeight + e._weight);
                sourceToNodeDistance.put(e._to, toWeight);
            }
        }

        // check whether negative cycle exists
        for (final WeightedEdge e : allEdges) {
            if (sourceToNodeDistance.get(e._to) > sourceToNodeDistance.get(e._from) + e._weight) {
                System.out.println("Negative cycle exists, contains edge: " + e);
            }
        }

        return sourceToNodeDistance;
    }

    public static Map<Integer, Integer> getShortestPathFromSourceTopologicalSort(final GraphInterface g, final Integer source) {
        final Map<Integer, Integer> sourceToNodeDistance = new HashMap<>();
        for (final Integer i : g.getNodes()) {
            sourceToNodeDistance.put(i, i == source ? 0 : Integer.MAX_VALUE);
        }

        // process node in topological order
        for (final Integer i : topologicalSort(g)) {
            final Integer fromWeight = sourceToNodeDistance.get(i);
            if (fromWeight.equals(Integer.MAX_VALUE)) { continue; } // unreachable
            for (final WeightedNode n : g.getAdjacencyNodesWithWeight(i)) {
                final Integer toWeight = Math.min(sourceToNodeDistance.get(n._node), fromWeight + n._weight);
                sourceToNodeDistance.put(n._node, toWeight);
            }
        }

        return sourceToNodeDistance;
    }

    public static Map<Integer, Integer> getShortestPathFromSourceDijkstra(final GraphInterface g, final Integer source) {
        final PriorityQueue<NodeDistance> q = new PriorityQueue<>();
        final Map<Integer, Integer> sourceToNodeDistance = new HashMap<>();

        q.add(new NodeDistance(source, 0));
        sourceToNodeDistance.put(source, 0);

        while (!q.isEmpty()) {
            final NodeDistance cur = q.poll();
            final Integer fromDistance = cur._distance;
            for (final WeightedNode node : g.getAdjacencyNodesWithWeight(cur._node)) {
                if (sourceToNodeDistance.containsKey(node._node) &&
                        sourceToNodeDistance.get(node._node) <= fromDistance + node._weight)
                {
                    continue;
                }

                final Integer toDistance = node._weight + fromDistance;
                sourceToNodeDistance.put(node._node, toDistance);
                q.add(new NodeDistance(node._node, toDistance));
            }
        }

        return sourceToNodeDistance;
    }

    private static class NodeDistance implements Comparable<NodeDistance> {
        public Integer _node;
        public Integer _distance;

        public NodeDistance(final Integer node, final Integer distance) {
            _node = node;
            _distance = distance; // init to max value
        }

        @Override
        public int compareTo(NodeDistance other) {
            return _distance - other._distance;
        }
    }

    @Test
    public void testBellmanFord() {
        final GraphInterface g = constructSingleSourceShortestPath();
        final Map<Integer, Integer> result = getShortestPathFromSourceBellmanFord(g, 1);
        assertEquals(result.get(1), Integer.valueOf(0));
        assertEquals(result.get(2), Integer.valueOf(2));
        assertEquals(result.get(3), Integer.valueOf(7));
        assertEquals(result.get(4), Integer.valueOf(4));
        assertEquals(result.get(5), Integer.valueOf(-2));
    }

    // TODO: add a better example for DAG
    @Test
    public void testTopologicalSort() {
        final GraphInterface g = constructDirectedExampleGraph3();
        final Map<Integer, Integer> result = getShortestPathFromSourceTopologicalSort(g, 1);
        System.out.println(result);
    }

    @Test
    public void testDijkstra() {
        final GraphInterface g = constructSingleSourceShortestPathNonNegative();
        final Map<Integer, Integer> result = getShortestPathFromSourceDijkstra(g, 1);
        assertEquals(result.get(1), Integer.valueOf(0));
        assertEquals(result.get(2), Integer.valueOf(8));
        assertEquals(result.get(3), Integer.valueOf(9));
        assertEquals(result.get(4), Integer.valueOf(5));
        assertEquals(result.get(5), Integer.valueOf(7));
    }
}
