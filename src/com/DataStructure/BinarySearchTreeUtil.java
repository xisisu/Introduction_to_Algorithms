package com.DataStructure;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Sisu on 10/9/2016.
 */
public class BinarySearchTreeUtil {
    public static void preOrderPrintTree(final BinarySearchTreeNode node) {
        if (node == null) { System.out.print("# "); return; }
        System.out.print(node.getVal() + " ");
        preOrderPrintTree(node.getLeft());
        preOrderPrintTree(node.getRight());
    }

    public static void inOrderPrintTree(final BinarySearchTreeNode node) {
        if (node == null) { System.out.print("# "); return; }
        inOrderPrintTree(node.getLeft());
        System.out.print(node.getVal() + " ");
        inOrderPrintTree(node.getRight());
    }

    public static void postOrderPrintTree(final BinarySearchTreeNode node) {
        if (node == null) { System.out.print("# "); return; }
        postOrderPrintTree(node.getLeft());
        postOrderPrintTree(node.getRight());
        System.out.print(node.getVal() + " ");
    }

    public static int getNodeSize(final BinarySearchTreeNode node) {
        if (node == null) { return 0; }
        return 1 + getNodeSize(node.getLeft()) + getNodeSize(node.getRight());
    }

    public static boolean validateTree(final BinarySearchTreeNode node) {
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

    private static BinarySearchTreeNode minimum(final BinarySearchTreeNode node) {
        if (node == null) { return null; }
        BinarySearchTreeNode cur = node;
        while (cur.getLeft() != null) { cur = cur.getLeft(); }
        return cur;
    }

    private static BinarySearchTreeNode maximum(final BinarySearchTreeNode node) {
        if (node == null) { return null; }
        BinarySearchTreeNode cur = node;
        while (cur.getRight() != null) { cur = cur.getRight(); }
        return cur;
    }

    public static BinarySearchTreeNode treeNodeSuccessor(final BinarySearchTreeNode node) {
        if (node == null) { return null; }
        BinarySearchTreeNode cur = node;
        if (cur.getRight() != null) { return minimum(cur.getRight()); }
        BinarySearchTreeNode parent = cur.getParent();
        while (parent != null && parent.getRight() == cur) {
            cur = parent;
            parent = cur.getParent();
        }
        return parent;
    }

    public static BinarySearchTreeNode treeNodePredecessor(final BinarySearchTreeNode node) {
        if (node == null) { return null; }
        BinarySearchTreeNode cur = node;
        if (cur.getLeft() != null) { return maximum(cur.getLeft()); }
        BinarySearchTreeNode parent = cur.getParent();
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
}
