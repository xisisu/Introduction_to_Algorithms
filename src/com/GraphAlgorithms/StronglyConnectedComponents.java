package com.GraphAlgorithms;

import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

import static com.GraphAlgorithms.GraphExamples.constructStronglyConnectedComponents2;
import static com.GraphAlgorithms.GraphExamples.constructStronglyConnectedComponents;

/**
 * Created by xisisu on 16-11-24.
 */
public class StronglyConnectedComponents {
    private static GraphInterface reverseGraph(final GraphInterface g) {
        final GraphInterface result = new GraphAdjacencyList(g.getSize()+1);
        for (final Integer n : g.getNodes()) {
            for (final Integer e : g.getAdjacencyNodes(n)) {
                result.addDirectedEdge(e, n);
            }
        }
        return result;
    }

    private static void dfsPrint(final Set<Integer> visited, final GraphInterface g, final Integer node) {
        if (visited.contains(node)) { return; }

        visited.add(node);
        System.out.print(node + " ");
        for (final Integer n : g.getAdjacencyNodes(node)) {
            dfsPrint(visited, g, n);
        }
    }

    /**
     * push to stack according to its dfs_time, in reverse order
     */
    private static void dfsUtil(final Set<Integer> visited
            , final GraphInterface g
            , final Integer node
            , final Stack<Integer> s)
    {
        if (visited.contains(node)) { return; }
        visited.add(node);
        for (final Integer n : g.getAdjacencyNodes(node)) {
            if (!visited.contains(n)) {
                dfsUtil(visited, g, n, s);
            }
        }
        // all out nodes and its child has been processed, push node to stack
        s.push(node);
    }

    public void stronglyConnectedComponents(final GraphInterface g) {
        final Stack<Integer> s = new Stack<>();
        final Set<Integer> visited = new HashSet<>();
        for (final Integer n : g.getNodes()) {
            dfsUtil(visited, g, n, s);
        }
        final GraphInterface gReverse = reverseGraph(g);

        visited.clear();
        while (!s.isEmpty()) {
            final Integer cur = s.pop();
            if (visited.contains(cur)) { continue; }
            dfsPrint(visited, gReverse, cur);
            System.out.println("");
        }
    }

    @Test
    public void test() {
        final GraphInterface g = constructStronglyConnectedComponents();
        stronglyConnectedComponents(g);
    }

    @Test
    public void test2() {
        final GraphInterface g = constructStronglyConnectedComponents2();
        stronglyConnectedComponents(g);
    }
}
