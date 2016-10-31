package com.DataStructure;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static com.DataStructure.RedBlackTreeUtil.*;

/**
 * Created by xisisu on 10/30/16.
 *
 * TODO: insertion and deletion
 *
 * 1. Every node is either red or black
 * 2. The root is black
 * 3. Every leaf (NIL) is black
 * 4. If a node is red, then both its children are black
 * 5. For each node, all paths from the node to decendant leaves contain the same number of black nodes
 */
public class RedBlackTree {
    private RedBlackTreeNode _root;

    public RedBlackTree() { _root = null; }

    public RedBlackTreeNode getRoot() { return _root; }

    public String toString() {
        System.out.print(" preOrder: ");
        preOrderPrintTree(_root);
        System.out.println("");
        System.out.print("  inOrder: ");
        inOrderPrintTree(_root);
        System.out.println("");
        System.out.print("postOrder: ");
        postOrderPrintTree(_root);
        System.out.println("");

        return "";
    }

    private void redBlackColorFix(final RedBlackTreeNode z) {
        // TODO: implement this
    }

    public void insert(final int val) {
        final RedBlackTreeNode newNode = new RedBlackTreeNode(val);
        if (_root == null) { _root = newNode; return; }

        RedBlackTreeNode pre = null;
        RedBlackTreeNode cur = _root;
        while (cur != null) {
            pre = cur;
            if (cur.getVal() < val) { cur = cur.getRight(); }
            else if (cur.getVal() > val) { cur = cur.getLeft(); }
            else { System.out.println("Node " + val + " already exists"); return; }
        }

        newNode.setParent(pre);
        if (val > pre.getVal()) { pre.setRight(newNode); }
        else { pre.setLeft(newNode); }

        // extra part for redBlackTree
        newNode.setColor(RedBlackTreeColor.RED);
        redBlackColorFix(newNode);
    }

    public boolean exists(final int val) {
        return findNode(val) != null;
    }

    public boolean existsRecursive(final int val) {
        return existsRecursive(val, _root);
    }

    public RedBlackTreeNode findNode(final int val) {
        RedBlackTreeNode cur = _root;
        while (cur != null) {
            if (cur.getVal() < val) { cur = cur.getRight(); }
            else if (cur.getVal() > val) { cur = cur.getLeft(); }
            else { return cur; }
        }
        return null;
    }

    private boolean existsRecursive(final int val, final RedBlackTreeNode cur) {
        if (cur == null) { return false; }
        if (cur.getVal() < val) { return existsRecursive(val, cur.getRight()); }
        else if (cur.getVal() > val) { return existsRecursive(val, cur.getLeft()); }
        else { return true; }
    }

    public void delete(final int val) {
        final RedBlackTreeNode node = findNode(val);
        if (node == null) { return; }
        // delete single node
        if (node.getLeft() == null && node.getRight() == null && node.getParent() == null) { _root = null; return; }

        final RedBlackTreeNode nodeToRemove = (node.getLeft() == null || node.getRight() == null) ?
                node :
                treeNodeSuccessor(node);
        final RedBlackTreeNode childOfRemoveNode = (nodeToRemove.getLeft() != null) ? nodeToRemove.getLeft() : nodeToRemove.getRight();
        if (childOfRemoveNode != null) { childOfRemoveNode.setParent(nodeToRemove.getParent()); }
        if (nodeToRemove.getParent() == null) { _root = childOfRemoveNode; }
        else if (nodeToRemove == nodeToRemove.getParent().getLeft()) { nodeToRemove.getParent().setLeft(childOfRemoveNode); }
        else { nodeToRemove.getParent().setRight(childOfRemoveNode); }
        node.setVal(nodeToRemove.getVal());
    }

    /**
     *     x                                y
     *    / \    --- leftRotate  -->       / \
     *   a  y                             x  c
     *     / \   <-- rightRotate ---     / \
     *    b  c                          a  b
     */

    public void leftRotate(final RedBlackTreeNode x) {
        final RedBlackTreeNode y = x.getRight();
        assert(y != null);
        // y's left subtree to x' right subtree
        x.setRight(y.getLeft());
        y.getLeft().setParent(x);

        // x's parent to y's parent
        y.setParent(x.getParent());

        if (x == _root) { // x is root
            _root = y;
        } else if (isLeftChild(x)) { // x is left child
            x.getParent().setLeft(y);
        } else { // x must be right child
            x.getParent().setRight(y);
        }

        y.setLeft(x);
        x.setParent(y);
    }

    public void rightRotate(final RedBlackTreeNode y) {
        final RedBlackTreeNode x = y.getLeft();
        assert(x != null);
        // x's right subtree to y' left subtree
        y.setLeft(x.getRight());
        x.getRight().setParent(y);

        // y's parent to x's parent
        x.setParent(y.getParent());

        if (y == _root) { // y is root
            _root = x;
        } else if (isLeftChild(y)) { // y is left child
            y.getParent().setLeft(x);
        } else { // y must be right child
            y.getParent().setRight(x);
        }

        x.setRight(y);
        y.setParent(x);
    }

    @Test public void testRotateRoot() {
        final RedBlackTree tree = new RedBlackTree();
        final List<Integer> data = Arrays.asList(5,3,7,6,8);
        for (final Integer val : data) { tree.insert(val); }
        System.out.println("input: " + data);
        System.out.println(tree);

        final RedBlackTreeNode x = tree.findNode(5);
        tree.leftRotate(x);
        System.out.println("After left rotate 5");
        System.out.println(tree);

        final RedBlackTreeNode y = tree.findNode(7);
        tree.rightRotate(y);
        System.out.println("After right rotate 7");
        System.out.println(tree);
    }

    @Test public void testRotate() {
        final RedBlackTree tree = new RedBlackTree();
        final List<Integer> data = Arrays.asList(1,5,3,7,6,8);
        for (final Integer val : data) { tree.insert(val); }
        System.out.println("input: " + data);
        System.out.println(tree);

        final RedBlackTreeNode x = tree.findNode(5);
        tree.leftRotate(x);
        System.out.println("After left rotate 5");
        System.out.println(tree);

        final RedBlackTreeNode y = tree.findNode(7);
        tree.rightRotate(y);
        System.out.println("After right rotate 7");
        System.out.println(tree);
    }
}
