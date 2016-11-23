package com.AdvancedDataStructures;

/**
 * Created by xisisu on 16-11-22.
 */
public class DisjointSetLinkedListNode {
    public final Integer _val;
    public DisjointSetLinkedListNode _next;
    public DisjointSetLinkedListNode _root;
    public DisjointSetLinkedListNode(final Integer val) {
        _val = val;
        _next = null;
        _root = this;
    }
}
