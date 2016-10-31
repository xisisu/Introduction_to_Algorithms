package com.Sorting;

import org.junit.Assert;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

/**
 * Created by Sisu on 10/2/2016.
 */
public class QuickSort {
    private int partition(final List<Integer> input, final int start, final int end) {
        final int pivotValue = input.get(end);
        int small = start, large = end-1;
        while (small <= large) {
            while (small <= large && input.get(small) < pivotValue) { ++small; }
            while (small <= large && input.get(large) > pivotValue) { --large; }
            if (small > large) { break; }
            Collections.swap(input, small, large);
            ++small; --large;
        }
        Collections.swap(input, small, end);
        return small;
    }

    /**
     * quick sort from [start, end], inclusive
     */
    private void quickSort(final List<Integer> input, final int start, final int end) {
        if (start >= end) { return; }
        final int pivot = partition(input, start, end);
        quickSort(input, start, pivot-1);
        quickSort(input, pivot+1, end);
    }

    public void quickSort(final List<Integer> input) {
        quickSort(input, 0, input.size()-1);
    }

    @Test
    public void test() {
        final int count = 10000;
        final int size = 1000;
        for (int i = 0; i < count; ++i) {
            final List<Integer> input = Util.generateRandomInput(size);
            quickSort(input);
            Assert.assertTrue(Util.verifyIsSorted(input));

            final List<Integer> inputWithRepeatNumbers = Util.generateRandomInputWithRepeatedNumbers(size, size);
            quickSort(inputWithRepeatNumbers);
            Assert.assertTrue(Util.verifyIsSorted(inputWithRepeatNumbers));
        }
    }
}
