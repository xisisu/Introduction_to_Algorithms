package com.Probabilities;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Sisu on 10/2/2016.
 */
public class HiringProblem {
    /**
     * suppose we always hire new assistant when the candidate is better than the current one. calculate the number of
     * hires that happens given input n
     *
     * answer: ln n
     * @param n total number of candidates
     * @return number of hires
     */
    public int numberOfHires(final int n) {
        if (n <= 1) { return n; }

        // create input, random its abilities
        List<Integer> candidates = new ArrayList<>();
        for (int i = 0; i < n; ++i) { candidates.add(i+1); }
        Collections.shuffle(candidates);

        Integer cur = Integer.MIN_VALUE;
        int hire = 0;
        for (int i = 0; i < candidates.size(); ++i) {
            if (candidates.get(i) > cur) {
                cur = candidates.get(i);
                ++hire;
            }
        }

        return hire;
    }

    @Test
    public void test() {
        int sum = 0;
        final int count = 10000;
        final int n = 10000;

        for (int i = 0; i < count; ++i) {
            sum += numberOfHires(n);
        }

        System.out.println("Candidate pool size: " + n + ", average number of hires: " + (double)sum / count + ", expect: " + Math.log(n));
    }
}
