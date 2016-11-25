package com.GraphAlgorithms;

import java.util.Set;

/**
 * Created by xisisu on 16-11-24.
 */
public interface GraphInterface {
    public void addUndirectedEdge(Integer i, Integer j);

    public void addDirectedEdge(Integer i, Integer j);

    public Set<Integer> getAdjacencyNodes(Integer i);

    public Integer getSize();

    public Set<Integer> getNodes();

    public void clear();
}
