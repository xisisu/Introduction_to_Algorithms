package com.Sorting;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by Sisu on 10/2/2016.
 */
public class Util {
    /**
     * return random input from 1 - n, in ArrayList format
     */
    public static List<Integer> generateRandomInput(final int n) {
        final ArrayList<Integer> result = new ArrayList<>();
        for (int i = 0; i < n; ++i) { result.add(i+1); }
        Collections.shuffle(result);
        return result;
    }

    /**
     * upperBound is not inclusive
     */
    public static List<Integer> generateRandomInputWithRepeatedNumbers(final int size, final int upperBound) {
        final Random random = new Random();
        final ArrayList<Integer> result = new ArrayList<>();
        for (int i = 0; i < size; ++i) { result.add(random.nextInt(upperBound)); }
        Collections.shuffle(result);
        return result;
    }

    public static List<Integer> generateRandomInputWithRepeatedNumbers(final int size) {
        final Random random = new Random();
        final ArrayList<Integer> result = new ArrayList<>();
        for (int i = 0; i < size; ++i) { result.add(random.nextInt()); }
        Collections.shuffle(result);
        return result;
    }

    public static boolean verifyIsSorted(final List<Integer> input) {
        for (int i = 1; i < input.size(); ++i) { if (input.get(i) < input.get(i-1)) { return false; } }
        return true;
    }

    @Test
    public void test() {
        final List<Integer> result = generateRandomInput(100);
        for (int i = 0; i < result.size(); ++i) {
            System.out.println(result.get(i) + ", ");
        }
    }
}
