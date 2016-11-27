package com.GraphAlgorithms;

import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

import static com.GraphAlgorithms.GraphExamples.constructMSTExample;

/**
 * Created by xisisu on 16-11-27.
 */
public class MinimalSpanningTreePrim {
    /**
     * keep selecting minimal weight edge from all connected nodes that connects to a non-connected node
     */
    public static void minimalSpanningTreePrim(final GraphInterface g) {
        final Integer start = 1;
        final Set<Integer> connected = new HashSet<>();
        connected.add(start);
        final PriorityQueue<WeightedEdge> q = new PriorityQueue<>();
        for (final WeightedNode e : g.getAdjacencyNodesWithWeight(start)) {
            q.add(new WeightedEdge(start, e._node, e._weight));
        }

        while (!q.isEmpty()) {
            final WeightedEdge cur = q.poll();
            if (connected.contains(cur._from) && connected.contains(cur._to)) {
                continue;
            }

            System.out.println("Select: " + cur);
            final Integer newNode = connected.contains(cur._from) ? cur._to : cur._from;
            connected.add(newNode);
            for (final WeightedNode e : g.getAdjacencyNodesWithWeight(newNode)) {
                q.add(new WeightedEdge(newNode, e._node, e._weight));
            }
        }
    }

    @Test
    public void test() {
        final GraphInterface g = constructMSTExample();
        minimalSpanningTreePrim(g);
    }
}
