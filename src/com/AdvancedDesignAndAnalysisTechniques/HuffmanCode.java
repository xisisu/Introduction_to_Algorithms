package com.AdvancedDesignAndAnalysisTechniques;

import org.junit.Test;
import org.testng.Assert;

import java.util.*;

/**
 * Greedy algorithm, huffman code
 * Created by xisisu on 11/13/16.
 */
public class HuffmanCode {
    final private char _unused = '*';

    public Map<Character, String> huffmanCode(final String words) {
        // construct freq map
        final Map<Character, Integer> freqMap = getFreqMap(words);
        System.out.println("freqMap is: " + freqMap);

        // construct priority heap, sort by frequency
        final PriorityQueue<TreeNode> queue = initQueue(freqMap);

        // start huffman coding
        final TreeNode root = encode(queue);

        // now constructing the final result, using pre-order traversal
        return decode(root);
    }

    private Map<Character, String> decode(final TreeNode root) {
        final Map<Character, String> result = new HashMap<>();
        if (root.isLeaf()) {
            result.put(root._char, "0");
            return result; // if only one node, just return 0 as mapping
        }

        final Stack<TreeNode> s = new Stack<>();
        TreeNode cur = root;
        while (cur != null || !s.isEmpty()) {
            if (cur != null) {
                s.push(cur);
                final String code = cur._code;
                cur = cur._left;
                if (cur != null) { cur._code = code + "0"; }
            } else {
                cur = s.pop();
                final String code = cur._code;
                cur = cur._right;
                if (cur != null) { cur._code = code + "1"; }
            }

            if (cur != null && cur.isLeaf() && !result.containsKey(cur._char)) {
                result.put(cur._char, cur._code);
            }
        }

        return result;
    }

    private Map<Character, Integer> getFreqMap(final String words) {
        final Map<Character, Integer> freqMap = new HashMap<>();
        for (int i = 0; i < words.length(); ++i) {
            final char c = words.charAt(i);
            if (!freqMap.containsKey(c)) { freqMap.put(c, 0); }
            freqMap.put(c, freqMap.get(c) + 1);
        }
        return freqMap;
    }

    private PriorityQueue<TreeNode> initQueue(final Map<Character, Integer> freqMap) {
        final PriorityQueue<TreeNode> queue = new PriorityQueue<>(freqMap.size());
        for (final char c : freqMap.keySet()) {
            queue.add(new TreeNode(c, freqMap.get(c)));
        }
        return queue;
    }

    private TreeNode encode(final PriorityQueue<TreeNode> queue) {
        while (queue.size() >= 2) {
            final TreeNode left = queue.poll(); // left code with 0
            final TreeNode right = queue.poll(); // right code with 1
            final TreeNode root = new TreeNode(_unused, left._freq + right._freq);
            root._left = left;
            root._right = right;
            queue.add(root);
        }
        assert(queue.size() == 1);
        return queue.poll();
    }

    private class TreeNode implements Comparable<TreeNode> {
        final private char _char;
        final private Integer _freq;
        private TreeNode _left;
        private TreeNode _right;
        private String _code;

        public TreeNode(final char c, final Integer freq) {
            _char = c;
            _freq = freq;
            _left = null;
            _right = null;
            _code = "";
        }

        public boolean isLeaf() { return _left == null && _right == null; }

        @Override
        public String toString() {
            return "(" + _char + ", " + _freq + ", " + _code + ")";
        }

        @Override
        public int compareTo(final TreeNode that) {
            return this._freq - that._freq;
        }
    }

    @Test
    public void test() {
        // example in 16.5
        final String words = "fffffeeeeeeeeeccccccccccccbbbbbbbbbbbbbddddddddddddddddaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
        final Map<Character, String> result = huffmanCode(words);
        Assert.assertEquals(result.get('a'), "0");
        Assert.assertEquals(result.get('b'), "101");
        Assert.assertEquals(result.get('c'), "100");
        Assert.assertEquals(result.get('d'), "111");
        Assert.assertEquals(result.get('e'), "1101");
        Assert.assertEquals(result.get('f'), "1100");
    }
}
