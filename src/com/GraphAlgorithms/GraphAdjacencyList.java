package com.GraphAlgorithms;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * assumes graph is undirected
 * Created by xisisu on 16-11-24.
 */
public class GraphAdjacencyList implements GraphInterface {
    private final Map<Integer, Set<WeightedNode>> _nodes = new HashMap<>();

    // we ignore node 0 here
    public GraphAdjacencyList(final int V) {
        for (int i = 1; i <= V; ++i) {
            _nodes.put(i, new HashSet<>());
        }
    }

    @Override
    public void addUndirectedEdgeWithWeight(Integer i, Integer j, Integer weight) {
        addDirectedEdgeWithWeight(i, j, weight);
        addDirectedEdgeWithWeight(j, i, weight);
    }

    @Override
    public void addDirectedEdgeWithWeight(Integer from, Integer to, Integer weight) {
        _nodes.get(from).add(new WeightedNode(to, weight));
    }

    @Override
    public void addUndirectedEdge(final Integer i, final Integer j) {
        addUndirectedEdgeWithWeight(i, j, 1);
    }

    @Override
    public void addDirectedEdge(Integer i, Integer j) {
        addDirectedEdgeWithWeight(i, j, 1);
    }

    @Override
    public Set<Integer> getAdjacencyNodes(final Integer i) {
        final Set<Integer> result = new HashSet<>();
        for (final WeightedNode n : _nodes.get(i)) {
            result.add(n._node);
        }
        return result;
    }

    @Override
    public Set<WeightedNode> getAdjacencyNodesWithWeight(Integer i) {
        return _nodes.get(i);
    }

    @Override
    public Integer getSize() {
        return _nodes.size();
    }

    @Override
    public Set<Integer> getNodes() {
        return _nodes.keySet();
    }

    @Override
    public void clear() {
        _nodes.clear();
    }
}
