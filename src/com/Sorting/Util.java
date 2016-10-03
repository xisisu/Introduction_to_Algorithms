package com.Sorting;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Created by Sisu on 10/2/2016.
 */
public class Util {
    /**
     * return random input from 1 - n, in ArrayList format
     */
    public static ArrayList<Integer> GenerateRandomInput(final int n) {
        final Random random = new Random();
        final ArrayList<Integer> result = new ArrayList<>();
        for (int i = 0; i < n; ++i) { result.add(i+1); }
        Collections.shuffle(result);
        return result;
    }

    public static boolean VerifyIsSorted(final ArrayList<Integer> input) {
        for (int i = 1; i < input.size(); ++i) { if (input.get(i) < input.get(i-1)) { return false; } }
        return true;
    }

    @Test
    public void test() {
        final ArrayList<Integer> result = GenerateRandomInput(100);
        for (int i = 0; i < result.size(); ++i) {
            System.out.println(result.get(i) + ", ");
        }
    }
}
