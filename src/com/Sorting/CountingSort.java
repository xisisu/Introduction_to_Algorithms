package com.Sorting;

import com.sun.deploy.util.ArrayUtil;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Sisu on 10/2/2016.
 */
public class CountingSort {
    public void CountingSort(final ArrayList<Integer> input, final int upperBound) {
        final int[] count = new int[upperBound];
        for (int i = 0; i < count.length; ++i) { count[i] = 0; }
        for (final Integer v : input) { ++count[v]; }
        for (int i = 1; i < count.length; ++i) { // so count[i] contains number of elements less than or equal to i
            count[i] += count[i-1];
        }
        final int[] result = new int[input.size()];
        for (int i = 0; i < result.length; ++i) { result[i] = 0; }
        for (int i = input.size()-1; i >= 0; --i) {
            result[count[input.get(i)]-1] = input.get(i);
            --count[input.get(i)];
        }
        input.clear();
        for (int i = 0; i < result.length; ++i) { input.add(result[i]); }
    }

    @Test
    public void Test() {
        final int count = 1000;
        final int size = 1000;
        for (int i = 0; i < count; ++i) {
            final ArrayList<Integer> inputWithRepeatNumbers = Util.GenerateRandomInputWithRepeatedNumbers(size, size);
            CountingSort(inputWithRepeatNumbers, size);
            Assert.assertTrue(Util.VerifyIsSorted(inputWithRepeatNumbers));
        }
    }
}
