package com.Sorting;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Sisu on 10/2/2016.
 */
public class HeapSort {
    private int leftChild(final int i) { return 2 * i + 1; }
    private int rightChild(final int i) { return 2 * i + 2; }

    /**
     * assume idx is within input, and each child is a heap.
     * Now maintain the input[idx] into the heap
     */
    private void maxHeapify(final List<Integer> input, final int idx, final int heapSize) {
        if (idx >= input.size() || idx < 0) { return; }
        int left = leftChild(idx);
        int right = rightChild(idx);
        int maxValueIdx = idx;
        if (left <= heapSize && input.get(left) > input.get(maxValueIdx)) { maxValueIdx = left; }
        if (right <= heapSize && input.get(right) > input.get(maxValueIdx)) { maxValueIdx = right; }
        if (maxValueIdx != idx) {
            Collections.swap(input, idx, maxValueIdx);
            maxHeapify(input, maxValueIdx, heapSize);
        }
    }

    private void buildMaxHeap(final List<Integer> input) {
        for (int i = (input.size()-1) / 2; i >= 0; --i) {
            maxHeapify(input, i, input.size()-1);
        }
    }

    public void heapSort(final List<Integer> input) {
        buildMaxHeap(input);
        int heapSize = input.size()-1;
        for (int i = input.size()-1; i >= 1; i--) {
            Collections.swap(input, 0, i);
            maxHeapify(input, 0, --heapSize);
        }
    }

    @Test
    public void test() {
        final int count = 1000;
        final int size = 1000;
        for (int i = 0; i < count; ++i) {
            final List<Integer> input = Util.generateRandomInput(size);
            heapSort(input);
            Assert.assertTrue(Util.verifyIsSorted(input));

            final List<Integer> inputWithRepeatNumbers = Util.generateRandomInputWithRepeatedNumbers(size, size);
            heapSort(inputWithRepeatNumbers);
            Assert.assertTrue(Util.verifyIsSorted(inputWithRepeatNumbers));
        }
    }
}
