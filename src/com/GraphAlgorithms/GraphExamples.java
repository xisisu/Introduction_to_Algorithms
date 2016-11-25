package com.GraphAlgorithms;

/**
 * Created by xisisu on 16-11-24.
 */
public class GraphExamples {
    /**
     * 1 -- 2
     * |   /| \
     * |  / |  3
     * | /  | /
     * 5 -- 4
     */
    public static GraphInterface constructExampleGraph1() {
        final GraphInterface g = new GraphAdjacencyList(5);
        g.addUndirectedEdge(1,2);
        g.addUndirectedEdge(1,5);
        g.addUndirectedEdge(2,3);
        g.addUndirectedEdge(2,4);
        g.addUndirectedEdge(2,5);
        g.addUndirectedEdge(3,4);
        g.addUndirectedEdge(4,5);
        return g;
    }

    /**
     * 1 -- 2
     * |   /|
     * |  / |  3
     * | /  | /|
     * 5 -- 4  6
     */
    public static GraphInterface constructExampleGraph2() {
        final GraphInterface g = new GraphAdjacencyList(6);
        g.addUndirectedEdge(1,2);
        g.addUndirectedEdge(1,5);
        g.addUndirectedEdge(2,4);
        g.addUndirectedEdge(2,5);
        g.addUndirectedEdge(3,4);
        g.addUndirectedEdge(3,6);
        g.addUndirectedEdge(4,5);
        return g;
    }

    /**
     * example 22.7
     */
    public static GraphInterface constructDirectedExampleGraph3() {
        final GraphInterface g = new GraphAdjacencyList(9);
        g.addDirectedEdge(1,2);
        g.addDirectedEdge(1,8);
        g.addDirectedEdge(2,3);
        g.addDirectedEdge(2,8);
        g.addDirectedEdge(3,6);
        g.addDirectedEdge(4,3);
        g.addDirectedEdge(4,5);
        g.addDirectedEdge(5,6);
        g.addDirectedEdge(7,8);
        return g;
    }

    /**
     * example 22.9
     */
    public static GraphInterface constructStronglyConnectedComponents() {
        final GraphInterface g = new GraphAdjacencyList(8);
        g.addDirectedEdge(1,3);
        g.addDirectedEdge(2,1);
        g.addDirectedEdge(2,4);
        g.addDirectedEdge(3,2);
        g.addDirectedEdge(3,4);
        g.addDirectedEdge(3,5);
        g.addDirectedEdge(4,6);
        g.addDirectedEdge(5,6);
        g.addDirectedEdge(5,7);
        g.addDirectedEdge(6,4);
        g.addDirectedEdge(6,8);
        g.addDirectedEdge(7,5);
        g.addDirectedEdge(7,8);
        g.addDirectedEdge(8,8);
        return g;
    }

    public static GraphInterface constructStronglyConnectedComponents2() {
        final GraphInterface g = new GraphAdjacencyList(5);
        g.addDirectedEdge(1,5);
        g.addDirectedEdge(5,2);
        g.addDirectedEdge(2,1);
        g.addDirectedEdge(5,3);
        g.addDirectedEdge(3,4);
        return g;
    }
}
