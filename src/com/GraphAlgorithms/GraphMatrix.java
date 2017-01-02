package com.GraphAlgorithms;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by xisis on 12/18/2016.
 */
public class GraphMatrix implements GraphInterface {
    private int[][] _weight;

    // we ignore node 0 here
    public GraphMatrix(final int V) {
        _weight = new int[V+1][V+1];
        for (int i = 1; i <= V; ++i) {
            for (int j = 0; j <= V; ++j) {
                _weight[i][j] = 0;
            }
        }
    }

    @Override
    public void addUndirectedEdgeWithWeight(Integer i, Integer j, Integer weight) {
        addDirectedEdgeWithWeight(i, j, weight);
        addDirectedEdgeWithWeight(j, i, weight);
    }

    @Override
    public void addDirectedEdgeWithWeight(Integer from, Integer to, Integer weight) {
        _weight[from][to] = weight;
    }

    @Override
    public void addUndirectedEdge(Integer i, Integer j) {
        addUndirectedEdgeWithWeight(i, j, 1);
    }

    @Override
    public void addDirectedEdge(Integer i, Integer j) {
        addDirectedEdgeWithWeight(i, j, 1);
    }

    @Override
    public Set<Integer> getAdjacencyNodes(Integer i) {
        final Set<Integer> result = new HashSet<>();
        for (int j = 1; j < _weight[i].length; ++j) {
            if (_weight[i][j] != 0) {
                result.add(j);
            }
        }
        return result;
    }

    @Override
    public Set<WeightedNode> getAdjacencyNodesWithWeight(Integer i) {
        final Set<WeightedNode> result = new HashSet<>();
        for (int j = 1; j < _weight[i].length; ++j) {
            if (_weight[i][j] != 0) {
                result.add(new WeightedNode(j, _weight[i][j]));
            }
        }
        return result;
    }

    @Override
    public Integer getSize() {
        return _weight.length;
    }

    @Override
    public Set<Integer> getNodes() {
        final Set<Integer> result = new HashSet<>();
        for (int i = 1; i < _weight.length; ++i) {
            result.add(i);
        }
        return result;
    }

    @Override
    public void clear() {
        _weight = null;
    }
}