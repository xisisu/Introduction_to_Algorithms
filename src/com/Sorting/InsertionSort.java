package com.Sorting;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sisu on 10/2/2016.
 */
public class InsertionSort {
    public void insertionSort(final List<Integer> input) {
        for (int i = 1; i < input.size(); ++i) {
            final Integer key = input.get(i);
            int j = i-1;
            while (j >= 0 && input.get(j) >= key) {
                input.set(j+1, input.get(j));
                --j;
            }
            input.set(j+1, key);
        }
    }

    @Test
    public void test() {
        final int count = 1000;
        final int size = 1000;
        for (int i = 0; i < count; ++i) {
            final List<Integer> input = Util.generateRandomInput(size);
            insertionSort(input);
            Assert.assertTrue(Util.verifyIsSorted(input));

            final List<Integer> inputWithRepeatNumbers = Util.generateRandomInputWithRepeatedNumbers(size, size);
            insertionSort(inputWithRepeatNumbers);
            Assert.assertTrue(Util.verifyIsSorted(inputWithRepeatNumbers));
        }
    }
}
