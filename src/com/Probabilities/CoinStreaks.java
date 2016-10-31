package com.Probabilities;

import org.junit.Test;

import java.util.Random;

/**
 * Created by Sisu on 10/2/2016.
 */
public class CoinStreaks {
    /**
     * flip the coin n times, return the longest streak of consecutive
     * answer: ln(n)
     */
    public int longestStreaks(final int n) {
        final Random random = new Random();
        int res = 0, pre = -1, count = 0;
        for (int i = 0; i < n; ++i) {
            final int cur = random.nextInt() % 2;
            if (cur == pre) { ++count; res = Integer.max(res, count); }
            else { pre = cur; count = 1; }
        }
        return res;
    }

    @Test
    public void test() {
        int n = 100;
        int count = 10000;
        System.out.println("For each test, we repeat " + count + " times to take the average.");
        for (int i = 1; i < n; ++i) {
            int sum = 0;
            for (int j = 0; j < count; ++j) {
                sum += longestStreaks(i);
            }
            System.out.println(i + " coins, average longest streaks is " + (double)sum / count);
        }
    }
}
