package com.Sorting;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by Sisu on 10/2/2016.
 */
public class FindMinAndMax {
    public ArrayList<Integer> FindMinAndMax(final ArrayList<Integer> input) {
        if (input.size() <= 1) { return input; }
        int min = Integer.min(input.get(0), input.get(1));
        int max = Integer.max(input.get(0), input.get(1));
        for (int i = 2; i < input.size(); i += 2) {
            int val1 = input.get(i);
            int val2 = i+1 < input.size() ? input.get(i+1) : val1;
            int tempMin = Integer.min(val1, val2);
            int tempMax = Integer.max(val1, val2);
            min = Integer.min(min, tempMin);
            max = Integer.max(max, tempMax);
        }

        final ArrayList<Integer> result = new ArrayList<>();
        result.add(min);
        result.add(max);
        return result;
    }

    @Test
    public void Test() {
        final int count = 1000;
        final int size = 1000;
        for (int i = 0; i < count; ++i) {
            final ArrayList<Integer> input = Util.GenerateRandomInput(size); // from [1, size]
            final ArrayList<Integer> result = FindMinAndMax(input);
            Assert.assertEquals(result.size(), 2);
            Assert.assertEquals(result.get(0), Integer.valueOf(1));
            Assert.assertEquals(result.get(1), Integer.valueOf(size));
        }
    }


}
