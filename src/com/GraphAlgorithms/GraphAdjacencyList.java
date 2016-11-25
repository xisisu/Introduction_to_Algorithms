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
    private final Map<Integer, Set<Integer>> _nodes = new HashMap<>();

    public GraphAdjacencyList(final int V) {
        for (int i = 1; i <= V; ++i) {
            _nodes.put(i, new HashSet<>());
        }
    }

    @Override
    public void addUndirectedEdge(final Integer i, final Integer j) {
        addDirectedEdge(i, j);
        addDirectedEdge(j, i);
    }

    @Override
    public void addDirectedEdge(Integer i, Integer j) {
        _nodes.get(i).add(j);
    }


    @Override
    public Set<Integer> getAdjacencyNodes(final Integer i) {
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
