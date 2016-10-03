package com.Sorting;

import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Sisu on 10/2/2016.
 */
public class BubbleSort {
    public void BubbleSort(final ArrayList<Integer> input) {
        for (int i = 0; i < input.size(); ++i) {
            for (int j = 0; j < input.size()-1-i; ++j) {
                if(input.get(j) > input.get(j+1)) {
                    Collections.swap(input, j, j+1);
                }
            }
        }
    }

    @Test
    public void Test() {
        final int count = 1000;
        final int size = 1000;
        for (int i = 0; i < count; ++i) {
            final ArrayList<Integer> input = Util.GenerateRandomInput(size);
            BubbleSort(input);
            Assert.assertTrue(Util.VerifyIsSorted(input));

            final ArrayList<Integer> inputWithRepeatNumbers = Util.GenerateRandomInputWithRepeatedNumbers(size, size);
            BubbleSort(inputWithRepeatNumbers);
            Assert.assertTrue(Util.VerifyIsSorted(inputWithRepeatNumbers));
        }
    }
}
