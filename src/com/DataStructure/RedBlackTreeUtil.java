package com.DataStructure;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by xisisu on 10/30/16.
 */
public class RedBlackTreeUtil {
    public static void preOrderPrintTree(final RedBlackTreeNode node) {
        if (node == null) { System.out.print("# "); return; }
        System.out.print(node.getVal() + " ");
        preOrderPrintTree(node.getLeft());
        preOrderPrintTree(node.getRight());
    }

    public static void inOrderPrintTree(final RedBlackTreeNode node) {
        if (node == null) { System.out.print("# "); return; }
        inOrderPrintTree(node.getLeft());
        System.out.print(node.getVal() + " ");
        inOrderPrintTree(node.getRight());
    }

    public static void postOrderPrintTree(final RedBlackTreeNode node) {
        if (node == null) { System.out.print("# "); return; }
        postOrderPrintTree(node.getLeft());
        postOrderPrintTree(node.getRight());
        System.out.print(node.getVal() + " ");
    }

    public static int getNodeSize(final RedBlackTreeNode node) {
        if (node == null) { return 0; }
        return 1 + getNodeSize(node.getLeft()) + getNodeSize(node.getRight());
    }

    public static boolean validateTree(final RedBlackTreeNode node) {
        if (node == null) { return true; }
        if (node.getLeft() != null) {
            if (node.getLeft().getParent() != node) { return false; }
            if (node.getLeft().getVal() > node.getVal()) { return false; }
        }
        if (node.getRight() != null) {
            if (node.getRight().getParent() != node) { return false; }
            if (node.getRight().getVal() < node.getVal()) { return false; }
        }
        if (node.getParent() != null) {
            if (node.getParent().getLeft() != node && node.getParent().getRight() != node) { return false; }
        }
        return validateTree(node.getLeft()) && validateTree(node.getRight());
    }

    private static RedBlackTreeNode minimun(final RedBlackTreeNode node) {
        if (node == null) { return null; }
        RedBlackTreeNode cur = node;
        while (cur.getLeft() != null) { cur = cur.getLeft(); }
        return cur;
    }

    private static RedBlackTreeNode maximum(final RedBlackTreeNode node) {
        if (node == null) { return null; }
        RedBlackTreeNode cur = node;
        while (cur.getRight() != null) { cur = cur.getRight(); }
        return cur;
    }

    public static RedBlackTreeNode treeNodeSuccessor(final RedBlackTreeNode node) {
        if (node == null) { return null; }
        RedBlackTreeNode cur = node;
        if (cur.getRight() != null) { return minimun(cur.getRight()); }
        RedBlackTreeNode parent = cur.getParent();
        while (parent != null && parent.getRight() == cur) {
            cur = parent;
            parent = cur.getParent();
        }
        return parent;
    }

    public static RedBlackTreeNode treeNodePredecessor(final RedBlackTreeNode node) {
        if (node == null) { return null; }
        RedBlackTreeNode cur = node;
        if (cur.getLeft() != null) { return maximum(cur.getLeft()); }
        RedBlackTreeNode parent = cur.getParent();
        while (parent != null && parent.getLeft() == cur) {
            cur = parent;
            parent = cur.getParent();
        }
        return parent;
    }

    public static List<Integer> generateRandomInput(final int size) {
        final List<Integer> result = new ArrayList<>();
        for (int i = 0; i < size; ++i) { result.add(i+1); }
        Collections.shuffle(result);
        return result;
    }

    public static boolean isLeftChild(final RedBlackTreeNode node) {
        return node != null && node.getParent() != null && node.getParent().getLeft() == node;
    }

    public static boolean isRightChild(final RedBlackTreeNode node) {
        return node != null && node.getParent() != null && node.getParent().getRight() == node;
    }
}
