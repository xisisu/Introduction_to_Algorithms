package com.DataStructure;

import org.junit.Test;
import sun.util.resources.cldr.so.CalendarData_so_ET;

/**
 * Created by Sisu on 10/4/2016.
 */
public class DoubleLinkedList {
    private Node _sentinel;

    public DoubleLinkedList() {
        _sentinel = new Node(null);
        _sentinel.setNext(_sentinel);
        _sentinel.setPrev(_sentinel);
    }

    public void insertVal(final Integer val) {
        final Node cur = new Node(val);
        _sentinel.getNext().setPrev(cur);
        cur.setNext(_sentinel.getNext());
        _sentinel.setNext(cur);
        cur.setPrev(_sentinel);
    }

    public boolean exists(final Integer val) {
        Node cur = _sentinel.getNext();
        while (cur != _sentinel) {
            if (cur.getVal().equals(val)) { return true; }
            cur = cur.getNext();
        }
        return false;
    }

    public void deleteVal(final Integer val) {
        Node cur = _sentinel.getNext();
        while (cur != _sentinel) {
            if (cur.getVal().equals(val)) {
                cur.getNext().setPrev(cur.getPrev());
                cur.getPrev().setNext(cur.getNext());
                cur.setNext(null);
                cur.setPrev(null);
                return;
            }
            cur = cur.getNext();
        }
    }

    public void prettyPrint() {
        Node cur = _sentinel.getNext();
        while (cur != _sentinel) {
            System.out.print(cur.getVal() + " --> ");
            cur = cur.getNext();
        }
        System.out.println(" NULL. ");
    }

    public void prettyPrintReverse() {
        Node cur = _sentinel.getPrev();
        System.out.print("NULL ");
        while (cur != _sentinel) {
            System.out.print(" <-- " + cur.getVal());
            cur = cur.getPrev();
        }
        System.out.println("");
    }

    private class Node {
        Node _prev;
        Node _next;
        Integer _val;

        public Node(final Integer val) {
            _prev = null;
            _next = null;
            _val = val;
        }

        public Integer getVal() { return _val; }

        public void setNext(final Node next) { _next = next; }
        public void setPrev(final Node prev) { _prev = prev; }
        public Node getNext() { return _next; }
        public Node getPrev() { return _prev; }
    }

    @Test
    public void test() {
        final DoubleLinkedList list = new DoubleLinkedList();

        System.out.println("Test insertion");
        for (int i = 0; i < 10; ++i) {
            list.insertVal(i);
            list.prettyPrint();
            list.prettyPrintReverse();
        }

        System.out.println("Test exist");
        for (int i = 0; i < 20; i += 3) {
            System.out.println("Checking: " + i + ", " + list.exists(i));
        }

        System.out.println("Test deletion");
        for (int i = 0; i < 10; ++i) {
            list.deleteVal(i);
            list.prettyPrint();
            list.prettyPrintReverse();
        }
    }
}
