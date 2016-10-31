package com.DataStructure;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.DataStructure.BinarySearchTreeUtil.*;

/**
 * Created by Sisu on 10/9/2016.
 */
public class BinarySearchTree {
    private BinarySearchTreeNode _root;

    public BinarySearchTree() { _root = null; }

    public BinarySearchTreeNode getRoot() { return _root; }

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

    public void insert(final int val) {
        final BinarySearchTreeNode newNode = new BinarySearchTreeNode(val);
        if (_root == null) { _root = newNode; return; }

        BinarySearchTreeNode pre = null;
        BinarySearchTreeNode cur = _root;
        while (cur != null) {
            pre = cur;
            if (cur.getVal() < val) { cur = cur.getRight(); }
            else if (cur.getVal() > val) { cur = cur.getLeft(); }
            else { System.out.println("Node " + val + " already exists"); return; }
        }

        newNode.setParent(pre);
        if (val > pre.getVal()) { pre.setRight(newNode); }
        else { pre.setLeft(newNode); }
    }

    public boolean exists(final int val) {
        return findNode(val) != null;
    }

    public boolean existsRecursive(final int val) {
        return existsRecursive(val, _root);
    }

    public BinarySearchTreeNode findNode(final int val) {
        BinarySearchTreeNode cur = _root;
        while (cur != null) {
            if (cur.getVal() < val) { cur = cur.getRight(); }
            else if (cur.getVal() > val) { cur = cur.getLeft(); }
            else { return cur; }
        }
        return null;
    }

    private boolean existsRecursive(final int val, final BinarySearchTreeNode cur) {
        if (cur == null) { return false; }
        if (cur.getVal() < val) { return existsRecursive(val, cur.getRight()); }
        else if (cur.getVal() > val) { return existsRecursive(val, cur.getLeft()); }
        else { return true; }
    }

    public void delete(final int val) {
        final BinarySearchTreeNode node = findNode(val);
        if (node == null) { return; }
        // delete single node
        if (node.getLeft() == null && node.getRight() == null && node.getParent() == null) { _root = null; return; }

        final BinarySearchTreeNode nodeToRemove = (node.getLeft() == null || node.getRight() == null) ?
                                            node :
                                            treeNodeSuccessor(node);
        final BinarySearchTreeNode childOfRemoveNode = (nodeToRemove.getLeft() != null) ? nodeToRemove.getLeft() : nodeToRemove.getRight();
        if (childOfRemoveNode != null) { childOfRemoveNode.setParent(nodeToRemove.getParent()); }
        if (nodeToRemove.getParent() == null) { _root = childOfRemoveNode; }
        else if (nodeToRemove == nodeToRemove.getParent().getLeft()) { nodeToRemove.getParent().setLeft(childOfRemoveNode); }
        else { nodeToRemove.getParent().setRight(childOfRemoveNode); }
        node.setVal(nodeToRemove.getVal());
    }

    @Test public void testInsert() {
        final Integer testSize = 100;
        final BinarySearchTree tree = new BinarySearchTree();
        final List<Integer> data = generateRandomInput(testSize);
        int i = 1;
        for (final Integer val : data) {
            tree.insert(val);
            assert(validateTree(tree.getRoot()));
            assert(getNodeSize(tree.getRoot()) == i);
            ++i;
        }
        System.out.println("input: " + data);
        System.out.println(tree);
    }

    @Test public void testExists() {
        final Integer testSize = 100;
        final BinarySearchTree tree = new BinarySearchTree();
        final List<Integer> data = generateRandomInput(testSize);
        data.forEach(tree::insert);
        System.out.println("input: " + data);
        System.out.println(tree);

        for (int i = 1; i <= testSize*2; i += 3) {
            if (i <= testSize) { assert(tree.exists(i)); }
            else { assert(!tree.exists(i)); }
        }
    }

    @Test public void testDeleteAlwaysRightChild() {
        final Integer testSize = 10;
        final List<Integer> data = new ArrayList<>();
        for (int i = 1; i <= testSize; ++i) { data.add(i); }

        final BinarySearchTree tree = new BinarySearchTree();
        data.forEach(tree::insert);
        System.out.println(tree);

        int i = testSize;
        for (final Integer val : data) {
            assert(getNodeSize(tree.getRoot()) == i);
            tree.delete(val);
            --i;
            assert(validateTree(tree.getRoot()));
        }
    }

    @Test public void testDeleteAlwaysLeftChild() {
        final Integer testSize = 10;
        final List<Integer> data = new ArrayList<>();
        for (int i = testSize; i >= 1; --i) { data.add(i); }

        final BinarySearchTree tree = new BinarySearchTree();
        data.forEach(tree::insert);
        System.out.println(tree);

        int i = testSize;
        for (final Integer val : data) {
            assert(getNodeSize(tree.getRoot()) == i);
            tree.delete(val);
            --i;
            assert(validateTree(tree.getRoot()));
        }
    }

    @Test public void testDeleteMixed() {
        final Integer testSize = 10;
        final List<Integer> data = new ArrayList<>();
        for (int i = testSize; i >= 1; --i) { data.add(i); }
        BinarySearchTree tree = new BinarySearchTree();
        data.forEach(tree::insert);
        System.out.println(tree);

        Collections.shuffle(data);
        int i = testSize;
        for (final Integer val : data) {
            assert(getNodeSize(tree.getRoot()) == i);
            tree.delete(val);
            --i;
            assert(validateTree(tree.getRoot()));
        }
    }

    @Test public void testMixedCase() {
        final Integer testSize = 1000;
        final BinarySearchTree tree = new BinarySearchTree();
        final List<Integer> data = generateRandomInput(testSize);
        data.forEach(tree::insert);
        System.out.println(tree);

        Collections.shuffle(data);
        int i = testSize;
        for (final Integer val : data) {
            assert(getNodeSize(tree.getRoot()) == i);
            tree.delete(val);
            --i;
            assert(validateTree(tree.getRoot()));
        }
    }

    @Test public void testParent() {
        final BinarySearchTree tree = new BinarySearchTree();
        final List<Integer> data = generateRandomInput(10);
        data.forEach(tree::insert);

        System.out.println("input: " + data);
        System.out.println(tree);

        for (int i = 0; i <= 10; ++i) {
            BinarySearchTreeNode cur = tree.findNode(i);
            System.out.println("Find " + i + ", " + cur);
        }
    }
}

