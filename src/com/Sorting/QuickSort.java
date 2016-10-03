package com.Sorting;

import com.sun.xml.internal.bind.v2.model.annotation.Quick;
import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Sisu on 10/2/2016.
 */
public class QuickSort {
    private int Partition(final ArrayList<Integer> input, final int start, final int end) {
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
    private void QuickSort(final ArrayList<Integer> input, final int start, final int end) {
        if (start >= end) { return; }
        final int pivot = Partition(input, start, end);
        QuickSort(input, start, pivot-1);
        QuickSort(input, pivot+1, end);
    }

    public void QuickSort(final ArrayList<Integer> input) {
        QuickSort(input, 0, input.size()-1);
    }

    @Test
    public void Test() {
        final int count = 10000;
        final int size = 1000;
        for (int i = 0; i < count; ++i) {
            final ArrayList<Integer> input = Util.GenerateRandomInput(size);
            QuickSort(input);
            Assert.assertTrue(Util.VerifyIsSorted(input));

            final ArrayList<Integer> inputWithRepeatNumbers = Util.GenerateRandomInputWithRepeatedNumbers(size, size);
            QuickSort(inputWithRepeatNumbers);
            Assert.assertTrue(Util.VerifyIsSorted(inputWithRepeatNumbers));
        }
    }
}
