package com.Probabilities;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.Random;

/**
 * Created by Sisu on 10/2/2016.
 */
public class BallsAndBins {
    /**
     * given number of bins, return number of balls to let each bin contains at least 1 ball
     * answer: n*ln(n) + n
     * @param bins
     * @return
     */
    public long numberOfBalls(final int bins) {
        final Set<Integer> binSets = new HashSet<>();
        final Random random = new Random();
        long count = 0;
        while (binSets.size() < bins) {
            binSets.add(random.nextInt(bins)); // generates [0, bins)
            ++count;
        }
        return count;
    }

    @Test
    public void test() {
        int n = 100;
        int count = 10000;
        System.out.println("For each test, we repeat " + count + " times to take the average.");
        for (int i = 1; i < n; ++i) {
            long sum = 0;
            for (int j = 0; j < count; ++j) {
                sum += numberOfBalls(i);
            }
            System.out.println(i + " bins, need an average of " + (double)sum / count + " balls to fill all. expect: " + (i + (double)i * Math.log(i)));
        }
    }
}
