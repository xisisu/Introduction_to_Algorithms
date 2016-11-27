package com.GraphAlgorithms;

/**
 * Created by xisisu on 16-11-27.
 */
public class WeightedNode {
    Integer _node;
    Integer _weight;
    public WeightedNode(Integer node, Integer weight) {
        _node = node;
        _weight = weight;
    }

    @Override
    public String toString() {
        return "WeightedNode{" +
                "_node=" + _node +
                ", _weight=" + _weight +
                '}';
    }
}
