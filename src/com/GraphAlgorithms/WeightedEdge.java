package com.GraphAlgorithms;

/**
 * Created by xisisu on 16-11-27.
 */
public class WeightedEdge implements Comparable<WeightedEdge> {
    Integer _from;
    Integer _to;
    Integer _weight;

    public WeightedEdge(final Integer from, final Integer to, final Integer weight) {
        _from = from;
        _to = to;
        _weight = weight;
    }

    @Override
    public String toString() {
        return "Edge{" +
                "_from=" + _from +
                ", _to=" + _to +
                ", _weight=" + _weight +
                '}';
    }

    @Override
    public int compareTo(WeightedEdge edge) {
        return _weight - edge._weight;
    }
}

