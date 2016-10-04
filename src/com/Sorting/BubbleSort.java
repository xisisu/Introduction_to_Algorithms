package com.Sorting;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Sisu on 10/2/2016.
 */
public class BubbleSort {
    public void bubbleSort(final ArrayList<Integer> input) {
        for (int i = 0; i < input.size(); ++i) {
            for (int j = 0; j < input.size()-1-i; ++j) {
                if(input.get(j) > input.get(j+1)) {
                    Collections.swap(input, j, j+1);
                }
            }
        }
    }

    @Test
    public void test() {
        final int count = 1000;
        final int size = 1000;
        for (int i = 0; i < count; ++i) {
            final ArrayList<Integer> input = Util.generateRandomInput(size);
            bubbleSort(input);
            Assert.assertTrue(Util.verifyIsSorted(input));

            final ArrayList<Integer> inputWithRepeatNumbers = Util.generateRandomInputWithRepeatedNumbers(size, size);
            bubbleSort(inputWithRepeatNumbers);
            Assert.assertTrue(Util.verifyIsSorted(inputWithRepeatNumbers));
        }
    }
}
