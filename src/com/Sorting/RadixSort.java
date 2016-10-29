package com.Sorting;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sisu on 10/2/2016.
 */
public class RadixSort {
    /**
     * key property: countingSort is stable, so we can start with least significant digit
     */
    private void countingSortOnNthDigit(final List<Integer> input, final int divider) {
        final int[] count = new int[10]; // there are only 10 digits
        for (int i = 0; i < count.length; ++i) { count[i] = 0; }
        for (final Integer v : input) { ++count[(v/divider) % 10]; }
        for (int i = 1; i < count.length; ++i) { // so count[i] contains number of elements <= i
            count[i] += count[i-1];
        }
        final int[] result = new int[input.size()];
        for (int i = 0; i < result.length; ++i) { result[i] = 0; }
        for (int i = input.size()-1; i >= 0; --i) {
            final int idx = (input.get(i) / divider) % 10;
            result[count[idx]-1] = input.get(i);
            --count[idx];
        }
        input.clear();
        for (int i = 0; i < result.length; ++i) { input.add(result[i]); }
    }

    public void radixSort(final List<Integer> input, final int upperBound) {
        int divider = 1;
        int bound = upperBound;
        while (bound > 0) {
            countingSortOnNthDigit(input, divider);
            divider *= 10;
            bound /= 10;
        }
    }

    @Test
    public void test() {
        final int count = 1000;
        final int size = 1000;
        for (int i = 0; i < count; ++i) {
            final List<Integer> inputWithRepeatNumbers = Util.generateRandomInputWithRepeatedNumbers(size, size);
            radixSort(inputWithRepeatNumbers, size);
            Assert.assertTrue(Util.verifyIsSorted(inputWithRepeatNumbers));
        }
    }
}
