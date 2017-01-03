package com.GraphAlgorithms;

import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import static com.GraphAlgorithms.GraphExamples.constructExampleGraph1;

/**
 * Also see: http://www.geeksforgeeks.org/breadth-first-traversal-for-a-graph/
 * Created by xisisu on 16-11-24.
 */
public class GraphBFS {
    enum BFSColor { WHITE, BLACK, GRAY }

    public static void BFS(final GraphInterface g, final Integer source) {
        class Record {
            BFSColor _color;
            Integer _distance;
            Integer _predecessor;
            public Record(final BFSColor color, final Integer distance, final Integer predecessor) {
                _color = color;
                _distance = distance;
                _predecessor = predecessor;
            }

            @Override
            public String toString() {
                return "Record{" + "_color=" + _color + ", _distance=" + _distance + ", _predecessor=" + _predecessor + '}';
            }
        }

        final Map<Integer, Record> nodeMap = new HashMap<>();
        for (final Integer n : g.getNodes()) {
            nodeMap.put(n, n.equals(source) ?
                    new Record(BFSColor.GRAY, 0, null) :
                    new Record(BFSColor.WHITE, Integer.MAX_VALUE, null));
        }

        final Queue<Integer> queue = new LinkedList<>();
        queue.add(source);

        while (!queue.isEmpty()) {
            final Integer cur = queue.poll();
            for (final Integer n : g.getAdjacencyNodes(cur)) {
                if (nodeMap.get(n)._color.equals(BFSColor.WHITE)) {
                    nodeMap.get(n)._color = BFSColor.GRAY;
                    nodeMap.get(n)._distance = nodeMap.get(cur)._distance + 1;
                    nodeMap.get(n)._predecessor = cur;
                    queue.add(n);
                }
            }
            nodeMap.get(cur)._color = BFSColor.BLACK;
        }

        for (final Integer n : nodeMap.keySet()) {
            System.out.println("node " + n + ": " + nodeMap.get(n));
        }
    }

    public static void BFS2(final GraphInterface g, final Integer source) {
        class Record {
            Integer _distance;
            Integer _predecessor;
            public Record(final Integer distance, final Integer predecessor) {
                _distance = distance;
                _predecessor = predecessor;
            }

            @Override
            public String toString() {
                return "Record{" + "_distance=" + _distance + ", _predecessor=" + _predecessor + '}';
            }
        }

        final Map<Integer, Record> nodeMap = new HashMap<>();
        nodeMap.put(source, new Record(0, null));

        final Queue<Integer> queue = new LinkedList<>();
        queue.add(source);

        int distance = 0;

        while (!queue.isEmpty()) {
            final Integer cur = queue.poll();
            distance += 1;
            for (final Integer n : g.getAdjacencyNodes(cur)) {
                if (!nodeMap.containsKey(n)) {
                    nodeMap.put(n, new Record(distance, cur));
                    queue.add(n);
                }
            }
        }

        for (final Integer n : nodeMap.keySet()) {
            System.out.println("node " + n + ": " + nodeMap.get(n));
        }
    }

    @Test public void testBFS() {
        final GraphInterface g = constructExampleGraph1();
        BFS(g, 1);
    }

    @Test public void testBFS2() {
        final GraphInterface g = constructExampleGraph1();
        BFS2(g, 1);
    }
}
