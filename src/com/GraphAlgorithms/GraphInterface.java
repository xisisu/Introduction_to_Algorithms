package com.GraphAlgorithms;

import java.util.Set;

/**
 * Created by xisisu on 16-11-24.
 */
public interface GraphInterface {
    public void addUndirectedEdgeWithWeight(Integer i, Integer j, Integer weight);

    public void addDirectedEdgeWithWeight(Integer from, Integer to, Integer weight);

    public void addUndirectedEdge(Integer i, Integer j);

    public void addDirectedEdge(Integer i, Integer j);

    public Set<Integer> getAdjacencyNodes(Integer i);

    public Set<WeightedNode> getAdjacencyNodesWithWeight(Integer i);

    public Integer getSize();

    public Set<Integer> getNodes();

    public void clear();
}
