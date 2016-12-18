package com.AdvancedDataStructures;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by xisisu on 16-11-22.
 */
public class DisjointSetLinkedList {
    private DisjointSetLinkedListNode _head;
    private DisjointSetLinkedListNode _tail;

    public DisjointSetLinkedListNode getHead() { return _head; }
    public DisjointSetLinkedListNode getTail() { return _tail; }

    public DisjointSetLinkedList() {
        _head = null;
        _tail = null;
    }

    public void destroy() {
        _head = null;
        _tail = null;
    }

    public void makeSet(final Integer x) {
        DisjointSetLinkedListNode cur = new DisjointSetLinkedListNode(x);
        _head = cur;
        _tail = cur;
    }

    public void unionWith(DisjointSetLinkedList y) {
        if (_head == null) {
            _head = y._head;
            _tail = y._tail;
            y.destroy();
            return;
        }

        DisjointSetLinkedListNode cur = _tail;
        cur._next = y._head;
        while (cur != null) {
            cur._root = _head;
            cur = cur._next;
        }
        _tail = y._tail;
        y.destroy();
    }

    public DisjointSetLinkedListNode findRoot(final DisjointSetLinkedListNode x) {
        return x._root;
    }

    @Override
    public String toString() {
        String result = "";
        DisjointSetLinkedListNode cur = _head;
        while (cur != null) {
            result += "(" + cur._val + "," + cur._root._val + ") --> ";
            cur = cur._next;
        }
        result += "NULL";
        return result;
    }

    @Test
    public void testFindRoot() {
        DisjointSetLinkedList result = new DisjointSetLinkedList();
        for (int i = 0; i < 10; ++i) {
            DisjointSetLinkedList temp = new DisjointSetLinkedList();
            temp.makeSet(i);
            result.unionWith(temp);
        }
        System.out.println(result);

        final DisjointSetLinkedListNode head = result.getHead();
        DisjointSetLinkedListNode cur = result.getHead();
        Integer i = 0;
        while (cur != null) {
            assertEquals(i, cur._val);
            assertEquals(cur._root, head);
            cur = cur._next;
            i++;
        }
    }
}
