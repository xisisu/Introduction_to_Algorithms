package com.DataStructure;

/**
 * Created by xisisu on 10/30/16.
 */
public class RedBlackTreeNode {
    private int _val;
    private RedBlackTreeColor _color;
    private RedBlackTreeNode _left;
    private RedBlackTreeNode _right;
    private RedBlackTreeNode _parent;

    // root is black
    public RedBlackTreeNode(final int val) { _val = val; _color = RedBlackTreeColor.BLACK; }

    public void setVal(final int val) { _val = val; }
    public int getVal() { return _val; }
    public void setColor(final RedBlackTreeColor color) { _color = color; }
    public RedBlackTreeColor getColor() { return _color; }

    public void setLeft(final RedBlackTreeNode val) { _left = val; }
    public void setRight(final RedBlackTreeNode val) { _right = val; }
    public void setParent(final RedBlackTreeNode val) { _parent = val; }
    public RedBlackTreeNode getLeft() { return _left; }
    public RedBlackTreeNode getRight() { return _right; }
    public RedBlackTreeNode getParent() { return _parent; }

    public String toString() {
        String parent = _parent == null ? "N" : Integer.toString(_parent.getVal());
        return Integer.toString(_val) + "(" + parent + ", " + _color + "), ";
    }
}
