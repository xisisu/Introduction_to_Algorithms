package com.DataStructure;

/**
 * Created by Sisu on 10/9/2016.
 */
public class BinarySearchTreeNode {
    private int _val;
    private BinarySearchTreeNode _left;
    private BinarySearchTreeNode _right;
    private BinarySearchTreeNode _parent;

    public BinarySearchTreeNode(final int val) { _val = val; }

    public void setVal(final int val) { _val = val; }
    public int getVal() { return _val; }
    public void setLeft(final BinarySearchTreeNode val) { _left = val; }
    public void setRight(final BinarySearchTreeNode val) { _right = val; }
    public void setParent(final BinarySearchTreeNode val) { _parent = val; }
    public BinarySearchTreeNode getLeft() { return _left; }
    public BinarySearchTreeNode getRight() { return _right; }
    public BinarySearchTreeNode getParent() { return _parent; }

    public String toString() {
        String parent = _parent == null ? "N" : Integer.toString(_parent.getVal());
        return Integer.toString(_val) + "(" + parent + "), ";
    }
}
