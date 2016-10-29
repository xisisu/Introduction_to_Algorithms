package com.Probabilities;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Sisu on 10/2/2016.
 */
public class HireOnce {
    /**
     * assume there are n candidates, and we can only hire once.
     * we record the max value among the first n/e, and then when we encounter a larger value, we hire it.
     * calculate the hired people's rank
     * @param n number of candidates
     * @return hired candidate rank
     */
    public int hireOnce(final int n) {
        if (n <= 1) { return n; }
        final Double EULER_NUMBER = 2.71828;

        // create input, random its abilities
        List<Integer> candidates = new ArrayList<>();
        for (int i = 0; i < n; ++i) { candidates.add(i+1); }
        Collections.shuffle(candidates);

        // record max among first n/e
        int curMax = Integer.MIN_VALUE;
        int threshold = (int) (n / EULER_NUMBER);
        for (int i = 0; i < candidates.size(); ++i) {
            if (i < threshold) { curMax = Integer.max(curMax, candidates.get(i)); }
            else if (candidates.get(i) > curMax) { return n - candidates.get(i); }
        }

        // hire the last one
        return n - candidates.get(n-1);
    }

    @Test
    public void test() {
        int sum = 0;
        final int count = 10000;
        final int n = 1000;

        for (int i = 0; i < count; ++i) {
            sum += hireOnce(n);
        }

        System.out.println("Candidate pool size: " + n + ", average rank of hires: " + sum / count);
    }
}
