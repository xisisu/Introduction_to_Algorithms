package com.AdvancedDataStructures;

import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xisisu on 16-11-22.
 */
public class DisjointSetForest {
    public Integer _val;
    public DisjointSetForest _parent = null;
    public Integer _rank = 0;

    public DisjointSetForest() {}

    public DisjointSetForest(final Integer val) { // works as makeSet
        _val = val;
        _parent = this; // point to it self
        _rank = 1;
    }

    public void unionWith(DisjointSetForest y) { // union by rank
        DisjointSetForest left  = getRoot(this);
        DisjointSetForest right = getRoot(y);

        if (left._rank < right._rank) {
            left._parent = right._parent;
        } else if (left._rank > right._rank) {
            right._parent = left._parent;
        } else {
            right._parent = left._parent;
            left._rank += 1;
        }
    }

    public static DisjointSetForest getRoot(DisjointSetForest x) {
        if (x != x._parent) { // path compression
            x._parent = getRoot(x._parent);
        }
        return x._parent;
    }

    @Override
    public String toString() {
        return _parent == null ? "null" : _val + "->" + _parent._val + ", rank: " + _rank;
    }

    @Test
    public void testFindRoot() {
        final Map<Integer, DisjointSetForest> nodeMap = new HashMap<>();

        for (int i = 0; i < 10; ++i) {
            DisjointSetForest temp = new DisjointSetForest(i);
            nodeMap.put(i, temp);
            if (i != 0) {
                temp.unionWith(nodeMap.get(0));
            }
        }

        for (final DisjointSetForest f : nodeMap.values()) {
            System.out.println(f);
        }
    }
}
