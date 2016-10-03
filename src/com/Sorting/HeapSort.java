package com.Sorting;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Sisu on 10/2/2016.
 */
public class HeapSort {
    private int LeftChild(final int i) { return 2 * i + 1; }
    private int RightChild(final int i) { return 2 * i + 2; }

    /**
     * assume idx is within input, and each child is a heap.
     * Now maintain the input[idx] into the heap
     */
    private void MaxHeapify(final ArrayList<Integer> input, final int idx, final int heapSize) {
        if (idx >= input.size() || idx < 0) { return; }
        int left = LeftChild(idx);
        int right = RightChild(idx);
        int maxValueIdx = idx;
        if (left <= heapSize && input.get(left) > input.get(maxValueIdx)) { maxValueIdx = left; }
        if (right <= heapSize && input.get(right) > input.get(maxValueIdx)) { maxValueIdx = right; }
        if (maxValueIdx != idx) {
            Collections.swap(input, idx, maxValueIdx);
            MaxHeapify(input, maxValueIdx, heapSize);
        }
    }

    private void BuildMaxHeap(final ArrayList<Integer> input) {
        for (int i = (input.size()-1) / 2; i >= 0; --i) {
            MaxHeapify(input, i, input.size()-1);
        }
    }

    public void HeapSort(final ArrayList<Integer> input) {
        BuildMaxHeap(input);
        int heapSize = input.size()-1;
        for (int i = input.size()-1; i >= 1; i--) {
            Collections.swap(input, 0, i);
            MaxHeapify(input, 0, --heapSize);
        }
    }

    @Test
    public void Test() {
        final int count = 1000;
        final int size = 1000;
        for (int i = 0; i < count; ++i) {
            final ArrayList<Integer> input = Util.GenerateRandomInput(size);
            HeapSort(input);
            Assert.assertTrue(Util.VerifyIsSorted(input));

            final ArrayList<Integer> inputWithRepeatNumbers = Util.GenerateRandomInputWithRepeatedNumbers(size, size);
            HeapSort(inputWithRepeatNumbers);
            Assert.assertTrue(Util.VerifyIsSorted(inputWithRepeatNumbers));
        }
    }
}
