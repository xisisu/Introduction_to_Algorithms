package com.AdvancedDesignAndAnalysisTechniques;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Dynamic programming, one variable to record temp result
 *
 * line_1     a1,1  -->  a1,2 --> a1,3 --> ... --> a1,n-1 --> a1,n
 *          /      \                                              \
 *        e1        \                                              \
 *       /         t1,2 (t1,2 means from a1,1 to a2,2)             x1
 * enter             \  (t2,1 means from a2,1 to a1,2)                  exit
 *       \            \                                            x2
 *        e2           \                                           /
 *          \           \                                         /
 * line_2     a2,1  -->  a2,2 --> a2,3 --> ... --> a2,n-1 --> a2,n
 *
 *
 *
 * Created by xisisu on 11/6/16.
 */
public class AssemblyLineScheduling {
    public int assemblyLinesScheduling(
            final List<Integer> line1
            , final List<Integer> line2
            , final List<Integer> cost1To2
            , final List<Integer> cost2To1
            , final Integer e1
            , final Integer e2
            , final Integer x1
            , final Integer x2)
    {
        final Integer arraySize = line1.size();
        // record results
        final List<Integer> r1 = new ArrayList<>(Collections.nCopies(arraySize, -1));
        final List<Integer> r2 = new ArrayList<>(Collections.nCopies(arraySize, -1));
        r1.set(0, 1);
        r2.set(0, 2);

        final List<Integer> f1 = new ArrayList<>(Collections.nCopies(arraySize, -1));
        final List<Integer> f2 = new ArrayList<>(Collections.nCopies(arraySize, -1));
        f1.set(0, e1 + line1.get(0));
        f2.set(0, e2 + line2.get(0));
        for (int j = 1; j < arraySize; ++j) {
            // process line1
            final Integer val1 = f1.get(j-1) + line1.get(j);
            final Integer val2 = f2.get(j-1) + line1.get(j) + cost2To1.get(j-1);
            if (val1 < val2) {
                f1.set(j, val1);
                r1.set(j, 1);
            } else {
                f1.set(j, val2);
                r1.set(j, 2);
            }

            // process line2
            final Integer val3 = f2.get(j-1) + line2.get(j);
            final Integer val4 = f1.get(j-1) + line2.get(j) + cost1To2.get(j-1);
            if (val3 < val4) {
                f2.set(j, val3);
                r2.set(j, 1);
            } else {
                f2.set(j, val4);
                r2.set(j, 2);
            }
        }

        // get final result
        final Integer val1 = f1.get(arraySize-1) + x1;
        final Integer val2 = f2.get(arraySize-1) + x2;
        if (val1 < val2) {
            System.out.println("pick line 1 to start, then " + r1);
        } else {
            System.out.println("pick line 2 to start, then " + r2);
        }

        System.out.println(f1);
        System.out.println(f2);

        return Math.min(val1, val2);
    }

    @Test
    public void test() {
        // example from Figure 15.2
        final List<Integer> line1    = Arrays.asList(7,9,3,4,8,4);
        final List<Integer> line2    = Arrays.asList(8,5,6,4,5,7);
        final List<Integer> cost1To2 = Arrays.asList(2,3,1,3,4);
        final List<Integer> cost2To1 = Arrays.asList(2,1,2,2,1);
        final Integer e1 = 2;
        final Integer e2 = 4;
        final Integer x1 = 3;
        final Integer x2 = 2;

        Assert.assertEquals(38, assemblyLinesScheduling(line1, line2, cost1To2, cost2To1, e1, e2, x1, x2));
    }

}
