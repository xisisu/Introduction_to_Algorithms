package com.Probabilities;

import org.junit.Test;

import java.util.HashSet;
import java.util.Random;

/**
 * Created by Sisu on 10/2/2016.
 */
public class BirthdayProblem {
    /**
     * given number of people n, return if two people have the same birthday
     * answer: k*(k-1) / 2n. if >50%, 23 people
     * @param n number of people
     * @return true if at least two people have same birthday
     */
    public boolean sameBirthday(final int n) {
        if (n <= 1) { return false; }
        final HashSet<Integer> birthDay = new HashSet<>();
        final Random random = new Random();
        for (int i = 0; i < n; ++i) {
            int cur = random.nextInt(365);
            if (birthDay.contains(cur)) { return true; }
            else { birthDay.add(cur); }
        }
        return false;
    }

    @Test
    public void test() {
        int n = 100;
        int count = 10000;
        System.out.println("For each test, we repeat " + count + " times to take the average.");
        for (int i = 1; i < n; ++i) {
            int sum = 0;
            for (int j = 0; j < count; ++j) {
                if (sameBirthday(i)) { ++sum; }
            }
            System.out.println(i + " people, same birthday happens " + sum + " times in " + count + " test, rate: " + (double)sum / count);
        }
    }

}
