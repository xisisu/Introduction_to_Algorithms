package com.Sorting;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by Sisu on 10/2/2016.
 */
public class MergeSort {
    private void Combine(final ArrayList<Integer> input, final int s1, final int e1, final int s2, final int e2) {
        final ArrayList<Integer> temp = new ArrayList<>();
        int idx1 = s1, idx2 = s2;
        while (idx1 <= e1 && idx2 <= e2) {
            if (input.get(idx1) < input.get(idx2)) {
                temp.add(input.get(idx1));
                ++idx1;
            } else {
                temp.add(input.get(idx2));
                ++idx2;
            }
        }

        while (idx1 <= e1) { temp.add(input.get(idx1++)); }
        while (idx2 <= e2) { temp.add(input.get(idx2++)); }

        int idx = 0;
        for (int i = s1; i <= e2; ++i) {
            input.set(i, temp.get(idx++));
        }
    }

    /**
     * merge sort for [start, end], inclusive
     */
    private void MergeSort(final ArrayList<Integer> input, final int start, final int end) {
        if (start >= end) { return; }
        int mid = start + (end-start)/2;
        MergeSort(input, start, mid);
        MergeSort(input, mid+1, end);
        Combine(input, start, mid, mid+1, end);
    }


    public void MergeSort(final ArrayList<Integer> input) {
        MergeSort(input, 0, input.size()-1);
    }

    @Test
    public void Test() {
        final int count = 1000;
        final int size = 1000;
        for (int i = 0; i < count; ++i) {
            final ArrayList<Integer> input = Util.GenerateRandomInput(size);
            MergeSort(input);
            Assert.assertTrue(Util.VerifyIsSorted(input));
        }
    }
}
