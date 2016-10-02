package com.Probabilities;

import org.junit.Test;

import java.util.Random;

/**
 * Created by Sisu on 10/2/2016.
 */
public class NumberOfFlips {
    /**
     * return number of flips until n connective coins appears
     * @param n
     * @return
     */
    public long NumberOfFlipsUntil(final int n) {
        if (n <= 1) { return n; }

        final Random random = new Random();
        long res = 0;
        int pre = -1, count = 0;
        while (true) {
            ++res;
            final int cur = random.nextInt() % 2;
            if (cur == pre) { ++count; }
            else { pre = cur; count = 1; }
            if (count >= n) {
                return res;
            }
        }
    }

    @Test
    public void test() {
        int n = 10;
        int count = 10000;
        System.out.println("For each test, we repeat " + count + " times to take the average.");
        for (int i = 1; i < n; ++i) {
            int sum = 0;
            for (int j = 0; j < count; ++j) {
                sum += NumberOfFlipsUntil(i);
            }
            System.out.println(i + " consecutive coins, average flip is " + (double)sum / count);
        }
    }
}
