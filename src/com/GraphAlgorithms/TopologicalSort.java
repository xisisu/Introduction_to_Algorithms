package com.GraphAlgorithms;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.GraphAlgorithms.GraphExamples.constructDirectedExampleGraph3;
import static org.junit.Assert.assertEquals;

/**
 * Also see: http://www.geeksforgeeks.org/topological-sorting/
 *
 * Created by xisisu on 16-11-24.
 */
public class TopologicalSort {
    private static void visit(final List<Integer> result, final GraphInterface g, final Integer n) {
        for (final Integer cur : g.getAdjacencyNodes(n)) {
            if (result.contains(cur)) { continue; }
            visit(result, g, cur);
        }
        result.add(n);
    }

    public static List<Integer> topologicalSort(final GraphInterface g) {
        final List<Integer> result = new ArrayList<>();
        for (final Integer n : g.getNodes()) {
            if (result.contains(n)) { continue; }
            visit(result, g, n);
        }
        Collections.reverse(result);
        return result;
    }

    @Test
    public void test() {
        final GraphInterface g = constructDirectedExampleGraph3();
        // this is only one possible solution
        assertEquals(Arrays.asList(9,7,4,5,1,2,8,3,6), topologicalSort(g));
    }
}
