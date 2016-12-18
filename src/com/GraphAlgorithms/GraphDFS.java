package com.GraphAlgorithms;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

import static com.GraphAlgorithms.GraphExamples.constructDirectedExampleGraph3;
import static com.GraphAlgorithms.GraphExamples.constructExampleGraph1;
import static org.junit.Assert.assertEquals;

/**
 * Created by xisisu on 16-11-24.
 */
public class GraphDFS {
    private static void visit(final List<Integer> result
            , final GraphInterface g
            , final Integer node)
    {
        if (result.contains(node)) { return; }
        result.add(node);
        for (final Integer n : g.getAdjacencyNodes(node)) {
            visit(result, g, n);
        }
    }

    public static List<Integer> DFS(final GraphInterface g) {
        final List<Integer> result = new ArrayList<>();
        for (final Integer n : g.getNodes()) {
            visit(result, g, n);
        }
        return result;
    }

    public static List<Integer> DFS2(final GraphInterface g) {
        final List<Integer> result = new ArrayList<>();
        final Stack<Integer> s = new Stack<>();
        for (final Integer n : g.getNodes()) {
            if (result.contains(n)) { continue; }
            s.push(n);
            while (!s.isEmpty()) {
                final Integer cur = s.pop();
                result.add(cur);
                for (final Integer val : g.getAdjacencyNodes(cur)) {
                    if (result.contains(val)) { continue; }
                    s.push(val);
                    break;
                }
            }
        }

        return result;
    }

    @Test
    public void testDFS() {
        final GraphInterface g = constructExampleGraph1();
        assertEquals(Arrays.asList(1,2,3,4,5), DFS(g));
    }

    @Test
    public void testDFS2() {
        final GraphInterface g = constructExampleGraph1();
        assertEquals(Arrays.asList(1,2,3,4,5), DFS2(g));
    }

    @Test
    public void testExampleGraph3() {
        final GraphInterface g = constructDirectedExampleGraph3();
        System.out.println(DFS(g));
    }
}
