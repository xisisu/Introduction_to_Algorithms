package com.AdvancedDataStructures;

import org.junit.Before;
import org.testng.annotations.Test;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.AdvancedDataStructures.DisjointSetForest.getRoot;
import static org.junit.Assert.assertEquals;

/**
 * Fig 21.2
 * Created by xisisu on 16-11-22.
 */
public class ConnectedComponents {
    private final static Integer MAP_SIZE = 10;
    private final List<Pair> _edges = new ArrayList<>();
    private void setUp() {
        // create edges
        _edges.add(new Pair(1,2));
        _edges.add(new Pair(1,3));
        _edges.add(new Pair(2,3));
        _edges.add(new Pair(2,4));

        _edges.add(new Pair(5,6));
        _edges.add(new Pair(5,7));

        _edges.add(new Pair(8,9));

        // node 10 is single node
    }

    private Map<Integer, DisjointSetLinkedList> setupMap(final int size) {
        final Map<Integer, DisjointSetLinkedList> result = new HashMap<>();
        for (int i = 1; i <= size; ++i) {
            DisjointSetLinkedList temp = new DisjointSetLinkedList();
            temp.makeSet(i);
            result.put(i, temp);
        }
        return result;
    }

    @Test
    public void testLinkedList() {
        setUp();
        final Map<Integer, DisjointSetLinkedList> nodeMap = setupMap(MAP_SIZE);

        int result = MAP_SIZE;
        for (final Pair edge : _edges) {
            final DisjointSetLinkedList left  = nodeMap.get(edge.fst);
            final DisjointSetLinkedList right = nodeMap.get(edge.snd);
            if (left.getHead() != right.getHead()) {
                left.unionWith(right);
                result--;
                right.destroy();
                nodeMap.put(edge.snd, left); // override it in nodeMap
            }
        }

        assertEquals(result, 4);

        for (final Integer key : nodeMap.keySet()) {
            System.out.println(key + " in set " + nodeMap.get(key).getHead()._val);
        }
    }

    private Map<Integer, DisjointSetForest> setupMapForest(final int size) {
        final Map<Integer, DisjointSetForest> result = new HashMap<>();
        for (int i = 1; i <= size; ++i) {
            DisjointSetForest temp = new DisjointSetForest(i);
            result.put(i, temp);
        }
        return result;
    }


    @Test
    public void testForest() {
        setUp();
        final Map<Integer, DisjointSetForest> nodeMap = setupMapForest(MAP_SIZE);

        int result = MAP_SIZE;
        for (final Pair edge : _edges) {
            final DisjointSetForest left  = nodeMap.get(edge.fst);
            final DisjointSetForest right = nodeMap.get(edge.snd);
            if (getRoot(left) != getRoot(right)) {
                left.unionWith(right);
                result--;
            }
        }

        assertEquals(result, 4);

        for (final Integer key : nodeMap.keySet()) {
            System.out.println(key + " in set " + getRoot(nodeMap.get(key)));
        }
    }

    class Pair {
        final Integer fst;
        final Integer snd;
        public Pair(final Integer a, final Integer b) {
            fst = a;
            snd = b;
        }
    }
}
